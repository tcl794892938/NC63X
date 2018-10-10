package nc.ui.zl.hql_customertype.ace.serviceproxy;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

public class Hql_customertypeModelDataManager extends BaseBillModelDataManager {

	@Override
	public void initModel() {
		//获取组织编码
		String fcode=getModel().getContext().getNodeCode();
		//自定义内容
		String pk_group = AppContext.getInstance().getPkGroup();
		String sqlwhere =" and pk_group = '" + pk_group +"'";
		if(fcode.equals("ZLH150")){
			sqlwhere =sqlwhere + " and vdef1='0' ";
		}
		if(fcode.equals("ZLH152")){
			String org=getModel().getContext().getPk_org();
			sqlwhere+=" and pk_org in ('"+org+"','0001A910000000000LRO') " ;
		}
		sqlwhere =sqlwhere + " order by code";
		super.initModelBySqlWhere(sqlwhere);
	}

}
