package nc.vo.zl.cwf_carconedit;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CarconeditBVO extends SuperVO {
/**
*��ϵ�绰
*/
public String phone;
/**
*��λ��
*/
public String pk_building;
/**
*�ϲ㵥������
*/
public String pk_carconedit;
/**
*����
*/
public String pk_carconedit_b;
/**
*�ͻ�
*/
public String pk_customer;
/**
*��λ��
*/
public String pk_house;
/**
*���ƺ�
*/
public String pk_plate;
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
* ��ȡ��ϵ�绰
*
* @return ��ϵ�绰
*/
public String getPhone () {
return this.phone;
 } 

/** 
* ������ϵ�绰
*
* @param phone ��ϵ�绰
*/
public void setPhone ( String phone) {
this.phone=phone;
 } 

/** 
* ��ȡ��λ��
*
* @return ��λ��
*/
public String getPk_building () {
return this.pk_building;
 } 

/** 
* ���ó�λ��
*
* @param pk_building ��λ��
*/
public void setPk_building ( String pk_building) {
this.pk_building=pk_building;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_carconedit () {
return this.pk_carconedit;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_carconedit �ϲ㵥������
*/
public void setPk_carconedit ( String pk_carconedit) {
this.pk_carconedit=pk_carconedit;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_carconedit_b () {
return this.pk_carconedit_b;
 } 

/** 
* ��������
*
* @param pk_carconedit_b ����
*/
public void setPk_carconedit_b ( String pk_carconedit_b) {
this.pk_carconedit_b=pk_carconedit_b;
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
* ��ȡ��λ��
*
* @return ��λ��
*/
public String getPk_house () {
return this.pk_house;
 } 

/** 
* ���ó�λ��
*
* @param pk_house ��λ��
*/
public void setPk_house ( String pk_house) {
this.pk_house=pk_house;
 } 

/** 
* ��ȡ���ƺ�
*
* @return ���ƺ�
*/
public String getPk_plate () {
return this.pk_plate;
 } 

/** 
* ���ó��ƺ�
*
* @param pk_plate ���ƺ�
*/
public void setPk_plate ( String pk_plate) {
this.pk_plate=pk_plate;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.carconedit_b");
  }
}