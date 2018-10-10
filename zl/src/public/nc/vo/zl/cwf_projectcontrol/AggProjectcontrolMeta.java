package nc.vo.zl.cwf_projectcontrol;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggProjectcontrolMeta extends AbstractBillMeta {
  public AggProjectcontrolMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.cwf_projectcontrol.ProjectcontrolVO.class);
    this.addChildren(nc.vo.zl.cwf_projectcontrol.ProjectcontrolBVO.class);
  }
}