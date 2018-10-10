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
   * ���ڷ��ò��
   */
  public ContractZqfycfVO[] getChildZqfycfVO(){
	  return (ContractZqfycfVO[])this.getChildren(ContractZqfycfVO.class);
  }
  
  /**
   * ���ڷ���
   */
  public ContractZqfyVO[] getChildZqfyVO(){
	  return (ContractZqfyVO[])this.getChildren(ContractZqfyVO.class);
  }
  
  /**
   * �ͻ���Ϣ
   */
  public ContractCustVO[] getChildCustVO(){
	  return (ContractCustVO[])this.getChildren(ContractCustVO.class);
  }
  
  /**
   * ������
   */
  public ContractCwcfVO[] getChildCwcfVO(){
	  return (ContractCwcfVO[])this.getChildren(ContractCwcfVO.class);
  }
  
  /**
   * ������
   */
  public ContractZzqVO[] getChildZzqVO(){
	  return (ContractZzqVO[])this.getChildren(ContractZzqVO.class);
  }
  
  /**
   * ��֤��
   */
  public ContractBzjVO[] getChildBzjVO(){
	  return (ContractBzjVO[])this.getChildren(ContractBzjVO.class);
  }
  
  /**
   * ҵ����
   */
  public ContractYwcfVO[] getChildYwcfVO(){
	  return (ContractYwcfVO[])this.getChildren(ContractYwcfVO.class);
  }
  
  /**
   * ������Ϣ
   */
  public ContractHouseVO[] getChildHouseVO(){
	  return (ContractHouseVO[])this.getChildren(ContractHouseVO.class);
  }
  
  /**
   * ������
   */
  public ContractMzqVO[] getChildMzqVO(){
	  return (ContractMzqVO[])this.getChildren(ContractMzqVO.class);
  }
  /**
   * ������ϸ
   */
  public ContractFkmxVO[] getChildFkmxVO(){
	  return (ContractFkmxVO[])this.getChildren(ContractFkmxVO.class);
  }
  /**
   * �����ϸ
   */
  public ContractZjmxVO[] getChildZjmxVO(){
	  return (ContractZjmxVO[])this.getChildren(ContractZjmxVO.class);
  }
  
  /**
   * ������ϸ
   */
  public ContractZqmxVO[] getChildZqmxVO(){
	  return (ContractZqmxVO[])this.getChildren(ContractZqmxVO.class);
  }
}