package nc.vo.zl.hql_jt_acceptance;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggJt_acceptanceVOMeta extends AbstractBillMeta {
  public AggJt_acceptanceVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_jt_acceptance.Jt_acceptanceVO.class);
  }
}