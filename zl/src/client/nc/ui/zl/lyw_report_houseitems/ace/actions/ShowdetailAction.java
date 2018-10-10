package nc.ui.zl.lyw_report_houseitems.ace.actions;

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
import nc.ui.zl.lyw_report_houseitems.ace.config.HTHistoryDlg;

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
		if(bm.getValueObjectAt(row, "pk_housesource")==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "查看详细明细不能选择小计行！");
			return ;
		}
		String pk_housesource=bm.getValueObjectAt(row, "pk_housesource").toString();
		String pk_org=bm.getValueObjectAt(row, "pk_orgpk").toString();
		String rdate =getStrObj(bm.getValueObjectAt(0, "s_type")) ;
		rdate=rdate.replaceAll("gatherdate", "b.begindate");
		String sql_g = "select g.pk_org,g.pk_project,g.pk_moneytype fkdetail,g.pk_accountno bankno,g.dbilldate dgatherdate," +
				"b.nskmny,b.ntaxrate taxrate,b.nsknotaxmny notaxmny,b.nsktaxmny taxe,b.pk_house from zl_gather g left join zl_gather_b b on g.pk_gather=b.pk_gather " +
				"where nvl(g.dr,0)=0 and nvl(b.dr,0)=0 and b.nskmny is not null and b.nskmny>0 and b.pk_house='"+pk_housesource+"' and g.pk_org='"+pk_org+"' and "+rdate;
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String,Object>> gather=(List<Map<String,Object>>) iQ.executeQuery(sql_g, new MapListProcessor());
		if(gather.size()<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "未查到详细数据！");
			return ;
		}
		HTHistoryDlg dlg=new HTHistoryDlg(billForm);
		dlg.initValue(gather);
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
	private String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
