package nc.ui.zl.cwf_gather.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction{

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
		BillCardPanel panel = billform.getBillCardPanel();
		
		if(getModel().getUiState()==UIState.ADD){
			Object obj=panel.getHeadItem("pk_customer").getValueObject();
			String sql="select count(*) from zl_gather where nvl(dr,0)=0 and pk_customer='"+obj+"' and vbillstatus<>1";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
			if(a>0&&!new UFBoolean(getStgObj(panel.getHeadItem("isadd").getValueObject())).booleanValue()){
				throw new Exception("该客户存在未审核的收款单，不可保存！");
			}
		}
		
		if(!new UFBoolean(getStgObj(panel.getHeadItem("isadd").getValueObject())).booleanValue()){
			BillModel bmodel=panel.getBillModel();
			for(int i=0;i<bmodel.getRowCount();i++){
				if(getuf(bmodel.getValueAt(i, "ntaxrate")).compareTo(new UFDouble(0))==0){
					continue;
				}
				UFDouble sk=getuf(bmodel.getValueAt(i, "nskmny"));
				UFDouble ws=getuf(bmodel.getValueAt(i, "nsknotaxmny"));
				UFDouble se=getuf(bmodel.getValueAt(i, "nsktaxmny"));
				if(ws.add(se).compareTo(sk)!=0){
					throw new Exception("表体存在本次收款无税+税额，与本次收款金额不等，请检查！");
				}
			}
		}
		
		super.doAction(e);
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	public UFDouble getuf(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	
}
