package nc.ui.zl.abs.tool;
/**
 * 卡片导出
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
		ArrayList<String> showbodyname = new ArrayList<String>();// 表体字段名
		for (int i = 0; i < bi.length; i++) {
			if (bi[i].isShow()) {
				showcol.add(i);
				showbodyname.add(bi[i].getName());
			}
		}
		List<Object[]> list1 = new ArrayList<Object[]>();// 表体值
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

			// 用户选择路径
			String path = ExcelUtils.getChooseFilePath(parent, name);

			// 判断传入的文件名是否为空
			if (StringUtils.isEmpty(path)) {
				// 如果为空，就不往下执行了
				MessageDialog.showHintDlg(bp, "提示", "已取消操作，导出失败！");
				return;

			}
			// 判断传入的文件名是否以.xls结尾
			if (!path.endsWith(".xls")) {
				// 如果不是以.xls结尾，就给文件名变量加上.xls扩展名
				path = path + ".xls";

			}
			// 构造一个输出流
			IOUtils util;

			try {
				util = new IOUtils(path, false, true);
			} catch (Exception e) {
				String error=e.toString().substring(e.toString().indexOf(":")+1, e.toString().length());
				MessageDialog.showErrorDlg(bp, "错误", error);
				return;
			}
			// 构造excel工具类对象
			ExcelTools excelTools = new ExcelTools();
			// 创建一个sheet
			excelTools.createSheet("导出数据");

			// 创建一行
			excelTools.createRow(0);
			excelTools.setRowHeight((short)400);
			excelTools.createCell((short)0);
			excelTools.setValue("导出数据过滤条件："+cond);
			excelTools.createRow(1);
			if (null != showbodyname) {
				excelTools.createRow(2);
				excelTools.setRowHeight((short)400);
				for (short i = 0; i < showbodyname.size(); i++) {
					// 创建一个单元格
					excelTools.createCell(i);
					// 将值写到单元格
					excelTools.setValue(showbodyname.get(i));

				}
				// 定义Object数组
				Object[] array = null;
				for (int i = 0; i < list.size(); i++) {
					// 迭代list
					array = (Object[]) list.get(i);
					if (null != array) {
						// 创建一行
						excelTools.createRow(i+3);
						excelTools.setRowHeight((short)400);
						for (short j = 0; j < array.length; j++) {
							// 创建一个单元格
							excelTools.createCell(j);
							// 将值写到单元格
							excelTools.setValue(array[j]);
						}
					}
				}
				// excelTools.setValue(4, (short) 0, "合计");
			}

			// 将excel写到磁盘上
			excelTools.writeExcel(util.getOutputStream());
			util.closeStream();
			MessageDialog.showHintDlg(bp, "提示", "导出成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 闭关流

	}

}
