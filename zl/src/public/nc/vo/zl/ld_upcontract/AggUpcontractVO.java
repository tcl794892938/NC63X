package nc.vo.zl.ld_upcontract;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_upcontract.UpcontractVO")
public class AggUpcontractVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggUpcontractVOMeta.class);
    return billMeta;
  }

  @Override
  public UpcontractVO getParentVO() {
    return (UpcontractVO) this.getParent();
  }
}