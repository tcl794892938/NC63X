package nc.vo.zl.hql_throwalease;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Throwalease_bzjthVO extends SuperVO {
/**
*Ӧ�˿�����
*/
public UFDate dytkdate;
/**
*������
*/
public UFDouble njsmny;
/**
*�ۿ���
*/
public UFDouble nkkmny;
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
public String pk_throwalease;
/**
*��֤���˻�����
*/
public String pk_throwaleasebzjth;
/**
*�к�
*/
public String rowno;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*Ӧ�˱�֤��
*/
public UFDouble ytdeposit;
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
*���˽��
*/
public UFDouble nytmny;
public UFDouble nconfirmed;

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

public UFDouble getNytmny() {
	return nytmny;
}

public void setNytmny(UFDouble nytmny) {
	this.nytmny = nytmny;
}

private Integer dr = 0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡӦ�˿�����
*
* @return Ӧ�˿�����
*/
public UFDate getDytkdate () {
return this.dytkdate;
 } 

/** 
* ����Ӧ�˿�����
*
* @param dytkdate Ӧ�˿�����
*/
public void setDytkdate ( UFDate dytkdate) {
this.dytkdate=dytkdate;
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getNjsmny () {
return this.njsmny;
 } 

/** 
* ���ý�����
*
* @param njsmny ������
*/
public void setNjsmny ( UFDouble njsmny) {
this.njsmny=njsmny;
 } 

/** 
* ��ȡ�ۿ���
*
* @return �ۿ���
*/
public UFDouble getNkkmny () {
return this.nkkmny;
 } 

/** 
* ���ÿۿ���
*
* @param nkkmny �ۿ���
*/
public void setNkkmny ( UFDouble nkkmny) {
this.nkkmny=nkkmny;
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
* ��ȡ��֤���˻�����
*
* @return ��֤���˻�����
*/
public String getPk_throwaleasebzjth () {
return this.pk_throwaleasebzjth;
 } 

/** 
* ���ñ�֤���˻�����
*
* @param pk_throwaleasebzjth ��֤���˻�����
*/
public void setPk_throwaleasebzjth ( String pk_throwaleasebzjth) {
this.pk_throwaleasebzjth=pk_throwaleasebzjth;
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
* ��ȡӦ�˱�֤��
*
* @return Ӧ�˱�֤��
*/
public UFDouble getYtdeposit () {
return this.ytdeposit;
 } 

/** 
* ����Ӧ�˱�֤��
*
* @param ytdeposit Ӧ�˱�֤��
*/
public void setYtdeposit ( UFDouble ytdeposit) {
this.ytdeposit=ytdeposit;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.throwalease_bzjth");
  }
}