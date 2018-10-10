package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_fcxxVO extends SuperVO {
/**
*¥��
*/
public String buildnum;
/**
*��������
*/
public String fcname;
/**
*�ϲ㵥������
*/
public String pk_customer;
/**
*����
*/
public String pk_customerfcxx;
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
*�������
*/
public UFDouble xsmj;
/**
*���޿�ʼʱ��
*/
public UFDateTime zlbegintime;
/**
*���޽�ֹʱ��
*/
public UFDateTime zlendtime;

public Integer dr=0;
/** 
* ��ȡ¥��
*
* @return ¥��
*/
public String getBuildnum () {
return this.buildnum;
 } 

/** 
* ����¥��
*
* @param buildnum ¥��
*/
public void setBuildnum ( String buildnum) {
this.buildnum=buildnum;
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
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_customer �ϲ㵥������
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_customerfcxx () {
return this.pk_customerfcxx;
 } 

/** 
* ��������
*
* @param pk_customerfcxx ����
*/
public void setPk_customerfcxx ( String pk_customerfcxx) {
this.pk_customerfcxx=pk_customerfcxx;
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

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getXsmj () {
return this.xsmj;
 } 

/** 
* �����������
*
* @param xsmj �������
*/
public void setXsmj ( UFDouble xsmj) {
this.xsmj=xsmj;
 } 

/** 
* ��ȡ���޿�ʼʱ��
*
* @return ���޿�ʼʱ��
*/
public UFDateTime getZlbegintime () {
return this.zlbegintime;
 } 

/** 
* �������޿�ʼʱ��
*
* @param zlbegintime ���޿�ʼʱ��
*/
public void setZlbegintime ( UFDateTime zlbegintime) {
this.zlbegintime=zlbegintime;
 } 

/** 
* ��ȡ���޽�ֹʱ��
*
* @return ���޽�ֹʱ��
*/
public UFDateTime getZlendtime () {
return this.zlendtime;
 } 

/** 
* �������޽�ֹʱ��
*
* @param zlendtime ���޽�ֹʱ��
*/
public void setZlendtime ( UFDateTime zlendtime) {
this.zlendtime=zlendtime;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer_fcxx");
  }

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
}