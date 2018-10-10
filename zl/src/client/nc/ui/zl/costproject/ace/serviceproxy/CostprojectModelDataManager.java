package nc.ui.zl.costproject.ace.serviceproxy;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

public class CostprojectModelDataManager extends BaseBillModelDataManager {

	@Override
	public void initModel() {
		//自定义内容
		String fcode=getModel().getContext().getNodeCode();
		
		String pk_group = AppContext.getInstance().getPkGroup();
		String sqlwhere =" and pk_group = '" + pk_group+"'";
		if(fcode.equals("ZLH120")){
			sqlwhere +=" and nvl(vdef1,0)=0 ";
		}
		if(fcode.equals("ZLH122")){
			String org=getModel().getContext().getPk_org();
			//sqlwhere+=" and pk_org in ('"+org+"','0001A910000000000LRO') " ;
			sqlwhere+=" and pk_org='"+org+"' or nvl(vdef1,0)=0 and nvl(dr,0)=0" ;
		}
		sqlwhere =sqlwhere +" order by code";
		super.initModelBySqlWhere(sqlwhere);
	}

}
