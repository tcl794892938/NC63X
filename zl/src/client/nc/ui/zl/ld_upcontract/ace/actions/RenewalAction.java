package nc.ui.zl.ld_upcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.zl.ld_upcontract.UpcontractUVO;
import nc.vo.zl.ld_upcontract.UpcontractVO;

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
		  
		  AggUpcontractVO aggvo=(AggUpcontractVO) getModel().getSelectedData();
		  
		  UpcontractVO vo = aggvo.getParentVO();
		  UpcontractUVO[] cvo = (UpcontractUVO[])aggvo.getChildrenVO();
		  UFDate enddate =aggvo.getParentVO().getEnddate();
		  

		  vo.setPk_upcontract("");
		  vo.setCode("");
		  
		  vo.setStartdate(enddate.getDateAfter(1));
		  vo.setEnddate(null);
		  vo.setRemark("");
		 
		  for(int i = 0;i<cvo.length;i++){
			  cvo[i].setPk_upcontract_u("");
		  }
		  bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
		  bill.getBillCardPanel().getBillData().setBodyValueVO(cvo);
		  
		  super.doAction(e);
		  

	  }


		@Override
		protected boolean isActionEnable() {
			Object obj=getModel().getSelectedData();
			if(obj==null){
				return false;
			}
			return true;
		}

}
