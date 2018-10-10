package nc.vo.zl.hql_builldingfile;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggBuildingfileVOMeta extends AbstractBillMeta {
  public AggBuildingfileVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.zl.hql_builldingfile.BuildingfileVO.class);
  }
}