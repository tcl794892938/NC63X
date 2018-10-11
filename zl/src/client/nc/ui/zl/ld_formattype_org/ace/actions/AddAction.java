package nc.ui.zl.ld_formattype_org.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.eampub.vochange.AddApprovePushFlagRule;
import nc.vo.zl.ld_formattype.FormattypeVO;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction {
	private ShowUpableBillForm billForm;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		
		FormattypeVO vo=(FormattypeVO) getModel().getSelectedData();
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if(vo != null){
			String sql3 = "select count(*) from zl_formattype where nvl(dr,0)=0 and vdef1 = '0' " +
					     "and upname ='"+vo.getPk_formattype()+"'";
			int count = (Integer) iQ.executeQuery(sql3, new ColumnProcessor());
			if(count > 0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", 
						                  "��ҵ̬�´��ڼ��ż�������������������֯����");
				return;
			}
		}
		//�費�ɱ༭
		
		super.doAction(e);
	}

	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		FormattypeVO vo=(FormattypeVO) getModel().getSelectedData();
		if(vo == null){
			return false;
		}
		
		return super.isActionEnable();
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
}