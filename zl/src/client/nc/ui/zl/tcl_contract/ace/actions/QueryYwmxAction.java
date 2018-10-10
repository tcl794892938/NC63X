package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.tcl_contract.ace.config.HTYwdetailDlg;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;

public class QueryYwmxAction extends NCAction {
	
	private static final long serialVersionUID = -4869202750729279045L;
	
	public QueryYwmxAction(){
		this.setCode("queryywmx");
		this.setBtnName("ÿ��Ӧ�����");
	}
	
	private BillManageModel model;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "��ʾ", "��ѡ��һ�����ݣ�");
			return ;
		}
		
		AggContractVO aggvo=(AggContractVO)obj;
		ContractYwcfVO[] ywvos=aggvo.getChildYwcfVO();
		if(ywvos==null||ywvos.length<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "��ʾ", "��ҵ�������ݣ�");
			return ;
		}
		
		Object obj2=12;
		Integer it=(Integer) obj2;
		
		//��ѯ��ϸ
		String sql="select c.pk_customer,c.pk_costproject,c.dstartdate,c.denddate,c.pk_month,sum(nvl(c.nqzmny,0)) nqzmny," +
				"sum(nvl(c.nyhmny,0)) nyhmny,sum(nvl(c.nysmny,0)) nysmny,sum(nvl(c.nskmny,0)) nskmny " +
				"from zl_contract_ywcf c where nvl(c.dr,0)=0 and c.pk_contract='"+aggvo.getParentVO().getPk_contract()+"' " +
				"group by c.pk_customer,c.pk_costproject,c.dstartdate,c.denddate,c.pk_month order by c.dstartdate";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String, Object>> maplist=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
		
		HTYwdetailDlg dlg=new HTYwdetailDlg(model.getContext().getEntranceUI());
		dlg.initValue(maplist);
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		
		return true;
	}
	
}
