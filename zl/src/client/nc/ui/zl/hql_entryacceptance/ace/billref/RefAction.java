package nc.ui.zl.hql_entryacceptance.ace.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.zl.hql_entryacceptance.EntryacceptanceVO;
import nc.vo.zl.hql_entryacceptance.Entryacceptance_khfcVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;

public class RefAction extends AbstractReferenceAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2971415335554067685L;


	public RefAction() {
		super();
		this.setCode("refaction");
		this.setBtnName("���պ�ͬ����");
	}
	
	private BillForm editor;

	private AbstractAppModel model;
	

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			
			AggContractVO[] vos= (AggContractVO[])PfUtilClient.getRetOldVos();
			 if(vos==null ||vos.length<=0 ){
				 MessageDialog.showErrorDlg(null, "����", "������ѡ��һ���������ݣ�");
				 return ;
			 }
			 if(vos.length > 1){
				 MessageDialog.showErrorDlg(null, "����", "ֻ��ѡ��һ����ͷ���ݣ�");
				 return ;
			 }
			
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 String sql_cust = "select pk_customer from zl_contract_cust where nvl(dr,0)=0 and pk_contract = '" +
			                   vos[0].getParentVO().getPk_contract() + "'";
			 Object pk_customer = iQ.executeQuery(sql_cust, new ColumnProcessor());
			 
			 AggEntryacceptanceVO aggvo=new AggEntryacceptanceVO();
			 EntryacceptanceVO hvo=new EntryacceptanceVO();
			 
			 hvo.setContractid(vos[0].getParentVO().getHtcode());
			 hvo.setPk_contract(vos[0].getParentVO().getHtname());
			 hvo.setPk_org(vos[0].getParentVO().getPk_org());
			 hvo.setPk_org_v(vos[0].getParentVO().getPk_org_v());
			 hvo.setPk_group(vos[0].getParentVO().getPk_group());
			 hvo.setPk_project(vos[0].getParentVO().getPk_project());
			 hvo.setStartday(vos[0].getParentVO().getDstartdate());
			 hvo.setEndday(vos[0].getParentVO().getDenddate());
			 hvo.setBilldate(vos[0].getParentVO().getDbilldate());
			 hvo.setVbillstatus(-1);
			 hvo.setDbilldate(new UFDate());
			 
			 hvo.setPk_billtype("0001ZZ1000000001S8SK");
			 hvo.setVbilltypecode("H462");
			 hvo.setVsrcid(vos[0].getParentVO().getPk_contract());
			 hvo.setVsrctype(vos[0].getParentVO().getVbilltypecode());
			 hvo.setCreator(AppContext.getInstance().getPkUser());
			 hvo.setCreationtime(new UFDateTime());
			 
			 //��ȡ��ͬ״̬vdef1
			 //hvo.setVdef1(vos[0].getParentVO().getHtstatus()+"");
			 
			 if(vos[0].getChildHouseVO() != null){
				 String sql_fcxx = "select pk_housesource,unit,roomnumber from zl_housesource where nvl(dr,0)=0 and " +
		 		           "pk_housesource in (";
				 for(int i = 0;i < vos[0].getChildHouseVO().length;i++){
					 sql_fcxx += "'"+vos[0].getChildHouseVO()[i].getPk_house()+"'";
					 if(i+1 != vos[0].getChildHouseVO().length){
						 sql_fcxx += ",";
					 }
				 }
				 sql_fcxx += ")";
				 List<Map<String, Object>> listmap = (List<Map<String, Object>>) iQ.executeQuery(sql_fcxx, new MapListProcessor());
				 ContractHouseVO[] cbvos = vos[0].getChildHouseVO();
				 List<Entryacceptance_khfcVO> list=new ArrayList<Entryacceptance_khfcVO>();
				 for(int j = 0;j < cbvos.length;j++){
					 Entryacceptance_khfcVO bvo = new Entryacceptance_khfcVO();
					 bvo.setPk_customer(pk_customer == null ?"":pk_customer.toString());
					 bvo.setEntrydate(vos[0].getParentVO().getDstartdate());
					 bvo.setPk_buildingfile(cbvos[j].getPk_building());
					 bvo.setPk_housesource(cbvos[j].getPk_house());
					 for(int i = 0;i < listmap.size();i++){
						 if(cbvos[j].getPk_house().equals(listmap.get(i).get("pk_housesource"))){
							 bvo.setUnit(listmap.get(i).get("unit").toString());
							 bvo.setRoomnumber(listmap.get(i).get("roomnumber").toString());
						 }
					 }
					 //bvo.setEntrydate(new UFDate());
					 list.add(bvo);
				 }
				 Entryacceptance_khfcVO [] bvos=new Entryacceptance_khfcVO[list.size()];
				 aggvo.setParentVO(hvo);
				 aggvo.setChildrenVO(list.toArray(bvos));
			 }
			 
			 
			 AggEntryacceptanceVO[] aggvos = new AggEntryacceptanceVO[1];
			 aggvos[0] = aggvo;
			 this.getTransferViewProcessor().processBillTransfer(aggvos);
			//this.editor.getBillCardPanel().getBillModel().setValueAt("20171128", 0, "vbatchcode");
			 			
		}
	}
	
	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			context.setCurrBilltype("H462");
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
//		// ���εĽ������ͼ���
		context.setTransTypes(this.getTranstypes());
//		// ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
//		// 2�������������ã���-1�������ݽ������ͷ��飩
		context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
		return context;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
