package nc.ui.zl.hql_prepayment.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.zl.hql_prepayment.PrepaymentVO;
import nc.vo.zl.hql_prepayment.Prepayment_bVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends ApproveScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484666283357425L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		AggPrepaymentVO aggvo = (AggPrepaymentVO) getModel().getSelectedData();
		Prepayment_bVO[] bvos = (Prepayment_bVO[]) aggvo.getChildren(Prepayment_bVO.class);
		if(bvos.length > 0){
			PrepaymentVO hvo = aggvo.getParentVO();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			List<AggRecbillVO> list = new ArrayList<AggRecbillVO>();
			List<AggConfirmationVO> cbvolist = new ArrayList<AggConfirmationVO>();
			ICwf_recbillMaintain cwf=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
			ILyw_confirmationMaintain lyw_cm = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
			for(int i = 0;i < bvos.length;i++){
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				//推应收单
			    RecbillVO recvo = new RecbillVO();
			    recvo.setPk_project(hvo.getPk_project());
				recvo.setPk_customer(bvos[i].getPk_customer());
				recvo.setPk_costproject(bvos[i].getPk_costproject());
				recvo.setPk_org(hvo.getPk_org());
				recvo.setPk_org_v(hvo.getPk_org_v());
				recvo.setPk_group(hvo.getPk_group());
				recvo.setBegindate(bvos[i].getDysdate());
				recvo.setGatherdate(bvos[i].getDysdate());
				recvo.setCaccountperiod(bvos[i].getCaccountperiod());
				recvo.setNrecmoney(bvos[i].getNysmny());
				recvo.setNnotaxmoney(bvos[i].getNysmny());
				recvo.setNtaxmoney(new UFDouble(0));
				recvo.setNtaxrate(new UFDouble(0));
				recvo.setVbillstatus(1);
				recvo.setDbilldate(new UFDate());
				recvo.setCreator(getModel().getContext().getPk_loginUser());
				recvo.setCreationtime(new UFDateTime());
				recvo.setApprover(getModel().getContext().getPk_loginUser());
				recvo.setApprovetime(new UFDateTime());
				recvo.setBilltypecode("H620");
				String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode='H620'";
				Object pk_billtype = iQ.executeQuery(sql, new ColumnProcessor());
				recvo.setPk_billtype(getStgObj(pk_billtype));
				recvo.setVsrcid(hvo.getPk_prepayment());
				recvo.setVsrctype(hvo.getPk_billtype());
				recvo.setVdef1(bvos[i].getPk_prepayment_b());
				recvo.setVdef2("zl_prepayment_b");
				AggRecbillVO agg=new AggRecbillVO();
				agg.setParentVO(recvo);
				list.add(agg);
				
				//录入待收入确认单
				ConfirmationVO chvo = new ConfirmationVO();
				chvo.setChargingproject(bvos[i].getPk_costproject());
				chvo.setCaccountperiod(bvos[i].getCaccountperiod());
				chvo.setAmountreceivable(bvos[i].getNysmny());
				chvo.setAmountconfirmed(new UFDouble(0));
				//chvo.setVbillno(getVbillCode("H531",1));
				chvo.setPk_org(hvo.getPk_org());
				chvo.setPk_org_v(hvo.getPk_org_v());
				chvo.setPk_group(hvo.getPk_group());
				chvo.setPk_customer(bvos[i].getPk_customer());
				chvo.setPk_project(hvo.getPk_project());
				chvo.setDreccollectdate(hvo.getDysdate());
				chvo.setCaccountperiod(bvos[i].getCaccountperiod());
				chvo.setDbilldate(new UFDate());
				chvo.setDfeestartdate(bvos[i].getDysdate());
				chvo.setVbillstatus(1);
				chvo.setCreator(getModel().getContext().getPk_loginUser());
				chvo.setCreationtime(new UFDateTime());
				chvo.setApprover(getModel().getContext().getPk_loginUser());
				chvo.setApprovetime(new UFDateTime());
				chvo.setVbilltypecode("H640");
				String sql1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
				Object pk_billtype1 = iQ.executeQuery(sql1, new ColumnProcessor());
				chvo.setPk_billtype(getStgObj(pk_billtype1));
				chvo.setVsrcid(hvo.getPk_prepayment());
				chvo.setVsrctype(hvo.getPk_billtype());
				chvo.setNtaxrate(new UFDouble(0));
				chvo.setNnotaxmny(bvos[i].getNysmny());
				chvo.setNtaxmny(new UFDouble(0));
				chvo.setVdef1(bvos[i].getPk_prepayment_b());
				chvo.setVdef2("zl_prepayment_b");
				AggConfirmationVO aggcvo = new AggConfirmationVO();
				aggcvo.setParentVO(chvo);
				cbvolist.add(aggcvo);
			}
			if(list.size()>0){
				cwf.insert(list.toArray(new AggRecbillVO[list.size()]), null);
			}
			if(cbvolist.size() > 0){
				lyw_cm.insert(cbvolist.toArray(new AggConfirmationVO[cbvolist.size()]), null);
			}
		}
		super.doAction(e);
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
