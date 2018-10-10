package nc.ui.zl.ld_carcontract.ace.action;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;

public class SaveAction extends SaveScriptAction {

	private ShowUpableBillForm billform;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		AggCarcontractVO aggvo = (AggCarcontractVO) billform.getValue();
		if(aggvo==null){
			return;
		}
		CarcontractVO vo = aggvo.getParentVO();
		BillModel model = billform.getBillCardPanel().getBillModel("pk_carcontract_b");
		billform.getBillCardPanel().setHeadItem("version", -1);
		int row = model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billform, "提示", "无车牌数据，不能保存！");
			return ;
		}
		
		//判断该车牌是否有合同
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for(int i=0;i<row;i++){
			Object a = model.getValueObjectAt(i, "platenum");
			Object platenum = ((DefaultConstEnum)a).getValue();
			String sql1 = "select * from zl_carcontract where nvl(dr,0)=0 and pk_project='"+vo.getPk_project()+"' and pk_carcontract in(select b.pk_carcontract from zl_carcontract_b b where nvl(dr,0)=0 and b.platenum='"+platenum+"')";
			List<CarcontractVO> listvo=(List<CarcontractVO>)iQ.executeQuery(sql1, new BeanListProcessor(CarcontractVO.class));
			
			Object obj1 = billform.getBillCardPanel().getHeadItem("startdate").getValueObject();
			Object obj2 = billform.getBillCardPanel().getHeadItem("enddate").getValueObject();
			Object obj3 = billform.getBillCardPanel().getHeadItem("vbillcode").getValueObject();
			
			
			UFDate date1 = new UFDate(obj1.toString());
			UFDate date2 = new UFDate(obj2.toString());
			if(listvo.size()>0){
				for(int j=0;j<listvo.size();j++){
					if((obj3!=null)&&(!obj3.equals(listvo.get(j).getVbillcode()))||obj3==null){
						UFDate date1_1 = listvo.get(j).getStartdate();
						UFDate date1_2 = listvo.get(j).getEnddate();
						if(!(date1_2.before(date1)||date1_1.after(date2))){
							MessageDialog.showHintDlg(billform, "提示", "当前车牌合同有效时间范围和单据号为："+listvo.get(j).getVbillcode()+ "冲突，不能保存！");
							return ;
						}
					}
				}
			}
		}
		
		//校验编码重复
		Object pkobj=billform.getBillCardPanel().getHeadItem("pk_carcontract").getValueObject();
		Object code=billform.getBillCardPanel().getHeadItem("code").getValueObject();
		String sql1="select count(1) from zl_carcontract where nvl(dr,0)=0 and vbilltypecode='ZLH52040' and " +
				" code='"+code+"' and pk_carcontract<>'"+pkobj+"'";
		
		Integer it=(Integer)iQ.executeQuery(sql1, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(billform, "提示", "合同号重复！");
			return ;
		}
		
		super.doAction(e);
		
		Object vsrcbid=billform.getBillCardPanel().getHeadItem("vsrcbid").getValueObject();
		Object enddate=billform.getBillCardPanel().getHeadItem("enddate").getValueObject();
		if(!getStgObj(vsrcbid).equals("")){
			
			String sql="select * from zl_carfiles where nvl(dr,0)=0 and pk_carfiles='"+getStgObj(vsrcbid)+"'";
			CarFilesVO cvo=(CarFilesVO) iQ.executeQuery(sql, new BeanProcessor(CarFilesVO.class));
			cvo.setVdef1(getStgObj(enddate));
				IVOPersistence ivp = NCLocator.getInstance().lookup(
						IVOPersistence.class);
				ivp.updateVO(cvo);
			
		}
	}
	
	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
