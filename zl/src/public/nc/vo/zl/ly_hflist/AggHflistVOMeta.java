package nc.vo.zl.ly_hflist;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggHflistVOMeta extends AbstractBillMeta {
  public AggHflistVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_hflist.HflistVO.class);
    this.addChildren(nc.vo.zl.ly_hflist.HflistRVO.class);
  }
}