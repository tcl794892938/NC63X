package nc.ui.zl.ld_carcontract.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
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
			this.card.loadTemplet("1001ZZ100000000C5DEH");
			card.setEnabled(false);
		}
	}

	private void initDialog() {
		//dlg=this;
		// 设置对话框主题
		this.setTitle("原始合同信息");
		// 设置最适合的大小
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(scrSize.width*2/3, scrSize.height*3/4));
		// 设置对话框位置，正中央
		this.setLocationRelativeTo(getParent());
		// 设置对话框布局
		this.setLayout(new BorderLayout());
	
		// 将panel加载到对话框中
		this.add(card, BorderLayout.CENTER);
		// 设置关闭方式
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	public void initValue(AggCarcontractVO aggvo){
		card.setBillValueVO(aggvo);
		this.showModal();
	}
	
	
}
