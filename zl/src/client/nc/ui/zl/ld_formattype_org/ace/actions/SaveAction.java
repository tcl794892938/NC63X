package nc.ui.zl.ld_formattype_org.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;

import nc.vo.zl.ld_formattype.FormattypeVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.SaveAction{
	
	private ShowUpableBillForm bill;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		//获取前台数据
		FormattypeVO fvo = (FormattypeVO)bill.getValue();
		//编码长度
		int subcodelen = fvo.getCode().length();
		
		//验证数据库是否存在改编码
		String sql_1 = "select  count(*) from zl_formattype where code='"+fvo.getCode()+"' and nvl(dr,0)=0";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count= (Integer)iQ.executeQuery(sql_1, new ColumnProcessor());
		
		//获取多级编码的上级编码
		UIRefPane scUIR=(UIRefPane) bill.getBillCardPanel().getHeadItem("upname").getComponent();
		Object fcode=scUIR.getRefCode();
		
		if(fcode!=null){//fvo.getCode().equals(fcode.toString().substring(0, fcode.toString().length()))
			if(fvo.getCode().length()!=fcode.toString().length()+2 || !fvo.getCode().substring(0, fcode.toString().length()).equals(fcode)){
				MessageDialog.showHintDlg(bill, "提示", "编码规则应为上级编码和两位字符的组合！");
				return;
			}
		}
		
		//一级编码判断
		if(fcode==null&&subcodelen!=2){
			MessageDialog.showHintDlg(bill, "提示", "编码长度应为2！");
			return ;
			
		}
		
		
		
		//增加保存
		if(getModel().getUiState()==UIState.ADD){
			//唯一性判断
			if(count>0){
				MessageDialog.showHintDlg(bill, "提示", "编码不符合唯一性！");
				return ;
			}
			
		}
		if(getModel().getUiState()==UIState.EDIT){
			
			//获取该编码下级编码
			String sql_2 = "select  code from zl_formattype where upname='"+fvo.getPk_formattype()+"' and nvl(dr,0)=0";
			Object soncode= iQ.executeQuery(sql_2, new ColumnProcessor());
			//通过主键获取原编码
			String sql_3 = "select  code from zl_formattype where pk_formattype='"+fvo.getPk_formattype()+"' and nvl(dr,0)=0";
			Object code= iQ.executeQuery(sql_3, new ColumnProcessor());
			//上级修改判断
			if(soncode!=null){
				MessageDialog.showHintDlg(bill, "提示", "存在下级，不可修改编码！请检查");
				return;
			}else{
				if(count>0&&!code.toString().equals(fvo.getCode())){
					MessageDialog.showHintDlg(bill, "提示", "编码不符合唯一性");
					return;
				}
			}
		}
		
		
		
		
		super.doAction(e);
	}
	

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}
