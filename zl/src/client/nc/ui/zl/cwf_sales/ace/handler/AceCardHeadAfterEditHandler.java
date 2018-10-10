package nc.ui.zl.cwf_sales.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{
private ShowUpableBillForm billForm;
	

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		if(e.getKey().equals("username")){
			
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			
			try {
				
				String getcount = "select count(*) from zl_sales where nvl(dr,0)=0 and username = '"+e.getValue()+"'";
				int count = (Integer) iQ.executeQuery(getcount, new ColumnProcessor());
				if(count>0){
						MessageDialog.showHintDlg(billForm, "提示", "该业务员权限控制下人员已存在！");
						return;
				}
				
				String sql="select name from bd_psndoc where nvl(dr,0)=0 and pk_psndoc='"+e.getValue()+"'";
				Object obj=iQ.executeQuery(sql, new ColumnProcessor());
				String name=obj==null?"":obj.toString();
				billForm.getBillCardPanel().setHeadItem("name", name);
				//e.getBillCardPanel().getBillModel().setValueAt(name, "username");
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

}
