package nc.ui.zl.ld_housesource.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;


public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	private ShowUpableBillForm billForm;
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		if(e.getKey().equals("projectname")){
			
			billForm.getBillCardPanel().setHeadItem("buildname", null);
			billForm.getBillCardPanel().setHeadItem("unit", null);
		}
		
		if(e.getKey().equals("buildname")){
			billForm.getBillCardPanel().setHeadItem("unit", null);
			if(e.getValue()==null){
				return ;
			}
			Object objpk=billForm.getBillCardPanel().getHeadItem("pk_housesource").getValueObject();
			//校验该楼栋是否已经有房产
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql="select count(1) from zl_housesource where buildname ='"+e.getValue()+"' and pk_housesource<>'"+objpk+"'";
			Integer it=0;
			try {
				it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			if(it==0){
				MessageDialog.showHintDlg(billForm, "提示", "该楼栋还未有房产，不可新增，请先建房！");
				billForm.getBillCardPanel().setHeadItem("buildname", null);
				return ;
			}
		}
		
		if(e.getKey().equals("unit")){
			if(e.getValue()==null){
				return ;
			}
			Object objpk=billForm.getBillCardPanel().getHeadItem("pk_housesource").getValueObject();
			Object objld=billForm.getBillCardPanel().getHeadItem("buildname").getValueObject();
			//校验单元是否存在
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql="select count(1) from zl_housesource where buildname ='"+objld+"' and unit='"+e.getValue()+"' and pk_housesource<>'"+objpk+"' and dr=0";
			Integer it=0;
			try {
				it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			if(it<=0){
				MessageDialog.showHintDlg(billForm, "提示", "该楼栋下该单元不存在！");
				billForm.getBillCardPanel().setHeadItem("unit", null);
				return ;
			}
		}
		
		if(e.getKey().equals("roomnumber")||e.getKey().equals("estatecode")||e.getKey().equals("estatename")){
			
			Object objpk=billForm.getBillCardPanel().getHeadItem("pk_housesource").getValueObject();
			Object objxm=billForm.getBillCardPanel().getHeadItem("projectname").getValueObject();
			Object objld=billForm.getBillCardPanel().getHeadItem("buildname").getValueObject();
			Object objunit=billForm.getBillCardPanel().getHeadItem("unit").getValueObject();
			if(objxm==null||objld==null||objunit==null){
				MessageDialog.showHintDlg(billForm, "提示", "请先维护项目，楼栋，单元！");
				billForm.getBillCardPanel().setHeadItem(e.getKey(), null);
				return ;
			}
			
			//校验是否重复
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql="select count(1) from zl_housesource where buildname ='"+objld+"' and unit='"+objunit+"' and "+e.getKey()+"='"+e.getValue()+"' and pk_housesource<>'"+objpk+"'" +
					" and dr=0";
			Integer it=0;
			try {
				it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			if(it>0){
				MessageDialog.showHintDlg(billForm, "提示", "录入的信息"+e.getValue()+"存在重复，请核查！");
				billForm.getBillCardPanel().setHeadItem(e.getKey(), null);
				return ;
			}
		}
		
		if(e.getKey().equals("housestate")){
			Object obj=e.getValue();
			if(e.getValue()==null){
				return ;
			}
			Integer it=Integer.valueOf(obj.toString());
			if(it==2||it==3){
				MessageDialog.showHintDlg(billForm, "提示", "房产状态只能录入自用或空置！");
				billForm.getBillCardPanel().setHeadItem(e.getKey(), null);
				return ;
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
