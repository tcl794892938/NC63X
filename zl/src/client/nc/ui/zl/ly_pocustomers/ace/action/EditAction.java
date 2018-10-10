package nc.ui.zl.ly_pocustomers.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;
import nc.vo.zl.ly_pocustomers.PocustomersVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {

	private ShowUpableBillForm billform;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		
		Object obj=getBillform().getBillCardPanel().getBillTable("id_pocustomers_zr").getRowCount();
		Integer rowcount=(Integer) (obj==null?"":obj);
		if(rowcount>1){
			for(int i=0;i<rowcount-1;i++){
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(i, "isnew", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(i, "tdate", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(i, "ttime", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(i, "ttype", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(i, "recorder", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(i, "tcustomertype", false);
		}
		}
		Object rows = getBillform().getBillCardPanel().getBillTable("id_pocustomers_org").getRowCount();
		Integer rowcount2 = (Integer) (rows == null ? "" : rows);
		if(rowcount2>0){
			for(int i=0;i<rowcount2;i++){
				// 获取表体某个页签中的单个值
				Object vdef1 = getBillform().getBillCardPanel().getBillModel("id_pocustomers_org")
						.getValueAt(i, "vdef1");
				String s = vdef1 == null ? "" : vdef1.toString();
				if(!s.equals("")){
					billform.getBillCardPanel().getBillModel("id_pocustomers_org").setCellEditable(i, "procode", false);
					billform.getBillCardPanel().getBillModel("id_pocustomers_org").setCellEditable(i, "remarks", false);
				}
			}
		}
	}

	@Override
	protected boolean isActionEnable() {
		Object obj = getModel().getSelectedData();
		if (obj == null) {
			return false;
		}
		AggPocustomersVO aggvo = (AggPocustomersVO) obj;
		PocustomersVO pvo = aggvo.getParentVO();
		Object obj1 = pvo.getCustomert();
		String ct = obj1 == null ? "" : obj1.toString();
		if (ct.equals("签约客户")) {
			return false;
		}
		return true;
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

}
