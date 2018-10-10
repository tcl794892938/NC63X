package nc.vo.zl.ld_kpregister;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_kpregister.KpregisterVO")
public class AggKpregisterVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggKpregisterVOMeta.class);
    return billMeta;
  }

  @Override
  public KpregisterVO getParentVO() {
    return (KpregisterVO) this.getParent();
  }
}