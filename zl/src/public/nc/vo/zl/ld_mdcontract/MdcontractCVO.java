package nc.vo.zl.ld_mdcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class MdcontractCVO extends SuperVO {
/**
*�����
*/
public String accountmonth;
/**
*��˰���
*/
public UFDouble freetaxmoney;
/**
*���ս��
*/
public UFDouble getmoney;
/**
*���ý�ֹ����
*/
public UFDate menddate;
/**
*Ӧ�տ�����
*/
public UFDate moneydate;
/**
*������ʼ����
*/
public UFDate mstartdate;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*�ϲ㵥������
*/
public String pk_mdcontract;
/**
*����
*/
public String pk_mdcontract_c;
/**
*Ӧ�ս��
*/
public UFDouble receivemoney;
/**
*��ע
*/
public String remark;
/**
*�к�
*/
public String rowno;
/**
*˰��
*/
public UFDouble taxmoney;
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
private Integer dr=0;
public UFDouble nconfirmed;

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* ��ȡ�����
*
* @return �����
*/
public String getAccountmonth () {
return this.accountmonth;
 } 

/** 
* ���û����
*
* @param accountmonth �����
*/
public void setAccountmonth ( String accountmonth) {
this.accountmonth=accountmonth;
 } 

/** 
* ��ȡ��˰���
*
* @return ��˰���
*/
public UFDouble getFreetaxmoney () {
return this.freetaxmoney;
 } 

/** 
* ������˰���
*
* @param freetaxmoney ��˰���
*/
public void setFreetaxmoney ( UFDouble freetaxmoney) {
this.freetaxmoney=freetaxmoney;
 } 

/** 
* ��ȡ���ս��
*
* @return ���ս��
*/
public UFDouble getGetmoney () {
return this.getmoney;
 } 

/** 
* �������ս��
*
* @param getmoney ���ս��
*/
public void setGetmoney ( UFDouble getmoney) {
this.getmoney=getmoney;
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
* ��ȡӦ�տ�����
*
* @return Ӧ�տ�����
*/
public UFDate getMoneydate () {
return this.moneydate;
 } 

/** 
* ����Ӧ�տ�����
*
* @param moneydate Ӧ�տ�����
*/
public void setMoneydate ( UFDate moneydate) {
this.moneydate=moneydate;
 } 

/** 
* ��ȡ������ʼ����
*
* @return ������ʼ����
*/
public UFDate getMstartdate () {
return this.mstartdate;
 } 

/** 
* ���÷�����ʼ����
*
* @param mstartdate ������ʼ����
*/
public void setMstartdate ( UFDate mstartdate) {
this.mstartdate=mstartdate;
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
public String getPk_mdcontract () {
return this.pk_mdcontract;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_mdcontract �ϲ㵥������
*/
public void setPk_mdcontract ( String pk_mdcontract) {
this.pk_mdcontract=pk_mdcontract;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_mdcontract_c () {
return this.pk_mdcontract_c;
 } 

/** 
* ��������
*
* @param pk_mdcontract_c ����
*/
public void setPk_mdcontract_c ( String pk_mdcontract_c) {
this.pk_mdcontract_c=pk_mdcontract_c;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getReceivemoney () {
return this.receivemoney;
 } 

/** 
* ����Ӧ�ս��
*
* @param receivemoney Ӧ�ս��
*/
public void setReceivemoney ( UFDouble receivemoney) {
this.receivemoney=receivemoney;
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
* ��ȡ˰��
*
* @return ˰��
*/
public UFDouble getTaxmoney () {
return this.taxmoney;
 } 

/** 
* ����˰��
*
* @param taxmoney ˰��
*/
public void setTaxmoney ( UFDouble taxmoney) {
this.taxmoney=taxmoney;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.mdcontract_c");
  }
}