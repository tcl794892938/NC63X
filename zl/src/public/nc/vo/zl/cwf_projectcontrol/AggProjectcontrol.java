package nc.vo.zl.cwf_projectcontrol;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.cwf_projectcontrol.ProjectcontrolVO")
public class AggProjectcontrol extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggProjectcontrolMeta.class);
    return billMeta;
  }

  @Override
  public ProjectcontrolVO getParentVO() {
    return (ProjectcontrolVO) this.getParent();
  }
}