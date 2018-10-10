package nc.ui.zl.cwf_gather.ace.handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		if(e.getKey().equals("begindate")){
			Object obj1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "enddate");
			if(obj1!=null & e.getValue()!=null){
				 DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				 try {
				 Date dt1 = df.parse(obj1.toString());
				 Date dt2 = df.parse(e.getValue().toString());
				 if(dt1.getTime()<dt2.getTime()){
					 MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "结束日期不得大于起始日期，请检查！");
					 e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), e.getKey());
				 }
				} catch (ParseException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
		if(e.getKey().equals("enddate")){
			Object obj1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "begindate");
			if(obj1!=null & e.getValue()!=null){
				 DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				 try {
				 Date dt1 = df.parse(obj1.toString());
				 Date dt2 = df.parse(e.getValue().toString());
				 if(dt1.getTime()>dt2.getTime()){
					 MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "结束日期不得大于起始日期，请检查！");
					 e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), e.getKey());
				 }
				} catch (ParseException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getKey().equals("nskmny")){
			Object obj=e.getBillCardPanel().getTotalTableModel().getValueAt(0, e.getBillCardPanel().getBillModel().getItemIndex(e.getKey()));
			UFDouble ud=obj==null?new UFDouble(0):new UFDouble(obj.toString());
			e.getBillCardPanel().setHeadItem("nskmny", ud);
			Object tax=e.getBillCardPanel().getBodyValueAt(e.getRow(), "ntaxrate");
			UFDouble tax1=tax==null?new UFDouble(0):new UFDouble(tax.toString());
			e.getBillCardPanel().setBodyValueAt(ud.div(tax1.add(100)).multiply(100), e.getRow(), "nsknotaxmny");//本次收款无税金额
			Object obj2=e.getBillCardPanel().getBodyValueAt(e.getRow(), "nsknotaxmny");
			UFDouble ud2=obj2==null?new UFDouble(0):new UFDouble(obj2.toString());
			e.getBillCardPanel().setBodyValueAt(ud.mod(ud2), e.getRow(), "nsktaxmny");//本次收款税额
		}
		
		if(e.getKey().equals("pk_building")){
				e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "pk_house");
		}
		
		if(e.getKey().equals("ntaxrate")){
			Object obj=e.getBillCardPanel().getBodyValueAt(e.getRow(), "nskmny");
			if(obj==null){
				return;
			}else{
				UFDouble ud=new UFDouble(obj.toString());
				Object tax=e.getBillCardPanel().getBodyValueAt(e.getRow(), "ntaxrate");
				UFDouble tax1=tax==null?new UFDouble(0):new UFDouble(tax.toString());
				e.getBillCardPanel().setBodyValueAt(ud.div(tax1.add(100)).multiply(100), e.getRow(), "nsknotaxmny");//本次收款无税金额
				Object obj2=e.getBillCardPanel().getBodyValueAt(e.getRow(), "nsknotaxmny");
				UFDouble ud2=obj2==null?new UFDouble(0):new UFDouble(obj2.toString());
				e.getBillCardPanel().setBodyValueAt(ud.mod(ud2), e.getRow(), "nsktaxmny");//本次收款税额
			}
		}
		
	}

}
