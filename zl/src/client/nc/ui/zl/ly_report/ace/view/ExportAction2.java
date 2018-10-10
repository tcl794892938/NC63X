
package nc.ui.zl.ly_report.ace.view;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.abs.tool.ExportAction;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractUIAppModel;


public class ExportAction2 extends NCAction {

	/**
	 * 报表界面数据导出
	 */
	private static final long serialVersionUID = -276196059813518429L;

	private ShowUpableBillForm billForm;
	  
	private AbstractUIAppModel model;
	  
	private String exportname;
	
	public ExportAction2(){
		  this.setBtnName("导出");
		  this.setCode("exportcode");
	  }
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		int a=billForm.getBillCardPanel().getBillModel().getRowCount();
		if(a<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "界面无数据，不可导出！");
			return ;
		}

		new ExportAction(billForm.getBillCardPanel().getBodyValueAt(0, "condition").toString(),billForm.getBillCardPanel(), exportname);
		
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public String getExportname() {
		return exportname;
	}

	public void setExportname(String exportname) {
		this.exportname = exportname;
	}

}
