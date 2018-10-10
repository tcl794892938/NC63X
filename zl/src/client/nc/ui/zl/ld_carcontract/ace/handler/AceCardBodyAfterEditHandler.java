package nc.ui.zl.ld_carcontract.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		String tabcode=e.getBillCardPanel().getCurrentBodyTableCode();
		BillModel model2=e.getBillCardPanel().getBillModel(tabcode);
		//业务拆分
		if(e.getKey().equals("ndiscountmoney")&&tabcode.equals("pk_carcontract_c")){
			
			UFDouble ud=getUFdobj(e.getValue());
			UFDouble nrentmoney=getUFdobj(model2.getValueAt(e.getRow(), "nrentmoney"));
			model2.setValueAt(nrentmoney.sub(ud), e.getRow(), "nreceivemoney");
			UFDouble count = new UFDouble(0);
			int rowcount1=e.getBillCardPanel().getBillModel("pk_carcontract_c").getRowCount();
			for(int i=0;i<rowcount1;i++){
				Object obj1 = model2.getValueObjectAt(i, "nreceivemoney");
				
				count = count.add(getUFdobj(obj1), 2, 0);
			}
			
			e.getBillCardPanel().getHeadItem("vdef1").setValue(count);
		}
		//判断车牌号重复
		if(e.getKey().equals("platenum")&&tabcode.equals("pk_carcontract_b")){
			BillModel model3=e.getBillCardPanel().getBillModel("pk_carcontract_c");
			BillModel model4=e.getBillCardPanel().getBillModel("pk_carcontract_f");
			int row1 = model3.getRowCount();
			int row2 = model4.getRowCount();
			if(row1>0||row2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改车牌号将清空[业务拆分][财务拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					model2.setValueAt(e.getOldValue(), e.getRow(), "platenum");
					return ;
				}
				billForm.getBillCardPanel().getBillModel("pk_carcontract_c").clearBodyData();
				billForm.getBillCardPanel().getBillModel("pk_carcontract_f").clearBodyData();
				
			}
			int row = model2.getRowCount();
			for(int i=0;i<row;i++){
				Object obj1 =  model2.getValueObjectAt(i, "platenum");
				if(null!=obj1){
				
					Object obj2 = ((DefaultConstEnum)obj1).getValue();
					if((obj2.equals(e.getValue()))&&(i!=e.getRow())){
						
						MessageDialog.showHintDlg(billForm, "提示", "车牌号"+obj1+"重复,请检查！");
						model2.setValueAt("", e.getRow(), 2);
						return;
					}
				}
			}
		}
		
	}
	private UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}
