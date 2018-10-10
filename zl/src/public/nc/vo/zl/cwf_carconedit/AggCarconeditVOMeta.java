package nc.vo.zl.cwf_carconedit;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggCarconeditVOMeta extends AbstractBillMeta {
  public AggCarconeditVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.cwf_carconedit.CarconeditVO.class);
    this.addChildren(nc.vo.zl.cwf_carconedit.CarconeditCVO.class);
    this.addChildren(nc.vo.zl.cwf_carconedit.CarconeditBVO.class);
    this.addChildren(nc.vo.zl.cwf_carconedit.CarconeditFVO.class);
  }
}