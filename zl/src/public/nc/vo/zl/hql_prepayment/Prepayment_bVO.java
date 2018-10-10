package nc.vo.zl.hql_prepayment;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Prepayment_bVO extends SuperVO {
/**
*�������
*/
public String caccountperiod;
/**
*Ӧ�տ�����
*/
public UFDate dysdate;
/**
*���ս��
*/
public UFDouble nskmny;
/**
*Ӧ�ս��
*/
public UFDouble nysmny;
/**
 * �ɳ�ֽ��
 */
public UFDouble noffsetmny;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ϲ㵥������
*/
public String pk_prepayment;
/**
*���Ԥ�ɵ���������
*/
public String pk_prepayment_b;

/**
 * �Ƿ��Ѵ����ƽ̨
 */
public UFBoolean is_kjpt;

/**
*��ע
*/
public String remark;
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
public UFDouble nconfirmed;

public Integer dr=0;

public String pk_customer;

public String getPk_customer() {
	return pk_customer;
}

public void setPk_customer(String pk_customer) {
	this.pk_customer = pk_customer;
}

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
* ��ȡ�������
*
* @return �������
*/
public String getCaccountperiod() {
	return caccountperiod;
}

/** 
* ���û������
*
* @param accountingyear �������
*/
public void setCaccountperiod(String caccountperiod) {
	this.caccountperiod = caccountperiod;
}

/** 
* ��ȡӦ�տ�����
*
* @return Ӧ�տ�����
*/
public UFDate getDysdate () {
return this.dysdate;
 } 

/** 
* ����Ӧ�տ�����
*
* @param dysdate Ӧ�տ�����
*/
public void setDysdate ( UFDate dysdate) {
this.dysdate=dysdate;
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
* ��ȡ�ɳ�ֽ��
*
* @return �ɳ�ֽ��
*/
public UFDouble getNoffsetmny() {
	return noffsetmny;
}

/** 
* ���ÿɳ�ֽ��
*
* @param noffsetmny �ɳ�ֽ��
*/
public void setNoffsetmny(UFDouble noffsetmny) {
	this.noffsetmny = noffsetmny;
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
public String getPk_prepayment () {
return this.pk_prepayment;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_prepayment �ϲ㵥������
*/
public void setPk_prepayment ( String pk_prepayment) {
this.pk_prepayment=pk_prepayment;
 } 

/** 
* ��ȡ���Ԥ�ɵ���������
*
* @return ���Ԥ�ɵ���������
*/
public String getPk_prepayment_b () {
return this.pk_prepayment_b;
 } 

/** 
* ���õ��Ԥ�ɵ���������
*
* @param pk_prepayment_b ���Ԥ�ɵ���������
*/
public void setPk_prepayment_b ( String pk_prepayment_b) {
this.pk_prepayment_b=pk_prepayment_b;
 } 

/** 
* ��ȡ�Ƿ��Ѵ����ƽ̨
*
* @return �Ƿ��Ѵ����ƽ̨
*/
public UFBoolean getIs_kjpt() {
	return is_kjpt;
}

/** 
* �����Ƿ��Ѵ����ƽ̨
*
* @param is_kjpt �Ƿ��Ѵ����ƽ̨
*/
public void setIs_kjpt(UFBoolean is_kjpt) {
	this.is_kjpt = is_kjpt;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.prepayment_b");
  }
}