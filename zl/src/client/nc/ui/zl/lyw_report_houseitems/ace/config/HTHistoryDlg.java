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
			this.blpanel.loadTemplet("1001ZZ100000000CBHB5");//房源详细查询
			blpanel.setEnabled(false);
		}
	}

	public void initDialog() {
		// 设置对话框主题
		this.setTitle("房源已收详细明细");
		// 设置最适合的大小
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(scrSize.width*2/3, scrSize.height*3/4));
		// 设置对话框位置，正中央
		this.setLocationRelativeTo(getParent());
		// 设置对话框布局
		this.setLayout(new BorderLayout());
		// 将panel加载到对话框中
		this.add(blpanel, BorderLayout.CENTER);
		// 设置关闭方式
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
