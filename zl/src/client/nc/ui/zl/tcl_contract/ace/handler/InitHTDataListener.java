package nc.ui.zl.tcl_contract.ace.handler;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends DefaultFuncNodeInitDataListener {

	
	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;
	@Override
	public void initData(FuncletInitData data) {

		String[] tabcode=new String[]{"pk_contract_cust","pk_contract_house","pk_contract_mzq",
				"pk_contract_zzq","pk_contract_bzj","pk_contract_ywcf","pk_contract_cwcf",
				"pk_contract_zqfy","pk_contract_zqfycf"};
		for(String tab : tabcode){
			billForm.getBillCardPanel().getBillTable(tab).removeSortListener();
			listView.getBillListPanel().getBodyTable(tab).removeSortListener();
			billForm.getBillCardPanel().setBodyAutoAddLine(tab, false);
			billForm.getBillCardPanel().getBodyPanel(tab).setBBodyMenuShow(false);
		}
		if (null == data) {
			this.getModel().initModel(null);
			return;
		}
		
		if (ILinkType.LINK_TYPE_QUERY == data.getInitType()) {

			Object userObj = null;
			List<String> pks = new ArrayList<String>();

			Object initData = data.getInitData();
			if (initData instanceof ILinkQueryData) {
				userObj = ((ILinkQueryData) initData).getUserObject();
				pks.add(((ILinkQueryData) initData).getBillID());
			} else if (initData instanceof ILinkQueryData[]) {
				ILinkQueryData[] datas = (ILinkQueryData[]) initData;
				userObj = ((ILinkQueryData[]) initData)[0].getUserObject();
				for (ILinkQueryData linkQueryData : datas) {
					pks.add(linkQueryData.getBillID());
				}
			}

			if (userObj != null && userObj.getClass().isArray()) {
				
				this.getModel().initModel(userObj);
				//联查结果是单个时，直接显示卡片界面
				if (((Object[])userObj).length==1&&null != getBillForm()) {
					getBillForm().showMeUp();
				}else if(null!=getListView()){
					getListView().showMeUp();
				}
				return;
			}
		}
		
		super.initData(data);
	}
	
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}


	public ShowUpableBillListView getListView() {
		return listView;
	}


	public void setListView(ShowUpableBillListView listView) {
		this.listView = listView;
	}
	
	

}
