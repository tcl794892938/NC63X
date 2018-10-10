package nc.ui.zl.base_project.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.zl.base_project.AggProjectVO;

public class SaveAction extends DifferentVOSaveAction {
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		AggProjectVO vo=(AggProjectVO)bill.getValue();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
	    
		/*String sql = "select count(*) from zl_project where nvl(dr,0)=0 and code='" + 
		             vo.getParentVO().getCode()+"'and pk_org='"+vo.getParentVO().getPk_org()+"'";
		int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());*/
		if(getModel().getUiState()==UIState.ADD){
			
			//project
			String pk_org=vo.getParentVO().getPk_org();
			if(pk_org==null||"".equals(pk_org)){
				MessageDialog.showHintDlg(bill, "提示", "请先设置财务组织!");
				return ;
			}
			
			String sql2="select value from pub_sysinit t where t.pk_org='"+pk_org+"' and initcode='ZL001'";
			Object objxm=iQ.executeQuery(sql2, new ColumnProcessor());
			Integer it2=objxm==null?0:Integer.valueOf(objxm.toString());
			
			String sql3="select count(1) from zl_project t where t.pk_org='"+pk_org+"' and nvl(dr,0)=0 "; 
			Integer it1=(Integer)iQ.executeQuery(sql3, new ColumnProcessor());
			if(it2<=it1){
				MessageDialog.showHintDlg(bill, "提示", "该公司项目已超最大授权数!");
				return ;
			}
			
			String sql_org = "select code from org_orgs where nvl(dr,0)=0 and pk_org = '"+vo.getParentVO().getPk_org()+"'";
			Object codeObj = iQ.executeQuery(sql_org, new ColumnProcessor());
			String sql="select max(code) from zl_project where nvl(dr,0)=0 and pk_org = '"+vo.getParentVO().getPk_org()+"'"+
					   " and code like '"+codeObj.toString()+"ZL%'";
			Object obj = iQ.executeQuery(sql, new ColumnProcessor());
			if(obj == null){
				bill.getBillCardPanel().setHeadItem("code", codeObj.toString()+"ZL0001");
			}else {
				int len = obj.toString().length();
				Integer num = Integer.parseInt(obj.toString().substring(len-4))+1;
				
				String code = obj.toString().substring(0,obj.toString().length()-num.toString().length());
				code += num;
				//vo.getParentVO().setCode(code);
				bill.getBillCardPanel().setHeadItem("code", code);
			}
		}
		/*if(getModel().getUiState()==UIState.EDIT){
			String sql2 = "select code from zl_project where pk_project ='" + 
		                  vo.getParentVO().getPk_project()+"'";
			Object codeObj = iQ.executeQuery(sql2, new ColumnProcessor());
			if(codeObj.toString().equals(vo.getParentVO().getCode())){
				super.doAction(e);
				return;
			}
			
		}*/
		
		/*if(a>0){
			MessageDialog.showHintDlg(bill, "提示", "项目编码已存在，请检查！");
			return;
		}*/
		
		super.doAction(e);
	}
}
