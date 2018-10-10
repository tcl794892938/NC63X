package nc.ui.zl.ly_businesssource.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		//System.out.println("123");
		if(e.getKey().equals("procode")){
			try {
				if(e.getRow()>0){
					for(int i=0;i<e.getRow();i++){
						Object procode=e.getBillCardPanel().getBillModel().getValueAt(i, "procode");
						Object procode1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "procode");
						if(getStgObj(procode).equals(getStgObj(procode1))){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示",
									"项目编码重复请重新选择！");
							e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "procode");
							e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "proname");
							e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "remarks");
							return;
						}
					}
				}
				String sql="select name from zl_project where nvl(dr,0)=0 and pk_project='"+e.getValue()+"'";
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Object obj=iQ.executeQuery(sql, new ColumnProcessor());
				String name=obj==null?"":obj.toString();
				e.getBillCardPanel().getBillModel().setValueAt(name, e.getRow(), "proname");
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			/*UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem("salesman").getComponent();
			ref.getRefModel();*/
		}
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
