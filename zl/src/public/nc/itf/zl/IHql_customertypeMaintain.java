package nc.itf.zl;

import nc.vo.zl.hql_customertype.CustomertypeVO;
import nc.vo.pub.BusinessException;

public interface IHql_customertypeMaintain {

	public void delete(CustomertypeVO vo) throws BusinessException;

	public CustomertypeVO insert(CustomertypeVO vo) throws BusinessException;

	public CustomertypeVO update(CustomertypeVO vo) throws BusinessException;

	public CustomertypeVO[] query(String whereSql) throws BusinessException;
}