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
		this.setBtnName("导出模板");
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
		String[] headColsCN2=new String[] {"表头行号",map.get(8),map.get(9)};
		
		doExport("商源信息", null, headColsCN,headColsCN2, null);
	}

	public void doExport(String defaultFileName, List<Object[]> list,
			String[] headColsCN,String[] headColsCN2, Container parent) throws Exception {
		// 用户选择路径
		String path = ExcelUtils.getChooseFilePath(parent, defaultFileName);
		// 判断传入的文件名是否为空
		if (StringUtils.isEmpty(path)) {
			// 如果为空，就不往下执行了
			MessageDialog.showHintDlg(tcr, "提示", "已取消操作，导出失败！");
			return;

		}
		// 判断传入的文件名是否以.xls结尾
		if (!path.endsWith(".xls")) {
			// 如果不是以.xls结尾，就给文件名变量加上.xls扩展名
			path = path + ".xls";

		}

		// 构造一个输出流
		IOUtils util = new IOUtils(path, false, true);
		// 构造excel工具类对象
		ExcelTools excelTools = new ExcelTools();
		// 创建一个sheet
		excelTools.createSheet("导出数据");
		//创建表头字段
		excelTools.createRow(0);
		for (int i=0;i<headColsCN.length;i++) {
			short j=(short) i;
			excelTools.createCell(j);
			excelTools.setValue("导出数据", 0, j, headColsCN[i]);
		}
		
		//创建表体字段
		excelTools.createRow(10);
		for (int i=0;i<headColsCN2.length;i++) {
			short j=(short) i;
			excelTools.createCell(j);
			excelTools.setValue("导出数据", 10, j, headColsCN2[i]);
		}

		// 将excel写到磁盘上
		excelTools.writeExcel(util.getOutputStream());
		// 闭关流
		util.closeStream();
		MessageDialog.showHintDlg(tcr, "提示", "导出成功！");

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
