package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.pub.BusinessException;

public interface IHql_builldingfileMaintain {

	public void delete(AggBuildingfileVO[] vos) throws BusinessException;

	public AggBuildingfileVO[] insert(AggBuildingfileVO[] vos) throws BusinessException;

	public AggBuildingfileVO[] update(AggBuildingfileVO[] vos) throws BusinessException;

	public AggBuildingfileVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	
	public BuildingfileVO updateAndRewrite(String pk_building) throws BusinessException;
	
	public void updateBuildingInfo(String sql) throws BusinessException;
}