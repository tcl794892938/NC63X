package nc.ui.zl.tcl_contract.ace.actions;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;

import nc.ui.pub.beans.UITable.SortItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.uitheme.ui.ThemeResourceCenter;

/**
 * 表体最大化最小化控制按钮
 * 
 * @author:August
 * @date:2011-8-3 上午10:14:26
 */
public class BodySetOrderByDateAction extends AbstractBodyTableExtendAction {
	/**
	 * @author:August
	 * @date:2011-8-3 上午10:16:06
	 */
	private static final long serialVersionUID = -6416154071908143272L;
	
	private ShowUpableBillForm billform;

	private Icon maxicon = ThemeResourceCenter.getInstance().getImage("themeres/ui/toolbaricons/move_down.png");

	public BodySetOrderByDateAction() {
		this.setCode("bodysetdate");
		this.setIcon(this.maxicon);
		// 设置提示
		this.putValue(Action.SHORT_DESCRIPTION, "按租金日期排序");

	}

	@Override
	public void doAction() {
		
		BillModel model2=billform.getBillCardPanel().getBillModel(billform.getBillCardPanel().getCurrentBodyTableCode());
		int row=model2.getRowCount();
		if(row<=0){
			return ;
		}
		
		int col1=model2.getBodyColByKey("pk_house");
		int col2=model2.getBodyColByKey("dstartdate");
		List<SortItem> list=new ArrayList<SortItem>();
		list.add(new SortItem(col2,true));
		list.add(new SortItem(col1,true));
		
		model2.sortByColumns(list);
		
		for(int i=0;i<model2.getRowCount();i++){
			model2.setValueAt((i+1)*10, i, "rowno");
		}
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

}
