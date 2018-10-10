package nc.vo.zl.ld_formattype;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggFormattypeVOMeta extends AbstractBillMeta {
  public AggFormattypeVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_formattype.FormattypeVO.class);
  }
}