package nc.vo.zl.ld_kpregister;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KpregisterVO extends SuperVO {
/**
*�����
*/
public String checkman;
/**
*�������
*/
public String checkpy;
/**
*���ʱ��
*/
public UFDateTime checktime;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*��Ʊ��
*/
public String kpcode;
/**
*��Ʊ����
*/
public UFDate kpdate;
/**
*��Ʊ��
*/
public String kpman;
/**
*��Ʊ���
*/
public UFDouble kpmoney;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��������
*/
public String pk_billtype;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*����
*/
public String pk_group;
/**
*����
*/
public String pk_kpregister;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillcode;
/**
*����״̬
*/
public Integer vbillstatus;
/**
*�������ͱ���
*/
public String vbilltypecode;
/**
*�Զ�����1
*/
public String vdef1;
/**
*�Զ�����10
*/
public String vdef10;
/**
*�Զ�����2
*/
public String vdef2;
/**
*�Զ�����3
*/
public String vdef3;
/**
*�Զ�����4
*/
public String vdef4;
/**
*�Զ�����5
*/
public String vdef5;
/**
*�Զ�����6
*/
public String vdef6;
/**
*�Զ�����7
*/
public String vdef7;
/**
*�Զ�����8
*/
public String vdef8;
/**
*�Զ�����9
*/
public String vdef9;
/**
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;
/**
 * �Ƿ���Ҫ�����ƽ̨
 */
public UFBoolean is_needkjpt;
/**
 * �Ƿ��Ѵ����ƽ̨
 */
public UFBoolean is_kjpt;

public UFBoolean getIs_needkjpt() {
	return is_needkjpt;
}

public void setIs_needkjpt(UFBoolean is_needkjpt) {
	this.is_needkjpt = is_needkjpt;
}

public UFBoolean getIs_kjpt() {
	return is_kjpt;
}

public void setIs_kjpt(UFBoolean is_kjpt) {
	this.is_kjpt = is_kjpt;
}

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
 * ��Ŀ
 */
public String pk_project;

public String getPk_project() {
	return pk_project;
}

public void setPk_project(String pk_project) {
	this.pk_project = pk_project;
}

/** 
* ��ȡ�����
*
* @return �����
*/
public String getCheckman () {
return this.checkman;
 } 

/** 
* ���������
*
* @param checkman �����
*/
public void setCheckman ( String checkman) {
this.checkman=checkman;
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public String getCheckpy () {
return this.checkpy;
 } 

/** 
* �����������
*
* @param checkpy �������
*/
public void setCheckpy ( String checkpy) {
this.checkpy=checkpy;
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getChecktime () {
return this.checktime;
 } 

/** 
* �������ʱ��
*
* @param checktime ���ʱ��
*/
public void setChecktime ( UFDateTime checktime) {
this.checktime=checktime;
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getCreationtime () {
return this.creationtime;
 } 

/** 
* ���ô���ʱ��
*
* @param creationtime ����ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getCreator () {
return this.creator;
 } 

/** 
* ���ô�����
*
* @param creator ������
*/
public void setCreator ( String creator) {
this.creator=creator;
 } 

/** 
* ��ȡ�Ƶ�����
*
* @return �Ƶ�����
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* �����Ƶ�����
*
* @param dbilldate �Ƶ�����
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* ��ȡ��Ʊ��
*
* @return ��Ʊ��
*/
public String getKpcode () {
return this.kpcode;
 } 

/** 
* ���ÿ�Ʊ��
*
* @param kpcode ��Ʊ��
*/
public void setKpcode ( String kpcode) {
this.kpcode=kpcode;
 } 

/** 
* ��ȡ��Ʊ����
*
* @return ��Ʊ����
*/
public UFDate getKpdate () {
return this.kpdate;
 } 

/** 
* ���ÿ�Ʊ����
*
* @param kpdate ��Ʊ����
*/
public void setKpdate ( UFDate kpdate) {
this.kpdate=kpdate;
 } 

/** 
* ��ȡ��Ʊ��
*
* @return ��Ʊ��
*/
public String getKpman () {
return this.kpman;
 } 

/** 
* ���ÿ�Ʊ��
*
* @param kpman ��Ʊ��
*/
public void setKpman ( String kpman) {
this.kpman=kpman;
 } 

/** 
* ��ȡ��Ʊ���
*
* @return ��Ʊ���
*/
public UFDouble getKpmoney () {
return this.kpmoney;
 } 

/** 
* ���ÿ�Ʊ���
*
* @param kpmoney ��Ʊ���
*/
public void setKpmoney ( UFDouble kpmoney) {
this.kpmoney=kpmoney;
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return this.modifiedtime;
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return this.modifier;
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_billtype () {
return this.pk_billtype;
 } 

/** 
* ���õ�������
*
* @param pk_billtype ��������
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* ���ÿͻ�����
*
* @param pk_customer �ͻ�����
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return this.pk_group;
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_kpregister () {
return this.pk_kpregister;
 } 

/** 
* ��������
*
* @param pk_kpregister ����
*/
public void setPk_kpregister ( String pk_kpregister) {
this.pk_kpregister=pk_kpregister;
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* ��ȡ��֯�汾
*
* @return ��֯�汾
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* ������֯�汾
*
* @param pk_org_v ��֯�汾
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return this.ts;
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.ts=ts;
 } 

/** 
* ��ȡ���ݺ�
*
* @return ���ݺ�
*/
public String getVbillcode () {
return this.vbillcode;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillcode ���ݺ�
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getVbillstatus () {
return this.vbillstatus;
 } 

/** 
* ���õ���״̬
*
* @param vbillstatus ����״̬
* @see String
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
 } 

/** 
* ��ȡ�������ͱ���
*
* @return �������ͱ���
*/
public String getVbilltypecode () {
return this.vbilltypecode;
 } 

/** 
* ���õ������ͱ���
*
* @param vbilltypecode �������ͱ���
*/
public void setVbilltypecode ( String vbilltypecode) {
this.vbilltypecode=vbilltypecode;
 } 

/** 
* ��ȡ�Զ�����1
*
* @return �Զ�����1
*/
public String getVdef1 () {
return this.vdef1;
 } 

/** 
* �����Զ�����1
*
* @param vdef1 �Զ�����1
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return this.vdef10;
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.vdef10=vdef10;
 } 

/** 
* ��ȡ�Զ�����2
*
* @return �Զ�����2
*/
public String getVdef2 () {
return this.vdef2;
 } 

/** 
* �����Զ�����2
*
* @param vdef2 �Զ�����2
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
 } 

/** 
* ��ȡ�Զ�����3
*
* @return �Զ�����3
*/
public String getVdef3 () {
return this.vdef3;
 } 

/** 
* �����Զ�����3
*
* @param vdef3 �Զ�����3
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
 } 

/** 
* ��ȡ�Զ�����4
*
* @return �Զ�����4
*/
public String getVdef4 () {
return this.vdef4;
 } 

/** 
* �����Զ�����4
*
* @param vdef4 �Զ�����4
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
 } 

/** 
* ��ȡ�Զ�����5
*
* @return �Զ�����5
*/
public String getVdef5 () {
return this.vdef5;
 } 

/** 
* �����Զ�����5
*
* @param vdef5 �Զ�����5
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
 } 

/** 
* ��ȡ�Զ�����6
*
* @return �Զ�����6
*/
public String getVdef6 () {
return this.vdef6;
 } 

/** 
* �����Զ�����6
*
* @param vdef6 �Զ�����6
*/
public void setVdef6 ( String vdef6) {
this.vdef6=vdef6;
 } 

/** 
* ��ȡ�Զ�����7
*
* @return �Զ�����7
*/
public String getVdef7 () {
return this.vdef7;
 } 

/** 
* �����Զ�����7
*
* @param vdef7 �Զ�����7
*/
public void setVdef7 ( String vdef7) {
this.vdef7=vdef7;
 } 

/** 
* ��ȡ�Զ�����8
*
* @return �Զ�����8
*/
public String getVdef8 () {
return this.vdef8;
 } 

/** 
* �����Զ�����8
*
* @param vdef8 �Զ�����8
*/
public void setVdef8 ( String vdef8) {
this.vdef8=vdef8;
 } 

/** 
* ��ȡ�Զ�����9
*
* @return �Զ�����9
*/
public String getVdef9 () {
return this.vdef9;
 } 

/** 
* �����Զ�����9
*
* @param vdef9 �Զ�����9
*/
public void setVdef9 ( String vdef9) {
this.vdef9=vdef9;
 } 

/** 
* ��ȡ��Դ����id
*
* @return ��Դ����id
*/
public String getVsrcid () {
return this.vsrcid;
 } 

/** 
* ������Դ����id
*
* @param vsrcid ��Դ����id
*/
public void setVsrcid ( String vsrcid) {
this.vsrcid=vsrcid;
 } 

/** 
* ��ȡ��Դ��������
*
* @return ��Դ��������
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* ������Դ��������
*
* @param vsrctype ��Դ��������
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.kpregister");
  }
}