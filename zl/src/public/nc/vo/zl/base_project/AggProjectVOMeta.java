package nc.vo.zl.base_project;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggProjectVOMeta extends AbstractBillMeta{
	
	public AggProjectVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.zl.base_project.ProjectVO.class);
	}
}