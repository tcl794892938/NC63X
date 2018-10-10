package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;
import nc.vo.pub.BusinessException;

public interface ICwf_projectcontrolMaintain {

	public void delete(AggProjectcontrol[] vos) throws BusinessException;

	public AggProjectcontrol[] insert(AggProjectcontrol[] vos) throws BusinessException;

	public AggProjectcontrol[] update(AggProjectcontrol[] vos) throws BusinessException;

	public AggProjectcontrol[] query(IQueryScheme queryScheme)
			throws BusinessException;

}