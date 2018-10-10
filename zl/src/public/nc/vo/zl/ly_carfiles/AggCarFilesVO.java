package nc.vo.zl.ly_carfiles;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ly_carfiles.CarFilesVO")
public class AggCarFilesVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggCarFilesVOMeta.class);
    return billMeta;
  }

  @Override
  public CarFilesVO getParentVO() {
    return (CarFilesVO) this.getParent();
  }
}