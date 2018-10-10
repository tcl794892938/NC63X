package nc.vo.zl.hql_throwalease;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Throwalease_tzysVO extends SuperVO {
/**
*������Ŀ
*/
public String pk_acceptance;
/**
*�ϲ㵥������
*/
public String pk_throwalease;
/**
*�������յǼ�����
*/
public String pk_throwaleasetzys;
/**
*���
*/
public String rowno;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*״��
*/
public String tzysstatus;
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

private Integer dr = 0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡ������Ŀ
*
* @return ������Ŀ
*/
public String getPk_acceptance () {
return this.pk_acceptance;
 } 

/** 
* ����������Ŀ
*
* @param pk_acceptance ������Ŀ
*/
public void setPk_acceptance ( String pk_acceptance) {
this.pk_acceptance=pk_acceptance;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_throwalease () {
return this.pk_throwalease;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_throwalease �ϲ㵥������
*/
public void setPk_throwalease ( String pk_throwalease) {
this.pk_throwalease=pk_throwalease;
 } 

/** 
* ��ȡ�������յǼ�����
*
* @return �������յǼ�����
*/
public String getPk_throwaleasetzys () {
return this.pk_throwaleasetzys;
 } 

/** 
* �����������յǼ�����
*
* @param pk_throwaleasetzys �������յǼ�����
*/
public void setPk_throwaleasetzys ( String pk_throwaleasetzys) {
this.pk_throwaleasetzys=pk_throwaleasetzys;
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public String getRowno () {
return this.rowno;
 } 

/** 
* �������
*
* @param rowno ���
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
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
* ��ȡ״��
*
* @return ״��
*/
public String getTzysstatus () {
return this.tzysstatus;
 } 

/** 
* ����״��
*
* @param tzysstatus ״��
*/
public void setTzysstatus ( String tzysstatus) {
this.tzysstatus=tzysstatus;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.throwalease_tzys");
  }
}