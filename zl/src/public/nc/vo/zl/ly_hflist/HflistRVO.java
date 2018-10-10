package nc.vo.zl.ly_hflist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HflistRVO extends SuperVO {
	/**
	 * ������
	 */
	public String finishqk;
	/**
	 * ���ʱ��
	 */
	public UFDateTime finishtime;
	/**
	 * �ط�����
	 */
	public UFDate hfdate;
	/**
	 * �ط���Ա
	 */
	public String hfman;
	/**
	 * �ط�����
	 */
	public UFBoolean hfservice;
	/**
	 * �ط����
	 */
	public String hfsituation;
	/**
	 * �ϲ㵥������
	 */
	public String pk_hflist;
	/**
	 * ����
	 */
	public String pk_hflist_r;
	/**
	 * ά�޷�����Ա
	 */
	public String serviceman;
	/**
	 * ʱ���
	 */
	public UFDateTime ts;
	/**
	 * δ���ԭ��
	 */
	public String unfinishyy;
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
	/**
	 * �Ƿ�ɾ��
	 */
	public Integer dr = 0;
	/**
	*�к�
	*/
	public String rowno;

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public String getFinishqk() {
		return this.finishqk;
	}

	/**
	 * ����������
	 * 
	 * @param finishqk
	 *            ������
	 */
	public void setFinishqk(String finishqk) {
		this.finishqk = finishqk;
	}

	/**
	 * ��ȡ���ʱ��
	 * 
	 * @return ���ʱ��
	 */
	public UFDateTime getFinishtime() {
		return this.finishtime;
	}

	/**
	 * �������ʱ��
	 * 
	 * @param finishtime
	 *            ���ʱ��
	 */
	public void setFinishtime(UFDateTime finishtime) {
		this.finishtime = finishtime;
	}

	/**
	 * ��ȡ�ط�����
	 * 
	 * @return �ط�����
	 */
	public UFDate getHfdate() {
		return this.hfdate;
	}

	/**
	 * ���ûط�����
	 * 
	 * @param hfdate
	 *            �ط�����
	 */
	public void setHfdate(UFDate hfdate) {
		this.hfdate = hfdate;
	}

	/**
	 * ��ȡ�ط���Ա
	 * 
	 * @return �ط���Ա
	 */
	public String getHfman() {
		return this.hfman;
	}

	/**
	 * ���ûط���Ա
	 * 
	 * @param hfman
	 *            �ط���Ա
	 */
	public void setHfman(String hfman) {
		this.hfman = hfman;
	}

	/**
	 * ��ȡ�ط�����
	 * 
	 * @return �ط�����
	 */
	public UFBoolean getHfservice() {
		return this.hfservice;
	}

	/**
	 * ���ûط�����
	 * 
	 * @param hfservice
	 *            �ط�����
	 */
	public void setHfservice(UFBoolean hfservice) {
		this.hfservice = hfservice;
	}

	/**
	 * ��ȡ�ط����
	 * 
	 * @return �ط����
	 */
	public String getHfsituation() {
		return this.hfsituation;
	}

	/**
	 * ���ûط����
	 * 
	 * @param hfsituation
	 *            �ط����
	 */
	public void setHfsituation(String hfsituation) {
		this.hfsituation = hfsituation;
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getPk_hflist() {
		return this.pk_hflist;
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param pk_hflist
	 *            �ϲ㵥������
	 */
	public void setPk_hflist(String pk_hflist) {
		this.pk_hflist = pk_hflist;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_hflist_r() {
		return this.pk_hflist_r;
	}

	/**
	 * ��������
	 * 
	 * @param pk_hflist_r
	 *            ����
	 */
	public void setPk_hflist_r(String pk_hflist_r) {
		this.pk_hflist_r = pk_hflist_r;
	}

	/**
	 * ��ȡά�޷�����Ա
	 * 
	 * @return ά�޷�����Ա
	 */
	public String getServiceman() {
		return this.serviceman;
	}

	/**
	 * ����ά�޷�����Ա
	 * 
	 * @param serviceman
	 *            ά�޷�����Ա
	 */
	public void setServiceman(String serviceman) {
		this.serviceman = serviceman;
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
	 * ��ȡδ���ԭ��
	 * 
	 * @return δ���ԭ��
	 */
	public String getUnfinishyy() {
		return this.unfinishyy;
	}

	/**
	 * ����δ���ԭ��
	 * 
	 * @param unfinishyy
	 *            δ���ԭ��
	 */
	public void setUnfinishyy(String unfinishyy) {
		this.unfinishyy = unfinishyy;
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
		return VOMetaFactory.getInstance().getVOMeta("zl.hflist_r");
	}
}