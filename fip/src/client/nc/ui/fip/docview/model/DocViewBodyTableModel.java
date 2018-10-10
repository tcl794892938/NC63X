/*
 * 
 */
package nc.ui.fip.docview.model;

import nc.ui.fip.pub.model.OrgChooserAppModel;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.model.AppEventConst;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.fip.docview.DocViewListVO;
import nc.vo.fip.pub.FipAppEventType;
import nc.vo.fip.pub.StringComparator;

/**
 * @author zhaohenga
 * @createdate 2010-3-18 下午01:59:50
 * @since NC6.0
 */
public class DocViewBodyTableModel extends BatchBillTableModel implements AppEventListener {
	private DocViewListVO listVO = null;
	private String pk_srcorg = null;

	/*
	 * handleEvent方法在AppEventListener中的实现
	 * 
	 * @see nc.ui.uif2.AppEventListener#handleEvent(nc.ui.uif2.AppEvent)
	 */
	@Override
	public void handleEvent(AppEvent event) {
		if ((FipAppEventType.DATA_CHANGED.equals(event.getType()) || AppEventConst.SELECTION_CHANGED.equals(event.getType())) && event.getSource() instanceof DocViewListTableModel) {
			DocViewListVO listvo = null;
			Object contextObject = event.getContextObject();
			if (contextObject != null && contextObject instanceof DocViewListVO) {
				listvo = (DocViewListVO) contextObject;
			} else {
				DocViewListTableModel listmodel = (DocViewListTableModel) event.getSource();
				Object selectedData = listmodel.getSelectedData();

				if (selectedData != null) {
					if (selectedData.getClass().isArray()) {
						Object[] o = (Object[]) selectedData;
						if (o.length > 0)
							listvo = (DocViewListVO) o[0];
					} else {
						listvo = (DocViewListVO) selectedData;
					}
				}
			}
			setListVO(listvo);
		} else if (AppEventConst.SELECTED_DATE_CHANGED.equals(event.getType()) && event.getSource() instanceof OrgChooserAppModel) {
			Object contextObject = event.getContextObject();
			setPk_srcorg((String) contextObject);
		}
	}

	public DocViewListVO getListVO() {
		return listVO;
	}

	public void setListVO(DocViewListVO listvo) {
		this.listVO = listvo == null ? null : (DocViewListVO) listvo.clone();
		fireEvent(new AppEvent(FipAppEventType.DATA_CHANGED, this, listVO));
	}

	public String getPk_srcorg() {
		return pk_srcorg;
	}

	public void setPk_srcorg(String pk_srcorg) {
		if (StringComparator.compare1(this.pk_srcorg, pk_srcorg) == 0)
			return;
		fireEvent(new AppEvent(FipAppEventType.ORG_WILL_CHANGED, this, pk_srcorg));
		this.pk_srcorg = pk_srcorg;
		fireEvent(new AppEvent(FipAppEventType.SRC_ORG_CHANGED, this, pk_srcorg));
	}

}