package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction {

	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		super.doAction(e);
		
		IVOPersistence ivp = NCLocator.getInstance().lookup(
				IVOPersistence.class);
		
		AggMdcontractVO aggvo = (AggMdcontractVO)getModel().getSelectedData();
		MdcontractVO pvo= aggvo.getParentVO();
		MdcontractCVO[] cvo = (MdcontractCVO[]) aggvo.getChildrenVO();
		
		//保留原合同信息，版本号为-1
		pvo.setVersion(-1);
		ivp.updateVO(pvo);

		if(cvo!=null&&cvo.length>0){
			AggRecbillVO[] aggvos = new AggRecbillVO[cvo.length];
			AggRecbillVO agg = null;
			RecbillVO rvo = null;
			//ConfirmationVO convo = null;
			//RecbillVO[] rvo = new RecbillVO[cvo.length];
			//待收入确认
			AggConfirmationVO[] aggvosc = new AggConfirmationVO[cvo.length];
			//AggConfirmationVO aggvoc = new AggConfirmationVO();
			
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(
					IUAPQueryBS.class);
			for(int i=0;i<cvo.length;i++){
				rvo = new RecbillVO();
				agg = new AggRecbillVO();
				AggConfirmationVO aggvoc = new AggConfirmationVO();
				//rvo[i].setVbillcode(getVbillCode(i));
				rvo.setPk_org(pvo.getPk_org());
				rvo.setPk_org_v(pvo.getPk_org_v());
				rvo.setPk_group(pvo.getPk_group());
				rvo.setPk_project(pvo.getPk_project());
				rvo.setPk_customer(cvo[i].getPk_customer());
				rvo.setPk_costproject(cvo[i].getPk_costproject());
				rvo.setBegindate(cvo[i].getMstartdate());
				rvo.setEnddate(cvo[i].getMenddate());
				rvo.setGatherdate(cvo[i].getMoneydate());
				rvo.setNrecmoney(cvo[i].getReceivemoney());
				rvo.setNnotaxmoney(cvo[i].getFreetaxmoney());
				rvo.setNtaxmoney(cvo[i].getTaxmoney());
				rvo.setCaccountperiod(cvo[i].getAccountmonth());
				rvo.setDbilldate(new UFDate());
				rvo.setCreator(AppContext.getInstance().getPkUser());
				rvo.setCreationtime(AppContext.getInstance().getServerTime());
				rvo.setVsrcid(pvo.getPk_mdcontract());
				rvo.setVsrctype(pvo.getPk_billtype());
				rvo.setNtaxrate(getUFdObj(pvo.getTaxrate()));
				
				String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode='H620'";
				Object pk_billtype = iQ
						.executeQuery(sql, new ColumnProcessor());
				rvo.setPk_billtype(getStgObj(pk_billtype));
				rvo.setBilltypecode("H620");
				rvo.setVbillstatus(1);
				rvo.setVdef1(cvo[i].getPk_mdcontract_c());
				rvo.setVdef2("zl_mdcontract_c");
				agg.setParentVO(rvo);
				aggvos[i]=agg;
				
				//待收入确认
				
				ConfirmationVO convo = new ConfirmationVO();
				//convo.setPk_confirmation(null);
				convo.setPk_org(pvo.getPk_org());
				convo.setPk_org_v(pvo.getPk_org_v());
				convo.setPk_group(pvo.getPk_group());
				convo.setPk_customer(pvo.getPk_customer());
				convo.setCaccountperiod(cvo[i].getAccountmonth());
				convo.setDcollectiondate(cvo[i].getMoneydate());
				convo.setDbilldate(new UFDate());
				convo.setCreator(AppContext.getInstance().getPkUser());
				convo.setCreationtime(AppContext.getInstance().getServerTime());
				convo.setVsrcid(pvo.getPk_mdcontract());
				convo.setVsrctype(pvo.getPk_billtype());
				String sql_1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
				Object pk_billtype_1 = iQ
						.executeQuery(sql_1, new ColumnProcessor());
				convo.setPk_billtype(getStgObj(pk_billtype_1));
				convo.setVbilltypecode("H640");
				convo.setVbillstatus(1);
				convo.setPk_project(pvo.getPk_project());
				convo.setDr(0);
				convo.setChargingproject(cvo[i].getPk_costproject());
				convo.setDfeestartdate(cvo[i].getMstartdate());
				convo.setDfeeenddate(cvo[i].getMenddate());
				convo.setCaccountperiod(cvo[i].getAccountmonth());
				convo.setAmountreceivable(cvo[i].getReceivemoney());
				convo.setAmountconfirmed(new UFDouble(0));
				convo.setDreccollectdate(cvo[i].getMoneydate());
				convo.setVdef1(cvo[i].getPk_mdcontract_c());
				convo.setVdef2("zl_mdcontract_c");
				convo.setNnotaxmny(cvo[i].getFreetaxmoney());
				convo.setNtaxmny(cvo[i].getTaxmoney());
				convo.setNtaxrate(new UFDouble(pvo.getTaxrate()));
				aggvoc.setParentVO(convo);
				aggvosc[i] = aggvoc;
			}
			//应收单
			ICwf_recbillMaintain irm=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
			irm.insert(aggvos, null);
			//待收入确认
			ILyw_confirmationMaintain icf = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
			icf.insert(aggvosc, null);
			//生成供参照修改合同，版本号为0
			pvo.setVersion(0);
			String pk = pvo.getPk_mdcontract();
			pvo.setPk_mdcontract(null);
			pvo.setVsrcid(pk);
			Object pk_p=ivp.insertVO(pvo);
			if(cvo!=null&&cvo.length>0){
				for (MdcontractCVO mc : cvo) {
					mc.setPk_mdcontract(getStgObj(pk_p));
				}
				ivp.insertVOArray(cvo);
			}
			pvo.setPk_mdcontract(pk);
		}
		
	}
	
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	public UFDouble getUFdObj(Object obj){
		return obj == null ? new UFDouble(0) : new UFDouble(obj.toString());
	}
	
}
