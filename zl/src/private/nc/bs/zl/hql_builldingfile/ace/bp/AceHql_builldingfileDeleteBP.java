package nc.bs.zl.hql_builldingfile.ace.bp;

import nc.bs.zl.hql_builldingfile.plugin.bpplugin.Hql_builldingfilePluginPoint;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHql_builldingfileDeleteBP {

	public void delete(AggBuildingfileVO[] bills) {

		DeleteBPTemplate<AggBuildingfileVO> bp = new DeleteBPTemplate<AggBuildingfileVO>(
				Hql_builldingfilePluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggBuildingfileVO> processer) {
//		// TODO ǰ����
//		IRule<AggBuildingfileVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBuildingfileVO> processer) {
		// TODO �����

	}
}
