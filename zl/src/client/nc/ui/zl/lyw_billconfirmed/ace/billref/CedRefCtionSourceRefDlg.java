package nc.ui.zl.lyw_billconfirmed.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class CedRefCtionSourceRefDlg extends SourceRefDlg {
	private static final long serialVersionUID = 1346878211293675730L;

	public CedRefCtionSourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	
	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/lyw_billconfirmed/ace/billref/CedRefCtionInfo.xml";
	}
}
