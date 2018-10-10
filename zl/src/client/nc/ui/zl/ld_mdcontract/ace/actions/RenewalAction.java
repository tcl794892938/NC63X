package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;

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
		  
		  AggMdcontractVO aggvo=(AggMdcontractVO) getModel().getSelectedData();
		  if(aggvo==null){
				return;
			}
		  MdcontractVO vo = aggvo.getParentVO();
		  MdcontractCVO[] cvo = (MdcontractCVO[])aggvo.getChildrenVO();
		  UFDate enddate =aggvo.getParentVO().getEnddate();
		  
		 
		  vo.setVbillcode("");
		  
		  vo.setStartdate(enddate.getDateAfter(1));
		  vo.setRentdate(enddate.getDateAfter(1));
		  vo.setAllrent(new UFDouble(0));
		  vo.setEnddate(null);
		  vo.setRemark("");
		  //vo.setVsrcid();
		  vo.setVdef1(vo.getPk_mdcontract());
		  vo.setPk_mdcontract("");
		  vo.setVersion(-1);
		  vo.setState(-1);
		  bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
		 
		  super.doAction(e);
		  bill.getBillCardPanel().getBillModel("pk_mdcontract_c").clearBodyData();

	  }


		@Override
		protected boolean isActionEnable() {
			Object obj=getModel().getSelectedData();
			if(obj==null){
				return false;
			}
			AggMdcontractVO aggvo=(AggMdcontractVO)obj;
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql = "select count(*) from zl_mdcontract where nvl(dr,0)=0 and vdef1='"+aggvo.getParentVO().getPk_mdcontract()+"'";
			Integer count = 0;
			try {
				count = (Integer) iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if(count>0){
				return false;
			}
			return true;
		}
		
		
}
