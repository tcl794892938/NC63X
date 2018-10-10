package nc.ui.zl.cwf_gather.ace.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UITable.SortItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.ic.m46.entity.FinProdInBodyVO;
import nc.vo.ic.m46.entity.FinProdInHeadVO;
import nc.vo.ic.m46.entity.FinProdInVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.qc.c003.entity.ReportVO;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.zl.cwf_gather.GatherBVO;
import nc.vo.zl.cwf_gather.GatherVO;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;


public class RefAction extends AbstractReferenceAction{

	public RefAction() {
		super();
		this.setCode("refaction");
		this.setBtnName("����Ӧ�յ�");
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

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			
			AggRecbillVO[] vos= (AggRecbillVO[])PfUtilClient.getRetOldVos();
			 if(vos==null ||vos.length<=0 ){
				 MessageDialog.showErrorDlg(null, "����", "������ѡ��һ�����ݣ�");
				 return ;
			 }
			 
			 if(vos.length>1){
				 for(int a=1;a<vos.length;a++){
					 if(vos[0].getParentVO().getNrecmoney().compareTo(new UFDouble(0))
							 !=vos[a].getParentVO().getNrecmoney().compareTo(new UFDouble(0))){
						 MessageDialog.showErrorDlg(null, "����", "Ӧ�ս�����ͬʱ������������");
						 return ;
					 }
				 }
			 }
			
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 AggGatherVO aggvo=new AggGatherVO();
			 GatherVO hvo=new GatherVO();
			 List<GatherBVO> list=new ArrayList<GatherBVO>();
			 //GatherBVO [] bvo=new GatherBVO[vos.length];
			 
			 hvo.setCreator(AppContext.getInstance().getPkUser());
			 hvo.setDbilldate(new UFDate());
			 hvo.setPk_group(vos[0].getParentVO().getPk_group());
			 hvo.setPk_org(vos[0].getParentVO().getPk_org());
			 hvo.setPk_org_v(vos[0].getParentVO().getPk_org_v());
			 hvo.setPk_project(vos[0].getParentVO().getPk_project());
			 hvo.setPk_customer(vos[0].getParentVO().getPk_customer());
			 //hvo.setNtaxrate(vos[0].getParentVO().getNtaxrate());
			 hvo.setIsadd(new UFBoolean(false));
			 
			 
			 String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H630'";
			 Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
			 hvo.setPk_billtype(getStgObj(pk_billtype));
			 
			 hvo.setVbilltypecode("H630");
			 hvo.setVbillstatus(-1);
			 UFDouble ys=new UFDouble(0);//Ӧ��
			 UFDouble ys2=new UFDouble(0);//����
			 for(int i=0;i<vos.length;i++){
				 GatherBVO bvo=new GatherBVO();
				 RecbillVO vo=vos[i].getParentVO();
				 bvo.setNrecmoney(vo.getNrecmoney()==null?new UFDouble(0):vo.getNrecmoney());
				 bvo.setNysmny(vo.getNrealmoney()==null?new UFDouble(0):vo.getNrealmoney());
//				 if("0001ZZ1000000001VIRL".equals(vo.getPk_billtype())){
//					 bvo.setNkcdmny(vo.getNrecmoney()==null?new UFDouble(0):vo.getNrecmoney());
//				 }
				 bvo.setNtaxmny(vo.getNtaxmoney()==null?new UFDouble(0):vo.getNtaxmoney());
				 bvo.setNnotaxmoney(vo.getNnotaxmoney()==null?new UFDouble(0):vo.getNnotaxmoney());
				 bvo.setRowno(((i+1)*10)+"");
				 bvo.setPk_building(vo.getPk_building());
				 bvo.setPk_house(vo.getPk_house());
				 bvo.setPk_costproject(vo.getPk_costproject());
				 bvo.setPk_carno(vo.getPk_carno());
				 bvo.setVsrcid(vo.getPk_recbill());
				 bvo.setVsrctype(vo.getPk_billtype());
				 bvo.setFirstpk(vo.getVsrcid());
				 bvo.setFirstbilltype(vo.getVsrctype());
				 bvo.setCaccountperiod(vo.getCaccountperiod());
				 bvo.setVdef1(vo.getVdef1());
				 bvo.setVdef2(vo.getVdef2());
				 bvo.setNtaxrate(vo.getNtaxrate()==null?new UFDouble(0):vo.getNtaxrate());
				 if(vo.getBegindate()!=null){
					 bvo.setBegindate(vo.getBegindate());
				 }
				 if(vo.getEnddate()!=null){
					 bvo.setEnddate(vo.getEnddate());
				 }
				 ys=ys.add(vo.getNrecmoney()==null?new UFDouble(0):vo.getNrecmoney());
				 ys2=ys2.add(vo.getNrealmoney()==null?new UFDouble(0):vo.getNrealmoney());
				 list.add(bvo);
			 }
			 hvo.setNrecmny(ys);
			 hvo.setNysmny(ys2);
			 AggGatherVO []aggvos=new AggGatherVO[1];
			 aggvo.setParentVO(hvo);
			 aggvo.setChildrenVO(list.toArray(new GatherBVO[0]));
			 aggvos[0]=aggvo;
			this.getTransferViewProcessor().processBillTransfer(aggvos);
			this.editor.getBillCardPanel().getBodyItem("pk_building").setEnabled(false);
			this.editor.getBillCardPanel().getBodyItem("pk_house").setEnabled(false);
			this.editor.getBillCardPanel().getBodyItem("pk_carno").setEnabled(false);
			this.editor.getBillCardPanel().getBodyItem("pk_costproject").setEnabled(false);
			this.editor.getBillCardPanel().getBodyItem("begindate").setEnabled(false);
			this.editor.getBillCardPanel().getBodyItem("enddate").setEnabled(false);
			this.editor.getBillCardPanel().getBodyItem("nskmny").setEnabled(false);
			this.editor.getBillCardPanel().getHeadItem("pk_project").setEnabled(false);
			this.editor.getBillCardPanel().getHeadItem("pk_customer").setEnabled(false);
			this.editor.getBillCardPanel().getHeadItem("nskmny").setEnabled(true);
			this.editor.getBillCardPanel().getBodyItem("ntaxrate").setEnabled(false);
			//this.editor.getBillCardPanel().getBillModel().setValueAt("20171128", 0, "vbatchcode");
			BillModel model2=this.editor.getBillCardPanel().getBillModel();
			int row=model2.getRowCount();
			if(row<=0){
				return ;
			}
			
			//int col1=model2.getBodyColByKey("begindate");
			model2.sortByColumn("begindate", true);
			for(int i=0;i<model2.getRowCount();i++){
				model2.setValueAt((i+1)*10, i, "rowno");
			}
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
			context.setCurrBilltype("H630");
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
