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
		this.setBtnName("参照报事单");
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
				 MessageDialog.showErrorDlg(null, "错误", "请选择一条数据！");
				 return ;
			 }
			
			 AggZylistVO zvo=new AggZylistVO();
			 ZylistVO hvo=new ZylistVO();
			 
			 hvo.setCreator(AppContext.getInstance().getPkUser());
			 hvo.setCreationtime(AppContext.getInstance().getServerTime());
			 hvo.setDbilldate(new UFDate());
			 //传递组织、集团、项目信息
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
			 //设置默认的单据类型，单据状态
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 String sql="select pk_billtypeid from bd_billtype where pk_billtypecode='H512'";
			 Object pk_billtype=iQ.executeQuery(sql, new ColumnProcessor());
			 hvo.setPk_billtype(getStgObj(pk_billtype));
			 
			 hvo.setBilltypecode("H512");
			 hvo.setListstate(-1);
			 //设置来源单据类型，来源单据主键
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
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
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
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
//		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
//		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
//		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
		return context;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
