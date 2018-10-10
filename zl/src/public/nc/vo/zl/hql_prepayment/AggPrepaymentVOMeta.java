package nc.vo.zl.hql_prepayment;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggPrepaymentVOMeta extends AbstractBillMeta {
  public AggPrepaymentVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_prepayment.PrepaymentVO.class);
    this.addChildren(nc.vo.zl.hql_prepayment.Prepayment_bVO.class);
  }
}