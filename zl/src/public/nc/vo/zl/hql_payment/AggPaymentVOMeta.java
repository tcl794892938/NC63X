package nc.vo.zl.hql_payment;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggPaymentVOMeta extends AbstractBillMeta {
  public AggPaymentVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_payment.PaymentVO.class);
    this.addChildren(nc.vo.zl.hql_payment.Payment_bVO.class);
  }
}