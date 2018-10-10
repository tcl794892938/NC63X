/**
 *
 */
package nc.ui.fip.docview.view;

import java.awt.Container;
import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.fip.docview.IDocViewListService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.IRefUI60;
import nc.ui.bd.ref.RefUIConfig;
import nc.ui.fip.docview.model.DocViewBodyTableModel;
import nc.ui.fip.pub.view.SrcOrgChooserPanel;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.uif2.FuncletDialog;
import nc.ui.uif2.IFunNodeClosingListener;
import nc.ui.uif2.UIFMenuFactory;
import nc.ui.uif2.editor.BatchBillTable;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.IAppModelDataManager;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.bd.ref.RefcolumnVO;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.docview.DocViewRefModel;
import nc.vo.fip.docview.SrcDocGroupVO;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

/**
 * @author gbh
 * 
 */
public class DocViewBodyDialog extends FuncletDialog implements IRefUI60 {
	private AbstractRefModel refModel;

	public DocViewBodyDialog(Container parent) {
		super(parent);
		setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1017clt_0", "01017clt-2-0002")/* @res "基础档案对照表" */);
		setSize(800, 600);
		if (parent != null && parent instanceof UIRefPane) {
			AbstractRefModel refModel2 = ((UIRefPane) parent).getRefModel();
			while (parent != null && !(parent instanceof BatchBillTable) && !(parent instanceof BillForm)) {
				parent = parent.getParent();
			}
			LoginContext context = null;
			if (parent != null) {
				if (parent instanceof BatchBillTable)
					context = ((BatchBillTable) parent).getModel().getContext();
				else if (parent instanceof BillForm)
					context = ((BillForm) parent).getModel().getContext();
			}
			if (context == null) {
				if (refModel2 != null && refModel2 instanceof DocViewRefModel) {
					context = ((DocViewRefModel) refModel2).getContext();
				}
			}
			setMenufactory(new UIFMenuFactory(context));
			setXmlFilePath("nc/ui/fip/docview/view/docviewbody_grp_config.xml");
			initUI();
			getBillTableModel().getContext().setNodeType(context.getNodeType());
			getBillTableModel().getContext().setFuncInfo(context.getFuncInfo());
		}
	}

	@Override
	public void setRefUIConfig(RefUIConfig refUIConfig) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractRefModel getRefModel() {
		// TODO Auto-generated method stub
		return refModel;
	}

	@Override
	public void setIncludeSubShow(boolean newIncludeSubShow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMultiCorpRef(boolean isMultiCorpRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMultiSelectedEnabled(boolean isMultiSelectedEnabled) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNotLeafSelectedEnabled(boolean newNotLeafSelectedEnabled) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRefModel(AbstractRefModel refModel) {
		this.refModel = refModel;
	}

	@Override
	public void setTreeGridNodeMultiSelected(boolean isMulti) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFilterDlgShow(boolean isFilterDlgShow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVersionButtonShow(boolean isVersionButtonShow) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getUISelectedPKs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefcolumnVO[] getRefcolumnVOs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMultiOrgSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RefUIConfig getRefUIConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onButtonOK() {
		this.closeOK();
	}

	@Override
	public void onButtonExit() {
		this.closeCancel();
	}

	public DocViewBodyTable getBillTable() {
		Object bean = getBeanByName("view");
		return (DocViewBodyTable) bean;
	}

	public SrcOrgChooserPanel getOrgChooser() {
		Object bean = getBeanByName("orgchooser");
		return (SrcOrgChooserPanel) bean;
	}

	public DocViewBodyTableModel getBillTableModel() {
		return (DocViewBodyTableModel) getBillTable().getModel();
	}

	public IAppModelDataManager getDataManager() {
		Object bean = getBeanByName("modelDataManager");
		return (IAppModelDataManager) bean;
	}

	@Override
	public void initUI() {
		super.initUI();
		getBillTable().getBillCardPanel().getBodyPanel().getTable().setSortEnabled(false);
		if (getClosingListener() == null)
			setClosingListener((IFunNodeClosingListener) getBeanByName(closingListener_BEAN_NAME));
	}

	@Override
	public int showModal() {
		if (getRefModel() != null) {
			prepareRef();
		}
		DocViewListVO listVO = getBillTableModel().getListVO();
		if (listVO == null || listVO.getSrcdocgroup() == null || listVO.getSrcdocgroup().length == 0) {
			return ID_CANCEL;
		}
		return super.showModal();
	}

	private void prepareRef() {
		if (getRefModel() instanceof DocViewRefModel) {
			DocViewRefModel refm = (DocViewRefModel) getRefModel();
			String pkValue = refm.getPkValue();
			DocViewListVO vo = new DocViewListVO();
			if (pkValue != null) {
				try {
					vo = NCLocator.getInstance().lookup(IDocViewListService.class).queryByPK(pkValue);
				} catch (BusinessException e) {
					Logger.error("", e);
				}
			} else {
				vo.setDesdocid(refm.getDesdoc());
				if (refm.getSrcdocs() != null) {
					SrcDocGroupVO[] vos = new SrcDocGroupVO[refm.getSrcdocs().length];
					for (int i = 0; i < vos.length; i++) {
						vos[i] = new SrcDocGroupVO();
						vos[i].setPk_srcdocid(refm.getSrcdocs()[i]);
					}
					vo.setSrcdocgroup(vos);
				}
				vo.setPk_org(refm.getPk_org());
				vo.setPk_group(refm.getPk_group());
				vo.setViewcode("view");
				vo.setViewname("view");
			}
			getBillTableModel().setListVO(vo);
			getBillTableModel().setPk_srcorg(vo.getPk_org());
		}
	}

	@Override
	public void closeOK() {
		if (getRefModel() != null) {
			DocViewListVO listVO = getBillTableModel().getListVO();
			if (listVO.getClassviews() != null) {
				IDocViewListService lookup = NCLocator.getInstance().lookup(IDocViewListService.class);
				BatchOperateVO vo = new BatchOperateVO();
				if (listVO.getPk_classview() != null) {
					vo.setUpdObjs(new DocViewListVO[] {
						listVO
					});
				} else {
					vo.setAddObjs(new DocViewListVO[] {
						listVO
					});
				}
				try {
					BatchOperateVO batchSave = lookup.batchSave(vo);
					if (batchSave.getAddObjs() != null)
						listVO = (DocViewListVO) batchSave.getAddObjs()[0];
					else
						listVO = (DocViewListVO) batchSave.getUpdObjs()[0];
				} catch (BusinessException e) {
					Logger.error("", e);
				}
				Vector<Object> vec = new Vector<Object>();
				vec.add(listVO.getPk_classview());
				getRefModel().setSelectedData(vec);
			}
		}
		super.closeOK();
	}
}