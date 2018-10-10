package nc.ui.zl.ld_kpregister.ace.handler;

import java.util.List;

import com.ufida.web.html.I;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {
	
	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		

		if(e.getKey().equals("pk_customer")){
			Object project=e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			String m = "and exists (select b.* from zl_customer_zzxm b " +
					"where nvl(b.dr,0)=0 and b.pk_customer=zl_customer.pk_customer and b.pk_project in('"+project+"'))";
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(m);
			ref.getRefModel().getRefSql();
		}
		
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	public Integer getIntObj(Object obj){
		return obj==null?0:Integer.parseInt(obj.toString());
	}
}
