package nc.vo.zl.hql_payment;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Payment_bVO extends SuperVO {
/**
*�������
*/
public String caccountperiod;
/**
*Ӧ�տ�����
*/
public UFDate dysdate;
/**
*������
*/
public UFDouble ncurrentnum;
/**
*ʵ������
*/
public UFDouble nrealnum;
/**
*���ս��
*/
public UFDouble nskmny;
/**
 * �ɷѽ��
 */
public UFDouble npaymny;
/**
 * ��ֽ��
 */
public UFDouble noffsetmny;

/**
 * Ԥ�ɿ�ɵֽ��
 */
public UFDouble nkdmny;

/**
 * �ڳ��ɵֽ��
 */
public UFDouble nqcmny;

/**
 * �ѵֳ���
 */
public UFDouble nycmny;

/**
*������
*/
public UFDouble nupnum;
/**
*Ӧ�ս��
*/
public UFDouble nysmny;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ϲ㵥������
*/
public String pk_payment;
/**
*ˮ��ѽɷѵ���������
*/
public String pk_paymentb;
/**
*����
*/
public UFDouble price;
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

public Integer dr=0;
public UFDouble nconfirmed;

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
* ��ȡ������
*
* @return ������
*/
public UFDouble getNcurrentnum () {
return this.ncurrentnum;
 } 

/** 
* ���ñ�����
*
* @param ncurrentnum ������
*/
public void setNcurrentnum ( UFDouble ncurrentnum) {
this.ncurrentnum=ncurrentnum;
 } 

/** 
* ��ȡʵ������
*
* @return ʵ������
*/
public UFDouble getNrealnum () {
return this.nrealnum;
 } 

/** 
* ����ʵ������
*
* @param nrealnum ʵ������
*/
public void setNrealnum ( UFDouble nrealnum) {
this.nrealnum=nrealnum;
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
* ��ȡԤ�ɿ�ɵֽ��
*
* @return Ԥ�ɿ�ɵֽ��
*/
public UFDouble getNkdmny() {
	return nkdmny;
}
/** 
* ����Ԥ�ɿ�ɵֽ��
*
* @param nkdmny Ԥ�ɿ�ɵֽ��
*/
public void setNkdmny(UFDouble nkdmny) {
	this.nkdmny = nkdmny;
}
/** 
* ��ȡ�ڳ��ɵֽ��
*
* @return �ڳ��ɵֽ��
*/
public UFDouble getNqcmny() {
	return nqcmny;
}
/** 
* �����ڳ��ɵֽ��
*
* @param nkdmny �ڳ��ɵֽ��
*/
public void setNqcmny(UFDouble nqcmny) {
	this.nqcmny = nqcmny;
}
/** 
* ��ȡ�ѵֳ���
*
* @return �ѵֳ���
*/
public UFDouble getNycmny() {
	return nycmny;
}
/** 
* �����ѵֳ���
*
* @param nkdmny �ѵֳ���
*/
public void setNycmny(UFDouble nycmny) {
	this.nycmny = nycmny;
}

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getNupnum () {
return this.nupnum;
 } 

/** 
* ����������
*
* @param nupnum ������
*/
public void setNupnum ( UFDouble nupnum) {
this.nupnum=nupnum;
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
* ��ȡ��ֽ��
*
* @return ��ֽ��
*/
public UFDouble getNoffsetmny() {
	return noffsetmny;
}

/** 
* ���ó�ֽ��
*
* @param noffsetmny ��ֽ��
*/
public void setNoffsetmny(UFDouble noffsetmny) {
	this.noffsetmny = noffsetmny;
}

/** 
* ��ȡ�ɷѽ��
*
* @return �ɷѽ��
*/
public UFDouble getNpaymny() {
	return npaymny;
}

/** 
* ���ýɷѽ��
*
* @param noffsetmny �ɷѽ��
*/
public void setNpaymny(UFDouble npaymny) {
	this.npaymny = npaymny;
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
public String getPk_payment () {
return this.pk_payment;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_payment �ϲ㵥������
*/
public void setPk_payment ( String pk_payment) {
this.pk_payment=pk_payment;
 } 

/** 
* ��ȡˮ��ѽɷѵ���������
*
* @return ˮ��ѽɷѵ���������
*/
public String getPk_paymentb () {
return this.pk_paymentb;
 } 

/** 
* ����ˮ��ѽɷѵ���������
*
* @param pk_paymentb ˮ��ѽɷѵ���������
*/
public void setPk_paymentb ( String pk_paymentb) {
this.pk_paymentb=pk_paymentb;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getPrice () {
return this.price;
 } 

/** 
* ���õ���
*
* @param price ����
*/
public void setPrice ( UFDouble price) {
this.price=price;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.payment_b");
  }
}