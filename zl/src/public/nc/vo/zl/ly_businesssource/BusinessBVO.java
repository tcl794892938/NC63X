package nc.vo.zl.ly_businesssource;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BusinessBVO extends SuperVO {
	/**
	 * �Ƿ�ɾ��
	 */
	public Integer dr=0;
	/**
	 * �ϲ㵥������
	 */
	public String pk_bussiness;
	/**
	 * ����
	 */
	public String pk_bussiness_b;
	/**
	 * ��Ŀ����
	 */
	public String procode;
	/**
	 * ��Ŀ����
	 */
	public String proname;
	/**
	 * ��ע
	 */
	public String remarks;
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
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getPk_bussiness() {
		return this.pk_bussiness;
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param pk_bussiness
	 *            �ϲ㵥������
	 */
	public void setPk_bussiness(String pk_bussiness) {
		this.pk_bussiness = pk_bussiness;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_bussiness_b() {
		return this.pk_bussiness_b;
	}

	/**
	 * ��������
	 * 
	 * @param pk_bussiness_b
	 *            ����
	 */
	public void setPk_bussiness_b(String pk_bussiness_b) {
		this.pk_bussiness_b = pk_bussiness_b;
	}

	/**
	 * ��ȡ��Ŀ����
	 * 
	 * @return ��Ŀ����
	 */
	public String getProcode() {
		return this.procode;
	}

	/**
	 * ������Ŀ����
	 * 
	 * @param procode
	 *            ��Ŀ����
	 */
	public void setProcode(String procode) {
		this.procode = procode;
	}

	/**
	 * ��ȡ��Ŀ����
	 * 
	 * @return ��Ŀ����
	 */
	public String getProname() {
		return this.proname;
	}

	/**
	 * ������Ŀ����
	 * 
	 * @param proname
	 *            ��Ŀ����
	 */
	public void setProname(String proname) {
		this.proname = proname;
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return ��ע
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param remarks
	 *            ��ע
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("zl.business_b");
	}
}