package nc.ui.zl.ld_parkcontract.ace.actions;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;

public class BodyAddAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction {

	private ShowUpableBillForm billform;
	@Override
	public void doAction() {
		try {
			billform.getBillCardPanel().stopEditing();
			billform.getBillCardPanel().dataNotNullValidate();
		} catch (ValidationException e) {
			MessageDialog.showHintDlg(billform, "提示", e.getMessage());
			return ;
		}
		
		String tabcode=billform.getBillCardPanel().getCurrentBodyTableCode();
		
		super.doAction();
		
		if(tabcode.equals("pk_parkcontract_b")){
			Object customer = billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			//获取当前用户车牌号
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql = "select * from zl_carfiles c where nvl(dr,0)=0 and khname='"+customer+"'";
			List<CarFilesVO> listvo = new ArrayList<CarFilesVO>();
			try {
				listvo=(List<CarFilesVO>)iQ.executeQuery(sql, new BeanListProcessor(CarFilesVO.class));
			} catch (BusinessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			Object obj = getBillform().getBillCardPanel()
					.getBillTable("pk_parkcontract_b").getRowCount();
			Integer rowcount = (Integer) (obj == null ? "" : obj);
			billform.getBillCardPanel().getBillModel("pk_parkcontract_b").setValueAt(customer, rowcount-1, "pk_customer");
			if(listvo.size()>0){
				billform.getBillCardPanel().getBillModel("pk_parkcontract_b").setValueAt(listvo.get(0).getPk_carfiles(), rowcount-1, "platenum");
			}
			
		}
		billform.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
