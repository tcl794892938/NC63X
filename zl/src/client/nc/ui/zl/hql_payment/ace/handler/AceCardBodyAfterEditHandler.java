package nc.ui.zl.hql_payment.ace.handler;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKey().equals("nupnum")||e.getKey().equals("ncurrentnum")||e.getKey().equals("price")){
			Object obj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nupnum");
	        Object obj2 = e.getBillCardPanel().getBodyValueAt(e.getRow(), "ncurrentnum");
	        Object obj3 = e.getBillCardPanel().getBodyValueAt(e.getRow(), "price");
			if(obj != null && obj2 != null){
				double up = new Double(obj.toString());
				double current = new Double(obj2.toString());
				if(current < up){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "本期用量不能小于上期用量！");
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
					return;
				}
				e.getBillCardPanel().setBodyValueAt(new UFDouble(current-up), e.getRow(), "nrealnum");
				if(obj3 != null){
					double price = new Double(obj3.toString());
					UFDouble jf = new UFDouble((current-up)*price);
					e.getBillCardPanel().setBodyValueAt(jf, e.getRow(), "npaymny");
					Object kdObj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nkdmny");
					Object qcObj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nqcmny");
					Object ycObj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nycmny");
					
					double kd = new Double(kdObj.toString())+new Double(qcObj.toString())-new Double(ycObj.toString());
					if(kd>jf.toDouble()){
						e.getBillCardPanel().setBodyValueAt(new UFDouble(jf.toDouble()), e.getRow(), "noffsetmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nysmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nskmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nconfirmed");
					}else {
						e.getBillCardPanel().setBodyValueAt(new UFDouble(kd), e.getRow(), "noffsetmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(jf.toDouble() - kd), e.getRow(), "nysmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nskmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nconfirmed");
					}
				}
			}
		}
		
		if(e.getKey().equals("pk_costproject")||e.getKey().equals("dysdate")){
			
			if(e.getRow()==0){
				Object costproject = e.getBillCardPanel().getBillModel().getValueObjectAt(0, "pk_costproject");
				Object ysdate = e.getBillCardPanel().getBillModel().getValueObjectAt(0, "dysdate");
				for(int j=0;j<e.getBillCardPanel().getBillModel().getRowCount();j++){
					e.getBillCardPanel().getBillModel().setValueAt(costproject, j, "pk_costproject");
					e.getBillCardPanel().getBillModel().setValueAt(ysdate, j, "dysdate");
					qryczmny(e,j);
				}
			}
			else{
			
			Object costproject = e.getBillCardPanel().getBodyValueAt(e.getRow(), "pk_costproject");

			Object customer = getColvalue(e.getBillCardPanel().getBillModel().getValueObjectAt(e.getRow(), "pk_customer"));
			if(customer==null){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "请先选择客户！");
				e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
				return;
			}
			Object project = e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			if(project==null){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "请先填写表头项目信息！");
				e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
				return;
			}
			Object ysdate = e.getBillCardPanel().getBodyValueAt(e.getRow(), "dysdate");

			try {
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(
						IUAPQueryBS.class);
				String sql_approve = "select count(*) from zl_payment where nvl(dr,0)=0 and " +
					             "pk_project = '"+project+"' and vbillstatus <> 1 and pk_payment in (select pk_payment " +
					             "from zl_payment_b where nvl(dr,0)=0 and pk_customer = '"+customer+"' and pk_costproject ='" +
					             costproject + "')";
			
				int appcount = (Integer) iQ.executeQuery(sql_approve, new ColumnProcessor());
				Object pk=e.getBillCardPanel().getHeadItem("pk_payment").getValueObject();
				if((pk==null&&appcount > 0)||(pk!=null&&appcount>1)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "当前收费项目下存在未审批的水电费预缴单，请检查！");
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
					return;
				}
				if(costproject!=null&&ysdate!=null){
					String sql_qc = "select sum(restmoney) sumqc from zl_sgmoney where nvl(dr,0)=0 and restmoney > 0 and payproject = '"+costproject
							+"' and khname = '"+customer+"' and pk_project = '" +project+"' and liststate = 1";
					String sql_pre = "select sum(nskmny) sumpre from zl_gather_b where nvl(dr,0)=0 and pk_gather in (select pk_gather " +
							"from zl_gather where nvl(dr,0)=0 and dbilldate <= '"+ysdate+"') and vdef1 in (select pk_prepayment_b from " +
							"zl_prepayment_b where nvl(dr,0)=0 and nskmny > 0 and pk_customer = '"+customer+"' and pk_costproject = '"
							+costproject+"' and dysdate <= '"+ysdate+"' and pk_prepayment in (select pk_prepayment from zl_prepayment where " +
							"nvl(dr,0)=0 and pk_project = '" +project+"' and vbillstatus = 1))";
					Object obj = iQ.executeQuery(sql_qc, new ColumnProcessor());
					Object obj2 = iQ.executeQuery(sql_pre, new ColumnProcessor());
					double sum = 0;
					e.getBillCardPanel().setBodyValueAt(new UFDouble(obj == null?"0":obj.toString()), e.getRow(), "nqcmny");
					sum += new Double(obj == null?"0":obj.toString());
					e.getBillCardPanel().setBodyValueAt(new UFDouble(obj2 == null?"0":obj2.toString()), e.getRow(), "nkdmny");
					sum += new Double(obj2 == null?"0":obj2.toString());
					String sql_pay = "select sum(noffsetmny) sumno from zl_payment_b where nvl(dr,0)=0 and pk_customer = '"+customer+"' and " +
							"pk_costproject = '"+costproject+"' and pk_payment in (select pk_payment from zl_payment where pk_project = '" +project+
							"' and vbillstatus = 1) order by pk_paymentb";
					Object obj3 = iQ.executeQuery(sql_pay, new ColumnProcessor());
					double dc = new Double(obj3 == null?"0":obj3.toString());
					if(sum < dc){
						dc = sum;
					}
					e.getBillCardPanel().setBodyValueAt(new UFDouble(dc), e.getRow(), "nycmny");
					
				}else{
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), "nqcmny");
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), "nkdmny");
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), "nycmny");
				}
				
				Object obj4 = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nupnum");
		        Object obj5 = e.getBillCardPanel().getBodyValueAt(e.getRow(), "ncurrentnum");
		        Object obj6 = e.getBillCardPanel().getBodyValueAt(e.getRow(), "price");
				if(obj4 != null && obj5 != null){
					double up = new Double(obj4.toString());
					double current = new Double(obj5.toString());
					if(current < up){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "本期用量不能小于上期用量！");
						return;
					}
					e.getBillCardPanel().setBodyValueAt(new UFDouble(current-up), e.getRow(), "nrealnum");
					if(obj6 != null){
						double price = new Double(obj6.toString());
						UFDouble jf = new UFDouble((current-up)*price);
						e.getBillCardPanel().setBodyValueAt(jf, e.getRow(), "npaymny");
						Object kdObj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nkdmny");
						Object qcObj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nqcmny");
						Object ycObj = e.getBillCardPanel().getBodyValueAt(e.getRow(), "nycmny");
						
						double kd = new Double(kdObj.toString())+new Double(qcObj.toString())-new Double(ycObj.toString());
						if(kd>jf.toDouble()){
							e.getBillCardPanel().setBodyValueAt(new UFDouble(jf.toDouble()), e.getRow(), "noffsetmny");
							e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nysmny");
							e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nskmny");
						}else {
							e.getBillCardPanel().setBodyValueAt(new UFDouble(kd), e.getRow(), "noffsetmny");
							e.getBillCardPanel().setBodyValueAt(new UFDouble(jf.toDouble() - kd), e.getRow(), "nysmny");
							e.getBillCardPanel().setBodyValueAt(new UFDouble(0), e.getRow(), "nskmny");
						}
					}
				}
				
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}}
		
		if(e.getKey().equals("dysdate")){
			try {
				if(e.getValue() != null){
						IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
						String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
							    	"nvl(dr,0)=0 and (begindate <= '"+e.getValue()+"' and enddate >= '"+e.getValue()+"')";
						Object pk = iQ.executeQuery(sql, new ColumnProcessor());
						if(pk == null){
								return;
						}
						e.getBillCardPanel().setBodyValueAt(pk, e.getRow(), "caccountperiod");
				}else{
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), "caccountperiod");
				}
			
				e.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
				e.getBillCardPanel().stopEditing();
			} catch (BusinessException e1) {
					e1.printStackTrace();
			}
		}
		
		if(e.getKey().equals("pk_customer")){
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			String []ss=ref.getRefPKs();
			List<String> list=new  ArrayList<String>();
			for(String s:ss){
				list.add(s);
			}
			for(int a=0;a<e.getBillCardPanel().getBillModel().getRowCount()-1;a++){
				if(list.contains(getColvalue(e.getBillCardPanel().getBillModel().getValueObjectAt(a, e.getKey())))){
					MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "包含界面已有数据，请检查！");
					e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), e.getKey());
					return;
				}
			}
				if(ref.getRefPKs()!=null && ref.getRefPKs().length>0){
					e.getBillCardPanel().getBillModel().addLine(ss.length-1);
					for(int i=e.getRow();i<e.getBillCardPanel().getBillModel().getRowCount();i++){
						e.getBillCardPanel().getBillModel().setValueAt(ss[i-e.getRow()], i, e.getKey());	
						e.getBillCardPanel().getBillModel().setCellEditable(i, e.getKey(), false);
						e.getBillCardPanel().getBillModel().setValueAt((i+1)*10, i, "rowno");
					}
			}
				e.getBillCardPanel().getBillModel().loadLoadRelationItemValue();	
		}
	}
	
	private static Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}
	
	public void qryczmny(CardBodyAfterEditEvent e,Integer i){
		Object costproject = e.getBillCardPanel().getBodyValueAt(i, "pk_costproject");

		Object customer = getColvalue(e.getBillCardPanel().getBillModel().getValueObjectAt(i, "pk_customer"));
		if(customer==null){
			MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "请先选择客户！");
			e.getBillCardPanel().setBodyValueAt(null,i, e.getKey());
			return;
		}
		Object project = e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
		if(project==null){
			MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "请先填写表头项目信息！");
			e.getBillCardPanel().setBodyValueAt(null, i, e.getKey());
			return;
		}
		Object ysdate = e.getBillCardPanel().getBodyValueAt(i, "dysdate");

		try {
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(
					IUAPQueryBS.class);
			String sql_approve = "select count(*) from zl_payment where nvl(dr,0)=0 and " +
				             "pk_project = '"+project+"' and vbillstatus <> 1 and pk_payment in (select pk_payment " +
				             "from zl_payment_b where nvl(dr,0)=0 and pk_customer = '"+customer+"' and pk_costproject ='" +
				             costproject + "')";
		
			int appcount = (Integer) iQ.executeQuery(sql_approve, new ColumnProcessor());
			Object pk=e.getBillCardPanel().getHeadItem("pk_payment").getValueObject();
			if((pk==null&&appcount > 0)||(pk!=null&&appcount>1)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "当前收费项目下存在未审批的水电费预缴单，请检查！");
				e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
				return;
			}
			if(ysdate!=null){
				String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
					    	"nvl(dr,0)=0 and (begindate <= '"+ysdate+"' and enddate >= '"+ysdate+"')";
				Object pka = iQ.executeQuery(sql, new ColumnProcessor());
				if(pka == null){
						return;
				}
				e.getBillCardPanel().setBodyValueAt(pka, i, "caccountperiod");
			}
			
			
			if(costproject!=null&&ysdate!=null){
				String sql_qc = "select sum(restmoney) sumqc from zl_sgmoney where nvl(dr,0)=0 and restmoney > 0 and payproject = '"+costproject
						+"' and khname = '"+customer+"' and pk_project = '" +project+"' and liststate = 1";
				String sql_pre = "select sum(nskmny) sumpre from zl_gather_b where nvl(dr,0)=0 and pk_gather in (select pk_gather " +
						"from zl_gather where nvl(dr,0)=0 and dbilldate <= '"+ysdate+"') and vdef1 in (select pk_prepayment_b from " +
						"zl_prepayment_b where nvl(dr,0)=0 and nskmny > 0 and pk_customer = '"+customer+"' and pk_costproject = '"
						+costproject+"' and dysdate <= '"+ysdate+"' and pk_prepayment in (select pk_prepayment from zl_prepayment where " +
						"nvl(dr,0)=0 and pk_project = '" +project+"' and vbillstatus = 1))";
				Object obj = iQ.executeQuery(sql_qc, new ColumnProcessor());
				Object obj2 = iQ.executeQuery(sql_pre, new ColumnProcessor());
				double sum = 0;
				e.getBillCardPanel().setBodyValueAt(new UFDouble(obj == null?"0":obj.toString()), i, "nqcmny");
				sum += new Double(obj == null?"0":obj.toString());
				e.getBillCardPanel().setBodyValueAt(new UFDouble(obj2 == null?"0":obj2.toString()), i, "nkdmny");
				sum += new Double(obj2 == null?"0":obj2.toString());
				String sql_pay = "select sum(noffsetmny) sumno from zl_payment_b where nvl(dr,0)=0 and pk_customer = '"+customer+"' and " +
						"pk_costproject = '"+costproject+"' and pk_payment in (select pk_payment from zl_payment where pk_project = '" +project+
						"' and vbillstatus = 1) order by pk_paymentb";
				Object obj3 = iQ.executeQuery(sql_pay, new ColumnProcessor());
				double dc = new Double(obj3 == null?"0":obj3.toString());
				if(sum < dc){
					dc = sum;
				}
				e.getBillCardPanel().setBodyValueAt(new UFDouble(dc), i, "nycmny");
				
			}else{
				e.getBillCardPanel().setBodyValueAt(null, i, "nqcmny");
				e.getBillCardPanel().setBodyValueAt(null, i, "nkdmny");
				e.getBillCardPanel().setBodyValueAt(null, i, "nycmny");
			}
			
			Object obj4 = e.getBillCardPanel().getBodyValueAt(i, "nupnum");
	        Object obj5 = e.getBillCardPanel().getBodyValueAt(i, "ncurrentnum");
	        Object obj6 = e.getBillCardPanel().getBodyValueAt(i, "price");
			if(obj4 != null && obj5 != null){
				double up = new Double(obj4.toString());
				double current = new Double(obj5.toString());
				if(current < up){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "本期用量不能小于上期用量！");
					return;
				}
				e.getBillCardPanel().setBodyValueAt(new UFDouble(current-up), i, "nrealnum");
				if(obj6 != null){
					double price = new Double(obj6.toString());
					UFDouble jf = new UFDouble((current-up)*price);
					e.getBillCardPanel().setBodyValueAt(jf, i, "npaymny");
					Object kdObj = e.getBillCardPanel().getBodyValueAt(i, "nkdmny");
					Object qcObj = e.getBillCardPanel().getBodyValueAt(i, "nqcmny");
					Object ycObj = e.getBillCardPanel().getBodyValueAt(i, "nycmny");
					
					double kd = new Double(kdObj.toString())+new Double(qcObj.toString())-new Double(ycObj.toString());
					if(kd>jf.toDouble()){
						e.getBillCardPanel().setBodyValueAt(new UFDouble(jf.toDouble()),i, "noffsetmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), i, "nysmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0), i, "nskmny");
					}else {
						e.getBillCardPanel().setBodyValueAt(new UFDouble(kd), i, "noffsetmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(jf.toDouble() - kd), i, "nysmny");
						e.getBillCardPanel().setBodyValueAt(new UFDouble(0),i, "nskmny");
					}
				}
			}
			
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
	}
}
