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
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "���ý�ֹʱ�䲻�ܳ�����ͬ��ֹʱ�䣡");
					model2.setValueAt("", e.getRow(), "menddate");
					return;
				}
			}
			if(e.getBillCardPanel().getHeadItem("startdate").getValueObject()!=null){
				UFDate startdate = new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString());
				if(eddate.beforeDate(startdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "���ý�ֹʱ�䲻���ں�ͬ��ʼʱ��֮ǰ��");
					model2.setValueAt("", e.getRow(), "menddate");
					return;
				}
			}
			if(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "mstartdate")!=null){
				UFDate stdate = new UFDate(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "mstartdate").toString());
				if(eddate.before(stdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "���ý�ֹʱ�䲻��С�ڷ��ÿ�ʼʱ�䣡");
					model2.setValueAt("", e.getRow(), "menddate");
					return;
				}
			}
			if(rowcount>1){
				for(int i=0;i<rowcount;i++){
					if(model2.getValueAt(i, "menddate")!=null&&e.getRow()!=i){
						UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
						if(eddate.beforeDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ���ý�ֹʱ�䲻��С��֮ǰ���ý�ֹʱ�䣡");
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
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "���ý�ֹʱ�䲻��С�ڷ��ÿ�ʼʱ�䣡");
					model2.setValueAt("", e.getRow(), "mstartdate");
					return;
				}
			}
			
			if(e.getBillCardPanel().getHeadItem("startdate").getValueObject()!=null){
				UFDate startdate = new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString());
				if(stdate.beforeDate(startdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "���ÿ�ʼʱ�䲻���ں�ͬ��ʼʱ��֮ǰ��");
					model2.setValueAt("", e.getRow(), "mstartdate");
					return;
				}
			}
			if(e.getBillCardPanel().getHeadItem("enddate").getValueObject()!=null){
				UFDate enddate = new UFDate(e.getBillCardPanel().getHeadItem("enddate").getValueObject().toString());
				if(stdate.afterDate(enddate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "���ÿ�ʼʱ�䲻���ں�ͬ��ֹʱ��ʱ��֮��");
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
								MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ���ÿ�ʼʱ�䲻��С��֮ǰ���ý�ֹʱ�䣡");
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
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			model2.setValueAt(e.getValue(), e.getRow(), "moneydate");
			model2.setValueAt(obj_a, e.getRow(), "accountmonth");
		}
		
		if(e.getKey().equals("receivemoney")){
			
			Object  obj2= e.getBillCardPanel().getHeadItem("taxrate").getValueObject();
			if(obj2==null){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "����ѡ��˰�ʣ�");
				return;
			}
			
			UFDouble money = new  UFDouble(0);
			for(int i=0;i<rowcount;i++){
				Object rm = model2.getValueAt(i, "receivemoney");
				UFDouble drm = new UFDouble(getStgObj(rm));
				money = money.add(drm);
			}
			e.getBillCardPanel().setHeadItem("allrent", money);
			//Ӧ��
			UFDouble rmy = new UFDouble(e.getValue().toString());
			//˰��
			UFDouble taxrate = new UFDouble(getStgObj(obj2));
			UFDouble ntax = taxrate.div(100);
			//��˰���
			UFDouble freetax = rmy.div(ntax.add(1));
			//˰��
			UFDouble taxm = rmy.sub(freetax);
			model2.setValueAt(freetax, e.getRow(), "freetaxmoney");
			model2.setValueAt(taxm, e.getRow(), "taxmoney");
			/*//˰��
			UFDouble rmy = new UFDouble(e.getValue().toString());
			
			Integer taxrate = Integer.valueOf(getStgObj(obj2));
			UFDouble tax = rmy.multiply(taxrate);
			UFDouble taxm = tax.div(100);
			//��˰���
			UFDouble freetax = rmy.sub(taxm);
			model2.setValueAt(freetax, e.getRow(), "freetaxmoney");
			model2.setValueAt(taxm, e.getRow(), "taxmoney");*/
			
		}
		
	}
	//�ַ�����װ
	public String getStgObj(Object obj){
		return obj==null?"0":obj.toString();
	}
}
