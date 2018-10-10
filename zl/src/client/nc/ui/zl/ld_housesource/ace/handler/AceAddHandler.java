package nc.ui.zl.ld_housesource.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	
	public AbstractAppModel model;
	public AbstractAppModel getModel() {
		return model;
	}
	public void setModel(AbstractAppModel model) {
		this.model = model;
		//model.addAppEventListener(this);
	}
	
	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		Object obj=this.model.getSelectedData();
		if(obj!=null ){
			AggHousesourceVO aggvo=(AggHousesourceVO) obj;
			aggvo.getParentVO().setCreator(null);
			aggvo.getParentVO().setCreationtime(null);
			aggvo.getParentVO().setModifier(null);
			aggvo.getParentVO().setModifiedtime(null);
			aggvo.getParentVO().setPk_housesource(null);
			panel.setBillValueVO(aggvo);
			//model.directlyUpdate(aggvo);
		}
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
		
	}
}
