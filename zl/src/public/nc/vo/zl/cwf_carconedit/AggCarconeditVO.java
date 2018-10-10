package nc.vo.zl.cwf_carconedit;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.cwf_carconedit.CarconeditVO")
public class AggCarconeditVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggCarconeditVOMeta.class);
    return billMeta;
  }

  @Override
  public CarconeditVO getParentVO() {
    return (CarconeditVO) this.getParent();
  }
}