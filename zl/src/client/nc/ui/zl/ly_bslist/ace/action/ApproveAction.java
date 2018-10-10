package nc.ui.zl.ly_bslist.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IVOPersistence;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.zl.lm_customer.Customer_wxfwVO;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.zl.ly_bslist.BslistVO;

public class ApproveAction extends ApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		
		//System.out.println("123");
		
		Object obj=getModel().getSelectedData();
		AggBslistVO aggvo=(AggBslistVO) obj;
		BslistVO vo=aggvo.getParentVO();
		
		Customer_wxfwVO wxfw=new Customer_wxfwVO();
		wxfw.setBsdj(vo.getListid());
		wxfw.setBsnr(vo.getBxcontent());
		wxfw.setPk_customer(vo.getKhname());
		wxfw.setBuildnum(vo.getPk_building());
		wxfw.setRoomname(vo.getFcname());
		
		IVOPersistence ivp = NCLocator.getInstance().lookup(
				IVOPersistence.class);
		ivp.insertVO(wxfw);
		
	}

}
