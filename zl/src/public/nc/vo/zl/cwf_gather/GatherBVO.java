package nc.vo.zl.cwf_gather;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class GatherBVO extends SuperVO {
/**
*���ÿ�ʼ����
*/
public UFDate begindate;
/**
*�������
*/
public String caccountperiod;
/**
*���ý�ֹ����
*/
public UFDate enddate;
/**
*�ɳ�ֽ��
*/
public UFDouble nkcdmny;
/**
*��˰���
*/
public UFDouble nnotaxmoney;
/**
 * ˰��
 */
public UFDouble ntaxmny;
/**
*Ӧ�ս��
*/
public UFDouble nrecmoney;
/**
*���ս��
*/
public UFDouble nysmny;
/**
*�����տ���
*/
public UFDouble nskmny;
/**
*�����տ���˰���
*/
public UFDouble nsknotaxmny;
/**
*�����տ�˰��
*/
public UFDouble nsktaxmny;
/**
*��ƿ�Ŀ
*/
public String pk_account;
/**
*¥��
*/
public String pk_building;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ϲ㵥������
*/
public String pk_gather;
/**
*�ӱ�����
*/
public String pk_gather_b;
/**
*��������
*/
public String pk_house;
/**
 * ���ƺ�
 */
public String pk_carno;
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

/**
 * ��Դ����id
 */
public String vsrcid;
/**
 * ��Դ��������
 */
public String vsrctype;
/**
 * ҵ�񵥾�����
 * @return
 */
public String firstpk;
/**
 * ҵ�񵥾�����
 * @return
 */
public String firstbilltype;
/**
 * ˰��
 * @return
 */
public UFDouble ntaxrate;

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public String getFirstpk() {
	return firstpk;
}

public void setFirstpk(String firstpk) {
	this.firstpk = firstpk;
}



public String getFirstbilltype() {
	return firstbilltype;
}

public void setFirstbilltype(String firstbilltype) {
	this.firstbilltype = firstbilltype;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

public String getPk_carno() {
	return pk_carno;
}

public void setPk_carno(String pk_carno) {
	this.pk_carno = pk_carno;
}

public UFDouble getNysmny() {
	return nysmny;
}

public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}

public String getVsrcid() {
	return vsrcid;
}

public void setVsrcid(String vsrcid) {
	this.vsrcid = vsrcid;
}

public String getVsrctype() {
	return vsrctype;
}

public void setVsrctype(String vsrctype) {
	this.vsrctype = vsrctype;
}

/** 
* ��ȡ���ÿ�ʼ����
*
* @return ���ÿ�ʼ����
*/
public UFDate getBegindate () {
return this.begindate;
 } 

/** 
* ���÷��ÿ�ʼ����
*
* @param begindate ���ÿ�ʼ����
*/
public void setBegindate ( UFDate begindate) {
this.begindate=begindate;
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public String getCaccountperiod () {
return this.caccountperiod;
 } 

/** 
* ���û������
*
* @param caccountperiod �������
*/
public void setCaccountperiod ( String caccountperiod) {
this.caccountperiod=caccountperiod;
 } 

/** 
* ��ȡ���ý�ֹ����
*
* @return ���ý�ֹ����
*/
public UFDate getEnddate () {
return this.enddate;
 } 

/** 
* ���÷��ý�ֹ����
*
* @param enddate ���ý�ֹ����
*/
public void setEnddate ( UFDate enddate) {
this.enddate=enddate;
 } 

/** 
* ��ȡ�ɳ�ֽ��
*
* @return �ɳ�ֽ��
*/
public UFDouble getNkcdmny () {
return this.nkcdmny;
 } 

/** 
* ���ÿɳ�ֽ��
*
* @param nkcdmny �ɳ�ֽ��
*/
public void setNkcdmny ( UFDouble nkcdmny) {
this.nkcdmny=nkcdmny;
 } 

/** 
* ��ȡ��˰���
*
* @return ��˰���
*/
public UFDouble getNnotaxmoney () {
return this.nnotaxmoney;
 } 

/** 
* ������˰���
*
* @param nnotaxmoney ��˰���
*/
public void setNnotaxmoney ( UFDouble nnotaxmoney) {
this.nnotaxmoney=nnotaxmoney;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getNrecmoney () {
return this.nrecmoney;
 } 

/** 
* ����Ӧ�ս��
*
* @param nrecmoney Ӧ�ս��
*/
public void setNrecmoney ( UFDouble nrecmoney) {
this.nrecmoney=nrecmoney;
 } 

/** 
* ��ȡ�����տ���
*
* @return �����տ���
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* ���ñ����տ���
*
* @param nskmny �����տ���
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 

/** 
* ��ȡ�����տ���˰���
*
* @return �����տ���˰���
*/
public UFDouble getNsknotaxmny () {
return this.nsknotaxmny;
 } 

/** 
* ���ñ����տ���˰���
*
* @param nsknotaxmny �����տ���˰���
*/
public void setNsknotaxmny ( UFDouble nsknotaxmny) {
this.nsknotaxmny=nsknotaxmny;
 } 

/** 
* ��ȡ�����տ�˰��
*
* @return �����տ�˰��
*/
public UFDouble getNsktaxmny () {
return this.nsktaxmny;
 } 

/** 
* ���ñ����տ�˰��
*
* @param nsktaxmny �����տ�˰��
*/
public void setNsktaxmny ( UFDouble nsktaxmny) {
this.nsktaxmny=nsktaxmny;
 } 

/** 
* ��ȡ��ƿ�Ŀ
*
* @return ��ƿ�Ŀ
*/
public String getPk_account () {
return this.pk_account;
 } 

/** 
* ���û�ƿ�Ŀ
*
* @param pk_account ��ƿ�Ŀ
*/
public void setPk_account ( String pk_account) {
this.pk_account=pk_account;
 } 

/** 
* ��ȡ¥��
*
* @return ¥��
*/
public String getPk_building () {
return this.pk_building;
 } 

/** 
* ����¥��
*
* @param pk_building ¥��
*/
public void setPk_building ( String pk_building) {
this.pk_building=pk_building;
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
public String getPk_gather () {
return this.pk_gather;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_gather �ϲ㵥������
*/
public void setPk_gather ( String pk_gather) {
this.pk_gather=pk_gather;
 } 

/** 
* ��ȡ�ӱ�����
*
* @return �ӱ�����
*/
public String getPk_gather_b () {
return this.pk_gather_b;
 } 

/** 
* �����ӱ�����
*
* @param pk_gather_b �ӱ�����
*/
public void setPk_gather_b ( String pk_gather_b) {
this.pk_gather_b=pk_gather_b;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.gather_b");
  }
}