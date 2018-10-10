package nc.ui.zl.abs.panel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import nc.ui.pubapp.billref.dest.ITransferListRowChangeProcessor;
import nc.ui.pubapp.billref.dest.ITransferListViewProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.BillManageModel;

public class TransferBillView extends ShowUpableBillListView implements
    PropertyChangeListener {

  private static int debugStaticCount;

  private static final long serialVersionUID = 2849667363943793436L;

  /**
   * �б��Ƿ��Ѿ����ع����ݣ�����Ѿ����ع���ô�ڵ㷵��ʱ�л��б��ʱ��Ͳ��ټ���
   */
  boolean dataAlreadySyschForList = false;

  /**
   * �Ƿ���Ҫ�����ݼ��ص����棬Ϊ�˿���Ч�����⣬��ֻ��һ�ŵ��ݵ�ʱ���б�����Ȳ�����
   * ֻ�����л����б���ʾ��ʱ�����ʾ�����������������
   */
  boolean needLoad = true;

  @SuppressWarnings("unused")
  private int debugCount;

  private ITransferListViewProcessor listProcessor;

  private ITransferListRowChangeProcessor listRowChangeProcessor;

  public TransferBillView() {
    this.debugCount = TransferBillView.debugStaticCount++;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if ("componentVisible".equals(evt.getPropertyName())) {
      if (((Boolean) evt.getNewValue()).booleanValue()) {
        this.needLoad = true;
        if (!this.dataAlreadySyschForList) {
          this.synchronizeDataFromModel();
          this.handleSelectionChanged();
          // this.alreadySys = true;
        }
      }
    }
  }

  @Override
  protected void handleSelectionChanged() {
    if (!this.needLoad) {
      return;
    }
    super.handleSelectionChanged();
    this.processRowChange();
  }

  @Override
  protected void synchronizeDataFromModel() {
    if (!this.needLoad) {
      return;
    }
    this.dataAlreadySyschForList = true;
    if (this.getListProcessor() != null) {
      this.getListProcessor().processBefore(this,
          this.getModel().getData().toArray(new Object[0]));
    }
    super.synchronizeDataFromModel();
    if (this.getListProcessor() != null) {
      this.getListProcessor().processAfter(this,
          this.getModel().getData().toArray(new Object[0]));
    }
  }

  void setListProcessor(ITransferListViewProcessor listProcessor) {
    this.listProcessor = listProcessor;
  }

  void setListRowChangeProcessor(
      ITransferListRowChangeProcessor listRowChangeProcessor) {
    this.listRowChangeProcessor = listRowChangeProcessor;
  }

  private ITransferListViewProcessor getListProcessor() {
    return this.listProcessor;
  }

  private ITransferListRowChangeProcessor getListRowChangeProcessor() {
    return this.listRowChangeProcessor;
  }

  private void processRowChange() {
    BillManageModel model = this.getModel();
    Object selectedData = model.getSelectedData();
    int selectedRow = this.getModel().getSelectedRow();
    if (this.getListRowChangeProcessor() != null) {
      this.getListRowChangeProcessor().processRowChange(this, selectedRow,
          selectedData);
    }
  }

}
