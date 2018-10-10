package nc.ui.zl.ld_parkcontract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.zl.ld_housesource.HousesourceVO;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractBVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.lm_customer.Customer_fcxxVO;

public class UnApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggParkcontractVO aggvo = (AggParkcontractVO) getModel().getSelectedData();
		ParkcontractVO vo = aggvo.getParentVO();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		ParkcontractBVO[] bvo = (ParkcontractBVO[]) aggvo.getChildren(ParkcontractBVO.class);
		
		String sql2 = "select count(*) from zl_carconedit where nvl(dr,0)=0 and vsrcbid ='"+vo.getPk_parkcontract()+"'";
		int count2 = (Integer) iQ.executeQuery(sql2, new ColumnProcessor());
		if(count2>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已生被参照，不可取消审批！");
				return;
		}

		super.doAction(e);
		//回写房源
		String sql_house = "select * from zl_housesource h where nvl(dr,0)=0 and projectname='"+vo.getPk_project()+"'";
		List<HousesourceVO> hList = (List<HousesourceVO>)iQ.executeQuery(sql_house, new BeanListProcessor(HousesourceVO.class));
		if(bvo.length>0){
			for(int i=0;i<bvo.length;i++){
				String pk_house = bvo[i].getParknum();
				for(int j=0;j<hList.size();j++){
					if(hList.get(j).getPk_housesource().equals(pk_house)){
						hList.get(j).setHousestate(0);
					}
				}
			}
			IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
			ivp.updateVOList(hList);
		}
		//回写客户信息中心
		String sql_cus = "select * from zl_customer_fcxx f where nvl(dr,0)=0 and f.pk_customer='"+vo.getPk_customer()+"'";
		List<Customer_fcxxVO> cList = (List<Customer_fcxxVO>)iQ.executeQuery(sql_cus, new BeanListProcessor(Customer_fcxxVO.class));
		List<Customer_fcxxVO> cuList = new ArrayList<Customer_fcxxVO>();
		//Customer_fcxxVO[] hvo = new Customer_fcxxVO[cList.size()];
		
		for(int i=0;i<cList.size();i++){
			for(int j=0;j<bvo.length;j++){
				if(cList.get(i).getFcname().equals(bvo[j].getParknum())){
					cuList.add(cList.get(i));
					
				}
			}
			
		}
		IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
		ivp.deleteVOList(cuList);
	}
}
