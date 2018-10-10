package nc.ui.zl.ld_kpregister.ace.abspower;


import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;

import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByBuilding;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

@SuppressWarnings("restriction")
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
		this.setDefaultPk_org(dlgDelegator);
		
		String[] baseDoc=new String[]{"pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			FilterProjectByOrg baseDocByOrgFileter = 
					new FilterProjectByOrg(dlgDelegator,"pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		//当前项目下的车位区
		String[] baseDoc3=new String[]{"pk_kpregister_d.pk_building"};
		for(int i=0;i<baseDoc3.length;i++){
			FilterBuildingByProject baseDocByProjFileter = 
					new FilterBuildingByProject(dlgDelegator,"pk_project",baseDoc3[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前项目下的车位号
		String[] baseDoc4=new String[]{"pk_kpregister_d.pk_house"};
		for(int i=0;i<baseDoc4.length;i++){
			FilterHouseByBuilding baseDocByProjFileter = 
					new FilterHouseByBuilding(dlgDelegator,"pk_kpregister_d.pk_building",baseDoc4[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "pk_kpregister_d.pk_customer" };
		for (int i = 0; i < baseDoc5.length; i++) {
			FilterCustomerByProject baseDocByOrgFileter = new FilterCustomerByProject(
					dlgDelegator, "pk_project", baseDoc5[i]);
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
