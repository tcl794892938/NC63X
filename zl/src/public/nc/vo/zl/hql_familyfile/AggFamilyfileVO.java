package nc.vo.zl.hql_familyfile;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_familyfile.FamilyfileVO")
public class AggFamilyfileVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggFamilyfileVOMeta.class);
    return billMeta;
  }

  @Override
  public FamilyfileVO getParentVO() {
    return (FamilyfileVO) this.getParent();
  }
}