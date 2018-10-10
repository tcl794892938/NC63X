package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractZqfyVO extends SuperVO {
/**
*�շ�����(��)
*/
public Integer dsfzq;
/**
*��ʼ����
*/
public UFDate dstartdate;
/**
*�շѽ��
*/
public UFDouble nsfmny;
/**
*˰��
*/
public UFDouble ntaxrate;
/**
*�ϲ㵥������
*/
public String pk_contract;
/**
*����
*/
public String pk_contract_zqfy;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*�շѱ�׼
*/
public String pk_feescale;
/**
*��������
*/
public String pk_house;

public UFDouble narea;
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
*��ע
*/
public String vmemo;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* ��ȡ�շ�����(��)
*
* @return �շ�����(��)
*/
public Integer getDsfzq () {
return this.dsfzq;
 } 

/** 
* �����շ�����(��)
*
* @param dsfzq �շ�����(��)
*/
public void setDsfzq ( Integer dsfzq) {
this.dsfzq=dsfzq;
 } 

/** 
* ��ȡ��ʼ����
*
* @return ��ʼ����
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* ���ÿ�ʼ����
*
* @param dstartdate ��ʼ����
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* ��ȡ�շѽ��
*
* @return �շѽ��
*/
public UFDouble getNsfmny () {
return this.nsfmny;
 } 

/** 
* �����շѽ��
*
* @param nsfmny �շѽ��
*/
public void setNsfmny ( UFDouble nsfmny) {
this.nsfmny=nsfmny;
 } 

/** 
* ��ȡ˰��
*
* @return ˰��
*/
public UFDouble getNtaxrate () {
return this.ntaxrate;
 } 

/** 
* ����˰��
*
* @param ntaxrate ˰��
*/
public void setNtaxrate ( UFDouble ntaxrate) {
this.ntaxrate=ntaxrate;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_contract () {
return this.pk_contract;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_contract �ϲ㵥������
*/
public void setPk_contract ( String pk_contract) {
this.pk_contract=pk_contract;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_contract_zqfy () {
return this.pk_contract_zqfy;
 } 

/** 
* ��������
*
* @param pk_contract_zqfy ����
*/
public void setPk_contract_zqfy ( String pk_contract_zqfy) {
this.pk_contract_zqfy=pk_contract_zqfy;
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
* ��ȡ�շѱ�׼
*
* @return �շѱ�׼
*/
public String getPk_feescale () {
return this.pk_feescale;
 } 

/** 
* �����շѱ�׼
*
* @param pk_feescale �շѱ�׼
*/
public void setPk_feescale ( String pk_feescale) {
this.pk_feescale=pk_feescale;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_house () {
return this.pk_house;
 } 

/** 
* ���÷�������
*
* @param pk_house ��������
*/
public void setPk_house ( String pk_house) {
this.pk_house=pk_house;
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

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return this.vmemo;
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.vmemo=vmemo;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zqfy");
  }

public UFDouble getNarea() {
	return narea;
}

public void setNarea(UFDouble narea) {
	this.narea = narea;
}
}