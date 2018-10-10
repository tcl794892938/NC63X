package nc.ui.zl.cwf_carconedit.ace.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.zl.cwf_carconedit.CarconeditBVO;
import nc.vo.zl.cwf_carconedit.CarconeditCVO;
import nc.vo.zl.cwf_carconedit.CarconeditFVO;
import nc.vo.zl.cwf_carconedit.CarconeditVO;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractCVO;
import nc.vo.zl.ld_carcontract.CarcontractFVO;


public class RefCarAction extends AbstractReferenceAction{

	public RefCarAction() {
		super();
		this.setCode("refaction");
		this.setBtnName("参照车辆合同");
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

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			
			AggCarcontractVO[] vos= (AggCarcontractVO[])PfUtilClient.getRetOldVos();
			 if(vos==null ||vos.length!=1 ){
				 MessageDialog.showErrorDlg(null, "错误", "请选择一条数据！");
				 return ;
			 }
			
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 AggCarconeditVO aggvo = null;
			 CarconeditVO vo = null;
			 
			 AggCarconeditVO[] aggvos = new AggCarconeditVO[vos.length];
			 for(int i=0;i<vos.length;i++){
				 aggvo = new AggCarconeditVO();
				 vo = new CarconeditVO();				 
				 vo.setCreator(AppContext.getInstance().getPkUser());
				 vo.setDbilldate(new UFDate());
				 vo.setPk_group(vos[i].getParentVO().getPk_group());
				 vo.setPk_org(vos[i].getParentVO().getPk_org());
				 vo.setPk_org_v(vos[i].getParentVO().getPk_org_v());
				// vo.setPk_poject(vos[i].getParentVO().getPk_project());
				 vo.setPk_poject(vos[i].getParentVO().getPk_project());
				 vo.setPk_customer(vos[i].getParentVO().getPk_customer());
				 vo.setPk_contracttype(vos[i].getParentVO().getPk_contracttype());
				 vo.setPk_costproject(vos[i].getParentVO().getPk_costproject());
				 vo.setPk_feescale(vos[i].getParentVO().getPk_feescale());
				 vo.setName(vos[i].getParentVO().getName());
				 vo.setCode(vos[i].getParentVO().getCode());
				 vo.setRentdate(vos[i].getParentVO().getRentdate());
				 vo.setStartdate(vos[i].getParentVO().getStartdate());
				 vo.setEnddate(vos[i].getParentVO().getEnddate());
				 vo.setCostcycle(vos[i].getParentVO().getCostcycle());
				 vo.setNtaxrate(vos[i].getParentVO().getNtaxrate());
				 vo.setNallrent(vos[i].getParentVO().getNallrent());
				 vo.setRemark(vos[i].getParentVO().getRemark());
				 String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H525'";
				 Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
				 vo.setPk_billtype(getStgObj(pk_billtype));
				 vo.setVbilltypecode("H525");
				 vo.setVbillstatus(-1);
				 vo.setVsrcbid(vos[i].getParentVO().getPk_carcontract());
				 vo.setVsrcbtype(vos[i].getParentVO().getPk_billtype());
				 vo.setVsrcbcode(vos[i].getParentVO().getVbilltypecode());		
				 vo.setVdef1(vos[i].getParentVO().getVdef1());
				 String maxversion="select max(version) from zl_carconedit where nvl(dr,0)=0 and vsrcbid='"+vos[i].getParentVO().getPk_carcontract()+"'";
				 Object version=iQ.executeQuery(maxversion, new ColumnProcessor());
				 Integer ver=(Integer) (version==null?0:version.toString());
				 if(ver.equals(0)){
					 vo.setVersion(1);
				 }else{
					 vo.setVersion(ver+1);
				 }
				 
				 aggvo.setParentVO(vo);
				 //基本信息
				 CarcontractBVO[] cbvo = (CarcontractBVO[]) vos[i].getTableVO("pk_carcontract_b");
				 CarconeditBVO[] bvo = new CarconeditBVO[cbvo.length];
				 for(int j=0;j<cbvo.length;j++){
					 //bvo = new CarconeditBVO();
					 bvo[j] = new CarconeditBVO();
					 bvo[j].setRowno(cbvo[j].getRowno());
					 bvo[j].setPk_plate(cbvo[j].getPlatenum());
					 bvo[j].setPk_customer(cbvo[j].getPk_customer());
					 bvo[j].setPhone(cbvo[j].getPhonenum());
					 bvo[j].setRemark(cbvo[j].getRemark());
				 }
				 aggvo.setTableVO("pk_carconedit_b", bvo);
				 
				 //费用信息
				 //查询费用信息
				 String sql1="select * from zl_carcontract_c where nvl(dr,0)=0 and pk_carcontract in(select pk_carcontract from zl_carcontract where vbillstatus=1 and version=-1 and nvl(dr,0)=0 ) order by to_number(rowno)";
				 List<CarcontractCVO> carCList = (List<CarcontractCVO>)iQ.executeQuery(sql1, new BeanListProcessor(CarcontractCVO.class));
				 List<CarcontractCVO> carCList1 = new ArrayList<CarcontractCVO>();
				 if(carCList.size()>0){
					CarcontractCVO cavo = null;
					for(int a=0;a<carCList.size();a++){
						if(vos[i].getParentVO().getPk_carcontract().equals(carCList.get(a).getPk_carcontract())){
							cavo = new CarcontractCVO();
							cavo = carCList.get(a);
							carCList1.add(cavo);
						}
					}
					 CarconeditCVO[] cvo = new CarconeditCVO[carCList1.size()];
					 for(int j=0;j<carCList1.size();j++){
						 //bvo = new CarconeditBVO();
						 cvo[j] = new  CarconeditCVO();
						 cvo[j].setRowno(carCList1.get(j).getRowno());
						 cvo[j].setPk_plate(carCList1.get(j).getPlatenum());
						 cvo[j].setPk_costproject(carCList1.get(j).getPk_costproject());
						 cvo[j].setMstartdate(carCList1.get(j).getMstartdate());
						 cvo[j].setMenddate(carCList1.get(j).getMenddate());
						 cvo[j].setPaydate(carCList1.get(j).getPaydate());
						 cvo[j].setNrentmoney(carCList1.get(j).getNrentmoney());
						 cvo[j].setNreceivemoney(carCList1.get(j).getNreceivemoney());
						 cvo[j].setNdiscountmoney(carCList1.get(j).getNdiscountmoney());
						 cvo[j].setNcollectemoney(carCList1.get(j).getNcollectemoney());
					 }
					 aggvo.setTableVO("pk_carconedit_c", cvo);
				}

				//财务分摊
				//查询财务分摊
				String sql2= "select * from zl_carcontract_f where nvl(dr,0)=0 and pk_carcontract in(select pk_carcontract from zl_carcontract where vbillstatus=1 and version=-1 and nvl(dr,0)=0 )  order by to_number(rowno)";
				List<CarcontractFVO> carFList = (List<CarcontractFVO>)iQ.executeQuery(sql2, new BeanListProcessor(CarcontractFVO.class));
				List<CarcontractFVO> carFList1 = new ArrayList<CarcontractFVO>();
				if(carFList.size()>0){
					CarcontractFVO cfvo = null;
					for(int a=0;a<carFList.size();a++){
						if(vos[i].getParentVO().getPk_carcontract().equals(carFList.get(a).getPk_carcontract())){
							cfvo = new CarcontractFVO();
							cfvo = carFList.get(a);
							carFList1.add(cfvo);
						}
					}
					CarconeditFVO[] fvo = new CarconeditFVO[carFList1.size()];
				    for(int j=0;j<carFList1.size();j++){
						 //bvo = new CarconeditBVO();
				    	 fvo[j] = new CarconeditFVO();
						 fvo[j].setRowno(carFList1.get(j).getRowno());
						 fvo[j].setPk_plate(carFList1.get(j).getPlatenum());
						 fvo[j].setPk_costproject(carFList1.get(j).getPk_costproject());
						 fvo[j].setMstartdate(carFList1.get(j).getMstartdate());
						 fvo[j].setMenddate(carFList1.get(j).getMenddate());
						 fvo[j].setPaydate(carFList1.get(j).getPaydate());
						 fvo[j].setNreceivemoney(carFList1.get(j).getNreceivemoney());
						 fvo[j].setNfreetaxmoney(carFList1.get(j).getNfreetaxmoney());
						 fvo[j].setNcollectemoney(carFList1.get(j).getNcollectemoney());
						 fvo[j].setNtaxmoney(carFList1.get(j).getNtaxmoney());
				    }
				    aggvo.setTableVO("pk_carconedit_f", fvo);
				}	
				
			 aggvos[i]=aggvo;
			 }
			this.getTransferViewProcessor().processBillTransfer(aggvos);
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
			context.setCurrBilltype("H525");
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
