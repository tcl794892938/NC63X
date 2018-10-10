package nc.vo.zl.ly_bslist;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBslistVOMeta extends AbstractBillMeta {
  public AggBslistVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_bslist.BslistVO.class);
  }
}