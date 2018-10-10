package nc.ui.zl.costproject.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.EventFromClosingHandlerJudger;
import nc.ui.uif2.components.CommonConfirmDialogUtils;

public class CancelAction extends nc.ui.pubapp.uif2app.actions.CancelAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();

	    // ����Ǵ�ClosingHandler������CancelAction����ѯ�ʣ�ֱ��ȡ��
	    // ����ڶ�ҳǩ������������ġ� ����һ��Ľڵ㣬���ֱ�ӹر��ˣ��Ƿ����setUiState��ʵ�޹ؽ�Ҫ
	    if (EventFromClosingHandlerJudger.isFromClosingHandler(e)
	        || UIDialog.ID_YES == CommonConfirmDialogUtils
	            .showConfirmCancelDialog(this.getModel().getContext()
	                .getEntranceUI()))

	    {

	      this.doBeforeCancel();
	      this.getModel().setUiState(UIState.NOT_EDIT);
	      // ȡ��ʱ��������״̬�仯�⣬����Ҫ��������ѡ������
	      this.doResetSelectedData();
	      ShowStatusBarMsgUtil.showStatusBarMsg(
	          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
	              "0pubapp-0125")/*@res "��ȡ��"*/, this.getModel().getContext());
	      this.canceled = true;
	    ((ShowUpableBillForm)getEditor()).setValue(obj);
	    }
	    else {
	      this.canceled = false;
	    }
	  
	}
}
