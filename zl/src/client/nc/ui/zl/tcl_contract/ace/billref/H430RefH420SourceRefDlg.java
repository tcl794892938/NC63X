package nc.ui.zl.tcl_contract.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class H430RefH420SourceRefDlg extends SourceRefDlg{
	
	private static final long serialVersionUID = 1346878211293675730L;

	public H430RefH420SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	
	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/tcl_contract/ace/billref/H430RefH420Info.xml";
	}

}
