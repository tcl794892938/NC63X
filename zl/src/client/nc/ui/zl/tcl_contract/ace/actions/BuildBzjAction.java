package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.bill.BillTabVO;
import nc.vo.pub.lang.UFDouble;

public class BuildBzjAction extends NCAction {

	private static final long serialVersionUID = -7036613864808146217L;
	
	public BuildBzjAction(){
		this.setCode("buildbzj");
		this.setBtnName("生成保证金");
	}
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		//保证金生成根据房产页签
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		BillModel model2=panel.getBillModel("pk_contract_bzj");
		int row2=model2.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有生成保证金数据，是否重新生成？");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		model2.clearBodyData();
		
		BillModel model=panel.getBillModel("pk_contract_house");
		int row=model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "提示", "无房产信息，不能生成保证金！");
			return ;
		}
		model2.addLine();
		
		Object objm=model.getTotalTableModel().getValueAt(0, model.getBodyColByKey("nmonthmny"));
		int i=model2.getRowCount()-1;
		Object cust=panel.getHeadItem("pk_customer").getValueObject();
		Object sfxm=panel.getHeadItem("pk_costproject").getValueObject();
		Object htrq=panel.getHeadItem("drentdate").getValueObject();
		model2.setValueAt("10", i, "rowno");
		model2.setValueAt(cust, i, "pk_customer");
		
		//默认取保证金
		String sql="select pk_costproject from zl_costproject where nvl(dr,0)=0 and code='05' and nvl(vdef1,0)=0";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object obj=iQ.executeQuery(sql, new ColumnProcessor());
		if(obj!=null){
			model2.setValueAt(obj, i, "pk_costproject");
		}else{
			model2.setValueAt(sfxm, i, "pk_costproject");
		}
		
		model2.setValueAt(htrq, i, "drecdate");
		model2.setValueAt(objm, i, "nysmny");
		panel.setHeadItem("nbzjmny", objm);
		model2.setValueAt(new UFDouble(0), i, "nskmny");
		model2.setValueAt(new UFDouble(0), i, "nytmny");
		model2.setValueAt(new UFDouble(0), i, "ntkmny");
		model2.setValueAt(objm, i, "nqkmny");
		model2.loadLoadRelationItemValue();
		
		BillTabVO vo=panel.getBillModel("pk_contract_bzj").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("生成保证金成功！",billForm.getModel().getContext());
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
