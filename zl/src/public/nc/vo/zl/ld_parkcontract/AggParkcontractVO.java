package nc.vo.zl.ld_parkcontract;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_parkcontract.ParkcontractVO")
public class AggParkcontractVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggParkcontractVOMeta.class);
    return billMeta;
  }

  @Override
  public ParkcontractVO getParentVO() {
    return (ParkcontractVO) this.getParent();
  }
}