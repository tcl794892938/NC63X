package nc.vo.zl.ly_businesssource;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBusinessSourceVOMeta extends AbstractBillMeta {
  public AggBusinessSourceVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_businesssource.BusinessVO.class);
    this.addChildren(nc.vo.zl.ly_businesssource.BusinessBVO.class);
  }
}