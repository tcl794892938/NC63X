package nc.impl.zl;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.ace.AceHql_builldingfilePubServiceImpl;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.IHql_builldingfileMaintain;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Hql_builldingfileMaintainImpl extends AceHql_builldingfilePubServiceImpl implements
		IHql_builldingfileMaintain {

	@Override
	public void delete(AggBuildingfileVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggBuildingfileVO[] insert(AggBuildingfileVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggBuildingfileVO[] update(AggBuildingfileVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggBuildingfileVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}
	
	@Override
	public BuildingfileVO updateAndRewrite(String pk_building) throws BusinessException {
		
		BaseDAO dao=new BaseDAO();
		
		String sql = "update zl_housesource h set h.buildarea = (select f.builtuparea from zl_familyfile f " +
				"where f.pk_familyfile = h.pk_familyfile),h.innerarea = (select f.innerarea from zl_familyfile "+
				"f where f.pk_familyfile = h.pk_familyfile),dr = 0 where h.buildname = '"+pk_building+"' and nvl(dr,0) = 0";
		dao.executeUpdate(sql);
		
		String sql2="select sum(nvl(e.buildarea,0)) builtuparea ,sum(nvl(e.innerarea,0)) innerarea from" +
				" zl_housesource e where nvl(e.dr,0)=0 and e.buildname = '"+pk_building+"'";
		BuildingfileVO vo=(BuildingfileVO)dao.executeQuery(sql2, new BeanProcessor(BuildingfileVO.class));
		
		return vo;
	}

	/**
	 * 更新楼栋信息（房源删除操作）
	 */
	@Override
	public void updateBuildingInfo(String sql) throws BusinessException {
		
		BaseDAO dao=new BaseDAO();
		dao.executeUpdate(sql);
	}
}
