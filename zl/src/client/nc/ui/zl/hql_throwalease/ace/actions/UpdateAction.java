package nc.ui.zl.hql_throwalease.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.hql_throwalease.ThrowaleaseVO;
import nc.vo.zl.hql_throwalease.Throwalease_fyqsVO;
import nc.vo.zl.hql_throwalease.Throwalease_zqfyqsVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

/**
 * 更新数据
 * 
 * @author Liu
 * 
 */
public class UpdateAction extends NCAction {

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

	public UpdateAction() {
		super();
		this.setCode("update");
		this.setBtnName("更新数据");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent e) throws Exception {
		List<AggConfirmationVO> aggclist=new ArrayList<AggConfirmationVO>();
		ILyw_confirmationMaintain lyw = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql0 = "select pk_billtypeid from bd_billtype where pk_billtypecode='H640'";
		Object pk_billtype1 = iQ.executeQuery(sql0, new ColumnProcessor());
		//费用清算
		String sql1="select * from zl_throwalease_fyqs f where f.njsmny=0 and f.recqr!=0 and nvl(f.dr,0)=0 and f.pk_throwalease in " +
				"(select t.pk_throwalease from zl_throwalease t where nvl(t.dr,0)=0 and t.vbillstatus=1)";
		List<Throwalease_fyqsVO> fyqss=(List<Throwalease_fyqsVO>) iQ.executeQuery(sql1, new BeanListProcessor(Throwalease_fyqsVO.class));
		for (Throwalease_fyqsVO fyqs : fyqss) {
			String sql2="select count(*) from zl_confirmation where vdef1='"+fyqs.getPk_throwaleasefyqs()+"' and nvl(dr,0)=0";
			Integer n=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
			if(n>0){
				continue;
			}
			String sql3="select * from zl_throwalease where nvl(dr,0)=0 and pk_throwalease='"+fyqs.getPk_throwalease()+"'";
			ThrowaleaseVO hvo=(ThrowaleaseVO) iQ.executeQuery(sql3, new BeanProcessor(ThrowaleaseVO.class));
			ConfirmationVO vo=new ConfirmationVO();
			vo.setPk_customer(fyqs.getPk_customer());
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
			vo.setPk_billtype(getStgObj(pk_billtype1));
			vo.setVsrcid(hvo.getPk_throwalease());
			vo.setVsrctype(hvo.getPk_billtype());
			String sql_tz = "select pk_accperiodmonth from bd_accperiodmonth where " +
				     "nvl(dr,0)=0 and (begindate <= '"+hvo.getDtzdate()+"' and enddate >= '"+hvo.getDtzdate()+"')";
			String pk_tz = iQ.executeQuery(sql_tz, new ColumnProcessor()).toString();
			vo.setCaccountperiod(pk_tz);
			vo.setHouseproperty(fyqs.getPk_housesource());
			String sql_build="select pk_building from zl_throwalease_khfc where nvl(dr,0)=0 and pk_throwalease='"+hvo.getPk_throwalease()+"' and pk_housesource='"+fyqs.getPk_housesource()+"'";
			Object build=iQ.executeQuery(sql_build, new ColumnProcessor());
			vo.setBuildno(getStgObj(build));
			vo.setChargingproject(fyqs.getPk_costproject());
			vo.setDfeestartdate(hvo.getDtzdate());
			vo.setDcollectiondate(hvo.getDtzdate());
			vo.setDreccollectdate(hvo.getDtzdate());
			if(fyqs.getRecqr()==null){
				vo.setAmountreceivable(new UFDouble(0));
				vo.setNnotaxmny(new UFDouble(0));
			}else{
				vo.setAmountreceivable(new UFDouble(0).sub(fyqs.getRecqr()));
				vo.setNnotaxmny(new UFDouble(0).sub(fyqs.getRecqr().div(fyqs.getNtaxrate().add(100)).multiply(100)));
			}
			vo.setAmountconfirmed(new UFDouble(0));
			vo.setNtaxrate(fyqs.getNtaxrate());
			vo.setNtaxmny(vo.getAmountreceivable().sub(vo.getNnotaxmny()));
			vo.setVdef1(fyqs.getPk_throwaleasefyqs());
			vo.setVdef2("zl_throwalease_fyqs");
			AggConfirmationVO aggcvo=new AggConfirmationVO();
			aggcvo.setParentVO(vo);
			aggclist.add(aggcvo);
		}
		
		//周期费用清算
		String sql4="select * from zl_throwalease_zqfyqs f where f.nskmny=0 and f.recqr!=0 and nvl(f.dr,0)=0 and f.pk_throwalease in " +
				"(select t.pk_throwalease from zl_throwalease t where nvl(t.dr,0)=0 and t.vbillstatus=1)";
		List<Throwalease_zqfyqsVO> zqfyqs=(List<Throwalease_zqfyqsVO>) iQ.executeQuery(sql4, new BeanListProcessor(Throwalease_zqfyqsVO.class));
		for (Throwalease_zqfyqsVO zqfyvo : zqfyqs) {
			String sql2="select count(*) from zl_confirmation where vdef1='"+zqfyvo.getPk_throwalease_zqfyqs()+"' and nvl(dr,0)=0";
			Integer n=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
			if(n>0){
				continue;
			}
			String sql3="select * from zl_throwalease where nvl(dr,0)=0 and pk_throwalease='"+zqfyvo.getPk_throwalease()+"'";
			ThrowaleaseVO hvo=(ThrowaleaseVO) iQ.executeQuery(sql3, new BeanProcessor(ThrowaleaseVO.class));
			ConfirmationVO vo=new ConfirmationVO();
			vo.setPk_customer(zqfyvo.getPk_customer());
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
			vo.setPk_billtype(getStgObj(pk_billtype1));
			vo.setVsrcid(hvo.getPk_throwalease());
			vo.setVsrctype(hvo.getPk_billtype());
			String sql_tz = "select pk_accperiodmonth from bd_accperiodmonth where " +
				     "nvl(dr,0)=0 and (begindate <= '"+hvo.getDtzdate()+"' and enddate >= '"+hvo.getDtzdate()+"')";
			String pk_tz = iQ.executeQuery(sql_tz, new ColumnProcessor()).toString();
			vo.setCaccountperiod(pk_tz);
			vo.setHouseproperty(zqfyvo.getPk_housesource());
			String sql_build="select pk_building from zl_throwalease_khfc where nvl(dr,0)=0 and pk_throwalease='"+hvo.getPk_throwalease()+"' and pk_housesource='"+zqfyvo.getPk_housesource()+"'";
			Object build=iQ.executeQuery(sql_build, new ColumnProcessor());
			vo.setBuildno(getStgObj(build));
			vo.setChargingproject(zqfyvo.getPk_costproject());
			vo.setDfeestartdate(hvo.getDtzdate());
			vo.setDcollectiondate(hvo.getDtzdate());
			if(zqfyvo.getRecqr()==null){
				vo.setAmountreceivable(new UFDouble(0));
				vo.setNnotaxmny(new UFDouble(0));
			}else{
				vo.setAmountreceivable(new UFDouble(0).sub(zqfyvo.getRecqr()));
				vo.setNnotaxmny(new UFDouble(0).sub(zqfyvo.getRecqr().div(zqfyvo.getNtaxrate().add(100)).multiply(100)));
			}
			vo.setAmountconfirmed(new UFDouble(0));
			vo.setNtaxrate(zqfyvo.getNtaxrate());
			vo.setNtaxmny(vo.getAmountreceivable().sub(vo.getNnotaxmny()));
			vo.setVdef1(zqfyvo.getPk_throwalease_zqfyqs());
			vo.setVdef2("zl_throwalease_zqfyqs");
			vo.setDreccollectdate(hvo.getDtzdate());
			AggConfirmationVO aggcvo=new AggConfirmationVO();
			aggcvo.setParentVO(vo);
			aggclist.add(aggcvo);
		}
		
		if(aggclist.size()>0){
			lyw.insert(aggclist.toArray(new AggConfirmationVO[aggclist.size()]), null);
		}
		MessageDialog.showErrorDlg(billform, "提示", "数据更新完成！");
	}
	

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
