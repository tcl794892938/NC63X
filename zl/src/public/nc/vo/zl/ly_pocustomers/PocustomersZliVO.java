package nc.vo.zl.ly_pocustomers;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PocustomersZliVO extends SuperVO {
/**
	 * 
	 */
	private static final long serialVersionUID = -4836263887877905291L;
/**
*¥��
*/
public String building;
/**
*��������ֹ(ÿ��ÿƽ)
*/
public UFDouble epriceend;
/**
*����������(ÿ��ÿƽ)
*/
public UFDouble epricestart;
/**
*����
*/
public String house;
/**
*��������
*/
public String other;
/**
*�ϲ㵥������
*/
public String pk_pocustomers;
/**
*����
*/
public String pk_pocustomers_zli;
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
 * �Ƿ�ɾ��
 */
public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* ��ȡ¥��
*
* @return ¥��
*/
public String getBuilding () {
return this.building;
 } 

/** 
* ����¥��
*
* @param building ¥��
*/
public void setBuilding ( String building) {
this.building=building;
 } 

/** 
* ��ȡ��������ֹ(ÿ��ÿƽ)
*
* @return ��������ֹ(ÿ��ÿƽ)
*/
public UFDouble getEpriceend () {
return this.epriceend;
 } 

/** 
* ������������ֹ(ÿ��ÿƽ)
*
* @param epriceend ��������ֹ(ÿ��ÿƽ)
*/
public void setEpriceend ( UFDouble epriceend) {
this.epriceend=epriceend;
 } 

/** 
* ��ȡ����������(ÿ��ÿƽ)
*
* @return ����������(ÿ��ÿƽ)
*/
public UFDouble getEpricestart () {
return this.epricestart;
 } 

/** 
* ��������������(ÿ��ÿƽ)
*
* @param epricestart ����������(ÿ��ÿƽ)
*/
public void setEpricestart ( UFDouble epricestart) {
this.epricestart=epricestart;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getHouse () {
return this.house;
 } 

/** 
* ���÷���
*
* @param house ����
*/
public void setHouse ( String house) {
this.house=house;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getOther () {
return this.other;
 } 

/** 
* ������������
*
* @param other ��������
*/
public void setOther ( String other) {
this.other=other;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_pocustomers () {
return this.pk_pocustomers;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_pocustomers �ϲ㵥������
*/
public void setPk_pocustomers ( String pk_pocustomers) {
this.pk_pocustomers=pk_pocustomers;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_pocustomers_zli () {
return this.pk_pocustomers_zli;
 } 

/** 
* ��������
*
* @param pk_pocustomers_zli ����
*/
public void setPk_pocustomers_zli ( String pk_pocustomers_zli) {
this.pk_pocustomers_zli=pk_pocustomers_zli;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.pocustomers_zli");
  }
}