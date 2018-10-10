package nc.ui.zl.ld_kpregister.ace.actions;

import java.awt.event.ActionEvent;


import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;

import nc.jdbc.framework.processor.BeanProcessor;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;

public class ApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		super.doAction(e);
		
		//”¶ ’µ•
		/*IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String rec_sql = "select * from zl_recbill where nvl(dr,0)=0 and pk_recbill='"+aggvo.getParentVO().getVsrcid()+"'";
		RecbillVO recbill = (RecbillVO)iQ.executeQuery(rec_sql, new BeanProcessor(RecbillVO.class));
		recbill.setInvoiceno(aggvo.getParentVO().getKpcode());
		recbill.setInvoicemoney(aggvo.getParentVO().getKpmoney());
		IVOPersistence ivp = NCLocator.getInstance().lookup(
				IVOPersistence.class);
		ivp.updateVO(recbill);*/
	}
}
