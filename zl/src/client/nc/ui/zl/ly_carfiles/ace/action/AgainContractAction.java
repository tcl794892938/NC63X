package nc.ui.zl.ly_carfiles.ace.action;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.linkoperate.ILinkAddData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractCVO;
import nc.vo.zl.ld_carcontract.CarcontractFVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;

public class AgainContractAction extends NCAction {
	private BillManageModel model;
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public AgainContractAction(){
		this.setCode("savecontract");
		this.setBtnName("合同续约");
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
		CarFilesVO vo=acvo.getParentVO();
		Object pk_carfiles=vo.getPk_carfiles();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select * from (select * from zl_carcontract where nvl(dr,0)=0 and vsrcbid='"+getStgObj(pk_carfiles)+"' order by enddate desc) where rownum=1";
		CarcontractVO cvo=(CarcontractVO) iQ.executeQuery(sql, new BeanProcessor(CarcontractVO.class));
		
		Object today=AppContext.getInstance().getBusiDate();
		Object enddate=cvo.getEnddate();
		if(dateToInteger(today)<=dateToInteger(enddate)){
			cvo.setStartdate(getAfterDay((UFDate) enddate));
		}else{
			cvo.setStartdate((UFDate) today);
		}
		cvo.setEnddate(null);
		
		Object pk_car=cvo.getPk_carcontract();
		
		String sqlb="select * from zl_carcontract_b where nvl(dr,0)=0 and pk_carcontract='"+getStgObj(pk_car)+"'";
		List<CarcontractBVO> cbvo=(List<CarcontractBVO>)iQ.executeQuery(sqlb, new BeanListProcessor(CarcontractBVO.class));
		
		String sqlc="select * from zl_carcontract_c where nvl(dr,0)=0 and pk_carcontract='"+getStgObj(pk_car)+"'";
		List<CarcontractCVO> ccvo=(List<CarcontractCVO>)iQ.executeQuery(sqlc, new BeanListProcessor(CarcontractCVO.class));
		
		String sqlf="select * from zl_carcontract_f where nvl(dr,0)=0 and pk_carcontract='"+getStgObj(pk_car)+"'";
		List<CarcontractFVO> cfvo=(List<CarcontractFVO>)iQ.executeQuery(sqlf, new BeanListProcessor(CarcontractFVO.class));
		
		List<AggCarcontractVO> aggvos=new ArrayList<AggCarcontractVO>();
		AggCarcontractVO aggvo=new AggCarcontractVO();
		aggvo.setParentVO(cvo);
		for(int i=0;i<cbvo.size();i++){
			aggvo.setTableVO("pk_carcontract_b",cbvo.toArray(new CarcontractBVO[i]));
		}
		for(int j=0;j<ccvo.size();j++){
			aggvo.setTableVO("pk_carcontract_c",ccvo.toArray(new CarcontractCVO[j]));
		}
		for(int k=0;k<cfvo.size();k++){
			aggvo.setTableVO("pk_carcontract_f",cfvo.toArray(new CarcontractFVO[k]));
		}
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
				return false;
			}else if(dateToInteger(vdef1)>=dateToInteger(today)){
				return true;
			}
		}
		
		return false;
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
	
	public UFDate getAfterDay(UFDate date){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		int day=cldr.get(Calendar.DATE);
		cldr.set(Calendar.DATE, day+1);
		
		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
