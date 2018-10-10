package nc.ui.zl.lyw_billconfirmed.ace.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedBVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedVO;

public class RefreshAction extends NCAction{

	private BillManageModel model;
	private ShowUpableBillForm billform;
	
	
	public RefreshAction(){
		this.setBtnName("刷新");
		this.setCode("cardrefresh");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj = getModel().getSelectedData();
		
		AggBillconfirmedVO aggvo1 = new AggBillconfirmedVO();
		AggBillconfirmedVO aggvo = (AggBillconfirmedVO)obj;
		String pk=aggvo.getParentVO().getPk_billconfirmed();
		//根据pk查询
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		//主表
		String sql_head = "select * from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed='"+pk+"'";
		BillconfirmedVO vo = (BillconfirmedVO)iQ.executeQuery(sql_head, new BeanProcessor(BillconfirmedVO.class));
		aggvo1.setParent(vo);
		//子表
		String sql_body = "select * from zl_billconfirmedb d where nvl(d.dr,0)=0 and d.pk_billconfirmed='"+pk+"'";
		List<BillconfirmedBVO> bList = (List<BillconfirmedBVO>)iQ.executeQuery(sql_body, new BeanListProcessor(BillconfirmedBVO.class));
		aggvo1.setChildren(BillconfirmedBVO.class,
				bList.toArray(new BillconfirmedBVO[0]));
		
		getModel().directlyUpdate(aggvo1);
		
	}
	
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	@Override
	protected boolean isActionEnable() {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		return true;
	}
}
