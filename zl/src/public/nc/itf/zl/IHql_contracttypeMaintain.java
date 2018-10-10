package nc.itf.zl;

import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.vo.pub.BusinessException;

public interface IHql_contracttypeMaintain {

	public void delete(ContracttypeVO vo) throws BusinessException;

	public ContracttypeVO insert(ContracttypeVO vo) throws BusinessException;

	public ContracttypeVO update(ContracttypeVO vo) throws BusinessException;

	public ContracttypeVO[] query(String whereSql) throws BusinessException;
}