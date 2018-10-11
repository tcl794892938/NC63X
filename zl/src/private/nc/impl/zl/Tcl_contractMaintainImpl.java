package nc.impl.zl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.ace.AceTcl_contractPubServiceImpl;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.itf.zl.ITcl_contractMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.ui.zl.tcl_contract.ace.config.CalendarUtls;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.lm_customer.Customer_fcxxVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractBzjVO;
import nc.vo.zl.tcl_contract.ContractCustVO;
import nc.vo.zl.tcl_contract.ContractCwcfVO;
import nc.vo.zl.tcl_contract.ContractFkmxVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractMzqVO;
import nc.vo.zl.tcl_contract.ContractVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;
import nc.vo.zl.tcl_contract.ContractZjmxVO;
import nc.vo.zl.tcl_contract.ContractZqfyVO;
import nc.vo.zl.tcl_contract.ContractZqfycfVO;
import nc.vo.zl.tcl_contract.ContractZqmxVO;
import nc.vo.zl.tcl_contract.ContractZzqVO;

public class Tcl_contractMaintainImpl extends AceTcl_contractPubServiceImpl
		implements ITcl_contractMaintain {

	@Override
	public void delete(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		if (clientFullVOs.length > 1) {
			throw new BusinessException("请选择一条单据！");
		}
		super.pubdeleteBills(clientFullVOs, originBills);
		// 删除后更改房产信息
		ContractVO cvo = originBills[0].getParentVO();
		if (cvo.getVersion() == -1 && cvo.getVbilltypecode().equals("H420")) {

			ContractHouseVO[] bvos = originBills[0].getChildHouseVO();
			if (bvos != null && bvos.length > 0) {
				String str = "";
				for (ContractHouseVO bvo : bvos) {
					str += "'" + bvo.getPk_house() + "',";
				}
				str = str.substring(0, str.lastIndexOf(","));

				String sql = "update zl_housesource set housestate='"
						+ AbsEnumType.HOUSE_KZ + "' where pk_housesource in("
						+ str + ")";
				BaseDAO dao = new BaseDAO();
				dao.executeUpdate(sql);
			}
		}
	}

	@Override
	public AggContractVO[] insert(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {

		AggContractVO[] bills = super
				.pubinsertBills(clientFullVOs, originBills);
		if (bills.length > 1) {
			throw new BusinessException("请选择一条单据！");
		}
		ContractVO cvo = bills[0].getParentVO();
		if (cvo.getVersion() == -1 && cvo.getVbilltypecode().equals("H420")) {

			ContractHouseVO[] bvos = bills[0].getChildHouseVO();
			if (bvos != null && bvos.length > 0) {
				String str = "";
				for (ContractHouseVO bvo : bvos) {
					str += "'" + bvo.getPk_house() + "',";
				}
				str = str.substring(0, str.lastIndexOf(","));

				String sql = "update zl_housesource set housestate='"
						+ AbsEnumType.HOUSE_DZ + "' where pk_housesource in("
						+ str + ")";
				BaseDAO dao = new BaseDAO();
				dao.executeUpdate(sql);
			}

		}
		return bills;
	}

	@Override
	public AggContractVO[] update(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {

		AggContractVO[] bills = super
				.pubupdateBills(clientFullVOs, originBills);
		if (bills.length > 1) {
			throw new BusinessException("请选择一条单据！");
		}
		ContractVO cvo = bills[0].getParentVO();
		if (cvo.getVersion() == -1 && cvo.getVbilltypecode().equals("H420")) {

			ContractHouseVO[] bvos = bills[0].getChildHouseVO();
			ContractHouseVO[] bvos2 = originBills[0].getChildHouseVO();

			if (bvos != null && bvos.length > 0) {
				String str = "";
				for (ContractHouseVO bvo : bvos) {
					int a = 0;
					for (ContractHouseVO bvo2 : bvos2) {
						if (bvo2.getPk_house().equals(bvo.getPk_house())) {
							a += 1;
						}
					}
					if (a == 0) {
						str += "'" + bvo.getPk_house() + "',";
					}
				}
				str = str.length() == 0 ? null : str.substring(0,
						str.lastIndexOf(","));
				String sql = "update zl_housesource set housestate='"
						+ AbsEnumType.HOUSE_DZ + "' where pk_housesource in("
						+ str + ")";
				BaseDAO dao = new BaseDAO();
				dao.executeUpdate(sql);
			}
			if (bvos2 != null && bvos2.length > 0) {
				String str = "";
				for (ContractHouseVO bvo2 : bvos2) {
					int a = 0;
					for (ContractHouseVO bvo : bvos) {
						if (bvo.getPk_house().equals(bvo2.getPk_house())) {
							a += 1;
						}
					}
					if (a == 0) {
						str += "'" + bvo2.getPk_house() + "',";
					}
				}
				str = str.length() == 0 ? null : str.substring(0,
						str.lastIndexOf(","));
				String sql = "update zl_housesource set housestate='"
						+ AbsEnumType.HOUSE_KZ + "' where pk_housesource in("
						+ str + ")";
				BaseDAO dao = new BaseDAO();
				dao.executeUpdate(sql);
			}

		}
		return bills;
	}

	@Override
	public AggContractVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggContractVO[] save(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggContractVO[] unsave(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggContractVO[] approve(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		AggContractVO[] bills = super.pubapprovebills(clientFullVOs,
				originBills);

		if (bills.length > 1) {
			throw new BusinessException("请选择一条单据！");
		}

		// 合同管理审核通过时保留0号版本
		ContractVO cvo = bills[0].getParentVO();
		if (cvo.getVbillstatus() == 1 && cvo.getVbilltypecode().equals("H420")) {
			
			Object vdef1=cvo.getVdef1();
			if(vdef1!=null){
				String sql_u="update zl_contract set htstatus=4 where pk_contract='"+vdef1+"'";
				BaseDAO dao = new BaseDAO();
				dao.executeUpdate(sql_u);
			}

			List<Customer_fcxxVO> flist = new ArrayList<Customer_fcxxVO>();
			AggContractVO newvo = (AggContractVO) bills[0].clone();
			newvo.getParentVO().setPk_contract(null);// 清空主键
			newvo.getParentVO().setVbillno("111");// 随便设置一个，放置重新生成
			newvo.getParentVO().setVsrcid(cvo.getPk_contract());
			newvo.getParentVO().setVersion(0);
			for (ContractCustVO vo : newvo.getChildCustVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractHouseVO vo : newvo.getChildHouseVO()) {
				String pk = vo.getPk_contract();
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);

				// 插入客户信息
				Customer_fcxxVO fvo = new Customer_fcxxVO();
				fvo.setBuildnum(vo.getPk_building());
				fvo.setFcname(vo.getPk_house());
				fvo.setXsmj(vo.getNarea());
				fvo.setZlbegintime(new UFDateTime(newvo.getParentVO()
						.getDstartdate().toString()));
				fvo.setZlendtime(new UFDateTime(newvo.getParentVO()
						.getDenddate().toString()));
				fvo.setVdef1(pk);
				fvo.setDr(0);
				fvo.setPk_customer(newvo.getParentVO().getPk_customer());
				flist.add(fvo);
			}
			for (ContractMzqVO vo : newvo.getChildMzqVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractZzqVO vo : newvo.getChildZzqVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractBzjVO vo : newvo.getChildBzjVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractYwcfVO vo : newvo.getChildYwcfVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractCwcfVO vo : newvo.getChildCwcfVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractZqfyVO vo : newvo.getChildZqfyVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractZqfycfVO vo : newvo.getChildZqfycfVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractFkmxVO vo : newvo.getChildFkmxVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for (ContractZjmxVO vo : newvo.getChildZjmxVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}

			for (ContractZqmxVO vo : newvo.getChildZqmxVO()) {
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			// 插入数据
			AggContractVO[] vos = this.insert(new AggContractVO[] { newvo },
					null);
			String pk = vos[0].getParentVO().getPk_contract();
			String sql = "update zl_contract set vbillno='" + cvo.getVbillno()
					+ "' where pk_contract='" + pk + "'";
			BaseDAO dao = new BaseDAO();
			dao.executeUpdate(sql);

			// 插入客户
			dao.insertVOArray(flist.toArray(new Customer_fcxxVO[0]));
			// 更改房源
			ContractHouseVO[] bvos = bills[0].getChildHouseVO();
			if (bvos != null && bvos.length > 0) {
				String str = "";
				for (ContractHouseVO bvo : bvos) {
					str += "'" + bvo.getPk_house() + "',";
				}
				str = str.substring(0, str.lastIndexOf(","));

				String sqlf = "update zl_housesource set housestate='"
						+ AbsEnumType.HOUSE_YZ + "' where pk_housesource in("
						+ str + ")";
				dao.executeUpdate(sqlf);
			}
			// 推待收入确认单
			try {
				sendToRec(bills[0]);
				sendToCn(bills[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (cvo.getVbillstatus() == 1 && cvo.getVbilltypecode().equals("H430")) {// 合同修订审核

			AggContractVO oldagg = this.queryHTbyPK(cvo.getVsrcid());
			if (oldagg == null) {
				throw new BusinessException("来源合同数据异常！");
			}

			BaseDAO dao = new BaseDAO();
			// 查询未收款数据
			String sql = "select count(1) from zl_gather_b b left join zl_gather r on b.pk_gather=r.pk_gather "
					+ "where nvl(b.dr,0)=0 and nvl(r.dr,0)=0 and r.vbillstatus<>1 and exists "
					+ "(select 1 from zl_recbill re where re.pk_recbill=b.vsrcid and nvl(re.dr,0)=0 and re.vsrcid='"
					+ cvo.getVsrcid() + "')";

			Integer it = (Integer) dao.executeQuery(sql, new ColumnProcessor());
			if (it > 0) {
				throw new BusinessException("合同还存在未审批的收款单，请先去审核！");
			}

			// String pk_oldcust=hvo.getPk_customer();
			ContractVO hvo = oldagg.getParentVO();
			hvo.setHtname(cvo.getHtname());
			hvo.setHtcode(cvo.getHtcode());
			// hvo.setPk_customer(cvo.getPk_customer());
			/*
			 * hvo.setPaystyle(cvo.getPaystyle());
			 * hvo.setNrentprice(cvo.getNrentprice());
			 * hvo.setNarea(cvo.getNarea()); hvo.setNdaymny(cvo.getNdaymny());
			 * hvo.setNmonthmny(cvo.getNmonthmny());
			 * hvo.setNyearmny(cvo.getNyearmny()); hvo.setNmny(cvo.getNmny());
			 * hvo.setNyhmny(cvo.getNyhmny());
			 */

			dao.updateVO(hvo);

			Map<String, Object> map = new HashMap<String, Object>();
			ContractHouseVO[] housevos = oldagg.getChildHouseVO();
			for (ContractHouseVO vo : housevos) {
				map.put(vo.getPk_house(), vo.getPk_customer());
			}

			// 判断是否客户变更(根据房产的客户来变更)
			AggContractVO aggvo = (AggContractVO) bills[0].clone();
			ContractHouseVO[] newvos = aggvo.getChildHouseVO();
			for (ContractHouseVO vo : newvos) {// vdef3存放合同房产表体主键
				String pk_house = vo.getPk_house();
				String pk_cust = vo.getPk_customer();
				String oldpk = map.get(pk_house) == null ? "" : map.get(pk_house).toString();
				if (!oldpk.equals(pk_cust)) {// 客户不同，更新信息

					// 原始合同
					dao.executeUpdate("update zl_contract_house set pk_customer='"
							+ pk_cust
							+ "' where nvl(dr,0)=0 and pk_house='"
							+ pk_house + "' and pk_contract='"+ hvo.getPk_contract()+"'");
					//查询截止日期
					String sqlrq1="select min(dstartdate) from zl_contract_ywcf f where nvl(dr,0)=0 and pk_house='"
							+ pk_house
							+ "' and pk_contract='"
							+ hvo.getPk_contract()
							+ "' "
							+ " and not exists (select 1 from zl_gather_b b where b.vdef1=f.pk_contract_ywcf and b.vdef2='zl_contract_ywcf' and nvl(b.dr,0)=0)";
					Object objrq1=dao.executeQuery(sqlrq1, new ColumnProcessor());
					if(objrq1 == null||"".equals(objrq1)){
						Debug.debug("开始更新财务数据的日期未找到！");
					}else{
						String rq1=objrq1.toString().substring(0, 10);
						
						//更新业务
						dao.executeUpdate("update zl_contract_ywcf f set pk_customer='"
								+ pk_cust
								+ "' where nvl(dr,0)=0 and pk_house='"
								+ pk_house
								+ "' and pk_contract='"
								+ hvo.getPk_contract()
								+ "' "
								+ " and substr(f.dstartdate,0,10) >='"+rq1+"'");
						
						//更新财务
						dao.executeUpdate("update zl_contract_cwcf f set pk_customer='"
								+ pk_cust
								+ "' where nvl(dr,0)=0 and pk_house='"
								+ pk_house
								+ "' and pk_contract='"
								+ hvo.getPk_contract()
								+ "' "
								+ " and substr(f.dstartdate,0,10) >='"+rq1+"'");
						//更新待收入
						dao.executeUpdate("update zl_confirmation n set pk_customer ='"+pk_cust+"'  where nvl(dr,0)=0 and n.vdef2='zl_contract_cwcf' and  " +
								"exists(select 1 from zl_contract_cwcf f where f.pk_contract_cwcf =n.vdef1 and nvl(f.dr,0)=0 " +
								"and f.pk_house='"+pk_house+"' and f.pk_contract='"+hvo.getPk_contract()+"'  and substr(f.dstartdate,0,10) >='"+rq1+"' )");
						
						//============================原合同===================================
						dao.executeUpdate("update zl_contract_ywcf f set pk_customer='"
								+ pk_cust
								+ "' where nvl(dr,0)=0 and pk_house='"
								+ pk_house
								+ "' and pk_contract='"
								+ vo.getPk_contract()
								+ "' "
								+ " and substr(f.dstartdate,0,10) >='"+rq1+"'");
						dao.executeUpdate("update zl_contract_cwcf f set pk_customer='"
								+ pk_cust
								+ "' where nvl(dr,0)=0 and pk_house='"
								+ pk_house
								+ "' and pk_contract='"
								+ vo.getPk_contract()
								+ "' "
								+ " and substr(f.dstartdate,0,10) >='"+rq1+"'");
					}
					
					//更新周期
					dao.executeUpdate("update zl_contract_zqfy f set pk_customer='"
							+ pk_cust
							+ "' where nvl(dr,0)=0 and pk_house='"
							+ pk_house
							+ "' and pk_contract='"+ hvo.getPk_contract()+"'");
					
					//查询截止日期2
					String sqlrq2="select min(dstartdate) from zl_contract_zqfycf f where nvl(dr,0)=0 and pk_house='"
							+ pk_house
							+ "' and pk_contract='"
							+ hvo.getPk_contract()
							+ "' "
							+ " and not exists (select 1 from zl_gather_b b where b.vdef1=f.pk_contract_zqfycf and b.vdef2='zl_contract_zqfycf' and nvl(b.dr,0)=0)";
					Object objrq2=dao.executeQuery(sqlrq2, new ColumnProcessor());
					if(objrq2 == null||"".equals(objrq2)){
						Debug.debug("开始更新周期数据的日期未找到！");
					}else{
						String rq2=objrq2.toString().substring(0, 10);
						
						//更新周期拆分
						dao.executeUpdate("update zl_contract_zqfycf f set pk_customer='"
								+ pk_cust
								+ "' where nvl(dr,0)=0 and pk_house='"
								+ pk_house
								+ "' and pk_contract='"
								+ hvo.getPk_contract()
								+ "' "
								+ " and substr(f.dstartdate,0,10) >='"+rq2+"'");
						//更新待收入2
						dao.executeUpdate("update zl_confirmation n set pk_customer ='"+pk_cust+"'  where nvl(dr,0)=0 and n.vdef2='zl_contract_zqfycf' and  " +
								"exists(select 1 from zl_contract_zqfycf f where f.pk_contract_zqfycf =n.vdef1 and nvl(f.dr,0)=0 " +
								"and f.pk_house='"+pk_house+"' and f.pk_contract='"+hvo.getPk_contract()+"' and substr(f.dstartdate,0,10) >='"+rq2+"' )");
						
						//============================原合同======================================
						dao.executeUpdate("update zl_contract_zqfycf f set pk_customer='"
								+ pk_cust
								+ "' where nvl(dr,0)=0 and pk_house='"
								+ pk_house
								+ "' and pk_contract='"
								+ vo.getPk_contract()
								+ "' "
								+ " and substr(f.dstartdate,0,10) >='"+rq2+"'");
					}
					
					//==========================================================================修订合同======================================================
					// 修订合同
					
					
					dao.executeUpdate("update zl_contract_zqfy f set pk_customer='"
							+ pk_cust
							+ "' where nvl(dr,0)=0 and pk_house='"
							+ pk_house
							+ "' and pk_contract='"+ vo.getPk_contract()+"'");
							

					// 更新应收单
					String sqlys = "update zl_recbill z set pk_customer='"
							+ pk_cust
							+ "' where nvl(dr,0)=0 and vsrcid='"
							+ hvo.getPk_contract()
							+ "' "
							+ " and pk_house='"
							+ pk_house
							+ "' and not exists(select 1 from zl_gather_b r where r.vsrcid=z.pk_recbill and nvl(r.dr,0)=0)";
					dao.executeUpdate(sqlys);

					// 更新客户信息中心的房产情况
					String sql2 = "update zl_customer_fcxx set pk_customer='"
							+ pk_cust + "' where " + " pk_customer='" + oldpk
							+ "' and nvl(dr,0)=0";
					dao.executeUpdate(sql2);
				}
			}
			AggContractVO newvo = this.queryHTbyPK(aggvo.getParentVO()
					.getPk_contract());
			bills = new AggContractVO[] { newvo };
		}
		return bills;
	}

	@Override
	public AggContractVO[] unapprove(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {

		BaseDAO dao = new BaseDAO();

		AggContractVO[] bills = super.pubunapprovebills(clientFullVOs,
				originBills);

		if (bills.length > 1) {
			throw new BusinessException("请选择一条数据！");
		}

		// 合同管理取消审核时去除0号版本
		for (AggContractVO aggvo : originBills) {

			ContractVO cvo = aggvo.getParentVO();
			if (cvo.getVbillstatus() == 1
					&& cvo.getVbilltypecode().equals("H420")) {

				//检验是否有续租的合同
				String sql = "select count(*) from zl_contract where nvl(dr,0)=0 and vdef1='"+cvo.getPk_contract()+"'";
				Integer count = (Integer) dao.executeQuery(sql, new ColumnProcessor());
				if(count>0){
					throw new BusinessException("该合同已经续约，不能取消审核！");
				}
				
				// 校验是否有下游
				String sqlxy = "select count(1) from zl_contract t where pk_contract ='"
						+ cvo.getPk_contract()
						+ "' and "
						+ "exists(select 1 from zl_contract s where s.vsrcid=t.pk_contract and s.vbilltypecode='H430' and nvl(s.dr,0)=0)";
				Integer it = (Integer) dao.executeQuery(sqlxy,
						new ColumnProcessor());
				if (it > 0) {
					throw new BusinessException("单据已有下游合同修订，不能取消审核！");
				}
				// 检验是否推了应收单
				String sqlrec = "select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in "
						+ "(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r "
						+ "where nvl(r.dr,0)=0 and r.vsrcid='"
						+ cvo.getPk_contract() + "')";
				Integer countrec = (Integer) dao.executeQuery(sqlrec,
						new ColumnProcessor());
				if (countrec > 0) {
					throw new BusinessException("单据生成应收单已被收款参照，不能取消审核！");
				}
				//
				String sqlcn = "select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in "
						+ "(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r "
						+ "where nvl(r.dr,0)=0 and r.vsrcid='"
						+ cvo.getPk_contract() + "')";
				Integer countcn = (Integer) dao.executeQuery(sqlcn,
						new ColumnProcessor());
				if (countcn > 0) {
					throw new BusinessException("单据生成待收入确认单已被确认收入参照，不能取消审核！");
				}
				// 检验是否被进场参照
				String sqljc = "select count(*) from zl_entryacceptance where nvl(dr,0)=0 and vsrcid='"
						+ cvo.getPk_contract() + "'";
				Integer countjc = (Integer) dao.executeQuery(sqljc,
						new ColumnProcessor());
				if (countjc > 0) {
					throw new BusinessException("单据已经被进场管理参照，不能取消审核！");
				}

				Object vdef1=cvo.getVdef1();
				if(vdef1!=null){
					String sql_u="update zl_contract set htstatus=2 where pk_contract='"+vdef1+"'";
					dao.executeUpdate(sql_u);
				}
				
				String pk = cvo.getPk_contract();
				dao.deleteByClause(ContractCustVO.class,
						" pk_contract in(select pk_contract from zl_contract where "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractHouseVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractMzqVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractZzqVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractBzjVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractYwcfVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractCwcfVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractZqfyVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractZqfycfVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractFkmxVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractZjmxVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractZqmxVO.class,
						" pk_contract in(select pk_contract from zl_contract where  "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(ContractVO.class,
						" pk_contract in(select pk_contract from zl_contract where "
								+ " vbilltypecode='H420' and vsrcid='" + pk
								+ "')");
				dao.deleteByClause(RecbillVO.class, " vsrcid='" + pk + "'");
				dao.deleteByClause(ConfirmationVO.class, " vsrcid='" + pk + "'");

				// 删除客户房产
				dao.deleteByClause(Customer_fcxxVO.class, " vdef1='" + pk + "'");

				// 更改房源
				ContractHouseVO[] bvos = bills[0].getChildHouseVO();
				if (bvos != null && bvos.length > 0) {
					String str = "";
					for (ContractHouseVO bvo : bvos) {
						str += "'" + bvo.getPk_house() + "',";
					}
					str = str.substring(0, str.lastIndexOf(","));

					String sqlf = "update zl_housesource set housestate='"
							+ AbsEnumType.HOUSE_DZ
							+ "' where pk_housesource in(" + str + ")";
					dao.executeUpdate(sqlf);
				}
			}
		}

		return bills;
	}

	@Override
	public AggContractVO[] queryHTforXD(IQueryScheme queryScheme)
			throws BusinessException {

		FromWhereSQLImpl obj2 = (FromWhereSQLImpl) queryScheme
				.getTableJoinFromWhereSQL();

		String where = obj2.getWhere();

		obj2.setWhere((where == null ? " 1=1" : where)
				+ " and vbillstatus=1 and htstatus in(1,2,3) and version='-1' and vbilltypecode='H420' "
				+ " and not exists(select 1 from zl_contract tt where zl_contract.pk_contract=tt.vsrcid and "
				+ " nvl(tt.dr,0)=0 and tt.vbillstatus<>1 and tt.vbilltypecode='H430') ");

		return super.pubquerybills(queryScheme);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggContractVO queryHTbyPK(String pk) throws BusinessException {

		BaseDAO dao = new BaseDAO();
		AggContractVO aggvo = new AggContractVO();
		String sql = "select * from zl_contract t where pk_contract='" + pk
				+ "'";
		ContractVO vo = (ContractVO) dao.executeQuery(sql, new BeanProcessor(
				ContractVO.class));
		aggvo.setParentVO(vo);
		// 查询客户页签
		String sql1 = "select * from zl_contract_cust where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractCustVO> list1 = (List<ContractCustVO>) dao.executeQuery(
				sql1, new BeanListProcessor(ContractCustVO.class));
		aggvo.setChildren(ContractCustVO.class,
				list1.toArray(new ContractCustVO[0]));
		// 查询房产页签
		String sql2 = "select * from zl_contract_house where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractHouseVO> list2 = (List<ContractHouseVO>) dao.executeQuery(
				sql2, new BeanListProcessor(ContractHouseVO.class));
		aggvo.setChildren(ContractHouseVO.class,
				list2.toArray(new ContractHouseVO[0]));
		// 查询免周期页签
		String sql3 = "select * from zl_contract_mzq where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractMzqVO> list3 = (List<ContractMzqVO>) dao.executeQuery(
				sql3, new BeanListProcessor(ContractMzqVO.class));
		aggvo.setChildren(ContractMzqVO.class,
				list3.toArray(new ContractMzqVO[0]));
		// 查询增长期页签
		String sql4 = "select * from zl_contract_zzq where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractZzqVO> list4 = (List<ContractZzqVO>) dao.executeQuery(
				sql4, new BeanListProcessor(ContractZzqVO.class));
		aggvo.setChildren(ContractZzqVO.class,
				list4.toArray(new ContractZzqVO[0]));
		// 查询保证金页签
		String sql5 = "select * from zl_contract_bzj where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractBzjVO> list5 = (List<ContractBzjVO>) dao.executeQuery(
				sql5, new BeanListProcessor(ContractBzjVO.class));
		aggvo.setChildren(ContractBzjVO.class,
				list5.toArray(new ContractBzjVO[0]));
		// 查询业务页签
		String sql6 = "select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractYwcfVO> list6 = (List<ContractYwcfVO>) dao.executeQuery(
				sql6, new BeanListProcessor(ContractYwcfVO.class));
		aggvo.setChildren(ContractYwcfVO.class,
				list6.toArray(new ContractYwcfVO[0]));
		// 查询财务页签
		String sql7 = "select * from zl_contract_cwcf where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractCwcfVO> list7 = (List<ContractCwcfVO>) dao.executeQuery(
				sql7, new BeanListProcessor(ContractCwcfVO.class));
		aggvo.setChildren(ContractCwcfVO.class,
				list7.toArray(new ContractCwcfVO[0]));
		// 查询周期页签
		String sql8 = "select * from zl_contract_zqfy where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractZqfyVO> list8 = (List<ContractZqfyVO>) dao.executeQuery(
				sql8, new BeanListProcessor(ContractZqfyVO.class));
		aggvo.setChildren(ContractZqfyVO.class,
				list8.toArray(new ContractZqfyVO[0]));
		// 查询周期拆分页签
		String sql9 = "select * from zl_contract_zqfycf where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractZqfycfVO> list9 = (List<ContractZqfycfVO>) dao
				.executeQuery(sql9, new BeanListProcessor(
						ContractZqfycfVO.class));
		aggvo.setChildren(ContractZqfycfVO.class,
				list9.toArray(new ContractZqfycfVO[0]));
		// 查询付款明细页签
		String sql10 = "select * from zl_contract_fkmx where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractFkmxVO> list10 = (List<ContractFkmxVO>) dao.executeQuery(
				sql10, new BeanListProcessor(ContractFkmxVO.class));
		aggvo.setChildren(ContractFkmxVO.class,
				list10.toArray(new ContractFkmxVO[0]));
		// 查询租金明细页签
		String sql11 = "select * from zl_contract_zjmx where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractZjmxVO> list11 = (List<ContractZjmxVO>) dao.executeQuery(
				sql11, new BeanListProcessor(ContractZjmxVO.class));
		aggvo.setChildren(ContractZjmxVO.class,
				list11.toArray(new ContractZjmxVO[0]));
		// 查询周期明细页签
		String sql12 = "select * from zl_contract_zqmx where nvl(dr,0)=0 and pk_contract='"
				+ pk + "'";
		List<ContractZqmxVO> list12 = (List<ContractZqmxVO>) dao.executeQuery(
				sql12, new BeanListProcessor(ContractZqmxVO.class));
		aggvo.setChildren(ContractZqmxVO.class,
				list12.toArray(new ContractZqmxVO[0]));

		return aggvo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AggContractVO> queryHTbyPK2(List<String> pks) throws BusinessException {
		//根据主键查合同
		BaseDAO dao = new BaseDAO();
		List<AggContractVO> aggvos=new ArrayList<AggContractVO>();
		for(int i=0;i<pks.size();i++){
			String pk=pks.get(i);
			AggContractVO aggvo = new AggContractVO();
			String sql = "select * from zl_contract t where pk_contract='"+pk+"'";
			ContractVO vo = (ContractVO) dao.executeQuery(sql, new BeanProcessor(
					ContractVO.class));
			aggvo.setParentVO(vo);
			// 查询客户页签
			String sql1 = "select * from zl_contract_cust where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractCustVO> list1 = (List<ContractCustVO>) dao.executeQuery(
					sql1, new BeanListProcessor(ContractCustVO.class));
			aggvo.setChildren(ContractCustVO.class,
					list1.toArray(new ContractCustVO[0]));
			// 查询房产页签
			String sql2 = "select * from zl_contract_house where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractHouseVO> list2 = (List<ContractHouseVO>) dao.executeQuery(
					sql2, new BeanListProcessor(ContractHouseVO.class));
			aggvo.setChildren(ContractHouseVO.class,
					list2.toArray(new ContractHouseVO[0]));
			// 查询免周期页签
			String sql3 = "select * from zl_contract_mzq where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractMzqVO> list3 = (List<ContractMzqVO>) dao.executeQuery(
					sql3, new BeanListProcessor(ContractMzqVO.class));
			aggvo.setChildren(ContractMzqVO.class,
					list3.toArray(new ContractMzqVO[0]));
			// 查询增长期页签
			String sql4 = "select * from zl_contract_zzq where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractZzqVO> list4 = (List<ContractZzqVO>) dao.executeQuery(
					sql4, new BeanListProcessor(ContractZzqVO.class));
			aggvo.setChildren(ContractZzqVO.class,
					list4.toArray(new ContractZzqVO[0]));
			// 查询保证金页签
			String sql5 = "select * from zl_contract_bzj where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractBzjVO> list5 = (List<ContractBzjVO>) dao.executeQuery(
					sql5, new BeanListProcessor(ContractBzjVO.class));
			aggvo.setChildren(ContractBzjVO.class,
					list5.toArray(new ContractBzjVO[0]));
			// 查询业务页签
			String sql6 = "select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractYwcfVO> list6 = (List<ContractYwcfVO>) dao.executeQuery(
					sql6, new BeanListProcessor(ContractYwcfVO.class));
			aggvo.setChildren(ContractYwcfVO.class,
					list6.toArray(new ContractYwcfVO[0]));
			// 查询财务页签
			String sql7 = "select * from zl_contract_cwcf where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractCwcfVO> list7 = (List<ContractCwcfVO>) dao.executeQuery(
					sql7, new BeanListProcessor(ContractCwcfVO.class));
			aggvo.setChildren(ContractCwcfVO.class,
					list7.toArray(new ContractCwcfVO[0]));
			// 查询周期页签
			String sql8 = "select * from zl_contract_zqfy where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractZqfyVO> list8 = (List<ContractZqfyVO>) dao.executeQuery(
					sql8, new BeanListProcessor(ContractZqfyVO.class));
			aggvo.setChildren(ContractZqfyVO.class,
					list8.toArray(new ContractZqfyVO[0]));
			// 查询周期拆分页签
			String sql9 = "select * from zl_contract_zqfycf where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractZqfycfVO> list9 = (List<ContractZqfycfVO>) dao
					.executeQuery(sql9, new BeanListProcessor(
							ContractZqfycfVO.class));
			aggvo.setChildren(ContractZqfycfVO.class,
					list9.toArray(new ContractZqfycfVO[0]));
			// 查询付款明细页签
			String sql10 = "select * from zl_contract_fkmx where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractFkmxVO> list10 = (List<ContractFkmxVO>) dao.executeQuery(
					sql10, new BeanListProcessor(ContractFkmxVO.class));
			aggvo.setChildren(ContractFkmxVO.class,
					list10.toArray(new ContractFkmxVO[0]));
			// 查询租金明细页签
			String sql11 = "select * from zl_contract_zjmx where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractZjmxVO> list11 = (List<ContractZjmxVO>) dao.executeQuery(
					sql11, new BeanListProcessor(ContractZjmxVO.class));
			aggvo.setChildren(ContractZjmxVO.class,
					list11.toArray(new ContractZjmxVO[0]));
			// 查询周期明细页签
			String sql12 = "select * from zl_contract_zqmx where nvl(dr,0)=0 and pk_contract='"+pk+"'";
			List<ContractZqmxVO> list12 = (List<ContractZqmxVO>) dao.executeQuery(
					sql12, new BeanListProcessor(ContractZqmxVO.class));
			aggvo.setChildren(ContractZqmxVO.class,
					list12.toArray(new ContractZqmxVO[0]));
			aggvos.add(aggvo);
		}
		return aggvos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggContractVO[] queryforJC(IQueryScheme queryScheme)
			throws BusinessException {
		String where = queryScheme.getTableListFromWhereSQL().getWhere();
		String from = queryScheme.getTableListFromWhereSQL().getFrom();
		String t1 = "1=1";
		if (where.contains("T1.")) {
			t1 = "nvl(T1.dr,0)=0";
		}

		String sql_head = "select * from "
				+ from
				+ " where "
				+ where
				+ " and nvl(zl_contract.dr,0)=0 and "
				+ t1
				+ " and zl_contract.vbillstatus = 1 and "
				+ "zl_contract.htstatus in (1,2) and zl_contract.htcode not in (select a.contractid from zl_entryacceptance a where"
				+ " nvl(a.dr,0)=0) and zl_contract.version = -1 order by zl_contract.pk_contract";
		String sql_body = "select * from zl_contract_house where nvl(dr,0)=0";
		BaseDAO dao = new BaseDAO();
		List<ContractVO> headList = (List<ContractVO>) dao.executeQuery(
				sql_head, new BeanListProcessor(ContractVO.class));
		List<ContractHouseVO> bodyList = (List<ContractHouseVO>) dao
				.executeQuery(sql_body, new BeanListProcessor(
						ContractHouseVO.class));
		AggContractVO[] aggvos = new AggContractVO[headList.size()];
		for (int i = 0; i < headList.size(); i++) {
			AggContractVO aggvo = new AggContractVO();

			aggvo.setParentVO(headList.get(i));
			List<ContractHouseVO> blist = new ArrayList<ContractHouseVO>();
			for (int j = 0; j < bodyList.size(); j++) {
				if (headList.get(i).getPk_contract()
						.equals(bodyList.get(j).getPk_contract())) {
					blist.add(bodyList.get(j));
				}
			}
			if (blist != null) {
				ContractHouseVO[] bvos = new ContractHouseVO[blist.size()];
				aggvo.setChildrenVO(blist.toArray(bvos));
			}
			aggvos[i] = aggvo;
		}
		return aggvos;

	}

	@SuppressWarnings("unchecked")
	@Override
	public AggContractVO[] queryforTC(IQueryScheme queryScheme)
			throws BusinessException {

		String where = queryScheme.getTableListFromWhereSQL().getWhere();
		String from = queryScheme.getTableListFromWhereSQL().getFrom();
		String t1 = "1=1";
		if (where.contains("T1.")) {
			t1 = "nvl(T1.dr,0)=0";
		}

		String sql_head = "select * from "
				+ from
				+ " where "
				+ where
				+ " and nvl(zl_contract.dr,0)=0 and "
				+ t1
				+ " and zl_contract.vbillstatus = 1 and "
				+ "zl_contract.htstatus = 3 and zl_contract.version = -1 and exists (select 1 from zl_contract_house h where nvl(h.dr,0)=0 and h.pk_contract_house not in " +
				"(select t.pk_fc from zl_throwalease_khfc t where nvl(t.dr,0)=0 and exists (select 1 from zl_throwalease tt where nvl(tt.dr,0)=0 " +
				"and tt.pk_throwalease=t.pk_throwalease)) and h.pk_contract=zl_contract.pk_contract) order by zl_contract.pk_contract";
		String sql_body = "select * from zl_contract_house h where nvl(h.dr,0)=0 and h.pk_contract_house not in " +
				"(select t.pk_fc from zl_throwalease_khfc t where nvl(t.dr,0)=0 and exists (select 1 from zl_throwalease tt where nvl(tt.dr,0)=0 " +
				"and tt.pk_throwalease=t.pk_throwalease))";
		BaseDAO dao = new BaseDAO();
		List<ContractVO> headList = (List<ContractVO>) dao.executeQuery(
				sql_head, new BeanListProcessor(ContractVO.class));
		List<ContractHouseVO> bodyList = (List<ContractHouseVO>) dao
				.executeQuery(sql_body, new BeanListProcessor(
						ContractHouseVO.class));
		AggContractVO[] aggvos = new AggContractVO[headList.size()];
		for (int i = 0; i < headList.size(); i++) {
			AggContractVO aggvo = new AggContractVO();

			aggvo.setParentVO(headList.get(i));
			List<ContractHouseVO> blist = new ArrayList<ContractHouseVO>();
			for (int j = 0; j < bodyList.size(); j++) {
				if (headList.get(i).getPk_contract()
						.equals(bodyList.get(j).getPk_contract())) {
					blist.add(bodyList.get(j));
				}
			}
			if (blist != null) {
				ContractHouseVO[] bvos = new ContractHouseVO[blist.size()];
				aggvo.setChildrenVO(blist.toArray(bvos));
			}
			aggvos[i] = aggvo;
		}
		return aggvos;
	}

	public void sendToRec(AggContractVO aggvo) throws Exception {
		BaseDAO dao = new BaseDAO();
		ArrayList<AggRecbillVO> aggrvos = new ArrayList<AggRecbillVO>();
		ContractVO vo = aggvo.getParentVO();
		ContractYwcfVO[] ywvos = aggvo.getChildYwcfVO();
		ContractZqfycfVO[] zqvos = aggvo.getChildZqfycfVO();
		// 查询该合同房产对应的楼栋
		Map<String, String> map = new HashMap<String, String>();
		ContractHouseVO[] hvos = aggvo.getChildHouseVO();
		for (ContractHouseVO hvo : hvos) {
			map.put(hvo.getPk_house(), hvo.getPk_building());
		}

		// 保证金推应收单
		ContractBzjVO[] bzvo = aggvo.getChildBzjVO();
		if (bzvo != null && bzvo.length > 0) {
			for (int i = 0; i < bzvo.length; i++) {
				//判断是否推了保证金
				String sql="select count(1) from zl_recbill where nvl(dr,0)=0 and vsrcid='"+vo.getPk_contract()+"' and " +
						" vdef2='"+AbsEnumType.HT_BZJ+"' ";
				Integer it=(Integer)dao.executeQuery(sql, new ColumnProcessor());
				if(it<=0){
				// 应收金额=0不推
				if (bzvo[i].getNysmny().compareTo(new UFDouble(0)) == 0) {
					continue;
				}
				// 构造应收单
				RecbillVO recvo = new RecbillVO();
				recvo.setPk_group(vo.getPk_group());
				recvo.setPk_org(vo.getPk_org());
				recvo.setPk_org_v(vo.getPk_org_v());
				recvo.setPk_project(vo.getPk_project());
				recvo.setPk_customer(vo.getPk_customer());
				recvo.setPk_billtype("0001ZZ1000000001RHPI");
				recvo.setBilltypecode("H620");
				recvo.setVbillstatus(1);
				recvo.setPk_costproject(bzvo[i].getPk_costproject());
				recvo.setGatherdate(bzvo[i].getDrecdate());
				// 获取会计月份
				String month = bzvo[i].getDrecdate().toString().substring(0, 7);
				String sqlmon = "select h.pk_accperiodmonth from bd_accperiodmonth h "
						+ "where h.yearmth='"
						+ month
						+ "' and nvl(dr,0)=0 and "
						+ "pk_accperiodscheme='"
						+ AbsEnumType.Period + "'";
				Object objmon = dao.executeQuery(sqlmon, new ColumnProcessor());
				recvo.setCaccountperiod(objmon == null ? "" : objmon.toString());
				recvo.setBegindate(bzvo[i].getDrecdate());
				recvo.setNrecmoney(bzvo[i].getNysmny());
				recvo.setNnotaxmoney(bzvo[i].getNysmny());
				recvo.setNtaxmoney(new UFDouble(0));
				recvo.setVsrcid(vo.getPk_contract());
				recvo.setVsrctype("0001ZZ1000000001SNDJ");
				recvo.setDbilldate(new UFDate());
				recvo.setCreator(AppContext.getInstance().getPkUser());
				recvo.setCreationtime(new UFDateTime());
				recvo.setApprover(AppContext.getInstance().getPkUser());
				recvo.setApprovetime(new UFDateTime());
				recvo.setDr(0);
				recvo.setVdef1(bzvo[i].getPk_contract_bzj());
				recvo.setVdef2(AbsEnumType.HT_BZJ);
				recvo.setNtaxrate(new UFDouble(0));
				AggRecbillVO rggvo = new AggRecbillVO();
				rggvo.setParentVO(recvo);
				aggrvos.add(rggvo);
			}
			}
		}

		// 业务拆分推应收
		if (ywvos != null && ywvos.length > 0) {
			for (int i = 0; i < ywvos.length; i++) {
				// 应收金额=0不推(期初不推)
				if (ywvos[i].getNysmny().compareTo(new UFDouble(0)) == 0
						|| ywvos[i].getIs_qc().booleanValue()) {
					continue;
				}
				ContractYwcfVO yvo = ywvos[i];
					// 构造应收单
					AggRecbillVO agg = new AggRecbillVO();
					RecbillVO recvo = new RecbillVO();

					recvo.setPk_group(vo.getPk_group());
					recvo.setPk_org(vo.getPk_org());
					recvo.setPk_org_v(vo.getPk_org_v());
					recvo.setPk_project(vo.getPk_project());
					recvo.setPk_customer(yvo.getPk_customer());
					recvo.setPk_billtype("0001ZZ1000000001RHPI");
					recvo.setBilltypecode("H620");
					recvo.setVbillstatus(1);
					recvo.setPk_costproject(yvo.getPk_costproject());
					recvo.setGatherdate(yvo.getDrecdate());

					recvo.setCaccountperiod(yvo.getPk_month());
					recvo.setNrecmoney(yvo.getNysmny());
					recvo.setVsrcid(vo.getPk_contract());
					recvo.setVsrctype("0001ZZ1000000001SNDJ");
					recvo.setDbilldate(new UFDate());
					recvo.setCreator(AppContext.getInstance().getPkUser());
					recvo.setCreationtime(new UFDateTime());
					recvo.setApprover(AppContext.getInstance().getPkUser());
					recvo.setApprovetime(new UFDateTime());
					recvo.setDr(0);
					recvo.setVdef1(yvo.getPk_contract_ywcf());
					recvo.setVdef2(AbsEnumType.HT_YWCF);
					recvo.setNtaxrate(new UFDouble(vo.getTaxstyle()));
					recvo.setNnotaxmoney(yvo.getNysmny()
							.div(100 + vo.getTaxstyle()).multiply(100));
					recvo.setNtaxmoney(recvo.getNrecmoney().sub(
							recvo.getNnotaxmoney()));

					recvo.setBegindate(yvo.getDstartdate());
					recvo.setEnddate(yvo.getDenddate());
					recvo.setPk_building(map.get(yvo.getPk_house()));
					recvo.setPk_house(yvo.getPk_house());

					agg.setParentVO(recvo);
					aggrvos.add(agg);
			}
		}

		// 周期费用推应收
		if (zqvos != null && zqvos.length > 0) {
			for (int i = 0; i < zqvos.length; i++) {
				// 应收金额=0不推
				if (zqvos[i].getNysmny().compareTo(new UFDouble(0)) == 0) {
					continue;
				}
				ContractZqfycfVO yvo = zqvos[i];
				// 构造应收单
				AggRecbillVO agg = new AggRecbillVO();
				RecbillVO recvo = new RecbillVO();

				recvo.setPk_group(vo.getPk_group());
				recvo.setPk_org(vo.getPk_org());
				recvo.setPk_org_v(vo.getPk_org_v());
				recvo.setPk_project(vo.getPk_project());
				recvo.setPk_customer(yvo.getPk_customer());
				recvo.setPk_billtype("0001ZZ1000000001RHPI");
				recvo.setBilltypecode("H620");
				recvo.setVbillstatus(1);
				recvo.setPk_costproject(yvo.getPk_costproject());
				recvo.setGatherdate(yvo.getDrecdate());

				recvo.setCaccountperiod(yvo.getPk_month());
				recvo.setNrecmoney(yvo.getNysmny());
				recvo.setNnotaxmoney(yvo.getNnotaxmny());
				recvo.setNtaxmoney(yvo.getNtaxmny());
				recvo.setVsrcid(vo.getPk_contract());
				recvo.setVsrctype("0001ZZ1000000001SNDJ");
				recvo.setDbilldate(new UFDate());
				recvo.setCreator(AppContext.getInstance().getPkUser());
				recvo.setCreationtime(new UFDateTime());
				recvo.setApprover(AppContext.getInstance().getPkUser());
				recvo.setApprovetime(new UFDateTime());
				recvo.setDr(0);
				recvo.setVdef1(yvo.getPk_contract_zqfycf());
				recvo.setVdef2(AbsEnumType.HT_ZQCF);
				String vdef1 = yvo.getVdef1();
				if (vdef1 == null || "~".equals(vdef1) || "".equals(vdef1)) {
					recvo.setNtaxrate(new UFDouble(0));
				} else {
					recvo.setNtaxrate(new UFDouble(vdef1));
				}

				recvo.setBegindate(yvo.getDstartdate());
				recvo.setEnddate(yvo.getDenddate());
				recvo.setPk_building(map.get(yvo.getPk_house()));
				recvo.setPk_house(yvo.getPk_house());

				agg.setParentVO(recvo);
				aggrvos.add(agg);
			}
		}

		if(aggrvos.size()>0){
			ICwf_recbillMaintain irm = NCLocator.getInstance().lookup(
					ICwf_recbillMaintain.class);
			irm.insert(aggrvos.toArray(new AggRecbillVO[aggrvos.size()]), null);
		}

	}

	public void sendToCn(AggContractVO aggctvo) throws Exception {
		BaseDAO dao = new BaseDAO();
		ArrayList<AggConfirmationVO> aggcnvos = new ArrayList<AggConfirmationVO>();
		ContractVO ctvo = aggctvo.getParentVO();
		ContractCwcfVO[] ctcwvos = aggctvo.getChildCwcfVO();
		String sql_1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
		String pk_billtype_1 = dao.executeQuery(sql_1, new ColumnProcessor())
				.toString();
		ContractHouseVO[] chvos = aggctvo.getChildHouseVO();
		ContractZqfyVO[] zqvos = aggctvo.getChildZqfyVO();
		Map<Object, UFDouble> zqmap = new HashMap<Object, UFDouble>();
		for (ContractZqfyVO zq : zqvos) {
			zqmap.put(zq.getPk_house(), zq.getNtaxrate());
			zqmap.put(zq.getPk_house()+zq.getPk_costproject(), zq.getNsfmny());
		}
		// 财务拆分部分推待收入确认
		if (ctcwvos != null && ctcwvos.length > 0) {
			for (int i = 0; i < ctcwvos.length; i++) {
				// 应收金额=0不推(期初不推)
				if (ctcwvos[i].getNysmny().compareTo(new UFDouble(0)) == 0
						|| ctcwvos[i].getIs_qc().booleanValue()) {
					continue;
				}
				ConfirmationVO cnvo = new ConfirmationVO();
				AggConfirmationVO aggcnvo = new AggConfirmationVO();
				cnvo.setPk_org(ctvo.getPk_org());
				cnvo.setPk_org_v(ctvo.getPk_org_v());
				cnvo.setPk_group(ctvo.getPk_group());
				cnvo.setPk_customer(ctcwvos[i].getPk_customer());
				cnvo.setHouseproperty(ctcwvos[i].getPk_house());
				cnvo.setCaccountperiod(ctcwvos[i].getPk_month());
				cnvo.setDcollectiondate(ctcwvos[i].getDrecdate());
				// 根据房产名称查出楼栋信息 租赁面积
				// String sql_building =
				// "select pk_building from zl_contract_house where pk_house='"+ctcwvos[i].getPk_house()+"' and nvl(dr,0)=0";
				// String pk_building=iQ.executeQuery(sql_building, new
				// ColumnProcessor()).toString();
				String pk_building = null;
				UFDouble nrentarea = null;
				for (int j = 0; j < chvos.length; j++) {
					if (chvos[j].getPk_house().equals(ctcwvos[i].getPk_house())) {
						pk_building = chvos[j].getPk_building();
						nrentarea = chvos[j].getNarea();
						break;
					}
				}
				cnvo.setBuildno(pk_building);
				cnvo.setDbilldate(new UFDate());
				cnvo.setCreator(AppContext.getInstance().getPkUser());
				cnvo.setCreationtime(AppContext.getInstance().getServerTime());
				cnvo.setApprover(AppContext.getInstance().getPkUser());
				cnvo.setApprovetime(AppContext.getInstance().getServerTime());
				cnvo.setVsrcid(ctvo.getPk_contract());
				cnvo.setVsrctype(ctvo.getPk_billtype());
				cnvo.setPk_billtype(pk_billtype_1);
				cnvo.setVbilltypecode("H640");
				cnvo.setVbillstatus(1);
				cnvo.setPk_project(ctvo.getPk_project());
				cnvo.setDr(0);
				cnvo.setChargingproject(ctcwvos[i].getPk_costproject());
				cnvo.setDfeestartdate(ctcwvos[i].getDstartdate());
				cnvo.setDfeeenddate(ctcwvos[i].getDenddate());
				cnvo.setAmountreceivable(ctcwvos[i].getNysmny());
				cnvo.setAmountconfirmed(new UFDouble(0));
				cnvo.setDreccollectdate(ctcwvos[i].getDrecdate());
				cnvo.setVdef1(ctcwvos[i].getPk_contract_cwcf());
				cnvo.setVdef2("zl_contract_cwcf");
				// 报表用
				cnvo.setNrentarea(nrentarea);
				cnvo.setNnotaxmny(ctcwvos[i].getNnotaxmny());
				cnvo.setNtaxmny(ctcwvos[i].getNtaxmny());
				cnvo.setNtaxrate(new UFDouble(ctvo.getTaxstyle()));
				aggcnvo.setParentVO(cnvo);
				aggcnvos.add(aggcnvo);
			}
		}
		// 周期费用拆分部分推待收入确认
		ContractZqfycfVO[] zqfycfs = aggctvo.getChildZqfycfVO();
		String sql_value="select value from pub_sysinit where dr=0 and initcode='ZL002' and pk_org='"+ctvo.getPk_org()+"'";
		Object value=dao.executeQuery(sql_value, new ColumnProcessor());
		if(value.toString().equals("N")){
			if (zqfycfs != null && zqfycfs.length > 0) {
				for (int i = 0; i < zqfycfs.length; i++) {
					// 应收金额=0不推
					if (zqfycfs[i].getNysmny().compareTo(new UFDouble(0)) == 0) {
						continue;
					}
					ConfirmationVO cnvo = new ConfirmationVO();
					AggConfirmationVO aggcnvo = new AggConfirmationVO();
					cnvo.setPk_org(ctvo.getPk_org());
					cnvo.setPk_org_v(ctvo.getPk_org_v());
					cnvo.setPk_group(ctvo.getPk_group());
					cnvo.setPk_customer(zqfycfs[i].getPk_customer());
					cnvo.setCaccountperiod(zqfycfs[i].getPk_month());
					cnvo.setDcollectiondate(zqfycfs[i].getDrecdate());
					// 获取楼栋信息
					String pk_building = null;
					UFDouble nrentarea = null;
					for (int j = 0; j < chvos.length; j++) {
						if (chvos[j].getPk_house().equals(zqfycfs[i].getPk_house())) {
							pk_building = chvos[j].getPk_building();
							nrentarea = chvos[j].getNarea();
							break;
						}
					}
					cnvo.setHouseproperty(zqfycfs[i].getPk_house());
					cnvo.setBuildno(pk_building);
					cnvo.setDbilldate(new UFDate());
					cnvo.setCreator(AppContext.getInstance().getPkUser());
					cnvo.setCreationtime(AppContext.getInstance().getServerTime());
					cnvo.setApprover(AppContext.getInstance().getPkUser());
					cnvo.setApprovetime(AppContext.getInstance().getServerTime());
					cnvo.setVsrcid(ctvo.getPk_contract());
					cnvo.setVsrctype(ctvo.getPk_billtype());
					cnvo.setPk_billtype(pk_billtype_1);
					cnvo.setVbilltypecode("H640");
					cnvo.setVbillstatus(1);
					cnvo.setPk_project(ctvo.getPk_project());
					cnvo.setDr(0);
					cnvo.setChargingproject(zqfycfs[i].getPk_costproject());
					cnvo.setDfeestartdate(zqfycfs[i].getDstartdate());
					cnvo.setDfeeenddate(zqfycfs[i].getDenddate());
					cnvo.setAmountreceivable(zqfycfs[i].getNysmny());
					cnvo.setAmountconfirmed(new UFDouble(0));
					cnvo.setDreccollectdate(zqfycfs[i].getDrecdate());
					cnvo.setVdef1(zqfycfs[i].getPk_contract_zqfycf());
					cnvo.setVdef2("zl_contract_zqfycf");
					// 报表用
					cnvo.setNrentarea(nrentarea);
					cnvo.setNnotaxmny(zqfycfs[i].getNnotaxmny());
					cnvo.setNtaxmny(zqfycfs[i].getNtaxmny());
					cnvo.setNtaxrate(zqmap.get(zqfycfs[i].getPk_house()));
					aggcnvo.setParentVO(cnvo);
					aggcnvos.add(aggcnvo);
				}
			}
		}else if(value.toString().equals("Y")){
			Integer ndignum=Integer.parseInt(ctvo.getNdegree().toString());
			if (zqfycfs != null && zqfycfs.length > 0) {
				for (int i = 0; i < zqfycfs.length; i++) {
					// 应收金额=0不推
					if (zqfycfs[i].getNysmny().compareTo(new UFDouble(0)) == 0) {
						continue;
					}
					UFDouble daymny=getUFdobj(zqmap.get(zqfycfs[i].getPk_house()+zqfycfs[i].getPk_costproject())).div(30);
					UFDate ud_fist=new UFDate(zqfycfs[i].getDstartdate().toString());
					UFDate ud_last=new UFDate(zqfycfs[i].getDenddate().toString());
					UFDate monthstart=ud_fist;
					UFDate monthend=CalendarUtls.getMaxMonthDay(ud_fist);
					UFDouble fymny=new UFDouble();
					UFDouble allmny=new UFDouble();
					while(monthend.beforeDate(ud_last)){
						int oneMT=CalendarUtls.getBetweenTwoDays(monthstart, monthend);
						if(ndignum==AbsEnumType.FeeScale2_JW){
							fymny=new UFDouble(Math.ceil(daymny.multiply(oneMT).doubleValue()));
						}else{
							fymny=daymny.multiply(oneMT).add(new UFDouble(0), ndignum);
						}
						ConfirmationVO cnvo = new ConfirmationVO();
						AggConfirmationVO aggcnvo = new AggConfirmationVO();
						cnvo.setPk_org(ctvo.getPk_org());
						cnvo.setPk_org_v(ctvo.getPk_org_v());
						cnvo.setPk_group(ctvo.getPk_group());
						cnvo.setPk_customer(zqfycfs[i].getPk_customer());
						String sql_pk = "select pk_accperiodmonth from bd_accperiodmonth where " +
								"nvl(dr,0)=0 and (begindate <= '"+monthstart+"' and enddate >= '"+monthstart+"')";
						Object pk=dao.executeQuery(sql_pk, new ColumnProcessor());
						cnvo.setCaccountperiod(pk.toString());
						cnvo.setDcollectiondate(monthstart);
						// 获取楼栋信息
						String pk_building = null;
						UFDouble nrentarea = null;
						for (int j = 0; j < chvos.length; j++) {
							if (chvos[j].getPk_house().equals(zqfycfs[i].getPk_house())) {
								pk_building = chvos[j].getPk_building();
								nrentarea = chvos[j].getNarea();
								break;
							}
						}
						cnvo.setHouseproperty(zqfycfs[i].getPk_house());
						cnvo.setBuildno(pk_building);
						cnvo.setDbilldate(new UFDate());
						cnvo.setCreator(AppContext.getInstance().getPkUser());
						cnvo.setCreationtime(AppContext.getInstance().getServerTime());
						cnvo.setApprover(AppContext.getInstance().getPkUser());
						cnvo.setApprovetime(AppContext.getInstance().getServerTime());
						cnvo.setVsrcid(ctvo.getPk_contract());
						cnvo.setVsrctype(ctvo.getPk_billtype());
						cnvo.setPk_billtype(pk_billtype_1);
						cnvo.setVbilltypecode("H640");
						cnvo.setVbillstatus(1);
						cnvo.setPk_project(ctvo.getPk_project());
						cnvo.setDr(0);
						cnvo.setChargingproject(zqfycfs[i].getPk_costproject());
						cnvo.setDfeestartdate(monthstart);
						cnvo.setDfeeenddate(monthend);
						cnvo.setAmountreceivable(fymny);
						cnvo.setAmountconfirmed(new UFDouble(0));
						cnvo.setDreccollectdate(monthstart);
						cnvo.setVdef1(zqfycfs[i].getPk_contract_zqfycf());
						cnvo.setVdef2("zl_contract_zqfycf");
						// 报表用
						cnvo.setNrentarea(nrentarea);
						cnvo.setNtaxrate(zqmap.get(zqfycfs[i].getPk_house()));
						cnvo.setNnotaxmny(fymny.div(cnvo.getNtaxrate().div(100).add(1)));
						cnvo.setNtaxmny(fymny.sub(cnvo.getNnotaxmny()));
						aggcnvo.setParentVO(cnvo);
						aggcnvos.add(aggcnvo);
						monthstart=CalendarUtls.getNextMonthFirstDay(monthstart);
						monthend=CalendarUtls.getMaxMonthDay(monthstart);
						allmny=fymny.add(allmny);
					}
					//最后一笔倒减
					ConfirmationVO cnvo = new ConfirmationVO();
					AggConfirmationVO aggcnvo = new AggConfirmationVO();
					cnvo.setPk_org(ctvo.getPk_org());
					cnvo.setPk_org_v(ctvo.getPk_org_v());
					cnvo.setPk_group(ctvo.getPk_group());
					cnvo.setPk_customer(zqfycfs[i].getPk_customer());
					String sql_pk1 = "select pk_accperiodmonth from bd_accperiodmonth where " +
							"nvl(dr,0)=0 and (begindate <= '"+monthstart+"' and enddate >= '"+monthstart+"')";
					Object pk1=dao.executeQuery(sql_pk1, new ColumnProcessor());
					cnvo.setCaccountperiod(pk1.toString());
					cnvo.setDcollectiondate(monthstart);
					// 获取楼栋信息
					String pk_building = null;
					UFDouble nrentarea = null;
					for (int j = 0; j < chvos.length; j++) {
						if (chvos[j].getPk_house().equals(zqfycfs[i].getPk_house())) {
							pk_building = chvos[j].getPk_building();
							nrentarea = chvos[j].getNarea();
							break;
						}
					}
					cnvo.setHouseproperty(zqfycfs[i].getPk_house());
					cnvo.setBuildno(pk_building);
					cnvo.setDbilldate(new UFDate());
					cnvo.setCreator(AppContext.getInstance().getPkUser());
					cnvo.setCreationtime(AppContext.getInstance().getServerTime());
					cnvo.setApprover(AppContext.getInstance().getPkUser());
					cnvo.setApprovetime(AppContext.getInstance().getServerTime());
					cnvo.setVsrcid(ctvo.getPk_contract());
					cnvo.setVsrctype(ctvo.getPk_billtype());
					cnvo.setPk_billtype(pk_billtype_1);
					cnvo.setVbilltypecode("H640");
					cnvo.setVbillstatus(1);
					cnvo.setPk_project(ctvo.getPk_project());
					cnvo.setDr(0);
					cnvo.setChargingproject(zqfycfs[i].getPk_costproject());
					cnvo.setDfeestartdate(monthstart);
					cnvo.setDfeeenddate(ud_last);
					cnvo.setAmountreceivable(zqfycfs[i].getNysmny().sub(allmny));
					cnvo.setAmountconfirmed(new UFDouble(0));
					cnvo.setDreccollectdate(monthstart);
					cnvo.setVdef1(zqfycfs[i].getPk_contract_zqfycf());
					cnvo.setVdef2("zl_contract_zqfycf");
					// 报表用
					cnvo.setNrentarea(nrentarea);
					cnvo.setNtaxrate(zqmap.get(zqfycfs[i].getPk_house()));
					cnvo.setNnotaxmny(cnvo.getAmountreceivable().div(cnvo.getNtaxrate().div(100).add(1)));
					cnvo.setNtaxmny(cnvo.getAmountreceivable().sub(cnvo.getNnotaxmny()));
					aggcnvo.setParentVO(cnvo);
					aggcnvos.add(aggcnvo);
				}
			}
		}

		// 保证金部分推待收入确认
		ContractBzjVO[] bzjvos = aggctvo.getChildBzjVO();
		if (bzjvos != null && bzjvos.length > 0) {
			for (int i = 0; i < bzjvos.length; i++) {
				// 应收金额=0不推
				if (bzjvos[i].getNysmny().compareTo(new UFDouble(0)) == 0) {
					continue;
				}
				ConfirmationVO cnvo = new ConfirmationVO();
				AggConfirmationVO aggcnvo = new AggConfirmationVO();
				cnvo.setPk_org(ctvo.getPk_org());
				cnvo.setPk_org_v(ctvo.getPk_org_v());
				cnvo.setPk_group(ctvo.getPk_group());
				cnvo.setPk_customer(bzjvos[i].getPk_customer());
				// cnvo.setCaccountperiod(bzjvos[i].getPk_month());
				cnvo.setDcollectiondate(bzjvos[i].getDrecdate());
				String sql = "select h.pk_accperiodmonth from bd_accperiodmonth h "
						+ "where h.enddate>='"
						+ bzjvos[i].getDrecdate()
						+ "' and h.begindate<='"
						+ bzjvos[i].getDrecdate()
						+ "' and nvl(dr,0)=0 and "
						+ "pk_accperiodscheme='"
						+ AbsEnumType.Period + "'";
				Object obj = dao.executeQuery(sql, new ColumnProcessor());
				cnvo.setCaccountperiod(obj == null ? null : obj.toString());

				// cnvo.setHouseproperty(bzjvos[i].getPk_house());
				// cnvo.setBuildno(pk_building);
				cnvo.setDbilldate(new UFDate());
				cnvo.setCreator(AppContext.getInstance().getPkUser());
				cnvo.setCreationtime(AppContext.getInstance().getServerTime());
				cnvo.setApprover(AppContext.getInstance().getPkUser());
				cnvo.setApprovetime(AppContext.getInstance().getServerTime());
				cnvo.setVsrcid(ctvo.getPk_contract());
				cnvo.setVsrctype(ctvo.getPk_billtype());
				cnvo.setPk_billtype(pk_billtype_1);
				cnvo.setVbilltypecode("H640");
				cnvo.setVbillstatus(1);
				cnvo.setPk_project(ctvo.getPk_project());
				cnvo.setDr(0);
				cnvo.setChargingproject(bzjvos[i].getPk_costproject());
				cnvo.setDfeestartdate(bzjvos[i].getDrecdate());
				cnvo.setAmountreceivable(bzjvos[i].getNysmny());
				cnvo.setAmountconfirmed(new UFDouble(0));
				cnvo.setDreccollectdate(bzjvos[i].getDrecdate());
				cnvo.setVdef1(bzjvos[i].getPk_contract_bzj());
				cnvo.setVdef2("zl_contract_bzj");
				// 报表用
				// cnvo.setNrentarea(new UFDouble(0));
				cnvo.setNnotaxmny(bzjvos[i].getNysmny());
				cnvo.setNtaxmny(new UFDouble(0));
				cnvo.setNtaxrate(new UFDouble(0));
				aggcnvo.setParentVO(cnvo);
				aggcnvos.add(aggcnvo);
			}
		}
		if(aggcnvos.size()>0){
			// 调待确认收入单的实现类的insert方法写入数据库
			ILyw_confirmationMaintain icf = NCLocator.getInstance().lookup(
					ILyw_confirmationMaintain.class);
			icf.insert(aggcnvos.toArray(new AggConfirmationVO[aggcnvos.size()]),
					null);
		}
	}
	
	private static UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	// public boolean findCnByVsrcid(String vsrcid) throws DAOException{
	// boolean flag=false;
	// String sql =
	// "select * from zl_confirmation where vsrcid='"+vsrcid+"'and nvl(dr,0)=0";
	// BaseDAO dao=new BaseDAO();
	// List<ConfirmationVO> cnvos=(List<ConfirmationVO>)dao.executeQuery(sql,
	// new BeanListProcessor(ConfirmationVO.class));
	// if(cnvos.size()>0){
	// flag=true;
	// }
	// return flag;
	// }
}
