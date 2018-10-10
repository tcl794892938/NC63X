/**
 * 
 */
package nc.ui.queryarea.quick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import nc.bs.logging.Logger;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.queryarea.action.AbsQSQueryAction;
import nc.ui.querytemplate.queryarea.IQueryExecutor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.zl.ld_customer_owner.ace.actions.CownerFastQuery;
import nc.ui.zl.ld_customer_tenant.ace.actions.CtenantFastQuery;
import nc.ui.zl.ld_mdcontract.ace.actions.MdcontractFastQuery;
import nc.ui.zl.lm_customer.ace.actions.CustomerFastQuery;
import nc.ui.zl.ly_businesssource.ace.action.BusinessFastQuery;
import nc.ui.zl.ly_pocustomers.ace.action.PocustomerFastQuery;
import nc.ui.zl.tcl_contract.ace.actions.ContractFastQuery;
import nc.ui.zl.tcl_contract.ace.actions.ContractMdFastQuery;
import nc.vo.querytemplate.queryscheme.QuerySchemeObject;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;

/**
 * 快速查询区查询Action
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-1-7
 */
public class QuickQueryAreaQueryAction extends AbsQSQueryAction {

	private static final long serialVersionUID = -8005549807527753662L;

	private QuickQueryArea quickQueryArea;
	
	public QuickQueryAreaQueryAction(QuickQueryArea quickQueryArea) {
		this.quickQueryArea = quickQueryArea;
		putValue(NAME, NCLangRes.getInstance().getStrByID("_template", "UPP_NewQryTemplate-0100")/*查 询*/);
		putValue(SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("_template", "UPP_NewQryTemplate-0100")/*查 询*/);
		initAction();
		initListener();
	}

	private void initAction() {
		registerHotKey(quickQueryArea);
	}

	private void initListener(){
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					IQueryScheme queryScheme = quickQueryArea.getQueryScheme();
					
					//将查询方案对象中用户的数据放进IQueryScheme中
					putUserDefineConsToQS(queryScheme);
					IQueryExecutor queryExecutor = quickQueryArea.getQueryExecutor();
					Logger.debug(queryExecutor.getClass().getName());
					try {
						Field field=queryExecutor.getClass().getDeclaredField("queryAction");
						field.setAccessible(true);
						DefaultQueryAction def=(DefaultQueryAction)field.get(queryExecutor);
						String funNode=def.getFunNode();
						//商源节点快速查询过滤
						if(funNode.equals("ZLH320")){
							BusinessFastQuery fastquery=new BusinessFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//潜在客户快速查询过滤
						if(funNode.equals("ZLH330")){
							PocustomerFastQuery fastquery=new PocustomerFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//客户信息中心快速查询过滤
						if(funNode.equals("ZLH340")){
							CustomerFastQuery fastquery=new CustomerFastQuery();
							queryScheme = fastquery.doQuery(queryScheme);
						}
						//业主管理快速查询过滤
						if(funNode.equals("ZLH350")){
							CownerFastQuery fastquery=new CownerFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//租户管理快速查询过滤
						if(funNode.equals("ZLH360")){
							CtenantFastQuery fastquery=new CtenantFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//合同管理快速查询过滤
						if(funNode.equals("ZLH420")){
							ContractFastQuery fastquery=new ContractFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//合同修订快速查询过滤
						if(funNode.equals("ZLH430")){
							ContractMdFastQuery fastquery=new ContractMdFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//多种经营合同快速查询过滤
						if(funNode.equals("ZLH440")){
							MdcontractFastQuery fastquery=new MdcontractFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//车位合同快速查询过滤
						if(funNode.equals("ZLH52030")){
							MdcontractFastQuery fastquery=new MdcontractFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
						//车辆快速查询过滤
						if(funNode.equals("ZLH52040")){
							MdcontractFastQuery fastquery=new MdcontractFastQuery();
							queryScheme=fastquery.doQuery(queryScheme);
						}
					} catch (Exception e2) {
					}
					
					queryExecutor.doQuery(queryScheme);
				} catch (Exception ex) {
					Logger.error(ex.getMessage(),ex);
					MessageDialog.showWarningDlg(quickQueryArea, NCLangRes
							.getInstance().getStrByID("_Template",
									"UPP_Template-000037")/* 警告 */, ex.getMessage());
				}
			}
			
		});
	}

	private void putUserDefineConsToQS(IQueryScheme queryScheme) {
		QuerySchemeVO quickQuerySchemeVO = quickQueryArea.getQuickQuerySchemeVO();
		if(quickQuerySchemeVO == null){
			return;
		}
		QuerySchemeObject qsObj = quickQuerySchemeVO.getQSObject4Blob();
		String[] userDefineKeys = qsObj.getUserDefineKeys();
		if(userDefineKeys!=null && userDefineKeys.length>0){
			for(String userKey : userDefineKeys){
				queryScheme.put(userKey, qsObj.get(userKey));
			}
		}
	}

	/*public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}*/
	
}