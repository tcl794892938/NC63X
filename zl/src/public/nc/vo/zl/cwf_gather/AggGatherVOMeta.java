package nc.vo.zl.cwf_gather;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggGatherVOMeta extends AbstractBillMeta {
  public AggGatherVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.cwf_gather.GatherVO.class);
    this.addChildren(nc.vo.zl.cwf_gather.GatherBVO.class);
  }
}