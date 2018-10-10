package nc.impl.zl;

import org.apache.camel.component.bean.BeanProcessor;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceHql_entryacceptancePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.zl.hql_entryacceptance.EntryacceptanceVO;
import nc.vo.zl.hql_entryacceptance.Entryacceptance_khfcVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractBzjVO;
import nc.vo.zl.tcl_contract.ContractCustVO;
import nc.vo.zl.tcl_contract.ContractCwcfVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractMzqVO;
import nc.vo.zl.tcl_contract.ContractVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;
import nc.vo.zl.tcl_contract.ContractZqfyVO;
import nc.vo.zl.tcl_contract.ContractZqfycfVO;
import nc.vo.zl.tcl_contract.ContractZzqVO;
import nc.itf.zl.IHql_entryacceptanceMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class Hql_entryacceptanceMaintainImpl extends AceHql_entryacceptancePubServiceImpl
		implements IHql_entryacceptanceMaintain {

	@Override
	public void delete(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggEntryacceptanceVO[] insert(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggEntryacceptanceVO[] update(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggEntryacceptanceVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggEntryacceptanceVO[] save(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggEntryacceptanceVO[] unsave(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	/*public AggEntryacceptanceVO[] approve(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}*/
	//重写审批方法
	public AggEntryacceptanceVO[] approve(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		//return super.pubapprovebills(clientFullVOs, originBills);
		
		AggEntryacceptanceVO[] bills = super.pubapprovebills(clientFullVOs, originBills);
		if(bills.length>1){
			throw new BusinessException("请选择一条单据！");
		}
		
		EntryacceptanceVO evo=bills[0].getParentVO();
	    Entryacceptance_khfcVO[] bvo = (Entryacceptance_khfcVO[]) bills[0].getChildren(Entryacceptance_khfcVO.class);
		if(evo.getVbillstatus()==1){
			String contractid = evo.getContractid();
			BaseDAO dao = new BaseDAO();
			String pk = evo.getPk_entryacceptance();
			String sql_contract = "update zl_contract set htstatus = 3,dindate = '"+bvo[0].getEntrydate()+"' where nvl(dr,0)=0 and version = -1 and htcode = '"+contractid+"'";
			dao.executeUpdate(sql_contract);
		}
		return bills;
	}

	@Override
	public AggEntryacceptanceVO[] unapprove(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		//return super.pubunapprovebills(clientFullVOs, originBills);
		AggEntryacceptanceVO[] bills = super.pubunapprovebills(clientFullVOs, originBills);
		if(bills.length>1){
			throw new BusinessException("请选择一条单据！");
		}
		
		EntryacceptanceVO evo=bills[0].getParentVO();
		String contractid = evo.getContractid();
		BaseDAO dao=new BaseDAO();
		String sql_throw = "select count(*) from zl_throwalease where nvl(dr,0)=0 and vsrcid ='"+evo.getVsrcid()+"'";
		int count = (Integer) dao.executeQuery(sql_throw, new ColumnProcessor());
		if(count > 0){
			throw new BusinessException("该进场已做退场管理，不能取消审核！");
		}
		if(evo.getVbillstatus()==-1){
			
			//String htstatus = evo.getVdef1();
			String sql_contract = "update zl_contract set htstatus = 2,dindate = null where nvl(dr,0)=0 and version = -1 and pk_contract = '"+evo.getVsrcid()+"'";
			dao.executeUpdate(sql_contract);
		}
		return bills;
	}

}
