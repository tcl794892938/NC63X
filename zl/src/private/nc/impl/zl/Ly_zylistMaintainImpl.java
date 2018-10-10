package nc.impl.zl;

import java.util.List;
import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceLy_zylistPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.zl.ly_zylist.ZylistSrVO;
import nc.vo.zl.ly_zylist.ZylistVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.itf.zl.ILy_zylistMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;

public class Ly_zylistMaintainImpl extends AceLy_zylistPubServiceImpl
		implements ILy_zylistMaintain {

	@Override
	public void delete(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggZylistVO[] insert(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggZylistVO[] update(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggZylistVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggZylistVO[] save(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggZylistVO[] unsave(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggZylistVO[] approve(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggZylistVO[] unapprove(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		AggZylistVO[] aggvos=super.pubunapprovebills(clientFullVOs, originBills);
		if(aggvos.length<1){
			throw new BusinessException("请选择一条单据！");
		}
		ZylistVO zvo=aggvos[0].getParentVO();
		Object pk_zylist=zvo.getPk_zylist();
		BaseDAO dao=new BaseDAO();
		
		String sql="select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in " +
				"(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+getStgObj(pk_zylist)+"')";
		Integer gather_c=(Integer) dao.executeQuery(sql, new ColumnProcessor());
		
		String sql1="select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in " +
				"(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+getStgObj(pk_zylist)+"')";
		Integer bill_c=(Integer)dao.executeQuery(sql1, new ColumnProcessor());
		
		if(gather_c>0){
			throw new BusinessException("生成应收单已被收款单参照无法取消审批！");
		}
		if(bill_c>0){
			throw new BusinessException("生成待收入确认已被收入确认参照无法取消审批");
		}
		
		dao.deleteByClause(RecbillVO.class, " vsrcid='"+getStgObj(pk_zylist)+"'");
		dao.deleteByClause(ConfirmationVO.class, " vsrcid='"+getStgObj(pk_zylist)+"'");
		return aggvos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggZylistVO[] query2(IQueryScheme queryScheme)
			throws BusinessException {
		BaseDAO dao=new BaseDAO();
		String sqls="select vsrcid from zl_hflist where nvl(dr,0)=0";
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
		String sql="select * from zl_zylist where "+conf+ "  and nvl(dr,0)=0 and liststate='1'" +
				" and pk_zylist not in ("+pks+") order by listid ";
		
		List<ZylistVO> list=(List<ZylistVO>)dao.executeQuery(sql, new BeanListProcessor(ZylistVO.class));
		
		AggZylistVO[] aggvos = new AggZylistVO[list.size()];
		for(int i=0;i<list.size();i++){
			AggZylistVO aggvo=new AggZylistVO();
			aggvo.setParentVO(list.get(i));
			Object pk_zyist=list.get(i).getPk_zylist();
			String sql2="select * from zl_zylist_sr where nvl(dr,0)=0 and pk_zylist='"+getStgObj(pk_zyist)+"'";
			List<ZylistSrVO> srlist=(List<ZylistSrVO>)dao.executeQuery(sql2, new BeanListProcessor(ZylistSrVO.class));
			aggvo.setChildrenVO(srlist.toArray(new ZylistSrVO[0]));
			aggvos[i]=aggvo;
		}
		return aggvos;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
