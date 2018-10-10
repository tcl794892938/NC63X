package nc.ui.zl.ld_housesource.ace.actions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.tool.ExcelTools;
import nc.ui.zl.abs.tool.ExcelUtils;
import nc.ui.zl.abs.tool.ExportListAction;
import nc.ui.zl.abs.tool.IOUtils;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class ExportAction extends NCAction {
	private static final long serialVersionUID = -9040886191995761338L;
	
	private ShowUpableBillListView listView;
	
	private BillManageModel model;
	
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}
	private TangramContainer tcr;
	
	public ExportAction() {
		super();
		this.setCode("export");
		this.setBtnName("导出");
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		int a=listView.getBillListPanel().getHeadBillModel().getRowCount();
		if(a<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "界面无数据，不可导出！");
			return ;
		}
		
		HousesourceVO[] vos = (HousesourceVO[])listView.getBillListPanel().getHeadBillModel().getBodyValueVOs(HousesourceVO.class.getName());
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql = "select code from org_orgs where nvl(dr,0)=0 and pk_org='"+vos[0].getPk_org()+"' ";
		Object obj=iQ.executeQuery(sql, new ColumnProcessor());
		String code=obj==null?"":obj.toString();
		new ExportListAction(listView.getBillListPanel(),code, "房源信息导出");

	}
	
	
	public ShowUpableBillListView getListView() {
		return listView;
	}
	public void setListView(ShowUpableBillListView listView) {
		this.listView = listView;
	}

	public TangramContainer getTcr() {
		return tcr;
	}
	public void setTcr(TangramContainer tcr) {
		this.tcr = tcr;
	}
	@Override
	protected boolean isActionEnable() {
		
		int a=getModel().getRowCount();
		if(a<=0){
			return false;
		}
		return true;
	}

}
