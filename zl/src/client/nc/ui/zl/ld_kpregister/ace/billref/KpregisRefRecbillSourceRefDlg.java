package nc.ui.zl.ld_kpregister.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class KpregisRefRecbillSourceRefDlg extends SourceRefDlg{

	public KpregisRefRecbillSourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/ld_kpregister/ace/billref/KpregisRefRecbillInfo.xml";
	}
}
