package nc.ui.zl.cwf_gather.ace.billref;

import java.awt.Container;

import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterCostpByOrg;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByBuilding;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

public class GatherRefRecbillReferQuery extends DefaultBillReferQuery{
	
	public GatherRefRecbillReferQuery(Container c, TemplateInfo info) {
		super(c, info);
	}

	@Override
	protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {dlgDelegator.registerNeedPermissionOrgFieldCode("pk_org");//注册主组织过滤
	dlgDelegator.getDealEnumTypeRef();
	String[] baseDoc=new String[]{"pk_project"};
	this.setDefaultPk_org(dlgDelegator);
	for(int i=0;i<baseDoc.length;i++){
		FilterProjectByOrg baseDocByOrgFileter = 
				new FilterProjectByOrg(dlgDelegator,"pk_org",baseDoc[i]); 
		baseDocByOrgFileter.addEditorListener();
	}
	
	String[] baseDoc2=new String[]{"pk_building"};
	for(int i=0;i<baseDoc2.length;i++){
		FilterBuildingByProject baseDocByOrgFileter = 
				new FilterBuildingByProject(dlgDelegator,"pk_project",baseDoc2[i]); 
		baseDocByOrgFileter.addEditorListener();
	}
	
	String[] baseDoc3=new String[]{"pk_customer"};
	for(int i=0;i<baseDoc3.length;i++){
		FilterCustomerByProject baseDocByOrgFileter = 
				new FilterCustomerByProject(dlgDelegator,"pk_project",baseDoc3[i]); 
		baseDocByOrgFileter.addEditorListener();
	}
	
	String[] baseDoc4=new String[]{"pk_house"};
	for(int i=0;i<baseDoc4.length;i++){
		FilterHouseByBuilding baseDocByOrgFileter = 
				new FilterHouseByBuilding(dlgDelegator,"pk_building",baseDoc4[i]); 
		baseDocByOrgFileter.addEditorListener();
	}
	
	String[] baseDoc5=new String[]{"pk_costproject"};
	for(int i=0;i<baseDoc5.length;i++){
		FilterCostpByOrg baseDocByOrgFileter = 
				new FilterCostpByOrg(dlgDelegator,"pk_org",baseDoc5[i]); 
		baseDocByOrgFileter.addEditorListener();
	}}
	
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
		
		//dlgDelegator.setPowerEnable(true);
	}
	
}
