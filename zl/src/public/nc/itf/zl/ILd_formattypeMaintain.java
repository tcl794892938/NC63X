package nc.itf.zl;

import nc.vo.zl.ld_formattype.FormattypeVO;
import nc.vo.pub.BusinessException;

public interface ILd_formattypeMaintain {

	public void delete(FormattypeVO vo) throws BusinessException;

	public FormattypeVO insert(FormattypeVO vo) throws BusinessException;

	public FormattypeVO update(FormattypeVO vo) throws BusinessException;

	public FormattypeVO[] query(String whereSql) throws BusinessException;
}