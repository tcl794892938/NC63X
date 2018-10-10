package nc.ui.zl.ld_carcontract.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;

public class DeleteAction extends DeleteScriptAction {
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj=getModel().getSelectedData();
		AggCarcontractVO aggvo=(AggCarcontractVO)obj;
		CarcontractVO vo=aggvo.getParentVO();
		Object vsrcbid=vo.getVsrcbid();
		if(!getStgObj(vsrcbid).equals("")){
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql="select * from zl_carfiles where nvl(dr,0)=0 and pk_carfiles='"+getStgObj(vsrcbid)+"'";
			CarFilesVO cvo=(CarFilesVO) iQ.executeQuery(sql, new BeanProcessor(CarFilesVO.class));
			Object vdef1=cvo.getVdef1();
			if(!getStgObj(vdef1).equals("")){
				cvo.setVdef1(null);
				IVOPersistence ivp = NCLocator.getInstance().lookup(
						IVOPersistence.class);
				ivp.updateVO(cvo);
			}
			
		}
		
		super.doAction(e);
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
