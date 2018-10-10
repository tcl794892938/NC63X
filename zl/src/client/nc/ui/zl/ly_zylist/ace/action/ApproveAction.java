package nc.ui.zl.ly_zylist.ace.action;

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
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.zl.ly_zylist.ZylistSrVO;
import nc.vo.zl.ly_zylist.ZylistVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends ApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		//System.out.println("123");
		
		Object obj = getModel().getSelectedData();
		AggZylistVO aggvo = (AggZylistVO) obj;
		ZylistVO zvo = aggvo.getParentVO();
		ZylistSrVO[] zsvos = (ZylistSrVO[]) aggvo.getChildrenVO();
		
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(
				IUAPQueryBS.class);
		ILyw_confirmationMaintain lyw=NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
		ICwf_recbillMaintain cwf=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
		
		//作业单有表体审批通过自动传应收单
		if (zsvos.length > 0) {
			super.doAction(e);
			//List<RecbillVO> rlist = new ArrayList<RecbillVO>();
			List<AggRecbillVO> aggrlist=new ArrayList<AggRecbillVO>();
			List<AggConfirmationVO> aggclist=new ArrayList<AggConfirmationVO>();
			for (int i=0;i<zsvos.length;i++) {
				Double ygs=Double.parseDouble(zsvos[i].getYgsmoney().toString());
				if(ygs==0){
					continue;
				}
				RecbillVO rvo = new RecbillVO();
				rvo.setPk_org(zvo.getPk_org());
				rvo.setPk_org_v(zvo.getPk_org_v());
				rvo.setPk_group(zvo.getPk_group());
				rvo.setPk_project(zvo.getPk_project());
				rvo.setDbilldate(new UFDate());
				rvo.setCreator(AppContext.getInstance().getPkUser());
				rvo.setCreationtime(AppContext.getInstance().getServerTime());
				rvo.setApprover(AppContext.getInstance().getPkUser());
				rvo.setApprovetime(AppContext.getInstance().getServerTime());
				//rvo.setVbillcode(getVbillCode(i));
				rvo.setPk_house(zvo.getFcname());
				rvo.setPk_building(zvo.getPk_building());
				rvo.setPk_customer(zvo.getKhname());
				rvo.setPk_costproject(zsvos[i].getPayproject());
				rvo.setVsrcid(zvo.getPk_zylist());
				rvo.setVsrctype(zvo.getPk_billtype());

				String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode='H620'";
				Object pk_billtype = iQ.executeQuery(sql, new ColumnProcessor());
				rvo.setPk_billtype(getStgObj(pk_billtype));
				rvo.setBilltypecode("H620");
				rvo.setVbillstatus(1);
				rvo.setBegindate(zsvos[i].getFinishtime().getDate());
				rvo.setGatherdate(zsvos[i].getFinishtime().getDate());
				rvo.setCaccountperiod(zsvos[i].getCaccountperiod());
				rvo.setNrecmoney(zsvos[i].getYgsmoney());
				rvo.setVdef1(zsvos[i].getPk_zylist_sr());
				rvo.setVdef2("zl_zylist_sr");
				//rlist.add(rvo);
				AggRecbillVO revo=new AggRecbillVO();
				revo.setParentVO(rvo);
				aggrlist.add(revo);
				
				//传待收入确认
				ConfirmationVO vo=new ConfirmationVO();
				vo.setPk_customer(zvo.getKhname());
				vo.setPk_org(zvo.getPk_org());
				vo.setPk_group(zvo.getPk_group());
				vo.setPk_org_v(zvo.getPk_org_v());
				vo.setDbilldate(new UFDate());
				vo.setDreccollectdate(zsvos[i].getFinishtime().getDate());
				vo.setPk_project(zvo.getPk_project());
				vo.setCreator(AppContext.getInstance().getPkUser());
				vo.setCreationtime(AppContext.getInstance().getServerTime());
				vo.setApprover(AppContext.getInstance().getPkUser());
				vo.setApprovetime(AppContext.getInstance().getServerTime());
				vo.setVbillstatus(1);
				vo.setVbilltypecode("H640");
				String sql1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
				Object pk_billtype1 = iQ.executeQuery(sql1, new ColumnProcessor());
				vo.setPk_billtype(getStgObj(pk_billtype1));
				vo.setVsrcid(zvo.getPk_zylist());
				vo.setVsrctype(zvo.getPk_billtype());
				vo.setBuildno(zvo.getPk_building());
				vo.setHouseproperty(zvo.getFcname());
				vo.setChargingproject(zsvos[i].getPayproject());
				vo.setDfeestartdate(zsvos[i].getFinishtime().getDate());
				vo.setCaccountperiod(zsvos[i].getCaccountperiod());
				vo.setAmountreceivable(zsvos[i].getYgsmoney());
				vo.setAmountconfirmed(new UFDouble(0));
				vo.setNtaxrate(new UFDouble(0));
				vo.setNnotaxmny(zsvos[i].getYgsmoney());
				vo.setNtaxmny(new UFDouble(0));
				vo.setVdef1(zsvos[i].getPk_zylist_sr());
				vo.setVdef2("zl_zylist_sr");
				AggConfirmationVO aggcvo=new AggConfirmationVO();
				aggcvo.setParentVO(vo);
				aggclist.add(aggcvo);
			}
			
			if(aggrlist.size()>0){
				cwf.insert(aggrlist.toArray(new AggRecbillVO[aggrlist.size()]), null);
			}
			if(aggclist.size()>0){
				lyw.insert(aggclist.toArray(new AggConfirmationVO[aggclist.size()]), null);
			}
			
		}
	}
	
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}
