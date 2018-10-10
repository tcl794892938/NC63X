package nc.ui.zl.abs.power;


import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refregion.AbstractLinkageColumnListener;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class MusFilterProjectByOrg2 extends AbstractLinkageColumnListener {

	private String orgField = null;
	private String targetField = null;
	private String filterField = null;

	public MusFilterProjectByOrg2(QueryConditionDLGDelegator dlg, String orgField, String basedocField) {
		super(dlg);
		this.orgField = orgField;
		this.targetField = basedocField;
	}

	public MusFilterProjectByOrg2(QueryConditionDLGDelegator dlg, String orgField, String basedocField, String filterField) {
		this(dlg, orgField, basedocField);
		this.filterField = filterField;
	}

	public void addEditorListener() {
		this.setFatherPath(this.orgField);
		this.setChildPath(this.targetField);
		// ע��༭�¼�
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

		// ���֮ǰ��ֵ
		if (refPane.getRefModel().getVecData() == null || refPane.getRefModel().getVecData().size() == 0) {
			// ������
		}
		else {
			refPane.getRefModel().clearData();
		}

		// ���ѡ�񵥸���֯����ô���ս��潫ֱ��ѡ�񣬶�����ͨ������ѡ�����ʵ��
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
		
		if(refPane.getRefModel().getPkFieldCode().equals("pk_project")){
			filterField="***";
		}else{
			filterField=null;
		}
		
		if (diffValues.size() >=0) {
			if(diffValues.size()==0&&!StringUtils.isEmpty(filterField)){
				refPane.getRefModel().addWherePart(" and pk_org ='' ");
				refPane.setMultiSelectedEnabled(true);
			}

			if (!StringUtils.isEmpty(filterField) && diffValues.size()>0) {
				String[] pk_orgs = diffValues.toArray(new String[0]);
				String p="";
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
				String userpk=AppContext.getInstance().getPkUser();
				String procontrol = 
					 "pk_project in (select pk_project from zl_project p"+
					 " where nvl(p.dr,0)=0 and p.pk_org in ("+p+") and"+
					 " not exists (select 1 from zl_projectcontrol_b pcb join zl_projectcontrol pc " +
					 " on (pcb.pk_projectcontrol=pc.pk_projectcontrol and pcb.usercode=" +
					 " (select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+userpk+"')) " +
					 " where pc.pk_org=p.pk_org)"+
					 " union all"+
					 " select pk_project from zl_project p"+
					 " where nvl(p.dr,0)=0 and p.pk_org in ("+p+") and "+
					 " exists (select 1 from zl_projectcontrol_b pcb join zl_projectcontrol pc " +
					 " on (pcb.pk_projectcontrol=pc.pk_projectcontrol and pcb.usercode=" +
					 "(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+userpk+"')) " +
					 " where pc.pk_org=p.pk_org))";
				refPane.getRefModel().addWherePart(" and pk_org in ("+p+") and "+procontrol);
				refPane.setPk_org(pk_orgs[0]);
				refPane.setMultiRefFilterPKs(pk_orgs);
				refPane.setMultiSelectedEnabled(true);
			} 
		}else if(null != this.qryCondDLGDelegator  &&
				null != this.qryCondDLGDelegator.getLogincontext()&&
				null != this.qryCondDLGDelegator.getLogincontext().getFuncInfo()&&
				! ArrayUtils.isEmpty(this.qryCondDLGDelegator.getLogincontext().getFuncInfo().getFuncPermissionPkorgs())){
			// ���û��ѡ������֯����ȡ�û�������֯�Ļ�����������
			refPane.setMultiRefFilterPKs(this.qryCondDLGDelegator.getLogincontext().getFuncInfo().getFuncPermissionPkorgs());
		}

	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
