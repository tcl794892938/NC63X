package nc.ui.zl.ld_carcontract.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.tcl_contract.ace.config.CalendarUtls;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	private ShowUpableBillForm billForm;
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		BillCardPanel panel=e.getBillCardPanel();
		int rowcount1=panel.getBillModel("pk_carcontract_c").getRowCount();
		int rowcount2=panel.getBillModel("pk_carcontract_f").getRowCount();
		if(e.getKey().equals("enddate")||e.getKey().equals("startdate")){
			
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "����ʱ�佫���[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				panel.getBillModel("pk_carcontract_c").clearBodyData();
				panel.getBillModel("pk_carcontract_f").clearBodyData();
			}
			
			Object obj1=e.getBillCardPanel().getHeadItem("enddate").getValueObject();
			Object obj2=e.getBillCardPanel().getHeadItem("startdate").getValueObject();
			if(obj1==null||obj2==null){
				return ;
			}
			UFDate eddate = new UFDate(obj1.toString());
			UFDate stdate = new UFDate(obj2.toString());
			if(eddate.before(stdate)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ͬ��ֹʱ�䲻��С�ں�ͬ��ʼʱ�䣡");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				return;
			}
		}
		
		
		//�շѱ�׼�༭���¼�
		if(e.getKey().equals("pk_feescale")){
			
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "�����շѱ�׼�����[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				panel.getBillModel("pk_carcontract_c").clearBodyData();
				panel.getBillModel("pk_carcontract_f").clearBodyData();
				
			}
			IUAPQueryBS IQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql = "select accountscal from zl_feescale e where nvl(dr,0)=0 and e.pk_feescale='"+e.getValue()+"'";
			Object accountscal = null;
			try {
				accountscal =  IQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			UFDouble nrentmoney = new UFDouble(accountscal.toString());
			e.getBillCardPanel().setHeadItem("nallrent", nrentmoney);
		}
		
		//
		if(e.getKey().equals("costcycle")||e.getKey().equals("pk_costproject")){//���ʽ-�շ���Ŀ
			
			
			int rowcount4=panel.getBillModel("pk_carcontract_c").getRowCount();
			int rowcount5=panel.getBillModel("pk_carcontract_f").getRowCount();
			String key="";
			if(e.getKey().equals("costcycle")){
				key="���ʽ";
			}else{
				key="�շ���Ŀ";
			}
			if(e.getValue()==null&&e.getKey().equals("pk_costproject")){
				e.getBillCardPanel().setHeadItem(e.getKey(), "");
				e.getBillCardPanel().setHeadItem("pk_feescale", "");
			}
			if(e.getKey().equals("costcycle")){
				Object obj1=e.getBillCardPanel().getHeadItem("enddate").getValueObject();
				Object obj2=e.getBillCardPanel().getHeadItem("startdate").getValueObject();
				if(obj1!=null&&obj2!=null){
					UFDate eddate = new UFDate(obj1.toString());
					UFDate stdate = new UFDate(obj2.toString());
					int count = CalendarUtls.getBetweenMonths(stdate, eddate);
					int count2 = Integer.parseInt(e.getValue().toString());
					if(count2>count&&count2!=13){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "�������ڴ��ں�ͬ��Чʱ�䳤�ȣ�");
						panel.setHeadItem(e.getKey(), 0);
						return;
					}
				}
			}
			if(rowcount4>0||rowcount5>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "����["+key+"]�����[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_carcontract_c").clearBodyData();
					panel.getBillModel("pk_carcontract_f").clearBodyData();
				}
			}
			
		}
		//�����շ���Ŀ
		if(e.getKey().equals("pk_project")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "������Ŀ�����[������Ϣ][������Ϣ][�����̯]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				
				panel.getBillModel("pk_carcontract_b").clearBodyData();
				panel.getBillModel("pk_carcontract_c").clearBodyData();
				panel.getBillModel("pk_carcontract_f").clearBodyData();
			}
			panel.getHeadItem("pk_customer").setValue(null);
		}
		if(e.getKey().equals("ntaxrate")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "������Ŀ�����[[�����̯]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				panel.getBillModel("pk_carcontract_f").clearBodyData();
			}
			
		}
		//�ͻ����Ʊ༭���¼�
		if(e.getKey().equals("pk_customer")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "���Ŀͻ����ƽ����[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_carcontract_c").clearBodyData();
					panel.getBillModel("pk_carcontract_f").clearBodyData();
				}
			}
			
			int rowcount3=panel.getBillModel("pk_carcontract_b").getRowCount();	
			for(int i=0;i<rowcount3;i++){
				panel.getBillModel("pk_carcontract_b").setValueAt(e.getValue(), i, 3);
				panel.getBillModel("pk_carcontract_b").setValueAt(null, i, 2);
				
			}
			
		}
	}

}
