package nc.ui.zl.lyw_report_confirmeditems.ace.abspower;

import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.zl.abs.power.MusFilterBuildingByProject;
import nc.ui.zl.abs.power.MusFilterCostpByOrg;
import nc.ui.zl.abs.power.MusFilterCustomerByProject;
import nc.ui.zl.abs.power.MusFilterHouseByBuilding;
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
		String[] baseDoc=new String[]{"confirmeditems.pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			MusFilterProjectByOrg2 baseDocByOrgFileter = 
					new MusFilterProjectByOrg2(dlgDelegator,"confirmeditems.pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的楼栋
		String[] baseDoc2=new String[]{"confirmeditems.pk_building"};
		for(int i=0;i<baseDoc2.length;i++){
			MusFilterBuildingByProject baseDocByProjFileter = 
					new MusFilterBuildingByProject(dlgDelegator,"confirmeditems.pk_project",baseDoc2[i]); 
			baseDocByProjFileter.addEditorListener();
		}
		//当前楼栋下的房源
		String[] baseDoc3 = new String[] { "confirmeditems.houseproperty" };
		for (int i = 0; i < baseDoc3.length; i++) {
			MusFilterHouseByBuilding baseDocByOrgFileter = new MusFilterHouseByBuilding(
					dlgDelegator, "confirmeditems.pk_building", baseDoc3[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前项目下的客户名
		String[] baseDoc5 = new String[] { "confirmeditems.pk_customer"};
		for (int i = 0; i < baseDoc5.length; i++) {
			MusFilterCustomerByProject baseDocByOrgFileter = new MusFilterCustomerByProject(
					dlgDelegator, "confirmeditems.pk_project", baseDoc5[i]);
			baseDocByOrgFileter.addEditorListener();
		}
		//当前组织下的收费项目
		String[] baseDoc6=new String[]{"confirmeditems.chargingproject"};
		for(int i=0;i<baseDoc6.length;i++){
			MusFilterCostpByOrg baseDocByOrgFileter = 
					new MusFilterCostpByOrg(dlgDelegator,"confirmeditems.pk_org",baseDoc6[i]); 
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
