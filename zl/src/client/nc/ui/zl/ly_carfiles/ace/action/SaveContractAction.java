package nc.ui.zl.ly_carfiles.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.linkoperate.ILinkAddData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;

public class SaveContractAction extends NCAction {
	private BillManageModel model;
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public SaveContractAction(){
		this.setCode("savecontract");
		this.setBtnName("签订合同");
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		Integer[] rows=model.getSelectedOperaRows();
		if(rows==null||rows.length!=1){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		Object obj=model.getSelectedData();
		AggCarFilesVO acvo=(AggCarFilesVO)obj;
		CarFilesVO cvo=acvo.getParentVO();
		Object pk_carfiles=cvo.getPk_carfiles();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String getvdef="select vdef1 from zl_carfiles where nvl(dr,0)=0 and pk_carfiles='"+getStgObj(pk_carfiles)+"'";
		Object vdef1=iQ.executeQuery(getvdef, new ColumnProcessor());
		Object today=AppContext.getInstance().getBusiDate();
		if(!getStgObj(vdef1).equals("")&&(dateToInteger(vdef1)>=dateToInteger(today))){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "该条车辆档案已经签订合同无需再次签订,请刷新页面再操作！");
			return ;
		}
		String getpk="select pk_carcontract_b from zl_carcontract_b where nvl(dr,0)=0 and platenum='"+getStgObj(pk_carfiles)+"'";
		Object pk=iQ.executeQuery(getpk, new ColumnProcessor());
		if(!getStgObj(pk).equals("")){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "该车牌号已有合同，请至车辆合同查询操作！");
			return ;
		}
		
		CarcontractVO cvo1=new CarcontractVO();
		cvo1.setPk_org(cvo.getPk_org());
		cvo1.setPk_org_v(cvo.getPk_org_v());
		cvo1.setPk_group(cvo.getPk_group());
		cvo1.setPk_project(cvo.getPk_project());
		cvo1.setPk_customer(cvo.getKhname());
		cvo1.setRentdate(AppContext.getInstance().getBusiDate());
		cvo1.setVsrcbtype(cvo.getPk_billtype());
		cvo1.setVsrcbid(cvo.getPk_carfiles());
		cvo1.setCostcycle(0);
		cvo1.setVbillstatus(-1);
		
		String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H524'";
		Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
		cvo1.setPk_billtype(getStgObj(pk_billtype));
		cvo1.setVbilltypecode("H524");
		cvo1.setDbilldate(AppContext.getInstance().getBusiDate());
		cvo1.setCreator(AppContext.getInstance().getPkUser());
		cvo1.setCreationtime(AppContext.getInstance().getServerTime());
		cvo1.setVersion(-1);
		
		CarcontractBVO[] cbvos=new CarcontractBVO[1];
		CarcontractBVO cbvo=new CarcontractBVO();
		cbvo.setPk_customer(cvo.getKhname());
		cbvo.setRowno("10");
		cbvo.setPlatenum(cvo.getPk_carfiles());
		cbvos[0]=cbvo;
		
		List<AggCarcontractVO> aggvos=new ArrayList<AggCarcontractVO>();
		AggCarcontractVO aggvo=new AggCarcontractVO();
		aggvo.setParentVO(cvo1);
		aggvo.setTableVO("pk_carcontract_b", cbvos);
		aggvos.add(aggvo);
		
		SFClientUtil.openLinkedADDDialog("ZLH52040", model.getContext().getEntranceUI(), new LinkAddData(aggvo));

	}
	
	class LinkAddData implements ILinkAddData{
    	
		private String id;
		private Object obj;
		
		public LinkAddData(String id){
			this.id=id;
		}	
		public LinkAddData(Object obj){
			this.obj=obj;
		}
		public Object getUserObject() {
			return obj;
		}
		@Override
		public String getSourceBillID() {
			return id;
		}
		@Override
		public String getSourceBillType() {
			return null;
		}
		@Override
		public String getSourcePkOrg() {
			return null;
		}
	}
	
	public Integer dateToInteger(Object date){
		String date1=getStgObj(date).substring(0, 10);
		String[] dates=date1.split("-");
		String date2="";
		for(int i=0;i<dates.length;i++){
			date2+=dates[i];
		}
		return Integer.parseInt(date2);
	}

	@Override
	protected boolean isActionEnable() {
		Object obj=model.getSelectedData();
		if(obj==null){
			return false;
		}
		AggCarFilesVO aggvo=(AggCarFilesVO)obj;
		CarFilesVO cvo=aggvo.getParentVO();
		Object liststate=cvo.getListstate();
		Object vdef1=cvo.getVdef1();
		Object today=AppContext.getInstance().getBusiDate();
		
		if(getStgObj(liststate).equals("1")){
			if(getStgObj(vdef1).equals("")){
				return true;
			}else if(dateToInteger(vdef1)<dateToInteger(today)){
				return true;
			}
		}
		
		return false;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
