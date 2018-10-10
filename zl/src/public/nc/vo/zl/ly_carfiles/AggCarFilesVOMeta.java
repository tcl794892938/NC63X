package nc.vo.zl.ly_carfiles;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggCarFilesVOMeta extends AbstractBillMeta {
  public AggCarFilesVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.ly_carfiles.CarFilesVO.class);
  }
}