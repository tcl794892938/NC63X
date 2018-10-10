package nc.vo.zl.ld_parkcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ParkcontractFVO extends SuperVO {
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
*��˰���
*/
public UFDouble nfreetaxmoney;
/**
*Ӧ�ս��
*/
public UFDouble nreceivemoney;
/**
*˰��
*/
public UFDouble ntaxmoney;
/**
*��λ��
*/
public String parkarea;
/**
*��λ��
*/
public String parknum;
/**
*Ӧ������
*/
public UFDate paydate;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ϲ㵥������
*/
public String pk_parkcontract;
/**
*��������
*/
public String pk_parkcontract_f;
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
public UFDouble nconfirmed;
public String vdef3;
private Integer dr=0;

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
* ��ȡ��˰���
*
* @return ��˰���
*/
public UFDouble getNfreetaxmoney () {
return this.nfreetaxmoney;
 } 

/** 
* ������˰���
*
* @param nfreetaxmoney ��˰���
*/
public void setNfreetaxmoney ( UFDouble nfreetaxmoney) {
this.nfreetaxmoney=nfreetaxmoney;
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
* ��ȡ˰��
*
* @return ˰��
*/
public UFDouble getNtaxmoney () {
return this.ntaxmoney;
 } 

/** 
* ����˰��
*
* @param ntaxmoney ˰��
*/
public void setNtaxmoney ( UFDouble ntaxmoney) {
this.ntaxmoney=ntaxmoney;
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
* ��ȡ��������
*
* @return ��������
*/
public String getPk_parkcontract_f () {
return this.pk_parkcontract_f;
 } 

/** 
* ���ò�������
*
* @param pk_parkcontract_f ��������
*/
public void setPk_parkcontract_f ( String pk_parkcontract_f) {
this.pk_parkcontract_f=pk_parkcontract_f;
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


  public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

@Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.parkcontract_f");
  }
}