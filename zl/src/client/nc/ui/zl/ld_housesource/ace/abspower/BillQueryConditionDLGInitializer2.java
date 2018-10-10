package nc.ui.zl.ld_housesource.ace.abspower;


import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterFamilyfileByProject;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

@SuppressWarnings("restriction")
public class BillQueryConditionDLGInitializer2 implements
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
		
		String[] baseDoc=new String[]{"projectname"};
		for(int i=0;i<baseDoc.length;i++){
			FilterProjectByOrg baseDocByOrgFileter = 
					new FilterProjectByOrg(dlgDelegator,"pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc2=new String[]{"buildname"};
		for(int i=0;i<baseDoc2.length;i++){
			FilterBuildingByProject baseDocByProjFileter = 
					new FilterBuildingByProject(dlgDelegator,"projectname",baseDoc2[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		
		String[] baseDoc3=new String[]{"pk_familyfile"};
		for(int i=0;i<baseDoc3.length;i++){
			FilterFamilyfileByProject baseDocByProjFileter = 
					new FilterFamilyfileByProject(dlgDelegator,"projectname",baseDoc3[i]); 
			baseDocByProjFileter.addEditorListener();
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
