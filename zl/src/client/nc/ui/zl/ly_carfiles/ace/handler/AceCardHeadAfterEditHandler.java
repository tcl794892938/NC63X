package nc.ui.zl.ly_carfiles.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	private ShowUpableBillForm billform;
	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		//System.out.println("123");
		if(e.getKey().equals("carnumber")){
			try {
				Object carnumber=billform.getBillCardPanel().getHeadItem("carnumber").getValueObject();
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String sql="select pk_carfiles from zl_carfiles where nvl(dr,0)=0 and carnumber='"+getStgObj(carnumber)+"'";
				Object pk=iQ.executeQuery(sql, new ColumnProcessor());
				if(!getStgObj(pk).equals("")){
					MessageDialog.showHintDlg(billform, "提示", "该车牌号已经存在，请检查后重新输入！");
					e.getBillCardPanel().setHeadItem("carnumber", null);
					return;
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getKey().equals("pk_project")){
			if(e.getOldValue()==null||e.getOldValue().equals("")){
				return;
			}else{
				if(!e.getOldValue().equals(e.getValue())){
					e.getBillCardPanel().setHeadItem("khname", null);
				}
			}
		}
		
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}