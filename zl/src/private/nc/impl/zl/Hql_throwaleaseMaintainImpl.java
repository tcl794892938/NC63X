package nc.impl.zl;

import java.util.ArrayList;
import java.util.List;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.ace.AceHql_throwaleasePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.zl.hql_throwalease.ThrowaleaseVO;
import nc.vo.zl.hql_throwalease.Throwalease_bzjthVO;
import nc.vo.zl.hql_throwalease.Throwalease_fyqsVO;
import nc.vo.zl.hql_throwalease.Throwalease_khfcVO;
import nc.vo.zl.hql_throwalease.Throwalease_zqfyqsVO;
import nc.vo.zl.ld_housesource.HousesourceVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.vo.zl.tcl_contract.ContractCwcfVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;
import nc.vo.zl.tcl_contract.ContractZqfyVO;
import nc.vo.zl.tcl_contract.ContractZqfycfVO;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.IHql_throwaleaseMaintain;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class Hql_throwaleaseMaintainImpl extends AceHql_throwaleasePubServiceImpl
		implements IHql_throwaleaseMaintain {

	@Override
	public void delete(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggThrowaleaseVO[] insert(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggThrowaleaseVO[] update(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggThrowaleaseVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggThrowaleaseVO[] save(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggThrowaleaseVO[] unsave(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggThrowaleaseVO[] approve(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		//return super.pubapprovebills(clientFullVOs, originBills);
		AggThrowaleaseVO[] aggvos = super.pubapprovebills(clientFullVOs, originBills);
		if(aggvos.length < 1){
			throw new BusinessException("请选择一条单据！");
		}
		ThrowaleaseVO hvo = aggvos[0].getParentVO();
		Throwalease_khfcVO[] bvo = (Throwalease_khfcVO[]) aggvos[0].getChildren(Throwalease_khfcVO.class);
		if(hvo.getVbillstatus() == 1){
			String contractid = hvo.getContractid();
			BaseDAO dao = new BaseDAO();
			
			String pks="";
			for(int i=0;i<bvo.length;i++){
				Object pkh=bvo[i].getPk_housesource();
				pks+="'"+pkh+"'";
				if(i<bvo.length-1){
					pks+=",";
				}
			}
			
			String sql_tz = "select pk_accperiodmonth from bd_accperiodmonth where " +
				     "nvl(dr,0)=0 and (begindate <= '"+hvo.getDtzdate()+"' and enddate >= '"+hvo.getDtzdate()+"')";
			String pk_tz = dao.executeQuery(sql_tz, new ColumnProcessor()).toString();
			
			//没有收款的应收单作废
			String sql="select * from zl_recbill where nvl(dr,0)=0 and begindate>='"+hvo.getDtzdate()+"' " +
					"and vsrcid='"+hvo.getVsrcid()+"' and vdef2 in ('zl_contract_ywcf','zl_contract_zqfycf') and pk_house in ("+pks+") and pk_recbill " +
							"not in (select b.vsrcid from zl_gather_b b where nvl(b.dr,0)=0 and b.vdef2 in ('zl_contract_ywcf','zl_contract_zqfycf'))";
			List<RecbillVO> reclist=(List<RecbillVO>)dao.executeQuery(sql, new BeanListProcessor(RecbillVO.class));
			
			String pk_yw="";
			String pk_zq="";
			if(reclist!=null&&reclist.size()>0){
				for(int i=0;i<reclist.size();i++){
					reclist.get(i).setDr(2);
					if(reclist.get(i).getVdef2().equals("zl_contract_ywcf")){
						Object pk1=reclist.get(i).getVdef1();
						pk_yw+="'"+pk1+"',";
					}
					if(reclist.get(i).getVdef2().equals("zl_contract_zqfycf")){
						Object pk1=reclist.get(i).getVdef1();
						pk_zq+="'"+pk1+"',";
					}
				}
				dao.updateVOArray(reclist.toArray(new RecbillVO[reclist.size()]));
			}
			
			if(pk_yw.length()>0){
				pk_yw=pk_yw.substring(0,pk_yw.length()-1);
			}
			if(pk_zq.length()>0){
				pk_zq=pk_zq.substring(0,pk_zq.length()-1);
			}
			
			//没有确认收入的待收入确认单作废
			String sql_bill="select * from zl_confirmation where nvl(dr,0)=0 and dfeestartdate>='"+hvo.getDtzdate()+"' " +
					"and vsrcid='"+hvo.getVsrcid()+"' and houseproperty in ("+pks+") and pk_confirmation " +
							"not in (select b.vsrcid from zl_billconfirmedb b where nvl(b.dr,0)=0)";
			List<ConfirmationVO> conclist=(List<ConfirmationVO>)dao.executeQuery(sql_bill, new BeanListProcessor(ConfirmationVO.class));
			
			if(conclist!=null&&conclist.size()>0){
				for(int i=0;i<conclist.size();i++){
					conclist.get(i).setDr(2);
				}
				dao.updateVOArray(conclist.toArray(new ConfirmationVO[conclist.size()]));
			}
			
			//回写房源状态变成空置
			String isno="select count(*) from zl_contract_house h where nvl(h.dr,0)=0 and h.pk_house in ("+pks+") and exists " +
					"(select 1 from zl_contract c where nvl(c.dr,0)=0 and c.pk_contract=h.pk_contract and c.vbillstatus=1 and c.version=-1 " +
					"and c.pk_contract!='"+hvo.getVsrcid()+"' and c.htstatus<>4)";
			Integer no=(Integer) dao.executeQuery(isno, new ColumnProcessor());
			if(no<=0){
				String sql_house = "select * from zl_housesource where nvl(dr,0)=0 and pk_housesource in ("+pks+")";
				List<HousesourceVO> houselist = (List<HousesourceVO>) dao.executeQuery(sql_house, new BeanListProcessor(HousesourceVO.class));
				for(int i = 0;i < houselist.size();i++){
					houselist.get(i).setHousestate(0);
				}
				dao.updateVOArray(houselist.toArray(new HousesourceVO[houselist.size()]));
			}
			
			//回写合同表体作废
			//房产信息页签
			String fcsql="select * from zl_contract_house where nvl(dr,0)=0 and pk_house in ("+pks+") and pk_contract='"+hvo.getVsrcid()+"'";
			List<ContractHouseVO> chlist=(List<ContractHouseVO>) dao.executeQuery(fcsql, new BeanListProcessor(ContractHouseVO.class));
			if(chlist!=null&&chlist.size()>0){
				for(int i=0;i<chlist.size();i++){
					chlist.get(i).setVdef5("Y");
				}
				dao.updateVOArray(chlist.toArray(new ContractHouseVO[chlist.size()]));
			}
			UFDate sdate=new UFDate();
			if(pk_yw!=null&&!pk_yw.equals("")){
				//业务拆分
				String ywcf="select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_contract_ywcf in ("+pk_yw+")";
				List<ContractYwcfVO> ywcflist=(List<ContractYwcfVO>) dao.executeQuery(ywcf, new BeanListProcessor(ContractYwcfVO.class));
				sdate=ywcflist.get(0).getDstartdate();
				if(ywcflist!=null&&ywcflist.size()>0){
					for(int i=0;i<ywcflist.size();i++){
						ywcflist.get(i).setDr(2);
					}
					dao.updateVOArray(ywcflist.toArray(new ContractYwcfVO[ywcflist.size()]));
				}
				//财务拆分
				String cwcf="select * from zl_contract_cwcf where nvl(dr,0)=0 and pk_house in ("+pks+") and pk_contract='"+hvo.getVsrcid()+"' and " +
						"dstartdate>='"+sdate+"'";
				List<ContractCwcfVO> cwcflist=(List<ContractCwcfVO>)dao.executeQuery(cwcf, new BeanListProcessor(ContractCwcfVO.class));
				if(cwcflist!=null&&cwcflist.size()>0){
					for(int i=0;i<cwcflist.size();i++){
						cwcflist.get(i).setDr(2);
					}
					dao.updateVOArray(cwcflist.toArray(new ContractCwcfVO[cwcflist.size()]));
				}
				
			}
			if(pk_zq!=null&&!pk_zq.equals("")){
				//周期费用拆分
				String zq2="select * from zl_contract_zqfycf where nvl(dr,0)=0 and pk_house in("+pks+") and pk_contract_zqfycf in ("+pk_zq+")";
				List<ContractZqfycfVO> zqlist2=(List<ContractZqfycfVO>)dao.executeQuery(zq2, new BeanListProcessor(ContractZqfycfVO.class));
				if(zqlist2!=null&&zqlist2.size()>0){
					for(int i=0;i<zqlist2.size();i++){
						zqlist2.get(i).setDr(2);
					}
					dao.updateVOArray(zqlist2.toArray(new ContractZqfycfVO[zqlist2.size()]));
				}
				
			}
			
			ILyw_confirmationMaintain lyw = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
			ICwf_recbillMaintain cwf=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
			String get_pkbill = "select pk_billtypeid from bd_billtype where pk_billtypecode='H620'";
			Object pk_billtype = dao.executeQuery(get_pkbill, new ColumnProcessor());
			String sql_fcxx = "select * from zl_housesource where nvl(dr,0)=0 and " +
					"pk_housesource in ("+pks+")";
			List<HousesourceVO> hsvo=(List<HousesourceVO>) dao.executeQuery(sql_fcxx, new BeanListProcessor(HousesourceVO.class));
			//费用清算推应收
			Throwalease_fyqsVO[] fyqs=(Throwalease_fyqsVO[]) aggvos[0].getChildren(Throwalease_fyqsVO.class);
			//List<RecbillVO> rlist=new ArrayList<RecbillVO>();
			List<AggRecbillVO> aggrlist=new ArrayList<AggRecbillVO>();
			List<AggConfirmationVO> aggclist=new ArrayList<AggConfirmationVO>();
			for(int i=0;i<fyqs.length;i++){
				Double js=Double.parseDouble(fyqs[i].getNjsmny().toString());
				if(js!=0){
					RecbillVO recvo=new RecbillVO();
					recvo.setPk_org(hvo.getPk_org());
					recvo.setPk_org_v(hvo.getPk_org_v());
					recvo.setPk_group(hvo.getPk_group());
					recvo.setPk_project(hvo.getPk_project());
					recvo.setDbilldate(new UFDate());
					recvo.setCreator(AppContext.getInstance().getPkUser());
					recvo.setCreationtime(AppContext.getInstance().getServerTime());
					recvo.setApprover(AppContext.getInstance().getPkUser());
					recvo.setApprovetime(AppContext.getInstance().getServerTime());
					//recvo.setVbillcode(getVbillCode(i, hvo.getPk_billtype()));
					recvo.setPk_customer(fyqs[i].getPk_customer());
					recvo.setPk_house(fyqs[i].getPk_housesource());
					
					for(int j=0;j<hsvo.size();j++){
						if(hsvo.get(j).getPk_housesource().equals(recvo.getPk_house())){
							recvo.setPk_building(hsvo.get(j).getBuildname());
						}
					}
					
					recvo.setPk_costproject(fyqs[i].getPk_costproject());
					recvo.setVsrcid(hvo.getPk_throwalease());
					recvo.setVsrctype(hvo.getPk_billtype());
					recvo.setPk_billtype(getStgObj(pk_billtype));
					recvo.setBilltypecode("H620");
					recvo.setVbillstatus(1);
					recvo.setBegindate(hvo.getDtzdate());
					//recvo.setEnddate(CalendarUtls.getBeforeFirstDay(eenddate));
					recvo.setGatherdate(hvo.getDtzdate());
					recvo.setCaccountperiod(pk_tz);
					recvo.setNrecmoney(new UFDouble(0-js));
					recvo.setNtaxrate(fyqs[i].getNtaxrate());
					recvo.setNnotaxmoney(new UFDouble(0).sub(fyqs[i].getNnotaxmoney()));
					recvo.setNtaxmoney(new UFDouble(0).sub(fyqs[i].getNtaxmny()));
					recvo.setVdef1(fyqs[i].getPk_throwaleasefyqs());
					recvo.setVdef2("zl_throwalease_fyqs");
					//rlist.add(recvo);
					AggRecbillVO aggrevo=new AggRecbillVO();
					aggrevo.setParentVO(recvo);
					aggrlist.add(aggrevo);
				}
				
				//传待收入确认
				Double recqr=Double.parseDouble(fyqs[i].getRecqr().toString());
				if(recqr!=0){
					ConfirmationVO vo=new ConfirmationVO();
					vo.setPk_customer(fyqs[0].getPk_customer());
					vo.setPk_org(hvo.getPk_org());
					vo.setPk_group(hvo.getPk_group());
					vo.setPk_org_v(hvo.getPk_org_v());
					vo.setDbilldate(new UFDate());
					vo.setCreator(AppContext.getInstance().getPkUser());
					vo.setCreationtime(AppContext.getInstance().getServerTime());
					vo.setApprover(AppContext.getInstance().getPkUser());
					vo.setApprovetime(AppContext.getInstance().getServerTime());
					vo.setPk_project(hvo.getPk_project());
					vo.setVbillstatus(1);
					vo.setVbilltypecode("H640");
					String sql1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
					Object pk_billtype1 = dao.executeQuery(sql1, new ColumnProcessor());
					vo.setPk_billtype(getStgObj(pk_billtype1));
					vo.setVsrcid(hvo.getPk_throwalease());
					vo.setVsrctype(hvo.getPk_billtype());
					vo.setCaccountperiod(pk_tz);
					vo.setHouseproperty(fyqs[i].getPk_housesource());
					for(int j=0;j<hsvo.size();j++){
						if(hsvo.get(j).getPk_housesource().equals(vo.getHouseproperty())){
							vo.setBuildno(hsvo.get(j).getBuildname());
						}
					}
					vo.setChargingproject(fyqs[i].getPk_costproject());
					vo.setDfeestartdate(hvo.getDtzdate());
					vo.setDcollectiondate(hvo.getDtzdate());
					vo.setDreccollectdate(hvo.getDtzdate());
					if(fyqs[i].getRecqr()==null){
						vo.setAmountreceivable(new UFDouble(0));
						vo.setNnotaxmny(new UFDouble(0));
					}else{
						vo.setAmountreceivable(new UFDouble(0).sub(fyqs[i].getRecqr()));
						vo.setNnotaxmny(new UFDouble(0).sub(fyqs[i].getRecqr().div(fyqs[i].getNtaxrate().add(100)).multiply(100)));
					}
					vo.setAmountconfirmed(new UFDouble(0));
					vo.setNtaxrate(fyqs[i].getNtaxrate());
					vo.setNtaxmny(vo.getAmountreceivable().sub(vo.getNnotaxmny()));
					vo.setVdef1(fyqs[i].getPk_throwaleasefyqs());
					vo.setVdef2("zl_throwalease_fyqs");
					AggConfirmationVO aggcvo=new AggConfirmationVO();
					aggcvo.setParentVO(vo);
					aggclist.add(aggcvo);
				}
				
			}
			if(aggrlist.size()>0){
				cwf.insert(aggrlist.toArray(new AggRecbillVO[aggrlist.size()]), null);
			}
			if(aggclist.size()>0){
				lyw.insert(aggclist.toArray(new AggConfirmationVO[aggclist.size()]), null);
			}
			
			//保证金推应收
			Throwalease_bzjthVO[] bzjvo=(Throwalease_bzjthVO[]) aggvos[0].getChildren(Throwalease_bzjthVO.class);
			if(bzjvo.length>0&&bzjvo!=null){
				List<AggRecbillVO> aggrlist1=new ArrayList<AggRecbillVO>();
				List<AggConfirmationVO> aggclist1=new ArrayList<AggConfirmationVO>();
				
				Double js=Double.parseDouble(bzjvo[0].getNjsmny().toString());
				if(js!=0){
					RecbillVO recvo=new RecbillVO();
					recvo.setPk_org(hvo.getPk_org());
					recvo.setPk_org_v(hvo.getPk_org_v());
					recvo.setPk_group(hvo.getPk_group());
					recvo.setPk_project(hvo.getPk_project());
					recvo.setDbilldate(new UFDate());
					recvo.setCreator(AppContext.getInstance().getPkUser());
					recvo.setCreationtime(AppContext.getInstance().getServerTime());
					recvo.setApprover(AppContext.getInstance().getPkUser());
					recvo.setApprovetime(AppContext.getInstance().getServerTime());
					recvo.setPk_customer(bzjvo[0].getPk_customer());
					recvo.setPk_costproject(bzjvo[0].getPk_costproject());
					recvo.setVsrcid(hvo.getPk_throwalease());
					recvo.setVsrctype(hvo.getPk_billtype());
					recvo.setPk_billtype(getStgObj(pk_billtype));
					recvo.setBilltypecode("H620");
					recvo.setVbillstatus(1);
					recvo.setBegindate(hvo.getDtzdate());
					recvo.setGatherdate(hvo.getDtzdate());
					recvo.setCaccountperiod(pk_tz);
					recvo.setNrecmoney(new UFDouble(0-js));
					recvo.setNtaxmoney(new UFDouble(0));
					recvo.setNtaxrate(new UFDouble(0));
					recvo.setNnotaxmoney(new UFDouble(0-js));
					recvo.setVdef1(bzjvo[0].getPk_throwaleasebzjth());
					recvo.setVdef2("zl_throwalease_bzjth");
					AggRecbillVO aggrevo=new AggRecbillVO();
					aggrevo.setParentVO(recvo);
					aggrlist1.add(aggrevo);
					cwf.insert(aggrlist1.toArray(new AggRecbillVO[aggrlist1.size()]), null);
					
					//保证金推待收入确认
					ConfirmationVO vo=new ConfirmationVO();
					vo.setChargingproject(bzjvo[0].getPk_costproject());
					vo.setDfeestartdate(hvo.getDtzdate());
					vo.setDcollectiondate(hvo.getDtzdate());
					vo.setDreccollectdate(hvo.getDtzdate());
					vo.setAmountreceivable(new UFDouble(0-js));
					vo.setAmountconfirmed(new UFDouble(0));
					vo.setPk_customer(bzjvo[0].getPk_customer());
					vo.setPk_org(hvo.getPk_org());
					vo.setPk_group(hvo.getPk_group());
					vo.setPk_org_v(hvo.getPk_org_v());
					vo.setDbilldate(new UFDate());
					vo.setPk_project(hvo.getPk_project());
					vo.setCreator(AppContext.getInstance().getPkUser());
					vo.setCreationtime(AppContext.getInstance().getServerTime());
					vo.setApprover(AppContext.getInstance().getPkUser());
					vo.setApprovetime(AppContext.getInstance().getServerTime());
					vo.setVbillstatus(1);
					vo.setVbilltypecode("H640");
					String sql1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
					Object pk_billtype1 = dao.executeQuery(sql1, new ColumnProcessor());
					vo.setPk_billtype(getStgObj(pk_billtype1));
					vo.setVsrcid(hvo.getPk_throwalease());
					vo.setVsrctype(hvo.getPk_billtype());
					vo.setCaccountperiod(pk_tz);
					vo.setNtaxrate(new UFDouble(0));
					vo.setNnotaxmny(new UFDouble(0-js));
					vo.setNtaxmny(new UFDouble(0));
					vo.setVdef1(bzjvo[0].getPk_throwaleasebzjth());
					vo.setVdef2("zl_throwalease_bzjth");
					AggConfirmationVO aggcvo=new AggConfirmationVO();
					aggcvo.setParentVO(vo);
					aggclist1.add(aggcvo);
					lyw.insert(aggclist1.toArray(new AggConfirmationVO[aggclist1.size()]), null);
					
				}
			}
			
			//周期费用清算
			Throwalease_zqfyqsVO[] zqfyvo=(Throwalease_zqfyqsVO[]) aggvos[0].getChildren(Throwalease_zqfyqsVO.class);
			if(zqfyvo.length>0&&zqfyvo!=null){
				List<AggRecbillVO> aggrlist2=new ArrayList<AggRecbillVO>();
				List<AggConfirmationVO> aggclist2=new ArrayList<AggConfirmationVO>();
				
				for(int i=0;i<zqfyvo.length;i++){
					Double yt=Double.parseDouble(zqfyvo[i].getNskmny().toString());
					if(yt!=0){
						RecbillVO recvo=new RecbillVO();
						recvo.setPk_org(hvo.getPk_org());
						recvo.setPk_org_v(hvo.getPk_org_v());
						recvo.setPk_group(hvo.getPk_group());
						recvo.setPk_project(hvo.getPk_project());
						recvo.setDbilldate(new UFDate());
						recvo.setCreator(AppContext.getInstance().getPkUser());
						recvo.setCreationtime(AppContext.getInstance().getServerTime());
						recvo.setApprover(AppContext.getInstance().getPkUser());
						recvo.setApprovetime(AppContext.getInstance().getServerTime());
						//recvo.setVbillcode(getVbillCode(i, hvo.getPk_billtype()));
						recvo.setPk_customer(zqfyvo[i].getPk_customer());
						recvo.setPk_house(zqfyvo[i].getPk_housesource());
						
						for(int j=0;j<hsvo.size();j++){
							if(hsvo.get(j).getPk_housesource().equals(recvo.getPk_house())){
								recvo.setPk_building(hsvo.get(j).getBuildname());
							}
						}
						
						recvo.setPk_costproject(zqfyvo[i].getPk_costproject());
						recvo.setVsrcid(hvo.getPk_throwalease());
						recvo.setVsrctype(hvo.getPk_billtype());
						recvo.setPk_billtype(getStgObj(pk_billtype));
						recvo.setBilltypecode("H620");
						recvo.setVbillstatus(1);
						recvo.setBegindate(hvo.getDtzdate());
						recvo.setGatherdate(hvo.getDtzdate());
						//recvo.setEnddate(CalendarUtls.getBeforeFirstDay(eenddate));
						recvo.setCaccountperiod(pk_tz);
						recvo.setNrecmoney(new UFDouble(0-yt));
						recvo.setNtaxrate(zqfyvo[i].getNtaxrate());
						recvo.setNnotaxmoney(new UFDouble(0).sub(zqfyvo[i].getNnotaxmoney()));
						recvo.setNtaxmoney(new UFDouble(0).sub(zqfyvo[i].getNtaxmny()));
						recvo.setVdef1(zqfyvo[i].getPk_throwalease_zqfyqs());
						recvo.setVdef2("zl_throwalease_zqfyqs");
						//rlist.add(recvo);
						AggRecbillVO aggrevo=new AggRecbillVO();
						aggrevo.setParentVO(recvo);
						aggrlist2.add(aggrevo);
					}
					
					//传待收入确认
					Double recqr=Double.parseDouble(zqfyvo[i].getRecqr().toString());
					if(recqr!=0){
						ConfirmationVO vo=new ConfirmationVO();
						vo.setPk_customer(zqfyvo[0].getPk_customer());
						vo.setPk_org(hvo.getPk_org());
						vo.setPk_group(hvo.getPk_group());
						vo.setPk_org_v(hvo.getPk_org_v());
						vo.setDbilldate(new UFDate());
						vo.setCreator(AppContext.getInstance().getPkUser());
						vo.setCreationtime(AppContext.getInstance().getServerTime());
						vo.setApprover(AppContext.getInstance().getPkUser());
						vo.setApprovetime(AppContext.getInstance().getServerTime());
						vo.setPk_project(hvo.getPk_project());
						vo.setVbillstatus(1);
						vo.setVbilltypecode("H640");
						String sql1 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
						Object pk_billtype1 = dao.executeQuery(sql1, new ColumnProcessor());
						vo.setPk_billtype(getStgObj(pk_billtype1));
						vo.setVsrcid(hvo.getPk_throwalease());
						vo.setVsrctype(hvo.getPk_billtype());
						vo.setCaccountperiod(pk_tz);
						vo.setHouseproperty(zqfyvo[i].getPk_housesource());
						for(int j=0;j<hsvo.size();j++){
							if(hsvo.get(j).getPk_housesource().equals(vo.getHouseproperty())){
								vo.setBuildno(hsvo.get(j).getBuildname());
							}
						}
						vo.setChargingproject(zqfyvo[i].getPk_costproject());
						vo.setDfeestartdate(hvo.getDtzdate());
						vo.setDcollectiondate(hvo.getDtzdate());
						if(zqfyvo[i].getRecqr()==null){
							vo.setAmountreceivable(new UFDouble(0));
							vo.setNnotaxmny(new UFDouble(0));
						}else{
							vo.setAmountreceivable(new UFDouble(0).sub(zqfyvo[i].getRecqr()));
							vo.setNnotaxmny(new UFDouble(0).sub(zqfyvo[i].getRecqr().div(zqfyvo[i].getNtaxrate().add(100)).multiply(100)));
						}
						vo.setAmountconfirmed(new UFDouble(0));
						vo.setNtaxrate(zqfyvo[i].getNtaxrate());
						vo.setNtaxmny(vo.getAmountreceivable().sub(vo.getNnotaxmny()));
						vo.setVdef1(zqfyvo[i].getPk_throwalease_zqfyqs());
						vo.setVdef2("zl_throwalease_zqfyqs");
						vo.setDreccollectdate(hvo.getDtzdate());
						AggConfirmationVO aggcvo=new AggConfirmationVO();
						aggcvo.setParentVO(vo);
						aggclist2.add(aggcvo);
					}
				}
				if(aggrlist2.size()>0){
					cwf.insert(aggrlist2.toArray(new AggRecbillVO[aggrlist2.size()]), null);
				}
				if(aggclist2.size()>0){
					lyw.insert(aggclist2.toArray(new AggConfirmationVO[aggclist2.size()]), null);
				}
				
			}
			
			String sql_house2="select * from zl_contract_house where nvl(dr,0)=0 and vdef5='N' and pk_contract='"+hvo.getVsrcid()+"'";
			List<ContractHouseVO> housevo=(List<ContractHouseVO>) dao.executeQuery(sql_house2, new BeanListProcessor(ContractHouseVO.class));
			if(housevo.size()==0||housevo==null){
				//回写合同状态变成退租
				String sql_contract = "update zl_contract set htstatus = 4,doutdate = '"+bvo[0].getTzdate()+"'" +
						" where nvl(dr,0)=0 and version = -1 and htcode = '"+contractid+"'";
				dao.executeUpdate(sql_contract);
				
				//保证金应收收款作废
				/*String sql_rec="update zl_recbill set dr=2 where nvl(dr,0)=0 and vsrcid='"+hvo.getVsrcid()+"' and vdef2='zl_contract_bzj'";
				dao.executeUpdate(sql_rec);
				String sql_gar="update zl_gather_b set dr=2 where nvl(dr,0)=0 and firstpk='"+hvo.getVsrcid()+"' and vdef2='zl_contract_bzj'";
				dao.executeUpdate(sql_gar);*/
				
			}
			
			String sql_ywcf="select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_contract='"+hvo.getVsrcid()+"'";
			List<ContractYwcfVO> ywcfvo=(List<ContractYwcfVO>)dao.executeQuery(sql_ywcf, new BeanListProcessor(ContractYwcfVO.class));
			if(ywcfvo.size()!=0&&ywcfvo!=null){
				for(int i=0;i<ywcfvo.size();i++){
					ContractYwcfVO cyvo=ywcfvo.get(i);
					cyvo.setRowno((i+1)*10+"");
				}
				dao.updateVOArray(ywcfvo.toArray(new ContractYwcfVO[ywcfvo.size()]));
			}
			
			String sql_cwcf="select * from zl_contract_cwcf where nvl(dr,0)=0 and pk_contract='"+hvo.getVsrcid()+"'";
			List<ContractCwcfVO> cwcfvo=(List<ContractCwcfVO>)dao.executeQuery(sql_cwcf, new BeanListProcessor(ContractCwcfVO.class));
			if(cwcfvo.size()!=0&&cwcfvo!=null){
				for(int i=0;i<cwcfvo.size();i++){
					ContractCwcfVO ccvo=cwcfvo.get(i);
					ccvo.setRowno((i+1)*10+"");
				}
				dao.updateVOArray(cwcfvo.toArray(new ContractCwcfVO[cwcfvo.size()]));
			}
			
			String sql_zqfy="select * from zl_contract_zqfy where nvl(dr,0)=0 and pk_contract='"+hvo.getVsrcid()+"'";
			List<ContractZqfyVO> fylist=(List<ContractZqfyVO>)dao.executeQuery(sql_zqfy, new BeanListProcessor(ContractZqfyVO.class));
			if(fylist.size()!=0&&fylist!=null){
				for(int i=0;i<fylist.size();i++){
					ContractZqfyVO fyvo=fylist.get(i);
					fyvo.setRowno((i+1)*10+"");
				}
				dao.updateVOArray(fylist.toArray(new ContractZqfyVO[fylist.size()]));
			}
			
			String sql_zqcf="select * from zl_contract_zqfycf where nvl(dr,0)=0 and pk_contract='"+hvo.getVsrcid()+"'";
			List<ContractZqfycfVO> cflist=(List<ContractZqfycfVO>)dao.executeQuery(sql_zqcf, new BeanListProcessor(ContractZqfycfVO.class));
			if(cflist.size()!=0&&cflist!=null){
				for(int i=0;i<cflist.size();i++){
					ContractZqfycfVO cfvo=cflist.get(i);
					cfvo.setRowno((i+1)*10+"");
				}
				dao.updateVOArray(cflist.toArray(new ContractZqfycfVO[cflist.size()]));
			}
			
		}
		
		
		return aggvos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggThrowaleaseVO[] unapprove(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		AggThrowaleaseVO[] bills = super.pubunapprovebills(clientFullVOs, originBills);
		if(bills.length < 1){
			throw new BusinessException("请选择一条单据！");
		}
		Object obj = bills[0].getParentVO().getPk_throwalease();
		Throwalease_khfcVO[] khfc=(Throwalease_khfcVO[])bills[0].getChildren(Throwalease_khfcVO.class);
		
		String pks="";
		for(int i=0;i<khfc.length;i++){
			Object pkh=khfc[i].getPk_housesource();
			pks+="'"+pkh+"'";
			if(i<khfc.length-1){
				pks+=",";
			}
		}
		BaseDAO dao=new BaseDAO();
		String sql="select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in " +
				"(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+getStgObj(obj)+"')";
		Integer gather_c=(Integer) dao.executeQuery(sql, new ColumnProcessor());
		
		String sql1="select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in " +
				"(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+getStgObj(obj)+"')";
		Integer bill_c=(Integer)dao.executeQuery(sql1, new ColumnProcessor());
		
		String sql2="select count(*) from zl_contract_house where nvl(dr,0)=0 and pk_house in ("+pks+") and pk_contract in " +
				"(select c.pk_contract from zl_contract c where nvl(c.dr,0)=0 and version=-1 and htstatus<>4)";
		Integer house_c=(Integer)dao.executeQuery(sql2, new ColumnProcessor());
		
		//过滤条件
		if(gather_c>0){
			throw new BusinessException("生成应收单已被收款单参照，无法取消审批！");
		}
		if(bill_c>0){
			throw new BusinessException("生成待收入确认已被收入确认参照，无法取消审批");
		}
		if(house_c>0){
			throw new BusinessException("该房间已经签订新合同，无法取消审批");
		}
		
		//删除退租推应收、待收入确认
		dao.deleteByClause(RecbillVO.class, " vsrcid='"+getStgObj(obj)+"'");
		dao.deleteByClause(ConfirmationVO.class, " vsrcid='"+getStgObj(obj)+"'");
		
		//回写应收、待收入确认、合同、房源
		String sql_house2="select * from zl_contract_house where nvl(dr,0)=0 and vdef5='N' and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		List<ContractHouseVO> housevo=(List<ContractHouseVO>) dao.executeQuery(sql_house2, new BeanListProcessor(ContractHouseVO.class));
		if(housevo.size()==0||housevo==null){
			String sql_contract = "update zl_contract set htstatus = 3,doutdate = null" +
					" where nvl(dr,0)=0 and version = -1 and pk_contract = '"+bills[0].getParentVO().getVsrcid()+"'";
			dao.executeUpdate(sql_contract);
			
			/*String sql_rec="update zl_recbill set dr=0 where nvl(dr,0)=2 and vsrcid='"+bills[0].getParentVO().getVsrcid()+"' and vdef2='zl_contract_bzj'";
			dao.executeUpdate(sql_rec);
			String sql_gar="update zl_gather_b set dr=0 where nvl(dr,0)=2 and firstpk='"+bills[0].getParentVO().getVsrcid()+"' and vdef2='zl_contract_bzj'";
			dao.executeUpdate(sql_gar);*/
		}
		
		String sql3="update zl_recbill set dr=0 where nvl(dr,0)=2 and begindate>='"+bills[0].getParentVO().getDtzdate()+"' " +
				"and vsrcid='"+bills[0].getParentVO().getVsrcid()+"' and vdef2 in ('zl_contract_ywcf','zl_contract_zqfycf') " +
						"and pk_house in ("+pks+") and pk_recbill not in " +
						"(select b.vsrcid from zl_gather_b b where nvl(b.dr,0)=0 and b.vdef2 in ('zl_contract_ywcf','zl_contract_zqfycf'))";
		dao.executeUpdate(sql3);
		
		String sql_bill="update zl_confirmation set dr=0 where nvl(dr,0)=2 and dfeestartdate>='"+bills[0].getParentVO().getDtzdate()+"' " +
				"and vsrcid='"+bills[0].getParentVO().getVsrcid()+"' and houseproperty in ("+pks+") and pk_confirmation " +
						"not in (select b.vsrcid from zl_billconfirmedb b where nvl(b.dr,0)=0)";
		dao.executeUpdate(sql_bill);
		
		String fcsql="update zl_contract_house set vdef5='N' where nvl(dr,0)=0 and pk_house in ("+pks+") and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		dao.executeUpdate(fcsql);
		
		String ywcf="update zl_contract_ywcf set dr=0 where nvl(dr,0)=2 and pk_house in ("+pks+") and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		dao.executeUpdate(ywcf);
		
		String cwcf="update zl_contract_cwcf set dr=0 where nvl(dr,0)=2 and pk_house in ("+pks+") and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		dao.executeUpdate(cwcf);
		
		String zq1="update zl_contract_zqfy set dr=0 where nvl(dr,0)=2 and pk_house in("+pks+") and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		dao.executeUpdate(zq1);
		
		String zq2="update zl_contract_zqfycf set dr=0 where nvl(dr,0)=2 and pk_house in("+pks+") and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		dao.executeUpdate(zq2);
		
		String sql_house = "update zl_housesource set housestate=3 where nvl(dr,0)=0 and pk_housesource in ("+pks+")";
		dao.executeUpdate(sql_house);
		
		//整理行号
		String sql11="select * from zl_contract_house where nvl(dr,0)=0 and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		List<ContractHouseVO> c11=(List<ContractHouseVO>) dao.executeQuery(sql11, new BeanListProcessor(ContractHouseVO.class));
		for(int i=0;i<c11.size();i++){
			ContractHouseVO chvo=c11.get(i);
			chvo.setRowno((i+1)*10+"");
		}
		dao.updateVOArray(c11.toArray(new ContractHouseVO[c11.size()]));
		String sql_ywcf="select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		List<ContractYwcfVO> ywcfvo=(List<ContractYwcfVO>)dao.executeQuery(sql_ywcf, new BeanListProcessor(ContractYwcfVO.class));
		if(ywcfvo.size()!=0&&ywcfvo!=null){
			for(int i=0;i<ywcfvo.size();i++){
				ContractYwcfVO cyvo=ywcfvo.get(i);
				cyvo.setRowno((i+1)*10+"");
			}
			dao.updateVOArray(ywcfvo.toArray(new ContractYwcfVO[ywcfvo.size()]));
		}
		String sql_cwcf="select * from zl_contract_cwcf where nvl(dr,0)=0 and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		List<ContractCwcfVO> cwcfvo=(List<ContractCwcfVO>)dao.executeQuery(sql_cwcf, new BeanListProcessor(ContractCwcfVO.class));
		if(cwcfvo.size()!=0&&cwcfvo!=null){
			for(int i=0;i<cwcfvo.size();i++){
				ContractCwcfVO ccvo=cwcfvo.get(i);
				ccvo.setRowno((i+1)*10+"");
			}
			dao.updateVOArray(cwcfvo.toArray(new ContractCwcfVO[cwcfvo.size()]));
		}
		String sql_zqfy="select * from zl_contract_zqfy where nvl(dr,0)=0 and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		List<ContractZqfyVO> fylist=(List<ContractZqfyVO>)dao.executeQuery(sql_zqfy, new BeanListProcessor(ContractZqfyVO.class));
		if(fylist.size()!=0&&fylist!=null){
			for(int i=0;i<fylist.size();i++){
				ContractZqfyVO fyvo=fylist.get(i);
				fyvo.setRowno((i+1)*10+"");
			}
			dao.updateVOArray(fylist.toArray(new ContractZqfyVO[fylist.size()]));
		}
		String sql_zqcf="select * from zl_contract_zqfycf where nvl(dr,0)=0 and pk_contract='"+bills[0].getParentVO().getVsrcid()+"'";
		List<ContractZqfycfVO> cflist=(List<ContractZqfycfVO>)dao.executeQuery(sql_zqcf, new BeanListProcessor(ContractZqfycfVO.class));
		if(cflist.size()!=0&&cflist!=null){
			for(int i=0;i<cflist.size();i++){
				ContractZqfycfVO cfvo=cflist.get(i);
				cfvo.setRowno((i+1)*10+"");
			}
			dao.updateVOArray(cflist.toArray(new ContractZqfycfVO[cflist.size()]));
		}
		
		return bills;
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
