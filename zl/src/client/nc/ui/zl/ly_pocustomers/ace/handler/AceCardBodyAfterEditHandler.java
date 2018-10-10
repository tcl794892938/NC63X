package nc.ui.zl.ly_pocustomers.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pub.BusinessException;

public class AceCardBodyAfterEditHandler implements
		IAppEventHandler<CardBodyAfterEditEvent> {

	private BillForm billform;

	public BillForm getBillform() {
		return billform;
	}

	public void setBillform(BillForm billform) {
		this.billform = billform;
	}

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {

		Object obj=getBillform().getBillCardPanel().getBillTable().getRowCount();
		Integer newrow=(Integer) (obj==null?0:obj);
		Integer nowrow=e.getRow();
		if (e.getKey().equals("tcustomertype")&&nowrow.equals(newrow-1)) {
			Integer num = (Integer) e.getValue();
			switch (num) {
			case 0:
				e.getBillCardPanel().setHeadItem("customert", "目标客户");
				break;
			case 1:
				e.getBillCardPanel().setHeadItem("customert", "意向客户");
				break;
			case 2:
				e.getBillCardPanel().setHeadItem("customert", "淘汰客户");
				break;
			case 3:
				e.getBillCardPanel().setHeadItem("customert", "签约客户");
				break;
			/*
			 * default:
			 * MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示",
			 * "没有选择跟踪状态！"); break;
			 */
			}
		}

		if (e.getKey().equals("procode")) {
			try {
				if(e.getRow()>0){
					for(int i=0;i<e.getRow();i++){
						Object procode=e.getBillCardPanel().getBillModel().getValueAt(i, "procode");
						Object procode1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "procode");
						if(getStgObj(procode).equals(getStgObj(procode1))){
							MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示",
									"项目编码重复请重新选择！");
							e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "procode");
							e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "proname");
							e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "remarks");
							return;
						}
					}
				}
				
				String sql = "select name from zl_project where nvl(dr,0)=0 and pk_project='"
						+ e.getValue() + "'";
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(
						IUAPQueryBS.class);
				Object obj1 = iQ.executeQuery(sql, new ColumnProcessor());
				String name = obj1 == null ? "" : obj1.toString();
				e.getBillCardPanel().getBillModel()
						.setValueAt(name, e.getRow(), "proname");
				
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
		if(e.getKey().equals("building")){
			if(e.getOldValue()==null||e.getOldValue().equals("")){
				return;
			}else{
				if(!e.getOldValue().equals(e.getValue())){
				e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "house");
			}
			}
			
		}

	}
	
	// 获取所选择的项目主键
		public String getPKpro() {
			String pk_pro = "";
			try {
				Object rows = billform.getBillCardPanel()
						.getBillTable("id_pocustomers_org").getRowCount();
				Integer rowcount = (Integer) (rows == null ? "" : rows);
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String procodes = "";
				for (int i = 0; i < rowcount; i++) {
					Object procode = billform.getBillCardPanel().getBillModel("id_pocustomers_org").getValueObjectAt(i, "procode");
					procodes += "'" + getStgObj(procode) + "'";
					if (i < rowcount - 1) {
						procodes += ",";
					}
				}
				String sql = "select pk_project from zl_project where code in ("
						+ procodes + ")";
				@SuppressWarnings("unchecked")
				List<Object> pk_pros = (List<Object>) iQ.executeQuery(sql,
						new ArrayListProcessor());
				for (int j = 0; j < pk_pros.size(); j++) {
					Object[] pk=(Object[]) pk_pros.get(j);
					pk_pro += "'" + pk[0] + "'";
					if (j < pk_pros.size() - 1) {
						pk_pro += ",";
					}
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			return pk_pro;
		}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
