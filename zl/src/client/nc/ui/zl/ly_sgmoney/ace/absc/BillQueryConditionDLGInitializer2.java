package nc.ui.zl.ly_sgmoney.ace.absc;

import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.FilterCostpByOrg;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByCustomer;
import nc.ui.zl.abs.power.FilterProjectByOrg;
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
		this.setDefaultPk_org(dlgDelegator);
		
		String[] baseDoc = new String[] { "pk_project" };
		for (int i = 0; i < baseDoc.length; i++) {
			FilterProjectByOrg baseDocByOrgFileter = new FilterProjectByOrg(
					dlgDelegator, "pk_org", baseDoc[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc1 = new String[] { "payproject" };
		for (int i = 0; i < baseDoc1.length; i++) {
			FilterCostpByOrg baseDocByOrgFileter = new FilterCostpByOrg(
					dlgDelegator, "pk_org", baseDoc1[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc2 = new String[] { "khname" };
		for (int i = 0; i < baseDoc2.length; i++) {
			FilterCustomerByProject baseDocByOrgFileter = new FilterCustomerByProject(
					dlgDelegator, "pk_project", baseDoc2[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc3=new String[]{"fcname"};
		for(int i=0;i<baseDoc3.length;i++){
			FilterHouseByCustomer baseDocByOrgFileter = 
					new FilterHouseByCustomer(dlgDelegator,"khname",baseDoc3[i]); 
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
