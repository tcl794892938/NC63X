package nc.impl.pub.ace;

import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceCostprojectPubServiceImpl {

	// 增加方法
	public CostprojectVO inserttreeinfo(CostprojectVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CostprojectVO> processer = new AroundProcesser<CostprojectVO>(
					null);
			processer.before(new CostprojectVO[] { vo });
			VOInsert<CostprojectVO> ins = new VOInsert<CostprojectVO>();
			CostprojectVO[] superVOs = ins.insert(new CostprojectVO[] { vo });
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除方法
	public void deletetreeinfo(CostprojectVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CostprojectVO> processer = new AroundProcesser<CostprojectVO>(
					null);
			processer.before(new CostprojectVO[] { vo });
			VODelete<CostprojectVO> voDel = new VODelete<CostprojectVO>();
			voDel.delete(new CostprojectVO[] { vo });
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// 修改方法
	public CostprojectVO updatetreeinfo(CostprojectVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CostprojectVO> processer = new AroundProcesser<CostprojectVO>(
					null);
			CostprojectVO[] originVOs = this
					.getTreeCardVOs(new CostprojectVO[] { vo });
			processer.before(new CostprojectVO[] { vo });
			VOUpdate<CostprojectVO> upd = new VOUpdate<CostprojectVO>();
			CostprojectVO[] superVOs = upd.update(new CostprojectVO[] { vo },
					originVOs);
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	private CostprojectVO[] getTreeCardVOs(CostprojectVO[] vos) {
		String[] ids = this.getIDS(vos);
		VOQuery<CostprojectVO> query = new VOQuery<CostprojectVO>(CostprojectVO.class);
		return query.query(ids);
	}

	private String[] getIDS(CostprojectVO[] vos) {
		int size = vos.length;
		String[] ids = new String[size];
		for (int i = 0; i < size; i++) {
			ids[i] = vos[i].getPrimaryKey();
		}
		return ids;
	}

	// 查询方法
	public CostprojectVO[] querytreeinfo(String whereSql) throws BusinessException {
		VOQuery<CostprojectVO> query = new VOQuery<CostprojectVO>(CostprojectVO.class);
		return query.query(whereSql, null);
	}
}