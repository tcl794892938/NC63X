package nc.bs.zl.hql_jt_acceptance.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * ��׼���ݵ���չ�����
 * 
 */
public enum Hql_jt_acceptancePluginPoint implements IPluginPoint {
	/**
	 * ����
	 */
	APPROVE,
	/**
	 * ����
	 */
	SEND_APPROVE,

	/**
	 * ȡ�����
	 */
	UNAPPROVE,

	/**
	 * �ջ�
	 */
	UNSEND_APPROVE,
	/**
	 * ɾ��
	 */
	DELETE,
	/**
	 * ����
	 */
	INSERT,
	/**
	 * ����
	 */
	UPDATE,
	/**
	 * �ű�ɾ��
	 */
	SCRIPT_DELETE,
	/**
	 * �ű�����
	 */
	SCRIPT_INSERT,
	/**
	 * �ű�����
	 */
	SCRIPT_UPDATE;

	@Override
	public String getComponent() {
		return "zl";
	}

	@Override
	public String getModule() {
		return "zl";
	}

	@Override
	public String getPoint() {
		return this.getClass().getName() + "." + this.name();
	}

}
