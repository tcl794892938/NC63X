package nc.ui.zl.costproject_org.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.zl.cwf_costproject.CostprojectVO;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction {
	private ShowUpableBillForm bill;
	public ShowUpableBillForm getBill() {
		return bill;
	}
	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		CostprojectVO cvo = (CostprojectVO)getModel().getSelectedData();
		if(cvo==null){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", 
	                  "在组织级不可以新增集团级节点！");
			return ;
		}
		if(cvo!=null){
			String sql = "select  count(vdef1) from zl_costproject where pk_vid='"+cvo.getPk_costproject()+"' and nvl(dr,0)=0  and vdef1='0'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			int count = (Integer)iQ.executeQuery(sql, new ColumnProcessor());
			
			if(count>0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", 
		                  "下级存在集团级项目，不可以新增组织级！");
					return;
			}
		}
		
		//设不可编辑
		BillCardPanel panel = bill.getBillCardPanel();
		
		panel.getHeadItem("pk_incomepro").setEdit(false);
		panel.getHeadItem("pk_costtype").setEdit(false);
		panel.getHeadItem("roundtype").setEdit(false);
		
		super.doAction(e);
	}
	
	/*@Override
	protected boolean isActionEnable() {
		CostprojectVO cvo = (CostprojectVO)getModel().getSelectedData();
		if(cvo==null){
			return false;
		}
		
		String sql = "select  count(vdef1) from zl_costproject where pk_vid='"+cvo.getPk_costproject()+"' and nvl(dr,0)=0  and vdef1='0'";
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
