package nc.ui.zl.ld_report_cwdy.ace.actions;


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
import nc.ui.zl.abs.power.MusFilterCostpByOrg;
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
		
		String[] baseDoc=new String[]{"report_cwdy.pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg baseDocByOrgFileter = 
					new MusFilterProjectByOrg(dlgDelegator,"report_cwdy.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		
		
		
		//当前组织下的收费项目
		String[] baseDoc6=new String[]{"report_cwdy.pk_costproject"};
		for(int i=0;i<baseDoc6.length;i++){
			MusFilterCostpByOrg baseDocByOrgFileter = 
					new MusFilterCostpByOrg(dlgDelegator,"report_cwdy.pk_org",baseDoc6[i]); 
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
