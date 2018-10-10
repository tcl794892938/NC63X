package nc.ui.zl.ly_pocustomers.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.bd.cust.CustSupplierVO;
import nc.vo.bd.cust.custorg.CustOrgVO;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.lm_customer.CustomerVO;
import nc.vo.zl.lm_customer.Customer_zzxmVO;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;
import nc.vo.zl.ly_pocustomers.PocustomersVO;

public class SaveAction extends DifferentVOSaveAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8471588123076148242L;
	
	public ShowUpableBillForm billform;
	

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	

	@Override
	public void doAction(ActionEvent e) throws Exception {
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		AggPocustomersVO aggvo = (AggPocustomersVO) this.getBillform()
				.getValue();
		
		Object obj1=getBillform().getBillCardPanel().getBillTable("id_pocustomers_zr").getRowCount();
		Integer rowcount=(Integer) (obj1==null?0:obj1);
		Object obj2=getBillform().getBillCardPanel().getBillModel("id_pocustomers_zr").getColumnName(1);
		String isnew=obj2==null?"":obj2.toString();
		if(isnew.equals("是否最新")){
			if(rowcount==1){
				billform.getBillCardPanel().getBillModel().setValueAt(true, 0, "isnew");
			}else if(rowcount>1){
				for(int i=1;i<rowcount;i++){
					billform.getBillCardPanel().getBillModel().setValueAt(false, i-1, "isnew");
				}
				billform.getBillCardPanel().getBillModel().setValueAt(true, rowcount-1, "isnew");
			}
		}
		
		super.doAction(e);
		
		// PocustomersZrVO
		// zrvo=getBillform().getBillCardPanel().getBillModel("");
		
		IVOPersistence ivp = NCLocator.getInstance().lookup(
				IVOPersistence.class);
		PocustomersVO pvo = aggvo.getParentVO();
		String ct = pvo.getCustomert().toString();

		if (ct.trim().equals("签约客户")) { 
			// 将潜在客户信息转发至客户信息中心
			CustomerVO cvo =new CustomerVO();
			cvo.setCustomercode(getVbillCode());
			cvo.setCustomername(pvo.getSourcename());
			cvo.setCustomertype(pvo.getCustomertype());
			cvo.setPk_org(pvo.getPk_org());
			cvo.setPk_org_v(pvo.getPk_org_v());
			cvo.setPk_group(pvo.getPk_group());
			cvo.setCustomerlxfs(pvo.getPhone());
			cvo.setCustomeraddress(pvo.getAddress());
			cvo.setZygw(pvo.getSalesman());
			cvo.setSfzh(pvo.getIdnumber());
			cvo.setYyzz(pvo.getBusinesslicense());
			cvo.setDbilldate(AppContext.getInstance().getBusiDate());
			Object user = AppContext.getInstance().getPkUser();
			cvo.setCreator(getStgObj(user));
			cvo.setCreationtime(AppContext.getInstance().getServerTime());
			cvo.setPk_pocus(pvo.getPk_pocustomers());
			Object pk_customer=ivp.insertVO(cvo);
			Object rows=billform.getBillCardPanel().getBillTable("id_pocustomers_org").getRowCount();
			Integer row=(Integer) (rows==null?"":rows);
			List<Customer_zzxmVO> list = new ArrayList<Customer_zzxmVO>();
			if(row>0){
				for(int i=0;i<row;i++){
				Customer_zzxmVO zvo=new Customer_zzxmVO();
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Object procode=getBillform().getBillCardPanel().getBillModel("id_pocustomers_org").getValueAt(i, "procode");
				String sql="select pk_project from zl_project where nvl(dr,0)=0 and code='"+getStgObj(procode)+"'";
				Object pk_project=iQ.executeQuery(sql, new ColumnProcessor());
				zvo.setPk_customer(getStgObj(pk_customer));
				zvo.setPk_project(getStgObj(pk_project));
				zvo.setVdef1("noedit");
				list.add(zvo);
				}
				ivp.insertVOList(list);
			}
			
			//将数据传至基础数据
			String pkcust=getStgObj(pk_customer);
			nc.vo.bd.cust.CustomerVO ccvo=new nc.vo.bd.cust.CustomerVO();
			ccvo.setPk_customer(getStgObj(pkcust));
			ccvo.setCode(getVbillCode());
			ccvo.setName(cvo.getCustomername());
			ccvo.setPk_group(cvo.getPk_group());
			ccvo.setPk_org(cvo.getPk_org());
			ccvo.setCreator(cvo.getCreator());
			ccvo.setCreationtime(cvo.getCreationtime());
			ccvo.setModifier(cvo.getModifier());
			ccvo.setModifiedtime(cvo.getModifiedtime());
			ccvo.setEnablestate(2);
			ccvo.setTel1(cvo.getCustomerlxfs());
			ccvo.setPk_country("0001Z010000000079UJJ");
			ccvo.setPk_custclass("1001A91000000009IZL0");
			ccvo.setPk_format("FMT0Z000000000000000");
			ccvo.setPk_timezone("0001Z010000000079U2P");
			HYPubBO_Client.insert(ccvo);
			
			CustSupplierVO csvo=new CustSupplierVO();
			csvo.setPk_cust_sup(getStgObj(pkcust));
			csvo.setCode(getVbillCode());
			csvo.setName(cvo.getCustomername());
			csvo.setSupenablestate(2);
			csvo.setCustsuptype(1);
			csvo.setPk_custclass("1001A91000000009IZL0");
			csvo.setPk_group(cvo.getPk_group());
			csvo.setPk_org(cvo.getPk_org());
			HYPubBO_Client.insert(csvo);
			
			CustOrgVO covo=new CustOrgVO();
			covo.setPk_customer(getStgObj(pkcust));
			covo.setPk_org(cvo.getPk_org());
			covo.setPk_group(cvo.getPk_group());
			covo.setEnablestate(2);
			covo.setDataoriginflag(0);
			HYPubBO_Client.insert(covo);
			
			MessageDialog.showHintDlg(billform, "提示",
					"签约客户已自动添加到客户信息中心！信息同步成功！");
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
	
	// 字符串封装
		public String getStgObj(Object obj) {
			return obj == null ? "" : obj.toString();
		}
}
