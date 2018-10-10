package nc.vo.zl.ld_mdcontract;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggMdcontractVOMeta extends AbstractBillMeta {
  public AggMdcontractVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ld_mdcontract.MdcontractVO.class);
    this.addChildren(nc.vo.zl.ld_mdcontract.MdcontractCVO.class);
  }
}