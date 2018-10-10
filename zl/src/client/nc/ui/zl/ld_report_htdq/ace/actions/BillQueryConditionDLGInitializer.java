package nc.ui.zl.ld_report_htdq.ace.actions;


import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.FilterContracttypeByOrg;
import nc.ui.zl.abs.power.MusFilterCustomerByProject;
import nc.ui.zl.abs.power.MusFilterHouseByCustomer;
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
		
		String[] baseDoc=new String[]{"report_htdq.pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg baseDocByOrgFileter = 
					new MusFilterProjectByOrg(dlgDelegator,"report_htdq.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "report_htdq.pk_customer" };
		for (int i = 0; i < baseDoc5.length; i++) {
			MusFilterCustomerByProject baseDocByOrgFileter = new MusFilterCustomerByProject(
					dlgDelegator, "report_htdq.pk_project", baseDoc5[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前客户名称下的房源
		String[] baseDoc3 = new String[] { "report_htdq.pk_house" };
		for (int i = 0; i < baseDoc3.length; i++) {
			MusFilterHouseByCustomer baseDocByOrgFileter = new MusFilterHouseByCustomer(
					dlgDelegator, "report_htdq.pk_customer", baseDoc3[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前组织下的合同类型
		String[] baseDoc2 = new String[] { "pk_con.pk_contracttype" };
		for (int i = 0; i < baseDoc2.length; i++) {
			FilterContracttypeByOrg baseDocByOrgFileter = new FilterContracttypeByOrg(
					dlgDelegator, "report_htdq.pk_org", baseDoc2[i]);
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
