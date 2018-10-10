package nc.vo.zl.ld_parkcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ParkcontractBVO extends SuperVO {
/**
*�Զ�����3
*/
public String attrname;
/**
*��λ��
*/
public String parkarea;
/**
*��λ��
*/
public String parknum;
/**
*��ϵ�绰
*/
public String phonenum;
/**
*�ͻ�
*/
public String pk_customer;
/**
*�ϲ㵥������
*/
public String pk_parkcontract;
/**
*������Ϣ����
*/
public String pk_parkcontract_b;
/**
*���ƺ�
*/
public String platenum;
/**
*��ע
*/
public String remark;
/**
*�к�
*/
public String rowno;
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

private Integer dr=0;


public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡ�Զ�����3
*
* @return �Զ�����3
*/
public String getAttrname () {
return this.attrname;
 } 

/** 
* �����Զ�����3
*
* @param attrname �Զ�����3
*/
public void setAttrname ( String attrname) {
this.attrname=attrname;
 } 

/** 
* ��ȡ��λ��
*
* @return ��λ��
*/
public String getParkarea () {
return this.parkarea;
 } 

/** 
* ���ó�λ��
*
* @param parkarea ��λ��
*/
public void setParkarea ( String parkarea) {
this.parkarea=parkarea;
 } 

/** 
* ��ȡ��λ��
*
* @return ��λ��
*/
public String getParknum () {
return this.parknum;
 } 

/** 
* ���ó�λ��
*
* @param parknum ��λ��
*/
public void setParknum ( String parknum) {
this.parknum=parknum;
 } 

/** 
* ��ȡ��ϵ�绰
*
* @return ��ϵ�绰
*/
public String getPhonenum () {
return this.phonenum;
 } 

/** 
* ������ϵ�绰
*
* @param phonenum ��ϵ�绰
*/
public void setPhonenum ( String phonenum) {
this.phonenum=phonenum;
 } 

/** 
* ��ȡ�ͻ�
*
* @return �ͻ�
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* ���ÿͻ�
*
* @param pk_customer �ͻ�
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_parkcontract () {
return this.pk_parkcontract;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_parkcontract �ϲ㵥������
*/
public void setPk_parkcontract ( String pk_parkcontract) {
this.pk_parkcontract=pk_parkcontract;
 } 

/** 
* ��ȡ������Ϣ����
*
* @return ������Ϣ����
*/
public String getPk_parkcontract_b () {
return this.pk_parkcontract_b;
 } 

/** 
* ���û�����Ϣ����
*
* @param pk_parkcontract_b ������Ϣ����
*/
public void setPk_parkcontract_b ( String pk_parkcontract_b) {
this.pk_parkcontract_b=pk_parkcontract_b;
 } 

/** 
* ��ȡ���ƺ�
*
* @return ���ƺ�
*/
public String getPlatenum () {
return this.platenum;
 } 

/** 
* ���ó��ƺ�
*
* @param platenum ���ƺ�
*/
public void setPlatenum ( String platenum) {
this.platenum=platenum;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.parkcontract_b");
  }
}