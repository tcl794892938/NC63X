package nc.vo.zl.ly_zylist;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_zylist.ZylistVO")
public class AggZylistVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggZylistVOMeta.class);
    return billMeta;
  }

  @Override
  public ZylistVO getParentVO() {
    return (ZylistVO) this.getParent();
  }
}