package nc.ui.zl.ld_parkcontract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_housesource.HousesourceVO;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractBVO;
import nc.vo.zl.ld_parkcontract.ParkcontractCVO;
import nc.vo.zl.ld_parkcontract.ParkcontractFVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.lm_customer.Customer_carVO;
import nc.vo.zl.lm_customer.Customer_fcxxVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;

import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggParkcontractVO aggvo = (AggParkcontractVO) getModel().getSelectedData();
		
		ParkcontractCVO[] fvo = (ParkcontractCVO[]) aggvo.getChildren(ParkcontractCVO.class);
		ParkcontractBVO[] bvo = (ParkcontractBVO[]) aggvo.getChildren(ParkcontractBVO.class);
		ParkcontractFVO[] cfvo = (ParkcontractFVO[]) aggvo.getChildren(ParkcontractFVO.class);
		if(fvo==null){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "未拆分分财务信息,请检查！");
			return;
		}
		// TODO 自动生成的方法存根
		super.doAction(e);
		ParkcontractVO vo = aggvo.getParentVO();
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if(fvo.length>0){
			AggRecbillVO[] aggvos = new AggRecbillVO[fvo.length];
			AggRecbillVO agg = null;
			RecbillVO rvo = null;
			//RecbillVO[] rvo = new RecbillVO[fvo.length];
			for(int i=0;i<fvo.length;i++){
				rvo = new RecbillVO();
				agg = new AggRecbillVO();
				//rvo.setVbillcode(getVbillCode(i));
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
				rvo.setVsrcid(vo.getPk_parkcontract());
				rvo.setVsrctype(vo.getPk_billtype());
				
				String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode='H620'";
				Object pk_billtype = iQ
						.executeQuery(sql, new ColumnProcessor());
				rvo.setPk_billtype(getStgObj(pk_billtype));
				rvo.setBilltypecode("H620");
				rvo.setVbillstatus(1);
				rvo.setDr(0);
				rvo.setPk_building(fvo[i].getParkarea());
				rvo.setPk_house(fvo[i].getParknum());
				for(int j=0;j<bvo.length;j++){
					if(fvo[i].getParkarea().equals(bvo[j].getParkarea())&&fvo[i].getParknum().equals(bvo[j].getParknum())){
						rvo.setPk_carno(bvo[j].getPlatenum());
					}
				}				
				rvo.setVdef1(fvo[i].getPk_parkcontract_c());
				rvo.setVdef2("zl_parkcontract_c");
				agg.setParentVO(rvo);
				aggvos[i]=agg;
			}
			ICwf_recbillMaintain irm=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
			irm.insert(aggvos, null);
		}
		if(cfvo.length>0){
			AggConfirmationVO[] aggvosc = new AggConfirmationVO[cfvo.length];
			
			for(int j=0;j<cfvo.length;j++){
				//aggvosc[j] = new AggConfirmationVO();
				AggConfirmationVO aggvoc = new AggConfirmationVO();
				
				ConfirmationVO convo = new ConfirmationVO();
				convo.setPk_org(vo.getPk_org());
				convo.setPk_org_v(vo.getPk_org_v());
				convo.setPk_group(vo.getPk_group());
				convo.setPk_customer(vo.getPk_customer());
				convo.setDbilldate(new UFDate());
				convo.setCreator(AppContext.getInstance().getPkUser());
				convo.setCreationtime(AppContext.getInstance().getServerTime());
				convo.setVsrcid(vo.getPk_parkcontract());
				convo.setVsrctype(vo.getPk_billtype());
				convo.setPk_project(vo.getPk_project());
				String sql_1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
				
				Object pk_billtype_1 = iQ
						.executeQuery(sql_1, new ColumnProcessor());
				convo.setPk_billtype(getStgObj(pk_billtype_1));
				convo.setVbilltypecode("H640");
				convo.setVbillstatus(1);
				convo.setDr(0);
				
				
				//会计年月
				String date = cfvo[j].getPaydate().toString();
				
				String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+date.substring(0, 7)+"'";
				Object obj=iQ.executeQuery(sql_date, new ColumnProcessor());
				convo.setCaccountperiod(obj.toString());
				convo.setDcollectiondate(cfvo[j].getPaydate());
				aggvoc.setParentVO(convo);
				
				convo.setDreccollectdate(cfvo[j].getPaydate());
				convo.setBuildno(cfvo[j].getParkarea());
				convo.setHouseproperty(cfvo[j].getParknum());
				convo.setChargingproject(cfvo[j].getPk_costproject());
				convo.setDfeestartdate(cfvo[j].getMstartdate());
				convo.setDfeeenddate(cfvo[j].getMenddate());
				convo.setCaccountperiod(obj.toString());
				convo.setAmountreceivable(cfvo[j].getNreceivemoney());
				convo.setVdef1(cfvo[j].getPk_parkcontract_f());
				convo.setVdef2("zl_parkcontract_f");
				convo.setAmountconfirmed(new UFDouble(0));
				convo.setDr(0);
				//aggvoc.setChildrenVO(conbvo);
				//报表用
				convo.setNnotaxmny(cfvo[j].getNfreetaxmoney());
				convo.setNtaxmny(cfvo[j].getNtaxmoney());
				convo.setNtaxrate(new UFDouble(vo.getNtaxrate()));
				aggvosc[j] = aggvoc;
			}
			ILyw_confirmationMaintain lyw = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
			
			lyw.insert(aggvosc, null);
		}
		//回写房源状态
		//获取当前项目下的房源
		String sql_house = "select * from zl_housesource h where nvl(dr,0)=0 and projectname='"+vo.getPk_project()+"'";
		List<HousesourceVO> hList = (List<HousesourceVO>)iQ.executeQuery(sql_house, new BeanListProcessor(HousesourceVO.class));
		if(bvo.length>0){
			for(int i=0;i<bvo.length;i++){
				String pk_house = bvo[i].getParknum();
				for(int j=0;j<hList.size();j++){
					if(hList.get(j).getPk_housesource().equals(pk_house)){
						hList.get(j).setHousestate(2);
					}
				}
			}
			IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
			ivp.updateVOList(hList);
		}
		//回写客户信息中心
		Customer_fcxxVO[] hvo = new Customer_fcxxVO[bvo.length];
		
		for(int i=0;i<bvo.length;i++){
			
			hvo[i] = new Customer_fcxxVO();
			hvo[i] .setBuildnum(bvo[i].getParkarea());
			hvo[i] .setFcname(bvo[i].getParknum());
			hvo[i] .setZlbegintime(new UFDateTime(vo.getStartdate().toDate()));
			hvo[i] .setZlendtime(new UFDateTime(vo.getEnddate().toDate()));
			hvo[i] .setPk_customer(bvo[i].getPk_customer());
			//查询相应车位的销售面积
			for(HousesourceVO vo1 :hList){
				if(vo1.getPk_housesource().equals(bvo[i].getParknum())){
					hvo[i].setXsmj(vo1.getBuildarea());
					break;
				}
			}
			
		}
		IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
		ivp.insertVOArray(hvo);
	}
	
	
	
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}
}
