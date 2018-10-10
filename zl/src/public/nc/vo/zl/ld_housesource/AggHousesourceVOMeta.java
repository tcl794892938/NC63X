package nc.vo.zl.ld_housesource;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggHousesourceVOMeta extends AbstractBillMeta {
  public AggHousesourceVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_housesource.HousesourceVO.class);
  }
}