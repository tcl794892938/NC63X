package nc.ui.zl.ld_kpregister.ace.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.ic.m46.entity.FinProdInBodyVO;
import nc.vo.ic.m46.entity.FinProdInHeadVO;
import nc.vo.ic.m46.entity.FinProdInVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.qc.c003.entity.ReportVO;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.zl.cwf_gather.GatherBVO;
import nc.vo.zl.cwf_gather.GatherVO;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.zl.ld_kpregister.KpregisterDVO;
import nc.vo.zl.ld_kpregister.KpregisterVO;


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
			 if(vos==null ||vos.length<1 ){
				 MessageDialog.showErrorDlg(null, "����", "������ѡ��һ�����ݣ�");
				 return ;
			 }
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			//�ж��Ա���δ������Ʊȷ��
			 for(int i=0;i<vos.length;i++){
				 String sql_judge = "select count(*) from zl_kpregister_d kd where nvl(kd.dr,0)=0 and kd.vsrcid='"+vos[i].getParentVO().getPk_recbill()+"' and " 
						 			+"exists(select k.*  from zl_kpregister k where nvl(k.dr,0)=0 and k.pk_kpregister=kd.pk_kpregister and k.vbillstatus=-1)";
				 Integer obj1 = (Integer)iQ.executeQuery(sql_judge, new ColumnProcessor()); 
				 if(obj1>0){
					 MessageDialog.showErrorDlg(null, "����", "ѡ��������д����ѱ�������δ�����������ݣ����飡");
					 return;
				 }
			 }
			 
			 
			 AggKpregisterVO aggvo = null;
			 AggKpregisterVO[] aggvos = new AggKpregisterVO[1];
			 KpregisterVO hvo = new KpregisterVO();
			 //List<KpregisterDVO> list = new ArrayList<KpregisterDVO>();
			 aggvo = new AggKpregisterVO();
			 //hvo = new KpregisterVO();
			 hvo.setCreator(AppContext.getInstance().getPkUser());
			 hvo.setCreationtime(new UFDateTime());
			 hvo.setDbilldate(new UFDate());
			 hvo.setPk_group(vos[0].getParentVO().getPk_group());
			 hvo.setPk_org(vos[0].getParentVO().getPk_org());
			 hvo.setPk_org_v(vos[0].getParentVO().getPk_org_v());
			 hvo.setPk_project(vos[0].getParentVO().getPk_project());
			 //hvo.setPk_customer(vos[i].getParentVO().getPk_customer());
			 hvo.setKpdate(new UFDate());
			 //hvo.setKpmoney(vos[i].getParentVO().getNrecmoney());
			 hvo.setKpman(AppContext.getInstance().getPkUser());
			 String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H650'";
			 Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
			 hvo.setPk_billtype(getStgObj(pk_billtype)); 
			 hvo.setVbilltypecode("H650");
			 hvo.setVbillstatus(-1);
			 /*hvo.setVsrcid(vos[i].getParentVO().getPk_recbill());
			 hvo.setVsrctype(vos[i].getParentVO().getPk_billtype());*/
			 UFDouble ncount = new UFDouble(0);
			 
			 KpregisterDVO[] dvo = new KpregisterDVO[vos.length];
			 for(int i=0;i<vos.length;i++){
				
				 //aggvo.setParentVO(hvo);
				 dvo[i]=new KpregisterDVO();
				 dvo[i].setPk_building(vos[i].getParentVO().getPk_building());
				 dvo[i].setPk_house(vos[i].getParentVO().getPk_house());
				 dvo[i].setPk_costproject(vos[i].getParentVO().getPk_costproject());
				 dvo[i].setBegindate(vos[i].getParentVO().getBegindate());
				 dvo[i].setEnddate(vos[i].getParentVO().getEnddate());
				 dvo[i].setCaccountperiod(vos[i].getParentVO().getCaccountperiod());
				 dvo[i].setVsrcid(vos[i].getParentVO().getPk_recbill());
				 dvo[i].setVsrctype(vos[i].getParentVO().getPk_billtype());
				 dvo[i].setFirstpk(vos[i].getParentVO().getVsrcid());
				 dvo[i].setFirstbilltype(vos[i].getParentVO().getVsrctype());
				 dvo[i].setPk_customer(vos[i].getParentVO().getPk_customer());
				 dvo[i].setNrecmoney(vos[i].getParentVO().getNrecmoney());
				 UFDouble nrece = new UFDouble(getStgObj(vos[i].getParentVO().getNrecmoney()));
				 UFDouble nkpmy= new UFDouble(getStgObj(vos[i].getParentVO().getInvoicemoney()));
				 UFDouble km = new UFDouble(0);
				 if(nrece.toDouble()>0){
					 dvo[i].setKpmoney(nrece.abs().sub(nkpmy.abs()));
					 ncount = ncount.add(nrece.abs().sub(nkpmy.abs()));
					 km = km.add(nrece.abs().sub(nkpmy.abs()));
				 }else{
					 UFDouble nct = new UFDouble(0);
					 dvo[i].setKpmoney(nct.sub(nrece.abs().sub(nkpmy.abs())));
					 ncount = ncount.add(nct.sub(nrece.abs().sub(nkpmy.abs())));
					 km = km.add(nrece.abs().sub(nkpmy.abs()));
				 }
				 //˰��
				 dvo[i].setNtaxrate(vos[i].getParentVO().getNtaxrate());
				 //��ȡ��˰
				 String sql_conn = "select sum(d.nnotaxmny) from zl_kpregister_d d where nvl(d.dr,0)=0 and d.vsrcid='"+vos[i].getParentVO().getPk_recbill()+"' and exists(select 1 from zl_kpregister k where nvl(k.dr,0)=0 and k.pk_kpregister=d.pk_kpregister and k.vbillstatus=1)";
					Object obj2 = null;
					try {
						obj2=iQ.executeQuery(sql_conn, new ColumnProcessor());
					} catch (BusinessException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				UFDouble notaxmny = (vos[i].getParentVO().getNnotaxmoney().sub(getuf(obj2)));
				dvo[i].setNnotaxmny(notaxmny);
				 //��ȡ˰��
				UFDouble ntaxmny = km.sub(notaxmny);
				 dvo[i].setNtaxmny(ntaxmny);
				 
				 
			 }
			 hvo.setKpmoney(ncount);
			 aggvo.setChildrenVO(dvo);
			 aggvo.setParentVO(hvo);
			 aggvos[0] = aggvo;
			 this.getTransferViewProcessor().processBillTransfer(aggvos); 
			
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
			context.setCurrBilltype("H650");
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
	public UFDouble getuf(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
}
