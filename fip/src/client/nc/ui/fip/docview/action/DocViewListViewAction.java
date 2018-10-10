package nc.ui.fip.docview.action;

import java.awt.event.ActionEvent;

import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.uap.busibean.SysinitAccessor;
import nc.ui.fip.docview.view.DocViewBodyDialog;
import nc.ui.fip.pub.view.SrcOrgChooserPanel;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIDialogEvent;
import nc.ui.pub.beans.UIDialogListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIFMenuFactory;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BatchBillTable;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.docview.DocViewVO;
import nc.vo.fip.pub.FipAppEventType;
import nc.vo.fip.pub.FipBaseDataProxy;

public class DocViewListViewAction extends NCAction {
	public DocViewListViewAction() {
		super();
		setCode("view");
		setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("1017clt_0", "01017clt-2-0003")/* @res "对照表" */);
	}

	private BatchBillTableModel model = null;
	private BatchBillTable editor = null;

	private DocViewBodyDialog dialog = null;

	public static final String tempPKflag = "#temppk";

	@Override
	public void doAction(ActionEvent e) throws Exception {
		int row = getModel().getSelectedIndex();
		if (row >= 0) {
			getEditor().getBillCardPanel().stopEditing();
			Object selectedData = getModel().getRow(row);
			if (selectedData != null) {
				DocViewListVO selectedvo = (DocViewListVO) selectedData;
				getDialog().getDataManager();
				getDialog().getBillTableModel().getContext().setEntranceUI(getModel().getContext().getEntranceUI());
				DocViewListVO vo = (DocViewListVO) selectedvo.clone();
				DocViewVO[] classviews = vo.getClassviews();
				if (classviews != null) {
					for (int i = 0; i < classviews.length; i++) {
						if (classviews[i].getPk_classview_b() == null) {
							classviews[i].setPk_classview_b(tempPKflag + i);
						}
					}
				}
				getDialog().getBillTableModel().setListVO(vo);
				getDialog().getBillTableModel().setPk_srcorg(vo.getPk_org());
				if (IBDMetaDataIDConst.ACCASOA.equals(vo.getDesdocid()) && NODE_TYPE.ORG_NODE.equals(getModel().getContext().getNodeType())) {
					SrcOrgChooserPanel orgChooser = getDialog().getOrgChooser();
					if (orgChooser != null) {
						orgChooser.getModel().setUiState(UIState.DISABLE);
						String accpk = FipBaseDataProxy.getAccountingBookIDByFiOrgAndSetOfBook(vo.getPk_org(), vo.getPk_setorg1());
						if (accpk != null) {
							String use2ndOrg = SysinitAccessor.getInstance().getParaString(accpk, "GL120");
							if ("Y".equalsIgnoreCase(use2ndOrg)) {
								orgChooser.getModel().setPk_org(null);
								orgChooser.getModel().setUiState(UIState.EDIT);
								orgChooser.setRefNodeName("业务单元");
								orgChooser.addWherePart(" and pk_corp in (select pk_corp from org_orgs where pk_org='" + vo.getPk_org() + "') and (pk_org='" + vo.getPk_org() + "' or not exists(select 1 from org_accountingbook where pk_relorg=org_orgs.pk_org and enablestate=2 and accountenablestate=2))");
							}
						}
						orgChooser.getModel().setPk_org(vo.getPk_org());
					}

				}
				getDialog().showModal();
				// if (getDialog().showModal() == UIDialog.ID_OK) {
				// dialogCloseOK();
				// }
			}
		}
	}

	private void dialogCloseOK() {
		int row = getModel().getSelectedIndex();
		DocViewVO[] vos = getDialog().getBillTableModel().getListVO().getClassviews();
		if (vos != null)
			for (int i = 0; i < vos.length; i++) {
				vos[i].setIndexnumber(i + 1);
				if (vos[i].getPk_classview_b() != null && vos[i].getPk_classview_b().startsWith(tempPKflag)) {
					vos[i].setPk_classview_b(null);
				}
			}
		getEditor().getBillCardPanel().setBodyValueAt(vos, row, DocViewListVO.VIEWOBJ);
		// vo.setClassviews((DocViewVO[]) getEditor().getBillCardPanel().getBodyValueAt(row, DocViewListVO.VIEWOBJ));
		getEditor().afterEdit(new BillEditEvent(this, row, row));
		Object selectedData = getEditor().getModel().getSelectedData();
		// Object selectedData = getEditor().getBillCardPanel().getBillModel().getBodyValueRowVO(row, DocViewListVO.class.getName());
		getModel().fireEvent(new AppEvent(FipAppEventType.DATA_CHANGED, getModel(), selectedData));
	}

	private DocViewBodyDialog getDialog() {
		if (dialog == null) {
			dialog = new DocViewBodyDialog(getEditor());
			dialog.setMenufactory(new UIFMenuFactory(getModel().getContext()));
			NODE_TYPE nodeType = getModel().getContext().getNodeType();
			if (NODE_TYPE.GROUP_NODE.equals(nodeType)) {
				dialog.setXmlFilePath("nc/ui/fip/docview/view/docviewbody_grp_config.xml");
			} else if (NODE_TYPE.ORG_NODE.equals(nodeType)) {
				dialog.setXmlFilePath("nc/ui/fip/docview/view/docviewbody_org_config.xml");
			}
			dialog.initUI();
			dialog.getBillTableModel().getContext().setNodeType(nodeType);
			dialog.getBillTableModel().getContext().setFuncInfo(getModel().getContext().getFuncInfo());
			dialog.addUIDialogListener(new UIDialogListener() {

				@Override
				public void dialogClosed(UIDialogEvent event) {
					if (getDialog().getResult() == UIDialog.ID_OK) {
						dialogCloseOK();
					}
				}
			});
		}
		return dialog;
	}

	public BatchBillTableModel getModel() {
		return model;
	}

	public void setModel(BatchBillTableModel model) {
		this.model = model;
		if (model != null) {
			model.removeAppEventListener(this);
			model.addAppEventListener(this);
		}
	}

	public BatchBillTable getEditor() {
		return editor;
	}

	public void setEditor(BatchBillTable editor) {
		this.editor = editor;
	}
}