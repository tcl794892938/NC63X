package nc.impl.pub.ace;

import nc.vo.zl.ld_formattype.FormattypeVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_formattypePubServiceImpl {

	// 增加方法
	public FormattypeVO inserttreeinfo(FormattypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<FormattypeVO> processer = new AroundProcesser<FormattypeVO>(
					null);
			processer.before(new FormattypeVO[] { vo });
			VOInsert<FormattypeVO> ins = new VOInsert<FormattypeVO>();
			FormattypeVO[] superVOs = ins.insert(new FormattypeVO[] { vo });
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除方法
	public void deletetreeinfo(FormattypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<FormattypeVO> processer = new AroundProcesser<FormattypeVO>(
					null);
			processer.before(new FormattypeVO[] { vo });
			VODelete<FormattypeVO> voDel = new VODelete<FormattypeVO>();
			voDel.delete(new FormattypeVO[] { vo });
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// 修改方法
	public FormattypeVO updatetreeinfo(FormattypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<FormattypeVO> processer = new AroundProcesser<FormattypeVO>(
					null);
			FormattypeVO[] originVOs = this
					.getTreeCardVOs(new FormattypeVO[] { vo });
			processer.before(new FormattypeVO[] { vo });
			VOUpdate<FormattypeVO> upd = new VOUpdate<FormattypeVO>();
			FormattypeVO[] superVOs = upd.update(new FormattypeVO[] { vo },
					originVOs);
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	private FormattypeVO[] getTreeCardVOs(FormattypeVO[] vos) {
		String[] ids = this.getIDS(vos);
		VOQuery<FormattypeVO> query = new VOQuery<FormattypeVO>(FormattypeVO.class);
		return query.query(ids);
	}

	private String[] getIDS(FormattypeVO[] vos) {
		int size = vos.length;
		String[] ids = new String[size];
		for (int i = 0; i < size; i++) {
			ids[i] = vos[i].getPrimaryKey();
		}
		return ids;
	}

	// 查询方法
	public FormattypeVO[] querytreeinfo(String whereSql) throws BusinessException {
		VOQuery<FormattypeVO> query = new VOQuery<FormattypeVO>(FormattypeVO.class);
		return query.query(whereSql, null);
	}
}