package nc.ui.zl.ld_parkcontract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction{

	private ShowUpableBillForm billform;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		billform.getBillCardPanel().setHeadItem("version", -1);
		
		//校验车位
		BillModel model = billform.getBillCardPanel().getBillModel("pk_parkcontract_b");
		AggParkcontractVO aggvo = (AggParkcontractVO) billform.getValue();
		if(aggvo==null){
			return;
		}
		ParkcontractVO vo = aggvo.getParentVO();
		int row = model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billform, "提示", "无车位数据，不能保存！");
			return ;
		}
		
		//判断该车位是否有合同
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for(int i=0;i<row;i++){
			
			Object a = model.getValueObjectAt(i, "parkarea");
			Object b = model.getValueObjectAt(i, "parknum");
			Object parkarea = ((DefaultConstEnum)a).getValue();
			
			Object parknum = ((DefaultConstEnum)b).getValue();
			String sql = "select * from zl_parkcontract where nvl(dr,0)=0 and pk_project='"+vo.getPk_project()+"' and pk_parkcontract in("+
						"select p.pk_parkcontract from zl_parkcontract_b p where nvl(dr,0)=0 and p.parkarea='"+parkarea+"' and p.parknum='"+parknum+"') and version=-1";
			List<ParkcontractVO> listvo=(List<ParkcontractVO>)iQ.executeQuery(sql, new BeanListProcessor(ParkcontractVO.class));
			
			Object obj1 = billform.getBillCardPanel().getHeadItem("startdate").getValueObject();
			Object obj2 = billform.getBillCardPanel().getHeadItem("enddate").getValueObject();
			Object obj3 = billform.getBillCardPanel().getHeadItem("vbillcode").getValueObject();
			
			UFDate date1 = new UFDate(obj1.toString());
			UFDate date2 = new UFDate(obj2.toString());
			if(listvo.size()>0){
				for(int j=0;j<listvo.size();j++){
					if(((obj3!=null)&&(!obj3.equals(listvo.get(j).getVbillcode())))||obj3==null){
						UFDate date1_1 = listvo.get(j).getStartdate();
						UFDate date1_2 = listvo.get(j).getEnddate();
						if(!(date1_2.before(date1)||date1_1.after(date2))){
							MessageDialog.showHintDlg(billform, "提示", "当前车位合同有效时间范围和单据号为："+listvo.get(j).getVbillcode()+ "冲突，不能保存！");
							return ;
						}
					}
				}
			}
		}
		
		//校验编码重复
		Object pkobj=billform.getBillCardPanel().getHeadItem("pk_parkcontract").getValueObject();
		Object code=billform.getBillCardPanel().getHeadItem("code").getValueObject();
		String sql1="select count(1) from zl_parkcontract where nvl(dr,0)=0  and " +
				" code='"+code+"' and pk_parkcontract<>'"+pkobj+"'";
		
		Integer it=(Integer)iQ.executeQuery(sql1, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(billform, "提示", "合同编码重复！");
			return ;
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
