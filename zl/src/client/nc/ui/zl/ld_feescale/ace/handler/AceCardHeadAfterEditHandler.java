package nc.ui.zl.ld_feescale.ace.handler;

import java.math.BigDecimal;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;


public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	private ShowUpableBillForm billForm;
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e){
		
		if(e.getKey().equals("chargeitem")){
			
			System.out.println("123");
			
			Object  nsd= e.getValue();
			
			String acts =nsd==null?"": nsd.toString();
			
			System.out.println(acts);
			//获取收费标准的舍入方式
			Object objform = null;
			String sql = "select roundtype from zl_costproject where pk_costproject='"+acts+"' and nvl(dr,0)=0";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			try {
				objform= iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			
			int form =Integer.valueOf(objform.toString());
			
			System.out.println(form);
			billForm.getBillCardPanel().setHeadItem("roundform", form);

		}
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}
