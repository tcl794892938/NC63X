package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractBzjVO extends SuperVO {
/**
*Ӧ������
*/
public UFDate drecdate;
/**
*Ƿ�ս��
*/
public UFDouble nqkmny;
/**
*���ս��
*/
public UFDouble nskmny;
/**
*���˽��
*/
public UFDouble ntkmny;
/**
*Ӧ�ս��
*/
public UFDouble nysmny;
/**
*Ӧ�˽��
*/
public UFDouble nytmny;
/**
*�ϲ㵥������
*/
public String pk_contract;
/**
*����
*/
public String pk_contract_bzj;
/**
*�շ���Ŀ
*/
public String pk_costproject;
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
* ��ȡӦ������
*
* @return Ӧ������
*/
public UFDate getDrecdate () {
return this.drecdate;
 } 

/** 
* ����Ӧ������
*
* @param drecdate Ӧ������
*/
public void setDrecdate ( UFDate drecdate) {
this.drecdate=drecdate;
 } 

/** 
* ��ȡǷ�ս��
*
* @return Ƿ�ս��
*/
public UFDouble getNqkmny () {
return this.nqkmny;
 } 

/** 
* ����Ƿ�ս��
*
* @param nqkmny Ƿ�ս��
*/
public void setNqkmny ( UFDouble nqkmny) {
this.nqkmny=nqkmny;
 } 

/** 
* ��ȡ���ս��
*
* @return ���ս��
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* �������ս��
*
* @param nskmny ���ս��
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 

/** 
* ��ȡ���˽��
*
* @return ���˽��
*/
public UFDouble getNtkmny () {
return this.ntkmny;
 } 

/** 
* �������˽��
*
* @param ntkmny ���˽��
*/
public void setNtkmny ( UFDouble ntkmny) {
this.ntkmny=ntkmny;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getNysmny () {
return this.nysmny;
 } 

/** 
* ����Ӧ�ս��
*
* @param nysmny Ӧ�ս��
*/
public void setNysmny ( UFDouble nysmny) {
this.nysmny=nysmny;
 } 

/** 
* ��ȡӦ�˽��
*
* @return Ӧ�˽��
*/
public UFDouble getNytmny () {
return this.nytmny;
 } 

/** 
* ����Ӧ�˽��
*
* @param nytmny Ӧ�˽��
*/
public void setNytmny ( UFDouble nytmny) {
this.nytmny=nytmny;
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
public String getPk_contract_bzj () {
return this.pk_contract_bzj;
 } 

/** 
* ��������
*
* @param pk_contract_bzj ����
*/
public void setPk_contract_bzj ( String pk_contract_bzj) {
this.pk_contract_bzj=pk_contract_bzj;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_bzj");
  }
}