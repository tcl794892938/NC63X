package nc.vo.zl.ld_carcontract;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggCarcontractVOMeta extends AbstractBillMeta {
  public AggCarcontractVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_carcontract.CarcontractVO.class);
    this.addChildren(nc.vo.zl.ld_carcontract.CarcontractBVO.class);
    this.addChildren(nc.vo.zl.ld_carcontract.CarcontractCVO.class);
    this.addChildren(nc.vo.zl.ld_carcontract.CarcontractFVO.class);
  }
}