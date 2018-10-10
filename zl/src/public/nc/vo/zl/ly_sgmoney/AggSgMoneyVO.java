package nc.vo.zl.ly_sgmoney;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_sgmoney.SgMoneyVO")
public class AggSgMoneyVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSgMoneyVOMeta.class);
    return billMeta;
  }

  @Override
  public SgMoneyVO getParentVO() {
    return (SgMoneyVO) this.getParent();
  }
}