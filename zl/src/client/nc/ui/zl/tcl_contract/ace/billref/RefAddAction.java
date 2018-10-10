package nc.ui.zl.tcl_contract.ace.billref;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.itf.zl.ITcl_contractMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.abs.panel.AbstractReferenceAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractBzjVO;
import nc.vo.zl.tcl_contract.ContractCustVO;
import nc.vo.zl.tcl_contract.ContractCwcfVO;
import nc.vo.zl.tcl_contract.ContractFkmxVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractMzqVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;
import nc.vo.zl.tcl_contract.ContractZjmxVO;
import nc.vo.zl.tcl_contract.ContractZqfyVO;
import nc.vo.zl.tcl_contract.ContractZqfycfVO;
import nc.vo.zl.tcl_contract.ContractZqmxVO;
import nc.vo.zl.tcl_contract.ContractZzqVO;


public class RefAddAction extends AbstractReferenceAction{

	private static final long serialVersionUID = -5590284625090273998L;

	public RefAddAction() {
		super();
		this.setCode("refaddaction");
		this.setBtnName("参照合同");
		putValue(Action.SHORT_DESCRIPTION, "参照合同管理");
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
			
			AggContractVO[] vos= (AggContractVO[])PfUtilClient.getRetOldVos();
			 if(vos==null ||vos.length<=0 ){
				 MessageDialog.showErrorDlg(null, "错误", "请选择一条数据！");
				 return ;
			 }
			
			 String pk=vos[0].getParentVO().getPk_contract();
			 ITcl_contractMaintain itm=NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
			 AggContractVO newvo=itm.queryHTbyPK(pk);
			//查询最大的版本号
			 String sql="select max(version) from zl_contract where vsrcid='"+pk+"' and vbilltypecode='H430' and nvl(dr,0)=0 ";
			 IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			 Object obj=iQ.executeQuery(sql, new ColumnProcessor());
			 int it=0;
			 if(obj==null){
				 it=1;
			 }else{
				 it=Integer.valueOf(obj.toString())+1;
			 }
			 newvo.getParentVO().setVersion(it);
			 //构造数据
			 String vbillno= newvo.getParentVO().getVbillno();
			 newvo.getParentVO().setVbillno(null);
			 newvo.getParentVO().setPk_contract(null);
			 newvo.getParentVO().setDbilldate(new UFDate());
			 newvo.getParentVO().setVbillstatus(-1);
			 newvo.getParentVO().setPk_billtype("0001ZZ1000000001SON1");
			 newvo.getParentVO().setVbilltypecode("H430");
			 newvo.getParentVO().setVsrcid(pk);
			 newvo.getParentVO().setVsrctype("H420");
			 newvo.getParentVO().setVsrcno(vbillno);
			 
			 newvo.getParentVO().setCreator(model.getContext().getPk_loginUser());
			 newvo.getParentVO().setCreationtime(new UFDateTime());
			 newvo.getParentVO().setModifier(null);
			 newvo.getParentVO().setModifiedtime(null);
			 newvo.getParentVO().setApprover(null);
			 newvo.getParentVO().setDapprovetime(null);
			 newvo.getParentVO().setApprovenote(null);
			 
			for(ContractCustVO vo : newvo.getChildCustVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractHouseVO vo : newvo.getChildHouseVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractMzqVO vo : newvo.getChildMzqVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractZzqVO vo : newvo.getChildZzqVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractBzjVO vo : newvo.getChildBzjVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractYwcfVO vo : newvo.getChildYwcfVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractCwcfVO vo : newvo.getChildCwcfVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractZqfyVO vo : newvo.getChildZqfyVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractZqfycfVO vo : newvo.getChildZqfycfVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractFkmxVO vo : newvo.getChildFkmxVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			for(ContractZjmxVO vo : newvo.getChildZjmxVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			
			for(ContractZqmxVO vo : newvo.getChildZqmxVO()){
				vo.setVdef3(vo.getPrimaryKey());
				vo.setPrimaryKey(null);
				vo.setPk_contract(null);
			}
			
			this.getTransferViewProcessor().processBillTransfer(new AggContractVO[]{newvo});
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
			context.setCurrBilltype("H430");
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

}
