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
	 * ��������ʱ����ڵĺ�ͬ���
	 * 
	 * @param panel
	 * @throws BusinessException
	 */
	public static UFDouble calculateMnyBetweenDate(String pk_contract,
			UFDate ud_fist, UFDate ud_last,Object pk_house) throws BusinessException {

		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

		// ������ҳǩ
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

		// ������ҳǩ
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

		//�����
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

		// ��������ȥ��ֵ�ǰ��ʼ���ڣ���������
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		for (int k = 0; k < zrow; k++) {
			ContractZzqVO zzqvo = zzqlist.get(k);
			Object obj3 = zzqvo.getDstartdate();
			UFDate ud3 = new UFDate(obj3.toString());
			UFDouble zzrate = getUFdobj(zzqvo.getNzzrate());
			if (ud3.afterDate(ud_last)) {// ���������ڼ�֮�󣬲�������
				break;
			} else if (ud3.beforeDate(ud_fist)) {// ��������֮ǰ������������
				daymny = daymny.multiply(zzrate).div(100).add(daymny);
			} else {// ���������м�(�ɵ�����ʼ��ֹ����)�����
					// ���
				UFDate ud4 = CalendarUtls.getBeforeFirstDay(ud3);
				if (!ud4.beforeDate(ud_fist)) {// ǰ�벿�����ϴ����
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
		
		//ҵ����ҳǩ
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
		
		// ����ʣ�µ���ʼ��������
		Map<String, Object> mapzj = new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist);
		mapzj.put("denddate", ud_last);
		mapzj.put("ndaymny", daymny);
		int day = CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
		mapzj.put("nday", day);
		listmap.add(mapzj);

		// �۳�������
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

				if (ud3.afterDate(ud2) || ud4.beforeDate(ud1)) {// ����������ڿ�ʼ���������⿪ʼ���ڽ�����������
					continue;
				}

				if (ud3.beforeDate(ud1)) {// �������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
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
		
		// �����ܽ��
		UFDouble udrow = new UFDouble(0);
		for (Map<String, Object> maps : listmap) {

			UFDouble price = getUFdobj(maps.get("ndaymny"));
			Integer days = Integer.valueOf(maps.get("nday").toString());
			udrow = price.multiply(days).add(udrow, 2);
		}

		return udrow;
	}

	/**
	 * ��������ʱ����ڵ�ĳ�������Ľ��
	 * 
	 * @param panel
	 * @throws BusinessException
	 */
	public static UFDouble calculateHouseMnyBetweenDate(BillCardPanel panel,
			UFDate ud_fist, UFDate ud_last, Object pk_house)
			throws BusinessException {

		// ������ҳǩ
		BillModel zmodel = panel.getBillModel("pk_contract_zzq");
		int zrow = zmodel.getRowCount();

		// ������ҳǩ
		BillModel mmodel = panel.getBillModel("pk_contract_mzq");
		int mrow = mmodel.getRowCount();

		// ��ȡ���������
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

		// ��������ȥ��ֵ�ǰ��ʼ���ڣ���������
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		for (int k = 0; k < zrow; k++) {

			Object obj3 = zmodel.getValueAt(k, "dstartdate");
			UFDate ud3 = new UFDate(obj3.toString());
			UFDouble zzrate = getUFdobj(zmodel.getValueAt(k, "nzzrate"));
			if (ud3.afterDate(ud_last)) {// ���������ڼ�֮�󣬲�������
				break;
			} else if (ud3.beforeDate(ud_fist)) {// ��������֮ǰ������������
				daymny = daymny.multiply(zzrate).div(100).add(daymny);
			} else {// ���������м�(�ɵ�����ʼ��ֹ����)�����
					// ���
				UFDate ud4 = CalendarUtls.getBeforeFirstDay(ud3);
				if (!ud4.beforeDate(ud_fist)) {// ǰ�벿�����ϴ����
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
		// ����ʣ�µ���ʼ��������
		Map<String, Object> mapzj = new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist);
		mapzj.put("denddate", ud_last);
		mapzj.put("ndaymny", daymny);
		int day = CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
		mapzj.put("nday", day);
		listmap.add(mapzj);

		// �۳�������
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

				if (ud3.afterDate(ud2) || ud4.beforeDate(ud1)) {// ����������ڿ�ʼ���������⿪ʼ���ڽ�����������
					continue;
				}

				if (ud3.beforeDate(ud1)) {// �������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
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

		// �����ܽ��
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
