package nc.ui.zl.lyw_report_customeritems.ace.abspower;


import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.MusFilterCustomerByProject;
import nc.ui.zl.abs.power.MusFilterProjectByOrg2;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class BillQueryConditionDLGInitializer implements
		IQueryConditionDLGInitializer {

	private BillManageModel model;

	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	@Override
	public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		
		dlgDelegator.registerNeedPermissionOrgFieldCode("pk_org");//注册主组织过滤
		dlgDelegator.getDealEnumTypeRef();
		this.setDefaultPk_org(dlgDelegator);
		//当前组织下的项目
		String[] baseDoc=new String[]{"customeritems.pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg2 baseDocByOrgFileter = 
					new MusFilterProjectByOrg2(dlgDelegator,"customeritems.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "customeritems.pk_customer"};
		for (int i = 0; i < baseDoc5.length; i++) {
			MusFilterCustomerByProject baseDocByOrgFileter = new MusFilterCustomerByProject(
					dlgDelegator, "customeritems.pk_project", baseDoc5[i]);
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
