package nc.ui.zl.costproject.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.SaveAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		ShowUpableBillForm billform=(ShowUpableBillForm)getEditor();
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		BillCardPanel panel = billform.getBillCardPanel();
		CostprojectVO vo=(CostprojectVO) billform.getValue();
		UIRefPane scUIR=(UIRefPane) billform.getBillCardPanel().getHeadItem("pk_vid").getComponent();
		Object fcode=scUIR.getRefCode();
		if(fcode==null && vo.getCode().length()!=2){
			MessageDialog.showHintDlg(billform, "��ʾ", "һ�����볤�ȱ���Ϊ2λ��");
			return;
		}
		if(fcode!=null){
			if(vo.getCode().length()!=(fcode.toString().length()+2) || !vo.getCode().substring(0, fcode.toString().length()).equals(fcode)){
				MessageDialog.showHintDlg(billform, "��ʾ", "�������ӦΪ�ϼ��������λ�ַ�����ϣ�");
				return;
			}
		}
		String sql="select count(*) from zl_costproject where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count =(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(getModel().getUiState()==UIState.ADD){
			
			if(count>0){
				MessageDialog.showHintDlg(billform, "��ʾ", "���벻����Ψһ��");
				return;
			}
			
			//��ȡ����
			//Object group=panel.getHeadItem("pk_group").getValueObject();
			//panel.setHeadItem("pk_org","0001A910000000000LRO");
			//panel.getHeadItem("pk_org").getValueObject();
			//��������
			panel.setHeadItem("vdef1", "0");
			//panel.getHeadItem("vdef1").getValueObject();
		}
		if(getModel().getUiState()==UIState.EDIT){
			
			//panel.getHeadItem("pk_org").getValueObject();
			String sql2="select * from zl_costproject where nvl(dr,0)=0 and pk_costproject='"+vo.getPk_costproject()+"'";
			CostprojectVO vo2= (CostprojectVO) iQ.executeQuery(sql2, new BeanProcessor(CostprojectVO.class));
			
			String sql3="select count(*) from zl_costproject where nvl(dr,0)=0 and pk_vid='"+vo.getCode()+"'";
			int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
			if(a>0&&!vo.getCode().equals(vo2.getCode())){
				MessageDialog.showHintDlg(billform, "��ʾ", "�����¼��������޸ı��룡����");
				return;
			}
			if(vo.getCode().equals(vo2.getCode())&& count>1){
				MessageDialog.showHintDlg(billform, "��ʾ", "���벻����Ψһ��");
				return;
			}
			if(!vo.getCode().equals(vo2.getCode()) && count >0){
				MessageDialog.showHintDlg(billform, "��ʾ", "���벻����Ψһ��");
				return;
			}
			
			
		}
		
		super.doAction(e);
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
