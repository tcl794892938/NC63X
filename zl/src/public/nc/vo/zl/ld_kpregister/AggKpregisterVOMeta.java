package nc.vo.zl.ld_kpregister;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggKpregisterVOMeta extends AbstractBillMeta {
  public AggKpregisterVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_kpregister.KpregisterVO.class);
    this.addChildren(nc.vo.zl.ld_kpregister.KpregisterDVO.class);
  }
}