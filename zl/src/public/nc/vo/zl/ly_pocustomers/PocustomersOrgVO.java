package nc.vo.zl.ly_pocustomers;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PocustomersOrgVO extends SuperVO {
/**
*�ϲ㵥������
*/
public String pk_pocustomers;
/**
*����
*/
public String pk_pocustomers_org;
/**
*��Ŀ����
*/
public String procode;
/**
*��Ŀ����
*/
public String proname;
/**
*��ע
*/
public String remarks;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*�Զ�����1
*/
public String vdef1;
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
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_pocustomers () {
return this.pk_pocustomers;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_pocustomers �ϲ㵥������
*/
public void setPk_pocustomers ( String pk_pocustomers) {
this.pk_pocustomers=pk_pocustomers;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_pocustomers_org () {
return this.pk_pocustomers_org;
 } 

/** 
* ��������
*
* @param pk_pocustomers_org ����
*/
public void setPk_pocustomers_org ( String pk_pocustomers_org) {
this.pk_pocustomers_org=pk_pocustomers_org;
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getProcode () {
return this.procode;
 } 

/** 
* ������Ŀ����
*
* @param procode ��Ŀ����
*/
public void setProcode ( String procode) {
this.procode=procode;
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getProname () {
return this.proname;
 } 

/** 
* ������Ŀ����
*
* @param proname ��Ŀ����
*/
public void setProname ( String proname) {
this.proname=proname;
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getRemarks () {
return this.remarks;
 } 

/** 
* ���ñ�ע
*
* @param remarks ��ע
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.pocustomers_org");
  }
}