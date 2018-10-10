package nc.impl.zl;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceCwf_recbillPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.pub.BusinessException;

public class Cwf_recbillMaintainImpl extends AceCwf_recbillPubServiceImpl
		implements ICwf_recbillMaintain {

	@Override
	public void delete(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] insert(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] update(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggRecbillVO[] save(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] unsave(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] approve(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] unapprove(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggRecbillVO[] query2(IQueryScheme queryScheme)
			throws BusinessException {
		String conf=queryScheme.getTableListFromWhereSQL().getWhere();
		String sql="select * from zl_recbill where "+conf+ "  and nvl(dr,0)=0 and vbillstatus=1 and abs(nvl(nrecmoney,0))>abs(nvl(nrealmoney,0))" +
				" order by vbillcode ";
		BaseDAO dao=new BaseDAO();
		List<RecbillVO> list=(List<RecbillVO>)dao.executeQuery(sql, new BeanListProcessor(RecbillVO.class));
		AggRecbillVO[] aggvos = new AggRecbillVO[list.size()];
		for(int i=0;i<list.size();i++){
			AggRecbillVO aggvo=new AggRecbillVO();
			aggvo.setParentVO(list.get(i));
			aggvos[i]=aggvo;
		}

		return aggvos;
	}
	@Override
	public AggRecbillVO[] query3(IQueryScheme queryScheme)
			throws BusinessException {
		String conf=queryScheme.getTableListFromWhereSQL().getWhere();
		String sql="select * from zl_recbill where "+conf+ "  and nvl(dr,0)=0 and vbillstatus=1 and abs(nvl(nrecmoney,0))<>abs(nvl(invoicemoney,0))" +
				" order by vbillcode ";
		BaseDAO dao=new BaseDAO();
		List<RecbillVO> list=(List<RecbillVO>)dao.executeQuery(sql, new BeanListProcessor(RecbillVO.class));
		AggRecbillVO[] aggvos = new AggRecbillVO[list.size()];
		for(int i=0;i<list.size();i++){
			AggRecbillVO aggvo=new AggRecbillVO();
			aggvo.setParentVO(list.get(i));
			aggvos[i]=aggvo;
		}

		return aggvos;
	}
}
