package nc.ui.zl.hql_familyfile.ace.actions;

import java.awt.event.ActionEvent;

public class RefreshAction extends nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		isActionEnable();
		super.doAction(e);
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		return true;
	}
}
