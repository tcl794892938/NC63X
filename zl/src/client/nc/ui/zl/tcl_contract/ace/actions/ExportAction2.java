package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;
import nc.ui.zl.abs.tool.ExportListAction;
import nc.vo.zl.ld_housesource.HousesourceVO;
import nc.vo.zl.tcl_contract.ContractVO;

public class ExportAction2 extends NCAction{

	private ShowUpableBillListView listView;
	
	private BillManageModel model;
	public ExportAction2() {
		super();
		this.setCode("export");
		this.setBtnName("导出");
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		int a=listView.getBillListPanel().getHeadBillModel().getRowCount();
		if(a<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "界面无数据，不可导出！");
			return ;
		}

		ContractVO[] vos = (ContractVO[])listView.getBillListPanel().getHeadBillModel().getBodyValueVOs(ContractVO.class.getName());
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql = "select code from org_orgs where nvl(dr,0)=0 and pk_org='"+vos[0].getPk_org()+"' ";
		Object obj=iQ.executeQuery(sql, new ColumnProcessor());
		String code=obj==null?"":obj.toString();
		new ExportListAction(listView.getBillListPanel(),code, "合同管理导出");
	}

	public ShowUpableBillListView getListView() {
		return listView;
	}
	public void setListView(ShowUpableBillListView listView) {
		this.listView = listView;
	}

	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
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
