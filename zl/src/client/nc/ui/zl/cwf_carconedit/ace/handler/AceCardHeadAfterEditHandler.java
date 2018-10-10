package nc.ui.zl.cwf_carconedit.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	ShowUpableBillForm billform;
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKey().equals("pk_customer")){
			int rowcount1 = billform.getBillCardPanel().getBillTable("pk_carconedit_b").getRowCount();
			int rowcount2 = billform.getBillCardPanel().getBillTable("pk_carconedit_c").getRowCount();
			if(rowcount1>0){
				for(int i=0;i<rowcount1;i++){
					int count=0;
					for(int j=0;j<rowcount2;j++){
						Object obj1 = billform.getBillCardPanel().getBillTable("pk_carconedit_c").getValueAt(j, 11);
						Double getm = Double.parseDouble(getObj(obj1));
						Object obj2 = billform.getBillCardPanel().getBillTable("pk_carconedit_c").getValueAt(j, 2);
						//车辆更名
						if(obj2==null){
							//基本信息车牌号
							Object b_plate = billform.getBillCardPanel().getBillTable("pk_carconedit_b").getValueAt(i, 3);
							//费用信息车牌号
							Object c_plate = billform.getBillCardPanel().getBillTable("pk_carconedit_c").getValueAt(j, 3);
							if(b_plate.equals(c_plate)){
								if(getm>0){
									count++;
								}
							}
							
						}else{//车位更名
							//基本信息车位区、费用信息车位区
							Object b_patk = billform.getBillCardPanel().getBillTable("pk_carconedit_b").getValueAt(i, 1);
							Object c_patk = billform.getBillCardPanel().getBillTable("pk_carconedit_c").getValueAt(j, 1);
							//基本信息车位号、费用信息车位号
							Object b_patn = billform.getBillCardPanel().getBillTable("pk_carconedit_b").getValueAt(i, 2);
							Object c_patn = billform.getBillCardPanel().getBillTable("pk_carconedit_c").getValueAt(j, 2);
							if(b_patk.equals(c_patk)&&b_patn.equals(c_patn)){
								if(getm>0){
									count++;
								}
							}
						}	
					}
					if(count<1){
						billform.getBillCardPanel().getBillTable("pk_carconedit_b").setValueAt(e.getValue(), i, 4);
					}
				}
			}
		}
	}
	public String getObj(Object obj){
		return obj==null?"0":obj.toString();
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

}
