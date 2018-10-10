package nc.impl.zl;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.ace.AceLd_kpregisterPubServiceImpl;
import nc.itf.zl.ILd_kpregisterMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pubitf.fip.service.IFipMessageService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.zl.cwf_gather.GatherBVO;
import nc.vo.zl.cwf_gather.GatherVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.zl.ld_kpregister.KpregisterDVO;
import nc.vo.zl.ld_kpregister.KpregisterVO;

public class Ld_kpregisterMaintainImpl extends AceLd_kpregisterPubServiceImpl
		implements ILd_kpregisterMaintain {

	@Override
	public void delete(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggKpregisterVO[] insert(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggKpregisterVO[] update(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggKpregisterVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggKpregisterVO[] save(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggKpregisterVO[] unsave(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggKpregisterVO[] approve(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		AggKpregisterVO[] aggvo = super.pubapprovebills(clientFullVOs, originBills);
		if(aggvo.length<1){
			throw new BusinessException("请选择一条单据！");
		}
		KpregisterDVO[] dvos = (KpregisterDVO[]) aggvo[0].getChildrenVO();
		KpregisterVO vo = aggvo[0].getParentVO();
		if(vo.getVbillstatus()==1 && vo.getIs_needkjpt().booleanValue()){
			sendXX(aggvo[0]);
		}
		
		BaseDAO dao=new BaseDAO();
		String sql1 = "select * from zl_recbill where nvl(dr,0)=0 and pk_project='"+vo.getPk_project()+"'";
		List<RecbillVO> rvoList = (List<RecbillVO>) dao.executeQuery(sql1, new BeanListProcessor(RecbillVO.class));
		String con = "";
		for(KpregisterDVO dvo:dvos){
			for(RecbillVO rvo:rvoList){
				if(rvo.getPk_recbill().equals(dvo.getVsrcid())){
					if(rvo.getInvoiceno()==null){
						con = "CONCAT(invoiceno,'"+aggvo[0].getParentVO().getKpcode()+"')";
					}else{
						con = "CONCAT(invoiceno,'、"+aggvo[0].getParentVO().getKpcode()+"')";
					}
					
				}
			}
			
			String sql = "update zl_recbill set invoicemoney=nvl(invoicemoney,0)+nvl("+dvo.getKpmoney()+",0),invoiceno="+con+" where nvl(dr,0)=0 and pk_recbill='"+dvo.getVsrcid()+"'";
			dao.executeUpdate(sql);
		}
		return super.pubapprovebills(clientFullVOs, originBills);
	}
	
	@Override
	public AggKpregisterVO[] unapprove(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		AggKpregisterVO[] aggvo = super.pubunapprovebills(clientFullVOs, originBills);
		if(aggvo.length<1){
			throw new BusinessException("请选择一条单据！");
		}
		KpregisterDVO[] dvos = (KpregisterDVO[]) aggvo[0].getChildrenVO();
		KpregisterVO vo = aggvo[0].getParentVO();
		if(originBills[0].getParentVO().getVbillstatus()==1 && vo.getIs_needkjpt().booleanValue()){
			unsendXX(originBills[0]);
		}
		
		BaseDAO dao=new BaseDAO();
		String sql1 = "select * from zl_recbill where nvl(dr,0)=0 and pk_project='"+vo.getPk_project()+"'";
		List<RecbillVO> rvoList = (List<RecbillVO>) dao.executeQuery(sql1, new BeanListProcessor(RecbillVO.class));
		String con = "";
		for(KpregisterDVO dvo:dvos){
			for(RecbillVO rvo:rvoList){
				if(rvo.getPk_recbill().equals(dvo.getVsrcid())){
					if(rvo.getInvoiceno().indexOf("、")!=-1){
						//"CONCAT(invoiceno,'"+aggvo[0].getParentVO().getKpcode()+"')";
						con = "replace(invoiceno,'、"+aggvo[0].getParentVO().getKpcode()+"','')";
					}else{
						con = "replace(invoiceno,'"+aggvo[0].getParentVO().getKpcode()+"','')";
					}
					
				}
			}
			
			String sql = "update zl_recbill set invoicemoney=nvl(invoicemoney,0)-nvl("+dvo.getKpmoney()+",0),invoiceno="+con+" where nvl(dr,0)=0 and pk_recbill='"+dvo.getVsrcid()+"'";
			dao.executeUpdate(sql);
		}
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	/**
	 * 发送会计平台
	 */
		//发送时校验是否有临时或正式单据
	public void sendXX(AggKpregisterVO aggvo) throws BusinessException{
		String pk=aggvo.getParentVO().getPk_kpregister();
		String sql="select count(1) from zl_kpregister r where pk_kpregister ='"+pk+"' and "
				+ "(exists(select 1 from fip_operatinglog g where g.src_relationid =r.pk_kpregister and nvl(g.dr,0)=0) "
				+ "or exists(select 1 from fip_relation g where g.src_relationid =r.pk_kpregister and nvl(g.dr,0)=0))";
		BaseDAO dao=new BaseDAO();
		Integer it=(Integer)dao.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("单据已传会计平台！");
		}
		
	  	KpregisterVO headvo=aggvo.getParentVO();
	  	KpregisterDVO[] bvos=(KpregisterDVO[])aggvo.getChildrenVO();
	  	
	  	FipRelationInfoVO reVO = new FipRelationInfoVO();
		reVO.setPk_group(headvo.getPk_group());
		reVO.setPk_org(headvo.getPk_org());
		reVO.setRelationID(headvo.getPrimaryKey());
		reVO.setPk_system("ZLH6");
		reVO.setBusidate(new UFDate());
		reVO.setPk_billtype("H650");
		reVO.setFreedef1(headvo.getVbillcode());
		
		UFDouble ud=headvo.getKpmoney().add(new UFDouble(0), 2);
		reVO.setFreedef3(ud.toString());
		reVO.setPk_operator(headvo.getCreator());
		
		FipMessageVO messageVO = new FipMessageVO();
		messageVO.setBillVO(aggvo);
		messageVO.setMessagetype(FipMessageVO.MESSAGETYPE_ADD);
		messageVO.setMessageinfo(reVO);

		 try {
			NCLocator.getInstance().lookup(IFipMessageService.class).sendMessage(messageVO);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException("传送单据生成平台出错"+e.getMessage());
		}
		 
		String sqlnew="update zl_kpregister set is_kjpt='Y' where pk_kpregister='"+headvo.getPk_kpregister()+"'";
		if(!aggvo.getParentVO().getIs_kjpt().booleanValue()){
			dao.executeUpdate(sqlnew);
		}
  }
	
	/**
	 * 取消发送会计平台
	 */
	public void unsendXX(AggKpregisterVO aggvo) throws BusinessException{
		//取消发送时校验是否有临时或正式单据
		String pk=aggvo.getParentVO().getPk_kpregister();
		String sql="select count(1) from zl_kpregister r where pk_kpregister ='"+pk+"' and "
				+ " exists(select 1 from fip_relation g where g.src_relationid =r.pk_kpregister and nvl(g.dr,0)=0)";
		BaseDAO dao=new BaseDAO();
		Integer it=(Integer)dao.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("单据已生成正式凭证，不允许取消审批！");
		}
		
		KpregisterVO headvo=aggvo.getParentVO();
	  	
	  	FipRelationInfoVO reVO = new FipRelationInfoVO();
		reVO.setPk_group(headvo.getPk_group());
		reVO.setPk_org(headvo.getPk_org());
		reVO.setRelationID(headvo.getPrimaryKey());
		reVO.setPk_system("ZLH6");
		reVO.setBusidate(new UFDate());
		reVO.setPk_billtype("H650");
		reVO.setFreedef1(headvo.getVbillcode());
		reVO.setPk_operator(headvo.getCreator());
		
		FipMessageVO messageVO = new FipMessageVO();
		messageVO.setBillVO(aggvo);
		messageVO.setMessagetype(FipMessageVO.MESSAGETYPE_DEL);
		messageVO.setMessageinfo(reVO);

		 try {
			NCLocator.getInstance().lookup(IFipMessageService.class).sendMessage(messageVO);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessException("传送单据生成平台出错"+e.getMessage());
		}
		String sqlnew="update zl_kpregister set is_kjpt='N' where pk_kpregister='"+headvo.getPk_kpregister()+"'";
		if(aggvo.getParentVO().getIs_kjpt().booleanValue()){
			dao.executeUpdate(sqlnew);
		}
  }
}
