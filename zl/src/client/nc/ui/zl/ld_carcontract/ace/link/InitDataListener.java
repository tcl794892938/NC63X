package nc.ui.zl.ld_carcontract.ace.link;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.linkoperate.ILinkAddData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.UIState;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;

public class InitDataListener extends DefaultFuncNodeInitDataListener {
	
	private ShowUpableBillForm cardForm;
	private ShowUpableBillListView listView;

	@Override
	public void initData(FuncletInitData data) {
		String[] tabcode=new String[]{"pk_carcontract_b","pk_carcontract_c","pk_carcontract_f"};
		for(String tab : tabcode){
			cardForm.getBillCardPanel().getBillTable(tab).removeSortListener();
			listView.getBillListPanel().getBodyTable(tab).removeSortListener();
			cardForm.getBillCardPanel().setBodyAutoAddLine(tab, false);
			cardForm.getBillCardPanel().getBodyPanel(tab).setBBodyMenuShow(false);
		}
		if (null == data) {
			this.getModel().initModel(null);
			return;
		}
		
		if (ILinkType.LINK_TYPE_ADD == data.getInitType()) {

			Object userObj = null;
			List<String> pks = new ArrayList<String>();

			Object initData = data.getInitData();
			if (initData instanceof ILinkAddData) {
				userObj = ((ILinkAddData) initData).getUserObject();
				pks.add(((ILinkAddData) initData).getSourceBillID());
			} else if (initData instanceof ILinkAddData[]) {
				ILinkAddData[] datas = (ILinkAddData[]) initData;
				userObj = ((ILinkAddData[]) initData)[0].getUserObject();
				for (ILinkAddData linkQueryData : datas) {
					pks.add(linkQueryData.getSourceBillID());
				}
			}

			if (userObj != null && !userObj.getClass().isArray()) {
				
				//getCardForm().setValue(userObj);
				//super.initData(data);
				//联查结果是单个时，直接显示卡片界面
				getCardForm().showMeUp();
				getCardForm().getModel().setUiState(UIState.ADD);
				this.getModel().initModel(userObj);
				getCardForm().setEditable(true);
				return;
			}
		}
		super.initData(data);
	}
	
	public ShowUpableBillForm getCardForm() {
		return cardForm;
	}

	public void setCardForm(ShowUpableBillForm cardForm) {
		this.cardForm = cardForm;
	}
	public ShowUpableBillListView getListView() {
		return listView;
	}

	public void setListView(ShowUpableBillListView listView) {
		this.listView = listView;
	}
}
