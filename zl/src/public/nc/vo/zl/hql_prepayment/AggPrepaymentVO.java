package nc.vo.zl.hql_prepayment;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_prepayment.PrepaymentVO")
public class AggPrepaymentVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggPrepaymentVOMeta.class);
    return billMeta;
  }

  @Override
  public PrepaymentVO getParentVO() {
    return (PrepaymentVO) this.getParent();
  }
}