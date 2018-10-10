package nc.vo.zl.cwf_recbill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RecbillVO extends SuperVO {
/**
*�������
*/
public String approvenote;
/**
*�����
*/
public String approver;
/**
*���ʱ��
*/
public UFDateTime approvetime;
/**
*���ÿ�ʼ����
*/
public UFDate begindate;
/**
*�������ͱ���
*/
public String billtypecode;
/**
*�������
*/
public String caccountperiod;
/**
*����ȷ�Ͻ��
*/
public UFDouble confirm;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*���ý�ֹ����
*/
public UFDate enddate;
/**
*Ӧ�ɿ�����
*/
public UFDate gatherdate;
/**
*��Ʊ���
*/
public UFDouble invoicemoney;
/**
*��Ʊ��
*/
public String invoiceno;
/**
*��ע
*/
public String memo;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��˰���
*/
public UFDouble nnotaxmoney;
/**
*���ս��
*/
public UFDouble nrealmoney;
/**
*Ӧ�ս��
*/
public UFDouble nrecmoney;
/**
*˰��
*/
public UFDouble ntaxmoney;
/**
 * ˰��
 */
public UFDouble ntaxrate;
/**
*��������
*/
public String pk_billtype;
/**
*¥��
*/
public String pk_building;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*����
*/
public String pk_group;
/**
*��������
*/
public String pk_house;
/**
 * ���ƺ�
 */
public String pk_carno;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*��Ŀ��Ϣ
*/
public String pk_project;
/**
*����
*/
public String pk_recbill;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillcode;
/**
*����״̬
*/
public Integer vbillstatus;
/**
*�Զ�����1
*/
public String vdef1;
/**
*�Զ�����10
*/
public String vdef10;
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
*�Զ�����6
*/
public String vdef6;
/**
*�Զ�����7
*/
public String vdef7;
/**
*�Զ�����8
*/
public String vdef8;
/**
*�Զ�����9
*/
public String vdef9;
/**
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;
/**
 * �Ƿ�ɾ��
 */
public Integer dr=0;


public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public String getPk_carno() {
	return pk_carno;
}

public void setPk_carno(String pk_carno) {
	this.pk_carno = pk_carno;
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
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* �����������
*
* @param approvenote �������
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return this.approver;
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getApprovetime () {
return this.approvetime;
 } 

/** 
* �������ʱ��
*
* @param approvetime ���ʱ��
*/
public void setApprovetime ( UFDateTime approvetime) {
this.approvetime=approvetime;
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
* ��ȡ�������ͱ���
*
* @return �������ͱ���
*/
public String getBilltypecode () {
return this.billtypecode;
 } 

/** 
* ���õ������ͱ���
*
* @param billtypecode �������ͱ���
*/
public void setBilltypecode ( String billtypecode) {
this.billtypecode=billtypecode;
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
* ��ȡ����ȷ�Ͻ��
*
* @return ����ȷ�Ͻ��
*/
public UFDouble getConfirm () {
return this.confirm;
 } 

/** 
* ��������ȷ�Ͻ��
*
* @param confirm ����ȷ�Ͻ��
*/
public void setConfirm ( UFDouble confirm) {
this.confirm=confirm;
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getCreationtime () {
return this.creationtime;
 } 

/** 
* ���ô���ʱ��
*
* @param creationtime ����ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getCreator () {
return this.creator;
 } 

/** 
* ���ô�����
*
* @param creator ������
*/
public void setCreator ( String creator) {
this.creator=creator;
 } 

/** 
* ��ȡ�Ƶ�����
*
* @return �Ƶ�����
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* �����Ƶ�����
*
* @param dbilldate �Ƶ�����
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
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
* ��ȡӦ�ɿ�����
*
* @return Ӧ�ɿ�����
*/
public UFDate getGatherdate () {
return this.gatherdate;
 } 

/** 
* ����Ӧ�ɿ�����
*
* @param gatherdate Ӧ�ɿ�����
*/
public void setGatherdate ( UFDate gatherdate) {
this.gatherdate=gatherdate;
 } 

/** 
* ��ȡ��Ʊ���
*
* @return ��Ʊ���
*/
public UFDouble getInvoicemoney () {
return this.invoicemoney;
 } 

/** 
* ���ÿ�Ʊ���
*
* @param invoicemoney ��Ʊ���
*/
public void setInvoicemoney ( UFDouble invoicemoney) {
this.invoicemoney=invoicemoney;
 } 

/** 
* ��ȡ��Ʊ��
*
* @return ��Ʊ��
*/
public String getInvoiceno () {
return this.invoiceno;
 } 

/** 
* ���ÿ�Ʊ��
*
* @param invoiceno ��Ʊ��
*/
public void setInvoiceno ( String invoiceno) {
this.invoiceno=invoiceno;
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getMemo () {
return this.memo;
 } 

/** 
* ���ñ�ע
*
* @param memo ��ע
*/
public void setMemo ( String memo) {
this.memo=memo;
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return this.modifiedtime;
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return this.modifier;
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
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
* ��ȡ���ս��
*
* @return ���ս��
*/
public UFDouble getNrealmoney () {
return this.nrealmoney;
 } 

/** 
* �������ս��
*
* @param nrealmoney ���ս��
*/
public void setNrealmoney ( UFDouble nrealmoney) {
this.nrealmoney=nrealmoney;
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
* ��ȡ˰��
*
* @return ˰��
*/
public UFDouble getNtaxmoney () {
return this.ntaxmoney;
 } 

/** 
* ����˰��
*
* @param ntaxmoney ˰��
*/
public void setNtaxmoney ( UFDouble ntaxmoney) {
this.ntaxmoney=ntaxmoney;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_billtype () {
return this.pk_billtype;
 } 

/** 
* ���õ�������
*
* @param pk_billtype ��������
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
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
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return this.pk_group;
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
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
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* ��ȡ��֯�汾
*
* @return ��֯�汾
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* ������֯�汾
*
* @param pk_org_v ��֯�汾
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* ��ȡ��Ŀ��Ϣ
*
* @return ��Ŀ��Ϣ
*/
public String getPk_project () {
return this.pk_project;
 } 

/** 
* ������Ŀ��Ϣ
*
* @param pk_project ��Ŀ��Ϣ
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_recbill () {
return this.pk_recbill;
 } 

/** 
* ��������
*
* @param pk_recbill ����
*/
public void setPk_recbill ( String pk_recbill) {
this.pk_recbill=pk_recbill;
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
* ��ȡ���ݺ�
*
* @return ���ݺ�
*/
public String getVbillcode () {
return this.vbillcode;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillcode ���ݺ�
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getVbillstatus () {
return this.vbillstatus;
 } 

/** 
* ���õ���״̬
*
* @param vbillstatus ����״̬
* @see String
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
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
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return this.vdef10;
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.vdef10=vdef10;
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
* ��ȡ�Զ�����6
*
* @return �Զ�����6
*/
public String getVdef6 () {
return this.vdef6;
 } 

/** 
* �����Զ�����6
*
* @param vdef6 �Զ�����6
*/
public void setVdef6 ( String vdef6) {
this.vdef6=vdef6;
 } 

/** 
* ��ȡ�Զ�����7
*
* @return �Զ�����7
*/
public String getVdef7 () {
return this.vdef7;
 } 

/** 
* �����Զ�����7
*
* @param vdef7 �Զ�����7
*/
public void setVdef7 ( String vdef7) {
this.vdef7=vdef7;
 } 

/** 
* ��ȡ�Զ�����8
*
* @return �Զ�����8
*/
public String getVdef8 () {
return this.vdef8;
 } 

/** 
* �����Զ�����8
*
* @param vdef8 �Զ�����8
*/
public void setVdef8 ( String vdef8) {
this.vdef8=vdef8;
 } 

/** 
* ��ȡ�Զ�����9
*
* @return �Զ�����9
*/
public String getVdef9 () {
return this.vdef9;
 } 

/** 
* �����Զ�����9
*
* @param vdef9 �Զ�����9
*/
public void setVdef9 ( String vdef9) {
this.vdef9=vdef9;
 } 

/** 
* ��ȡ��Դ����id
*
* @return ��Դ����id
*/
public String getVsrcid () {
return this.vsrcid;
 } 

/** 
* ������Դ����id
*
* @param vsrcid ��Դ����id
*/
public void setVsrcid ( String vsrcid) {
this.vsrcid=vsrcid;
 } 

/** 
* ��ȡ��Դ��������
*
* @return ��Դ��������
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* ������Դ��������
*
* @param vsrctype ��Դ��������
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.recbill");
  }
}