package nc.ui.zl.hql_contracttype.ace.serviceproxy;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

public class Hql_contracttypeModelDataManager extends BaseBillModelDataManager {

	@Override
	public void initModel() {
		//��ȡ��֯����
		String fcode=getModel().getContext().getNodeCode();
		//�Զ�������
		
		String pk_group = AppContext.getInstance().getPkGroup();
		String sqlwhere =" and pk_group = '" + pk_group +"'";
		if(fcode.equals("ZLH140")){
			sqlwhere =sqlwhere + " and nvl(vdef1,0)=0 ";
		}
		if(fcode.equals("ZLH142")){
			String org=getModel().getContext().getPk_org();
			sqlwhere+=" and (pk_org='"+org+"' or nvl(vdef1,0)=0) " ;
		}
		sqlwhere =sqlwhere + " order by code";
		super.initModelBySqlWhere(sqlwhere);
	}

}
