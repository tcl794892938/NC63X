package nc.ui.zl.abs.power;


import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refregion.AbstractLinkageColumnListener;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings("restriction")
public class MusFilterHouseByCustomer extends AbstractLinkageColumnListener {

	private String orgField = null;
	private String targetField = null;
	private String filterField = null;

	public MusFilterHouseByCustomer(QueryConditionDLGDelegator dlg, String orgField, String basedocField) {
		super(dlg);
		this.orgField = orgField;
		this.targetField = basedocField;
	}

	public MusFilterHouseByCustomer(QueryConditionDLGDelegator dlg, String orgField, String basedocField, String filterField) {
		this(dlg, orgField, basedocField);
		this.filterField = filterField;
	}

	public void addEditorListener() {
		this.setFatherPath(this.orgField);
		this.setChildPath(this.targetField);
		// 注册编辑事件
		this.qryCondDLGDelegator.registerCriteriaEditorListener(this);
	}

	public void processLinkageLogic(List<IFieldValueElement> fatherValues, IFilterEditor editor) {
		List<String> diffValues = new ArrayList<String>();
		for (IFieldValueElement fve : fatherValues) {
			if (diffValues.contains(fve.getSqlString()) || fve.getSqlString() == null) {
				continue;
			}
			diffValues.add(fve.getSqlString());
		}  

		FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
		if(wrapper.getFieldValueElemEditorComponent() == null || !(wrapper.getFieldValueElemEditorComponent() instanceof UIRefPane)) {
			return;
		}
		
		UIRefPane refPane = (UIRefPane) wrapper.getFieldValueElemEditorComponent();
		if (refPane.getRefModel() == null) {
			return;
		}

		// 清空之前的值
		if (refPane.getRefModel().getVecData() == null || refPane.getRefModel().getVecData().size() == 0) {
			// 不处理
		}
		else {
			refPane.getRefModel().clearData();
		}

		// 如果选择单个组织，那么参照界面将直接选择，而不是通过左右选择框来实现
		if (diffValues.size() == 1) {
			refPane.setMultiOrgSelected(false);
			refPane.setMultiCorpRef(false);
			qryCondDLGDelegator.setRefMultiCorpFlag(targetField, false);
		}

		else {
			refPane.setMultiOrgSelected(false);
			refPane.setMultiCorpRef(false);
			qryCondDLGDelegator.setRefMultiCorpFlag(targetField, false);
		}
		
		if(refPane.getRefModel().getPkFieldCode().equals("pk_housesource")){
			filterField="***";
		}else{
			filterField=null;
		}
		
		if (diffValues.size() >=0) {
			if(diffValues.size()==0&&!StringUtils.isEmpty(filterField)){
				refPane.getRefModel().addWherePart(" and pk_housesource ='' ");
				refPane.getRefModel().getRefSql();
				refPane.setMultiSelectedEnabled(true);
			}

			if (!StringUtils.isEmpty(filterField) && diffValues.size()>0) {
					String[] pk_orgs = diffValues.toArray(new String[0]);
					String p = "";
					if(pk_orgs.length>1){
						
						for(int i=0;i<pk_orgs.length;i++){
							if(i==pk_orgs.length-1){
								p = p + "'"+pk_orgs[i]+"'";
							}else{
								p = p + "'"+pk_orgs[i]+"',";
							}
							
						}
					
					}else if(pk_orgs.length==1){
						p = "'"+pk_orgs[0]+"'";
					}
						
						refPane.getRefModel().addWherePart(" and pk_housesource in (select fcname from " +
								"zl_customer_fcxx where nvl(dr,0)=0 and pk_customer in("+p+ "))");
						refPane.setPk_org(pk_orgs[0]);
						refPane.getRefModel().getRefSql();
						refPane.setMultiRefFilterPKs(pk_orgs);
						refPane.setMultiSelectedEnabled(true);
					
					
			} 
		}

		else if(null != this.qryCondDLGDelegator  &&
				null != this.qryCondDLGDelegator.getLogincontext()&&
				null != this.qryCondDLGDelegator.getLogincontext().getFuncInfo()&&
				! ArrayUtils.isEmpty(this.qryCondDLGDelegator.getLogincontext().getFuncInfo().getFuncPermissionPkorgs())){
			// 如果没有选择主组织，则取用户分配组织的基本档案数据
			refPane.setMultiRefFilterPKs(this.qryCondDLGDelegator.getLogincontext().getFuncInfo().getFuncPermissionPkorgs());
		}

	}
	
}
