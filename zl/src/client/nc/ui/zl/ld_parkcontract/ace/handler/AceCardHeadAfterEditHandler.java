package nc.ui.zl.ld_parkcontract.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.tcl_contract.ace.config.CalendarUtls;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	private ShowUpableBillForm billForm;
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		BillCardPanel panel=billForm.getBillCardPanel();
		int rowcount1=panel.getBillModel("pk_parkcontract_c").getRowCount();
		int rowcount2=panel.getBillModel("pk_parkcontract_f").getRowCount();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if(e.getKey().equals("enddate")||e.getKey().equals("startdate")){
			
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "����ʱ�佫���[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				panel.getBillModel("pk_parkcontract_c").clearBodyData();
				panel.getBillModel("pk_parkcontract_f").clearBodyData();
			}
			
			
			
			Object obj1=e.getBillCardPanel().getHeadItem("enddate").getValueObject();
			Object obj2=e.getBillCardPanel().getHeadItem("startdate").getValueObject();
			if(obj1==null||obj2==null){
				return ;
			}
			UFDate eddate = new UFDate(obj1.toString());
			UFDate stdate = new UFDate(obj2.toString());
			if(eddate.before(stdate)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ͬ��ֹʱ�䲻��С�ں�ͬ��ʼʱ�䣡");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				return;
			}
			
			/*String sql1 = "select * from zl_mdcontract m where nvl(dr,0)=0 and  m.version=-1";
			List<ParkcontractVO> listvo = null;
			try {
				listvo=(List<ParkcontractVO>)iQ.executeQuery(sql1, new BeanListProcessor(ParkcontractVO.class));
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			Object obj3 = e.getBillCardPanel().getHeadItem("pk_parkcontract").getValueObject();
			Object obj4 = e.getBillCardPanel().getHeadItem("vsrcbid").getValueObject();
			for(ParkcontractVO vo:listvo){
				if(e.getKey().equals("enddate")){
					if(obj3!=null){
						if(getStgObj(vo.getVsrcbid()).equals(getStgObj(obj1))){
							UFDate end1 = getUFdObj(e.getValue());
							UFDate str1 = getUFdObj(vo.getStartdate());
							if(end1.afterDate(str1)){
								MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ֹʱ�䲻������Լ��ͬ��ʼʱ��֮��");
								e.getBillCardPanel().getHeadItem(e.getKey()).setValue(null);
								return;
							}
						}
					}
				}else if(e.getKey().equals("startdate")){
					if(getStgObj(vo.getPk_parkcontract()).equals(getStgObj(obj4))){
						UFDate str2 = getUFdObj(e.getValue());
						UFDate end2 = getUFdObj(vo.getEnddate());
						if(end2.afterDate(str2)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ʼʱ�䲻���ڱ���Լ��ͬ��ֹʱ��֮ǰ��");
							e.getBillCardPanel().getHeadItem(e.getKey()).setValue(null);
							return;
						}
					}
				}
			}*/
		}
		
		
		//�շѱ�׼�༭���¼�
		if(e.getKey().equals("pk_feescale")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "�����շѱ�׼�����[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				panel.getBillModel("pk_parkcontract_c").clearBodyData();
				panel.getBillModel("pk_parkcontract_f").clearBodyData();
			}
			IUAPQueryBS IQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql = "select accountscal from zl_feescale e where nvl(dr,0)=0 and e.pk_feescale='"+e.getValue()+"'";
			Object accountscal = null;
			try {
				accountscal =  IQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			UFDouble nrentmoney = new UFDouble(accountscal.toString());
			e.getBillCardPanel().setHeadItem("nallrent", nrentmoney);
		}
		
		if(e.getKey().equals("costcycle")||e.getKey().equals("pk_costproject")){//���ʽ-�շ���Ŀ
			
			
			int rowcount4=panel.getBillModel("pk_parkcontract_c").getRowCount();
			int rowcount5=panel.getBillModel("pk_parkcontract_f").getRowCount();
			String key="";
			if(e.getKey().equals("costcycle")){
				key="���ʽ";
			}else{
				key="�շ���Ŀ";
			}
			if(e.getValue()==null){
				e.getBillCardPanel().setHeadItem(e.getKey(), "");
				e.getBillCardPanel().setHeadItem("pk_feescale", "");
			}
			if(e.getKey().equals("costcycle")){
				Object obj1=e.getBillCardPanel().getHeadItem("enddate").getValueObject();
				Object obj2=e.getBillCardPanel().getHeadItem("startdate").getValueObject();
				if(obj1!=null&&obj2!=null){
					UFDate eddate = new UFDate(obj1.toString());
					UFDate stdate = new UFDate(obj2.toString());
					int count = CalendarUtls.getBetweenMonths(stdate, eddate);
					int count2 = Integer.parseInt(e.getValue().toString());
					if(count2>count&&count2!=13){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "�������ڴ��ں�ͬ��Чʱ�䳤�ȣ�");
						panel.setHeadItem(e.getKey(), 0);
						return;
					}
				}
				
				
			}
			if(rowcount4>0||rowcount5>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "����["+key+"]�����[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_parkcontract_c").clearBodyData();
					panel.getBillModel("pk_parkcontract_f").clearBodyData();
				}
			}
			
		}
		if(e.getKey().equals("pk_project")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "������Ŀ�����[������Ϣ][������Ϣ][�����̯]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				
				panel.getBillModel("pk_parkcontract_b").clearBodyData();
				panel.getBillModel("pk_parkcontract_c").clearBodyData();
				panel.getBillModel("pk_parkcontract_f").clearBodyData();
			}
			panel.setHeadItem("pk_customer", null);
		}
		if(e.getKey().equals("ntaxrate")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "������Ŀ�����[[�����̯]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				panel.getBillModel("pk_parkcontract_f").clearBodyData();
			}
			
		}
		//�ͻ��༭���¼�
		if(e.getKey().equals("pk_customer")){
			if(rowcount1>0||rowcount2>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "���Ŀͻ����ƽ����[ҵ����][������]��Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_parkcontract_c").clearBodyData();
					panel.getBillModel("pk_parkcontract_f").clearBodyData();
				}
			}
			int rowcount3=panel.getBillModel("pk_parkcontract_b").getRowCount();
			if(rowcount3>0){
				for(int i=0;i<rowcount3;i++){
					panel.getBillModel("pk_parkcontract_b").setValueAt(e.getValue(), i, 4);
					panel.getBillModel("pk_parkcontract_b").setValueAt(null, i, 6);
					
				}
			}
			
		}
	}
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	public UFDate getUFdObj(Object obj){
		return obj==null?new UFDate():new UFDate(obj.toString());
	}
	
}
