package nc.ui.zl.ld_housesource.ace.link;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitDataListener extends DefaultFuncNodeInitDataListener {
	private ShowUpableBillForm cardForm;
	private ShowUpableBillListView listView;

	@Override
	public void initData(FuncletInitData data) {
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
				if (((Object[])userObj).length==1&&null != getCardForm()) {
					getCardForm().showMeUp();
				}else if(null!=getListView()){
					getListView().showMeUp();
				}
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
