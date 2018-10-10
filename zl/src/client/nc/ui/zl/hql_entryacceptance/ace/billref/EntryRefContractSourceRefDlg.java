package nc.ui.zl.hql_entryacceptance.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class EntryRefContractSourceRefDlg extends SourceRefDlg {
	private static final long serialVersionUID = 1346878211293675730L;

	public EntryRefContractSourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	
	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/hql_entryacceptance/ace/billref/EntryRefContractInfo.xml";
	}
}
