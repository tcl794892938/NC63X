package nc.vo.zl.cwf_recbill;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.cwf_recbill.RecbillVO")
public class AggRecbillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggRecbillVOMeta.class);
    return billMeta;
  }

  @Override
  public RecbillVO getParentVO() {
    return (RecbillVO) this.getParent();
  }
}