package nc.impl.zl;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceLy_bslistPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.zl.ly_bslist.BslistVO;
import nc.itf.zl.ILy_bslistMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class Ly_bslistMaintainImpl extends AceLy_bslistPubServiceImpl
		implements ILy_bslistMaintain {

	@Override
	public void delete(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] insert(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] update(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBslistVO[] save(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] unsave(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] approve(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] unapprove(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBslistVO[] query2(IQueryScheme queryScheme)
			throws BusinessException {
		BaseDAO dao=new BaseDAO();
		String sqls="select vsrcid from zl_zylist where nvl(dr,0)=0";
		List<Object> pk=(List<Object>)dao.executeQuery(sqls, new ArrayListProcessor());
		String pks="";
		if(pk.size()==0||pk==null){
			pks="' '";
		}else{
			for (int i=0;i<pk.size();i++) {
				Object[] pk1=(Object[]) pk.get(i);
				pks+="'"+pk1[0].toString()+"'";
				if(i<pk.size()-1){
					pks+=",";
				}
			}
		}
		
		/*String sql_psndoc="select pk_base_doc from sm_user where nvl(dr,0)=0 and cuserid='"+AppContext.getInstance().getPkUser()+"'";
		Object pk_psndoc=dao.executeQuery(sql_psndoc, new ColumnProcessor());
		String config="";
		if(pk_psndoc!=null){
			config=" and acceptman='"+pk_psndoc+"'";
		}*/
		
		String conf=queryScheme.getTableListFromWhereSQL().getWhere();
		String sql="select * from zl_bslist where "+conf+ " and nvl(dr,0)=0 and liststate='1'" +
				" and pk_bslist not in ("+pks+") order by listid ";
		List<BslistVO> list=(List<BslistVO>)dao.executeQuery(sql, new BeanListProcessor(BslistVO.class));
		AggBslistVO[] aggvos = new AggBslistVO[list.size()];
		for(int i=0;i<list.size();i++){
			AggBslistVO aggvo=new AggBslistVO();
			aggvo.setParentVO(list.get(i));
			aggvos[i]=aggvo;
		}

		return aggvos;
	}

}
