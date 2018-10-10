package nc.ui.zl.ly_businesssource.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;
import nc.vo.zl.ly_businesssource.BusinessVO;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.zl.ly_zylist.ZylistVO;

public class SaveAction extends DifferentVOSaveAction{
	public BillForm billform;


	public BillForm getBillform() {
	return billform;
}

public void setBillform(BillForm billform) {
	this.billform = billform;
}

@Override
public void doAction(ActionEvent e) throws Exception {
	billform.getBillCardPanel().stopEditing();
	billform.getBillCardPanel().dataNotNullValidate();
	AggBusinessSourceVO aggvo=(AggBusinessSourceVO) this.billform.getValue();
	if(aggvo.getChildrenVO().length==0){
		MessageDialog.showHintDlg(billform, "提示", "表体不可为空！");
		return;
	}
	
	IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
	BusinessVO vo=aggvo.getParentVO();
	String sql="select count(*) from zl_business where nvl(dr,0)=0 and idnumber='"+vo.getIdnumber()+"' and pk_org='"+vo.getPk_org()+"'";
	Integer count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
	if(count>0){
		MessageDialog.showHintDlg(billform, "提示", "该身份证已注册商源，请勿重复注册！");
		return;
	}
	
	if(getModel().getUiState()==UIState.ADD){
		billform.getBillCardPanel().getHeadItem("sourceid").setValue(getVbillCode());
		super.doAction(e);
	}
	
	if(getModel().getUiState()==UIState.EDIT){
	
		super.doAction(e);
	}
	
}

/**
 * 生成单据编号
 * @param code
 * @return
 * @throws Exception
 */
public String getVbillCode() throws Exception{
	
	String billcode="";
	IUAPQueryBS iQ = NCLocator.getInstance().lookup(
			IUAPQueryBS.class);
	Object pk_org=billform.getBillCardPanel().getHeadItem("pk_org").getValueObject();
	String getorgcode="select code from org_orgs where nvl(dr,0)=0 and pk_org='"+getStgObj(pk_org)+"'";
	Object orgcode=iQ.executeQuery(getorgcode, new ColumnProcessor());
	
	String sql="select max(sourceid) from zl_business where nvl(dr,0)=0 and sourceid like '"+getStgObj(orgcode)+"ZL%'";
	Object sourceid=iQ.executeQuery(sql, new ColumnProcessor());
	
	if(getStgObj(sourceid).equals("")){
		billcode=getStgObj(orgcode)+"ZL0001";
	}else{
		Integer length=getStgObj(sourceid).length();
		Integer num=Integer.parseInt(getStgObj(sourceid).substring(length-4,length))+1;
		String zero="";
		for(int i=0;i<4-num.toString().length();i++){
			zero+="0";
		}
		billcode=getStgObj(orgcode)+"ZL"+zero+num;
	}
	return billcode;
}

public String getStgObj(Object obj){
	return obj==null?"":obj.toString();
}

	private UFDouble getUfd(UFDouble ud){
		return ud==null?new UFDouble(0):ud;
	}
}
