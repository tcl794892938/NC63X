package nc.impl.zl;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceLd_mdcontractPubServiceImpl;
import nc.itf.zl.ILd_mdcontractMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class Ld_mdcontractMaintainImpl extends AceLd_mdcontractPubServiceImpl
		implements ILd_mdcontractMaintain {

	@Override
	public void delete(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggMdcontractVO[] insert(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		AggMdcontractVO[] bills = super.pubinsertBills(clientFullVOs, originBills);
		if(clientFullVOs.length>0){
			for(int i=0;i<clientFullVOs.length;i++){
				bills[i].getParentVO().setState(-1);
			}
		}
		return bills;
	}

	@Override
	public AggMdcontractVO[] update(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		AggMdcontractVO[] bills = super.pubupdateBills(clientFullVOs, originBills);
		if(clientFullVOs.length>0){
			for(int i=0;i<clientFullVOs.length;i++){
				bills[i].getParentVO().setState(-1);
			}
		}
		
		
		return bills;
	}

	@Override
	public AggMdcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggMdcontractVO[] save(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggMdcontractVO[] unsave(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggMdcontractVO[] approve(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggMdcontractVO[] unapprove(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		AggMdcontractVO[] bills = super.pubunapprovebills(clientFullVOs, originBills);
		if(bills.length<1){
			throw new BusinessException("请选择一条单据！");
		}
		BaseDAO dao=new BaseDAO();
		//取消审核时去除0号版本
		for(AggMdcontractVO aggvo:originBills){
			MdcontractVO pvo=aggvo.getParentVO();
			if(pvo.getState()==1&&pvo.getVbilltypecode().equals("H440")){
				
				String pk=pvo.getPk_mdcontract();
				dao.deleteByClause(MdcontractVO.class, " pk_mdcontract in(select pk_mdcontract from zl_mdcontract where " +
						"  vsrcid='"+pk+"' and nvl(dr,0)=0)");
				dao.deleteByClause(MdcontractCVO.class, " pk_mdcontract in(select pk_mdcontract from zl_mdcontract where  " +
						" vsrcid='"+pk+"')");
				
			}
			
		}
		
		//取消审批时删除已推、且未收的数据
		MdcontractVO vo = bills[0].getParentVO();
		
		//应收单
		String sql1 = "select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in " +
				"(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+vo.getPk_mdcontract()+"')";
		Integer gather_c=(Integer) dao.executeQuery(sql1, new ColumnProcessor());
		//带收入确认单
		String sql2="select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in " +
				"(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+vo.getPk_mdcontract()+"')";
		Integer bill_c=(Integer)dao.executeQuery(sql2, new ColumnProcessor());
		//过滤条件
		if(gather_c>0){
			throw new BusinessException("生成应收单已被收款单参照，无法取消审批！");
		}
		if(bill_c>0){
			throw new BusinessException("生成待收入确认已被收入确认参照，无法取消审批");
		}
		
		//删除退租推应收、待收入确认
		dao.deleteByClause(RecbillVO.class, " vsrcid='"+vo.getPk_mdcontract()+"'");
		dao.deleteByClause(ConfirmationVO.class, " vsrcid='"+vo.getPk_mdcontract()+"'");
		return bills;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggMdcontractVO[] query2(IQueryScheme queryScheme)
			throws BusinessException {
		String where=queryScheme.getTableListFromWhereSQL().getWhere();
		String from = queryScheme.getTableListFromWhereSQL().getFrom();
		String sql_head = "select * from "+from+" where "+where+" and state=1 and version=-1 and nvl(dr,0)=0 order by code ";
		String sql_body = "select * from zl_mdcontract_c where nvl(dr,0)=0";
		BaseDAO dao=new BaseDAO();
		List<MdcontractVO> headList=(List<MdcontractVO>)dao.executeQuery(sql_head, new BeanListProcessor(MdcontractVO.class));
		List<MdcontractCVO> bodyList=(List<MdcontractCVO>)dao.executeQuery(sql_body, new BeanListProcessor(MdcontractCVO.class));
		AggMdcontractVO[] aggvos = new AggMdcontractVO[headList.size()];
		AggMdcontractVO aggvo= null;
		for(int i=0;i<headList.size();i++){
			aggvo=new AggMdcontractVO();
			
			aggvo.setParentVO(headList.get(i));
			
			int count1 = 0;//获取对应表体长度
			
			for(int j=0;j<bodyList.size();j++){
				
				if(headList.get(i).getPk_mdcontract().equals(bodyList.get(j).getPk_mdcontract())){
					
					count1++;
				}
			}
			if(count1>0){
				MdcontractCVO[] cvo = new MdcontractCVO[count1];
				//赋值
				int count2 = 0;
				for(int m=0;m<bodyList.size();m++){
					
					if(headList.get(i).getPk_mdcontract().equals(bodyList.get(m).getPk_mdcontract())){
						cvo[count2] = new MdcontractCVO();
						cvo[count2] = bodyList.get(m);	
						count2++;
					}
				}
				if(count2>0){
					for(int k=0;k<count2;k++){
						if(cvo[k]!=null){
							aggvo.setChildrenVO(cvo);					
						}
					}
				}
			}else{
				MdcontractCVO[] cvo1 = new MdcontractCVO[1];
				cvo1[0] = new MdcontractCVO();
				/*MdcontractCVO mcvo = new MdcontractCVO();
				mcvo = null;*/
				//cvo1[0]=null;
				
				aggvo.setChildrenVO(cvo1);
			}
			aggvos[i]=aggvo;
		}
		System.out.println(aggvos);
		return aggvos;
			
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggMdcontractVO queryHTbyPK(String pk) throws BusinessException {
		BaseDAO dao=new BaseDAO();
		AggMdcontractVO aggvo=new AggMdcontractVO();
		String sql="select * from zl_mdcontract where pk_mdcontract='"+pk+"'";
		MdcontractVO mvo=(MdcontractVO) dao.executeQuery(sql, new BeanProcessor(MdcontractVO.class));
		aggvo.setParentVO(mvo);
		
		//查询表体
		String sql1="select * from zl_mdcontract_c where nvl(dr,0)=0 and pk_mdcontract='"+pk+"'";
		List<MdcontractCVO> mcvo=(List<MdcontractCVO>) dao.executeQuery(sql1, new BeanListProcessor(MdcontractCVO.class));
		aggvo.setChildren(MdcontractCVO.class, mcvo.toArray(new MdcontractCVO[0]));
		
		return aggvo;
	}
	
	public Double getDouble(Object obj){
		return obj==null?0:Double.parseDouble(obj.toString());
	}

}
