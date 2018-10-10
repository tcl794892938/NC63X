package nc.ui.zl.lm_customer.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.UIState;
import nc.vo.bd.cust.CustSupplierVO;
import nc.vo.bd.cust.custorg.CustOrgVO;
import nc.vo.zl.lm_customer.AggCustomerVO;
import nc.vo.zl.lm_customer.CustomerVO;
import nc.vo.zl.lm_customer.Customer_zzxmVO;

public class SaveAction extends DifferentVOSaveAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7428956433757566854L;
	public BillForm billform;


	public BillForm getBillform() {
	return billform;
	}
	
	public void setBillform(BillForm billform) {
		this.billform = billform;
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		AggCustomerVO aggvo=(AggCustomerVO) this.billform.getValue();
		Customer_zzxmVO[] zvo=(Customer_zzxmVO[])aggvo.getChildren(Customer_zzxmVO.class);
		if(zvo.length<=0){
			MessageDialog.showHintDlg(billform, "提示", "表体项目不可为空！");
			return;
		}
		CustomerVO vo=aggvo.getParentVO();
		String sql1="select count(*) from bd_customer where code='"+vo.getCustomercode()+"' and nvl(dr,0)=0";
		String sql2="select count(*) from bd_cust_supplier where code='"+vo.getCustomercode()+"' and nvl(dr,0)=0";
		Integer  it1=(Integer) iQ.executeQuery(sql1, new ColumnProcessor());
		Integer it2=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
		
		String sql="select count(*) from zl_customer where nvl(dr,0)=0 and sfzh='"+vo.getSfzh()+"'";
		Integer count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		
		String sql11="select count(*) from zl_customer where nvl(dr,0)=0 and yyzz='"+vo.getYyzz()+"'";
		Integer count11=(Integer) iQ.executeQuery(sql11, new ColumnProcessor());
		
		if(getModel().getUiState()==UIState.ADD){
			if(it1>0||it2>0){
				MessageDialog.showHintDlg(billform, "提示", "该客户编码已存在，不可同步");
				return;
			}
			if(count>0){
				MessageDialog.showHintDlg(billform, "提示", "该身份证已注册客户，请勿重复注册！");
				return;
			}
			if(count11>0){
				MessageDialog.showHintDlg(billform, "提示", "该营业执照已注册客户，请勿重复注册！");
				return;
			}
			String code=getVbillCode();
			billform.getBillCardPanel().getHeadItem("customercode").setValue(code);
			super.doAction(e);
			
			if(vo.getFzkh()==null){
				String sql4="select pk_customer from zl_customer where nvl(dr,0)=0 and customercode='"+code+"'";
				Object pkcust=iQ.executeQuery(sql4, new ColumnProcessor());
				nc.vo.bd.cust.CustomerVO cvo=new nc.vo.bd.cust.CustomerVO();
				cvo.setPk_customer(getStgObj(pkcust));
				cvo.setCode(code);
				cvo.setName(vo.getCustomername());
				cvo.setPk_group(vo.getPk_group());
				cvo.setPk_org(vo.getPk_org());
				cvo.setCreator(vo.getCreator());
				cvo.setCreationtime(vo.getCreationtime());
				cvo.setModifier(vo.getModifier());
				cvo.setModifiedtime(vo.getModifiedtime());
				cvo.setEnablestate(2);
				cvo.setTel1(vo.getCustomerlxfs());
				cvo.setPk_country("0001Z010000000079UJJ");
				cvo.setPk_custclass("1001A91000000009IZL0");
				cvo.setPk_format("FMT0Z000000000000000");
				cvo.setPk_timezone("0001Z010000000079U2P");
				HYPubBO_Client.insert(cvo);
				
				CustSupplierVO csvo=new CustSupplierVO();
				csvo.setPk_cust_sup(getStgObj(pkcust));
				csvo.setCode(code);
				csvo.setName(vo.getCustomername());
				csvo.setSupenablestate(2);
				csvo.setCustsuptype(1);
				csvo.setPk_custclass("1001A91000000009IZL0");
				csvo.setPk_group(vo.getPk_group());
				csvo.setPk_org(vo.getPk_org());
				HYPubBO_Client.insert(csvo);
				
				CustOrgVO covo=new CustOrgVO();
				covo.setPk_customer(getStgObj(pkcust));
				covo.setPk_org(vo.getPk_org());
				covo.setPk_group(vo.getPk_group());
				covo.setEnablestate(2);
				covo.setDataoriginflag(0);
				HYPubBO_Client.insert(covo);
				
				MessageDialog.showHintDlg(billform, "提示", "同步成功！");
			}
			
		}
		
		if(getModel().getUiState()==UIState.EDIT){
			if(it1>1||it2>1){
				MessageDialog.showHintDlg(billform, "提示", "该客户编码已存在，不可同步");
				return;
			}
			
			String sql0="select * from zl_customer where nvl(dr,0)=0 and sfzh='"+vo.getSfzh()+"'";
			CustomerVO vo1=(CustomerVO) iQ.executeQuery(sql0, new BeanProcessor(CustomerVO.class));
			if(vo1!=null){
				if(vo1.getPk_org().equals(vo.getPk_org())&&vo1.getSfzh().equals(vo.getSfzh())){
				if(count>1){
					MessageDialog.showHintDlg(billform, "提示", "该身份证已注册客户，请勿重复注册！");
					return;
				}
				}else{
				if(count>0){
					MessageDialog.showHintDlg(billform, "提示", "该身份证已注册客户，请勿重复注册！");
					return;
				}
			}
			}
			super.doAction(e);
			if(vo.getFzkh()==null){
				String pkcust=vo.getPk_customer();
				
				String sql5="select * from bd_customer where nvl(dr,0)=0 and pk_customer='"+pkcust+"'";
				nc.vo.bd.cust.CustomerVO cvo=(nc.vo.bd.cust.CustomerVO) iQ.executeQuery(sql5, new BeanProcessor(nc.vo.bd.cust.CustomerVO.class));
				if(cvo==null){
					cvo=new nc.vo.bd.cust.CustomerVO();
					cvo.setPk_customer(getStgObj(pkcust));
					cvo.setCode(vo.getCustomercode());
					cvo.setName(vo.getCustomername());
					cvo.setPk_group(vo.getPk_group());
					cvo.setPk_org(vo.getPk_org());
					cvo.setCreator(vo.getCreator());
					cvo.setCreationtime(vo.getCreationtime());
					cvo.setModifier(vo.getModifier());
					cvo.setModifiedtime(vo.getModifiedtime());
					cvo.setEnablestate(2);
					cvo.setTel1(vo.getCustomerlxfs());
					cvo.setPk_country("0001Z010000000079UJJ");
					cvo.setPk_custclass("1001A91000000009IZL0");
					cvo.setPk_format("FMT0Z000000000000000");
					cvo.setPk_timezone("0001Z010000000079U2P");
					HYPubBO_Client.insert(cvo);
					
					CustSupplierVO csvo=new CustSupplierVO();
					csvo.setPk_cust_sup(getStgObj(pkcust));
					csvo.setCode(vo.getCustomercode());
					csvo.setName(vo.getCustomername());
					csvo.setSupenablestate(2);
					csvo.setCustsuptype(1);
					csvo.setPk_custclass("1001A91000000009IZL0");
					csvo.setPk_group(vo.getPk_group());
					csvo.setPk_org(vo.getPk_org());
					HYPubBO_Client.insert(csvo);
					
					CustOrgVO covo=new CustOrgVO();
					covo.setPk_customer(getStgObj(pkcust));
					covo.setPk_org(vo.getPk_org());
					covo.setPk_group(vo.getPk_group());
					covo.setEnablestate(2);
					covo.setDataoriginflag(0);
					HYPubBO_Client.insert(covo);
					
					MessageDialog.showHintDlg(billform, "提示", "同步成功！");
				}else{
					cvo.setName(vo.getCustomername());
					cvo.setPk_group(vo.getPk_group());
					cvo.setPk_org(vo.getPk_org());
					cvo.setCreator(vo.getCreator());
					cvo.setCreationtime(vo.getCreationtime());
					cvo.setModifier(vo.getModifier());
					cvo.setModifiedtime(vo.getModifiedtime());
					cvo.setEnablestate(2);
					cvo.setTel1(vo.getCustomerlxfs());
					cvo.setPk_country("0001Z010000000079UJJ");
					cvo.setPk_custclass("1001A91000000009IZL0");
					cvo.setPk_format("FMT0Z000000000000000");
					cvo.setPk_timezone("0001Z010000000079U2P");
					HYPubBO_Client.update(cvo);
					
					String sql6="select * from bd_cust_supplier where nvl(dr,0)=0 and pk_cust_sup='"+pkcust+"'";
					CustSupplierVO csvo=(CustSupplierVO) iQ.executeQuery(sql6, new BeanProcessor(CustSupplierVO.class));
					csvo.setName(vo.getCustomername());
					csvo.setSupenablestate(2);
					csvo.setCustsuptype(1);
					csvo.setPk_custclass("1001A91000000009IZL0");
					csvo.setPk_group(vo.getPk_group());
					csvo.setPk_org(vo.getPk_org());
					HYPubBO_Client.update(csvo);
					
					String sql7="select * from bd_custorg where nvl(dr,0)=0 and pk_customer='"+pkcust+"'";
					CustOrgVO covo=(CustOrgVO) iQ.executeQuery(sql7, new BeanProcessor(CustOrgVO.class));
					covo.setPk_customer(vo.getPk_customer());
					covo.setPk_org(vo.getPk_org());
					covo.setPk_group(vo.getPk_group());
					covo.setEnablestate(2);
					HYPubBO_Client.update(covo);
					
					MessageDialog.showHintDlg(billform, "提示", "同步成功！");
				}
				
			}
			
		}
		
	}

	/**
	 * 生成单据编号
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getVbillCode() throws Exception{
		
		String billcode="";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(
				IUAPQueryBS.class);
		Object pk_org=billform.getBillCardPanel().getHeadItem("pk_org").getValueObject();
		String getorgcode="select code from org_orgs where nvl(dr,0)=0 and pk_org='"+getStgObj(pk_org)+"'";
		Object orgcode=iQ.executeQuery(getorgcode, new ColumnProcessor());
		
		String sql="select max(customercode) from zl_customer where nvl(dr,0)=0 and customercode like '"+getStgObj(orgcode)+"ZL%'";
		Object sourceid=iQ.executeQuery(sql, new ColumnProcessor());
		
		if(getStgObj(sourceid).equals("")){
			billcode=getStgObj(orgcode)+"ZL0001";
		}else{
			Integer length=getStgObj(sourceid).length();
			Integer num=Integer.parseInt(getStgObj(sourceid).substring(length-4,length))+1;
			String zero="";
			for(int i=0;i<4-num.toString().length();i++){
				zero+="0";
			}
			billcode=getStgObj(orgcode)+"ZL"+zero+num;
		}
		return billcode;
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
