package nc.ui.zl.lyw_billconfirmed.ace.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedBVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class RefAction extends AbstractReferenceAction {
	public RefAction() {
		super();
		this.setCode("refaction");
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
			AggConfirmationVO[] aggcnvos= (AggConfirmationVO[])PfUtilClient.getRetOldVos();
			
			 if(aggcnvos==null||aggcnvos.length<=0){
				 MessageDialog.showErrorDlg(null, "����", "������ѡ��һ�����ݣ�");
				 return;
			 }
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 //�ж��Ա���δ��������ȷ�ϵ�
			 for(int i=0;i<aggcnvos.length;i++){
				 String sql_judge = "select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.vsrcid='"+aggcnvos[i].getParentVO().getPk_confirmation()+"' and " 
						 			+"exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=-1)";
				 Integer obj1 = (Integer)iQ.executeQuery(sql_judge, new ColumnProcessor()); 
				 if(obj1>0){
					 MessageDialog.showErrorDlg(null, "����", "ѡ��������д����ѱ�������δ�����������ݣ����飡");
					 return;
				 }
			 }
			 
			 
			 
			
			 UFDouble nreceivtotal = new UFDouble(0);
			 UFDouble nconfirmedtotal = new UFDouble(0);
			 BillconfirmedVO cdvo = new BillconfirmedVO();
			 AggBillconfirmedVO[] aggcdvos = new AggBillconfirmedVO[1];
			 AggBillconfirmedVO aggcdvo = new AggBillconfirmedVO();
			 ArrayList<BillconfirmedBVO> cdbvos = new ArrayList<BillconfirmedBVO>();
			 for(int i=0;i<aggcnvos.length;i++){
				 ConfirmationVO cnvo = aggcnvos[i].getParentVO();
				 BillconfirmedBVO cdbvo = new BillconfirmedBVO();
				 cdbvo.setAmountreceivable(cnvo.getAmountreceivable());
				 if(cnvo.getAmountconfirmed()==null){
					 cnvo.setAmountconfirmed(new UFDouble(0));
				 }
				 cdbvo.setAmountconfirmed(cnvo.getAmountconfirmed());
				//��ȡȷ���ܽ��
				String sql_conm = "select sum(b.amountconfirming) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+cnvo.getPk_confirmation()+"' and exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=1)";
				Object obj1 = iQ.executeQuery(sql_conm, new ColumnProcessor());
				
				 cdbvo.setAmountconfirming(cnvo.getAmountreceivable().sub(getuf(obj1)));
				//��˰���
				 String sql_conn = "select sum(b.nnotaxmny) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+cnvo.getPk_confirmation()+"' and exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=1)";
					Object obj2 = null;
					try {
						obj2=iQ.executeQuery(sql_conn, new ColumnProcessor());
					} catch (BusinessException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				UFDouble notaxmny = (cnvo.getAmountreceivable().div(new UFDouble(1).add(getuf(cnvo.getNtaxrate()).div(new UFDouble(100))))).sub(getuf(obj2));
				cdbvo.setNnotaxmny(notaxmny);
				//˰��
				String sql_tax = "select sum(b.ntaxmny) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+cnvo.getPk_confirmation()+"' and exists(select d.* from zl_billconfirmed d where nvl(d.dr,0)=0 and d.pk_billconfirmed= b.pk_billconfirmed and d.vbillstatus=1)";
				Object obj3 = null;
				try {
					obj3=iQ.executeQuery(sql_tax, new ColumnProcessor());
				} catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				UFDouble ntaxmny = (cnvo.getAmountreceivable().sub(cnvo.getAmountreceivable().div(new UFDouble(1).add(getuf(cnvo.getNtaxrate()).div(new UFDouble(100)))))).sub(getuf(obj3));
				cdbvo.setNtaxmny(ntaxmny);
				 
				 nconfirmedtotal=nconfirmedtotal.add(cdbvo.getAmountconfirming());
				 nreceivtotal=nreceivtotal.add(cnvo.getAmountreceivable());
				 cdbvo.setBuildno(cnvo.getBuildno());
				 cdbvo.setCaccountperiod(cnvo.getCaccountperiod());
				 cdbvo.setChargingproject(cnvo.getChargingproject());
				 cdbvo.setHouseproperty(cnvo.getHouseproperty());
				 cdbvo.setPk_customer(cnvo.getPk_customer());	
				 cdbvo.setDfeeenddate(cnvo.getDfeeenddate());
				 cdbvo.setDfeestartdate(cnvo.getDfeestartdate());
				 cdbvo.setVsrcid(cnvo.getPk_confirmation());
				 cdbvo.setDreccollectdate(cnvo.getDreccollectdate());
				 cdbvo.setVsrctype(cnvo.getPk_billtype());
				 cdbvo.setVdef2(cnvo.getVdef2());//���ڴ洢��Դ��¼����
				 cdbvo.setVdef1(cnvo.getVdef1());//���ڴ洢Դ��¼��pk
				 cdbvo.setNtaxrate(getuf(cnvo.getNtaxrate()));
				 cdbvo.setNrentarea(cnvo.getNrentarea());
				 cdbvos.add(cdbvo);
			 }
			 //������һЩֵ
			 ConfirmationVO cnvotemp=aggcnvos[0].getParentVO();
			 cdvo.setPk_group(cnvotemp.getPk_group());
			 cdvo.setPk_org(cnvotemp.getPk_org());
			 cdvo.setPk_org_v(cnvotemp.getPk_org_v());
			 //String sql = "select * from bd_billtype where pk_billtypecode='H641'";
			 cdvo.setPk_billtype("0001ZZ1000000002002O");//����ȷ�ϵ�pk
			 //cdvo.setVsrctype("0001ZZ10000000024H9R");//������ȷ�ϵ�pk
			 cdvo.setConfirmer(getModel().getContext().getPk_loginUser());
			 cdvo.setVbillstatus(-1);
			 cdvo.setDbilldate(new UFDate());
			 cdvo.setNreceivtotal(nreceivtotal);
			 cdvo.setNconfirmedtotal(nconfirmedtotal);
			 cdvo.setPk_project(cnvotemp.getPk_project());
			 cdvo.setPk_customer(aggcnvos[0].getParentVO().getPk_customer());
			 aggcdvo.setParent(cdvo);
			 BillconfirmedBVO[] cdbvosarray = new BillconfirmedBVO[cdbvos.size()];
			 aggcdvo.setChildrenVO(cdbvos.toArray(cdbvosarray));
			 aggcdvos[0] = aggcdvo;
			 //ϵͳ�Զ�����Ŀ�����
			 this.getTransferViewProcessor().processBillTransfer(aggcdvos);
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
			context.setCurrBilltype("H641");
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
		// ���εĽ������ͼ���
		context.setTransTypes(this.getTranstypes());
		// ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
		// 2�������������ã���-1�������ݽ������ͷ��飩
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
