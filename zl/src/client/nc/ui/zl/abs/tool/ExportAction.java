package nc.ui.zl.abs.tool;
/**
 * ��Ƭ����
 */

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;

import org.apache.commons.lang.StringUtils;

public class ExportAction {

	private BillCardPanel bp;

	private String name;
	
	private String cond;

	public ExportAction(String cond,BillCardPanel bp, String name) {
		super();
		this.cond=cond;
		this.bp = bp;
		this.name = name;
		doAction();
	}

	public void doAction() {

		BillItem[] bi = bp.getBillModel().getBodyItems();

		ArrayList<Integer> showcol = new ArrayList<Integer>();
		ArrayList<String> showbodyname = new ArrayList<String>();// �����ֶ���
		for (int i = 0; i < bi.length; i++) {
			if (bi[i].isShow()) {
				showcol.add(i);
				showbodyname.add(bi[i].getName());
			}
		}
		List<Object[]> list1 = new ArrayList<Object[]>();// ����ֵ
		for (int a = 0; a < bp.getBillModel().getRowCount(); a++) {

			Object[] objb = new Object[showcol.size()];

			for (int j = 0; j < showcol.size(); j++) {

				objb[j] = bp.getBillModel().getValueAt(a, showcol.get(j));

			}

			list1.add(objb);
		}

		doExport(this.cond,showbodyname, list1, null);
	}

	private void doExport(String cond,ArrayList<String> showbodyname, List<Object[]> list,
			Container parent) {
		try {

			// �û�ѡ��·��
			String path = ExcelUtils.getChooseFilePath(parent, name);

			// �жϴ�����ļ����Ƿ�Ϊ��
			if (StringUtils.isEmpty(path)) {
				// ���Ϊ�գ��Ͳ�����ִ����
				MessageDialog.showHintDlg(bp, "��ʾ", "��ȡ������������ʧ�ܣ�");
				return;

			}
			// �жϴ�����ļ����Ƿ���.xls��β
			if (!path.endsWith(".xls")) {
				// ���������.xls��β���͸��ļ�����������.xls��չ��
				path = path + ".xls";

			}
			// ����һ�������
			IOUtils util;

			try {
				util = new IOUtils(path, false, true);
			} catch (Exception e) {
				String error=e.toString().substring(e.toString().indexOf(":")+1, e.toString().length());
				MessageDialog.showErrorDlg(bp, "����", error);
				return;
			}
			// ����excel���������
			ExcelTools excelTools = new ExcelTools();
			// ����һ��sheet
			excelTools.createSheet("��������");

			// ����һ��
			excelTools.createRow(0);
			excelTools.setRowHeight((short)400);
			excelTools.createCell((short)0);
			excelTools.setValue("�������ݹ���������"+cond);
			excelTools.createRow(1);
			if (null != showbodyname) {
				excelTools.createRow(2);
				excelTools.setRowHeight((short)400);
				for (short i = 0; i < showbodyname.size(); i++) {
					// ����һ����Ԫ��
					excelTools.createCell(i);
					// ��ֵд����Ԫ��
					excelTools.setValue(showbodyname.get(i));

				}
				// ����Object����
				Object[] array = null;
				for (int i = 0; i < list.size(); i++) {
					// ����list
					array = (Object[]) list.get(i);
					if (null != array) {
						// ����һ��
						excelTools.createRow(i+3);
						excelTools.setRowHeight((short)400);
						for (short j = 0; j < array.length; j++) {
							// ����һ����Ԫ��
							excelTools.createCell(j);
							// ��ֵд����Ԫ��
							excelTools.setValue(array[j]);
						}
					}
				}
				// excelTools.setValue(4, (short) 0, "�ϼ�");
			}

			// ��excelд��������
			excelTools.writeExcel(util.getOutputStream());
			util.closeStream();
			MessageDialog.showHintDlg(bp, "��ʾ", "�����ɹ���");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �չ���

	}

}
