package nc.ui.zl.lm_customer.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.ILm_customerMaintain;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.zl.lm_customer.AggCustomerVO;
import nc.vo.zl.lm_customer.CustomerVO;
import nc.vo.zl.ly_pocustomers.PocustomersVO;


public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2928957745692377431L;
	public BillForm billForm;


	public BillForm getBillForm() {
	return billForm;
}

public void setBillForm(BillForm billForm) {
	this.billForm = billForm;
}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		List<AggCustomerVO> agglist=new ArrayList<AggCustomerVO>();
		String exception="";
		for(int i=0;i<obj.length;i++){
			AggCustomerVO aggvo=(AggCustomerVO) obj[i];
			agglist.add(aggvo);
		}
		if(obj.length>500){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "一次删除500张单据！");
			return;
		}
		AggCustomerVO [] aggvos2=agglist.toArray(new AggCustomerVO[0]);
		for(AggCustomerVO aggvo:aggvos2){
			CustomerVO vo=aggvo.getParentVO();
			String sql="select count(*) from zl_contract_house where dr=0 and pk_customer='"+vo.getPk_customer()+"' ";
			Integer a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
			if(a>0){
				agglist.remove(aggvo);
				exception+="客户"+vo.getCustomername()+"已经签订合同房产，";
				continue;
			}
			
			String sql1="select count(*) from zl_mdcontract where dr=0 and pk_customer='"+vo.getPk_customer()+"'";
			Integer a1=(Integer) iQ.executeQuery(sql1, new ColumnProcessor());
			if(a1>0){
				agglist.remove(aggvo);
				exception+="客户"+vo.getCustomername()+"已经签订多种经营合同，";
				continue;
			}
			
			String sql2="select count(*) from zl_carfiles where dr=0 and khname='"+vo.getPk_customer()+"'";
			Integer a2=(Integer)iQ.executeQuery(sql2, new ColumnProcessor());
			if(a2>0){
				agglist.remove(aggvo);
				exception+="客户"+vo.getCustomername()+"已经录入车辆档案，";
				continue;
			}
			
			String sql3="select count(*) from zl_prepayment_b where dr=0 and pk_customer='"+vo.getPk_customer()+"'";
			Integer a3=(Integer)iQ.executeQuery(sql3, new ColumnProcessor());
			if(a3>0){
				agglist.remove(aggvo);
				exception+="客户"+vo.getCustomername()+"已经录入水电费预缴单，";
				continue;
			}
			
			String sql4="select count(*) from zl_payment_b where dr=0 and pk_customer='"+vo.getPk_customer()+"'";
			Integer a4=(Integer)iQ.executeQuery(sql4, new ColumnProcessor());
			if(a4>0){
				agglist.remove(aggvo);
				exception+="客户"+vo.getCustomername()+"已经录入水电费缴费单，";
				continue;
			}
			
			String sql9="select count(*) from zl_gather where dr=0 and isadd='Y' and pk_customer='"+vo.getPk_customer()+"'";
			Integer a9=(Integer)iQ.executeQuery(sql9, new ColumnProcessor());
			if(a9>0){
				agglist.remove(aggvo);
				exception+="客户"+vo.getCustomername()+"已经生成收款单，";
				continue;
			}
		}
		
//		if(vo.getFzkh()==null||"".equals(vo.getFzkh())){
//			
//			String sql5="select * from bd_customer where nvl(dr,0)=0 and pk_customer='"+vo.getPk_customer()+"'";
//			nc.vo.bd.cust.CustomerVO cvo=(nc.vo.bd.cust.CustomerVO) iQ.executeQuery(sql5, new BeanProcessor(nc.vo.bd.cust.CustomerVO.class));
//			String sql6="select * from bd_cust_supplier where nvl(dr,0)=0 and pk_cust_sup='"+vo.getPk_customer()+"'";
//			CustSupplierVO csvo=(CustSupplierVO) iQ.executeQuery(sql6, new BeanProcessor(CustSupplierVO.class));
//			String sql7="select * from bd_custorg where nvl(dr,0)=0 and pk_customer='"+vo.getPk_customer()+"'";
//			CustOrgVO covo=(CustOrgVO) iQ.executeQuery(sql7, new BeanProcessor(CustOrgVO.class));
//			if(cvo!=null){
//				HYPubBO_Client.delete(cvo);
//			}
//			if(csvo!=null){
//				HYPubBO_Client.delete(csvo);
//			}
//			if(covo!=null){
//				HYPubBO_Client.delete(covo);
//			}
//		}
		
		AggCustomerVO [] aggvos=agglist.toArray(new AggCustomerVO[0]);
		if(aggvos!=null &&aggvos.length>0){
			ILm_customerMaintain del=NCLocator.getInstance().lookup(ILm_customerMaintain.class);
			del.delete(aggvos);
			
			for(AggCustomerVO aggvo:aggvos){
				CustomerVO vo=aggvo.getParentVO();
				if(vo.getPk_pocus()!=null&&!"".equals(vo.getPk_pocus())){
					IVOPersistence ivp = NCLocator.getInstance().lookup(
							IVOPersistence.class);
					String sql8="select * from zl_pocustomers where nvl(dr,0)=0 and pk_pocustomers='"+vo.getPk_pocus()+"'";
					PocustomersVO pvo=(PocustomersVO) iQ.executeQuery(sql8, new BeanProcessor(PocustomersVO.class));
					pvo.setCustomert("暂无跟踪状态");
					ivp.updateVO(pvo);
				}
			}
		}
		//super.doAction(e);
		
		if(aggvos==null || aggvos.length==0){
			String yc=exception.substring(0, exception.length()-1);
			MessageDialog.showHintDlg(billForm, "提示", "无可删数据("+yc+")!请先手动删除业务单据再操作!");
		}
		else{
			MessageDialog.showHintDlg(billForm, "提示", "删除成功!");
		}
//		String pkcust="";
//		if(vo.getOldpsndoc()!=null){
//			pkcust=vo.getOldpsndoc();
//		}else{
//			pkcust=vo.getPk_customer();
//		}
	}

	@Override
	protected boolean isActionEnable() {
		Object obj= getModel().getSelectedData();
		if(obj==null){
			return false;
		}
	return true;
	}
	
	
}
