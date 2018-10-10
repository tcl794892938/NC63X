package nc.ui.zl.ly_zylist.ace.billref;

import java.awt.event.ActionEvent;

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
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.zl.ly_zylist.ZylistVO;


public class RefAction extends AbstractReferenceAction{

	public RefAction() {
		super();
		this.setCode("refaction");
		this.setBtnName("���ձ��µ�");
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
			
			AggBslistVO[] bvos= (AggBslistVO[]) PfUtilClient.getRetOldVos();
			 if(bvos==null||bvos.length!=1){
				 MessageDialog.showErrorDlg(null, "����", "��ѡ��һ�����ݣ�");
				 return ;
			 }
			
			 AggZylistVO zvo=new AggZylistVO();
			 ZylistVO hvo=new ZylistVO();
			 
			 hvo.setCreator(AppContext.getInstance().getPkUser());
			 hvo.setCreationtime(AppContext.getInstance().getServerTime());
			 hvo.setDbilldate(new UFDate());
			 //������֯�����š���Ŀ��Ϣ
			 hvo.setPk_group(bvos[0].getParentVO().getPk_group());
			 hvo.setPk_org(bvos[0].getParentVO().getPk_org());
			 hvo.setPk_org_v(bvos[0].getParentVO().getPk_org_v());
			 hvo.setPk_project(bvos[0].getParentVO().getPk_project());
			 
			 hvo.setListdate(AppContext.getInstance().getBusiDate());
			 hvo.setKhname(bvos[0].getParentVO().getKhname());
			 hvo.setFcname(bvos[0].getParentVO().getFcname());
			 hvo.setPk_building(bvos[0].getParentVO().getPk_building());
			 hvo.setLxphone(bvos[0].getParentVO().getLxphone());
			 hvo.setBxdate(bvos[0].getParentVO().getBxdate());
			 hvo.setAcceptman(bvos[0].getParentVO().getAcceptman());
			 hvo.setAcceptbm(bvos[0].getParentVO().getAcceptbm());
			 hvo.setBxcontent(bvos[0].getParentVO().getBxcontent());
			 //����Ĭ�ϵĵ������ͣ�����״̬
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H512'";
			 Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
			 hvo.setPk_billtype(getStgObj(pk_billtype));
			 
			 hvo.setBilltypecode("H512");
			 hvo.setListstate(-1);
			 //������Դ�������ͣ���Դ��������
			 hvo.setClisttype(bvos[0].getParentVO().getPk_billtype());
			 hvo.setPk_clist(bvos[0].getParentVO().getPk_bslist());
			 
			 AggZylistVO[] zvos=new AggZylistVO[1];
			 zvo.setParentVO(hvo);
			 zvos[0]=zvo;
			this.getTransferViewProcessor().processBillTransfer(zvos);
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
			context.setCurrBilltype("H512");
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
