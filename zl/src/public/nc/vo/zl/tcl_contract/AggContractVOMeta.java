package nc.vo.zl.tcl_contract;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggContractVOMeta extends AbstractBillMeta {
  public AggContractVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.tcl_contract.ContractVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractZqfycfVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractZqfyVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractCustVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractCwcfVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractZzqVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractBzjVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractYwcfVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractHouseVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractMzqVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractFkmxVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractZjmxVO.class);
    this.addChildren(nc.vo.zl.tcl_contract.ContractZqmxVO.class);
  }
}