package nc.vo.zl.base_project;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.zl.base_project.ProjectVO")

public class AggProjectVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggProjectVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public ProjectVO getParentVO(){
	  	return (ProjectVO)this.getParent();
	  }
	  
}