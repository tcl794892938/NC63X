package nc.ui.zl.ly_hflist.ace.billref;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * 来源单据显示类
 * @author Liu
 *
 */
public class HflistRefZylistSourceRefDlg extends SourceRefDlg{
	
	public HflistRefZylistSourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	private static final long serialVersionUID = 1346878211293675730L;

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/zl/ly_hflist/ace/billref/HflistRefZylistInfo.xml";
	}

}
