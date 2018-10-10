package nc.vo.zl.lyw_confirmation;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.lyw_confirmation.ConfirmationVO")
public class AggConfirmationVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggConfirmationVOMeta.class);
    return billMeta;
  }

  @Override
  public ConfirmationVO getParentVO() {
    return (ConfirmationVO) this.getParent();
  }
}