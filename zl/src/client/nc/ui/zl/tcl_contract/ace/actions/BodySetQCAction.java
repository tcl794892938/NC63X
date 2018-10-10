package nc.ui.zl.tcl_contract.ace.actions;

import javax.swing.Action;
import javax.swing.Icon;

import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

/**
 * 表体最大化最小化控制按钮
 * 
 * @author:August
 * @date:2011-8-3 上午10:14:26
 */
public class BodySetQCAction extends AbstractBodyTableExtendAction {
	/**
	 * @author:August
	 * @date:2011-8-3 上午10:16:06
	 */
	private static final long serialVersionUID = -6416154071908143272L;
	
	private ShowUpableBillForm billform;

	private Icon maxicon = ThemeResourceCenter.getInstance().getImage("themeres/ui/toolbaricons/col_set.png");

	public BodySetQCAction() {
		this.setCode("bodysetqc");
		this.setIcon(this.maxicon);
		// 设置提示
		this.putValue(Action.SHORT_DESCRIPTION, "设置期初数据");

	}

	@Override
	public void doAction() {
		
		BillModel model2=billform.getBillCardPanel().getBillModel(billform.getBillCardPanel().getCurrentBodyTableCode());
		int row=model2.getRowCount();
		if(row<=0){
			return ;
		}
		for(int i=0;i<row;i++){
			Object obj=model2.getValueAt(i, "dstartdate");
			UFDate ud=new UFDate(obj.toString());
			if(ud.beforeDate(new UFDate("2018-01-01"))){
				model2.setValueAt(new UFBoolean(true), i, "is_qc");
			}
		}
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

}
