package nc.vo.zl.hql_entryacceptance;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_entryacceptance.EntryacceptanceVO")
public class AggEntryacceptanceVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggEntryacceptanceVOMeta.class);
    return billMeta;
  }

  @Override
  public EntryacceptanceVO getParentVO() {
    return (EntryacceptanceVO) this.getParent();
  }
}