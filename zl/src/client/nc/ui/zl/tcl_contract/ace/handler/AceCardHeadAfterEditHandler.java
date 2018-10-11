package nc.ui.zl.tcl_contract.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.tcl_contract.ace.config.CalculateRentUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		if(e.getKey().equals("dstartdate")||e.getKey().equals("denddate")){//起始终止日期
			
			BillCardPanel panel=billForm.getBillCardPanel();
			int rowcount=panel.getBillModel("pk_contract_bzj").getRowCount();
			int rowcount2=panel.getBillModel("pk_contract_mzq").getRowCount();
			int rowcount3=panel.getBillModel("pk_contract_zzq").getRowCount();
			int rowcount4=panel.getBillModel("pk_contract_ywcf").getRowCount();
			int rowcount5=panel.getBillModel("pk_contract_cwcf").getRowCount();
			int rowcount6=panel.getBillModel("pk_contract_zqfy").getRowCount();
			int rowcount7=panel.getBillModel("pk_contract_zqfycf").getRowCount();
			if(rowcount>0||rowcount2>0||rowcount3>0||rowcount4>0||rowcount5>0||rowcount6>0||rowcount7>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改合同起始终止日期将清空[保证金][免租期][增长期][业务拆分][财务拆分][周期费用拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_contract_bzj").clearBodyData();
					panel.getBillModel("pk_contract_mzq").clearBodyData();
					panel.getBillModel("pk_contract_zzq").clearBodyData();
					panel.getBillModel("pk_contract_ywcf").clearBodyData();
					panel.getBillModel("pk_contract_cwcf").clearBodyData();
					//panel.getBillModel("pk_contract_zqfy").clearBodyData();
					panel.getBillModel("pk_contract_zqfycf").clearBodyData();
					if(e.getKey().equals("dstartdate")){
						int row=panel.getBillModel("pk_contract_zqfy").getRowCount();
						for(int i=0;i<row;i++){
							panel.getBillModel("pk_contract_zqfy").setValueAt(panel.getHeadItem("dstartdate").getValueObject(), i, "dstartdate");
						}
					}
				}
			}
			
			Object obj1=panel.getHeadItem("dstartdate").getValueObject();
			Object obj2=panel.getHeadItem("denddate").getValueObject();
			
			if(obj1==null||obj2==null){
				return ;
			}
			
			UFDate ud1=new UFDate(obj1.toString());
			UFDate ud2=new UFDate(obj2.toString());
			if(!ud1.beforeDate(ud2)){
				MessageDialog.showHintDlg(billForm, "提示", "合同终止日不能早于等于合同起始日！");
				panel.setHeadItem(e.getKey(), null);
				return ;
			}
		}
		
		if(e.getKey().equals("rentstyle")){//租赁方式
			
			BillCardPanel panel=billForm.getBillCardPanel();
			int rowcount=panel.getBillModel("pk_contract_house").getRowCount();
			int rowcount2=panel.getBillModel("pk_contract_zzq").getRowCount();
			if(rowcount<=0&&rowcount2<=0){
				return ;
			}
			
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改租赁方式将影响[房产页签][增长期]信息，是否确认更改！");
			if(it!=UIDialog.ID_OK){
				panel.setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			
			//重算房产租金
			int[] rows=new int[rowcount];
			for(int i=0;i<rowcount;i++){
				rows[i]=i;
			}
			try {
				CalculateRentUtils.recalHouse(billForm.getBillCardPanel(), rows);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}
			
			//重算增长期
			int[] rows2=new int[rowcount2];
			for(int i=0;i<rowcount2;i++){
				rows2[i]=i;
			}
			try {
				CalculateRentUtils.recalZzqData(billForm.getBillCardPanel(), rows2);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}
			
		}
		
		if(e.getKey().equals("nrentprice")){//租金单价
			
			BillCardPanel panel=billForm.getBillCardPanel();
			Object obj=e.getValue();
			UFDouble npce=obj==null?new UFDouble(0):new UFDouble(obj.toString());
			if(npce.compareTo(new UFDouble(0))<0){
				MessageDialog.showHintDlg(billForm, "提示", "租金单价不可为空，或小于零！");
				panel.setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			
			
			int rowcount=panel.getBillModel("pk_contract_house").getRowCount();
			int rowcount2=panel.getBillModel("pk_contract_zzq").getRowCount();
			int rowcount3=panel.getBillModel("pk_contract_bzj").getRowCount();
			int rowcount4=panel.getBillModel("pk_contract_ywcf").getRowCount();
			int rowcount5=panel.getBillModel("pk_contract_cwcf").getRowCount();
			if(rowcount<=0&&rowcount2<=0&&rowcount3<=0&&rowcount4<=0&&rowcount5<=0){
				return ;
			}
			
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改租金单价将影响[房产页签],清除[增长期][保证金][业务拆分][财务拆分]信息，是否确认更改！");
			if(it!=UIDialog.ID_OK){
				panel.setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			
			panel.getBillModel("pk_contract_zzq").clearBodyData();
			panel.getBillModel("pk_contract_bzj").clearBodyData();
			panel.getBillModel("pk_contract_ywcf").clearBodyData();
			panel.getBillModel("pk_contract_cwcf").clearBodyData();
			
			//重算房产租金
			int[] rows=new int[rowcount];
			for(int i=0;i<rowcount;i++){
				rows[i]=i;
			}
			try {
				CalculateRentUtils.recalHouse(billForm.getBillCardPanel(), rows);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}
			
			//重算增长期
			/*int[] rows2=new int[rowcount2];
			for(int i=0;i<rowcount2;i++){
				rows2[i]=i;
			}
			try {
				CalculateRentUtils.recalZzqData(billForm.getBillCardPanel(), rows2);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}*/
		}
		
		if(e.getKey().equals("paystyle")||e.getKey().equals("pk_costproject")){//付款方式-收费项目
			
			BillCardPanel panel=billForm.getBillCardPanel();
			int rowcount4=panel.getBillModel("pk_contract_ywcf").getRowCount();
			int rowcount5=panel.getBillModel("pk_contract_cwcf").getRowCount();
			String key="";
			if(e.getKey().equals("paystyle")){
				key="付款方式";
			}else{
				key="收费项目";
			}
			if(rowcount4>0||rowcount5>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改["+key+"]将清空[业务拆分][财务拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_contract_ywcf").clearBodyData();
					panel.getBillModel("pk_contract_cwcf").clearBodyData();
				}
			}
			
			if(e.getKey().equals("pk_costproject")){
				
				panel.setHeadItem("ndegree", null);
				Object obj=e.getValue();
				if(obj==null){
					return ;
				}
				String sql="select roundtype from zl_costproject where pk_costproject='"+e.getValue()+"'";
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Object objjd=null;
				try {
					objjd=iQ.executeQuery(sql, new ColumnProcessor());
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
				panel.setHeadItem("ndegree", objjd);
			}
		}
		
		if(e.getKey().equals("taxstyle")){
			
			BillCardPanel panel=billForm.getBillCardPanel();
			int rowcount5=panel.getBillModel("pk_contract_cwcf").getRowCount();
			int rowcount6=panel.getBillModel("pk_contract_zqfy").getRowCount();
			int rowcount7=panel.getBillModel("pk_contract_zqfycf").getRowCount();
			if(rowcount5>0||rowcount6>0||rowcount7>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改合同税率将清空[财务拆分][周期费用][周期费用拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_contract_cwcf").clearBodyData();
					panel.getBillModel("pk_contract_zqfy").clearBodyData();
					panel.getBillModel("pk_contract_zqfycf").clearBodyData();
				}
			}
		}
		
		if(e.getKey().equals("is_mz")){
			
			BillCardPanel panel=billForm.getBillCardPanel();
			Object obj=e.getValue();
			UFBoolean boo=new UFBoolean(obj.toString());
			if(boo.booleanValue()){
				//判断免租期
				BillModel modelm=panel.getBillModel("pk_contract_mzq");
				int row=modelm.getRowCount();
				if(row<=0){
					MessageDialog.showHintDlg(billForm, "提示", "包含免租期需先设置表体首行免租起始日期等于合同起始日期！");
					panel.setHeadItem("is_mz", new UFBoolean(false));
					return ;
				}
				if(row>0){
					Object objd=modelm.getValueAt(0, "dstartdate");
					if(objd==null){
						MessageDialog.showHintDlg(billForm, "提示", "免租期的首行起始日期不能为空！");
						panel.setHeadItem("is_mz", new UFBoolean(false));
						return ;
					}
					UFDate udd=new UFDate(objd.toString());
					
					Object objh=panel.getHeadItem("dstartdate").getValueObject();
					UFDate udh=new UFDate(objh.toString());
					
					if(!udd.isSameDate(udh)){
						MessageDialog.showHintDlg(billForm, "提示", "免租期的首行起始日期应等于合同的起始日期！");
						panel.setHeadItem("is_mz", new UFBoolean(false));
						return ;
					}
					
					Object obje=modelm.getValueAt(0, "denddate");
					if(obje==null){
						MessageDialog.showHintDlg(billForm, "提示", "免租期的首行结束日期不能为空！");
						panel.setHeadItem("is_mz", new UFBoolean(false));
						return ;
					}
					
					UFDate ude=new UFDate(obje.toString());
					//校验付款方式页签
					BillModel modelf=panel.getBillModel("pk_contract_fkmx");
					if(modelf.getRowCount()>0){
						Object objn=modelf.getValueAt(0, "dstartdate");
						if(objn!=null){
							UFDate udn=new UFDate(objn.toString());
							if(!udn.afterDate(ude)){
								MessageDialog.showHintDlg(billForm, "提示", "付款明细的首行日期不能早于免租结束日期！");
								panel.setHeadItem("is_mz", new UFBoolean(false));
								return ;
							}
						}
					}
					
					//校验周期方式页签
					BillModel modelz=panel.getBillModel("pk_contract_zqmx");
					if(modelz.getRowCount()>0){
						Object objn=modelz.getValueAt(0, "dstartdate");
						if(objn!=null){
							UFDate udn=new UFDate(objn.toString());
							if(!udn.afterDate(ude)){
								MessageDialog.showHintDlg(billForm, "提示", "周期付款变更的首行日期不能早于免租结束日期！");
								panel.setHeadItem("is_mz", new UFBoolean(false));
								return ;
							}
						}
					}
					
					//end
				}
			}
			
		}
	}
	
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
