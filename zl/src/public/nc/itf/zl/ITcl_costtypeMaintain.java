package nc.itf.zl;

import nc.vo.zl.tcl_costtype.CosttypeVO;
import nc.vo.pub.BusinessException;

public interface ITcl_costtypeMaintain {

	public void delete(CosttypeVO vo) throws BusinessException;

	public CosttypeVO insert(CosttypeVO vo) throws BusinessException;

	public CosttypeVO update(CosttypeVO vo) throws BusinessException;

	public CosttypeVO[] query(String whereSql) throws BusinessException;
}