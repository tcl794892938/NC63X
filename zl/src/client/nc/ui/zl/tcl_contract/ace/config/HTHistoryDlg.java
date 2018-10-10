package nc.ui.zl.tcl_contract.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.zl.tcl_contract.AggContractVO;

public class HTHistoryDlg extends UIDialog {

	private static final long serialVersionUID = -9152484574365793689L;

	public HTHistoryDlg(Container parent) {
		super(parent);
		initialize();
		initDialog();
	}

	private BillCardPanel card;
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("0001ZZ1000000001SLCS");
			card.setEnabled(false);
		}
	}

	private void initDialog() {
		//dlg=this;
		// ���öԻ�������
		this.setTitle("ԭʼ��ͬ��Ϣ");
		// �������ʺϵĴ�С
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(scrSize.width*2/3, scrSize.height*3/4));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
	
		// ��panel���ص��Ի�����
		this.add(card, BorderLayout.CENTER);
		// ���ùرշ�ʽ
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	public void initValue(AggContractVO aggvo){
		card.setBillValueVO(aggvo);
		this.showModal();
	}
	
	
}
