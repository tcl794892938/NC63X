package nc.ui.zl.lyw_report_houseitems.ace.abspower;


import nc.desktop.ui.WorkbenchEnvironment;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterCostpByOrg;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByBuilding;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.ui.zl.abs.power.MusFilterBuildingByProject;
import nc.ui.zl.abs.power.MusFilterCostpByOrg;
import nc.ui.zl.abs.power.MusFilterCustomerByProject;
import nc.ui.zl.abs.power.MusFilterHouseByBuilding;
import nc.ui.zl.abs.power.MusFilterHouseByCustomer;
import nc.ui.zl.abs.power.MusFilterProjectByOrg2;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uap.rbac.profile.FunctionPermProfileManager;

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
		
		String[]pks=FunctionPermProfileManager
		        .getInstance()
		        .getProfile(
		            WorkbenchEnvironment.getInstance().getLoginUser().getUser_code()).getFuncSubInfo("ZLH420").getFuncPermissionPkorgs();
				dlgDelegator.registerNeedPermissionOrgFieldCode("houseitems.pk_org",pks);//注册主组织过滤
		dlgDelegator.getDealEnumTypeRef();
		this.setDefaultPk_org(dlgDelegator);
		//当前组织下的项目
		String[] baseDoc=new String[]{"houseitems.pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg2 baseDocByOrgFileter = 
					new MusFilterProjectByOrg2(dlgDelegator,"houseitems.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "houseitems.pk_customer"};
		for (int i = 0; i < baseDoc5.length; i++) {
			MusFilterCustomerByProject baseDocByOrgFileter = new MusFilterCustomerByProject(
					dlgDelegator, "houseitems.pk_project", baseDoc5[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的楼栋
		String[] baseDoc2=new String[]{"houseitems.pk_buildno"};
		for(int i=0;i<baseDoc2.length;i++){
			MusFilterBuildingByProject baseDocByProjFileter = 
					new MusFilterBuildingByProject(dlgDelegator,"houseitems.pk_project",baseDoc2[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前楼栋下的房源
		String[] baseDoc3 = new String[] { "houseitems.pk_house" };
		for (int i = 0; i < baseDoc3.length; i++) {
			MusFilterHouseByBuilding baseDocByOrgFileter = new MusFilterHouseByBuilding(
					dlgDelegator, "houseitems.pk_buildno", baseDoc3[i]);
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
