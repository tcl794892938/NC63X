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
import nc.ui.zl.tcl_contract.ace.config.HTZqdetailDlg;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;
import nc.vo.zl.tcl_contract.ContractZqfyVO;
import nc.vo.zl.tcl_contract.ContractZqfycfVO;

public class QueryZqfyAction extends NCAction {
	
	private static final long serialVersionUID = -4869202750729279045L;
	
	public QueryZqfyAction(){
		this.setCode("queryzqfy");
		this.setBtnName("每期应收物业费");
	}
	
	private BillManageModel model;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		AggContractVO aggvo=(AggContractVO)obj;
		ContractZqfycfVO[] zqvos=aggvo.getChildZqfycfVO();
		if(zqvos==null||zqvos.length<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "无业务拆分数据！");
			return ;
		}
		
		
		//查询明细
		String sql="select c.pk_customer,c.pk_costproject,c.dstartdate,c.denddate,c.pk_month,sum(nvl(c.nfymny,0)) nfymny," +
				"sum(nvl(c.nyhmny,0)) nyhmny,sum(nvl(c.nysmny,0)) nysmny,sum(nvl(c.nskmny,0)) nskmny,sum(nvl(c.nnotaxmny,0)) nnotaxmny," +
				"sum(nvl(c.ntaxmny,0)) ntaxmny  " +
				"from zl_contract_zqfycf c where nvl(c.dr,0)=0 and c.pk_contract='"+aggvo.getParentVO().getPk_contract()+"' " +
				"group by c.pk_customer,c.pk_costproject,c.dstartdate,c.denddate,c.pk_month order by c.dstartdate";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String, Object>> maplist=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
		
		HTZqdetailDlg dlg=new HTZqdetailDlg(model.getContext().getEntranceUI());
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
