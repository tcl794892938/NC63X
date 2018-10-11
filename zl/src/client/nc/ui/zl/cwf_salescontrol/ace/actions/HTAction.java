package nc.ui.zl.cwf_salescontrol.ace.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ITcl_contractMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.NCAction;
import nc.ui.zl.cwf_salescontrol.ace.actions.QueryAction.MouseLisnter111;
import nc.vo.zl.tcl_contract.AggContractVO;

public class HTAction extends NCAction{

	public BillForm billForm;

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}
	
	public HTAction(){
		super();
		this.setCode("htaction");
		this.setBtnName("合同信息");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent e) throws Exception {
		this.billForm.getBillCardPanel().dataNotNullValidate();
		BillModel model=billForm.getBillCardPanel().getBillModel();
		MouseListener[] lis=billForm.getBillCardPanel().getBillTable().getMouseListeners();
		int row=-1;
		int col=-1;
		for(MouseListener l:lis){
			if(l instanceof MouseLisnter111){
				row=((MouseLisnter111) l).getRow();
				col=((MouseLisnter111) l).getCol();
			}
		}
		//int row=billForm.getBillCardPanel().getBillTable().getSelectedRow();
		//int col=billForm.getBillCardPanel().getBillTable().getSelectedColumn();
		Object obj1=this.billForm.getBillCardPanel().getHeadItem("pk_org").getValueObject();
		Object obj2=this.billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
		Object obj3=this.billForm.getBillCardPanel().getHeadItem("pk_building").getValueObject();
		String wheresql=" buildname='"+obj3.toString()+"' and pk_org='"+obj1.toString()+"' and projectname='"+obj2.toString()+"' and nvl(dr,0)=0";
		
		if(row==-1|| col==0||col==-1){
			MessageDialog.showHintDlg(billForm, "提示", "请先选取房间号！");
			return;
		}
		if(model.getValueAt(row, col)==null){
			MessageDialog.showHintDlg(billForm, "提示", "该房间不存在，请重新选取！");
			return;
		}
		if(model.getBackground(row, col).equals(new Color(255, 238, 188)) || model.getBackground(row, col).equals(Color.LIGHT_GRAY)){
			MessageDialog.showHintDlg(billForm, "提示", "该房间尚无合同信息，请检查！");
			return;
		}
		
		String fh=model.getValueAt(row, col).toString().trim().split("单元")[1];
		String dy=model.getValueAt(row, col).toString().trim().split("单元")[0];
		Integer lc=Integer.parseInt(model.getValueAt(row, "lc").toString());
		String sql="select pk_housesource from zl_housesource where  floorn='"+lc+"' and roomnumber='"+fh+"' and unit='"+dy+"' and +"+wheresql+"";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object pkfc= iQ.executeQuery(sql, new ColumnProcessor());
		String sql2="select a.pk_contract from zl_contract a left join zl_contract_house b on a.pk_contract=b.pk_contract " +
				"where nvl(a.dr,0)=0 and nvl(b.dr,0)=0 and b.pk_house='"+pkfc+"' and a.version=-1";
		List<Object> listobj=(List<Object>) iQ.executeQuery(sql2, new ArrayListProcessor());
		if(listobj==null||listobj.size()==0){
			MessageDialog.showHintDlg(billForm, "提示", "该房间尚无合同信息，请检查！");
			return;
		}
		List<String> obja=new ArrayList<String>();
		for(int i=0;i<listobj.size();i++){
			Object[] obj=(Object[]) listobj.get(i);
			obja.add(obj[0].toString());
		}
		ITcl_contractMaintain qryht=NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
		List<AggContractVO> aggvos=(List<AggContractVO>) qryht.queryHTbyPK2(obja);
		SFClientUtil.openLinkedQueryDialog("ZLH420", billForm, new LinkQueryData(aggvos.toArray(new AggContractVO[aggvos.size()])));
		
	}


	class LinkQueryData implements ILinkQueryData{
    	
		private String id;
		private Object obj;
		
		public LinkQueryData(String id){
			this.id=id;
		}	
		public LinkQueryData(Object obj){
			this.obj=obj;
		}			
		public Object getUserObject() {
			return obj;
		}
		public String getBillID() {
			return id;
		}
		@Override
		public String getBillType() {
			return null;
		}
		@Override
		public String getPkOrg() {
			return null;
		}
	}

	
}
