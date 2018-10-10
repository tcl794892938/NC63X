package nc.ui.zl.costproject.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.cwf_costproject.CostprojectVO;

public class editAction extends nc.ui.pubapp.uif2app.actions.EditAction{
	
	private ShowUpableBillForm bill;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		
		CostprojectVO cvo = (CostprojectVO)getModel().getSelectedData();
		//��ȡ�ñ����¼�����
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql = "select count(*) from zl_feescale where chargeitem = '"+cvo.getPk_costproject()+"'";
		
		int count = (Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count > 0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "��ǰ�շ���Ŀ�ѱ����ã������޸ģ�����");
			return;
		}
		String sql_2 = "select  code from zl_costproject where pk_vid='"+cvo.getPk_costproject()+"' and nvl(dr,0)=0";
		
		Object soncode= iQ.executeQuery(sql_2, new ColumnProcessor());
		//�ϼ��޸��ж�
		if(soncode!=null){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�����¼��������޸ı��룡����");
			return;
		}
		
		//�費�ɱ༭
		BillCardPanel panel = bill.getBillCardPanel();
		
		Object pk_vid=cvo.getPk_vid();
		String vid=pk_vid==null?"":pk_vid.toString();
		if(!vid.equals("")){
			panel.getHeadItem("pk_incomepro").setEdit(false);
			panel.getHeadItem("pk_costtype").setEdit(false);
			panel.getHeadItem("roundtype").setEdit(false);
		}else{
			panel.getHeadItem("pk_incomepro").setEdit(true);
			panel.getHeadItem("pk_costtype").setEdit(true);
			panel.getHeadItem("roundtype").setEdit(true);
		}
		
		
		super.doAction(e);
	}

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}


}
