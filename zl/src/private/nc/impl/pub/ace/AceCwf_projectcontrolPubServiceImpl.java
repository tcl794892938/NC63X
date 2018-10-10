package nc.impl.pub.ace;

import nc.bs.zl.cwf_projectcontrol.ace.bp.AceCwf_projectcontrolInsertBP;
import nc.bs.zl.cwf_projectcontrol.ace.bp.AceCwf_projectcontrolUpdateBP;
import nc.bs.zl.cwf_projectcontrol.ace.bp.AceCwf_projectcontrolDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;

public abstract class AceCwf_projectcontrolPubServiceImpl {
	// ����
	public AggProjectcontrol[] pubinsertBills(AggProjectcontrol[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggProjectcontrol> transferTool = new BillTransferTool<AggProjectcontrol>(
					vos);
			AggProjectcontrol[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceCwf_projectcontrolInsertBP action = new AceCwf_projectcontrolInsertBP();
			AggProjectcontrol[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggProjectcontrol[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggProjectcontrol> transferTool = new BillTransferTool<AggProjectcontrol>(
					vos);
			AggProjectcontrol[] fullBills = transferTool.getClientFullInfoBill();
			AceCwf_projectcontrolDeleteBP deleteBP = new AceCwf_projectcontrolDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggProjectcontrol[] pubupdateBills(AggProjectcontrol[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggProjectcontrol> transTool = new BillTransferTool<AggProjectcontrol>(
					vos);
			// ��ȫǰ̨VO
			AggProjectcontrol[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggProjectcontrol[] originBills = transTool.getOriginBills();
			// ����BP
			AceCwf_projectcontrolUpdateBP bp = new AceCwf_projectcontrolUpdateBP();
			AggProjectcontrol[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggProjectcontrol[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggProjectcontrol[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggProjectcontrol> query = new BillLazyQuery<AggProjectcontrol>(
					AggProjectcontrol.class);
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