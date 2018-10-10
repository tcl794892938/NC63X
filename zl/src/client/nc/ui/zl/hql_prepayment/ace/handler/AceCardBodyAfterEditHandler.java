package nc.ui.zl.hql_prepayment.ace.handler;

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
import nc.vo.am.manager.AccperiodVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根

		if(e.getKey().equals("nysmny")){
			int row = e.getRow();
			Object ysObj = e.getBillCardPanel().getBodyValueAt(row, e.getKey());
			if(ysObj != null){
				e.getBillCardPanel().setBodyValueAt(new UFDouble(0), row, "nskmny");
				e.getBillCardPanel().setBodyValueAt(new UFDouble(0), row, "nconfirmed");
				
			}
		}
		
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
		if(e.getKey().equals("pk_costproject")||e.getKey().equals("dysdate")){
			if(e.getRow()==0){
				try {
				Object costproject = e.getBillCardPanel().getBillModel().getValueObjectAt(0, "pk_costproject");
				Object ysdate = e.getBillCardPanel().getBillModel().getValueObjectAt(0, "dysdate");
				if(ysdate!=null){
					IUAPQueryBS iQ = NCLocator.getInstance().lookup(
							IUAPQueryBS.class);
					String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
						    	"nvl(dr,0)=0 and (begindate <= '"+ysdate+"' and enddate >= '"+ysdate+"')";
					Object pka = iQ.executeQuery(sql, new ColumnProcessor());
					if(pka == null){
							return;
					}
					e.getBillCardPanel().setBodyValueAt(pka, 0, "caccountperiod");
				}
				Object caccountperiod = e.getBillCardPanel().getBillModel().getValueObjectAt(0, "caccountperiod");
				for(int j=0;j<e.getBillCardPanel().getBillModel().getRowCount();j++){
					e.getBillCardPanel().getBillModel().setValueAt(costproject, j, "pk_costproject");
					e.getBillCardPanel().getBillModel().setValueAt(ysdate, j, "dysdate");
					e.getBillCardPanel().getBillModel().setValueAt(caccountperiod, j, "caccountperiod");
				}
				} catch (BusinessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
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
}
