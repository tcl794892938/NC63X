package nc.vo.zl.ld_housesource;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_housesource.HousesourceVO")
public class AggHousesourceVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggHousesourceVOMeta.class);
    return billMeta;
  }

  @Override
  public HousesourceVO getParentVO() {
    return (HousesourceVO) this.getParent();
  }
}