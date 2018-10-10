package nc.vo.zl.tcl_contract;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.tcl_contract.ContractVO")
public class AggContractVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggContractVOMeta.class);
    return billMeta;
  }

  @Override
  public ContractVO getParentVO() {
    return (ContractVO) this.getParent();
  }
  
  /**
   * 周期费用拆分
   */
  public ContractZqfycfVO[] getChildZqfycfVO(){
	  return (ContractZqfycfVO[])this.getChildren(ContractZqfycfVO.class);
  }
  
  /**
   * 周期费用
   */
  public ContractZqfyVO[] getChildZqfyVO(){
	  return (ContractZqfyVO[])this.getChildren(ContractZqfyVO.class);
  }
  
  /**
   * 客户信息
   */
  public ContractCustVO[] getChildCustVO(){
	  return (ContractCustVO[])this.getChildren(ContractCustVO.class);
  }
  
  /**
   * 财务拆分
   */
  public ContractCwcfVO[] getChildCwcfVO(){
	  return (ContractCwcfVO[])this.getChildren(ContractCwcfVO.class);
  }
  
  /**
   * 增长期
   */
  public ContractZzqVO[] getChildZzqVO(){
	  return (ContractZzqVO[])this.getChildren(ContractZzqVO.class);
  }
  
  /**
   * 保证金
   */
  public ContractBzjVO[] getChildBzjVO(){
	  return (ContractBzjVO[])this.getChildren(ContractBzjVO.class);
  }
  
  /**
   * 业务拆分
   */
  public ContractYwcfVO[] getChildYwcfVO(){
	  return (ContractYwcfVO[])this.getChildren(ContractYwcfVO.class);
  }
  
  /**
   * 房产信息
   */
  public ContractHouseVO[] getChildHouseVO(){
	  return (ContractHouseVO[])this.getChildren(ContractHouseVO.class);
  }
  
  /**
   * 免租期
   */
  public ContractMzqVO[] getChildMzqVO(){
	  return (ContractMzqVO[])this.getChildren(ContractMzqVO.class);
  }
  /**
   * 付款明细
   */
  public ContractFkmxVO[] getChildFkmxVO(){
	  return (ContractFkmxVO[])this.getChildren(ContractFkmxVO.class);
  }
  /**
   * 租金明细
   */
  public ContractZjmxVO[] getChildZjmxVO(){
	  return (ContractZjmxVO[])this.getChildren(ContractZjmxVO.class);
  }
  
  /**
   * 周期明细
   */
  public ContractZqmxVO[] getChildZqmxVO(){
	  return (ContractZqmxVO[])this.getChildren(ContractZqmxVO.class);
  }
}