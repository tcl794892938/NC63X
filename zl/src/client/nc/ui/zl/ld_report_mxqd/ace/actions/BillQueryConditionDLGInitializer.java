package nc.ui.zl.ld_report_mxqd.ace.actions;


import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;

import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterContracttypeByOrg;
import nc.ui.zl.abs.power.FilterCostpByOrg;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByBuilding;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.ui.zl.abs.power.MusFilterBuildingByProject;
import nc.ui.zl.abs.power.MusFilterCostpByOrg;
import nc.ui.zl.abs.power.MusFilterCustomerByProject;
import nc.ui.zl.abs.power.MusFilterHouseByBuilding;
import nc.ui.zl.abs.power.MusFilterProjectByOrg;

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
		
		String[] baseDoc=new String[]{"report_mxqd.pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg baseDocByOrgFileter = 
					new MusFilterProjectByOrg(dlgDelegator,"report_mxqd.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的楼栋report_mxqd.pk_house
		String[] baseDoc2=new String[]{"report_mxqd.buildname"};
		for(int i=0;i<baseDoc2.length;i++){
			MusFilterBuildingByProject baseDocByProjFileter = 
					new MusFilterBuildingByProject(dlgDelegator,"report_mxqd.pk_project",baseDoc2[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前楼栋下的房产
		String[] baseDoc4 = new String[] { "report_mxqd.pk_house" };
		for (int i = 0; i < baseDoc4.length; i++) {
			MusFilterHouseByBuilding baseDocByOrgFileter = new MusFilterHouseByBuilding(
					dlgDelegator, "report_mxqd.buildname", baseDoc4[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "report_mxqd.pk_customer" };
		for (int i = 0; i < baseDoc5.length; i++) {
			MusFilterCustomerByProject baseDocByOrgFileter = new MusFilterCustomerByProject(
					dlgDelegator, "report_mxqd.pk_project", baseDoc5[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		
		//当前组织下的收费项目
		String[] baseDoc6=new String[]{"report_mxqd.pk_costproject"};
		for(int i=0;i<baseDoc6.length;i++){
			MusFilterCostpByOrg baseDocByOrgFileter = 
					new MusFilterCostpByOrg(dlgDelegator,"report_mxqd.pk_org",baseDoc6[i]); 
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
