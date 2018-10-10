package nc.ui.zl.hql_customertype_org.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.BusinessException;
import nc.vo.zl.hql_customertype.CustomertypeVO;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		CustomertypeVO cvo=(CustomertypeVO)getModel().getSelectedData();
		if(cvo==null){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", 
	                  "在组织级不可以新增集团级节点！");
				return;
		}
		if(cvo!=null){
			String sql = "select count(vdef1) from zl_customertype where pk_parentid='"+cvo.getPk_customertype()+"' and nvl(dr,0)=0  and vdef1='0'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			int count = (Integer)iQ.executeQuery(sql, new ColumnProcessor());
			
			if(count>0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", 
		                  "下级存在集团级项目，不可以新增组织级！");
					return;
			}
		}
		
		
		super.doAction(e);
	}
	/*@Override
	protected boolean isActionEnable() {
		CustomertypeVO cvo=(CustomertypeVO)getModel().getSelectedData();
		if(cvo==null){
			return false;
		}
		
		String sql = "select count(vdef1) from zl_customertype where pk_parentid='"+cvo.getPk_customertype()+"' and nvl(dr,0)=0  and vdef1='0'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count=0;
		try {
			count = (Integer)iQ.executeQuery(sql, new ColumnProcessor());
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(count==0){
			return true;
		}else{
			return false;
		}
	}*/
}
