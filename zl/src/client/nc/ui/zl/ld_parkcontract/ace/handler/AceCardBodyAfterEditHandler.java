package nc.ui.zl.ld_parkcontract.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
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
		BillCardPanel panel=billForm.getBillCardPanel();
		int rowcount1=panel.getBillModel("pk_parkcontract_c").getRowCount();
		int rowcount2=panel.getBillModel("pk_parkcontract_f").getRowCount();
		//业务拆分
		if(e.getKey().equals("ndiscountmoney")&&tabcode.equals("pk_parkcontract_c")){
			
			UFDouble ud=getUFdobj(e.getValue());
			UFDouble nrentmoney=getUFdobj(model2.getValueAt(e.getRow(), "nrentmoney"));
			model2.setValueAt(nrentmoney.sub(ud), e.getRow(), "nreceivemoney");
			UFDouble count = new UFDouble(0);
			for(int i=0;i<rowcount1;i++){
				Object obj1 = model2.getValueObjectAt(i, "nreceivemoney");
				
				count = count.add(getUFdobj(obj1), 2, 0);
			}
			
			panel.getHeadItem("vdef1").setValue(count);
		}
		//车位区
		if(e.getKey().equals("parkarea")&&tabcode.equals("pk_parkcontract_b")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改车位区将清空[业务拆分][财务拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					//panel.setHeadItem(e.getKey(), e.getOldValue());
					model2.setValueAt(e.getOldValue(), e.getRow(), "parkarea");
					return ;
				}
				panel.getBillModel("pk_parkcontract_c").clearBodyData();
				panel.getBillModel("pk_parkcontract_f").clearBodyData();
			}
		}
		//判断车位重复
		if(e.getKey().equals("parknum")&&tabcode.equals("pk_parkcontract_b")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改车位号将清空[业务拆分][财务拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					//panel.setHeadItem(e.getKey(), e.getOldValue());
					model2.setValueAt(e.getOldValue(), e.getRow(), "parknum");
					return ;
				}
				panel.getBillModel("pk_parkcontract_c").clearBodyData();
				panel.getBillModel("pk_parkcontract_f").clearBodyData();
			}
			//获取当前车位区
			Object parkarea = model2.getValueObjectAt(e.getRow(), "parkarea");
			//Object parkareaobj = ((DefaultConstEnum)parkarea).getValue();
			int row = model2.getRowCount();
			for(int i=0;i<row-1;i++){
				Object obj1 = model2.getValueObjectAt(i, "parkarea");
				Object obj2_1 = model2.getValueObjectAt(i, "parknum");
				if(obj2_1!=null&&obj1!=null){
					Object obj2 = ((DefaultConstEnum)obj2_1).getValue();
					if((obj1.equals(parkarea))&&obj2.equals(e.getValue())&&(i!=e.getRow())){
						MessageDialog.showHintDlg(billForm, "提示", "车位号"+obj2_1+"重复,请检查！");
						model2.setValueAt("", e.getRow(), 3);
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
