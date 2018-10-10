package nc.ui.zl.ld_parkcontract.ace.handler;

import java.util.List;

import com.ufida.web.html.I;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {
	
	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		
		String tabcode=e.getBillCardPanel().getCurrentBodyTableCode();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		if(e.getKey().equals("ndiscountmoney")&&tabcode.equals("pk_parkcontract_c")){
			int row = billForm.getBillCardPanel().getBillModel("pk_parkcontract_f").getRowCount();
			BillModel model = billForm.getBillCardPanel().getBillModel("pk_parkcontract_f");
			if(row>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有财务拆分数据，是否重新生成？");
				if(it!=UIDialog.ID_OK){
					return ;
				}
			}
			model.clearBodyData();
		}
		//车牌号
		if(e.getKey().equals("platenum")&&tabcode.equals("pk_parkcontract_b")){
			Object pk_customer = billForm.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem("platenum").getComponent();
			ref.getRefModel().addWherePart(" and khname='"+pk_customer+"'");
		}
		//当前组织、项目下的车位区
		if(e.getKey().equals("parkarea")&&tabcode.equals("pk_parkcontract_b")){
			Object pk_project = billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			Object pk_org = billForm.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			//查询通过有车位的房源查询了楼房
			String sql ="select buildname from zl_housesource h where nvl(dr,0)=0 and pk_org='"+pk_org+
					"' and h.projectname='"+pk_project+"' and h.pk_familyfile in(select pk_familyfile from zl_familyfile ff where nvl(dr,0)=0"+
					" and ff.pk_formattypeid in(select pk_formattype from zl_formattype where nvl(dr,0)=0 and code='03')) group by buildname";
			String sql2 ="select count(buildname) from zl_housesource h where nvl(dr,0)=0 and pk_org='"+pk_org+
					"' and h.projectname='"+pk_project+"' and h.pk_familyfile in(select pk_familyfile from zl_familyfile ff where nvl(dr,0)=0"+
					" and ff.pk_formattypeid in(select pk_formattype from zl_formattype where nvl(dr,0)=0 and code='03')) group by buildname";
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			Object count = null;
			try {
				count =  iQ.executeQuery(sql2, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			/*if(getIntObj(count)>0){
				ref.getRefModel().addWherePart(" and pk_buildingfile in("+sql+") ");
			}*/
			ref.getRefModel().addWherePart(" and pk_buildingfile in("+sql+") ");
		}
		//当前组织、项目下的车位号
		if(e.getKey().equals("parknum")&&tabcode.equals("pk_parkcontract_b")){
			//Object parkarea = e.getBillCardPanel().getBillModel(tabcode).;
			//Object parkarea = billForm.getBillCardPanel().getBillTable(tabcode).getValueAt(e.getRow(), 1);
			
			UIRefPane ref2=(UIRefPane)e.getBillCardPanel().getBodyItem("parkarea").getComponent();
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			
			ref.getRefModel().addWherePart(" and buildname = '"+ref2.getRefPK()+"' and housestate=0");
			ref.getRefModel().getRefSql();
		}
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	public Integer getIntObj(Object obj){
		return obj==null?0:Integer.parseInt(obj.toString());
	}
}
