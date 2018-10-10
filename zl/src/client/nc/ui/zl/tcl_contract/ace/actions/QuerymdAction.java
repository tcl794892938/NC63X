package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_sales.SalesVO;

public class QuerymdAction extends DefaultQueryAction
{
  private static final long serialVersionUID = 5953276677401427168L;
  
  public void doAction(ActionEvent e) throws Exception {
	  
	if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
		
		this.processQuery2();
		
	}
	
  }
  
  private void processQuery2() {
		try {
		IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
		
		FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
		
		String where = obj2.getWhere();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String user = AppContext.getInstance().getPkUser();
		String wherenew=(where==null?" 1=1":where)+" and vbilltypecode='H430' ";
		String usersql = "select * from zl_sales where nvl(dr,0)=0 and username=(select pk_base_doc from sm_user where cuserid='"+user+"') ";
		SalesVO vo=(SalesVO)iQ.executeQuery(usersql, new BeanProcessor(SalesVO.class));
		if(vo!=null){
			wherenew +=" and pk_customer in (select pk_customer from zl_customer where nvl(dr,0)=0 and zygw='"+vo.getUsername()+"')";
		}
		
		String pros = "";
		//用户表的身份字段是人员基本信息表的主键
		String get_pk_pro="select c.pk_project from zl_projectcontrol c where nvl(c.dr,0)=0 and c.pk_projectcontrol in " +
				"(select b.pk_projectcontrol from zl_projectcontrol_b b where nvl(b.dr,0)=0 and " +
				"b.usercode=(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"'))";
		List<Object> pk_pro=(List<Object>) iQ.executeQuery(get_pk_pro, new ArrayListProcessor());
		if(pk_pro.size()>0){
			for(int i=0;i<pk_pro.size();i++){
				Object[] pks=(Object[]) pk_pro.get(i);
				pros+="'"+getStgObj(pks[0])+"'";
				if(i<pk_pro.size()-1){
					pros+=",";
				}
			}
		}
		if(pros!=""){
			wherenew += " and pk_project in("+pros+")";
			obj2.setWhere(wherenew);
		}
		
		obj2.setWhere(wherenew);
		
		this.getQueryExecutor().doQuery(queryScheme);
		afterProcessQuery(queryScheme);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}