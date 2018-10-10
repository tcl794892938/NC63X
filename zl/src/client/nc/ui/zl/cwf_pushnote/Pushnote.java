package nc.ui.zl.cwf_pushnote;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class Pushnote implements IBackgroundWorkPlugin{

	public String org;
	public Integer day;
	
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public PreAlertObject executeTask(BgWorkingContext e)
			throws BusinessException {
		this.org=e.getKeyMap().get("组织").toString();
		this.day=Integer.parseInt(e.getKeyMap().get("天数").toString());
		String text="";
		text+=Qryhtdz()+Qrymxqd();
		
		if(text.equals("")){
			return null;
		}
		PreAlertObject a=new PreAlertObject();
		a.setReturnObj(text);
		a.setMsgTitle("催缴记录提醒");
		a.setReturnType(PreAlertReturnType.RETURNOBJECT);
		return a;
	}

	public String  Qryhtdz() {
		String s="";
		try {
		UFDateTime time=AppContext.getInstance().getServerTime();
		UFDateTime time2=time.getDateTimeAfter(day);
		String newwhere1 =" pk_org='"+this.org+"' and endtime>='"+time+"' and endtime<'"+time2+"'";
		String sql = "select count(*) from (select * from (select t.pk_org,t.pk_project,t.pk_customer,t.pk_contracttype,th.pk_house,t.htcode pk_code,t.denddate endtime from zl_contract t "
				+"left join zl_contract_house th on (th.pk_contract=t.pk_contract and nvl(th.dr,0)=0) "
				+"where nvl(t.dr,0)=0 and t.version=-1 and t.vbillstatus=1 and htcode is not null) a where "+newwhere1
				+" union "
				+"select * from (select md.pk_org,md.pk_project,md.pk_customer,md.pk_contracttype,md.vsrcid pk_house,md.code pk_code,md.enddate endtime from zl_mdcontract md "
				+"where nvl(md.dr,0)=0 and md.version=-1 and md.state=1) b where "+newwhere1
				+" union "
				+"select * from (select p.pk_org,p.pk_project,p.pk_customer,p.pk_contracttype,pb.parknum pk_house,p.code pk_code,p.enddate endtime from zl_parkcontract p "
				+"join zl_parkcontract_b pb on(p.pk_parkcontract=pb.pk_parkcontract and nvl(p.dr,0)=0 and nvl(pb.dr,0)=0 and p.version=-1 and p.vbillstatus=1)) c where "+newwhere1
				+" union "
				+"select * from (select c.pk_org,c.pk_project,c.pk_customer,c.pk_contracttype,c.vsrcbid pk_house,c.code pk_code,c.enddate endtime "
				+"from zl_carcontract c where nvl(c.dr,0)=0 and c.version=-1 and c.vbillstatus=1) d where "+newwhere1+")";

		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count= (Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count>0){
			s= "未来"+day+"天内有合同将要到期,请检查！";
		}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return s;
	}
	
	public String Qrymxqd(){
		String s="";
		try {
		UFDateTime time=AppContext.getInstance().getServerTime();
		UFDateTime time2=time.getDateTimeAfter(day);
		String where =" pk_org='"+this.org+"' and receivedate>='"+time+"' and receivedate<'"+time2+"'";
		String sql = "select count(*) from (select m.pk_org,m.pk_project,  m.pk_customer,m.buildname,m.floorn,m.unit,m.roomnumber,m.pk_house,m.buildarea,m.innerarea,m.pk_costproject, m.receivedate,m.nreceivemoney, "
				+"(case when m.nreceivemoney < 0 then -max(abs(m.ngetmoney1)) when m.nreceivemoney > 0 then  max(m.ngetmoney1) else 0 end) ngetmoney, "
				+"max(m.getdate1) getdate, m.arrearage, m.getman, m.paytype,m.pk_report_mxqd from (select r.pk_org,r.pk_project,r.pk_house,h.floorn,h.roomnumber,h.unit, "
				+"h.buildarea, h.innerarea,h.buildname,r.pk_customer, r.pk_costproject, r.nrecmoney nreceivemoney, "
				+"(case when r.gatherdate is null then r.begindate else r.gatherdate end) receivedate, "
				+"(case when gb.nysmny is not null then gb.nysmny + gb.nskmny else 0 end) ngetmoney1, "
				+"g.creationtime getdate1,(case when r.nrealmoney is not null then (r.nrecmoney - r.nrealmoney) else r.nrecmoney end) arrearage, "
				+"g.creator getman,g.pk_paytype paytype ,r.vbillcode pk_report_mxqd "
				+"from zl_recbill r left join zl_housesource h on  (r.pk_house = h.pk_housesource and nvl(h.dr, 0) = 0) "
				+"left join zl_gather_b gb on (r.pk_recbill = gb.vsrcid  and r.pk_house is not null and nvl(gb.dr, 0) = 0) "
				+"left join zl_gather g on (gb.pk_gather = g.pk_gather and  g.vbillstatus = 1 and nvl(g.dr, 0) = 0) where nvl(r.dr, 0) = 0 "
				+"order by r.pk_org, r.pk_project, to_number(h.floorn) desc, to_number(h.unit) asc, to_number(h.roomnumber) asc, r.pk_customer) m  where  "+where
				+" group by m.pk_org,m.pk_project,m.floorn,m.unit,m.roomnumber,  m.pk_customer,m.buildname,m.pk_house,m.buildarea,m.innerarea,m.pk_costproject,m.nreceivemoney, m.receivedate, "
				+" m.arrearage, m.getman, m.paytype,pk_report_mxqd) ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count= (Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count>0){
			s= "未来"+day+"天内存在催缴记录，请检查！";
		}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
