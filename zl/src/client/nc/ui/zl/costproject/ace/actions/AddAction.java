package nc.ui.zl.costproject.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.cwf_costproject.CostprojectVO;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction{
	ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBil1() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
	
		CostprojectVO cvo = (CostprojectVO)getModel().getSelectedData();
		if(cvo == null){
			BillCardPanel panel = bill.getBillCardPanel();
			
			panel.getHeadItem("pk_incomepro").setEdit(true);
			panel.getHeadItem("pk_costtype").setEdit(true);
			panel.getHeadItem("roundtype").setEdit(true);
		}
		if(cvo!=null){
			
			String sql = "select  count(vdef1) from zl_costproject where pk_vid='"+cvo.getPk_costproject()+"' and nvl(dr,0)=0  and vdef1='1'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Integer count = (Integer)iQ.executeQuery(sql, new ColumnProcessor());
			if(count!=0){
				MessageDialog.showHintDlg(bill, "��ʾ", "�¼�������֯����Ŀ���������ӣ�����");
				return;
			}
			
			//�費�ɱ༭
			BillCardPanel panel = bill.getBillCardPanel();
			
			panel.getHeadItem("pk_incomepro").setEdit(false);
			panel.getHeadItem("pk_costtype").setEdit(false);
			panel.getHeadItem("roundtype").setEdit(false);
		}
		// TODO �Զ����ɵķ������
		super.doAction(e);
	}
	

}
