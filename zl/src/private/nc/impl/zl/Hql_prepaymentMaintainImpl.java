package nc.impl.zl;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceHql_prepaymentPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.zl.hql_prepayment.Prepayment_bVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.itf.zl.IHql_prepaymentMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;

public class Hql_prepaymentMaintainImpl extends AceHql_prepaymentPubServiceImpl
		implements IHql_prepaymentMaintain {

	@Override
	public void delete(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggPrepaymentVO[] insert(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggPrepaymentVO[] update(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggPrepaymentVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggPrepaymentVO[] save(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggPrepaymentVO[] unsave(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggPrepaymentVO[] approve(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggPrepaymentVO[] unapprove(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		//return super.pubunapprovebills(clientFullVOs, originBills);
		AggPrepaymentVO[] bills = super.pubunapprovebills(clientFullVOs, originBills);
		if(bills.length < 1){
			throw new BusinessException("请选择一条单据！");
		}
		
		Object obj = bills[0].getParentVO().getPk_prepayment();
		BaseDAO dao=new BaseDAO();
		String sql="select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in " +
				"(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+getStgObj(obj)+"')";
		Integer gather_c=(Integer) dao.executeQuery(sql, new ColumnProcessor());
		
		String sql1="select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in " +
				"(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+getStgObj(obj)+"')";
		Integer bill_c=(Integer)dao.executeQuery(sql1, new ColumnProcessor());
		
		if(gather_c>0){
			throw new BusinessException("生成应收单已被收款单参照无法取消审批！");
		}
		if(bill_c>0){
			throw new BusinessException("生成待收入确认已被收入确认参照无法取消审批");
		}
		Prepayment_bVO[] pbvo = (Prepayment_bVO[]) bills[0].getChildren(Prepayment_bVO.class);
		for(int i = 0;i < pbvo.length;i++){
			if(pbvo[i].getNskmny().toDouble() > 0){
				throw new BusinessException("此单据已产生抵消，不能取消审核！");
			}
		}
		
		dao.deleteByClause(RecbillVO.class, " vsrcid='"+getStgObj(obj)+"'");
		dao.deleteByClause(ConfirmationVO.class, " vsrcid='"+getStgObj(obj)+"'");
		return bills;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
