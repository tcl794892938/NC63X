package nc.vo.zl.hql_throwalease;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_throwalease.ThrowaleaseVO")
public class AggThrowaleaseVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggThrowaleaseVOMeta.class);
    return billMeta;
  }

  @Override
  public ThrowaleaseVO getParentVO() {
    return (ThrowaleaseVO) this.getParent();
  }
}