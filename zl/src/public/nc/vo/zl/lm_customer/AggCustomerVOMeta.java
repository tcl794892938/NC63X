package nc.vo.zl.lm_customer;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggCustomerVOMeta extends AbstractBillMeta {
  public AggCustomerVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.lm_customer.CustomerVO.class);
    this.addChildren(nc.vo.zl.lm_customer.Customer_qjfyVO.class);
    this.addChildren(nc.vo.zl.lm_customer.Customer_fcxxVO.class);
    this.addChildren(nc.vo.zl.lm_customer.Customer_zzxmVO.class);
    this.addChildren(nc.vo.zl.lm_customer.Customer_wxfwVO.class);
    this.addChildren(nc.vo.zl.lm_customer.Customer_carVO.class);
  }
}