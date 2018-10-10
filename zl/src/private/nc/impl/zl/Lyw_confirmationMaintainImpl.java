package nc.impl.zl;

import java.util.List;
import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceLyw_confirmationPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.pub.BusinessException;

public class Lyw_confirmationMaintainImpl extends AceLyw_confirmationPubServiceImpl
		implements ILyw_confirmationMaintain {

	@Override
	public void delete(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] insert(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] update(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggConfirmationVO[] save(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] unsave(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		
	
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] approve(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] unapprove(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggConfirmationVO[] querytoconfirm(IQueryScheme queryScheme)
			throws BusinessException {
		String where=queryScheme.getTableListFromWhereSQL().getWhere();
		String from = queryScheme.getTableListFromWhereSQL().getFrom();
		//String sql_head = "select * from "+from+" where "+where+" and nvl(dr,0)=0 and abs(amountreceivable)>abs(amountconfirmed) and vbillstatus='1' and vdef2='N'";
		String sql_head = "select * from "+from+" where "+where+"and nvl(dr,0)=0 and abs(nnotaxmny)>abs(amountconfirmed) and vbillstatus='1' and not exists(select 1 from zl_billconfirmed y where y.vsrcid=zl_confirmation.pk_confirmation and zl_confirmation.vbillstatus<>1)";
		BaseDAO dao=new BaseDAO();
		List<ConfirmationVO> headList=(List<ConfirmationVO>)dao.executeQuery(sql_head, new BeanListProcessor(ConfirmationVO.class));
		AggConfirmationVO[] aggvos = new AggConfirmationVO[headList.size()];
		for(int i = 0;i < headList.size();i++){
			AggConfirmationVO aggvo=new AggConfirmationVO();
			aggvo.setParentVO(headList.get(i));
			aggvos[i]=aggvo;
		}
		return aggvos;
	}
}
