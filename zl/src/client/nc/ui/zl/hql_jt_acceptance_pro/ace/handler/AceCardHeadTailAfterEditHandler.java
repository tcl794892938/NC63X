package nc.ui.zl.hql_jt_acceptance_pro.ace.handler;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;

public class AceCardHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	private BillManageModel bm;
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		
		/*if(e.getKey().equals("code")&&getBm().getUiState()==UIState.EDIT){
			if(e.getValue().toString().length()!=e.getOldValue().toString().length()){
				MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "编码长度不可修改！");
				e.getBillCardPanel().setHeadItem("code",e.getOldValue());
				return;
			}
			return;
		}*/
		
		if(e.getKey().equals("code")){
			Object codeObj = e.getBillCardPanel().getHeadItem("code").getValueObject();
			Object orgObj = e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			
			if(codeObj != null && orgObj != null){
				String code = codeObj.toString();
				String org = orgObj.toString();
				if(code.length() == 2){
					MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "项目级节点不可新增一级档案！");
					e.getBillCardPanel().setHeadItem("code",null);
					return;
				}
				
				if(code.length()%2 == 0 && code.length() > 2 && code.length() <= 8){
					try {
					IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
					String sql = "select pk_acceptance,code,pk_project from zl_jt_acceptance where " +
							     "nvl(dr,0)=0 and code = '" + code.substring(0,code.length()-2) + "'" +
							     " and pk_org = '"+org+"'";
					Map<String, Object> map = (Map<String, Object>) iQ.executeQuery(sql, new MapProcessor());
					BillItem project = e.getBillCardPanel().getHeadItem("pk_project");
					/*for(int i = 0;i < listmap.size();i++){
						if(listmap.get(i).get("code").equals(code.substring(0,code.length()-2))){
							((UIRefPane)project.getComponent()).setPK(listmap.get(i).get("pk_project"));
					        project.setEdit(false);
							((UIRefPane)parent.getComponent()).setPK(listmap.get(i).get("pk_acceptance"));
						}
					}*/
					if(map != null){
						String sql_pa = "select count(*) from zl_jt_acceptance where nvl(dr,0)=0 and nvl(vdef1,0)='0' and "+
						        "pk_parent = '"+map.get("pk_acceptance")+"'";
						Integer count = (Integer) iQ.executeQuery(sql_pa, new ColumnProcessor());
							if(count>0){
								MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "上级节点中存在组织级档案，不可新增！");
								e.getBillCardPanel().setHeadItem("code", null);
								return;
							}
							e.getBillCardPanel().setHeadItem("pk_parent", map.get("pk_acceptance"));
						if(map.get("pk_project") == null){
							project.setEdit(true);
						}else {
							project.setEdit(false);
							((UIRefPane)project.getComponent()).setPK(map.get("pk_project"));
						}
					}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}else{
					MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "编码格式(2-2-2-2)！");
					e.getBillCardPanel().setHeadItem("code", null);
					return;
				}
			}
		}
		
	}

	public BillManageModel getBm() {
		return bm;
	}

	public void setBm(BillManageModel bm) {
		this.bm = bm;
	}

}
