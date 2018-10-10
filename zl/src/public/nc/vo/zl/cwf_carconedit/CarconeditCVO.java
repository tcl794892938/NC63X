package nc.vo.zl.cwf_carconedit;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CarconeditCVO extends SuperVO {
/**
*���ý�ֹ����
*/
public UFDate menddate;
/**
*���ÿ�ʼ����
*/
public UFDate mstartdate;
/**
*���ս��
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
*��λ��
*/
public String pk_building;
/**
*�ϲ㵥������
*/
public String pk_carconedit;
/**
*������Ϣ����
*/
public String pk_carconedit_c;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*��λ��
*/
public String pk_house;
/**
*���ƺ�
*/
public String pk_plate;
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
*�Զ�����
*/
public String vdef3;
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
* ��ȡ���ս��
*
* @return ���ս��
*/
public UFDouble getNcollectemoney () {
return this.ncollectemoney;
 } 

/** 
* �������ս��
*
* @param ncollectemoney ���ս��
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
* ��ȡ������Ϣ����
*
* @return ������Ϣ����
*/
public String getPk_carconedit_c () {
return this.pk_carconedit_c;
 } 

/** 
* ���÷�����Ϣ����
*
* @param pk_carconedit_c ������Ϣ����
*/
public void setPk_carconedit_c ( String pk_carconedit_c) {
this.pk_carconedit_c=pk_carconedit_c;
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
* ��ȡ�Զ�����
*
* @return �Զ�����
*/
public String getVdef3 () {
return this.vdef3;
 } 

/** 
* �����Զ�����
*
* @param vdef3 �Զ�����
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.carconedit_c");
  }
}