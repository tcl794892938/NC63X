package nc.ui.zl.cwf_gather.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		if(e.getKey().equals("nskmny")){
			BillCardPanel panel=e.getBillCardPanel();
			BillModel model=panel.getBillModel();
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			if(model.getRowCount()<=0){
				MessageDialog.showHintDlg(panel, "提示", "表体无数据，本次收款总金额不可维护！");
				panel.setHeadItem(e.getKey(), null);
				return;
			}
			
			Object obj1=e.getBillCardPanel().getHeadItem("nrecmny").getValueObject();
			Object obj2=e.getBillCardPanel().getHeadItem("nysmny").getValueObject();
			UFDouble rec=obj1==null?new UFDouble(0):new UFDouble(obj1.toString());
			UFDouble ys=obj2==null?new UFDouble(0):new UFDouble(obj2.toString());
			UFDouble sk=e.getValue()==null?new UFDouble(0):new UFDouble(e.getValue().toString());
			UFDouble rec2=getjdz(rec);
			UFDouble ys2=getjdz(ys);
			UFDouble sk2=getjdz(sk);
			if(rec.compareTo(new UFDouble(0))<0 && sk.compareTo(new UFDouble(0))>0){
				MessageDialog.showHintDlg(panel, "提示", "应收金额为负数，本次收款总金额不可大于0！");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				return;
			}
			if(ys2.add(sk2).sub(rec2).compareTo(new UFDouble(0))>0){
				MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "本次收款总金额不可大于应收未收金额！");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				return;
			}
			for(int i=0;i<model.getRowCount();i++){
				model.setValueAt(null, i, "nskmny");
				Object obja=model.getValueAt(i, "nrecmoney");
				Object objb=model.getValueAt(i, "nysmny");
				UFDouble recmny=obja==null?new UFDouble(0):new UFDouble(obja.toString());
				UFDouble ysmny=objb==null?new UFDouble(0):new UFDouble(objb.toString());
				UFDouble ysws=recmny.sub(ysmny);
				if(getjdz(sk).compareTo(getjdz(ysws))<0){
					model.setValueAt(sk, i, "nskmny");
					if(getuf(model.getValueAt(i, "ntaxrate")).compareTo(new UFDouble(0))!=0){
						model.setValueAt(sk.div(new UFDouble(1).add(getuf(model.getValueAt(i, "ntaxrate")).div(new UFDouble(100)))), i, "nsknotaxmny");
						model.setValueAt(sk.sub(getuf(model.getValueAt(i, "nsknotaxmny"))), i, "nsktaxmny");
					}
					sk=new UFDouble(0);
				}else{
					model.setValueAt(ysws, i, "nskmny");
					String sqla="select sum(nsknotaxmny) from zl_gather_b where dr=0 and vsrcid='"+model.getValueAt(i, "vsrcid")+"' and pk_gather_b" +
							"<>'"+getstg2(model.getValueAt(i, "pk_gather_b"))+"'";
					try {
						Object objaa=iQ.executeQuery(sqla, new ColumnProcessor());
						model.setValueAt(getuf(model.getValueAt(i, "nnotaxmoney")).sub(getuf(objaa)), i, "nsknotaxmny");
						model.setValueAt(getuf(model.getValueAt(i, "nskmny")).sub(getuf(model.getValueAt(i, "nsknotaxmny"))), i, "nsktaxmny");
					} catch (BusinessException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					sk=sk.sub(ysws);
				}
			}
		}
		
		if(e.getKey().equals("pk_project")){
				
			e.getBillCardPanel().setHeadItem("pk_customer", null);
			if(e.getBillCardPanel().getBillModel().getRowCount()>0){
				for(int i=0;i<e.getBillCardPanel().getBillModel().getRowCount();i++){
					e.getBillCardPanel().getBillModel().setValueAt(null, i, "pk_building");
					e.getBillCardPanel().getBillModel().setValueAt(null, i, "pk_house");
					e.getBillCardPanel().getBillModel().setValueAt(null, i, "pk_carno");
				}
			}
			
		}
		
		if(e.getKey().equals("pk_customer")){
			
			if(e.getBillCardPanel().getBillModel().getRowCount()>0){
				for(int i=0;i<e.getBillCardPanel().getBillModel().getRowCount();i++){
					e.getBillCardPanel().getBillModel().setValueAt(null, i, "pk_building");
					e.getBillCardPanel().getBillModel().setValueAt(null, i, "pk_house");
					e.getBillCardPanel().getBillModel().setValueAt(null, i, "pk_carno");
				}
			}
		}
		
	}
	
	public UFDouble getjdz(UFDouble ud){
		return ud.compareTo(new UFDouble(0))>0?ud:new UFDouble(0).sub(ud);
	}

	public UFDouble getuf(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	
	public String getstg2(Object obj){
		return obj==null?"123":obj.toString();
	}
}
