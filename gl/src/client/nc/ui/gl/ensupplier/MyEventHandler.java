package nc.ui.gl.ensupplier;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.card.BillCardUI;
import nc.ui.trade.card.CardEventHandler;
import nc.vo.bd.bankaccount.BankAccbasVO;

public class MyEventHandler extends CardEventHandler {

	public MyEventHandler(BillCardUI billUI, ICardController control) {
		super(billUI, control);
	}

	@Override
	protected void onBoElse(int intBtn) throws Exception {
		
	}
	
	

	@Override
	protected void onBoExport() throws Exception {
		
		BillCardPanel panel=this.getBillCardPanelWrapper().getBillCardPanel();
		BillModel model=panel.getBillModel();
		int[] rows=panel.getBillTable().getSelectedRows();
		if(rows==null||rows.length<=0){
			MessageDialog.showHintDlg(getBillUI(), "提示", "请选择数据！");
			return ;
		}
		if(rows.length>500){
			MessageDialog.showHintDlg(getBillUI(), "提示", "最多同时停用500条数据，请分批停用！");
			return ;
		}
		
		String str="";
		for(int i=0;i<rows.length;i++){
			Object obj=model.getValueAt(rows[i], "pk_bankaccbas");
			String ss=obj==null?"":obj.toString();
			str+="'"+ss+"',";
		}
		str=str.substring(0, str.lastIndexOf(","));
		
		String sql="select * from bd_bankaccbas where pk_bankaccbas in("+str+")";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<BankAccbasVO> listvo=(List<BankAccbasVO>)iQ.executeQuery(sql, new BeanListProcessor(BankAccbasVO.class));
		for(BankAccbasVO vo : listvo){
			vo.setEnablestate(3);
		}
		
		for(int i=0;i<rows.length;i++){
			model.setValueAt("已停用", rows[i], "enablestate");
		}
		
		HYPubBO_Client.updateAry(listvo.toArray(new BankAccbasVO[0]));
	}

	//================启用=====================
	@Override
	protected void onBoImport() throws Exception {
		
		BillCardPanel panel=this.getBillCardPanelWrapper().getBillCardPanel();
		BillModel model=panel.getBillModel();
		int[] rows=panel.getBillTable().getSelectedRows();
		if(rows==null||rows.length<=0){
			MessageDialog.showHintDlg(getBillUI(), "提示", "请选择数据！");
			return ;
		}
		if(rows.length>500){
			MessageDialog.showHintDlg(getBillUI(), "提示", "最多同时启用500条数据，请分批启用！");
			return ;
		}
		
		String str="";
		for(int i=0;i<rows.length;i++){
			Object obj=model.getValueAt(rows[i], "pk_bankaccbas");
			String ss=obj==null?"":obj.toString();
			str+="'"+ss+"',";
		}
		str=str.substring(0, str.lastIndexOf(","));
		
		String sql="select * from bd_bankaccbas where pk_bankaccbas in("+str+")";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<BankAccbasVO> listvo=(List<BankAccbasVO>)iQ.executeQuery(sql, new BeanListProcessor(BankAccbasVO.class));
		for(BankAccbasVO vo : listvo){
			vo.setEnablestate(2);
		}
		
		HYPubBO_Client.updateAry(listvo.toArray(new BankAccbasVO[0]));
		
		for(int i=0;i<rows.length;i++){
			model.setValueAt("已启用", rows[i], "enablestate");
		}
	}

	@Override
	protected void onBoQuery()throws Exception{
		
		StringBuffer strWhere = new StringBuffer();

		if (askForQueryCondition(strWhere) == false)
			return;// 用户放弃了查询
		
		String sql="select * from v_supplier_enable where 1=1 and "+strWhere+" order by code ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String, Object>> maplist=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
		
		BillModel model=getBillCardPanelWrapper().getBillCardPanel().getBillModel();
		model.clearBodyData();
		for(int i=0;i<maplist.size();i++){
			Map<String, Object> map=maplist.get(i);
			model.addLine();
			for(String s: map.keySet()){
				model.setValueAt(map.get(s), i, s);
			}
		}
	}
	
}