package nc.impl.pub.ace;

import nc.bs.zl.base_project.ace.bp.AceBase_projectInsertBP;
import nc.bs.zl.base_project.ace.bp.AceBase_projectUpdateBP;
import nc.bs.zl.base_project.ace.bp.AceBase_projectDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.base_project.AggProjectVO;

public abstract class AceBase_projectPubServiceImpl {
	// ����
	public AggProjectVO[] pubinsertBills(AggProjectVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggProjectVO> transferTool = new BillTransferTool<AggProjectVO>(
					vos);
			AggProjectVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceBase_projectInsertBP action = new AceBase_projectInsertBP();
			AggProjectVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggProjectVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggProjectVO> transferTool = new BillTransferTool<AggProjectVO>(
					vos);
			AggProjectVO[] fullBills = transferTool.getClientFullInfoBill();
			AceBase_projectDeleteBP deleteBP = new AceBase_projectDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggProjectVO[] pubupdateBills(AggProjectVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggProjectVO> transTool = new BillTransferTool<AggProjectVO>(
					vos);
			// ��ȫǰ̨VO
			AggProjectVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggProjectVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceBase_projectUpdateBP bp = new AceBase_projectUpdateBP();
			AggProjectVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggProjectVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggProjectVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggProjectVO> query = new BillLazyQuery<AggProjectVO>(
					AggProjectVO.class);
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