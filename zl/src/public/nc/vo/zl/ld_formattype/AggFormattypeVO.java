package nc.vo.zl.ld_formattype;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_formattype.FormattypeVO")
public class AggFormattypeVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggFormattypeVOMeta.class);
    return billMeta;
  }

  @Override
  public FormattypeVO getParentVO() {
    return (FormattypeVO) this.getParent();
  }
}