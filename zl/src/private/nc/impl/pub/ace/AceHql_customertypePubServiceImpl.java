package nc.impl.pub.ace;

import nc.vo.zl.hql_customertype.CustomertypeVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_customertypePubServiceImpl {

	// 增加方法
	public CustomertypeVO inserttreeinfo(CustomertypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CustomertypeVO> processer = new AroundProcesser<CustomertypeVO>(
					null);
			processer.before(new CustomertypeVO[] { vo });
			VOInsert<CustomertypeVO> ins = new VOInsert<CustomertypeVO>();
			CustomertypeVO[] superVOs = ins.insert(new CustomertypeVO[] { vo });
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除方法
	public void deletetreeinfo(CustomertypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CustomertypeVO> processer = new AroundProcesser<CustomertypeVO>(
					null);
			processer.before(new CustomertypeVO[] { vo });
			VODelete<CustomertypeVO> voDel = new VODelete<CustomertypeVO>();
			voDel.delete(new CustomertypeVO[] { vo });
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// 修改方法
	public CustomertypeVO updatetreeinfo(CustomertypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CustomertypeVO> processer = new AroundProcesser<CustomertypeVO>(
					null);
			CustomertypeVO[] originVOs = this
					.getTreeCardVOs(new CustomertypeVO[] { vo });
			processer.before(new CustomertypeVO[] { vo });
			VOUpdate<CustomertypeVO> upd = new VOUpdate<CustomertypeVO>();
			CustomertypeVO[] superVOs = upd.update(new CustomertypeVO[] { vo },
					originVOs);
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	private CustomertypeVO[] getTreeCardVOs(CustomertypeVO[] vos) {
		String[] ids = this.getIDS(vos);
		VOQuery<CustomertypeVO> query = new VOQuery<CustomertypeVO>(CustomertypeVO.class);
		return query.query(ids);
	}

	private String[] getIDS(CustomertypeVO[] vos) {
		int size = vos.length;
		String[] ids = new String[size];
		for (int i = 0; i < size; i++) {
			ids[i] = vos[i].getPrimaryKey();
		}
		return ids;
	}

	// 查询方法
	public CustomertypeVO[] querytreeinfo(String whereSql) throws BusinessException {
		VOQuery<CustomertypeVO> query = new VOQuery<CustomertypeVO>(CustomertypeVO.class);
		return query.query(whereSql, null);
	}
}