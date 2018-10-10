package nc.vo.zl.lyw_confirmation;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ConfirmationVO extends SuperVO {
/**
*ȷ��������
*/
public UFDouble amountconfirmed;
/**
*Ӧ�ս��
*/
public UFDouble amountreceivable;
/**
*��������
*/
public String approvenote;
/**
*������
*/
public String approver;
/**
*����ʱ��
*/
public UFDateTime approvetime;
/**
*¥����
*/
public String buildno;
/**
*�������
*/
public String caccountperiod;
/**
*�շ���Ŀ
*/
public String chargingproject;
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
*�տ�����
*/
public UFDate dcollectiondate;
/**
*���ý�ֹ����
*/
public UFDate dfeeenddate;
/**
*���ÿ�ʼ����
*/
public UFDate dfeestartdate;
/**
*��������
*/
public String houseproperty;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��������
*/
public String pk_billtype;
/**
*����ȷ�ϵ�����
*/
public String pk_confirmation;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*����
*/
public String pk_group;
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
*�к�
*/
public String rowno;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillno;
/**
*����״̬
*/
public Integer vbillstatus;
/**
*�������ͱ���
*/
public String vbilltypecode;
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
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;
public Integer dr;
public UFDate dreccollectdate;
public UFDouble nnotaxmny;
public UFDouble ntaxmny;
public UFDouble nrentarea;
public UFDouble ntaxrate;

/**
 * �Ƿ����Ƶ���
 */
public UFBoolean isadd;



public UFBoolean getIsadd() {
	return isadd;
}

public void setIsadd(UFBoolean isadd) {
	this.isadd = isadd;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡȷ��������
*
* @return ȷ��������
*/
public UFDouble getAmountconfirmed () {
return this.amountconfirmed;
 } 

/** 
* ����ȷ��������
*
* @param amountconfirmed ȷ��������
*/
public void setAmountconfirmed ( UFDouble amountconfirmed) {
this.amountconfirmed=amountconfirmed;
 } 

/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getAmountreceivable () {
return this.amountreceivable;
 } 

/** 
* ����Ӧ�ս��
*
* @param amountreceivable Ӧ�ս��
*/
public void setAmountreceivable ( UFDouble amountreceivable) {
this.amountreceivable=amountreceivable;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* ������������
*
* @param approvenote ��������
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getApprover () {
return this.approver;
 } 

/** 
* ����������
*
* @param approver ������
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getApprovetime () {
return this.approvetime;
 } 

/** 
* ��������ʱ��
*
* @param approvetime ����ʱ��
*/
public void setApprovetime ( UFDateTime approvetime) {
this.approvetime=approvetime;
 } 

/** 
* ��ȡ¥����
*
* @return ¥����
*/
public String getBuildno () {
return this.buildno;
 } 

/** 
* ����¥����
*
* @param buildno ¥����
*/
public void setBuildno ( String buildno) {
this.buildno=buildno;
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
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getChargingproject () {
return this.chargingproject;
 } 

/** 
* �����շ���Ŀ
*
* @param chargingproject �շ���Ŀ
*/
public void setChargingproject ( String chargingproject) {
this.chargingproject=chargingproject;
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
* ��ȡ�տ�����
*
* @return �տ�����
*/
public UFDate getDcollectiondate () {
return this.dcollectiondate;
 } 

/** 
* �����տ�����
*
* @param dcollectiondate �տ�����
*/
public void setDcollectiondate ( UFDate dcollectiondate) {
this.dcollectiondate=dcollectiondate;
 } 

/** 
* ��ȡ���ý�ֹ����
*
* @return ���ý�ֹ����
*/
public UFDate getDfeeenddate () {
return this.dfeeenddate;
 } 

/** 
* ���÷��ý�ֹ����
*
* @param dfeeenddate ���ý�ֹ����
*/
public void setDfeeenddate ( UFDate dfeeenddate) {
this.dfeeenddate=dfeeenddate;
 } 

/** 
* ��ȡ���ÿ�ʼ����
*
* @return ���ÿ�ʼ����
*/
public UFDate getDfeestartdate () {
return this.dfeestartdate;
 } 

/** 
* ���÷��ÿ�ʼ����
*
* @param dfeestartdate ���ÿ�ʼ����
*/
public void setDfeestartdate ( UFDate dfeestartdate) {
this.dfeestartdate=dfeestartdate;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getHouseproperty () {
return this.houseproperty;
 } 

/** 
* ���÷�������
*
* @param houseproperty ��������
*/
public void setHouseproperty ( String houseproperty) {
this.houseproperty=houseproperty;
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
* ��ȡ����ȷ�ϵ�����
*
* @return ����ȷ�ϵ�����
*/
public String getPk_confirmation () {
return this.pk_confirmation;
 } 

/** 
* ��������ȷ�ϵ�����
*
* @param pk_confirmation ����ȷ�ϵ�����
*/
public void setPk_confirmation ( String pk_confirmation) {
this.pk_confirmation=pk_confirmation;
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
* ��ȡ���ݺ�
*
* @return ���ݺ�
*/
public String getVbillno () {
return this.vbillno;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillno ���ݺ�
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
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
* ��ȡ�������ͱ���
*
* @return �������ͱ���
*/
public String getVbilltypecode () {
return this.vbilltypecode;
 } 

/** 
* ���õ������ͱ���
*
* @param vbilltypecode �������ͱ���
*/
public void setVbilltypecode ( String vbilltypecode) {
this.vbilltypecode=vbilltypecode;
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

public UFDate getDreccollectdate() {
	return dreccollectdate;
}

public void setDreccollectdate(UFDate dreccollectdate) {
	this.dreccollectdate = dreccollectdate;
}

/** 
* ������Դ��������
*
* @param vsrctype ��Դ��������
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  public UFDouble getNnotaxmny() {
	return nnotaxmny;
}

public void setNnotaxmny(UFDouble nnotaxmny) {
	this.nnotaxmny = nnotaxmny;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

public UFDouble getNrentarea() {
	return nrentarea;
}

public void setNrentarea(UFDouble nrentarea) {
	this.nrentarea = nrentarea;
}

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

@Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.confirmation");
  }
}