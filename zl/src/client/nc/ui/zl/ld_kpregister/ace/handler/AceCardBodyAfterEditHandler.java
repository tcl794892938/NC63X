package nc.ui.zl.ld_kpregister.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.cwf_recbill.RecbillVO;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		String tabcode=e.getBillCardPanel().getCurrentBodyTableCode();
		BillModel model2=e.getBillCardPanel().getBillModel(tabcode);
		//开票金额修改
		if(e.getKey().equals("kpmoney")&&tabcode.equals("pk_kpregister_d")){
			
			Object obj2 = model2.getValueObjectAt(e.getRow(), "vsrcid");
			//Object obj2 = ((DefaultConstEnum)obj2_1).getValue();
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql = "select * from zl_recbill  where nvl(dr,0)=0 and pk_recbill='"+getStgObj(obj2)+"'";
			RecbillVO vo = null;
			try {
				vo=(RecbillVO)iQ.executeQuery(sql, new BeanProcessor(RecbillVO.class));
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			Object obj1 = model2.getValueObjectAt(e.getRow(), "nrecmoney");
			if(getUFdobj(obj1).toDouble()>0){
				if(getUFdobj(e.getValue()).toDouble()<0){
					/*MessageDialog.showHintDlg(billForm, "提示", "当前开票金额为收款,请输入正数！");
					model2.setValueAt(e.getOldValue(), e.getRow(), "kpmoney");
					return;*/
					//应收单开票金额
					UFDouble nkp = getUFdobj(vo.getInvoicemoney());
					if(nkp.compareTo(getUFdobj(0))==0){
						MessageDialog.showHintDlg(billForm, "提示", "当前开票金额不能输入负数！");
						model2.setValueAt(e.getOldValue(), e.getRow(), "kpmoney");
						return;
					}else if(nkp.compareTo(getUFdobj(0))==1){
						if((getUFdobj(e.getValue()).abs()).compareTo(nkp)==1){
							MessageDialog.showHintDlg(billForm, "提示", "当前开票金额的绝对值应小于或等于"+nkp.add(getUFdobj(5),2)+"！");
							model2.setValueAt(e.getOldValue(), e.getRow(), "kpmoney");
							return;
						}
					}
					
				}
			}else if(getUFdobj(obj1).toDouble()<0){
				if(getUFdobj(e.getValue()).toDouble()>0){
					MessageDialog.showHintDlg(billForm, "提示", "当前开票金额为退款,请输入负数！");
					model2.setValueAt(e.getOldValue(), e.getRow(), "kpmoney");
					return;
				}
			}
			
			UFDouble nrece = new UFDouble(getStgObj(vo.getNrecmoney()));
			UFDouble nkpmy= new UFDouble(getStgObj(vo.getInvoicemoney()));
			UFDouble money1 = nrece.abs().sub(nkpmy.abs());
			UFDouble money2 = getUFdobj(e.getValue()).abs();
			UFDouble taxrate=getUFdobj(model2.getValueObjectAt(e.getRow(), "ntaxrate"));
			//判断本次确认金额是否是最后一笔
			String pk = getStgObj(e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "pk_kpregister_d"));
			if(!pk.equals("")){
				pk = " and pk_kpregister_d <> '"+pk+"'";
			}
			if(money2.toDouble()>(money1.toDouble()+5)){
				MessageDialog.showHintDlg(billForm, "提示", "当前开票金额不能大于"+(money1.toDouble()+5)+"，请检查！");
				model2.setValueAt(e.getOldValue(), e.getRow(), "kpmoney");
				return;
			}else if(money1.compareTo(money2)==0){
				//获取无税
				 String sql_conn = "select sum(d.nnotaxmny) from zl_kpregister_d d where nvl(d.dr,0)=0 and d.vsrcid='"+getStgObj(model2.getValueObjectAt(e.getRow(), "vsrcid"))+"' and exists(select 1 from zl_kpregister k where nvl(k.dr,0)=0 and k.pk_kpregister=d.pk_kpregister and k.vbillstatus=1)"+pk;
					Object obj4 = null;
					try {
						obj4=iQ.executeQuery(sql_conn, new ColumnProcessor());
					} catch (BusinessException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				UFDouble notaxmny = (getUFdobj(obj1).div(new UFDouble(1).add(taxrate.div(new UFDouble(100))))).sub(getUFdobj(obj4));
				//税额
				UFDouble ntaxmny = getUFdobj(e.getValue()).sub(notaxmny);
				e.getBillCardPanel().getBillModel().setValueAt(notaxmny, e.getRow(), "nnotaxmny");
				e.getBillCardPanel().getBillModel().setValueAt(ntaxmny, e.getRow(), "ntaxmny");
			}else{
				
				UFDouble notaxmny=getUFdobj(e.getValue()).div(new UFDouble(1).add(taxrate.div(new UFDouble(100))));
				UFDouble ntaxmny=getUFdobj(e.getValue()).sub(notaxmny);
				e.getBillCardPanel().getBillModel().setValueAt(notaxmny, e.getRow(), "nnotaxmny");
				e.getBillCardPanel().getBillModel().setValueAt(ntaxmny, e.getRow(), "ntaxmny");
			}
			
			
			
			
			
			
			int rowcount = model2.getRowCount();
			 UFDouble ncount = new UFDouble(0);
			for(int i=0;i<rowcount;i++){
				Object obj3 = model2.getValueObjectAt(i, "kpmoney");
				ncount = ncount.add(getUFdobj(obj3));
			}
			
			
			e.getBillCardPanel().setHeadItem("kpmoney", ncount);
			
		}
		
		
	}
	private UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	private String getStgObj(Object obj){
		return obj==null?" ":obj.toString();
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}
