package nc.ui.zl.ld_housesource.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.vo.uif2.LoginContext;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
/**
 * <b> 组织切换事件 </b>
 *
 * @author author
 * @version tempProject version
 */
public class AceOrgChangeHandler implements IAppEventHandler<OrgChangedEvent> {

	private BillForm billForm;

	@Override
	public void handleAppEvent(OrgChangedEvent e) {
		if (this.billForm.isEditable()) {
			// 在编辑状态下，主组织切换时，清空界面数据，自动表体增行，并设置行号
			BillCardPanel panel = billForm.getBillCardPanel();
			panel.setHeadItem("projectname", null);
			panel.setHeadItem("buildname", null);
			panel.setHeadItem("unit", null);
			panel.setHeadItem("roomnumber", null);
			panel.setHeadItem("estatecode", null);
			panel.setHeadItem("estatename", null);
			panel.setHeadItem("floorn", null);
			panel.setHeadItem("pk_familyfile", null);
			panel.setHeadItem("buildarea", null);
			panel.setHeadItem("innerarea", null);
			
			this.billForm.getBillCardPanel();
		}
		LoginContext context = this.billForm.getModel().getContext();
		// 进行参照过滤
		BillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(),
				context);
	}

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}

}
