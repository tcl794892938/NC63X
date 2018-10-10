package nc.impl.pub.ace;

import nc.bs.zl.hql_builldingfile.ace.bp.AceHql_builldingfileInsertBP;
import nc.bs.zl.hql_builldingfile.ace.bp.AceHql_builldingfileUpdateBP;
import nc.bs.zl.hql_builldingfile.ace.bp.AceHql_builldingfileDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

public abstract class AceHql_builldingfilePubServiceImpl {
	// ����
	public AggBuildingfileVO[] pubinsertBills(AggBuildingfileVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBuildingfileVO> transferTool = new BillTransferTool<AggBuildingfileVO>(
					vos);
			AggBuildingfileVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceHql_builldingfileInsertBP action = new AceHql_builldingfileInsertBP();
			AggBuildingfileVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBuildingfileVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggBuildingfileVO> transferTool = new BillTransferTool<AggBuildingfileVO>(
					vos);
			AggBuildingfileVO[] fullBills = transferTool.getClientFullInfoBill();
			AceHql_builldingfileDeleteBP deleteBP = new AceHql_builldingfileDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBuildingfileVO[] pubupdateBills(AggBuildingfileVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBuildingfileVO> transTool = new BillTransferTool<AggBuildingfileVO>(
					vos);
			// ��ȫǰ̨VO
			AggBuildingfileVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggBuildingfileVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceHql_builldingfileUpdateBP bp = new AceHql_builldingfileUpdateBP();
			AggBuildingfileVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBuildingfileVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBuildingfileVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBuildingfileVO> query = new BillLazyQuery<AggBuildingfileVO>(
					AggBuildingfileVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

}