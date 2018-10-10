package nc.ui.zl.ld_formattype.ace.serviceproxy;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

public class Ld_formattypeModelDataManager extends BaseBillModelDataManager {

	@Override
	public void initModel() {
		//自定义内容
		String pk_group = AppContext.getInstance().getPkGroup();
		String sqlwhere = " and pk_group = '" + pk_group + "' ";
		//super.initModelBySqlWhere(sqlwhere);
		
        String fcode=getModel().getContext().getNodeCode();
		
		if(fcode.equals("ZLH160")){
			sqlwhere += " and vdef1='0'";
		}
		if(fcode.equals("ZLH162")){
			String org=getModel().getContext().getPk_org();
			sqlwhere+=" and pk_org in ('"+org+"','0001A910000000000LRO') " ;
		}
		sqlwhere += " order by code";
		super.initModelBySqlWhere(sqlwhere);
	}

}
