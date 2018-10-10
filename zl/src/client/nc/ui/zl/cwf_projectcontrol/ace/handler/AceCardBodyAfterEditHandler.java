package nc.ui.zl.cwf_projectcontrol.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		if(e.getKey().equals("usercode")){
			
			try {
			String sql="select name from bd_psndoc where nvl(dr,0)=0 and pk_psndoc='"+e.getValue()+"'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Object obj=iQ.executeQuery(sql, new ColumnProcessor());
				String name=obj==null?"":obj.toString();
				e.getBillCardPanel().getBillModel().setValueAt(name, e.getRow(), "username");
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
		
	}

}
