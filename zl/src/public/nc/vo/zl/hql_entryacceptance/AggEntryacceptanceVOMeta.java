package nc.vo.zl.hql_entryacceptance;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggEntryacceptanceVOMeta extends AbstractBillMeta {
  public AggEntryacceptanceVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_entryacceptance.EntryacceptanceVO.class);
    this.addChildren(nc.vo.zl.hql_entryacceptance.Entryacceptance_khfcVO.class);
    this.addChildren(nc.vo.zl.hql_entryacceptance.Entryacceptance_jcysVO.class);
  }
}