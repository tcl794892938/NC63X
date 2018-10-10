package nc.ui.zl.tcl_contract.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;
import nc.vo.zl.lm_customer.CustomerVO;

public class AceCardBodyAfterEditHandler2 implements IAppEventHandler<CardBodyAfterEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		String tabcode=billForm.getBillCardPanel().getCurrentBodyTableCode();
		BillCardPanel panel=billForm.getBillCardPanel();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BillModel model2=billForm.getBillCardPanel().getBillModel(tabcode);
		
		//======================================================客户页签=============================================================
		if(e.getKey().equals("pk_customer")&&tabcode.equals("pk_contract_cust")){
			
			Object obj=e.getValue();
			billForm.getBillCardPanel().setHeadItem("pk_customer", obj);
			
			String[] key=new String[]{"pk_customer","customertype","sfzh","yyzz","customerlxfs","customeraddress"};
			String[] htkey=new String[]{"pk_customer","customertype","idno","busilicence","lxphone","lxaddress"};
			if(obj==null){
				for(String k:htkey){
					model2.setValueAt(null, e.getRow(), k);
				}
				return ;
			}
			
			String sql="select * from zl_customer r where r.pk_customer='"+obj+"'";
			CustomerVO vo=null;
			try {
				vo=(CustomerVO)iQ.executeQuery(sql, new BeanProcessor(CustomerVO.class));
			} catch (BusinessException e1) {
				Debug.debug(e1.getMessage());
			}
			if(vo==null){
				MessageDialog.showHintDlg(billForm, "提示", "客户数据异常！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			for(int i=0;i<key.length;i++){
				model2.setValueAt(vo.getAttributeValue(key[i]), e.getRow(), htkey[i]);
			}
			
			//更新其它页签客户
			String[] tabcodes=new String[]{"pk_contract_bzj","pk_contract_ywcf","pk_contract_cwcf","pk_contract_zqfy","pk_contract_zqfycf"};
			
			for(String tab:tabcodes){
				
				BillModel model1=panel.getBillModel(tab);
				if(model1.getRowCount()<=0){
					continue ;
				}
				
				for(int i=0;i<model1.getRowCount();i++){
					model1.setValueAt(e.getValue(), i,  model1.getBodyColByKey("pk_customer"));
					model1.getValueAt(i, "pk_customer");
				}
				model1.loadLoadRelationItemValue();
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
