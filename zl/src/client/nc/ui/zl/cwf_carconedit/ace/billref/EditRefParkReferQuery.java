package nc.ui.zl.cwf_carconedit.ace.billref;

import java.awt.Container;

import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterCostpByOrg;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByBuilding;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.ui.zl.ld_parkcontract.ace.abspower.FilterCarByProject;
import nc.ui.zl.ld_parkcontract.ace.abspower.FilterFeescaleByCostp;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

public class EditRefParkReferQuery extends DefaultBillReferQuery{
	
	public EditRefParkReferQuery(Container c, TemplateInfo info) {
		super(c, info);
	}

	@Override
	protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		
		dlgDelegator.registerNeedPermissionOrgFieldCode("pk_org");//注册主组织过滤
		this.setDefaultPk_org(dlgDelegator);
		
		String[] baseDoc=new String[]{"pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			FilterProjectByOrg baseDocByOrgFileter = 
					new FilterProjectByOrg(dlgDelegator,"pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的车辆
		String[] baseDoc2=new String[]{"pk_parkcontract_b.platenum"};
		for(int i=0;i<baseDoc2.length;i++){
			FilterCarByProject baseDocByProjFileter = 
					new FilterCarByProject(dlgDelegator,"pk_project",baseDoc2[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前项目下的车位区
		String[] baseDoc3=new String[]{"pk_parkcontract_b.parkarea"};
		for(int i=0;i<baseDoc3.length;i++){
			FilterBuildingByProject baseDocByProjFileter = 
					new FilterBuildingByProject(dlgDelegator,"pk_project",baseDoc3[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前项目下的车位号
		String[] baseDoc4=new String[]{"pk_parkcontract_b.parknum"};
		for(int i=0;i<baseDoc4.length;i++){
			FilterHouseByBuilding baseDocByProjFileter = 
					new FilterHouseByBuilding(dlgDelegator,"pk_parkcontract_b.parkarea",baseDoc4[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "pk_customer" };
		for (int i = 0; i < baseDoc5.length; i++) {
			FilterCustomerByProject baseDocByOrgFileter = new FilterCustomerByProject(
					dlgDelegator, "pk_project", baseDoc5[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前组织下的收费项目
		String[] baseDoc6 = new String[] { "pk_costproject" };
		for (int i = 0; i < baseDoc6.length; i++) {
			FilterCostpByOrg baseDocByOrgFileter = new FilterCostpByOrg(
					dlgDelegator, "pk_org", baseDoc6[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前收费项目下的收费标准
		String[] baseDoc7 = new String[] { "pk_feescale" };
		for (int i = 0; i < baseDoc7.length; i++) {
			FilterFeescaleByCostp baseDocByOrgFileter = new FilterFeescaleByCostp(
					dlgDelegator, "pk_costproject", baseDoc7[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		 // 按主组织过滤计划部门
//	    QDeptFilter deptFilter =
//	        new QDeptFilter(dlgDelegator, "pk_deptdoc");
//	    deptFilter.setForceMultiCorpRef(UFBoolean.FALSE);
//	    deptFilter.addEditorListener();


	    // 按主组织过滤计划员
//	    QPsndocFilter planPsndocFilter =
//	        new QPsndocFilter(dlgDelegator, "pk_psndoc");
//	    planPsndocFilter.setForceMultiCorpRef(UFBoolean.FALSE);
//	    planPsndocFilter.addEditorListener();
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
		
		//dlgDelegator.setPowerEnable(true);
	}
	
}
