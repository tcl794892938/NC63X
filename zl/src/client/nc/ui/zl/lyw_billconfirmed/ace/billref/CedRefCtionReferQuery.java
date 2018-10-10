package nc.ui.zl.lyw_billconfirmed.ace.billref;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.zl.abs.power.FilterBuildingByProject;
import nc.ui.zl.abs.power.FilterCostpByOrg2;
import nc.ui.zl.abs.power.FilterCustomerByProject;
import nc.ui.zl.abs.power.FilterHouseByBuilding;
import nc.ui.zl.abs.power.FilterProjectByOrg;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;

public class CedRefCtionReferQuery extends DefaultBillReferQuery {

	public CedRefCtionReferQuery(Container c, TemplateInfo info) {
		super(c, info);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
		// TODO 自动生成的方法存根
		dlgDelegator.registerNeedPermissionOrgFieldCode("pk_org");//注册主组织过滤
		try {
			this.setDefaultPk_org(dlgDelegator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] baseDoc = new String[]{"pk_project"};
		for(int i=0;i<baseDoc.length;i++){
			FilterProjectByOrg baseDocByOrgFileter = 
					new FilterProjectByOrg(dlgDelegator,"pk_org",baseDoc[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc2=new String[]{"buildno"};
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
		
		String[] baseDoc4=new String[]{"houseproperty"};
		for(int i=0;i<baseDoc4.length;i++){
			FilterHouseByBuilding baseDocByOrgFileter = 
					new FilterHouseByBuilding(dlgDelegator,"buildno",baseDoc4[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
		
		String[] baseDoc5=new String[]{"chargingproject"};
		for(int i=0;i<baseDoc5.length;i++){
			FilterCostpByOrg2 baseDocByOrgFileter = 
					new FilterCostpByOrg2(dlgDelegator,"pk_org",baseDoc5[i]); 
			baseDocByOrgFileter.addEditorListener();
		}
	}
	
	private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) throws Exception {
		String defaultOrg = null;
		try {
			String pk_org = OrgSettingAccessor.getDefaultOrgUnit();
				defaultOrg = pk_org;
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		if (defaultOrg != null && defaultOrg.trim().length() > 0) {
			dlgDelegator.setDefaultValue("pk_org", defaultOrg);
			/*String sql="select wm_concat(pk_costproject) from zl_costproject where nvl(dr,0)=0 and (pk_org='"+defaultOrg+"' or nvl(vdef1,0)='0') " +
					"and code not like '05%' and code not like '06%'";*/
			String sql="select wm_concat(pk_costproject) from zl_costproject where nvl(dr,0)=0 " +
					"and code not like '05%' and code not like '06%'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Object pk=iQ.executeQuery(sql, new ColumnProcessor());
			dlgDelegator.setDefaultValue("chargingproject", pk.toString());
		}
	}
}
