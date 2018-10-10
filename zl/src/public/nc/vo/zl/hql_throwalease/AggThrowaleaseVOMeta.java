package nc.vo.zl.hql_throwalease;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggThrowaleaseVOMeta extends AbstractBillMeta {
  public AggThrowaleaseVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_throwalease.ThrowaleaseVO.class);
    this.addChildren(nc.vo.zl.hql_throwalease.Throwalease_bzjthVO.class);
    this.addChildren(nc.vo.zl.hql_throwalease.Throwalease_khfcVO.class);
    this.addChildren(nc.vo.zl.hql_throwalease.Throwalease_tzysVO.class);
    this.addChildren(nc.vo.zl.hql_throwalease.Throwalease_fyqsVO.class);
  }
}