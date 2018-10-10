package nc.ui.zl.lyw_report_confirmeditems.ace.actions;
/**
 * 卡片导出
 */

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.zl.abs.tool.ExportAction;


public class ExportAction2 extends NCAction{
	private static final long serialVersionUID = -1798441926036449420L;
	private BillManageModel model;
	private ShowUpableBillForm billForm;
	private String exportname;
	public ExportAction2(){
		this.setBtnName("导出");
		this.setCode("exportcode");
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		new ExportAction(billForm.getBillCardPanel().getBodyValueAt(0, "condition").toString(),billForm.getBillCardPanel(), exportname);
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	public String getExportname() {
		return exportname;
	}

	public void setExportname(String exportname) {
		this.exportname = exportname;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}
}
