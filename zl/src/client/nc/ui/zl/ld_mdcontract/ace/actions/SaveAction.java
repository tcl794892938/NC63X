package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import com.borland.jbcl.control.Message;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

import nc.vo.pub.lang.UFDate;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction {

	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		//billForm.getBillCardPanel().getHeadItem("status").getValueObject();
		AggMdcontractVO aggvo = (AggMdcontractVO) billForm.getValue();
		
		Object enddate = billForm.getBillCardPanel().getHeadItem("enddate").getValueObject();
		billForm.getBillCardPanel().setHeadItem("version", -1);
		billForm.getBillCardPanel().setHeadItem("dr", 0);
		UFDate date1 = new UFDate(enddate.toString());
		BillModel model = panel.getBillModel("pk_mdcontract_c");
		int row = model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "����û��ֵ�����ɱ��棡");
			return;
		}
		for(int i=0;i<row;i++){
			Object obj_mend = billForm.getBillCardPanel().getBillModel("pk_mdcontract_c").getValueAt(i, "menddate");
			UFDate date2 = new UFDate(obj_mend.toString());
			if(date2.after(date1)){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�շѽ�ֹʱ�䲻�ܴ��ں�ͬ��ֹ�գ����ɱ��棡");
				return;
			}
		}
		//�жϱ������һ�з�����ֹʱ���Ƿ�Ϊ��ͬ��ֹʱ��
		Object obj_mend = billForm.getBillCardPanel().getBillModel("pk_mdcontract_c").getValueAt(row-1, "menddate");
		if(!date1.equals(getUFdateObject(obj_mend))){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�����շѽ�ֹʱ��Ӧ���ں�ͬ��ֹ�գ����ɱ��棡");
			return;
		}
		//У������ظ�
		Object pkobj=billForm.getBillCardPanel().getHeadItem("pk_mdcontract").getValueObject();
		Object code=billForm.getBillCardPanel().getHeadItem("code").getValueObject();
		String sql1="select count(1) from zl_mdcontract where nvl(dr,0)=0  and " +
				" code='"+code+"' and pk_mdcontract<>'"+pkobj+"' and version=-1";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql1, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(billForm, "��ʾ", "��ͬ�����ظ���");
			return ;
		}
		
		super.doAction(e);
		
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	public UFDate getUFdateObject(Object obj){
		return obj==null?new UFDate():new UFDate(obj.toString());
	}
}
