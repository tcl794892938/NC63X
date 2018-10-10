package nc.ui.zl.lyw_report_customeritems.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.zl.lyw_report_customeritems.ace.config.HTHistoryDlg;

public class ShowdetailAction extends NCAction {
	private static final long serialVersionUID = 6157689097448826493L;
	private BillManageModel model;
	private BillForm billForm;
	public ShowdetailAction() {
		super();
		this.setCode("showdetail");
		this.setBtnName("查看详细明细"); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillModel bm = billForm.getBillCardPanel().getBillModel();
		int row = billForm.getBillCardPanel().getBillTable().getSelectedRow();
		if(row<0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		if(bm.getValueObjectAt(row, "pk_customerpk")==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "查看详细明细不能选择小计行！");
			return ;
		}
		String pk_customerpk=bm.getValueObjectAt(row, "pk_customerpk").toString();
		String pk_org=bm.getValueObjectAt(row, "pk_orgpk").toString();
		String sql_c="select g.pk_org,g.pk_project,g.pk_customer,g.pk_moneytype fkdetail,g.pk_accountno bankno,g.dbilldate dgatherdate," +
				"b.nskmny,b.ntaxrate taxrate,b.nsknotaxmny notaxmny,b.nsktaxmny taxe from zl_gather g left join zl_gather_b b on g.pk_gather=b.pk_gather " +
				"where nvl(g.dr,0)=0 and nvl(b.dr,0)=0 and b.nskmny is not null and b.nskmny>0 and g.pk_customer='"+pk_customerpk+"' and g.pk_org='"+pk_org+"'";		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String,Object>> cs=(List<Map<String,Object>>) iQ.executeQuery(sql_c, new MapListProcessor());

		if(cs.size()<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "未查到详细数据！");
			return ;
		}
		HTHistoryDlg dlg=new HTHistoryDlg(billForm);
		dlg.initValue(cs);
	}
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}
}
