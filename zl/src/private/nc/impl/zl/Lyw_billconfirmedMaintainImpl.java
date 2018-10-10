package nc.impl.zl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.ace.AceLyw_billconfirmedPubServiceImpl;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.ILyw_billconfirmedMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pubitf.fip.service.IFipMessageService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedBVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class Lyw_billconfirmedMaintainImpl extends AceLyw_billconfirmedPubServiceImpl
		implements ILyw_billconfirmedMaintain {

	@Override
	public void delete(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBillconfirmedVO[] insert(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBillconfirmedVO[] update(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBillconfirmedVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBillconfirmedVO[] save(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBillconfirmedVO[] unsave(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggBillconfirmedVO[] approve(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		AggBillconfirmedVO[] bills = super.pubapprovebills(clientFullVOs, originBills);
		//获得选中行的主表主键
		String pk_billconfirmed = bills[0].getParentVO().getPk_billconfirmed();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select * from zl_billconfirmedb where nvl(dr,0)=0 and pk_billconfirmed='"+pk_billconfirmed+"'";
		//String sql3 = "select * from zl_confirmation where nvl(dr,0)=0 ";
		String sql4 = "select * from zl_billconfirmed where nvl(dr,0)=0 and pk_billconfirmed='"+pk_billconfirmed+"'";
		IVOPersistence ivp = NCLocator.getInstance().lookup(IVOPersistence.class);
		ArrayList<BillconfirmedBVO> cdbvos=(ArrayList<BillconfirmedBVO>)iQ.executeQuery(sql, new BeanListProcessor(BillconfirmedBVO.class));
		//ArrayList<ConfirmationVO> cnvos=(ArrayList<ConfirmationVO>)iQ.executeQuery(sql3, new BeanListProcessor(ConfirmationVO.class));
		ArrayList<BillconfirmedVO> cdvos = (ArrayList<BillconfirmedVO>)iQ.executeQuery(sql4, new BeanListProcessor(BillconfirmedVO.class));
		Map<String, String> map=new HashMap<String, String>();
		Map<String, String> map2=new HashMap<String, String>();
		
		map.put("zl_contract_cwcf", "nconfirmed");
		map.put("zl_contract_bzj", "vdef1");
		map.put("zl_contract_zqfycf", "nconfirmed");
		map.put("zl_carcontract_f", "nconfirmed");
		map.put("zl_parkcontract_f", "nconfirmed");
		map.put("zl_mdcontract_c", "nconfirmed");
		map.put("zl_payment_b", "nconfirmed");
		map.put("zl_prepayment_b", "nconfirmed");
		map.put("zl_zylist_sr", "nconfirmed");
		map.put("zl_throwalease_fyqs", "nconfirmed");
		map.put("zl_throwalease_bzjth", "nconfirmed");
		map.put("zl_throwalease_zqfyqs", "nconfirmed");
	
		map2.put("zl_contract_cwcf", "pk_contract_cwcf");
		map2.put("zl_contract_bzj", "pk_contract_bzj");
		map2.put("zl_contract_zqfycf", "pk_contract_zqfycf");
		map2.put("zl_carcontract_f", "pk_carcontract_f");
		map2.put("zl_parkcontract_f", "pk_parkcontract_f");
		map2.put("zl_mdcontract_c", "pk_mdcontract_c");
		map2.put("zl_payment_b", "pk_paymentb");
		map2.put("zl_prepayment_b", "pk_prepayment_b");
		map2.put("zl_zylist_sr", "pk_zylist_sr");
		map2.put("zl_throwalease_fyqs", "pk_throwaleasefyqs");
		map2.put("zl_throwalease_bzjth", "pk_throwaleasebzjth");
		map2.put("zl_throwalease_zqfyqs", "pk_throwaleasezqfyqs");
		BaseDAO dao=new BaseDAO();

			for(int i=0;i<cdbvos.size();i++){
				//回写待收入确认单的已确认收入金额
//				for(int j=0;j<cnvos.size();j++){
//					if(cdbvos.get(i).getVsrcid().equals(cnvos.get(j).getPk_confirmation())){
//						//cnvos.get(j).setVdef2("N");
//						//保存amountconfirmed原始的值到vdef1
//						//cnvos.get(j).setVdef1(cnvos.get(j).getAmountconfirmed().toString());
////						cnvos.get(j).setAmountconfirmed(cnvos.get(j).getAmountconfirmed().add(cdbvos.get(i).getNnotaxmny()));
////						break;
//					};
//				}
				String sql1="update zl_confirmation set amountconfirmed=nvl(amountconfirmed,0)+nvl("+cdbvos.get(i).getNnotaxmny()+",0) " +
						"where pk_confirmation='"+cdbvos.get(i).getVsrcid()+"'";
				dao.executeUpdate(sql1);
				String sql2 = "";
				if(cdbvos.get(i).getVdef2().equals("zl_contract_bzj")){
					sql2="update "+cdbvos.get(i).getVdef2()+" set "+map.get(cdbvos.get(i).getVdef2())+"= to_char((case when "+map.get(cdbvos.get(i).getVdef2())+"='~' then 0 else to_number("+map.get(cdbvos.get(i).getVdef2())+") end) +"+cdbvos.get(i).getAmountconfirming()+")"+

							" where "+map2.get(cdbvos.get(i).getVdef2())+"='"+cdbvos.get(i).getVdef1()+"'";
				}else{
					sql2="update "+cdbvos.get(i).getVdef2()+" set "+map.get(cdbvos.get(i).getVdef2())+"=nvl("+map.get(cdbvos.get(i).getVdef2())+",0)+"+
							cdbvos.get(i).getAmountconfirming()+
							" where "+map2.get(cdbvos.get(i).getVdef2())+"='"+cdbvos.get(i).getVdef1()+"' ";
				}
				
				dao.executeUpdate(sql2);//回写业务单据
			}

		//ivp.updateVOList(cnvos);
		//传会计平台
		sendXX(bills[0]);
		cdvos.get(0).setIs_kjpt(new UFBoolean(true));
		ivp.updateVOList(cdvos);
		return bills;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggBillconfirmedVO[] unapprove(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		AggBillconfirmedVO[] bills = pubunapprovebills(clientFullVOs, originBills);
		String pk_billconfirmed = bills[0].getParentVO().getPk_billconfirmed();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select * from zl_billconfirmedb where nvl(dr,0)=0 and pk_billconfirmed='"+pk_billconfirmed+"'";
		//String sql3 = "select * from zl_confirmation where nvl(dr,0)=0 ";
		String sql4 = "select * from zl_billconfirmed where nvl(dr,0)=0 and pk_billconfirmed='"+pk_billconfirmed+"'";
		IVOPersistence ivp = NCLocator.getInstance().lookup(IVOPersistence.class);
		ArrayList<BillconfirmedBVO> cdbvos=(ArrayList<BillconfirmedBVO>)iQ.executeQuery(sql, new BeanListProcessor(BillconfirmedBVO.class));
		//ArrayList<ConfirmationVO> cnvos=(ArrayList<ConfirmationVO>)iQ.executeQuery(sql3, new BeanListProcessor(ConfirmationVO.class));
		ArrayList<BillconfirmedVO> cdvos = (ArrayList<BillconfirmedVO>)iQ.executeQuery(sql4, new BeanListProcessor(BillconfirmedVO.class));
		ArrayList<String> strs = new ArrayList<String>();
		strs.add(cdbvos.get(0).getVsrcid());
		Map<String, String> map=new HashMap<String, String>();
		Map<String, String> map2=new HashMap<String, String>();
		
		map.put("zl_contract_cwcf", "nconfirmed");
		map.put("zl_contract_bzj", "vdef1");
		map.put("zl_contract_zqfycf", "nconfirmed");
		map.put("zl_carcontract_f", "nconfirmed");
		map.put("zl_parkcontract_f", "nconfirmed");
		map.put("zl_mdcontract_c", "nconfirmed");
		map.put("zl_payment_b", "nconfirmed");
		map.put("zl_prepayment_b", "nconfirmed");
		map.put("zl_zylist_sr", "nconfirmed");
		map.put("zl_throwalease_fyqs", "nconfirmed");
		map.put("zl_throwalease_bzjth", "nconfirmed");
		map.put("zl_throwalease_zqfyqs", "nconfirmed");
	
		map2.put("zl_contract_cwcf", "pk_contract_cwcf");
		map2.put("zl_contract_bzj", "pk_contract_bzj");
		map2.put("zl_contract_zqfycf", "pk_contract_zqfycf");
		map2.put("zl_carcontract_f", "pk_carcontract_f");
		map2.put("zl_parkcontract_f", "pk_parkcontract_f");
		map2.put("zl_mdcontract_c", "pk_mdcontract_c");
		map2.put("zl_payment_b", "pk_paymentb");
		map2.put("zl_prepayment_b", "pk_prepayment_b");
		map2.put("zl_zylist_sr", "pk_zylist_sr");
		map2.put("zl_throwalease_fyqs", "pk_throwaleasefyqs");
		map2.put("zl_throwalease_bzjth", "pk_throwaleasebzjth");
		map2.put("zl_throwalease_zqfyqs", "pk_throwaleasezqfyqs");
		BaseDAO dao=new BaseDAO();
		
			for(int i=0;i<cdbvos.size();i++){
				//恢复上游单据的已确认收入
//				for(int j=0;j<cnvos.size();j++){
//					if(cdbvos.get(i).getVsrcid().equals(cnvos.get(j).getPk_confirmation())){
//						cnvos.get(j).setAmountconfirmed(cnvos.get(j).getAmountconfirmed().sub(cdbvos.get(i).getNnotaxmny()));
//					};
//				}
				String sql1="update zl_confirmation set amountconfirmed=nvl(amountconfirmed,0)-nvl("+cdbvos.get(i).getNnotaxmny()+",0) " +
						"where pk_confirmation='"+cdbvos.get(i).getVsrcid()+"'";
				dao.executeUpdate(sql1);
				String sql2="update "+cdbvos.get(i).getVdef2()+" set "+map.get(cdbvos.get(i).getVdef2())+"=nvl("+map.get(cdbvos.get(i).getVdef2())+",0)-"+
						cdbvos.get(i).getAmountconfirming()+
						" where "+map2.get(cdbvos.get(i).getVdef2())+"='"+cdbvos.get(i).getVdef1()+"'";
				
				dao.executeUpdate(sql2);//恢复业务单据已确认收入金额
			}
		
		//ivp.updateVOList(cnvos);
		//取消已传入会计平台的凭证
		unsendXX(bills[0]);
		cdvos.get(0).setIs_kjpt(new UFBoolean(false));
		ivp.updateVOList(cdvos);
		return bills;
	}
	/**
	 * 取消发送会计平台
	 */
	public void unsendXX(AggBillconfirmedVO aggvo) throws BusinessException{
		//取消发送时校验是否有临时或正式单据
		String pk=aggvo.getParentVO().getPk_billconfirmed();
		String sql="select count(1) from zl_billconfirmed r where pk_billconfirmed ='"+pk+"' and "
				+ " exists(select 1 from fip_relation g where g.src_relationid =r.pk_billconfirmed and nvl(g.dr,0)=0)";
		//BaseDAO dao=new BaseDAO();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("单据已生成正式凭证，不允许取消审批！");
		}
	  	BillconfirmedVO headvo=aggvo.getParentVO();
	  	FipRelationInfoVO reVO = new FipRelationInfoVO();
		reVO.setPk_group(headvo.getPk_group());
		reVO.setPk_org(headvo.getPk_org());
		reVO.setRelationID(headvo.getPrimaryKey());
		reVO.setPk_system("ZLH6");
		reVO.setBusidate(new UFDate());
		reVO.setPk_billtype("H641");
		reVO.setFreedef1(headvo.getVbilltypecode());
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
	}
	
	/**
	 * 发送会计平台
	 */
		//发送时校验是否有临时或正式单据
	public void sendXX(AggBillconfirmedVO aggvo) throws BusinessException{
		String pk=aggvo.getParentVO().getPk_billconfirmed();
		String sql="select count(1) from zl_billconfirmed r where pk_billconfirmed ='"+pk+"' and "
				+ "(exists(select 1 from fip_operatinglog g where g.src_relationid =r.pk_billconfirmed and nvl(g.dr,0)=0) "
				+ "or exists(select 1 from fip_relation g where g.src_relationid =r.pk_billconfirmed and nvl(g.dr,0)=0))";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("单据已传会计平台！");
		}
		BillconfirmedVO headvo=aggvo.getParentVO();
	  	FipRelationInfoVO reVO = new FipRelationInfoVO();
		reVO.setPk_group(headvo.getPk_group());
		reVO.setPk_org(headvo.getPk_org());
		reVO.setRelationID(headvo.getPrimaryKey());
		reVO.setPk_system("ZLH6");
		reVO.setBusidate(new UFDate());
		reVO.setPk_billtype("H641");
		reVO.setFreedef1(headvo.getVbillno());
		//reVO.setFreedef3(ud.toString());
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
//		String sqlnew="update zl_billconfirmed set is_kjpt='Y' where pk_billconfirmed='"+headvo.getPk_billconfirmed()+"'";
//		if(!aggvo.getParentVO().getIs_kjpt().booleanValue()){
//			dao.executeUpdate(sqlnew);
//		}
	}
}
