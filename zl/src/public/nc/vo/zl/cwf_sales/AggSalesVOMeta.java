package nc.vo.zl.cwf_sales;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSalesVOMeta extends AbstractBillMeta {
  public AggSalesVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.cwf_sales.SalesVO.class);
  }
}