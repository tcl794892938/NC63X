package nc.ui.zl.ly_businesssource.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;
import nc.vo.zl.ly_businesssource.BusinessBVO;
import nc.vo.zl.ly_businesssource.BusinessVO;
import nc.vo.zl.ly_pocustomers.PocustomersOrgVO;
import nc.vo.zl.ly_pocustomers.PocustomersVO;

/**
 * 生成潜在客户
 * 
 * @author Liu
 * 
 */
public class AddcustomerAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4736173593203370957L;
	
	private BillManageModel model;
	private ShowUpableBillForm billform;

	/**
	 * model监听
	 * 
	 * @return
	 */
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public AddcustomerAction() {
		super();
		this.setCode("addcustomer");
		this.setBtnName("生成潜在客户");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Integer[] rows = model.getSelectedOperaRows();
		if (rows == null || rows.length == 0 || rows.length > 1) {
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"请选择一条数据！");
			return;
		}
		Object obj = model.getSelectedData();
		IVOPersistence ivp = NCLocator.getInstance().lookup(
				IVOPersistence.class);

		// 商源主表更新dr，潜在客户主表生成
		AggBusinessSourceVO aggvo = (AggBusinessSourceVO) obj;
		BusinessVO bvo = aggvo.getParentVO();
		
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(
				IUAPQueryBS.class);
		String sql0="select count(*) from zl_customer where nvl(dr,0)=0 and sfzh='"+bvo.getIdnumber()+"'";
		Integer count=(Integer) iQ.executeQuery(sql0, new ColumnProcessor());
		if(count>0){
			MessageDialog.showHintDlg(billform, "提示", "该身份证已注册客户，请勿重复注册！");
			return;
		}
		
		Object obj1 = bvo.getVdef1();
		String vdef1 = obj1 == null ? "" : obj1.toString();
		if (vdef1.equals("had")) {
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"该客户已经是潜在客户无需再次生成！");
		} else {
			bvo.setDr(1);
			bvo.setVdef1("had");
			ivp.updateVO(bvo);

			PocustomersVO pvo = new PocustomersVO();
			pvo.setSourceid(getVbillCode());
			pvo.setSourcename(bvo.getSourcename());
			pvo.setCustomertype(bvo.getCustomertype());
			pvo.setPk_group(bvo.getPk_group());
			pvo.setPk_org(bvo.getPk_org());
			pvo.setPk_org_v(bvo.getPk_org_v());
			pvo.setPhone(bvo.getPhone());
			pvo.setAddress(bvo.getAddress());
			pvo.setSalesman(bvo.getSalesman());
			pvo.setIdnumber(bvo.getIdnumber());
			pvo.setBusinesslicense(bvo.getBusinesslicense());
			pvo.setCustomert("暂无跟踪状态");
			// pvo.setCustomert("潜在客户");
			pvo.setCreator(bvo.getCreator());
			pvo.setCreationtime(bvo.getCreationtime());
			pvo.setModifier(AppContext.getInstance().getPkUser());
			pvo.setModifiedtime(AppContext.getInstance().getServerTime());
			pvo.setDbilldate(AppContext.getInstance().getBusiDate());
			String pk_po = ivp.insertVO(pvo);

			// 商源子表更新dr，潜在客户子表生成
			BusinessBVO[] bbvo = (BusinessBVO[]) aggvo.getChildrenVO();
			Integer row = bbvo.length;
			List<PocustomersOrgVO> list = new ArrayList<PocustomersOrgVO>();
			for (int i = 0; i < row; i++) {
				PocustomersOrgVO orgvo = new PocustomersOrgVO();
				orgvo.setProcode(bbvo[i].getProcode());
				orgvo.setProname(bbvo[i].getProname());
				orgvo.setRemarks(bbvo[i].getRemarks());
				orgvo.setPk_pocustomers(pk_po);
				orgvo.setVdef1("noedit");
				list.add(orgvo);
			}
			String[] pk_org = ivp.insertVOArray(list
					.toArray(new PocustomersOrgVO[0]));
			for (BusinessBVO bbvo1 : bbvo) {
				bbvo1.setDr(1);
			}
			ivp.updateVOArray(bbvo);
			/*
			 * PocustomersOrgVO orgvo=new PocustomersOrgVO(); for (BusinessBVO
			 * bbvo1 : bbvo) { orgvo.setProcode(bbvo1.getProcode());
			 * orgvo.setProname(bbvo1.getProname());
			 * orgvo.setRemarks(bbvo1.getRemarks());
			 * orgvo.setPk_pocustomers(pk_po); orgvo.setDr(0);
			 * ivp.insertVO(orgvo); }
			 */
			if (pk_po != null && pk_org != null) {
				MessageDialog.showHintDlg(model.getContext().getEntranceUI(),
						"提示", "潜在客户生成成功！");
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
		
		String sql="select max(sourceid) from zl_pocustomers where nvl(dr,0)=0 and sourceid like '"+getStgObj(orgcode)+"ZL%'";
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
