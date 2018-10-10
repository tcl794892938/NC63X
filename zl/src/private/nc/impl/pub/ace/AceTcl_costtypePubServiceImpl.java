package nc.impl.pub.ace;

import nc.vo.zl.tcl_costtype.CosttypeVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceTcl_costtypePubServiceImpl {

	// 增加方法
	public CosttypeVO inserttreeinfo(CosttypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CosttypeVO> processer = new AroundProcesser<CosttypeVO>(
					null);
			processer.before(new CosttypeVO[] { vo });
			VOInsert<CosttypeVO> ins = new VOInsert<CosttypeVO>();
			CosttypeVO[] superVOs = ins.insert(new CosttypeVO[] { vo });
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除方法
	public void deletetreeinfo(CosttypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CosttypeVO> processer = new AroundProcesser<CosttypeVO>(
					null);
			processer.before(new CosttypeVO[] { vo });
			VODelete<CosttypeVO> voDel = new VODelete<CosttypeVO>();
			voDel.delete(new CosttypeVO[] { vo });
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// 修改方法
	public CosttypeVO updatetreeinfo(CosttypeVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<CosttypeVO> processer = new AroundProcesser<CosttypeVO>(
					null);
			CosttypeVO[] originVOs = this
					.getTreeCardVOs(new CosttypeVO[] { vo });
			processer.before(new CosttypeVO[] { vo });
			VOUpdate<CosttypeVO> upd = new VOUpdate<CosttypeVO>();
			CosttypeVO[] superVOs = upd.update(new CosttypeVO[] { vo },
					originVOs);
			return superVOs[0];
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	private CosttypeVO[] getTreeCardVOs(CosttypeVO[] vos) {
		String[] ids = this.getIDS(vos);
		VOQuery<CosttypeVO> query = new VOQuery<CosttypeVO>(CosttypeVO.class);
		return query.query(ids);
	}

	private String[] getIDS(CosttypeVO[] vos) {
		int size = vos.length;
		String[] ids = new String[size];
		for (int i = 0; i < size; i++) {
			ids[i] = vos[i].getPrimaryKey();
		}
		return ids;
	}

	// 查询方法
	public CosttypeVO[] querytreeinfo(String whereSql) throws BusinessException {
		VOQuery<CosttypeVO> query = new VOQuery<CosttypeVO>(CosttypeVO.class);
		return query.query(whereSql, " order by code ");
	}
}