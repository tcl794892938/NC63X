package nc.vo.zl.lyw_billconfirmed;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBillconfirmedVOMeta extends AbstractBillMeta {
  public AggBillconfirmedVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.lyw_billconfirmed.BillconfirmedVO.class);
    this.addChildren(nc.vo.zl.lyw_billconfirmed.BillconfirmedBVO.class);
  }
}