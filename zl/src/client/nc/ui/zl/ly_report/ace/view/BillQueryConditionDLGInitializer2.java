package nc.ui.zl.ly_report.ace.view;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.FieldValueElementEditorFactory;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.MusFilterCostpByOrg;
import nc.ui.zl.abs.power.MusFilterCustomerByProject;
import nc.ui.zl.abs.power.MusFilterHouseByCustomer;
import nc.ui.zl.abs.power.MusFilterProjectByOrg;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uap.rbac.profile.FunctionPermProfileManager;

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
		String[]pks=FunctionPermProfileManager
        .getInstance()
        .getProfile(
            WorkbenchEnvironment.getInstance().getLoginUser().getUser_code()).getFuncSubInfo("ZLH420").getFuncPermissionPkorgs();
		dlgDelegator.registerNeedPermissionOrgFieldCode("zl_zbzj.pk_org",pks);//注册主组织过滤
		
		dlgDelegator.getDealEnumTypeRef();
		this.setDefaultPk_org(dlgDelegator);
		
		String[] baseDoc=new String[]{"zl_zbzj.proname"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg baseDocByOrgFileter = 
					new MusFilterProjectByOrg(dlgDelegator,"zl_zbzj.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc1=new String[]{"zl_zbzj.pk_project"};
		for(int i=0;i<baseDoc1.length;i++){
			MusFilterProjectByOrg baseDocByOrgFileter = 
					new MusFilterProjectByOrg(dlgDelegator,"zl_zbzj.pk_org",baseDoc1[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc2=new String[]{"zl_zbzj.pk_costproject"};
		for(int i=0;i<baseDoc2.length;i++){
			MusFilterCostpByOrg baseDocByOrgFileter = 
					new MusFilterCostpByOrg(dlgDelegator,"zl_zbzj.pk_org",baseDoc2[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc3=new String[]{"zl_zbzj.pk_customer"};
		for(int i=0;i<baseDoc3.length;i++){
			MusFilterCustomerByProject baseDocByOrgFileter = 
					new MusFilterCustomerByProject(dlgDelegator,"zl_zbzj.pk_project",baseDoc3[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前客户名称下的房源
		String[] baseDoc4 = new String[] { "zl_zbzj.housename" };
		for (int i = 0; i < baseDoc4.length; i++) {
			MusFilterHouseByCustomer baseDocByOrgFileter = new MusFilterHouseByCustomer(
					dlgDelegator, "zl_zbzj.pk_customer", baseDoc4[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc5=new String[]{"htmx.pk_project"};
		for(int i=0;i<baseDoc5.length;i++){
			MusFilterProjectByOrg baseDocByOrgFileter = 
					new MusFilterProjectByOrg(dlgDelegator,"htmx.pk_org",baseDoc5[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc6=new String[]{"htmx.pk_costproject"};
		for(int i=0;i<baseDoc6.length;i++){
			MusFilterCostpByOrg baseDocByOrgFileter = 
					new MusFilterCostpByOrg(dlgDelegator,"htmx.pk_org",baseDoc6[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc7=new String[]{"htmx.pk_customer"};
		for(int i=0;i<baseDoc7.length;i++){
			MusFilterCustomerByProject baseDocByOrgFileter = 
					new MusFilterCustomerByProject(dlgDelegator,"htmx.pk_project",baseDoc7[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前客户名称下的房源
		String[] baseDoc8 = new String[] { "htmx.pk_house" };
		for (int i = 0; i < baseDoc8.length; i++) {
			MusFilterHouseByCustomer baseDocByOrgFileter = new MusFilterHouseByCustomer(
					dlgDelegator, "htmx.pk_customer", baseDoc8[i]);
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
