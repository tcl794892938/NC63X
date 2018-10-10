package nc.vo.zl.ly_zylist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZylistSrVO extends SuperVO {
/**
*������
*/
public String finishqk;
/**
*���ʱ��
*/
public UFDateTime finishtime;
/**
*�շ���Ŀ
*/
public String payproject;
/**
*�ϲ㵥������
*/
public String pk_zylist;
/**
*����
*/
public String pk_zylist_sr;
/**
*��ע
*/
public String remarks;
/**
*ά�޷�����Ա
*/
public String serviceman;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*δ���ԭ��
*/
public String unfinishyy;
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
*Ӧ�ս��
*/
public UFDouble ygsmoney=new UFDouble(0);
/**
*���ս��
*/
public UFDouble yjsmoney=new UFDouble(0);
/**
 * �Ƿ�ɾ��
 */
public Integer dr=0;
/**
*�������
*/
public String caccountperiod;
/**
*�к�
*/
public String rowno;
public UFDouble nconfirmed;

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

public String getRowno() {
	return rowno;
}

public void setRowno(String rowno) {
	this.rowno = rowno;
}

public String getCaccountperiod() {
	return caccountperiod;
}

public void setCaccountperiod(String caccountperiod) {
	this.caccountperiod = caccountperiod;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡ������
*
* @return ������
*/
public String getFinishqk () {
return this.finishqk;
 } 

/** 
* ����������
*
* @param finishqk ������
*/
public void setFinishqk ( String finishqk) {
this.finishqk=finishqk;
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getFinishtime () {
return this.finishtime;
 } 

/** 
* �������ʱ��
*
* @param finishtime ���ʱ��
*/
public void setFinishtime ( UFDateTime finishtime) {
this.finishtime=finishtime;
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPayproject () {
return this.payproject;
 } 

/** 
* �����շ���Ŀ
*
* @param payproject �շ���Ŀ
*/
public void setPayproject ( String payproject) {
this.payproject=payproject;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_zylist () {
return this.pk_zylist;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_zylist �ϲ㵥������
*/
public void setPk_zylist ( String pk_zylist) {
this.pk_zylist=pk_zylist;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_zylist_sr () {
return this.pk_zylist_sr;
 } 

/** 
* ��������
*
* @param pk_zylist_sr ����
*/
public void setPk_zylist_sr ( String pk_zylist_sr) {
this.pk_zylist_sr=pk_zylist_sr;
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getRemarks () {
return this.remarks;
 } 

/** 
* ���ñ�ע
*
* @param remarks ��ע
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
 } 

/** 
* ��ȡά�޷�����Ա
*
* @return ά�޷�����Ա
*/
public String getServiceman () {
return this.serviceman;
 } 

/** 
* ����ά�޷�����Ա
*
* @param serviceman ά�޷�����Ա
*/
public void setServiceman ( String serviceman) {
this.serviceman=serviceman;
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
* ��ȡδ���ԭ��
*
* @return δ���ԭ��
*/
public String getUnfinishyy () {
return this.unfinishyy;
 } 

/** 
* ����δ���ԭ��
*
* @param unfinishyy δ���ԭ��
*/
public void setUnfinishyy ( String unfinishyy) {
this.unfinishyy=unfinishyy;
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
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getYgsmoney () {
return this.ygsmoney;
 } 

/** 
* ����Ӧ�ս��
*
* @param ygsmoney Ӧ�ս��
*/
public void setYgsmoney ( UFDouble ygsmoney) {
this.ygsmoney=ygsmoney;
 } 

/** 
* ��ȡ���ս��
*
* @return ���ս��
*/
public UFDouble getYjsmoney () {
return this.yjsmoney;
 } 

/** 
* �������ս��
*
* @param yjsmoney ���ս��
*/
public void setYjsmoney ( UFDouble yjsmoney) {
this.yjsmoney=yjsmoney;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.zylist_sr");
  }
}