package nc.vo.zl.ld_carcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CarcontractCVO extends SuperVO {
/**
*���ý�ֹ����
*/
public UFDate menddate;
/**
*���ÿ�ʼ����
*/
public UFDate mstartdate;
/**
*ʵ�ս��
*/
public UFDouble ncollectemoney;
/**
*�Żݽ��
*/
public UFDouble ndiscountmoney;
/**
*Ӧ�ս��
*/
public UFDouble nreceivemoney;
/**
*��Լ���
*/
public UFDouble nrentmoney;
/**
*Ӧ������
*/
public UFDate paydate;
/**
*�ϲ㵥������
*/
public String pk_carcontract;
/**
*������Ϣ����
*/
public String pk_carcontract_c;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*���ƺ�
*/
public String platenum;
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
public Integer dr=0;
public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡ���ý�ֹ����
*
* @return ���ý�ֹ����
*/
public UFDate getMenddate () {
return this.menddate;
 } 

/** 
* ���÷��ý�ֹ����
*
* @param menddate ���ý�ֹ����
*/
public void setMenddate ( UFDate menddate) {
this.menddate=menddate;
 } 

/** 
* ��ȡ���ÿ�ʼ����
*
* @return ���ÿ�ʼ����
*/
public UFDate getMstartdate () {
return this.mstartdate;
 } 

/** 
* ���÷��ÿ�ʼ����
*
* @param mstartdate ���ÿ�ʼ����
*/
public void setMstartdate ( UFDate mstartdate) {
this.mstartdate=mstartdate;
 } 

/** 
* ��ȡʵ�ս��
*
* @return ʵ�ս��
*/
public UFDouble getNcollectemoney () {
return this.ncollectemoney;
 } 

/** 
* ����ʵ�ս��
*
* @param ncollectemoney ʵ�ս��
*/
public void setNcollectemoney ( UFDouble ncollectemoney) {
this.ncollectemoney=ncollectemoney;
 } 

/** 
* ��ȡ�Żݽ��
*
* @return �Żݽ��
*/
public UFDouble getNdiscountmoney () {
return this.ndiscountmoney;
 } 

/** 
* �����Żݽ��
*
* @param ndiscountmoney �Żݽ��
*/
public void setNdiscountmoney ( UFDouble ndiscountmoney) {
this.ndiscountmoney=ndiscountmoney;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getNreceivemoney () {
return this.nreceivemoney;
 } 

/** 
* ����Ӧ�ս��
*
* @param nreceivemoney Ӧ�ս��
*/
public void setNreceivemoney ( UFDouble nreceivemoney) {
this.nreceivemoney=nreceivemoney;
 } 

/** 
* ��ȡ��Լ���
*
* @return ��Լ���
*/
public UFDouble getNrentmoney () {
return this.nrentmoney;
 } 

/** 
* ������Լ���
*
* @param nrentmoney ��Լ���
*/
public void setNrentmoney ( UFDouble nrentmoney) {
this.nrentmoney=nrentmoney;
 } 

/** 
* ��ȡӦ������
*
* @return Ӧ������
*/
public UFDate getPaydate () {
return this.paydate;
 } 

/** 
* ����Ӧ������
*
* @param paydate Ӧ������
*/
public void setPaydate ( UFDate paydate) {
this.paydate=paydate;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_carcontract () {
return this.pk_carcontract;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_carcontract �ϲ㵥������
*/
public void setPk_carcontract ( String pk_carcontract) {
this.pk_carcontract=pk_carcontract;
 } 

/** 
* ��ȡ������Ϣ����
*
* @return ������Ϣ����
*/
public String getPk_carcontract_c () {
return this.pk_carcontract_c;
 } 

/** 
* ���÷�����Ϣ����
*
* @param pk_carcontract_c ������Ϣ����
*/
public void setPk_carcontract_c ( String pk_carcontract_c) {
this.pk_carcontract_c=pk_carcontract_c;
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPk_costproject () {
return this.pk_costproject;
 } 

/** 
* �����շ���Ŀ
*
* @param pk_costproject �շ���Ŀ
*/
public void setPk_costproject ( String pk_costproject) {
this.pk_costproject=pk_costproject;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.carcontract_c");
  }
}