package nc.vo.zl.lyw_confirmation;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggConfirmationVOMeta extends AbstractBillMeta {
  public AggConfirmationVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.lyw_confirmation.ConfirmationVO.class);
  }
}