package nc.vo.zl.hql_payment;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PaymentVO extends SuperVO {
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
*��������
*/
public UFDate billdate;
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
*ˮ��ѽɷѵ�����
*/
public String pk_payment;
/**
*��ע
*/
public String remark;
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
/**
*��Ŀ��Ϣ
*/
private String pk_project;
/**
*Ӧ�տ�����
*/
public UFDate dysdate;
/**
*Ӧ�ս��
*/
public UFDouble nysmny;
/**
*���ս��
*/
public UFDouble nskmny;
/**
*�������
*/
public String accountingyear;
/**
*�շ���Ŀ
*/
public String pk_costproject;

public Integer dr=0;
/** 
* ��ȡ��Ŀ��Ϣ
*
* @return ��Ŀ��Ϣ
*/
public String getPk_project() {
	return pk_project;
}
/** 
* ������Ŀ��Ϣ
*
* @param pk_project ��Ŀ��Ϣ
*/
public void setPk_project(String pk_project) {
	this.pk_project = pk_project;
}
/** 
* ��ȡӦ�տ�����
*
* @return Ӧ�տ�����
*/
public UFDate getDysdate() {
	return dysdate;
}
/** 
* ����Ӧ�տ�����
*
* @param dysdate Ӧ�տ�����
*/
public void setDysdate(UFDate dysdate) {
	this.dysdate = dysdate;
}
/** 
* ��ȡӦ�ս��
*
* @return Ӧ�ս��
*/
public UFDouble getNysmny() {
	return nysmny;
}
/** 
* ����Ӧ�ս��
*
* @param nysmny Ӧ�ս��
*/
public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}
/** 
* ��ȡ���ս��
*
* @return ���ս��
*/
public UFDouble getNskmny() {
	return nskmny;
}
/** 
* �������ս��
*
* @param nskmny ���ս��
*/
public void setNskmny(UFDouble nskmny) {
	this.nskmny = nskmny;
}
/** 
* ��ȡ�������
*
* @return �������
*/
public String getAccountingyear() {
	return accountingyear;
}
/** 
* ���û������
*
* @param accountingyear �������
*/
public void setAccountingyear(String accountingyear) {
	this.accountingyear = accountingyear;
}
/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPk_costproject() {
	return pk_costproject;
}
/** 
* �����շ���Ŀ
*
* @param pk_costproject �շ���Ŀ
*/
public void setPk_costproject(String pk_costproject) {
	this.pk_costproject = pk_costproject;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
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
* ��ȡ��������
*
* @return ��������
*/
public UFDate getBilldate () {
return this.billdate;
 } 

/** 
* ���õ�������
*
* @param billdate ��������
*/
public void setBilldate ( UFDate billdate) {
this.billdate=billdate;
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
* ��ȡˮ��ѽɷѵ�����
*
* @return ˮ��ѽɷѵ�����
*/
public String getPk_payment () {
return this.pk_payment;
 } 

/** 
* ����ˮ��ѽɷѵ�����
*
* @param pk_payment ˮ��ѽɷѵ�����
*/
public void setPk_payment ( String pk_payment) {
this.pk_payment=pk_payment;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.payment");
  }
}