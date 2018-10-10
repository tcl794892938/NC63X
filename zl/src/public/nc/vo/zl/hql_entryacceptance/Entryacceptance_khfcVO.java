package nc.vo.zl.hql_entryacceptance;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Entryacceptance_khfcVO extends SuperVO {
/**
*��������
*/
public UFDate entrydate;
/**
*¥��
*/
public String pk_buildingfile;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*�ϲ㵥������
*/
public String pk_entryacceptance;
/**
*�ͻ�������Ϣ����
*/
public String pk_entryacceptance_khfc;
/**
*��������
*/
public String pk_housesource;
/**
*��ע
*/
public String remark;
/**
*����
*/
public String roomnumber;
/**
*�к�
*/
public String rowno;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*��Ԫ
*/
public String unit;
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
* ��ȡ��������
*
* @return ��������
*/
public UFDate getEntrydate () {
return this.entrydate;
 } 

/** 
* ���ý�������
*
* @param entrydate ��������
*/
public void setEntrydate ( UFDate entrydate) {
this.entrydate=entrydate;
 } 

/** 
* ��ȡ¥��
*
* @return ¥��
*/
public String getPk_buildingfile () {
return this.pk_buildingfile;
 } 

/** 
* ����¥��
*
* @param pk_buildingfile ¥��
*/
public void setPk_buildingfile ( String pk_buildingfile) {
this.pk_buildingfile=pk_buildingfile;
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
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_entryacceptance () {
return this.pk_entryacceptance;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_entryacceptance �ϲ㵥������
*/
public void setPk_entryacceptance ( String pk_entryacceptance) {
this.pk_entryacceptance=pk_entryacceptance;
 } 

/** 
* ��ȡ�ͻ�������Ϣ����
*
* @return �ͻ�������Ϣ����
*/
public String getPk_entryacceptance_khfc () {
return this.pk_entryacceptance_khfc;
 } 

/** 
* ���ÿͻ�������Ϣ����
*
* @param pk_entryacceptance_khfc �ͻ�������Ϣ����
*/
public void setPk_entryacceptance_khfc ( String pk_entryacceptance_khfc) {
this.pk_entryacceptance_khfc=pk_entryacceptance_khfc;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_housesource () {
return this.pk_housesource;
 } 

/** 
* ���÷�������
*
* @param pk_housesource ��������
*/
public void setPk_housesource ( String pk_housesource) {
this.pk_housesource=pk_housesource;
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getRemark () {
return this.remark;
 } 

/** 
* ���ñ�ע
*
* @param remark ��ע
*/
public void setRemark ( String remark) {
this.remark=remark;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getRoomnumber () {
return this.roomnumber;
 } 

/** 
* ���÷���
*
* @param roomnumber ����
*/
public void setRoomnumber ( String roomnumber) {
this.roomnumber=roomnumber;
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getRowno () {
return this.rowno;
 } 

/** 
* �����к�
*
* @param rowno �к�
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
* ��ȡ��Ԫ
*
* @return ��Ԫ
*/
public String getUnit () {
return this.unit;
 } 

/** 
* ���õ�Ԫ
*
* @param unit ��Ԫ
*/
public void setUnit ( String unit) {
this.unit=unit;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.entryacceptance_khfc");
  }
}