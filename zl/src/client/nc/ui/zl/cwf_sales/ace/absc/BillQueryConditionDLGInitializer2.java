package nc.ui.zl.cwf_sales.ace.absc;

import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class BillQueryConditionDLGInitializer2 implements IQueryConditionDLGInitializer {
	private BillManageModel model;

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	@Override
	public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {

		dlgDelegator.registerNeedPermissionOrgFieldCode("pk_org");// 注册主组织过滤
		dlgDelegator.getDealEnumTypeRef();
		String[] baseDoc = new String[] { "username" };
		this.setDefaultPk_org(dlgDelegator);
		for (int i = 0; i < baseDoc.length; i++) {
			FilterPsndocByOrg baseDocByOrgFileter = new FilterPsndocByOrg(
					dlgDelegator, "pk_org", baseDoc[i]);
			baseDocByOrgFileter.addEditorListener();
		}
	}

	private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {

		String defaultOrg = null;
		try {
			String pk_org = OrgSettingAccessor.getDefaultOrgUnit();
			defaultOrg = pk_org;
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);

		}
		if (defaultOrg != null && defaultOrg.trim().length() > 0) {
			dlgDelegator.setDefaultValue("pk_org", defaultOrg);
		}
	}
}
