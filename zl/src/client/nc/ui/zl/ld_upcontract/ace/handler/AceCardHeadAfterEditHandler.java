package nc.ui.zl.ld_upcontract.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	ShowUpableBillForm billform;
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO �Զ����ɵķ������
		BillModel model2 = billform.getBillCardPanel().getBillModel("pk_upcontract_c");
		if(e.getKey().equals("pk_customer")){
			int rowcount = billform.getBillCardPanel().getBillTable().getRowCount();
			if(rowcount>0){
				for(int i=0;i<rowcount;i++){
					Object obj = billform.getBillCardPanel().getBillTable().getValueAt(i, 10);
					String get=obj==null?"0":obj.toString();
					Double getm = Double.parseDouble(get);
					if(getm<1){
						billform.getBillCardPanel().getBillTable().setValueAt(e.getValue(), i, 1);
					}
				}
			}
		}
		
		
		if(e.getKey().equals("enddate")){
			
			if(e.getValue()==null||e.getBillCardPanel().getHeadItem("startdate").getValueObject()==null){
				billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			
			UFDate eddate = new UFDate(e.getValue().toString());
			UFDate stdate = new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString());
			
			if(eddate.before(stdate)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ͬ��ֹʱ�䲻��С�ں�ͬ��ʼʱ�䣡");
				e.getBillCardPanel().getHeadItem("enddate").setValue(null);
				return;
			}
			int rowcount=model2.getRowCount();
			if(rowcount>0){
				for(int i=0;i<rowcount;i++){
					if(model2.getValueAt(i, "menddate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
						if(eddate.beforeDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ���ý�ֹʱ�䲻��С��֮ǰ���ý�ֹʱ�䣡");
							e.getBillCardPanel().getHeadItem("enddate").setValue(null);
							return;
						}
					}
					if(model2.getValueAt(i, "mstartdate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "mstartdate").toString());
						if(eddate.beforeDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ���ý�ֹʱ�䲻��С��֮ǰ���ÿ�ʼʱ�䣡");
							e.getBillCardPanel().getHeadItem("enddate").setValue(null);
							return;
						}
					}
				}
			}
		}
		
		if(e.getKey().equals("startdate")){
			
			if(e.getValue()==null||e.getBillCardPanel().getHeadItem("enddate").getValueObject()==null){
				billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			UFDate stdate = new UFDate(e.getValue().toString());
			UFDate eddate = new UFDate(e.getBillCardPanel().getHeadItem("enddate").getValueObject().toString());
			
			if(eddate.before(stdate)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ͬ��ֹʱ�䲻��С�ں�ͬ��ʼʱ�䣡");
				e.getBillCardPanel().getHeadItem("startdate").setValue(null);
				return;
			}
			int rowcount=model2.getRowCount();
			if(rowcount>0){
				for(int i=0;i<rowcount;i++){
					if(model2.getValueAt(i, "menddate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
						if(eddate.afterDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ʼʱ�䲻����֮ǰ���ý�ֹʱ��֮��");
							e.getBillCardPanel().getHeadItem("startdate").setValue(null);
							return;
						}
					}
					if(model2.getValueAt(i, "mstartdate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "mstartdate").toString());
						if(eddate.afterDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ʼʱ�䲻���ڷ��ÿ�ʼʱ��֮��");
							e.getBillCardPanel().getHeadItem("startdate").setValue(null);
							return;
						}
					}
				}
			}
			
		}
	}
	
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	

}
