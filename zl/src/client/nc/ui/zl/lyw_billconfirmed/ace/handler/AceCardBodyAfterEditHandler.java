package nc.ui.zl.lyw_billconfirmed.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements
		IAppEventHandler<CardBodyAfterEditEvent> {

	private BillForm billform;

	public BillForm getBillform() {
		return billform;
	}

	public void setBillform(BillForm billform) {
		this.billform = billform;
	}
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		UFDouble zero = new UFDouble(0);
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if(e.getKey().equals("amountconfirming")){
			//UFDouble nconfirmedtotal =new UFDouble (e.getBillCardPanel().getHeadItem("nconfirmedtotal").getValueObject().toString());
			UFDouble taxrate=null;
			if(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "ntaxrate")!=null){
				taxrate = new UFDouble(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "ntaxrate").toString());
			}
			UFDouble amountconfirmed = new UFDouble(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "amountconfirmed").toString());
			UFDouble amountreceivable = new UFDouble(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "amountreceivable").toString());
			UFDouble amountconfirmed2=amountconfirmed.multiply(new UFDouble(1).add(taxrate.div(new UFDouble(100))));
			UFDouble amountconfirming=null;
			UFDouble temp = amountreceivable.sub(amountconfirmed2);
			amountconfirming = e.getValue()==null? temp:new UFDouble(e.getValue().toString());
//			amountconfirming = e.getValue()==temp? new UFDouble(0):new UFDouble(e.getValue().toString());
			//判断本次确认金额是否是最后一笔
			String pk = getStgObj(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "pk_billconfirmedb"));
			if(!pk.equals("")){
				pk = " and pk_billconfirmedb <> '"+pk+"'";
			}
			//获取来源单据主键
			String pk_src = getStgObj(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "vsrcid"));
			//获取之前确认总金额
			String sql_conm = "select sum(b.amountconfirming) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+pk_src+"'  and and exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=1) "+pk;
			Object obj = null;
			try {
				obj=iQ.executeQuery(sql_conm, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			//获取之前确认总无税金额
			String sql_conn = "select sum(b.nnotaxmny) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+pk_src+"'  and and exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=1) "+pk;
			Object obj1 = null;
			try {
				obj1=iQ.executeQuery(sql_conn, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			//获取之前总税额
			String sql_tax = "select sum(b.ntaxmny) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+pk_src+"' and and exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=1) "+pk;
			Object obj2 = null;
			try {
				obj2=iQ.executeQuery(sql_tax, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			UFDouble allamountr = new UFDouble(getStgObj(obj));
			allamountr = allamountr.add(amountconfirming);
			if(allamountr.compareTo(amountreceivable)==0){
				//无税金额
				UFDouble notaxmny = (amountreceivable.div(new UFDouble(1).add(taxrate.div(new UFDouble(100))))).sub(getuf(obj1));
				//税额
				UFDouble nt = new UFDouble(getStgObj(obj2));
				UFDouble ntaxmny = (amountreceivable.sub(amountreceivable.div(new UFDouble(1).add(taxrate.div(new UFDouble(100)))))).sub(nt);
				e.getBillCardPanel().getBillModel().setValueAt(notaxmny, e.getRow(), "nnotaxmny");
				e.getBillCardPanel().getBillModel().setValueAt(ntaxmny, e.getRow(), "ntaxmny");
				tocount(e);
				return;
			}
			//应收金额大于0
			if(amountreceivable.compareTo(zero)>0){
				if(!(amountconfirming.compareTo(zero)>0 && amountconfirming.compareTo(temp)<=0)){
					//弹出提示框“确认的金额不能比未确认的金额大”
					MessageDialog.showHintDlg(e.getContext()
							.getEntranceUI(), "提示", "本次确认的数额应介于(0.00  ,  "+temp+"]");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "amountconfirming");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "ntaxmny");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "nnotaxmny");
					tocount(e);
					return;
				}
				if(taxrate!=null){
					if(amountconfirming.compareTo(temp)!=0){
						UFDouble ntaxmny=amountconfirming.multiply(taxrate.div(100));
						UFDouble notaxmny=amountconfirming.sub(amountconfirming.multiply(taxrate).div(100));
						e.getBillCardPanel().getBillModel().setValueAt(notaxmny, e.getRow(), "nnotaxmny");
						e.getBillCardPanel().getBillModel().setValueAt(ntaxmny, e.getRow(), "ntaxmny");
					}
				}
			}else{
				if(!(amountconfirming.compareTo(zero)<0 && amountconfirming.compareTo(temp)>=0)){
					//弹出提示框“确认的金额不能比未确认的金额大”
					MessageDialog.showHintDlg(e.getContext()
							.getEntranceUI(), "提示", "本次确认的数额应介于["+temp+"  ,  0.00)");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "amountconfirming");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "ntaxmny");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "nnotaxmny");
					tocount(e);
					return;
				}
				if(taxrate!=null){
					if(amountconfirming.compareTo(temp)!=0){
						UFDouble ntaxmny=amountconfirming.multiply(taxrate.div(100));
						UFDouble notaxmny=amountconfirming.sub(amountconfirming.multiply(taxrate.div(100)));
						e.getBillCardPanel().getBillModel().setValueAt(notaxmny, e.getRow(), "nnotaxmny");
						e.getBillCardPanel().getBillModel().setValueAt(ntaxmny, e.getRow(), "ntaxmny");
					}
				}
			}
			tocount(e);
		}
	}
	public void tocount(CardBodyAfterEditEvent e){
		UFDouble nconfirmedtotal2=new UFDouble(0);
		int count = billform.getBillCardPanel().getBillModel().getRowCount();
		for(int i=0;i<count;i++){
			if(e.getBillCardPanel().getBillModel().getValueAt(i, "amountconfirming") == null){
				nconfirmedtotal2=nconfirmedtotal2.add(new UFDouble(0));
			}else{
				nconfirmedtotal2=nconfirmedtotal2.add(new UFDouble(e.getBillCardPanel().getBillModel().getValueAt(i, "amountconfirming").toString()));
			}
			e.getBillCardPanel().getHeadItem("nconfirmedtotal").setValue(nconfirmedtotal2);
		}
	}
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

	public UFDouble getuf(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
}
