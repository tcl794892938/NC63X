package nc.vo.zl.lm_customer;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.lm_customer.CustomerVO")
public class AggCustomerVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggCustomerVOMeta.class);
    return billMeta;
  }

  @Override
  public CustomerVO getParentVO() {
    return (CustomerVO) this.getParent();
  }
}