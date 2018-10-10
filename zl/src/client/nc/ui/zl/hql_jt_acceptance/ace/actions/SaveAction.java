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
		// TODO 自动生成的方法存根
		//取消编辑监听
		bill.getBillCardPanel().stopEditing();
		//设置必输项不可为空
		bill.getBillCardPanel().dataNotNullValidate();
		//接收卡片界面上的数据
		AggJt_acceptanceVO aggvo = (AggJt_acceptanceVO) bill.getValue();
		Jt_acceptanceVO vo = aggvo.getParentVO();
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		//修改时不改变编码不校验编码唯一性
		if(getModel().getUiState() == UIState.EDIT){
			String getcode="select code from zl_jt_acceptance where nvl(dr,0)=0 and pk_acceptance='"+vo.getPk_acceptance()+"'";
			Object code=iQ.executeQuery(getcode, new ColumnProcessor());
			if(code.toString().length()!=vo.getCode().toString().length()){
				bill.getBillCardPanel().setHeadItem("code",code);
				throw new Exception("编码长度不可修改");
			}
			
			super.doAction(e);
		}
		if(getModel().getUiState()==UIState.ADD){
			
			//查询数据库进退场验收项目表中所有数据的主键、编码
			String sql_code = "select pk_acceptance,code from zl_jt_acceptance where nvl(dr,0)=0 " +
					" and pk_org = '"+vo.getPk_org()+"'";
			
			List<Map<String, Object>> listmap = (List<Map<String, Object>>) iQ.executeQuery(sql_code, 
					new MapListProcessor());
			//保存时校验编码规则
			if(vo.getCode().length()%2 != 0 || vo.getCode().length() > 8){
				MessageDialog.showHintDlg(bill, "提示", "编码规则应为上级编码和两位字符的组合，且长度不超过8位！");
				bill.getBillCardPanel().setHeadItem("code", null);
				return;
			}
			if(vo.getCode().length() > 2 && vo.getPk_parent() == null){
				MessageDialog.showHintDlg(bill, "提示", "编码规则应为上级编码和两位字符的组合，如果是一级编码长度必须为2位！");
				bill.getBillCardPanel().setHeadItem("code", null);
				return;
			}
			
			//保存时校验编码的唯一性
			for(int i = 0;i < listmap.size();i++){
				if(listmap.get(i).get("code").equals(vo.getCode())){
					MessageDialog.showHintDlg(bill, "提示", "档案编码已存在请重新输入");
					bill.getBillCardPanel().setHeadItem("code", null);
					return;
				}
			}
			bill.getBillCardPanel().setHeadItem("vdef1", 0);
			super.doAction(e);
		}
		
	}
	
}
