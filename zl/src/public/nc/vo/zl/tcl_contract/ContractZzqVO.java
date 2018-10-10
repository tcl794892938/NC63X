package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractZzqVO extends SuperVO {
/**
*������ʼ����
*/
public UFDate dstartdate;
/**
*�����������
*/
public UFDouble ndaymny;
/**
*�����������
*/
public UFDouble ndayzzmny;
/**
*�����������
*/
public UFDouble nmonthmny;
/**
*�����������
*/
public UFDouble nmonthzzmny;
/**
*�����������
*/
public UFDouble nyearmny;
/**
*�����������
*/
public UFDouble nyearzzmny;
/**
*��������(%)
*/
public UFDouble nzzrate;
/**
*�ϲ㵥������
*/
public String pk_contract;
/**
*����
*/
public String pk_contract_zzq;
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

public UFDouble nmny;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* ��ȡ������ʼ����
*
* @return ������ʼ����
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* ����������ʼ����
*
* @param dstartdate ������ʼ����
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDouble getNdaymny () {
return this.ndaymny;
 } 

/** 
* ���������������
*
* @param ndaymny �����������
*/
public void setNdaymny ( UFDouble ndaymny) {
this.ndaymny=ndaymny;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDouble getNdayzzmny () {
return this.ndayzzmny;
 } 

/** 
* ���������������
*
* @param ndayzzmny �����������
*/
public void setNdayzzmny ( UFDouble ndayzzmny) {
this.ndayzzmny=ndayzzmny;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDouble getNmonthmny () {
return this.nmonthmny;
 } 

/** 
* ���������������
*
* @param nmonthmny �����������
*/
public void setNmonthmny ( UFDouble nmonthmny) {
this.nmonthmny=nmonthmny;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDouble getNmonthzzmny () {
return this.nmonthzzmny;
 } 

/** 
* ���������������
*
* @param nmonthzzmny �����������
*/
public void setNmonthzzmny ( UFDouble nmonthzzmny) {
this.nmonthzzmny=nmonthzzmny;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDouble getNyearmny () {
return this.nyearmny;
 } 

/** 
* ���������������
*
* @param nyearmny �����������
*/
public void setNyearmny ( UFDouble nyearmny) {
this.nyearmny=nyearmny;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDouble getNyearzzmny () {
return this.nyearzzmny;
 } 

/** 
* ���������������
*
* @param nyearzzmny �����������
*/
public void setNyearzzmny ( UFDouble nyearzzmny) {
this.nyearzzmny=nyearzzmny;
 } 

/** 
* ��ȡ��������(%)
*
* @return ��������(%)
*/
public UFDouble getNzzrate () {
return this.nzzrate;
 } 

/** 
* ������������(%)
*
* @param nzzrate ��������(%)
*/
public void setNzzrate ( UFDouble nzzrate) {
this.nzzrate=nzzrate;
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
public String getPk_contract_zzq () {
return this.pk_contract_zzq;
 } 

/** 
* ��������
*
* @param pk_contract_zzq ����
*/
public void setPk_contract_zzq ( String pk_contract_zzq) {
this.pk_contract_zzq=pk_contract_zzq;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zzq");
  }

public UFDouble getNmny() {
	return nmny;
}

public void setNmny(UFDouble nmny) {
	this.nmny = nmny;
}
}