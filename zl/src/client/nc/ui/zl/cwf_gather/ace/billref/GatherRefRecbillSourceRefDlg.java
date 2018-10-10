package nc.ui.zl.cwf_gather.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class GatherRefRecbillSourceRefDlg extends SourceRefDlg{
	
	private static final long serialVersionUID = 1346878211293675730L;

	public GatherRefRecbillSourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}
	
	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/cwf_gather/ace/billref/GatherRefRecbillInfo.xml";
	}

}
