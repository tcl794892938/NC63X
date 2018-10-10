package nc.ui.zl.hql_familyfile.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

public class SaveAction extends DifferentVOSaveAction {
	private ShowUpableBillForm billform;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		AggFamilyfileVO aggvo = (AggFamilyfileVO)billform.getValue();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		//保存时校验项目编码唯一性（项目维度）
		/*String code=aggvo.getParentVO().getCode();
		String proj=aggvo.getParentVO().getPk_projectid();
		//Object obj= aggvo.getParentVO().getAttributeValue("pk_familyfile");
		//String sql="select count(1) from zl_familyfile where nvl(dr,0)=0 and code='"+code+"' "
			//	+ "and pk_familyfile<>'"+obj+"' and pk_projectid='"+proj+"'";
		String sql="select count(1) from zl_familyfile where nvl(dr,0)=0 and code='"+code+"' "
				+ "and pk_projectid='"+proj+"'";
		
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(getModel().getUiState()==UIState.EDIT){
			String sql2 = "select code from zl_familyfile where pk_familyfile ='"+aggvo.getParentVO().getPk_familyfile()+"'";
			Object codeObj = iQ.executeQuery(sql2, new ColumnProcessor());
			if(codeObj.toString().equals(code)){
				super.doAction(e);
				return;
			}
			
		}
		if(it>0){
			MessageDialog.showHintDlg(billform, "提示", "该项目信息下户型编码已经存在！");
			return ;
		}*/
		if(getModel().getUiState()==UIState.ADD){
			String sql_pro = "select code from zl_project where nvl(dr,0)=0 and pk_project = '"+aggvo.getParentVO().getPk_projectid()+"'";
			Object codeObj = iQ.executeQuery(sql_pro, new ColumnProcessor());
			String sql="select max(code) from zl_familyfile where nvl(dr,0)=0 and pk_projectid = '"+aggvo.getParentVO().getPk_projectid()+"'"+
					   " and code like '"+codeObj.toString()+"ZL%'";
			Object obj = iQ.executeQuery(sql, new ColumnProcessor());
			if(obj == null){
				billform.getBillCardPanel().setHeadItem("code", codeObj.toString()+"ZL0001");
			}else {
				int len = obj.toString().length();
				Integer num = Integer.parseInt(obj.toString().substring(len-4))+1;
				
				String code = obj.toString().substring(0,obj.toString().length()-num.toString().length());
				code += num;
				//vo.getParentVO().setCode(code);
				billform.getBillCardPanel().setHeadItem("code", code);
			}
		}
		
		super.doAction(e);
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
}
