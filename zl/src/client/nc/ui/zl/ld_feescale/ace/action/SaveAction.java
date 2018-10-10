package nc.ui.zl.ld_feescale.ace.action;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.ld_feescale.AggFeescaleVO;
import nc.vo.zl.ld_feescale.FeescaleVO;

public class SaveAction extends DifferentVOSaveAction {
	private ShowUpableBillForm bill;
	
	public void doAction(ActionEvent e) throws Exception{
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		//获取前台数据
		AggFeescaleVO aggvo = (AggFeescaleVO)bill.getValue();
		
		double count = aggvo.getParentVO().getAccountscal().toDouble();
		if(count>=0){
			
			
			BillCardPanel panel = bill.getBillCardPanel();
			
			//增加后的保存
			if(getModel().getUiState()==UIState.ADD){
				String pk_org = panel.getHeadItem("pk_org").getValueObject().toString();
				//查询数据库编码
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				
				String sql_1 = "select s.code from org_orgs s where nvl(dr,0)=0 and pk_org='"+pk_org+"'";
				Object org_code = (Object) iQ.executeQuery(sql_1, new ColumnProcessor());
				
				String sql = "select max(code) as code from zl_feescale where nvl(dr,0)=0"+
				             " and code like '"+org_code+"ZL%'";
				Object codeList=(Object) iQ.executeQuery(sql, new ColumnProcessor());
				//不存在数据情况
				if(null==codeList){
					
					String code1 = org_code + "ZL" +"0001";
					
					panel.setHeadItem("code", code1);
				}else{		
					//存在数据情况
					
					String codenum = codeList.toString();
					int num =  Integer.parseInt(codenum.substring(codenum.length() - 4)) + 1;
					String codenum1 = String.valueOf(num);
					int len = 4 - codenum1.length();
					for(int i=0;i<len;i++){
						codenum1 = "0" + codenum1;
					}
					
					String newcode = org_code + "ZL" +codenum1;
					panel.setHeadItem("code", newcode);
				}
			}
		}else{
			MessageDialog.showHintDlg(bill, "提示", "计算数额不能小于0！");
			return ;
		}
		super.doAction(e);
		
	}
	public ShowUpableBillForm getBill() {
		return bill;
	}
	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}
