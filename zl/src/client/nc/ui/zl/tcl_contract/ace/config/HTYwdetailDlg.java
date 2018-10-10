package nc.ui.zl.tcl_contract.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;

public class HTYwdetailDlg extends UIDialog {

	private static final long serialVersionUID = -9152484574365793689L;

	public HTYwdetailDlg(Container parent) {
		super(parent);
		initialize();
		initDialog();
	}

	private BillCardPanel card;
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ100000000C8AB8");
			card.setEnabled(false);
		}
	}

	private void initDialog() {
		//dlg=this;
		// ���öԻ�������
		this.setTitle("ҵ����ϸ");
		// �������ʺϵĴ�С
		//Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(900,600));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
	
		// ��panel���ص��Ի�����
		this.add(card, BorderLayout.CENTER);
		// ���ùرշ�ʽ
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
