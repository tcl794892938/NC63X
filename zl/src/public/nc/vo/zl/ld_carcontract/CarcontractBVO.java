package nc.vo.zl.ld_carcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CarcontractBVO extends SuperVO {
	/**
	 * ��ϵ�绰
	 */
	public String phonenum;
	/**
	 * �ϲ㵥������
	 */
	public String pk_carcontract;
	/**
	 * ������Ϣ����
	 */
	public String pk_carcontract_b;
	/**
	 * �ͻ�
	 */
	public String pk_customer;
	/**
	 * ���ƺ�
	 */
	public String platenum;
	/**
	 * ��ע
	 */
	public String remark;
	/**
	 * �к�
	 */
	public String rowno;
	/**
	 * ʱ���
	 */
	public UFDateTime ts;
	/**
	 * �Զ�����1
	 */
	public String vdef1;
	/**
	 * �Զ�����2
	 */
	public String vdef2;
	/**
	 * �Զ�����3
	 */
	public String vdef3;
	/**
	 * �Ƿ�ɾ��
	 */
	public Integer dr=0;

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * ��ȡ��ϵ�绰
	 * 
	 * @return ��ϵ�绰
	 */
	public String getPhonenum() {
		return this.phonenum;
	}

	/**
	 * ������ϵ�绰
	 * 
	 * @param phonenum
	 *            ��ϵ�绰
	 */
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getPk_carcontract() {
		return this.pk_carcontract;
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param pk_carcontract
	 *            �ϲ㵥������
	 */
	public void setPk_carcontract(String pk_carcontract) {
		this.pk_carcontract = pk_carcontract;
	}

	/**
	 * ��ȡ������Ϣ����
	 * 
	 * @return ������Ϣ����
	 */
	public String getPk_carcontract_b() {
		return this.pk_carcontract_b;
	}

	/**
	 * ���û�����Ϣ����
	 * 
	 * @param pk_carcontract_b
	 *            ������Ϣ����
	 */
	public void setPk_carcontract_b(String pk_carcontract_b) {
		this.pk_carcontract_b = pk_carcontract_b;
	}

	/**
	 * ��ȡ�ͻ�
	 * 
	 * @return �ͻ�
	 */
	public String getPk_customer() {
		return this.pk_customer;
	}

	/**
	 * ���ÿͻ�
	 * 
	 * @param pk_customer
	 *            �ͻ�
	 */
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}

	/**
	 * ��ȡ���ƺ�
	 * 
	 * @return ���ƺ�
	 */
	public String getPlatenum() {
		return this.platenum;
	}

	/**
	 * ���ó��ƺ�
	 * 
	 * @param platenum
	 *            ���ƺ�
	 */
	public void setPlatenum(String platenum) {
		this.platenum = platenum;
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return ��ע
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param remark
	 *            ��ע
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ��ȡ�к�
	 * 
	 * @return �к�
	 */
	public String getRowno() {
		return this.rowno;
	}

	/**
	 * �����к�
	 * 
	 * @param rowno
	 *            �к�
	 */
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	/**
	 * ��ȡ�Զ�����1
	 * 
	 * @return �Զ�����1
	 */
	public String getVdef1() {
		return this.vdef1;
	}

	/**
	 * �����Զ�����1
	 * 
	 * @param vdef1
	 *            �Զ�����1
	 */
	public void setVdef1(String vdef1) {
		this.vdef1 = vdef1;
	}

	/**
	 * ��ȡ�Զ�����2
	 * 
	 * @return �Զ�����2
	 */
	public String getVdef2() {
		return this.vdef2;
	}

	/**
	 * �����Զ�����2
	 * 
	 * @param vdef2
	 *            �Զ�����2
	 */
	public void setVdef2(String vdef2) {
		this.vdef2 = vdef2;
	}

	/**
	 * ��ȡ�Զ�����3
	 * 
	 * @return �Զ�����3
	 */
	public String getVdef3() {
		return this.vdef3;
	}

	/**
	 * �����Զ�����3
	 * 
	 * @param vdef3
	 *            �Զ�����3
	 */
	public void setVdef3(String vdef3) {
		this.vdef3 = vdef3;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("zl.carcontract_b");
	}
}