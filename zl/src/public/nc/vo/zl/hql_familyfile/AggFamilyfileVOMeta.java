package nc.vo.zl.hql_familyfile;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggFamilyfileVOMeta extends AbstractBillMeta {
  public AggFamilyfileVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_familyfile.FamilyfileVO.class);
  }
}