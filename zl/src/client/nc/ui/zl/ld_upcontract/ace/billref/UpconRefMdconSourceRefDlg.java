package nc.ui.zl.ld_upcontract.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class UpconRefMdconSourceRefDlg extends SourceRefDlg{

	private static final long serialVersionUID = 1346878211293675730L;

	public UpconRefMdconSourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/ld_upcontract/ace/billref/UpconRefMdconInfo.xml";
	}
}
