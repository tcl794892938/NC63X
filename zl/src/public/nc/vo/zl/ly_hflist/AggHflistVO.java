package nc.vo.zl.ly_hflist;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_hflist.HflistVO")
public class AggHflistVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggHflistVOMeta.class);
    return billMeta;
  }

  @Override
  public HflistVO getParentVO() {
    return (HflistVO) this.getParent();
  }
}