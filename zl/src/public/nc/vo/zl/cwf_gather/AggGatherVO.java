package nc.vo.zl.cwf_gather;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.cwf_gather.GatherVO")
public class AggGatherVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggGatherVOMeta.class);
    return billMeta;
  }

  @Override
  public GatherVO getParentVO() {
    return (GatherVO) this.getParent();
  }
}