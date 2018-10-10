package nc.vo.zl.hql_jt_acceptance;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_jt_acceptance.Jt_acceptanceVO")
public class AggJt_acceptanceVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggJt_acceptanceVOMeta.class);
    return billMeta;
  }

  @Override
  public Jt_acceptanceVO getParentVO() {
    return (Jt_acceptanceVO) this.getParent();
  }
}