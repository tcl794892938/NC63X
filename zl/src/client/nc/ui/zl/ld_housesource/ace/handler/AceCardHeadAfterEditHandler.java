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
			//У���¥���Ƿ��Ѿ��з���
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql="select count(1) from zl_housesource where buildname ='"+e.getValue()+"' and pk_housesource<>'"+objpk+"'";
			Integer it=0;
			try {
				it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			if(it==0){
				MessageDialog.showHintDlg(billForm, "��ʾ", "��¥����δ�з������������������Ƚ�����");
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
			//У�鵥Ԫ�Ƿ����
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql="select count(1) from zl_housesource where buildname ='"+objld+"' and unit='"+e.getValue()+"' and pk_housesource<>'"+objpk+"' and dr=0";
			Integer it=0;
			try {
				it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			if(it<=0){
				MessageDialog.showHintDlg(billForm, "��ʾ", "��¥���¸õ�Ԫ�����ڣ�");
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
				MessageDialog.showHintDlg(billForm, "��ʾ", "����ά����Ŀ��¥������Ԫ��");
				billForm.getBillCardPanel().setHeadItem(e.getKey(), null);
				return ;
			}
			
			//У���Ƿ��ظ�
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
				MessageDialog.showHintDlg(billForm, "��ʾ", "¼�����Ϣ"+e.getValue()+"�����ظ�����˲飡");
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
				MessageDialog.showHintDlg(billForm, "��ʾ", "����״ֻ̬��¼�����û���ã�");
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
