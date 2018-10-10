package nc.vo.zl.ly_zylist;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggZylistVOMeta extends AbstractBillMeta {
  public AggZylistVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_zylist.ZylistVO.class);
    this.addChildren(nc.vo.zl.ly_zylist.ZylistSrVO.class);
  }
}