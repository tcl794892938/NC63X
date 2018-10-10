package nc.ui.zl.costproject.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.vo.zl.tcl_costtype.CosttypeVO;
import nc.ui.pub.bill.BillCardPanel;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		//String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// ��������֯Ĭ��ֵ
		//panel.setHeadItem("pk_org", );
		panel.setHeadItem("pk_group", pk_group);
		//panel.setHeadItem("pk_org", pk_org);
		
		panel.setHeadItem("dbilldate", new UFDate());
		panel.setTailItem("creator", AppContext.getInstance().getPkUser());
		panel.setTailItem("creationtime", new UFDateTime());
		
		/*panel.getHeadItem("pk_org").getComponent().setVisible(false);
		panel.getHeadItem("pk_org").getCaptionLabel().setText("");
*/		//panel.hideHeadItem(new String[]{"code"});
		//panel.setHeadItem("pk_org", "0001A910000000000LRO");
		
		
		if(e.getBillForm().getModel().getSelectedData()!=null){
			CostprojectVO vo=(CostprojectVO)e.getBillForm().getModel().getSelectedData();
			panel.setHeadItem("code", vo.getCode());
			panel.setHeadItem("pk_vid", vo.pk_costproject);
			panel.setHeadItem("pk_incomepro", vo.getPk_incomepro());
			panel.setHeadItem("pk_costtype", vo.getPk_costtype());
			panel.setHeadItem("roundtype", vo.getRoundtype());
		}
	}
}
