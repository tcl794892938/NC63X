package nc.vo.zl.hql_builldingfile;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.hql_builldingfile.BuildingfileVO")
public class AggBuildingfileVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggBuildingfileVOMeta.class);
    return billMeta;
  }

  @Override
  public BuildingfileVO getParentVO() {
    return (BuildingfileVO) this.getParent();
  }
}