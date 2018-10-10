package nc.ui.zl.ld_upcontract.ace.billref;

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
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.zl.cwf_gather.GatherBVO;
import nc.vo.zl.cwf_gather.GatherVO;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.zl.ld_upcontract.UpcontractUVO;
import nc.vo.zl.ld_upcontract.UpcontractVO;

public class ReferAction extends AbstractReferenceAction{

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
	    
	public ReferAction(){
		super();
		this.setCode("referAction");
		this.setBtnName("����");
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			
			AggMdcontractVO[] vos= (AggMdcontractVO[])PfUtilClient.getRetOldVos();
			 if(vos==null ||vos.length!=1 ){
				 MessageDialog.showErrorDlg(null, "����", "��ѡ��һ�����ݣ�");
				 return ;
			 }
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			
			 UpcontractVO pvo = null;
			 AggUpcontractVO aggvo = null;
			 AggUpcontractVO[] aggvos = new AggUpcontractVO[vos.length];
			 
			 
			 for(int i=0;i<vos.length;i++){
				 int size = 0;
				 if(vos[i].getChildrenVO()!=null){
					 size = vos[i].getChildrenVO().length;
				 }
				 aggvo = new AggUpcontractVO();
				 
				 pvo =  new UpcontractVO();
				 pvo.setCreator(AppContext.getInstance().getPkUser());
				 pvo.setDbilldate(new UFDate());
				 pvo.setPk_group(vos[i].getParentVO().getPk_group());
				 pvo.setPk_org(vos[i].getParentVO().getPk_org());
				 pvo.setPk_org_v(vos[i].getParentVO().getPk_org_v());
				 pvo.setPk_contracttype(vos[i].getParentVO().getPk_contracttype());
				 pvo.setPk_project(vos[i].getParentVO().getPk_project());
				 pvo.setPk_customer(vos[i].getParentVO().getPk_customer());
				 //pvo.setCode(vos[i].getParentVO().getCode());
				 pvo.setVbillcode(vos[i].getParentVO().getCode());
				 pvo.setName(vos[i].getParentVO().getName());
				 pvo.setLocation(vos[i].getParentVO().getLocation());
				 pvo.setRentdate(vos[i].getParentVO().getRentdate());
				 pvo.setStartdate(vos[i].getParentVO().getStartdate());
				 pvo.setEnddate(vos[i].getParentVO().getEnddate());
				 pvo.setTaxrate(vos[i].getParentVO().getTaxrate());
				 pvo.setAllrent(vos[i].getParentVO().getAllrent());
				 pvo.setRemark(vos[i].getParentVO().getRemark());
				 String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H450'";
				 Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
				 pvo.setPk_billtype(getStgObj(pk_billtype));
				 pvo.setVbilltypecode("H450");
				 pvo.setVbillstatus(-1);
				 pvo.setVsrcid(vos[i].getParentVO().getPk_mdcontract());
				 pvo.setVsrctype(vos[i].getParentVO().getPk_billtype());
				 pvo.setVsrcbillcode(vos[i].getParentVO().getVbillcode());
				String maxversion="select max(version) from zl_upcontract where nvl(dr,0)=0 and vsrcid='"+vos[i].getParentVO().getPk_mdcontract()+"'";
				Object version=iQ.executeQuery(maxversion, new ColumnProcessor());
				Integer ver= Integer.parseInt(version==null?"0":version.toString());
				if(ver.equals(0)){
					pvo.setVersion(1);
				}else{
					pvo.setVersion(ver+1);
				}
				 
				 aggvo.setParentVO(pvo);
				 MdcontractCVO[] mdvo = (MdcontractCVO[]) vos[i].getChildrenVO();
				 //UpcontractUVO[] cvo = null;
				 if(size>0){
					 UpcontractUVO[] cvo = new UpcontractUVO[size]; 
					 for(int j=0;j<size;j++){
						 System.out.println(mdvo[j].getRowno());
						 cvo[j] = new UpcontractUVO();
						 cvo[j].setRowno(mdvo[j].getRowno());
						 cvo[j].setPk_costproject(mdvo[j].getPk_costproject());
						 cvo[j].setPk_customer(mdvo[j].getPk_customer());
						 cvo[j].setMstartdate(mdvo[j].getMstartdate());
						 cvo[j].setMenddate(mdvo[j].getMenddate());
						 cvo[j].setMoneydate(mdvo[j].getMoneydate());
						 cvo[j].setAccountmonth(mdvo[j].getAccountmonth());
						 cvo[j].setReceivemoney(mdvo[j].getReceivemoney());
						 cvo[j].setFreetaxmoney(mdvo[j].getFreetaxmoney());
						 cvo[j].setTaxmoney(mdvo[j].getTaxmoney());
						 cvo[j].setGetmoney(mdvo[j].getGetmoney());
						 cvo[j].setRemark(mdvo[j].getRemark());
					 }
					 System.out.println(cvo);
					 aggvo.setChildrenVO(cvo);
				 }
				
				 aggvos[i]=aggvo;
				
			 }
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
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel().getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			context.setCurrBilltype("H450");
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
