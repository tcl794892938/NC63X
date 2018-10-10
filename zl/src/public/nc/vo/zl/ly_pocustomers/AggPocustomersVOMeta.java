package nc.vo.zl.ly_pocustomers;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggPocustomersVOMeta extends AbstractBillMeta {
  public AggPocustomersVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_pocustomers.PocustomersVO.class);
    this.addChildren(nc.vo.zl.ly_pocustomers.PocustomersOrgVO.class);
    this.addChildren(nc.vo.zl.ly_pocustomers.PocustomersZrVO.class);
    this.addChildren(nc.vo.zl.ly_pocustomers.PocustomersZliVO.class);
  }
}