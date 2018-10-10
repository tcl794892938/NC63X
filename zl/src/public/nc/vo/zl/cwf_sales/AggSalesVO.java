package nc.vo.zl.cwf_sales;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.cwf_sales.SalesVO")
public class AggSalesVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSalesVOMeta.class);
    return billMeta;
  }

  @Override
  public SalesVO getParentVO() {
    return (SalesVO) this.getParent();
  }
}