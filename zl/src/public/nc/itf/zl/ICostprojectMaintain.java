package nc.itf.zl;

import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.vo.pub.BusinessException;

public interface ICostprojectMaintain {

	public void delete(CostprojectVO vo) throws BusinessException;

	public CostprojectVO insert(CostprojectVO vo) throws BusinessException;

	public CostprojectVO update(CostprojectVO vo) throws BusinessException;

	public CostprojectVO[] query(String whereSql) throws BusinessException;
}