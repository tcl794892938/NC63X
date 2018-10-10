package nc.vo.zl.ld_feescale;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggFeescaleVOMeta extends AbstractBillMeta{
	
	public AggFeescaleVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.zl.ld_feescale.FeescaleVO.class);
	}
}