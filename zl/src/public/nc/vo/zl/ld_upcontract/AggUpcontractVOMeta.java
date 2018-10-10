package nc.vo.zl.ld_upcontract;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggUpcontractVOMeta extends AbstractBillMeta {
  public AggUpcontractVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_upcontract.UpcontractVO.class);
    this.addChildren(nc.vo.zl.ld_upcontract.UpcontractUVO.class);
  }
}