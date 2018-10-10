package nc.ui.zl.ld_housesource.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.ld_housesource.ace.config.CFDlg;
import nc.ui.zl.ld_housesource.ace.config.HBDlg;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class CFAction extends NCAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2280159834223077985L;
	
	public BillManageModel model;
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public CFAction() {
		this.setCode("cfaction");
		this.setBtnName("²ð·Ö");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		AggHousesourceVO aggvo=(AggHousesourceVO) model.getSelectedData();
		
		new CFDlg(model.getContext().getEntranceUI(), aggvo);
		String sql="select * from zl_housesource where nvl(zl_housesource.dr,0)=0 and pk_org='"+aggvo.getParentVO().getPk_org()+"'" +
				" and projectname='"+aggvo.getParentVO().getProjectname()+"' and buildname='"+aggvo.getParentVO().getBuildname()+"'"
				+ "order by to_number(floorn) desc,to_number(unit) asc,to_number(roomnumber) asc";

		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<HousesourceVO> clist=(List<HousesourceVO>)iQ.executeQuery(sql, new BeanListProcessor(HousesourceVO.class));
		List<AggHousesourceVO> aggvos=new ArrayList<AggHousesourceVO>();
		for(HousesourceVO vo:clist){
			AggHousesourceVO aggvo2=new AggHousesourceVO();
			aggvo2.setParentVO(vo);
			aggvos.add(aggvo2);
		}
		getModel().initModel(aggvos.toArray(new AggHousesourceVO[0]));
	}

	@Override
	protected boolean isActionEnable() {
		Object[] obj=model.getSelectedOperaDatas();
		if(obj==null){
			return false;
		}
		if(obj!=null&&obj.length>1){
			return false;
		}
		for(Object ob:obj){
			if(((AggHousesourceVO)ob).getParentVO().getHousestate()!=0){
				return false;
			}
		}
		return true;
	}
}
