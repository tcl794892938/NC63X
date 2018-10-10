package nc.vo.zl.ld_mdcontract;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_mdcontract.MdcontractVO")
public class AggMdcontractVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggMdcontractVOMeta.class);
    return billMeta;
  }

  @Override
  public MdcontractVO getParentVO() {
    return (MdcontractVO) this.getParent();
  }
}