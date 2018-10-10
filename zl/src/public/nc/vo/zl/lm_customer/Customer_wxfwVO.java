package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_wxfwVO extends SuperVO {
	/**
	 * ���µ���
	 */
	public String bsdj;
	/**
	 * ��������
	 */
	public String bsnr;
	/**
	 * ¥����
	 */
	public String buildnum;
	/**
	 * �ͻ�����
	 */
	public String customername;
	/**
	 * �ϲ㵥������
	 */
	public String pk_customer;
	/**
	 * ����
	 */
	public String pk_customerwxfw;
	/**
	 * ��������
	 */
	public String roomname;
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
	 * �Զ�����4
	 */
	public String vdef4;
	/**
	 * �Զ�����5
	 */
	public String vdef5;
	public Integer dr = 0;

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * ��ȡ���µ���
	 * 
	 * @return ���µ���
	 */
	public String getBsdj() {
		return this.bsdj;
	}

	/**
	 * ���ñ��µ���
	 * 
	 * @param bsdj
	 *            ���µ���
	 */
	public void setBsdj(String bsdj) {
		this.bsdj = bsdj;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getBsnr() {
		return this.bsnr;
	}

	/**
	 * ���ñ�������
	 * 
	 * @param bsnr
	 *            ��������
	 */
	public void setBsnr(String bsnr) {
		this.bsnr = bsnr;
	}

	/**
	 * ��ȡ¥����
	 * 
	 * @return ¥����
	 */
	public String getBuildnum() {
		return this.buildnum;
	}

	/**
	 * ����¥����
	 * 
	 * @param buildnum
	 *            ¥����
	 */
	public void setBuildnum(String buildnum) {
		this.buildnum = buildnum;
	}

	/**
	 * ��ȡ�ͻ�����
	 * 
	 * @return �ͻ�����
	 */
	public String getCustomername() {
		return this.customername;
	}

	/**
	 * ���ÿͻ�����
	 * 
	 * @param customername
	 *            �ͻ�����
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getPk_customer() {
		return this.pk_customer;
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param pk_customer
	 *            �ϲ㵥������
	 */
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_customerwxfw() {
		return this.pk_customerwxfw;
	}

	/**
	 * ��������
	 * 
	 * @param pk_customerwxfw
	 *            ����
	 */
	public void setPk_customerwxfw(String pk_customerwxfw) {
		this.pk_customerwxfw = pk_customerwxfw;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getRoomname() {
		return this.roomname;
	}

	/**
	 * ���÷�������
	 * 
	 * @param roomname
	 *            ��������
	 */
	public void setRoomname(String roomname) {
		this.roomname = roomname;
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

	/**
	 * ��ȡ�Զ�����4
	 * 
	 * @return �Զ�����4
	 */
	public String getVdef4() {
		return this.vdef4;
	}

	/**
	 * �����Զ�����4
	 * 
	 * @param vdef4
	 *            �Զ�����4
	 */
	public void setVdef4(String vdef4) {
		this.vdef4 = vdef4;
	}

	/**
	 * ��ȡ�Զ�����5
	 * 
	 * @return �Զ�����5
	 */
	public String getVdef5() {
		return this.vdef5;
	}

	/**
	 * �����Զ�����5
	 * 
	 * @param vdef5
	 *            �Զ�����5
	 */
	public void setVdef5(String vdef5) {
		this.vdef5 = vdef5;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("zl.customer_wxfw");
	}
}