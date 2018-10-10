package nc.vo.zl.hql_payment;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_payment.PaymentVO")
public class AggPaymentVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggPaymentVOMeta.class);
    return billMeta;
  }

  @Override
  public PaymentVO getParentVO() {
    return (PaymentVO) this.getParent();
  }
}