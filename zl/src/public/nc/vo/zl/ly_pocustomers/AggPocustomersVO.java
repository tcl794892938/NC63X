package nc.vo.zl.ly_pocustomers;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_pocustomers.PocustomersVO")
public class AggPocustomersVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggPocustomersVOMeta.class);
    return billMeta;
  }

  @Override
  public PocustomersVO getParentVO() {
    return (PocustomersVO) this.getParent();
  }
}