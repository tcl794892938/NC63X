package nc.vo.zl.lyw_billconfirmed;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillconfirmedBVO extends SuperVO {
/**
*��ȷ��������
*/
public UFDouble amountconfirmed;
/**
*����ȷ������
*/
public UFDouble amountconfirming;
/**
*Ӧ�ս��
*/
public UFDouble amountreceivable;
/**
*¥����
*/
public String buildno;
/**
*�������
*/
public String caccountperiod;
/**
*�շ���Ŀ
*/
public String chargingproject;
/**
*���ý�ֹ����
*/
public UFDate dfeeenddate;
/**
*���ÿ�ʼ����
*/
public UFDate dfeestartdate;
/**
*��������
*/
public String houseproperty;
/**
*�ϲ㵥������
*/
public String pk_billconfirmed;
/**
*����ȷ�ϵ���������
*/
public String pk_billconfirmedb;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*�к�
*/
public String rowno;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillno;
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
*��Դ���ݱ���id
*/
public String vsrcbid;
/**
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;
public Integer dr;
public UFDate dreccollectdate;
public UFDouble nrentarea;
public UFDouble nnotaxmny;
public UFDouble ntaxmny;
public UFDouble ntaxrate;
public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡ��ȷ��������
*
* @return ��ȷ��������
*/
public UFDouble getAmountconfirmed () {
return this.amountconfirmed;
 } 

/** 
* ������ȷ��������
*
* @param amountconfirmed ��ȷ��������
*/
public void setAmountconfirmed ( UFDouble amountconfirmed) {
this.amountconfirmed=amountconfirmed;
 } 

/** 
* ��ȡ����ȷ������
*
* @return ����ȷ������
*/
public UFDouble getAmountconfirming () {
return this.amountconfirming;
 } 

/** 
* ���ñ���ȷ������
*
* @param amountconfirming ����ȷ������
*/
public void setAmountconfirming ( UFDouble amountconfirming) {
this.amountconfirming=amountconfirming;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getAmountreceivable () {
return this.amountreceivable;
 } 

/** 
* ����Ӧ�ս��
*
* @param amountreceivable Ӧ�ս��
*/
public void setAmountreceivable ( UFDouble amountreceivable) {
this.amountreceivable=amountreceivable;
 } 

/** 
* ��ȡ¥����
*
* @return ¥����
*/
public String getBuildno () {
return this.buildno;
 } 

/** 
* ����¥����
*
* @param buildno ¥����
*/
public void setBuildno ( String buildno) {
this.buildno=buildno;
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public String getCaccountperiod () {
return this.caccountperiod;
 } 

/** 
* ���û������
*
* @param caccountperiod �������
*/
public void setCaccountperiod ( String caccountperiod) {
this.caccountperiod=caccountperiod;
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getChargingproject () {
return this.chargingproject;
 } 

/** 
* �����շ���Ŀ
*
* @param chargingproject �շ���Ŀ
*/
public void setChargingproject ( String chargingproject) {
this.chargingproject=chargingproject;
 } 

/** 
* ��ȡ���ý�ֹ����
*
* @return ���ý�ֹ����
*/
public UFDate getDfeeenddate () {
return this.dfeeenddate;
 } 

/** 
* ���÷��ý�ֹ����
*
* @param dfeeenddate ���ý�ֹ����
*/
public void setDfeeenddate ( UFDate dfeeenddate) {
this.dfeeenddate=dfeeenddate;
 } 

/** 
* ��ȡ���ÿ�ʼ����
*
* @return ���ÿ�ʼ����
*/
public UFDate getDfeestartdate () {
return this.dfeestartdate;
 } 

/** 
* ���÷��ÿ�ʼ����
*
* @param dfeestartdate ���ÿ�ʼ����
*/
public void setDfeestartdate ( UFDate dfeestartdate) {
this.dfeestartdate=dfeestartdate;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getHouseproperty () {
return this.houseproperty;
 } 

/** 
* ���÷�������
*
* @param houseproperty ��������
*/
public void setHouseproperty ( String houseproperty) {
this.houseproperty=houseproperty;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_billconfirmed () {
return this.pk_billconfirmed;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_billconfirmed �ϲ㵥������
*/
public void setPk_billconfirmed ( String pk_billconfirmed) {
this.pk_billconfirmed=pk_billconfirmed;
 } 

/** 
* ��ȡ����ȷ�ϵ���������
*
* @return ����ȷ�ϵ���������
*/
public String getPk_billconfirmedb () {
return this.pk_billconfirmedb;
 } 

/** 
* ��������ȷ�ϵ���������
*
* @param pk_billconfirmedb ����ȷ�ϵ���������
*/
public void setPk_billconfirmedb ( String pk_billconfirmedb) {
this.pk_billconfirmedb=pk_billconfirmedb;
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
* ��ȡ���ݺ�
*
* @return ���ݺ�
*/
public String getVbillno () {
return this.vbillno;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillno ���ݺ�
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
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
* ��ȡ��Դ���ݱ���id
*
* @return ��Դ���ݱ���id
*/
public String getVsrcbid () {
return this.vsrcbid;
 } 

/** 
* ������Դ���ݱ���id
*
* @param vsrcbid ��Դ���ݱ���id
*/
public void setVsrcbid ( String vsrcbid) {
this.vsrcbid=vsrcbid;
 } 

/** 
* ��ȡ��Դ����id
*
* @return ��Դ����id
*/
public String getVsrcid () {
return this.vsrcid;
 } 

/** 
* ������Դ����id
*
* @param vsrcid ��Դ����id
*/
public void setVsrcid ( String vsrcid) {
this.vsrcid=vsrcid;
 } 

/** 
* ��ȡ��Դ��������
*
* @return ��Դ��������
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* ������Դ��������
*
* @param vsrctype ��Դ��������
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  public UFDate getDreccollectdate() {
	return dreccollectdate;
}

public void setDreccollectdate(UFDate dreccollectdate) {
	this.dreccollectdate = dreccollectdate;
}

public UFDouble getNrentarea() {
	return nrentarea;
}

public void setNrentarea(UFDouble nrentarea) {
	this.nrentarea = nrentarea;
}

public UFDouble getNnotaxmny() {
	return nnotaxmny;
}

public void setNnotaxmny(UFDouble nnotaxmny) {
	this.nnotaxmny = nnotaxmny;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

@Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.billconfirmedb");
  }
}