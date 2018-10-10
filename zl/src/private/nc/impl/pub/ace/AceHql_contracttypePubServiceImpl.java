package nc.impl.pub.ace;

import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_contracttypePubServiceImpl {

	// 增加方法
	public ContracttypeVO inserttreeinfo(ContracttypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<ContracttypeVO> processer = new AroundProcesser<ContracttypeVO>(
					null);
			processer.before(new ContracttypeVO[] { vo });
			VOInsert<ContracttypeVO> ins = new VOInsert<ContracttypeVO>();
			ContracttypeVO[] superVOs = ins.insert(new ContracttypeVO[] { vo });
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除方法
	public void deletetreeinfo(ContracttypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<ContracttypeVO> processer = new AroundProcesser<ContracttypeVO>(
					null);
			processer.before(new ContracttypeVO[] { vo });
			VODelete<ContracttypeVO> voDel = new VODelete<ContracttypeVO>();
			voDel.delete(new ContracttypeVO[] { vo });
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// 修改方法
	public ContracttypeVO updatetreeinfo(ContracttypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<ContracttypeVO> processer = new AroundProcesser<ContracttypeVO>(
					null);
			ContracttypeVO[] originVOs = this
					.getTreeCardVOs(new ContracttypeVO[] { vo });
			processer.before(new ContracttypeVO[] { vo });
			VOUpdate<ContracttypeVO> upd = new VOUpdate<ContracttypeVO>();
			ContracttypeVO[] superVOs = upd.update(new ContracttypeVO[] { vo },
					originVOs);
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	private ContracttypeVO[] getTreeCardVOs(ContracttypeVO[] vos) {
		String[] ids = this.getIDS(vos);
		VOQuery<ContracttypeVO> query = new VOQuery<ContracttypeVO>(ContracttypeVO.class);
		return query.query(ids);
	}

	private String[] getIDS(ContracttypeVO[] vos) {
		int size = vos.length;
		String[] ids = new String[size];
		for (int i = 0; i < size; i++) {
			ids[i] = vos[i].getPrimaryKey();
		}
		return ids;
	}

	// 查询方法
	public ContracttypeVO[] querytreeinfo(String whereSql) throws BusinessException {
		VOQuery<ContracttypeVO> query = new VOQuery<ContracttypeVO>(ContracttypeVO.class);
		return query.query(whereSql, null);
	}
}