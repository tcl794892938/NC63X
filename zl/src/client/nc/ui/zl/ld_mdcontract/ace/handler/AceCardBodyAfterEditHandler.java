package nc.ui.zl.ld_mdcontract.ace.handler;

import java.util.Date;

import com.borland.jbcl.control.Message;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		BillModel model2=e.getBillCardPanel().getBillModel("pk_mdcontract_c");
		int rowcount = model2.getRowCount();
		if(e.getKey().equals("menddate")){
			if(e.getValue()==null){
				return ;
			}
			UFDate eddate = new UFDate(e.getValue().toString());
			
			if(e.getBillCardPanel().getHeadItem("enddate").getValueObject()!=null){
				UFDate enddate = new UFDate(e.getBillCardPanel().getHeadItem("enddate").getValueObject().toString());
				if(eddate.afterDate(enddate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用截止时间不能超过合同终止时间！");
					model2.setValueAt("", e.getRow(), "menddate");
					return;
				}
			}
			if(e.getBillCardPanel().getHeadItem("startdate").getValueObject()!=null){
				UFDate startdate = new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString());
				if(eddate.beforeDate(startdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用截止时间不能在合同起始时间之前！");
					model2.setValueAt("", e.getRow(), "menddate");
					return;
				}
			}
			if(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "mstartdate")!=null){
				UFDate stdate = new UFDate(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "mstartdate").toString());
				if(eddate.before(stdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用截止时间不能小于费用开始时间！");
					model2.setValueAt("", e.getRow(), "menddate");
					return;
				}
			}
			if(rowcount>1){
				for(int i=0;i<rowcount;i++){
					if(model2.getValueAt(i, "menddate")!=null&&e.getRow()!=i){
						UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
						if(eddate.beforeDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "当前费用截止时间不能小于之前费用截止时间！");
							model2.setValueAt("", e.getRow(), "menddate");
							return;
						}
					}
					
				}
				for(int j=e.getRow()+1;j<rowcount;j++){
					if(model2.getValueAt(j-1, "menddate")!=null&&!model2.getValueAt(j-1, "menddate").equals(e.getBillCardPanel().getHeadItem("enddate").getValueObject())){
						UFDate odate = new UFDate(model2.getValueAt(j-1, "menddate").toString());
						model2.setValueAt(odate.getDateAfter(1), j, "mstartdate");
					}else if(model2.getValueAt(j-1, "menddate").equals(e.getBillCardPanel().getHeadItem("enddate").getValueObject())){
						model2.setValueAt(new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString()).getDateAfter(1), j, "mstartdate");
					}
					
				}
			}
			
		
		}
		
		if(e.getKey().equals("mstartdate")){
			if(e.getValue()==null){
				return ;
			}
			UFDate stdate = new UFDate(e.getValue().toString());
			if(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "menddate")!=null){
				UFDate eddate = new UFDate(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "menddate").toString());
				if(eddate.before(stdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用截止时间不能小于费用开始时间！");
					model2.setValueAt("", e.getRow(), "mstartdate");
					return;
				}
			}
			
			if(e.getBillCardPanel().getHeadItem("startdate").getValueObject()!=null){
				UFDate startdate = new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString());
				if(stdate.beforeDate(startdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用开始时间不能在合同开始时间之前！");
					model2.setValueAt("", e.getRow(), "mstartdate");
					return;
				}
			}
			if(e.getBillCardPanel().getHeadItem("enddate").getValueObject()!=null){
				UFDate enddate = new UFDate(e.getBillCardPanel().getHeadItem("enddate").getValueObject().toString());
				if(stdate.afterDate(enddate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "费用开始时间不能在合同终止时间时间之后！");
					model2.setValueAt("", e.getRow(), "mstartdate");
					return;
				}
			}
			if(e.getBillCardPanel().getHeadItem("enddate").getValueObject()!=null&&e.getBillCardPanel().getHeadItem("startdate").getValueObject()!=null){
				if(rowcount>0){
					for(int i=0;i<rowcount;i++){
						if(model2.getValueAt(i, "menddate")!=null&&e.getRow()!=i){
							UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
							if(stdate.beforeDate(odate)){
								MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "当前费用开始时间不能小于之前费用截止时间！");
								model2.setValueAt("", e.getRow(), "mstartdate");
								return;
							}
						}
					 }
				}
			}
			
			String date = e.getValue().toString();
			String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+date.substring(0, 7)+"'";
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Object obj_a = null;
			try {
				obj_a = iQ.executeQuery(sql_date, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			model2.setValueAt(e.getValue(), e.getRow(), "moneydate");
			model2.setValueAt(obj_a, e.getRow(), "accountmonth");
		}
		
		if(e.getKey().equals("receivemoney")){
			
			Object  obj2= e.getBillCardPanel().getHeadItem("taxrate").getValueObject();
			if(obj2==null){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "请先选择税率！");
				return;
			}
			
			UFDouble money = new  UFDouble(0);
			for(int i=0;i<rowcount;i++){
				Object rm = model2.getValueAt(i, "receivemoney");
				UFDouble drm = new UFDouble(getStgObj(rm));
				money = money.add(drm);
			}
			e.getBillCardPanel().setHeadItem("allrent", money);
			//应收
			UFDouble rmy = new UFDouble(e.getValue().toString());
			//税率
			UFDouble taxrate = new UFDouble(getStgObj(obj2));
			UFDouble ntax = taxrate.div(100);
			//无税金额
			UFDouble freetax = rmy.div(ntax.add(1));
			//税额
			UFDouble taxm = rmy.sub(freetax);
			model2.setValueAt(freetax, e.getRow(), "freetaxmoney");
			model2.setValueAt(taxm, e.getRow(), "taxmoney");
			/*//税额
			UFDouble rmy = new UFDouble(e.getValue().toString());
			
			Integer taxrate = Integer.valueOf(getStgObj(obj2));
			UFDouble tax = rmy.multiply(taxrate);
			UFDouble taxm = tax.div(100);
			//无税金额
			UFDouble freetax = rmy.sub(taxm);
			model2.setValueAt(freetax, e.getRow(), "freetaxmoney");
			model2.setValueAt(taxm, e.getRow(), "taxmoney");*/
			
		}
		
	}
	//字符串封装
	public String getStgObj(Object obj){
		return obj==null?"0":obj.toString();
	}
}
