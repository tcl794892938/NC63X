package nc.ui.zl.hql_throwalease.ace.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.ui.zl.hql_throwalease.ace.config.CalculateRentUtils;
import nc.ui.zl.hql_throwalease.ace.config.CalendarUtls;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.zl.hql_throwalease.ThrowaleaseVO;
import nc.vo.zl.hql_throwalease.Throwalease_bzjthVO;
import nc.vo.zl.hql_throwalease.Throwalease_fyqsVO;
import nc.vo.zl.hql_throwalease.Throwalease_khfcVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;

public class RefAction extends AbstractReferenceAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7176673935883282272L;


	public RefAction() {
		super();
		this.setCode("refaction");
		this.setBtnName("参照合同管理");
	}
	
	private BillForm editor;

	private AbstractAppModel model;
	

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			
			AggContractVO[] vos= (AggContractVO[])PfUtilClient.getRetOldVos();
			 if(vos==null ||vos.length<=0 ){
				 MessageDialog.showErrorDlg(null, "错误", "请至少选择一条表体数据！");
				 return ;
			 }
			 if(vos.length > 1){
				 MessageDialog.showErrorDlg(null, "错误", "只可选择一条表头数据！");
				 return ;
			 }
			 ContractHouseVO[] cbvos = vos[0].getChildHouseVO();
			 for(int i=0;i<cbvos.length-1;i++){
				 if(!cbvos[i].getPk_customer().equals(cbvos[i+1].getPk_customer())){
					 MessageDialog.showErrorDlg(null, "错误", "只能选择同一个客户下的房产！");
					 return ;
				 }
			 }
			 
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 /*String sql_cust = "select pk_customer from zl_contract_house where nvl(dr,0)=0 and pk_contract = '" +
			                   vos[0].getParentVO().getPk_contract() + "'";*/
			 Object pk_customer = cbvos[0].getPk_customer();
			 String sql_fcxx = "select pk_housesource,unit,roomnumber from zl_housesource where nvl(dr,0)=0 and " +
			 		           "pk_housesource in (";
			 for(int i = 0;i < vos[0].getChildHouseVO().length;i++){
				 sql_fcxx += "'"+vos[0].getChildHouseVO()[i].getPk_house()+"'";
				 if(i+1 != vos[0].getChildHouseVO().length){
					 sql_fcxx += ",";
				 }
			 }
			 sql_fcxx += ")";
			 List<Map<String, Object>> listmap = (List<Map<String, Object>>) iQ.executeQuery(sql_fcxx, new MapListProcessor());
			 AggThrowaleaseVO aggvo=new AggThrowaleaseVO();
			 ThrowaleaseVO hvo=new ThrowaleaseVO();
			 
			 hvo.setContractid(vos[0].getParentVO().getHtcode());
			 hvo.setContractname(vos[0].getParentVO().getHtname());
			 hvo.setPk_org(vos[0].getParentVO().getPk_org());
			 hvo.setPk_org_v(vos[0].getParentVO().getPk_org_v());
			 hvo.setPk_group(vos[0].getParentVO().getPk_group());
			 hvo.setPk_project(vos[0].getParentVO().getPk_project());
			 //System.out.println(hvo.getPk_project());
			 hvo.setDstartdate(vos[0].getParentVO().getDstartdate());
			 hvo.setDenddate(vos[0].getParentVO().getDenddate());
			 hvo.setDentrydate(vos[0].getParentVO().getDindate());
			 hvo.setBilldate(vos[0].getParentVO().getDbilldate());
			 hvo.setDtzdate(AppContext.getInstance().getBusiDate());
			 
			 if(AppContext.getInstance().getBusiDate().after(vos[0].getParentVO().getDenddate())){
				 hvo.setTztype(2);
			 }else{
				hvo.setTztype(1);
			 }
			 
			 hvo.setVbillstatus(-1);
			 hvo.setDbilldate(new UFDate());
			 
			 hvo.setPk_billtype("0001ZZ1000000001UKA6");
			 hvo.setVbilltypecode("H463");
			 hvo.setVsrcid(vos[0].getParentVO().getPk_contract());
			 hvo.setVsrctype(vos[0].getParentVO().getVbilltypecode());
			 
			 hvo.setCreator(AppContext.getInstance().getPkUser());
			 hvo.setCreationtime(new UFDateTime());
			 aggvo.setParentVO(hvo);
			 
			 //客户房产信息页签塞值
			 List<Throwalease_khfcVO> list=new ArrayList<Throwalease_khfcVO>();
			 for(int j = 0;j < cbvos.length;j++){
				 ContractHouseVO cbvo = cbvos[j];
				 Throwalease_khfcVO bvo = new Throwalease_khfcVO();
				 bvo.setPk_customer(pk_customer == null ?"":pk_customer.toString());
				 bvo.setPk_building(cbvo.getPk_building());
				 bvo.setPk_housesource(cbvo.getPk_house());
				 bvo.setTzdate(AppContext.getInstance().getBusiDate());
				 bvo.setPk_fc(cbvo.getPk_contract_house());
				 for(int i = 0;i < listmap.size();i++){
					 if(cbvo.getPk_house().equals(listmap.get(i).get("pk_housesource"))){
						 bvo.setUnit(listmap.get(i).get("unit").toString());
						 bvo.setRoomnumber(listmap.get(i).get("roomnumber").toString());
					 }
				 }
				 
				 list.add(bvo);
			 }
			 Throwalease_khfcVO [] bvos=new Throwalease_khfcVO[list.size()];
			 aggvo.setChildrenVO(list.toArray(bvos));
			 
			 //费用清算页签塞值
			List<Throwalease_fyqsVO> list2=new ArrayList<Throwalease_fyqsVO>();
			for(int j = 0;j < cbvos.length;j++){
				 ContractHouseVO cbvo = cbvos[j];
				 Throwalease_fyqsVO fvo = new Throwalease_fyqsVO();
				 fvo.setPk_customer(pk_customer == null ?"":pk_customer.toString());
				 fvo.setPk_housesource(cbvo.getPk_house());
				 fvo.setPk_costproject(vos[0].getParentVO().getPk_costproject());
				 fvo.setNkkmny(new UFDouble(0));
				 fvo.setNtaxrate(new UFDouble(vos[0].getParentVO().getTaxstyle().toString()));
				 Calendar c=Calendar.getInstance();
				 c.set(c.HOUR_OF_DAY,0);
				 c.set(c.MINUTE, 0);
				 c.set(c.SECOND,0);
				 UFDate today=new UFDate(c.getTime());
				 
				 String sql="select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_customer='"+pk_customer+"' " +
							"and pk_house='"+cbvo.getPk_house()+"' and pk_contract='"+vos[0].getParentVO().getPk_contract()+"' and dstartdate<='"+today+"' " +
									"and denddate>='"+today+"' order by dstartdate";
				ContractYwcfVO ywvo=(ContractYwcfVO) iQ.executeQuery(sql, new BeanProcessor(ContractYwcfVO.class));
				if(ywvo==null||ywvo.getNskmny()==null){
					fvo.setNskmny(new UFDouble(0));
					fvo.setNjsmny(new UFDouble(0));
					fvo.setNnotaxmoney(new UFDouble(0));
					fvo.setNtaxmny(new UFDouble(0));
					fvo.setRecqr(new UFDouble(0));
				}else{
					CalculateRentUtils cal=new CalculateRentUtils();
					Double nsk=Double.parseDouble(ywvo.getNskmny().toString());
					Double nys=Double.parseDouble(ywvo.getNysmny().toString());
					if(nsk==0){
						fvo.setNskmny(new UFDouble(0));
						fvo.setNjsmny(new UFDouble(0));
						fvo.setNnotaxmoney(new UFDouble(0));
						fvo.setNtaxmny(new UFDouble(0));
					}else if(nys==nsk){
						UFDate startdate=today;
						UFDate enddate=ywvo.getDenddate();
						UFDouble sk=cal.calculateMnyBetweenDate(vos[0].getParentVO().getPk_contract(), startdate, enddate,cbvo.getPk_house());
						fvo.setNskmny(sk);
						fvo.setNjsmny(sk);
						fvo.setNnotaxmoney(sk.div(fvo.getNtaxrate().add(100)).multiply(100));
						fvo.setNtaxmny(sk.sub(fvo.getNnotaxmoney()));
					}else{
						UFDate startdate=today;
						UFDate enddate=ywvo.getDenddate();
						UFDouble sk=cal.calculateMnyBetweenDate(vos[0].getParentVO().getPk_contract(), startdate, enddate,cbvo.getPk_house());
						Double sy=nys-nsk;
						UFDouble yt=sk.sub(sy);
						fvo.setNskmny(yt);
						fvo.setNjsmny(yt);
						fvo.setNnotaxmoney(yt.div(fvo.getNtaxrate().add(100)).multiply(100));
						fvo.setNtaxmny(yt.sub(fvo.getNnotaxmoney()));
					}
					CalendarUtls cul=new CalendarUtls();
					UFDate ymdate=cul.getMaxMonthDay(today);
					UFDouble rec=new UFDouble(0);
					if(ymdate.afterDate(ywvo.getDenddate())){
						rec=cal.calculateMnyBetweenDate(vos[0].getParentVO().getPk_contract(), today, ywvo.getDenddate(),cbvo.getPk_house());
					}else{
						rec=cal.calculateMnyBetweenDate(vos[0].getParentVO().getPk_contract(), today, ymdate,cbvo.getPk_house());
					}
					fvo.setRecqr(rec);
					
				}
				 
				list2.add(fvo);
			 }
			aggvo.setChildrenVO(list2.toArray(new Throwalease_fyqsVO[list2.size()]));
			
			/*String countsql="select count(*) from zl_contract_house where nvl(dr,0)=0 and " +
					"pk_contract='"+vos[0].getParentVO().getPk_contract()+"' " +
							"and pk_contract_house not in (select pk_fc from zl_throwalease_khfc where nvl(dr,0)=0)";
			Integer count=(Integer) iQ.executeQuery(countsql, new ColumnProcessor());
			if(count==list.size()){*/
				 //保证金退还页签塞值
				List<Throwalease_bzjthVO> bzlist=new ArrayList<Throwalease_bzjthVO>();
				String sql_bzj = "select pk_customer,pk_costproject,nskmny from zl_contract_bzj where nvl(dr,0)=0 and pk_contract ='" + 
				                  vos[0].getParentVO().getPk_contract()+"'";
				Map<String, Object> bzjlistmap = (Map<String, Object>) iQ.executeQuery(sql_bzj, new MapProcessor());
				
				if(bzjlistmap!=null&&bzjlistmap.size()>0){
					Throwalease_bzjthVO bzjvo = new Throwalease_bzjthVO();
					bzjvo.setPk_customer(bzjlistmap.get("pk_customer").toString());
					bzjvo.setPk_costproject(bzjlistmap.get("pk_costproject").toString());
					
					String sql="select sum(b.ytdeposit) from zl_throwalease_bzjth b where nvl(b.dr,0)=0 and b.pk_throwalease in " +
							"(select t.pk_throwalease from zl_throwalease t where nvl(t.dr,0)=0 and vbillstatus=1 and t.vsrcid='"+vos[0].getParentVO().getPk_contract()+"')";
					Object yit=iQ.executeQuery(sql, new ColumnProcessor());
					UFDouble yit1=yit==null?new UFDouble(0):new UFDouble(yit.toString());
					UFDouble ytbzj=new UFDouble(bzjlistmap.get("nskmny").toString()).sub(yit1);
					bzjvo.setYtdeposit(ytbzj);
					bzjvo.setNkkmny(new UFDouble(0));
					bzjvo.setNjsmny(ytbzj);
					bzjvo.setDytkdate(AppContext.getInstance().getBusiDate());
					bzlist.add(bzjvo);
					aggvo.setChildrenVO(bzlist.toArray(new Throwalease_bzjthVO[bzlist.size()]));
					
				}
				
			 //}
			
			AggThrowaleaseVO[] aggvos = new AggThrowaleaseVO[1];
			aggvos[0] = aggvo;
			this.getTransferViewProcessor().processBillTransfer(aggvos);
			//this.editor.getBillCardPanel().getBillModel().setValueAt("20171128", 0, "vbatchcode");
			 
		}
	}
	
	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			context.setCurrBilltype("H463");
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
//		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
//		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
//		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
		return context;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
