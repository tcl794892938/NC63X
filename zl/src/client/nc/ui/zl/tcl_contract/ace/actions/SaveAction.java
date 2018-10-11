package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.tcl_contract.ace.config.CalculateRentUtils;

public class SaveAction extends SaveScriptAction {

	private static final long serialVersionUID = 8196125762659199432L;
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		BillModel m1=panel.getBillModel("pk_contract_zqfy");
		BillModel m2=panel.getBillModel("pk_contract_zqfycf");
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> map1=new HashMap<String, Object>();
		int row1=m1.getRowCount();
		int row2=m2.getRowCount();
		if(row1>0){
			for(int i=0;i<row1;i++){
				Object obj1=getColvalue(m1.getValueObjectAt(i, "pk_house"));
				Object obj2=getColvalue(m1.getValueObjectAt(i, "pk_costproject"));
				String pks=obj1.toString()+obj2.toString();
				map.put(pks, pks);
			}
			if(row2>0){
				for(int i=0;i<row2;i++){
					Object obj1=getColvalue(m2.getValueObjectAt(i, "pk_house"));
					Object obj2=getColvalue(m2.getValueObjectAt(i, "pk_costproject"));
					String pks=obj1.toString()+obj2.toString();
					if(map.containsKey(pks)&&!map1.containsKey(pks)){
						map1.put(pks, pks);
					}
					if(!map.containsKey(pks)){
						MessageDialog.showHintDlg(billForm, "提示", "周期费用跟周期费用拆分房产数量不对应，请重新生成！");
						return;
					}
				}
				if(map1.size()!=map.size()){
					MessageDialog.showHintDlg(billForm, "提示", "周期费用跟周期费用拆分房产数量不对应，请重新生成！");
					return;
				}
			}
		}else{
			if(row2>0){
				MessageDialog.showHintDlg(billForm, "提示", "没有周期费用数据，请新增周期费用数据再重新生成周期费用拆分！");
				return;
			}
		}
		
		//校验合同号重复
		Object pkobj=panel.getHeadItem("pk_contract").getValueObject();
		Object code=panel.getHeadItem("htcode").getValueObject();
		String sql="select count(1) from zl_contract where nvl(dr,0)=0 and vbilltypecode='H420' and " +
				" version=-1 and htcode='"+code+"' and pk_contract<>'"+pkobj+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(panel, "提示", "合同号重复！");
			return ;
		}
		
		CalculateRentUtils.recalRent(panel);//租金重算
		super.doAction(e);
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	private static Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}

}
