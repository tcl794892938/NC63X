package nc.vo.zl.ly_sgmoney;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSgMoneyVOMeta extends AbstractBillMeta {
  public AggSgMoneyVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_sgmoney.SgMoneyVO.class);
  }
}