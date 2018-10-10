package nc.ui.zl.ly_businesssource.ace.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;
import nc.ui.zl.abs.tool.ExcelTools;
import nc.ui.zl.abs.tool.ExcelUtils;
import nc.ui.zl.abs.tool.IOUtils;

public class ExportAction extends NCAction {
	private static final long serialVersionUID = -9040886191995761338L;

	private ShowUpableBillForm bill;
	private TangramContainer tcr;

	public ExportAction() {
		super();
		this.setCode("export");
		this.setBtnName("����ģ��");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {

		BillCardPanel card=bill.getBillCardPanel();
		Map<Integer, String> map=new HashMap<Integer, String>();
		
		map.put(0, card.getHeadItem("pk_org").getName());
		//map.put(1, card.getHeadItem("sourceid").getName());
		map.put(1, card.getHeadItem("sourcename").getName());
		map.put(2, card.getHeadItem("customertype").getName());
		map.put(3, card.getHeadItem("phone").getName());
		map.put(4, card.getHeadItem("address").getName());
		map.put(5, card.getHeadItem("salesman").getName());
		map.put(6, card.getHeadItem("idnumber").getName());
		map.put(7, card.getHeadItem("businesslicense").getName());
		map.put(8, card.getBodyItem("procode").getName());
		map.put(9, card.getBodyItem("remarks").getName());
		
		String[] headColsCN = new String[] { map.get(0), map.get(1), map.get(2), map.get(3),
				map.get(4), map.get(5), map.get(6), map.get(7)};
		String[] headColsCN2=new String[] {"��ͷ�к�",map.get(8),map.get(9)};
		
		doExport("��Դ��Ϣ", null, headColsCN,headColsCN2, null);
	}

	public void doExport(String defaultFileName, List<Object[]> list,
			String[] headColsCN,String[] headColsCN2, Container parent) throws Exception {
		// �û�ѡ��·��
		String path = ExcelUtils.getChooseFilePath(parent, defaultFileName);
		// �жϴ�����ļ����Ƿ�Ϊ��
		if (StringUtils.isEmpty(path)) {
			// ���Ϊ�գ��Ͳ�����ִ����
			MessageDialog.showHintDlg(tcr, "��ʾ", "��ȡ������������ʧ�ܣ�");
			return;

		}
		// �жϴ�����ļ����Ƿ���.xls��β
		if (!path.endsWith(".xls")) {
			// ���������.xls��β���͸��ļ�����������.xls��չ��
			path = path + ".xls";

		}

		// ����һ�������
		IOUtils util = new IOUtils(path, false, true);
		// ����excel���������
		ExcelTools excelTools = new ExcelTools();
		// ����һ��sheet
		excelTools.createSheet("��������");
		//������ͷ�ֶ�
		excelTools.createRow(0);
		for (int i=0;i<headColsCN.length;i++) {
			short j=(short) i;
			excelTools.createCell(j);
			excelTools.setValue("��������", 0, j, headColsCN[i]);
		}
		
		//���������ֶ�
		excelTools.createRow(10);
		for (int i=0;i<headColsCN2.length;i++) {
			short j=(short) i;
			excelTools.createCell(j);
			excelTools.setValue("��������", 10, j, headColsCN2[i]);
		}

		// ��excelд��������
		excelTools.writeExcel(util.getOutputStream());
		// �չ���
		util.closeStream();
		MessageDialog.showHintDlg(tcr, "��ʾ", "�����ɹ���");

	}

	public TangramContainer getTcr() {
		return tcr;
	}

	public void setTcr(TangramContainer tcr) {
		this.tcr = tcr;
	}

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}
