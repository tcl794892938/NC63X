package nc.ui.zl.ld_parkcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;

import nc.itf.zl.ILd_parkcontractMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.ld_parkcontract.ace.config.HTHistoryDlg;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;

public class LinkSourceAction extends NCAction{

	public LinkSourceAction(){
		this.setCode("linksource");
		this.setBtnName("����ԭʼ��ͬ");
	}
	
	private BillManageModel model;
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "��ʾ", "��ѡ��һ�����ݣ�");
			return ;
		}
		
		AggParkcontractVO aggvo=(AggParkcontractVO)obj;
		String pk=aggvo.getParentVO().getPk_parkcontract();
		//��ѯԭʼpk
		String sql="select pk_parkcontract from zl_parkcontract where vbilltypecode='H523' and vsrcbid='"+pk+"' " +
				" and nvl(dr,0)=0 and version=0 ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object objpk=iQ.executeQuery(sql, new ColumnProcessor());
		if(objpk==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "��ʾ", "ԭʼ��ͬ��ѯ�쳣����ԭʼ���ݣ�");
			return ;
		}
		ILd_parkcontractMaintain itp=NCLocator.getInstance().lookup(ILd_parkcontractMaintain.class);
		AggParkcontractVO newaggvo=itp.queryCHTbyPK(objpk.toString());
		HTHistoryDlg dlg=new HTHistoryDlg(model.getContext().getEntranceUI());
		dlg.initValue(newaggvo);
	}
	
	@Override
	protected boolean isActionEnable() {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		AggParkcontractVO aggvo=(AggParkcontractVO)obj;
		Integer it=aggvo.getParentVO().getVbillstatus();
		if(it==1){
			return true;
		}
		return false;
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

}
