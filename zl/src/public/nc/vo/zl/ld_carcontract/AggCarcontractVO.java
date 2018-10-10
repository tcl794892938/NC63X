package nc.vo.zl.ld_carcontract;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_carcontract.CarcontractVO")
public class AggCarcontractVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggCarcontractVOMeta.class);
    return billMeta;
  }

  @Override
  public CarcontractVO getParentVO() {
    return (CarcontractVO) this.getParent();
  }
}