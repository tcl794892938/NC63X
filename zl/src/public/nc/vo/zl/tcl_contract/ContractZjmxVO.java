package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractZjmxVO extends SuperVO {
/**
*�����������
*/
public UFDate denddate;
/**
*���⿪ʼ����
*/
public UFDate dstartdate;
/**
*�ϲ㵥������
*/
public String pk_contract;
/**
*����
*/
public String pk_contract_zjmx;
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

public UFDouble nhtmny;
public UFDouble nyearmny;

public UFDouble nht1mny;
public UFDouble nyear1mny;

public UFDouble nht2mny;
public UFDouble nyear2mny;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDate getDenddate () {
return this.denddate;
 } 

/** 
* ���������������
*
* @param denddate �����������
*/
public void setDenddate ( UFDate denddate) {
this.denddate=denddate;
 } 

/** 
* ��ȡ���⿪ʼ����
*
* @return ���⿪ʼ����
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* �������⿪ʼ����
*
* @param dstartdate ���⿪ʼ����
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zjmx");
  }

public String getPk_contract_zjmx() {
	return pk_contract_zjmx;
}

public void setPk_contract_zjmx(String pk_contract_zjmx) {
	this.pk_contract_zjmx = pk_contract_zjmx;
}

public UFDouble getNhtmny() {
	return nhtmny;
}

public void setNhtmny(UFDouble nhtmny) {
	this.nhtmny = nhtmny;
}

public UFDouble getNyearmny() {
	return nyearmny;
}

public void setNyearmny(UFDouble nyearmny) {
	this.nyearmny = nyearmny;
}

public UFDouble getNht1mny() {
	return nht1mny;
}

public void setNht1mny(UFDouble nht1mny) {
	this.nht1mny = nht1mny;
}

public UFDouble getNyear1mny() {
	return nyear1mny;
}

public void setNyear1mny(UFDouble nyear1mny) {
	this.nyear1mny = nyear1mny;
}

public UFDouble getNht2mny() {
	return nht2mny;
}

public void setNht2mny(UFDouble nht2mny) {
	this.nht2mny = nht2mny;
}

public UFDouble getNyear2mny() {
	return nyear2mny;
}

public void setNyear2mny(UFDouble nyear2mny) {
	this.nyear2mny = nyear2mny;
}
}