package nc.ui.zl.tcl_contract.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;

public class HTZqdetailDlg extends UIDialog {

	

	
	private static final long serialVersionUID = -3547603492864507708L;

	public HTZqdetailDlg(Container parent) {
		super(parent);
		initialize();
		initDialog();
	}

	private BillCardPanel card;
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ100000000CBGIC");
			card.setEnabled(false);
		}
	}

	private void initDialog() {
		//dlg=this;
		// 设置对话框主题
		this.setTitle("周期明细");
		// 设置最适合的大小
		//Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(900,600));
		// 设置对话框位置，正中央
		this.setLocationRelativeTo(getParent());
		// 设置对话框布局
		this.setLayout(new BorderLayout());
	
		// 将panel加载到对话框中
		this.add(card, BorderLayout.CENTER);
		// 设置关闭方式
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	public void initValue(List<Map<String, Object>> maplist){
		
		BillModel model=card.getBillModel();
		model.clearBodyData();
		for(Map<String, Object> map:maplist){
			model.addLine();
			for(String key:map.keySet()){
				model.setValueAt(map.get(key), model.getRowCount()-1, key);
			}
		}
		model.loadLoadRelationItemValue();
		this.showModal();
	}
	
	
}
