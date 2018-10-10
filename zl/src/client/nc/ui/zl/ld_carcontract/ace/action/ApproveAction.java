package nc.ui.zl.ld_carcontract.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractCVO;
import nc.vo.zl.ld_carcontract.CarcontractFVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.lm_customer.Customer_carVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggCarcontractVO aggvo = (AggCarcontractVO) getModel().getSelectedData();
		
		CarcontractCVO[] fvo = (CarcontractCVO[])aggvo.getChildren(CarcontractCVO.class);
		CarcontractBVO[] bvo = (CarcontractBVO[])aggvo.getChildren(CarcontractBVO.class);
		CarcontractFVO[] cfvo = (CarcontractFVO[])aggvo.getChildren(CarcontractFVO.class);
		if(fvo.length==0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "未拆分分财务信息,请检查！");
			return;
		}
		// TODO 自动生成的方法存根
		super.doAction(e);
		CarcontractVO vo = aggvo.getParentVO();
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		//推应收单
		if(fvo.length>0){
			
			AggRecbillVO[] aggvos = new AggRecbillVO[fvo.length];
			AggRecbillVO agg = null;
			RecbillVO rvo = null;
			for(int i=0;i<fvo.length;i++){
				agg = new AggRecbillVO();
				rvo = new RecbillVO();
				//rvo[i].setVbillcode(getVbillCode(i));
				rvo.setPk_org(vo.getPk_org());
				rvo.setPk_org_v(vo.getPk_org_v());
				rvo.setPk_group(vo.getPk_group());
				rvo.setPk_project(vo.getPk_project());
				rvo.setPk_customer(vo.getPk_customer());
				rvo.setPk_costproject(fvo[i].getPk_costproject());
				rvo.setBegindate(fvo[i].getMstartdate());
				rvo.setEnddate(fvo[i].getMenddate());
				rvo.setGatherdate(fvo[i].getPaydate());
				//会计年月
				String date = fvo[i].getPaydate().toString();
				
				String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+date.substring(0, 7)+"'";
				Object obj=iQ.executeQuery(sql_date, new ColumnProcessor());
				rvo.setCaccountperiod(obj.toString());
				
				rvo.setNrecmoney(fvo[i].getNreceivemoney());
				//rvo[i].setNnotaxmoney(fvo[i].getNfreetaxmoney());
				//rvo[i].setNtaxmoney(fvo[i].getNtaxmoney());
				//rvo[i].setCaccountperiod(cvo[i].getAccountmonth());
				rvo.setDbilldate(new UFDate());
				rvo.setCreator(AppContext.getInstance().getPkUser());
				rvo.setCreationtime(AppContext.getInstance().getServerTime());
				rvo.setVsrcid(vo.getPk_carcontract());
				rvo.setVsrctype(vo.getPk_billtype());
				
				String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode='H620'";
				Object pk_billtype = iQ
						.executeQuery(sql, new ColumnProcessor());
				rvo.setPk_billtype(getStgObj(pk_billtype));
				rvo.setBilltypecode("H620");
				rvo.setVbillstatus(1);
				rvo.setDr(0);
				rvo.setPk_carno(fvo[i].getPlatenum());
				rvo.setVdef1(fvo[i].getPk_carcontract_c());
				rvo.setVdef2("zl_carcontract_c");
				agg.setParentVO(rvo);
				aggvos[i]=agg;
			}
			ICwf_recbillMaintain irm=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
			irm.insert(aggvos, null);
			
		}
		//推客户信息中心
		
		Customer_carVO[] carvo = new Customer_carVO[bvo.length];
		
		for(int i=0;i<bvo.length;i++){
			carvo[i] = new Customer_carVO();
			carvo[i] .setCarnum(bvo[i].getPlatenum());
			carvo[i] .setHtbegindate(vo.getStartdate());
			carvo[i] .setHtenddate(vo.getEnddate());
			carvo[i] .setPk_customer(bvo[i].getPk_customer());
			carvo[i] .setDr(0);
			
		}
		IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
		ivp.insertVOArray(carvo);
		//推待收入确认
		if(cfvo.length>0){
			AggConfirmationVO[] aggvosc = new AggConfirmationVO[cfvo.length];
			
			
			
			for(int j=0;j<cfvo.length;j++){
				ConfirmationVO convo = new ConfirmationVO();
				AggConfirmationVO aggvoc = new AggConfirmationVO();
				convo.setPk_org(vo.getPk_org());
				convo.setPk_org_v(vo.getPk_org_v());
				convo.setPk_group(vo.getPk_group());
				convo.setPk_customer(vo.getPk_customer());
				
				//会计年月
				String date = cfvo[j].getPaydate().toString();
				
				String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+date.substring(0, 7)+"'";
				Object obj=iQ.executeQuery(sql_date, new ColumnProcessor());
				convo.setCaccountperiod(obj.toString());
				convo.setDcollectiondate(cfvo[j].getPaydate());
				convo.setDbilldate(new UFDate());
				convo.setCreator(AppContext.getInstance().getPkUser());
				convo.setCreationtime(AppContext.getInstance().getServerTime());
				convo.setVsrcid(vo.getPk_carcontract());
				convo.setVsrctype(vo.getPk_billtype());
				String sql_1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
				
				Object pk_billtype_1 = iQ
						.executeQuery(sql_1, new ColumnProcessor());
				convo.setPk_billtype(getStgObj(pk_billtype_1));
				convo.setVbilltypecode("H640");
				convo.setVbillstatus(1);
				convo.setPk_project(vo.getPk_project());
				convo.setAmountconfirmed(new UFDouble(0));
				convo.setChargingproject(cfvo[j].getPk_costproject());
				convo.setDfeestartdate(cfvo[j].getMstartdate());
				convo.setDfeeenddate(cfvo[j].getMenddate());
				convo.setCaccountperiod(obj.toString());
				convo.setAmountreceivable(cfvo[j].getNreceivemoney());
				convo.setDreccollectdate(cfvo[j].getPaydate());
				convo.setVdef1(cfvo[j].getPk_carcontract_f());
				convo.setVdef2("zl_carcontract_f");
				//报表用
				convo.setNnotaxmny(cfvo[j].getNfreetaxmoney());
				convo.setNtaxmny(cfvo[j].getNtaxmoney());
				convo.setNtaxrate(new UFDouble(vo.getNtaxrate()));
				aggvoc.setParentVO(convo);
				aggvosc[j] = aggvoc;
			}
			ILyw_confirmationMaintain icf = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
			icf.insert(aggvosc, null);
		}
	}
	
	
	
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}
}
