package nc.ui.zl.lyw_report_houseitems.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class HTHistoryDlg extends UIDialog {
	private static final long serialVersionUID = 5660990824923830671L;
	private BillListPanel blpanel;
	CircularlyAccessibleValueObject a;
	public HTHistoryDlg(Container parent) {
		super(parent);
		initialize();
		initDialog();
	}

	public void initialize() {
		if (null == this.blpanel) {
			this.blpanel = new BillListPanel();
			this.blpanel.loadTemplet("1001ZZ100000000CBHB5");//��Դ��ϸ��ѯ
			blpanel.setEnabled(false);
		}
	}

	public void initDialog() {
		// ���öԻ�������
		this.setTitle("��Դ������ϸ��ϸ");
		// �������ʺϵĴ�С
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(scrSize.width*2/3, scrSize.height*3/4));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
		// ��panel���ص��Ի�����
		this.add(blpanel, BorderLayout.CENTER);
		// ���ùرշ�ʽ
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	public void initValue(List<Map<String,Object>> gather){
		int row=0;
		for(int i=0;i<gather.size();i++){
			Iterator<String> iter = gather.get(i).keySet().iterator();
			blpanel.getBodyBillModel().addLine();
			while(iter.hasNext()){
				String key=iter.next();
				Object value=gather.get(i).get(key);
				blpanel.getBodyBillModel().setValueAt(value,row,key);
			}
			row++;
		}
		blpanel.getBodyBillModel().execLoadFormula();
		this.showModal();
	}
}
