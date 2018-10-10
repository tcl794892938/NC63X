package nc.ui.zl.hql_jt_acceptance.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;

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
				if(code.length()%2 == 0 && code.length() > 2 && code.length() <= 8){
					try {
					IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
					String sql = "select pk_acceptance from zl_jt_acceptance where " +
							     "nvl(dr,0)=0 and code = '" + code.substring(0,code.length()-2) + 
							     "' and pk_org = '"+org+"'";
					Object obj  = iQ.executeQuery(sql, new ColumnProcessor());
					if(obj==null){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "上级节点在档案中不存在！");
						e.getBillCardPanel().setHeadItem("code", null);
						return;
					}
					if(obj != null){
						String sql_pa = "select count(*) from zl_jt_acceptance where nvl(dr,0)=0 and nvl(vdef1,0)='1' and "+
								        "pk_parent = '"+obj+"'";
						Integer count = (Integer) iQ.executeQuery(sql_pa, new ColumnProcessor());
							if(count>0){
								MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "上级节点中存在项目级档案，不可新增！");
								e.getBillCardPanel().setHeadItem("code", null);
								return;
							}
						e.getBillCardPanel().setHeadItem("pk_parent", obj);
					}
					} catch (BusinessException e1) {
						e1.printStackTrace();
					}
				}else if(code.length() != 2){
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
