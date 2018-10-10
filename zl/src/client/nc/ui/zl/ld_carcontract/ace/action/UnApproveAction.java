package nc.ui.zl.ld_carcontract.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractBVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.lm_customer.Customer_carVO;

public class UnApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggCarcontractVO aggvo = (AggCarcontractVO) getModel().getSelectedData();
		CarcontractVO vo = aggvo.getParentVO();
		CarcontractBVO[] bvo = (CarcontractBVO[]) aggvo.getChildren(CarcontractBVO.class);
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql2 = "select count(*) from zl_carconedit where nvl(dr,0)=0 and vsrcbid ='"+vo.getPk_carcontract()+"'";
		int count2 = (Integer) iQ.executeQuery(sql2, new ColumnProcessor());
		if(count2>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已生被参照，不可取消审批！");
				return;
		}
		
		/*String sql3 = "select count(*) from zl_confirmation where nvl(dr,0)=0 and vsrcid ='"+vo.getPk_carcontract()+"'";
		int count3 = (Integer) iQ.executeQuery(sql3, new ColumnProcessor());
		if(count3>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已生成待收入确认单，不可取消！");
				return;
		}*/
		super.doAction(e);
		//推客户信息中心

		Customer_carVO carvo = new Customer_carVO();
		List<Customer_carVO> carList = new ArrayList<Customer_carVO>();
		for(int i=0;i<bvo.length;i++){
			carvo = new Customer_carVO();
			carvo.setCarnum(bvo[i].getPlatenum());
			carvo.setHtbegindate(vo.getStartdate());
			carvo.setHtenddate(vo.getEnddate());
			carvo.setPk_customer(bvo[i].getPk_customer());
			carvo.setDr(0);
			carList.add(carvo);
		}
		IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
		ivp.deleteVOList(carList);
	}
}
