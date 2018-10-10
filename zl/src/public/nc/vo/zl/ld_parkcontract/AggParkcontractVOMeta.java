package nc.vo.zl.ld_parkcontract;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggParkcontractVOMeta extends AbstractBillMeta {
  public AggParkcontractVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_parkcontract.ParkcontractVO.class);
    this.addChildren(nc.vo.zl.ld_parkcontract.ParkcontractCVO.class);
    this.addChildren(nc.vo.zl.ld_parkcontract.ParkcontractFVO.class);
    this.addChildren(nc.vo.zl.ld_parkcontract.ParkcontractBVO.class);
  }
}