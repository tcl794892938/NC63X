package nc.vo.zl.ly_bslist;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_bslist.BslistVO")
public class AggBslistVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBslistVOMeta.class);
    return billMeta;
  }

  @Override
  public BslistVO getParentVO() {
    return (BslistVO) this.getParent();
  }
}