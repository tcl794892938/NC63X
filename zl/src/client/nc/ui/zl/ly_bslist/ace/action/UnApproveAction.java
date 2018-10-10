package nc.ui.zl.ly_bslist.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.zl.lm_customer.Customer_wxfwVO;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.zl.ly_bslist.BslistVO;

public class UnApproveAction extends UNApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();
		AggBslistVO aggvo=(AggBslistVO)obj;
		BslistVO zvo=aggvo.getParentVO();
		Object pk_bslist=zvo.getPk_bslist();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql1="select pk_zylist from zl_zylist where nvl(dr,0)=0 and vsrcid='"+getStgObj(pk_bslist)+"'";
		Object zylist=iQ.executeQuery(sql1, new ColumnProcessor());
		
		if(!getStgObj(zylist).equals("")){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"已生成作业单无法取消审批！");
			return;
		}
		
		IVOPersistence ivp = NCLocator.getInstance().lookup(IVOPersistence.class);
		String sql2="select * from zl_customer_wxfw where nvl(dr,0)=0 and bsdj='"+zvo.getListid()+"'";
		Customer_wxfwVO wxvo=(Customer_wxfwVO) iQ.executeQuery(sql2, new BeanProcessor(Customer_wxfwVO.class));
		ivp.deleteVO(wxvo);
		
		super.doAction(e);
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
