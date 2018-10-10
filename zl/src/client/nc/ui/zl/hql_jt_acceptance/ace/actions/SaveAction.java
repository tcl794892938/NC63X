package nc.ui.zl.hql_jt_acceptance.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;
import nc.vo.zl.hql_jt_acceptance.Jt_acceptanceVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction {
	ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		//ȡ���༭����
		bill.getBillCardPanel().stopEditing();
		//���ñ������Ϊ��
		bill.getBillCardPanel().dataNotNullValidate();
		//���տ�Ƭ�����ϵ�����
		AggJt_acceptanceVO aggvo = (AggJt_acceptanceVO) bill.getValue();
		Jt_acceptanceVO vo = aggvo.getParentVO();
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		//�޸�ʱ���ı���벻У�����Ψһ��
		if(getModel().getUiState() == UIState.EDIT){
			String getcode="select code from zl_jt_acceptance where nvl(dr,0)=0 and pk_acceptance='"+vo.getPk_acceptance()+"'";
			Object code=iQ.executeQuery(getcode, new ColumnProcessor());
			if(code.toString().length()!=vo.getCode().toString().length()){
				bill.getBillCardPanel().setHeadItem("code",code);
				throw new Exception("���볤�Ȳ����޸�");
			}
			
			super.doAction(e);
		}
		if(getModel().getUiState()==UIState.ADD){
			
			//��ѯ���ݿ���˳�������Ŀ�����������ݵ�����������
			String sql_code = "select pk_acceptance,code from zl_jt_acceptance where nvl(dr,0)=0 " +
					" and pk_org = '"+vo.getPk_org()+"'";
			
			List<Map<String, Object>> listmap = (List<Map<String, Object>>) iQ.executeQuery(sql_code, 
					new MapListProcessor());
			//����ʱУ��������
			if(vo.getCode().length()%2 != 0 || vo.getCode().length() > 8){
				MessageDialog.showHintDlg(bill, "��ʾ", "�������ӦΪ�ϼ��������λ�ַ�����ϣ��ҳ��Ȳ�����8λ��");
				bill.getBillCardPanel().setHeadItem("code", null);
				return;
			}
			if(vo.getCode().length() > 2 && vo.getPk_parent() == null){
				MessageDialog.showHintDlg(bill, "��ʾ", "�������ӦΪ�ϼ��������λ�ַ�����ϣ������һ�����볤�ȱ���Ϊ2λ��");
				bill.getBillCardPanel().setHeadItem("code", null);
				return;
			}
			
			//����ʱУ������Ψһ��
			for(int i = 0;i < listmap.size();i++){
				if(listmap.get(i).get("code").equals(vo.getCode())){
					MessageDialog.showHintDlg(bill, "��ʾ", "���������Ѵ�������������");
					bill.getBillCardPanel().setHeadItem("code", null);
					return;
				}
			}
			bill.getBillCardPanel().setHeadItem("vdef1", 0);
			super.doAction(e);
		}
		
	}
	
}
