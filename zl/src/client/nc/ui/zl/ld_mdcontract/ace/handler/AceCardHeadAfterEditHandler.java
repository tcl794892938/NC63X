package nc.ui.zl.ld_mdcontract.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.ld_mdcontract.MdcontractVO;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{
	private ShowUpableBillForm billform;
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		BillModel model2 = billform.getBillCardPanel().getBillModel("pk_mdcontract_c");
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if(e.getKey().equals("enddate")){
			if(e.getValue()==null){
				billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			int rowcount=model2.getRowCount();
			if(rowcount>0){
				int it=MessageDialog.showOkCancelDlg(billform, "��ʾ", "����ʱ�佫��ձ�����Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").clearBodyData();
			}
			
			
			UFDate eddate = new UFDate(e.getValue().toString());
			if(e.getBillCardPanel().getHeadItem("startdate").getValueObject()!=null){
				
				UFDate stdate = new UFDate(e.getBillCardPanel().getHeadItem("startdate").getValueObject().toString());
				
				if(eddate.before(stdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ͬ��ֹʱ�䲻��С�ں�ͬ��ʼʱ�䣡");
					e.getBillCardPanel().getHeadItem("enddate").setValue(null);
					return;
				}
			}
			
		
			/*if(rowcount>0){
				for(int i=0;i<rowcount;i++){
					if(model2.getValueAt(i, "menddate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
						if(eddate.beforeDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ���ý�ֹʱ�䲻��С��֮ǰ���ý�ֹʱ�䣡");
							e.getBillCardPanel().getHeadItem("enddate").setValue(null);
							return;
						}
					}
					if(model2.getValueAt(i, "mstartdate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "mstartdate").toString());
						if(eddate.beforeDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ���ý�ֹʱ�䲻��С��֮ǰ���ÿ�ʼʱ�䣡");
							e.getBillCardPanel().getHeadItem("enddate").setValue(null);
							return;
						}
					}
				}
			}*/
			String sql1 = "select * from zl_mdcontract m where nvl(dr,0)=0 and  m.version=-1";
			List<MdcontractVO> listvo = null;
			try {
				listvo=(List<MdcontractVO>)iQ.executeQuery(sql1, new BeanListProcessor(MdcontractVO.class));
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			Object obj1 = e.getBillCardPanel().getHeadItem("pk_mdcontract").getValueObject();
			Object obj2 = e.getBillCardPanel().getHeadItem("vdef1").getValueObject();
			for(MdcontractVO vo:listvo){
				if(obj1!=null){
					if(getStgObj(vo.getVsrcid()).equals(getStgObj(obj1))){
						UFDate end1 = getUFdObj(e.getValue());
						UFDate str1 = getUFdObj(vo.getStartdate());
						if(end1.afterDate(str1)||end1.equals(str1)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ֹʱ�䲻������Լ��ͬ��ʼʱ��֮��");
							e.getBillCardPanel().getHeadItem(e.getKey()).setValue(null);
							return;
						}
					}
				}
				
			}
			
		}
		
		if(e.getKey().equals("startdate")){
			
			if(e.getValue()==null){
				billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
				return ;
			}
			int rowcount=model2.getRowCount();
			if(rowcount>0){
				int it=MessageDialog.showOkCancelDlg(billform, "��ʾ", "����ʱ�佫��ձ�����Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").clearBodyData();
			}
			UFDate stdate = new UFDate(e.getValue().toString());
			if(e.getBillCardPanel().getHeadItem("enddate").getValueObject()!=null){
				
				UFDate eddate = new UFDate(e.getBillCardPanel().getHeadItem("enddate").getValueObject().toString());
				
				if(eddate.before(stdate)){
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ͬ��ֹʱ�䲻��С�ں�ͬ��ʼʱ�䣡");
					e.getBillCardPanel().getHeadItem("startdate").setValue(null);
					return;
				}
			}
			
			
			/*if(rowcount>0){
				for(int i=0;i<rowcount;i++){
					if(model2.getValueAt(i, "menddate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "menddate").toString());
						if(stdate.afterDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ʼʱ�䲻����֮ǰ���ý�ֹʱ��֮��");
							e.getBillCardPanel().getHeadItem("startdate").setValue(null);
							return;
						}
					}
					if(model2.getValueAt(i, "mstartdate")!=null){
						UFDate odate = new UFDate(model2.getValueAt(i, "mstartdate").toString());
						if(stdate.afterDate(odate)){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ʼʱ�䲻���ڷ��ÿ�ʼʱ��֮��");
							e.getBillCardPanel().getHeadItem("startdate").setValue(null);
							return;
						}
					}
				}
			}*/
			String sql1 = "select * from zl_mdcontract m where nvl(dr,0)=0 and  m.version=-1";
			List<MdcontractVO> listvo = null;
			try {
				listvo=(List<MdcontractVO>)iQ.executeQuery(sql1, new BeanListProcessor(MdcontractVO.class));
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			Object obj1 = e.getBillCardPanel().getHeadItem("pk_mdcontract").getValueObject();
			Object obj2 = e.getBillCardPanel().getHeadItem("vdef1").getValueObject();
			for(MdcontractVO vo:listvo){
				if(getStgObj(vo.getPk_mdcontract()).equals(getStgObj(obj2))){
					UFDate str2 = getUFdObj(e.getValue());
					UFDate end2 = getUFdObj(vo.getEnddate());
					if(end2.afterDate(str2)||end2.equals(str2)){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "��ʾ", "��ǰ��ͬ��ʼʱ�䲻���ڱ���Լ��ͬ��ֹʱ��֮ǰ��");
						e.getBillCardPanel().getHeadItem(e.getKey()).setValue(null);
						return;
					}
				}
				
			}
		}
		
		if(e.getKey().equals("taxrate")){
			
			if(model2.getRowCount()>0){
				for(int i=0;i<model2.getRowCount();i++){
					if(model2.getValueAt(i, "receivemoney")!=null){
						Object rm = model2.getValueAt(i, "receivemoney");
						UFDouble drm = new UFDouble(rm.toString());
						//˰��
						Object  obj2= e.getValue();
						Integer taxrate = Integer.valueOf(obj2.toString());
						UFDouble tax = drm.multiply(taxrate);
						UFDouble taxm = tax.div(100);
						//��˰���
						UFDouble freetax = drm.sub(taxm);
						model2.setValueAt(freetax, i, "freetaxmoney");
						model2.setValueAt(taxm, i, "taxmoney");
					}
				}
				
			}
		}
		//�ͻ��༭���¼�
		if(e.getKey().equals("pk_customer")){
			int rowcount3=billform.getBillCardPanel().getBillModel("pk_mdcontract_c").getRowCount();	
			for(int i=0;i<rowcount3;i++){
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(e.getValue(), i, 2);	
			}
		}
		if(e.getKey().equals("pk_project")){
			int rowcount=model2.getRowCount();
			if(rowcount>0){
				int it=MessageDialog.showOkCancelDlg(billform, "��ʾ", "������Ŀ����ձ�����Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				billform.getBillCardPanel().getHeadItem("pk_customer").setValue(null);
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").clearBodyData();
			}
		}
		if(e.getKey().equals("pk_contracttype")){
			int rowcount=model2.getRowCount();
			if(rowcount>0){
				int it=MessageDialog.showOkCancelDlg(billform, "��ʾ", "���ĺ�ͬ���ͽ���ձ�����Ϣ���Ƿ�ȷ�ϸ��ģ�");
				if(it!=UIDialog.ID_OK){
					billform.getBillCardPanel().setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}
				billform.getBillCardPanel().getHeadItem("pk_customer").setValue(null);
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").clearBodyData();
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
