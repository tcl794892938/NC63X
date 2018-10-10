package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_qjfyVO extends SuperVO {
/**
*��ʼ����
*/
public UFDateTime begindate;
/**
*¥��
*/
public String buildnum;
/**
*�ͻ�����
*/
public String customername;
/**
*��ֹ����
*/
public UFDateTime enddate;
/**
*��������
*/
public String fcname;
/**
*�������
*/
public String kjny;
/**
*�ϲ㵥������
*/
public String pk_customer;
/**
*����
*/
public String pk_customerqjfy;
/**
*�շ���Ŀ
*/
public String sfproject;
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
*Ӧ�ɿ�����
*/
public UFDateTime yjkdate;
/**
*Ӧ�ս��
*/
public UFDouble ysamount;
/** 
* ��ȡ��ʼ����
*
* @return ��ʼ����
*/
public UFDateTime getBegindate () {
return this.begindate;
 } 

/** 
* ���ÿ�ʼ����
*
* @param begindate ��ʼ����
*/
public void setBegindate ( UFDateTime begindate) {
this.begindate=begindate;
 } 

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
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getCustomername () {
return this.customername;
 } 

/** 
* ���ÿͻ�����
*
* @param customername �ͻ�����
*/
public void setCustomername ( String customername) {
this.customername=customername;
 } 

/** 
* ��ȡ��ֹ����
*
* @return ��ֹ����
*/
public UFDateTime getEnddate () {
return this.enddate;
 } 

/** 
* ���ý�ֹ����
*
* @param enddate ��ֹ����
*/
public void setEnddate ( UFDateTime enddate) {
this.enddate=enddate;
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
* ��ȡ�������
*
* @return �������
*/
public String getKjny () {
return this.kjny;
 } 

/** 
* ���û������
*
* @param kjny �������
*/
public void setKjny ( String kjny) {
this.kjny=kjny;
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
public String getPk_customerqjfy () {
return this.pk_customerqjfy;
 } 

/** 
* ��������
*
* @param pk_customerqjfy ����
*/
public void setPk_customerqjfy ( String pk_customerqjfy) {
this.pk_customerqjfy=pk_customerqjfy;
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getSfproject () {
return this.sfproject;
 } 

/** 
* �����շ���Ŀ
*
* @param sfproject �շ���Ŀ
*/
public void setSfproject ( String sfproject) {
this.sfproject=sfproject;
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
* ��ȡӦ�ɿ�����
*
* @return Ӧ�ɿ�����
*/
public UFDateTime getYjkdate () {
return this.yjkdate;
 } 

/** 
* ����Ӧ�ɿ�����
*
* @param yjkdate Ӧ�ɿ�����
*/
public void setYjkdate ( UFDateTime yjkdate) {
this.yjkdate=yjkdate;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getYsamount () {
return this.ysamount;
 } 

/** 
* ����Ӧ�ս��
*
* @param ysamount Ӧ�ս��
*/
public void setYsamount ( UFDouble ysamount) {
this.ysamount=ysamount;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer_qjfy");
  }
}