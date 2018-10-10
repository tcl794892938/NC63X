package nc.ui.fip.docview.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import nc.bs.logging.Logger;
import nc.md.MDBaseQueryFacade;
import nc.md.model.MetaDataException;
import nc.md.model.type.IType;
import nc.pub.fip.cache.OrgRelationCache;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.pubitf.fip.external.IRefInitializer;
import nc.ui.fip.docview.model.DocViewBodyTableModel;
import nc.ui.fip.pub.reftool.RefInitializerFactory;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillTableCellRenderer;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BatchBillTable;
import nc.ui.uif2.model.AppEventConst;
import nc.vo.bd.ref.RefCustomizedVO;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.docview.DocViewVO;
import nc.vo.fip.docview.SrcDocGroupVO;
import nc.vo.fip.impacfactor.ImpacFactorVO;
import nc.vo.fip.orgtool.OrgConvertor;
import nc.vo.fip.pub.FipAppEventType;
import nc.vo.fip.pub.StringComparator;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.MetaDataUserDefAttributeAdpter;

public class DocViewBodyTable extends BatchBillTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, HashMap<String, String[]>> multiCache = new HashMap<String, HashMap<String, String[]>>();
	boolean isCrossingData = false;

	@Override
	public void handleEvent(AppEvent event) {
		super.handleEvent(event);
		if (FipAppEventType.DATA_CHANGED.equals(event.getType()) && event.getSource() instanceof DocViewBodyTableModel) {
			refreshData();
		} else if (AppEventConst.MODEL_INITIALIZED.equals(event.getType())) {
		} else if (FipAppEventType.SRC_ORG_CHANGED.equals(event.getType()) && event.getSource() instanceof DocViewBodyTableModel) {
			refreshData();
		} else if (FipAppEventType.ORG_WILL_CHANGED.equals(event.getType()) && event.getSource() instanceof DocViewBodyTableModel) {
			commitData();
		} else if (AppEventConst.UISTATE_CHANGED.equals(event.getType())) {
			multiCache.clear();
		}
	}

	private void refreshData() {
		multiCache.clear();
		if (getModel() instanceof DocViewBodyTableModel) {
			DocViewBodyTableModel tablemodel = (DocViewBodyTableModel) getModel();
			DocViewListVO listVO = tablemodel.getListVO();
			if (listVO != null) {
				initSrcColumns(listVO.getSrcdocgroup(), listVO);
				initDesColumn(listVO.getDesdocid(), listVO);
			} else {
				initSrcColumns(null, listVO);
			}
			String pk_srcorg = tablemodel.getPk_srcorg();
			if (pk_srcorg != null) {
				if (listVO != null) {
					DocViewVO[] classviews = listVO.getClassviews();
					if (classviews != null) {
						ArrayList<DocViewVO> volist = new ArrayList<DocViewVO>();
						for (DocViewVO docViewVO : classviews) {
							if (pk_srcorg.equals(docViewVO.getPk_org()))
								volist.add(docViewVO);
						}
						if (!volist.isEmpty()) {
							getModel().initModel(volist.toArray());
							return;
						}
					}
				}
			} else {
				if (listVO != null) {
					DocViewVO[] classviews = listVO.getClassviews();
					getModel().initModel(classviews);
					return;
				}
			}
		}
		getModel().initModel(null);
	}

	public void commitData() {
		UIState uiState = getModel().getUiState();
		if (!UIState.EDIT.equals(uiState) && !UIState.ADD.equals(uiState))
			return;
		getBillCardPanel().stopEditing();
		if (getModel().getRowCount() > 0) {
			crossMultiDatas(getModel().getSelectedIndex());
		}
		if (getModel() instanceof DocViewBodyTableModel) {
			DocViewBodyTableModel tablemodel = (DocViewBodyTableModel) getModel();
			DocViewListVO listVO = tablemodel.getListVO();
			if (listVO != null) {
				String pk_org = tablemodel.getPk_srcorg();
				DocViewVO[] classviews = listVO.getClassviews();
				ArrayList<DocViewVO> list = new ArrayList<DocViewVO>();
				if (classviews != null)
					for (DocViewVO docViewVO : classviews) {
						if (StringComparator.compare1(pk_org, docViewVO.getPk_org()) != 0)
							list.add(docViewVO);
					}
				CircularlyAccessibleValueObject[] bodyValueVOs = getBillCardPanel().getBillModel().getBodyValueVOs(DocViewVO.class.getName());
				if (bodyValueVOs != null && bodyValueVOs.length > 0) {
					for (CircularlyAccessibleValueObject vo : bodyValueVOs) {
						if (StringComparator.compare1(pk_org, (String) vo.getAttributeValue(DocViewVO.PK_ORG)) == 0)
							list.add((DocViewVO) vo);
					}
				}
				if (list.isEmpty())
					listVO.setClassviews(null);
				else
					listVO.setClassviews(list.toArray(new DocViewVO[0]));
				listVO.setViewobj(listVO.getClassviews());
			}
		}
	}

	private void initItemRef(BillItem bi, String entityid, DocViewListVO listvo, String pk_org) {
		if (bi == null)
			return;
		if (bi.getItemEditor() == null)
			return;
		JComponent component = bi.getItemEditor().getComponent();
		if (component == null)
			return;
		if (component != null && component instanceof UIRefPane) {
			IRefInitializer refInitializer = RefInitializerFactory.createRefInitializer(getModel().getContext().getNodeType(), entityid, null);
			if (refInitializer != null) {
				String pk_setorg1 = listvo.getPk_setorg1();
				String orgEntityID = OrgRelationCache.getInstance().getOrgEntityID(entityid);
				if (orgEntityID != null) {
					String orgEntityID2 = OrgRelationCache.getInstance().getOrgEntityID(listvo.getDesdocid());
					if (StringComparator.compare1(orgEntityID, orgEntityID2) != 0) {
						pk_setorg1 = OrgConvertor.convertOrgPK(orgEntityID2, orgEntityID, pk_setorg1);
					}
				}
				refInitializer.initRef((UIRefPane) component, listvo.getPk_group(), pk_org, pk_setorg1);
			}
		}
	}

	protected void initDesColumn(String desdocid, DocViewListVO listvo) {
		BillItem bi = getBillCardPanel().getBodyItem(DocViewVO.DESDOCVALUE);
		if (bi == null)
			return;
		RefCustomizedVO refTypeSet = bi.getRefTypeSet();
		try {
			IType typeByID = MDBaseQueryFacade.getInstance().getTypeByID(desdocid, IType.STYLE_SINGLE);
			bi.setMetaDataProperty(new MetaDataUserDefAttributeAdpter(bi.getMetaDataProperty().getAttribute(), typeByID));
			bi.setName(typeByID.getDisplayName());
		} catch (MetaDataException e) {
			Logger.error("", e);
		}
		RefCustomizedVO refTypeSet2 = bi.getRefTypeSet();
		if (refTypeSet2 != null) {
			if (refTypeSet != null)
				refTypeSet2.setReturnCode(refTypeSet.isReturnCode());
			else
				refTypeSet2.setReturnCode(false);
		}
		initItemRef(bi, desdocid, listvo, listvo.getPk_org());
		bi.setShow(true);
		getBillCardPanel().hideBodyTableCol(bi.getKey());
		getBillCardPanel().showBodyTableCol(bi.getKey());
		getBillCardPanel().getBodyPanel().refreshTableCol(bi.getKey());
	}

	protected void initSrcColumns(SrcDocGroupVO[] srcdocgroup, DocViewListVO listvo) {
		BillItem[] bodyItems = getBillCardPanel().getBodyItems();
		String prefix = "factorvalue";
		if (bodyItems != null) {
			for (BillItem billItem : bodyItems) {
				if (billItem.getKey().startsWith(prefix)) {
					billItem.setShow(false);
					getBillCardPanel().hideBodyTableCol(billItem.getKey());
				}
			}
		}
		if (srcdocgroup != null && srcdocgroup.length > 0) {
			ImpacFactorVO[] factors = listvo.getFactors();
			for (int i = 0; i < srcdocgroup.length; i++) {
				String strKey = prefix + (i + 1);
				BillItem bi = getBillCardPanel().getBodyItem(strKey);
				if (bi == null)
					continue;
				RefCustomizedVO refTypeSet = bi.getRefTypeSet();
				SrcDocGroupVO srcDocGroupVO = srcdocgroup[i];
				IType typeByID = null;
				String pk_srcdocid = srcDocGroupVO.getPk_srcdocid();
				try {
					typeByID = MDBaseQueryFacade.getInstance().getTypeByID(pk_srcdocid, IType.STYLE_SINGLE);
					bi.setMetaDataProperty(new MetaDataUserDefAttributeAdpter(bi.getMetaDataProperty().getAttribute(), typeByID));
					bi.setName(typeByID.getDisplayName());
				} catch (MetaDataException e1) {
					Logger.error("", e1);
				}
				RefCustomizedVO refTypeSet2 = bi.getRefTypeSet();
				if (refTypeSet2 != null) {
					if (refTypeSet != null)
						refTypeSet2.setReturnCode(refTypeSet.isReturnCode());
					else
						refTypeSet2.setReturnCode(false);
				}
				if (factors != null && factors.length == srcdocgroup.length) {
					ImpacFactorVO factor = factors[i];
					if (factor != null) {
						if (factor.getDisplaytype() != null) {
							switch (factor.getDisplaytype()) {
							case 0: {// name
								if (refTypeSet2 != null) {
									refTypeSet2.setReturnCode(false);
								}
								break;
							}
							case 1: {// code
								if (refTypeSet2 != null) {
									refTypeSet2.setReturnCode(true);
								}
								break;
							}
							case 9: {// formula
								if (factor.getDisplayformula() != null) {
									bi.setLoadFormula(new String[] { factor.getDisplayformula() });
								}
								break;
							}
							default: {

							}
							}
						}
						if (srcDocGroupVO.getSrcref() == null && factor.getRefmodelname() != null) {
							JComponent component = bi.getItemEditor().getComponent();
							if (component == null)
								return;
							if (component != null && component instanceof UIRefPane) {
								((UIRefPane) component).setRefNodeName(factor.getRefmodelname());
							}
						}
					}
				}
				if (srcDocGroupVO.getSrcref() != null) {
					JComponent component = bi.getItemEditor().getComponent();
					if (component == null)
						return;
					if (component != null && component instanceof UIRefPane) {
						((UIRefPane) component).setRefNodeName(srcDocGroupVO.getSrcref());
					}
				}
				String pk_srcorg = listvo.getPk_org();
				if (getModel() instanceof DocViewBodyTableModel) {
					DocViewBodyTableModel tablemodel = (DocViewBodyTableModel) getModel();
					String pk_srcorg2 = tablemodel.getPk_srcorg();
					if (pk_srcorg2 != null)
						pk_srcorg = pk_srcorg2;
				}
				initItemRef(bi, pk_srcdocid, listvo, pk_srcorg);
				bi.setShow(true);
				getBillCardPanel().showBodyTableCol(bi.getKey());
				getBillCardPanel().getBodyPanel().refreshTableCol(bi.getKey());
				TableColumn showCol = getBillCardPanel().getBodyPanel().getShowCol(bi.getKey());
				if (showCol != null) {
					TableCellRenderer cellRenderer = showCol.getCellRenderer();
					if ((cellRenderer instanceof BillTableCellRenderer) && !(cellRenderer instanceof MultiDocCellRenderer))
						showCol.setCellRenderer(new MultiDocCellRenderer(this));
				}
			}
		}
	}

	@Override
	public void initUI() {
		super.initUI();
		initBillTable();
	}

	private void initBillTable() {
		getBillCardPanel().getBillTable().setColumnSelectionAllowed(false);
		getBillCardPanel().getBillTable().setSortEnabled(false);
	}

	@Override
	public boolean beforeEdit(BillEditEvent e) {
		BillItem bi = getBillCardPanel().getBodyItem(e.getKey());
		if (bi != null) {
			JComponent component = bi.getComponent();
			if (component != null && component instanceof UIRefPane) {
				if (!((UIRefPane) component).isMultiSelectedEnabled())
					((UIRefPane) component).setMultiSelectedEnabled(true);
			}
		}
		return super.beforeEdit(e);
	}

	@Override
	protected void doAfterEdit(BillEditEvent e) {
		super.doAfterEdit(e);
		BillItem bi = getBillCardPanel().getBodyItem(e.getKey());
		if (bi != null) {
			JComponent component = bi.getComponent();
			if (component != null && component instanceof UIRefPane) {
				String[] refPKs = ((UIRefPane) component).getRefPKs();
				if (refPKs != null && refPKs.length > 1) {
					HashMap<String, String[]> rowMap = multiCache.get("" + e.getRow());
					if (rowMap == null) {
						rowMap = new HashMap<String, String[]>();
						multiCache.put("" + e.getRow(), rowMap);
					}

					if (!DocViewVO.DESDOCVALUE.equals(bi.getKey())) {
						// 有级次的来源档案,按照先下级后上级的规则,将主健数组反转
						try {
							IGeneralAccessor accessor = GeneralAccessorFactory.getAccessor(bi.getMetaDataProperty().getRefBusinessEntity().getID());
							if (accessor != null) {
								if (accessor.isHaslevel()) {
									ArrayList<String> sortpks = new ArrayList<String>();
									for (int j = refPKs.length - 1; j >= 0; j--) {
										sortpks.add(refPKs[j]);
									}
									refPKs = sortpks.toArray(refPKs);
								}
							}
						} catch (Throwable ex) {
							Logger.info(ex);
						}
					}

					rowMap.put(e.getKey(), refPKs);
				}
			}
		}
		if (DocViewVO.DESDOCVALUE.equals(e.getKey())) {
			crossMultiDatas(e.getRow());
		}
	}

	@Override
	public void bodyRowChange(BillEditEvent e) {
		super.bodyRowChange(e);
		if (!isCrossingData)
			crossMultiDatas(e.getOldRow());
	}

	public HashMap<String, String[]> getCachedDocs(int row) {
		return multiCache.get("" + row);
	}

	public void crossMultiDatas(int row) {
		isCrossingData = true;
		HashMap<String, String[]> hashMap = getCachedDocs(row);
		if (hashMap != null) {
			ArrayList<DocViewVO> volist = new ArrayList<DocViewVO>();
			volist.add((DocViewVO) getBillCardPanel().getBillModel().getBodyValueRowVO(row, DocViewVO.class.getName()));
			Set<Entry<String, String[]>> entrySet = hashMap.entrySet();
			for (Entry<String, String[]> entry : entrySet) {
				volist = getCrossedVO(volist, entry);
			}
			try {
				getModel().delLine(row);
			} catch (Exception e) {
				Logger.error("", e);
			}
			getModel().insertLines(row, volist.toArray());
			getModel().setSelectedIndex(row);
			multiCache.put("" + row, null);
		}
		isCrossingData = false;
	}

	private ArrayList<DocViewVO> getCrossedVO(ArrayList<DocViewVO> volist, Entry<String, String[]> entry) {
		ArrayList<DocViewVO> rslist = new ArrayList<DocViewVO>();
		for (DocViewVO docViewVO : volist) {
			for (String pk : entry.getValue()) {
				DocViewVO vo = (DocViewVO) docViewVO.clone();
				vo.setAttributeValue(entry.getKey(), pk);
				rslist.add(vo);
			}
		}
		return rslist;
	}
}
