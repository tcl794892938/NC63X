package nc.ui.zl.tcl_costtype.ace.serviceproxy;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

public class Tcl_costtypeModelDataManager extends BaseBillModelDataManager {

	@Override
	public void initModel() {
		//自定义内容
		String pk_group = AppContext.getInstance().getPkGroup();
		String sqlwhere = " and pk_group = '" + pk_group + "' ";
		super.initModelBySqlWhere(sqlwhere);
	}

}
