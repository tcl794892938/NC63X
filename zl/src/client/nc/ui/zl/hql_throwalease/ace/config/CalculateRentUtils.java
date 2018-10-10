package nc.ui.zl.hql_throwalease.ace.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractMzqVO;
import nc.vo.zl.tcl_contract.ContractVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;
import nc.vo.zl.tcl_contract.ContractZzqVO;

public class CalculateRentUtils {

	/**
	 * 计算两个时间段内的合同金额
	 * 
	 * @param panel
	 * @throws BusinessException
	 */
	public static UFDouble calculateMnyBetweenDate(String pk_contract,
			UFDate ud_fist, UFDate ud_last,Object pk_house) throws BusinessException {

		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

		// 增长期页签
		String sql1 = "select * from zl_contract_zzq where nvl(dr,0)=0 and pk_contract='"
				+ pk_contract + "' order by dstartdate";
		List<ContractZzqVO> zzqlist = (List<ContractZzqVO>) iQ.executeQuery(
				sql1, new BeanListProcessor(ContractZzqVO.class));
		int zrow = zzqlist.size();
		/*if(zzqlist.size()==0||zzqlist==null){
			zrow=0;
		}else{
			zrow=zzqlist.size();
		}*/

		// 免租期页签
		String sql2 = "select * from zl_contract_mzq where nvl(dr,0)=0 and pk_contract='"
				+ pk_contract + "' order by dstartdate";
		List<ContractMzqVO> mzqlist = (List<ContractMzqVO>) iQ.executeQuery(
				sql2, new BeanListProcessor(ContractMzqVO.class));
		int mrow = mzqlist.size();
		/*if (mzqlist.size() == 0 || mzqlist == null) {
			mrow = 0;
		} else {
			mrow = mzqlist.size();
		}*/

		//日租金
		/*String sql3 = "select * from zl_contract where nvl(dr,0)=0 and pk_contract='"
				+ pk_contract + "'";
		ContractVO cvo = (ContractVO) iQ.executeQuery(sql3, new BeanProcessor(
				ContractVO.class));
		Object objday = cvo.getNdaymny();

		UFDouble daymny = getUFdobj(objday);*/
		String sql3 = "select * from zl_contract_house where nvl(dr,0)=0 and pk_contract='"
				+ pk_contract + "' and pk_house='"+pk_house+"'";
		ContractHouseVO chvo = (ContractHouseVO) iQ.executeQuery(sql3, new BeanProcessor(
				ContractHouseVO.class));
		Object objday = chvo.getNdaymny();

		UFDouble daymny = getUFdobj(objday);

		// 按增长期去拆分当前起始日期，结束日期
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		for (int k = 0; k < zrow; k++) {
			ContractZzqVO zzqvo = zzqlist.get(k);
			Object obj3 = zzqvo.getDstartdate();
			UFDate ud3 = new UFDate(obj3.toString());
			UFDouble zzrate = getUFdobj(zzqvo.getNzzrate());
			if (ud3.afterDate(ud_last)) {// 增长期在期间之后，不做操作
				break;
			} else if (ud3.beforeDate(ud_fist)) {// 增长期在之前，需计算日租金
				daymny = daymny.multiply(zzrate).div(100).add(daymny);
			} else {// 介于日期中间(可等于起始终止日期)，拆分
					// 租金
				UFDate ud4 = CalendarUtls.getBeforeFirstDay(ud3);
				if (!ud4.beforeDate(ud_fist)) {// 前半部分用上次租金
					Map<String, Object> mapzj = new HashMap<String, Object>();
					mapzj.put("dstartdate", ud_fist);
					mapzj.put("denddate", ud4);
					mapzj.put("ndaymny", daymny);
					int day = CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
					mapzj.put("nday", day);
					listmap.add(mapzj);
				}

				daymny = daymny.multiply(zzrate).div(100).add(daymny);
				ud_fist = ud3;
			}
		}
		
		//业务拆分页签
		/*String sql_ywcf="select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_house='"+pk_house+"' " +
				"and pk_contract='"+pk_contract+"' and denddate='"+ud_last+"'";
		List<ContractYwcfVO> ywvo=(List<ContractYwcfVO>)iQ.executeQuery(sql_ywcf, new BeanListProcessor(ContractYwcfVO.class));
		UFDate ud_start=ywvo.get(0).getDstartdate();
		Double money=Double.parseDouble(ywvo.get(0).getNysmny().toString());
		if(ywvo.size()>1){
			money+=Double.parseDouble(ywvo.get(1).getNysmny().toString());
		}
		int allday=CalendarUtls.getBetweenTwoDays(ud_start, ud_last);
		daymny=new UFDouble(money).div(allday);*/
		
		// 加入剩下的起始结束日期
		Map<String, Object> mapzj = new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist);
		mapzj.put("denddate", ud_last);
		mapzj.put("ndaymny", daymny);
		int day = CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
		mapzj.put("nday", day);
		listmap.add(mapzj);

		// 扣除免租期
		for (Map<String, Object> maps : listmap) {

			UFDate ud1 = new UFDate(maps.get("dstartdate").toString());
			UFDate ud2 = new UFDate(maps.get("denddate").toString());
			Integer days = Integer.valueOf(maps.get("nday").toString());

			for (int k = 0; k < mrow; k++) {

				Object obj3 = mzqlist.get(k).getDstartdate();
				Object obj4 = mzqlist.get(k).getDenddate();
				UFDate ud3 = new UFDate(obj3.toString());
				UFDate ud4 = new UFDate(obj4.toString());
				UFDate start = new UFDate();
				UFDate end = new UFDate();

				if (ud3.afterDate(ud2) || ud4.beforeDate(ud1)) {// 免租结束早于开始，或者免租开始迟于结束，不计算
					continue;
				}

				if (ud3.beforeDate(ud1)) {// 免租早于开始日期取开始日期,否则取免租开始日期
					start = ud1;
				} else {
					start = ud3;
				}

				if (ud4.afterDate(ud2)) {
					end = ud2;
				} else {
					end = ud4;
				}

				int mzday = CalendarUtls.getBetweenTwoDays(start, end);

				days = days - mzday;
			}

			maps.put("nday", days);
		}
		
		// 计算总金额
		UFDouble udrow = new UFDouble(0);
		for (Map<String, Object> maps : listmap) {

			UFDouble price = getUFdobj(maps.get("ndaymny"));
			Integer days = Integer.valueOf(maps.get("nday").toString());
			udrow = price.multiply(days).add(udrow, 2);
		}

		return udrow;
	}

	/**
	 * 计算两个时间段内的某个房产的金额
	 * 
	 * @param panel
	 * @throws BusinessException
	 */
	public static UFDouble calculateHouseMnyBetweenDate(BillCardPanel panel,
			UFDate ud_fist, UFDate ud_last, Object pk_house)
			throws BusinessException {

		// 增长期页签
		BillModel zmodel = panel.getBillModel("pk_contract_zzq");
		int zrow = zmodel.getRowCount();

		// 免租期页签
		BillModel mmodel = panel.getBillModel("pk_contract_mzq");
		int mrow = mmodel.getRowCount();

		// 获取房产日租金
		BillModel hmodel = panel.getBillModel("pk_contract_house");
		int hrow = hmodel.getRowCount();

		Object objday = null;
		for (int i = 0; i < hrow; i++) {
			if (getColvalue(hmodel.getValueObjectAt(i, "pk_house")).equals(
					pk_house)) {
				objday = hmodel.getValueAt(i, "ndaymny");
				break;
			}
		}

		UFDouble daymny = getUFdobj(objday);

		// 按增长期去拆分当前起始日期，结束日期
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		for (int k = 0; k < zrow; k++) {

			Object obj3 = zmodel.getValueAt(k, "dstartdate");
			UFDate ud3 = new UFDate(obj3.toString());
			UFDouble zzrate = getUFdobj(zmodel.getValueAt(k, "nzzrate"));
			if (ud3.afterDate(ud_last)) {// 增长期在期间之后，不做操作
				break;
			} else if (ud3.beforeDate(ud_fist)) {// 增长期在之前，需计算日租金
				daymny = daymny.multiply(zzrate).div(100).add(daymny);
			} else {// 介于日期中间(可等于起始终止日期)，拆分
					// 租金
				UFDate ud4 = CalendarUtls.getBeforeFirstDay(ud3);
				if (!ud4.beforeDate(ud_fist)) {// 前半部分用上次租金
					Map<String, Object> mapzj = new HashMap<String, Object>();
					mapzj.put("dstartdate", ud_fist);
					mapzj.put("denddate", ud4);
					mapzj.put("ndaymny", daymny);
					int day = CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
					mapzj.put("nday", day);
					listmap.add(mapzj);
				}

				daymny = daymny.multiply(zzrate).div(100).add(daymny);
				ud_fist = ud3;
			}
		}
		// 加入剩下的起始结束日期
		Map<String, Object> mapzj = new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist);
		mapzj.put("denddate", ud_last);
		mapzj.put("ndaymny", daymny);
		int day = CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
		mapzj.put("nday", day);
		listmap.add(mapzj);

		// 扣除免租期
		for (Map<String, Object> maps : listmap) {

			UFDate ud1 = new UFDate(maps.get("dstartdate").toString());
			UFDate ud2 = new UFDate(maps.get("denddate").toString());
			Integer days = Integer.valueOf(maps.get("nday").toString());

			for (int k = 0; k < mrow; k++) {

				Object obj3 = mmodel.getValueAt(k, "dstartdate");
				Object obj4 = mmodel.getValueAt(k, "denddate");
				UFDate ud3 = new UFDate(obj3.toString());
				UFDate ud4 = new UFDate(obj4.toString());
				UFDate start = new UFDate();
				UFDate end = new UFDate();

				if (ud3.afterDate(ud2) || ud4.beforeDate(ud1)) {// 免租结束早于开始，或者免租开始迟于结束，不计算
					continue;
				}

				if (ud3.beforeDate(ud1)) {// 免租早于开始日期取开始日期,否则取免租开始日期
					start = ud1;
				} else {
					start = ud3;
				}

				if (ud4.afterDate(ud2)) {
					end = ud2;
				} else {
					end = ud4;
				}

				int mzday = CalendarUtls.getBetweenTwoDays(start, end);

				days = days - mzday;
			}

			maps.put("nday", days);
		}

		// 计算总金额
		UFDouble udrow = new UFDouble(0);
		for (Map<String, Object> maps : listmap) {

			UFDouble price = getUFdobj(maps.get("ndaymny"));
			Integer days = Integer.valueOf(maps.get("nday").toString());
			udrow = price.multiply(days).add(udrow, 2);
		}

		return udrow;
	}

	private static UFDouble getUFdobj(Object obj) {
		return obj == null ? new UFDouble(0) : new UFDouble(obj.toString());
	}

	private static Object getColvalue(Object obj) {

		if (obj == null) {
			return obj;
		} else if (obj instanceof DefaultConstEnum) {
			return ((DefaultConstEnum) obj).getValue();
		}

		return null;
	}

}
