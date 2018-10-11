package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ITcl_contractMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractCustVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractVO;

public class RenewalAction extends nc.ui.pubapp.uif2app.actions.CopyAction {
	private static final long serialVersionUID = 3997648355004022401L;
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}
	
	public RenewalAction(){
		super();
		this.setCode("renewal");
		this.setBtnName("合同续约");
	}
	

	  @Override
	  public void doAction(ActionEvent e) throws Exception {
		  
		  Object obj=getModel().getSelectedData();
		  AggContractVO aggvo1=(AggContractVO)obj;
		  String pk=aggvo1.getParentVO().getPk_contract();
		  
		  ITcl_contractMaintain itm=NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
		  AggContractVO newagg=itm.queryHTbyPK(pk);
		  
		  getModel().directlyUpdate(newagg);
		  AggContractVO aggvo=(AggContractVO) getModel().getSelectedData();
		  if(aggvo==null){
				return;
			}
		  ContractVO vo = aggvo.getParentVO();
		  UFDate enddate =aggvo.getParentVO().getDenddate();
		  
		  vo.setDstartdate(enddate.getDateAfter(1));
		  vo.setDrentdate(enddate.getDateAfter(1));
		  //vo.setAllrent(new UFDouble(0));
		  vo.setDenddate(null);
		  vo.setVdef1(vo.getPk_contract());
		  vo.setPk_contract("");
		  vo.setVersion(-1);
		  
		  vo.setVbillno(null);
		  vo.setHtcode(null);
		  vo.setPaystyle(null);
		  vo.setNarea(null);
		  vo.setNbzjmny(null);
		  vo.setNdaymny(null);
		  vo.setNdegree(null);
		  vo.setNmny(null);
		  vo.setNmonthmny(null);
		  vo.setNrentprice(null);
		  vo.setNyearmny(null);
		  vo.setNyhmny(null);
		  vo.setNysmny(null);
		  vo.setNzqmny(null);
		  vo.setDindate(null);
		  vo.setDoutdate(null);
		  vo.setHtstatus(1);
		  vo.setVbillstatus(-1);
		  vo.setCreator(null);
		  vo.setCreationtime(null);
		  vo.setModifier(null);
		  vo.setModifiedtime(null);
		  vo.setApprover(null);
		  vo.setApprovenote(null);
		  vo.setDapprovetime(null);
		  vo.setDbilldate(new UFDate());
		  vo.setVmzq(null);
		  vo.setVdzfs(null);
		  vo.setIs_mz(null);
		  vo.setVmemo(null);
		  vo.setPk_contract(null);
		  //vo.setState(-1);
		  bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
		  
		  ContractCustVO []vos1=aggvo.getChildCustVO();
		  ContractHouseVO[] vos2=aggvo.getChildHouseVO();
		  for(ContractCustVO vo1:vos1){
			  vo1.setPk_contract_cust(null);
		  }
		  for(ContractHouseVO vo2:vos2){
			  vo2.setPk_contract_house(null);
		  }
		 
		  super.doAction(e);
		 
		  bill.getBillCardPanel().getBillModel("pk_contract_bzj").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_mzq").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_zzq").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_fkmx").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_zqmx").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_zjmx").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_ywcf").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_cwcf").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_zqfy").clearBodyData();
		  bill.getBillCardPanel().getBillModel("pk_contract_zqfycf").clearBodyData();

	  }


		@Override
		protected boolean isActionEnable() {
			Object obj=getModel().getSelectedData();
			if(obj==null){
				return false;
			}
			AggContractVO aggvo=(AggContractVO)obj;
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql = "select count(*) from zl_contract where nvl(dr,0)=0 and vdef1='"+aggvo.getParentVO().getPk_contract()+"'";
			Integer count = 0;
			try {
				count = (Integer) iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if(count>0||aggvo.getParentVO().getVbillstatus()!=1||aggvo.getParentVO().getHtstatus()==4){
				return false;
			}
			return true;
		}
		
		
}
