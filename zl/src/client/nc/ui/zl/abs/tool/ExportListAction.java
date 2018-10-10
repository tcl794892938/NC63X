package nc.ui.zl.abs.tool;
/**
 * �б���
 */

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.StringUtils;

public class ExportListAction {

	private BillListPanel bp;

	private String name;
	
	private String code;

	public ExportListAction(BillListPanel bp,String code, String name) {
		super();
		this.bp = bp;
		this.name = name;
		this.code = code;
		doAction();
	}

	public void doAction() {

		
		BillItem[] bi = bp.getHeadBillModel().getBodyItems();

		ArrayList<Integer> showcol = new ArrayList<Integer>();
		ArrayList<String> showname = new ArrayList<String>();// ��ͷ�ֶ���
		showname.add("��֯����");
		for (int i = 0; i < bi.length; i++) {
			if (bi[i].isShow()) {
				showcol.add(i);
				showname.add(bi[i].getName());
			}
		}
		
		List<Object[]> list1 = new ArrayList<Object[]>();// ��ͷֵ
			
		for (int a = 0; a < bp.getHeadBillModel().getRowCount(); a++) {

			Object[] objb = new Object[showcol.size()+1];
			objb[0]=code;
			for (int j = 0; j < showcol.size(); j++) {

				objb[j+1] = bp.getHeadBillModel().getValueAt(a, showcol.get(j));

			}

			list1.add(objb);
		}

		doExport(showname, list1, null);
		
	}

	private void doExport(ArrayList<String> showname, List<Object[]> list,Container parent) {
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

			util = new IOUtils(path, false, true);
			// ����excel���������
			ExcelTools excelTools = new ExcelTools();
			// ����һ��sheet
			excelTools.createSheet("��������");
			// ����һ��
			excelTools.createRow(0);
			// �ж���ͷ�Ƿ�Ϊ��

			for (short i = 0; i < showname.size(); i++) {
				// ����һ����Ԫ��
				excelTools.createCell(i);
				// ��ֵд����Ԫ��
				excelTools.setValue(showname.get(i));
			}
			
			// ����Object����
			Object[] array = null;
			for (int i = 0; i < list.size(); i++) {
				// ����list
				array = (Object[]) list.get(i);
				if (null != array) {
					// ����һ��
					excelTools.createRow(i + 1);
					for (short j = 0; j < array.length; j++) {
						// ����һ����Ԫ��
						excelTools.createCell(j);
						// ��ֵд����Ԫ��
						excelTools.setValue(array[j]);
					}
				}
			}

			// ��excelд��������
			excelTools.writeExcel(util.getOutputStream());
			util.closeStream();
			MessageDialog.showHintDlg(bp, "��ʾ", "�����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �չ���
	}

}
