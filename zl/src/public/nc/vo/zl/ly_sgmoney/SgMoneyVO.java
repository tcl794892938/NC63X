package nc.vo.zl.ly_sgmoney;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SgMoneyVO extends SuperVO {
/**
*�������ͱ���
*/
public String billtypecode;
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
*��������
*/
public String fcname;
/**
*�ͻ�����
*/
public String khname;
/**
*���ݱ��
*/
public String listid;
/**
*����״̬
*/
public Integer liststate;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*�շ���Ŀ
*/
public String payproject;
/**
*��������
*/
public String pk_billtype;
/**
*����
*/
public String pk_group;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*��Ŀ��Ϣ
*/
public String pk_project;
/**
*����
*/
public String pk_sgmoney;
/**
*Ԥ�ɿ����
*/
public UFDouble restmoney;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*�Զ�����3
*/
public String vde3;
/**
*�Զ�����1
*/
public String vdef1;
/**
*�Զ�����2
*/
public String vdef2;
/**
*�Զ�����4
*/
public String vdef4;
/**
*�Զ�����5
*/
public String vdef5;
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
* ��ȡ�������ͱ���
*
* @return �������ͱ���
*/
public String getBilltypecode () {
return this.billtypecode;
 } 

/** 
* ���õ������ͱ���
*
* @param billtypecode �������ͱ���
*/
public void setBilltypecode ( String billtypecode) {
this.billtypecode=billtypecode;
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
* ��ȡ��������
*
* @return ��������
*/
public String getFcname () {
return this.fcname;
 } 

/** 
* ���÷�������
*
* @param fcname ��������
*/
public void setFcname ( String fcname) {
this.fcname=fcname;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getKhname () {
return this.khname;
 } 

/** 
* ���ÿͻ�����
*
* @param khname �ͻ�����
*/
public void setKhname ( String khname) {
this.khname=khname;
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getListid () {
return this.listid;
 } 

/** 
* ���õ��ݱ��
*
* @param listid ���ݱ��
*/
public void setListid ( String listid) {
this.listid=listid;
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getListstate () {
return this.liststate;
 } 

/** 
* ���õ���״̬
*
* @param liststate ����״̬
* @see String
*/
public void setListstate ( Integer liststate) {
this.liststate=liststate;
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
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPayproject () {
return this.payproject;
 } 

/** 
* �����շ���Ŀ
*
* @param payproject �շ���Ŀ
*/
public void setPayproject ( String payproject) {
this.payproject=payproject;
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
* ��ȡ��Ŀ��Ϣ
*
* @return ��Ŀ��Ϣ
*/
public String getPk_project () {
return this.pk_project;
 } 

/** 
* ������Ŀ��Ϣ
*
* @param pk_project ��Ŀ��Ϣ
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_sgmoney () {
return this.pk_sgmoney;
 } 

/** 
* ��������
*
* @param pk_sgmoney ����
*/
public void setPk_sgmoney ( String pk_sgmoney) {
this.pk_sgmoney=pk_sgmoney;
 } 

/** 
* ��ȡԤ�ɿ����
*
* @return Ԥ�ɿ����
*/
public UFDouble getRestmoney () {
return this.restmoney;
 } 

/** 
* ����Ԥ�ɿ����
*
* @param restmoney Ԥ�ɿ����
*/
public void setRestmoney ( UFDouble restmoney) {
this.restmoney=restmoney;
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
* ��ȡ�Զ�����3
*
* @return �Զ�����3
*/
public String getVde3 () {
return this.vde3;
 } 

/** 
* �����Զ�����3
*
* @param vde3 �Զ�����3
*/
public void setVde3 ( String vde3) {
this.vde3=vde3;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.sgmoney");
  }
}