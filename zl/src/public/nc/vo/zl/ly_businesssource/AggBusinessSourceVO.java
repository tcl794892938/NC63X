package nc.vo.zl.ly_businesssource;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_businesssource.BusinessVO")
public class AggBusinessSourceVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBusinessSourceVOMeta.class);
    return billMeta;
  }

  @Override
  public BusinessVO getParentVO() {
    return (BusinessVO) this.getParent();
  }
}