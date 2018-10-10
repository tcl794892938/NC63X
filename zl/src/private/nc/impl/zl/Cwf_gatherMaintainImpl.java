package nc.impl.zl;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.ace.AceCwf_gatherPubServiceImpl;
import nc.pubitf.fip.service.IFipMessageService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.zl.cwf_gather.GatherBVO;
import nc.vo.zl.cwf_gather.GatherVO;
import nc.itf.zl.ICwf_gatherMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class Cwf_gatherMaintainImpl extends AceCwf_gatherPubServiceImpl
		implements ICwf_gatherMaintain {

	@Override
	public void delete(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggGatherVO[] insert(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggGatherVO[] update(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggGatherVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggGatherVO[] save(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggGatherVO[] unsave(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggGatherVO[] approve(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		AggGatherVO[]bills= super.pubapprovebills(clientFullVOs, originBills);
		if(bills.length>1){
			throw new BusinessException("请选择一条单据！");
		}
		GatherVO hvo=bills[0].getParentVO();
		
		if(hvo.getVbillstatus()==1){
			//hvo.setIs_kjpt(new UFBoolean(true));
			sendXX(bills[0]);
		}
		if(hvo.getIsadd().booleanValue()){
			return bills;
		}
		
		GatherBVO[] bvos=(GatherBVO[]) bills[0].getChildrenVO();
		Map<String, String> map=new HashMap<String, String>();
		Map<String, String> map2=new HashMap<String, String>();
		map.put("zl_contract_bzj", "nskmny");
		map.put("zl_contract_ywcf", "nskmny");
		map.put("zl_contract_zqfycf", "nskmny");
		map.put("zl_carcontract_c", "ncollectemoney");
		map.put("zl_parkcontract_c", "ncollectemoney");
		map.put("zl_mdcontract_c", "getmoney");
		map.put("zl_payment_b", "nskmny");
		map.put("zl_prepayment_b", "nskmny");
		map.put("zl_zylist_sr", "yjsmoney");
		map.put("zl_throwalease_fyqs", "nytmny");
		map.put("zl_throwalease_bzjth", "nytmny");
		map.put("zl_throwalease_zqfyqs", "nytmny");
		
		
		map2.put("zl_contract_bzj", "pk_contract_bzj");
		map2.put("zl_contract_ywcf", "pk_contract_ywcf");
		map2.put("zl_contract_zqfycf", "pk_contract_zqfycf");
		map2.put("zl_carcontract_c", "pk_carcontract_c");
		map2.put("zl_parkcontract_c", "pk_parkcontract_c");
		map2.put("zl_mdcontract_c", "pk_mdcontract_c");
		map2.put("zl_payment_b", "pk_paymentb");
		map2.put("zl_prepayment_b", "pk_prepayment_b");
		map2.put("zl_zylist_sr", "pk_zylist_sr");
		map2.put("zl_throwalease_fyqs", "pk_throwaleasefyqs");
		map2.put("zl_throwalease_bzjth", "pk_throwaleasebzjth");
		map2.put("zl_throwalease_zqfyqs", "pk_throwalease_zqfyqs");
		BaseDAO dao=new BaseDAO();
		for(GatherBVO bvo:bvos){
			String sql1="update zl_recbill set nrealmoney=nvl(nrealmoney,0)+nvl("+bvo.getNskmny()+",0) where pk_recbill='"+bvo.getVsrcid()+"' ";
			dao.executeUpdate(sql1);//回写应收单
			
			String sql2="update "+bvo.getVdef2()+" set "+map.get(bvo.getVdef2())+"=nvl("+map.get(bvo.getVdef2())+",0)+" +
					"nvl("+getjdz(bvo.getNskmny())+",0)" +
					" where "+map2.get(bvo.getVdef2())+"='"+bvo.getVdef1()+"'";
			dao.executeUpdate(sql2);//回写业务单据
			
			if(bvo.getVdef2().equals("zl_contract_bzj")){
				String sql4="update zl_contract_bzj set nqkmny=nqkmny-nvl("+getjdz(bvo.getNskmny())+",0) where pk_contract_bzj='"+bvo.getVdef1()+"'";
				dao.executeUpdate(sql4);
			}
			
//			if(bvo.getVdef2().equals("zl_prepayment_b")){
//				String sql3="update zl_prepayment_b set NOFFSETMNY=nvl(NOFFSETMNY,0)+nvl("+bvo.getNskmny()+",0) where PK_PREPAYMENT_B" +
//						" ='"+bvo.getVdef1()+"'";
//				dao.executeUpdate(sql3);
//			}
		}
		
		
		
		return bills;
	}

	@Override
	public AggGatherVO[] unapprove(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {

		AggGatherVO[]bills= super.pubapprovebills(clientFullVOs, originBills);
		if(bills.length>1){
			throw new BusinessException("请选择一条单据！");
		}
		GatherVO hvo=bills[0].getParentVO();
		
		if(originBills[0].getParentVO().getVbillstatus()==1){
			//clientFullVOs[0].getParentVO().setIs_kjpt(new UFBoolean(false));
			unsendXX(originBills[0]);
		}
		if(hvo.getIsadd().booleanValue()){
			return bills;
		}
		GatherBVO[] bvos=(GatherBVO[]) bills[0].getChildrenVO();
		Map<String, String> map=new HashMap<String, String>();
		Map<String, String> map2=new HashMap<String, String>();
		map.put("zl_contract_bzj", "nskmny");
		map.put("zl_contract_ywcf", "nskmny");
		map.put("zl_contract_zqfycf", "nskmny");
		map.put("zl_carcontract_c", "ncollectemoney");
		map.put("zl_parkcontract_c", "ncollectemoney");
		map.put("zl_mdcontract_c", "getmoney");
		map.put("zl_payment_b", "nskmny");
		map.put("zl_prepayment_b", "nskmny");
		map.put("zl_zylist_sr", "yjsmoney");
		map.put("zl_throwalease_fyqs", "nytmny");
		map.put("zl_throwalease_bzjth", "nytmny");
		
		map2.put("zl_contract_bzj", "pk_contract_bzj");
		map2.put("zl_contract_ywcf", "pk_contract_ywcf");
		map2.put("zl_contract_zqfycf", "pk_contract_zqfycf");
		map2.put("zl_carcontract_c", "pk_carcontract_c");
		map2.put("zl_parkcontract_c", "pk_parkcontract_c");
		map2.put("zl_mdcontract_c", "pk_mdcontract_c");
		map2.put("zl_payment_b", "pk_paymentb");
		map2.put("zl_prepayment_b", "pk_prepayment_b");
		map2.put("zl_zylist_sr", "pk_zylist_sr");
		map2.put("zl_throwalease_fyqs", "pk_throwaleasefyqs");
		map2.put("zl_throwalease_bzjth", "pk_throwaleasebzjth");
		BaseDAO dao=new BaseDAO();
		for(GatherBVO bvo:bvos){
			String sql1="update zl_recbill set nrealmoney=nvl(nrealmoney,0)-nvl("+bvo.getNskmny()+",0) where pk_recbill='"+bvo.getVsrcid()+"' ";
			dao.executeUpdate(sql1);//回写应收单
			
			String sql2="update "+bvo.getVdef2()+" set "+map.get(bvo.getVdef2())+"=nvl("+map.get(bvo.getVdef2())+",0)-" +
					"nvl("+getjdz(bvo.getNskmny())+",0)" +
					" where "+map2.get(bvo.getVdef2())+"='"+bvo.getVdef1()+"'";
			dao.executeUpdate(sql2);//回写业务单据
			
			if(bvo.getVdef2().equals("zl_contract_bzj")){
				String sql4="update zl_contract_bzj set nqkmny=nqkmny+nvl("+getjdz(bvo.getNskmny())+",0) where pk_contract_bzj='"+bvo.getVdef1()+"'";
				dao.executeUpdate(sql4);
			}
			
//			if(bvo.getVdef2().equals("zl_prepayment_b")){
//				String sql3="update zl_prepayment_b set NOFFSETMNY=nvl(NOFFSETMNY,0)-nvl("+bvo.getNskmny()+",0) where PK_PREPAYMENT_B" +
//						" ='"+bvo.getVdef1()+"'";
//				dao.executeUpdate(sql3);
//			}
		}
		
		return bills;
	
	}

	/**
	 * 发送会计平台
	 */
		//发送时校验是否有临时或正式单据
	public void sendXX(AggGatherVO aggvo) throws BusinessException{
		String pk=aggvo.getParentVO().getPk_gather();
		String sql="select count(1) from zl_gather r where pk_gather ='"+pk+"' and "
				+ "(exists(select 1 from fip_operatinglog g where g.src_relationid =r.pk_gather and nvl(g.dr,0)=0) "
				+ "or exists(select 1 from fip_relation g where g.src_relationid =r.pk_gather and nvl(g.dr,0)=0))";
		BaseDAO dao=new BaseDAO();
		Integer it=(Integer)dao.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("单据已传会计平台！");
		}
		
	  	GatherVO headvo=aggvo.getParentVO();
	  	GatherBVO[] bvos=(GatherBVO[])aggvo.getChildrenVO();
	  	
	  	FipRelationInfoVO reVO = new FipRelationInfoVO();
		reVO.setPk_group(headvo.getPk_group());
		reVO.setPk_org(headvo.getPk_org());
		reVO.setRelationID(headvo.getPrimaryKey());
		reVO.setPk_system("ZLH6");
		reVO.setBusidate(new UFDate());
		reVO.setPk_billtype("H630");
		reVO.setFreedef1(headvo.getVbillcode());
		
		UFDouble ud=headvo.getNskmny().add(new UFDouble(0), 2);
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
		 
		String sqlnew="update zl_gather set is_kjpt='Y' where pk_gather='"+headvo.getPk_gather()+"'";
		if(!aggvo.getParentVO().getIs_kjpt().booleanValue()){
			dao.executeUpdate(sqlnew);
		}
  }
	
	/**
	 * 取消发送会计平台
	 */
	public void unsendXX(AggGatherVO aggvo) throws BusinessException{
		//取消发送时校验是否有临时或正式单据
		String pk=aggvo.getParentVO().getPk_gather();
		String sql="select count(1) from zl_gather r where pk_gather ='"+pk+"' and "
				+ " exists(select 1 from fip_relation g where g.src_relationid =r.pk_gather and nvl(g.dr,0)=0)";
		BaseDAO dao=new BaseDAO();
		Integer it=(Integer)dao.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("单据已生成正式凭证，不允许取消审批！");
		}
		
	  	GatherVO headvo=aggvo.getParentVO();
	  	
	  	FipRelationInfoVO reVO = new FipRelationInfoVO();
		reVO.setPk_group(headvo.getPk_group());
		reVO.setPk_org(headvo.getPk_org());
		reVO.setRelationID(headvo.getPrimaryKey());
		reVO.setPk_system("ZLH6");
		reVO.setBusidate(new UFDate());
		reVO.setPk_billtype("H630");
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
		String sqlnew="update zl_gather set is_kjpt='N' where pk_gather='"+headvo.getPk_gather()+"'";
		if(aggvo.getParentVO().getIs_kjpt().booleanValue()){
			dao.executeUpdate(sqlnew);
		}
  }
	
	public UFDouble getjdz(UFDouble ud){
		return ud.compareTo(new UFDouble(0))>0?ud:new UFDouble(0).sub(ud);
	}
}
