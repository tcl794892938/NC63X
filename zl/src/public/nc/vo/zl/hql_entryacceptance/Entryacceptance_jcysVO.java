package nc.vo.zl.hql_entryacceptance;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Entryacceptance_jcysVO extends SuperVO {
/**
*�ϲ㵥������
*/
public String pk_entryacceptance;
/**
*�������յǼ�����
*/
public String pk_entryacceptance_jcys;
/**
*������Ŀ
*/
public String pk_jt_acceptance;
/**
*���
*/
public String rowno;
/**
*���2
*/
public String serialnumber;
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

private Integer dr = 0;

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
* ��ȡ�������յǼ�����
*
* @return �������յǼ�����
*/
public String getPk_entryacceptance_jcys () {
return this.pk_entryacceptance_jcys;
 } 

/** 
* ���ý������յǼ�����
*
* @param pk_entryacceptance_jcys �������յǼ�����
*/
public void setPk_entryacceptance_jcys ( String pk_entryacceptance_jcys) {
this.pk_entryacceptance_jcys=pk_entryacceptance_jcys;
 } 

/** 
* ��ȡ������Ŀ
*
* @return ������Ŀ
*/
public String getPk_jt_acceptance () {
return this.pk_jt_acceptance;
 } 

/** 
* ����������Ŀ
*
* @param pk_jt_acceptance ������Ŀ
*/
public void setPk_jt_acceptance ( String pk_jt_acceptance) {
this.pk_jt_acceptance=pk_jt_acceptance;
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
* ��ȡ���2
*
* @return ���2
*/
public String getSerialnumber () {
return this.serialnumber;
 } 

/** 
* �������2
*
* @param serialnumber ���2
*/
public void setSerialnumber ( String serialnumber) {
this.serialnumber=serialnumber;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.entryacceptance_jcys");
  }
}