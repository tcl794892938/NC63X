package nc.ui.zl.ld_mdcontract.ace.actions;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;

public class BodyDelAction extends nc.ui.pubapp.uif2app.actions.BodyDelLineAction{
	private ShowUpableBillForm billform;
	
	@Override
	public void doAction() {
		AggMdcontractVO aggvo = (AggMdcontractVO) billform.getValue();
		Object obj = getBillform().getBillCardPanel()
				.getBillTable("pk_mdcontract_c").getRowCount();
		Integer rowcount = (Integer) (obj == null ? "" : obj);
		Object obj1 = getBillform().getBillCardPanel()
				.getBillTable("pk_mdcontract_c").getSelectedRow();
		Integer row = (Integer) (obj1 == null ? "" : obj1);
		int it=MessageDialog.showOkCancelDlg(billform, "提示", "删除表体将清空该表体及其以下表体数据信息，是否确认删除？");
		if(it!=UIDialog.ID_OK){
			return ;
		}
		
		
		super.doAction();
		MdcontractCVO[] cvo = (MdcontractCVO[]) aggvo.getChildrenVO();
		
		if(row!=rowcount-1){
			MdcontractCVO[] cvo1 = new MdcontractCVO[row];
			for(int i=0;i<row;i++){
				cvo1[i]=cvo[i];
			}
			AggMdcontractVO[] aggvos = new AggMdcontractVO[1];
			aggvo.setChildrenVO(cvo1);
			aggvos[0] = aggvo;
			//billform.getModel().directlyUpdate(aggvos);
			billform.getBillCardPanel().getBillData().setBodyValueVO(cvo1);
			billform.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		}
		
		UFDouble allrent = new UFDouble(0);
		if(rowcount>0){
			for(int i=0;i<rowcount;i++){
				Object rm = billform.getBillCardPanel().getBodyValueAt(i, "receivemoney");
				allrent = allrent.add(new UFDouble(getStrObj(rm)));
			}
		}
		billform.getBillCardPanel().setHeadItem("allrent", allrent);
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	public String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
