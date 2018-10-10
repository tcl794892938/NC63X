package nc.vo.zl.ly_hflist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HflistVO extends SuperVO {
	/**
	 * �ӵ�����
	 */
	public String acceptbm;
	/**
	 * �ӵ���
	 */
	public String acceptman;
	/**
	 * �������ͱ���
	 */
	public String billtypecode;
	/**
	 * ��������
	 */
	public String bxcontent;
	/**
	 * ��������
	 */
	public UFDate bxdate;
	/**
	 * �����
	 */
	public String checkman;
	/**
	 * �������
	 */
	public String checkpy;
	/**
	 * ���ʱ��
	 */
	public UFDateTime checktime;
	/**
	 * ��Դ��������
	 */
	public String clisttype;
	/**
	 * ����ʱ��
	 */
	public UFDateTime creationtime;
	/**
	 * ������
	 */
	public String creator;
	/**
	 * �Ƶ�����
	 */
	public UFDate dbilldate = AppContext.getInstance().getBusiDate();
	/**
	 * ��������
	 */
	public String fcname;
	/**
	 * �ͻ�����
	 */
	public String khname;
	/**
	 * ��������
	 */
	public UFDate listdate;
	/**
	 * ���ݱ��
	 */
	public String listid;
	/**
	 * ����״̬
	 */
	public Integer liststate;
	/**
	 * ��ϵ�绰
	 */
	public String lxphone;
	/**
	 * �޸�ʱ��
	 */
	public UFDateTime modifiedtime;
	/**
	 * �޸���
	 */
	public String modifier;
	/**
	 * ��������
	 */
	public String pk_billtype;
	/**
	 * ��Դ����id
	 */
	public String pk_clist;
	/**
	 * ����
	 */
	public String pk_group;
	/**
	 * ����
	 */
	public String pk_hflist;
	/**
	 * ��֯
	 */
	public String pk_org;
	/**
	 * ��֯�汾
	 */
	public String pk_org_v;
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
	/**
	 * �Ƿ�ɾ��
	 */
	public Integer dr = 0;
	/**
	*��Ŀ��Ϣ
	*/
	public String pk_project;
	/**
	*¥��
	*/
	public String pk_building;

	public String getPk_project() {
		return pk_project;
	}

	public void setPk_project(String pk_project) {
		this.pk_project = pk_project;
	}

	public String getPk_building() {
		return pk_building;
	}

	public void setPk_building(String pk_building) {
		this.pk_building = pk_building;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * ��ȡ�ӵ�����
	 * 
	 * @return �ӵ�����
	 */
	public String getAcceptbm() {
		return this.acceptbm;
	}

	/**
	 * ���ýӵ�����
	 * 
	 * @param acceptbm
	 *            �ӵ�����
	 */
	public void setAcceptbm(String acceptbm) {
		this.acceptbm = acceptbm;
	}

	/**
	 * ��ȡ�ӵ���
	 * 
	 * @return �ӵ���
	 */
	public String getAcceptman() {
		return this.acceptman;
	}

	/**
	 * ���ýӵ���
	 * 
	 * @param acceptman
	 *            �ӵ���
	 */
	public void setAcceptman(String acceptman) {
		this.acceptman = acceptman;
	}

	/**
	 * ��ȡ�������ͱ���
	 * 
	 * @return �������ͱ���
	 */
	public String getBilltypecode() {
		return this.billtypecode;
	}

	/**
	 * ���õ������ͱ���
	 * 
	 * @param billtypecode
	 *            �������ͱ���
	 */
	public void setBilltypecode(String billtypecode) {
		this.billtypecode = billtypecode;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getBxcontent() {
		return this.bxcontent;
	}

	/**
	 * ���ñ�������
	 * 
	 * @param bxcontent
	 *            ��������
	 */
	public void setBxcontent(String bxcontent) {
		this.bxcontent = bxcontent;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public UFDate getBxdate() {
		return this.bxdate;
	}

	/**
	 * ���ñ�������
	 * 
	 * @param bxdate
	 *            ��������
	 */
	public void setBxdate(UFDate bxdate) {
		this.bxdate = bxdate;
	}

	/**
	 * ��ȡ�����
	 * 
	 * @return �����
	 */
	public String getCheckman() {
		return this.checkman;
	}

	/**
	 * ���������
	 * 
	 * @param checkman
	 *            �����
	 */
	public void setCheckman(String checkman) {
		this.checkman = checkman;
	}

	/**
	 * ��ȡ�������
	 * 
	 * @return �������
	 */
	public String getCheckpy() {
		return this.checkpy;
	}

	/**
	 * �����������
	 * 
	 * @param checkpy
	 *            �������
	 */
	public void setCheckpy(String checkpy) {
		this.checkpy = checkpy;
	}

	/**
	 * ��ȡ���ʱ��
	 * 
	 * @return ���ʱ��
	 */
	public UFDateTime getChecktime() {
		return this.checktime;
	}

	/**
	 * �������ʱ��
	 * 
	 * @param checktime
	 *            ���ʱ��
	 */
	public void setChecktime(UFDateTime checktime) {
		this.checktime = checktime;
	}

	/**
	 * ��ȡ��Դ��������
	 * 
	 * @return ��Դ��������
	 */
	public String getClisttype() {
		return this.clisttype;
	}

	/**
	 * ������Դ��������
	 * 
	 * @param clisttype
	 *            ��Դ��������
	 */
	public void setClisttype(String clisttype) {
		this.clisttype = clisttype;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ��
	 */
	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	/**
	 * ���ô���ʱ��
	 * 
	 * @param creationtime
	 *            ����ʱ��
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * ���ô�����
	 * 
	 * @param creator
	 *            ������
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * ��ȡ�Ƶ�����
	 * 
	 * @return �Ƶ�����
	 */
	public UFDate getDbilldate() {
		return this.dbilldate;
	}

	/**
	 * �����Ƶ�����
	 * 
	 * @param dbilldate
	 *            �Ƶ�����
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getFcname() {
		return this.fcname;
	}

	/**
	 * ���÷�������
	 * 
	 * @param fcname
	 *            ��������
	 */
	public void setFcname(String fcname) {
		this.fcname = fcname;
	}

	/**
	 * ��ȡ�ͻ�����
	 * 
	 * @return �ͻ�����
	 */
	public String getKhname() {
		return this.khname;
	}

	/**
	 * ���ÿͻ�����
	 * 
	 * @param khname
	 *            �ͻ�����
	 */
	public void setKhname(String khname) {
		this.khname = khname;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public UFDate getListdate() {
		return this.listdate;
	}

	/**
	 * ���õ�������
	 * 
	 * @param listdate
	 *            ��������
	 */
	public void setListdate(UFDate listdate) {
		this.listdate = listdate;
	}

	/**
	 * ��ȡ���ݱ��
	 * 
	 * @return ���ݱ��
	 */
	public String getListid() {
		return this.listid;
	}

	/**
	 * ���õ��ݱ��
	 * 
	 * @param listid
	 *            ���ݱ��
	 */
	public void setListid(String listid) {
		this.listid = listid;
	}

	/**
	 * ��ȡ����״̬
	 * 
	 * @return ����״̬
	 * @see String
	 */
	public Integer getListstate() {
		return this.liststate;
	}

	/**
	 * ���õ���״̬
	 * 
	 * @param liststate
	 *            ����״̬
	 * @see String
	 */
	public void setListstate(Integer liststate) {
		this.liststate = liststate;
	}

	/**
	 * ��ȡ��ϵ�绰
	 * 
	 * @return ��ϵ�绰
	 */
	public String getLxphone() {
		return this.lxphone;
	}

	/**
	 * ������ϵ�绰
	 * 
	 * @param lxphone
	 *            ��ϵ�绰
	 */
	public void setLxphone(String lxphone) {
		this.lxphone = lxphone;
	}

	/**
	 * ��ȡ�޸�ʱ��
	 * 
	 * @return �޸�ʱ��
	 */
	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	/**
	 * �����޸�ʱ��
	 * 
	 * @param modifiedtime
	 *            �޸�ʱ��
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * ��ȡ�޸���
	 * 
	 * @return �޸���
	 */
	public String getModifier() {
		return this.modifier;
	}

	/**
	 * �����޸���
	 * 
	 * @param modifier
	 *            �޸���
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getPk_billtype() {
		return this.pk_billtype;
	}

	/**
	 * ���õ�������
	 * 
	 * @param pk_billtype
	 *            ��������
	 */
	public void setPk_billtype(String pk_billtype) {
		this.pk_billtype = pk_billtype;
	}

	/**
	 * ��ȡ��Դ����id
	 * 
	 * @return ��Դ����id
	 */
	public String getPk_clist() {
		return this.pk_clist;
	}

	/**
	 * ������Դ����id
	 * 
	 * @param pk_clist
	 *            ��Դ����id
	 */
	public void setPk_clist(String pk_clist) {
		this.pk_clist = pk_clist;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * ���ü���
	 * 
	 * @param pk_group
	 *            ����
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_hflist() {
		return this.pk_hflist;
	}

	/**
	 * ��������
	 * 
	 * @param pk_hflist
	 *            ����
	 */
	public void setPk_hflist(String pk_hflist) {
		this.pk_hflist = pk_hflist;
	}

	/**
	 * ��ȡ��֯
	 * 
	 * @return ��֯
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * ������֯
	 * 
	 * @param pk_org
	 *            ��֯
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * ��ȡ��֯�汾
	 * 
	 * @return ��֯�汾
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * ������֯�汾
	 * 
	 * @param pk_org_v
	 *            ��֯�汾
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
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
		return VOMetaFactory.getInstance().getVOMeta("zl.hflist");
	}
}