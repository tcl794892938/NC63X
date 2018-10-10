package nc.vo.zl.cwf_recbill;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggRecbillVOMeta extends AbstractBillMeta {
  public AggRecbillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.cwf_recbill.RecbillVO.class);
  }
}