package nc.vo.zl.ld_feescale;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.ld_feescale.FeescaleVO")

public class AggFeescaleVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggFeescaleVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public FeescaleVO getParentVO(){
	  	return (FeescaleVO)this.getParent();
	  }
	  
}