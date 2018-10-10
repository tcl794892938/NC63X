package nc.ui.zl.abs.tool;
/**
 * 列表导出
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
		ArrayList<String> showname = new ArrayList<String>();// 表头字段名
		showname.add("组织编码");
		for (int i = 0; i < bi.length; i++) {
			if (bi[i].isShow()) {
				showcol.add(i);
				showname.add(bi[i].getName());
			}
		}
		
		List<Object[]> list1 = new ArrayList<Object[]>();// 表头值
			
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

			util = new IOUtils(path, false, true);
			// 构造excel工具类对象
			ExcelTools excelTools = new ExcelTools();
			// 创建一个sheet
			excelTools.createSheet("导出数据");
			// 创建一行
			excelTools.createRow(0);
			// 判断列头是否为空

			for (short i = 0; i < showname.size(); i++) {
				// 创建一个单元格
				excelTools.createCell(i);
				// 将值写到单元格
				excelTools.setValue(showname.get(i));
			}
			
			// 定义Object数组
			Object[] array = null;
			for (int i = 0; i < list.size(); i++) {
				// 迭代list
				array = (Object[]) list.get(i);
				if (null != array) {
					// 创建一行
					excelTools.createRow(i + 1);
					for (short j = 0; j < array.length; j++) {
						// 创建一个单元格
						excelTools.createCell(j);
						// 将值写到单元格
						excelTools.setValue(array[j]);
					}
				}
			}

			// 将excel写到磁盘上
			excelTools.writeExcel(util.getOutputStream());
			util.closeStream();
			MessageDialog.showHintDlg(bp, "提示", "导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 闭关流
	}

}
