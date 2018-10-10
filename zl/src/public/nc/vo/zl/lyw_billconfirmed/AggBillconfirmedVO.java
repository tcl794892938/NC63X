package nc.vo.zl.lyw_billconfirmed;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.lyw_billconfirmed.BillconfirmedVO")
public class AggBillconfirmedVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBillconfirmedVOMeta.class);
    return billMeta;
  }

  @Override
  public BillconfirmedVO getParentVO() {
    return (BillconfirmedVO) this.getParent();
  }
}