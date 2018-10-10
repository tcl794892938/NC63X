package nc.ui.zl.lyw_confirmation.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{
	private ShowUpableBillForm billform;

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		if(e.getKey().equals("houseproperty")){
			Object objarea=null;
			String sql="select buildarea from zl_housesource where pk_housesource='"+e.getValue()+"'";
			try {
				objarea=iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				Debug.debug(e1.getMessage());
			}
			if(objarea==null){
				MessageDialog.showHintDlg(billform, "提示", "请先维护该房源的建筑面积！");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				return ;
			}
			billform.getBillCardPanel().getHeadItem("nrentarea").setValue(objarea);
		}
		
		
		
		if(e.getKey().equals("dfeestartdate")||e.getKey().equals("dfeeenddate")){
			Object obj1=e.getBillCardPanel().getHeadItem("dfeeenddate").getValueObject();
			Object obj2=e.getBillCardPanel().getHeadItem("dfeestartdate").getValueObject();
			
			if(e.getKey().equals("dfeestartdate")){
				billform.getBillCardPanel().getHeadItem("dcollectiondate").setValue(e.getValue());
				String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+getStgObj(e.getValue()).substring(0, 7)+"'";
				Object obj_a = null;
				try {
					obj_a=iQ.executeQuery(sql_date, new ColumnProcessor());
				} catch (BusinessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				billform.getBillCardPanel().getHeadItem("caccountperiod").setValue(obj_a);
				billform.getBillCardPanel().getHeadItem("dreccollectdate").setValue(e.getValue());
			}
			
			
			if(obj1==null||obj2==null){
				return ;
			}
			UFDate eddate = new UFDate(obj1.toString());
			UFDate stdate = new UFDate(obj2.toString());
			if(eddate.before(stdate)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用截止日期不能小于费用开始日期！");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				if(e.getKey().equals("dfeestartdate")){
					billform.getBillCardPanel().getHeadItem("dcollectiondate").setValue("");
					billform.getBillCardPanel().getHeadItem("caccountperiod").setValue("");
					billform.getBillCardPanel().getHeadItem("dreccollectdate").setValue("");
				}
				
				return;
			}
			
			
		}
		
		//应确认总金额
		if(e.getKey().equals("amountreceivable")){
			Object taxrate = billform.getBillCardPanel().getHeadItem("ntaxrate").getValueObject();
			if(taxrate==null){
				billform.getBillCardPanel().getHeadItem("ntaxmny").setValue(0);
				billform.getBillCardPanel().getHeadItem("nnotaxmny").setValue(e.getValue());
			}else{
				UFDouble notax = getUFdobj(e.getValue()).div((getUFdobj(taxrate).div(100)).add(1));
				UFDouble tax = getUFdobj(e.getValue()).sub(notax);
				
				billform.getBillCardPanel().getHeadItem("ntaxmny").setValue(tax);
				billform.getBillCardPanel().getHeadItem("nnotaxmny").setValue(notax);
			}
		}
		//税率
		if(e.getKey().equals("ntaxrate")){
			Object rec = billform.getBillCardPanel().getHeadItem("amountreceivable").getValueObject();
			if(rec==null){
				return;
			}else{
				UFDouble notax = getUFdobj(rec).div((getUFdobj(e.getValue()).div(100)).add(1));
				UFDouble tax = getUFdobj(rec).sub(notax);
				
				billform.getBillCardPanel().getHeadItem("ntaxmny").setValue(tax);
				billform.getBillCardPanel().getHeadItem("nnotaxmny").setValue(notax);
			}
		}
		
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	private UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
}
