package nc.vo.zl.ld_parkcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ParkcontractVO extends SuperVO {
/**
*�������
*/
public String approvenote;
/**
*������
*/
public String approver;
/**
*���ʱ��
*/
public UFDateTime approvetime;
/**
*��ͬ����
*/
public String code;
/**
*��������
*/
public Integer costcycle;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*�Ƶ�ʱ��
*/
public UFDate dbilldate;
/**
*��ͬ��ֹ����
*/
public UFDate enddate;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��Լ�ܽ��
*/
public UFDouble nallrent;
/**
*��ͬ����
*/
public String name;
/**
*˰��
*/
public Integer ntaxrate;
/**
*��������
*/
public String pk_billtype;
/**
*��ͬ����
*/
public String pk_contracttype;
/**
*�շ���Ŀ
*/
public String pk_costproject;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*�շѱ�׼
*/
public String pk_feescale;
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
*��λ��ͬ����
*/
public String pk_parkcontract;
/**
*��Ŀ
*/
public String pk_project;
/**
*��ע
*/
public String remark;
/**
*ǩ������
*/
public UFDate rentdate;
/**
*��ͬ��ʼ����
*/
public UFDate startdate;
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
public String vsrcbid;
/**
*��Դ��������
*/
public String vsrcbtype;

public Integer dr=0;
/**
*�汾
*/
public Integer version=-1;

public Integer getVersion() {
	return version;
}

public void setVersion(Integer version) {
	this.version = version;
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
* ��ȡ��ͬ����
*
* @return ��ͬ����
*/
public String getCode () {
return this.code;
 } 

/** 
* ���ú�ͬ����
*
* @param code ��ͬ����
*/
public void setCode ( String code) {
this.code=code;
 } 

/** 
* ��ȡ��������
*
* @return ��������
* @see String
*/
public Integer getCostcycle () {
return this.costcycle;
 } 

/** 
* ���÷�������
*
* @param costcycle ��������
* @see String
*/
public void setCostcycle ( Integer costcycle) {
this.costcycle=costcycle;
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
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* �����Ƶ�ʱ��
*
* @param dbilldate �Ƶ�ʱ��
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* ��ȡ��ͬ��ֹ����
*
* @return ��ͬ��ֹ����
*/
public UFDate getEnddate () {
return this.enddate;
 } 

/** 
* ���ú�ͬ��ֹ����
*
* @param enddate ��ͬ��ֹ����
*/
public void setEnddate ( UFDate enddate) {
this.enddate=enddate;
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
* ��ȡ��Լ�ܽ��
*
* @return ��Լ�ܽ��
*/
public UFDouble getNallrent () {
return this.nallrent;
 } 

/** 
* ������Լ�ܽ��
*
* @param nallrent ��Լ�ܽ��
*/
public void setNallrent ( UFDouble nallrent) {
this.nallrent=nallrent;
 } 

/** 
* ��ȡ��ͬ����
*
* @return ��ͬ����
*/
public String getName () {
return this.name;
 } 

/** 
* ���ú�ͬ����
*
* @param name ��ͬ����
*/
public void setName ( String name) {
this.name=name;
 } 

/** 
* ��ȡ˰��
*
* @return ˰��
*/
public Integer getNtaxrate () {
return this.ntaxrate;
 } 

/** 
* ����˰��
*
* @param ntaxrate ˰��
*/
public void setNtaxrate ( Integer ntaxrate) {
this.ntaxrate=ntaxrate;
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
* ��ȡ��ͬ����
*
* @return ��ͬ����
*/
public String getPk_contracttype () {
return this.pk_contracttype;
 } 

/** 
* ���ú�ͬ����
*
* @param pk_contracttype ��ͬ����
*/
public void setPk_contracttype ( String pk_contracttype) {
this.pk_contracttype=pk_contracttype;
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
* ��ȡ�շѱ�׼
*
* @return �շѱ�׼
*/
public String getPk_feescale () {
return this.pk_feescale;
 } 

/** 
* �����շѱ�׼
*
* @param pk_feescale �շѱ�׼
*/
public void setPk_feescale ( String pk_feescale) {
this.pk_feescale=pk_feescale;
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
* ��ȡ��λ��ͬ����
*
* @return ��λ��ͬ����
*/
public String getPk_parkcontract () {
return this.pk_parkcontract;
 } 

/** 
* ���ó�λ��ͬ����
*
* @param pk_parkcontract ��λ��ͬ����
*/
public void setPk_parkcontract ( String pk_parkcontract) {
this.pk_parkcontract=pk_parkcontract;
 } 

/** 
* ��ȡ��Ŀ
*
* @return ��Ŀ
*/
public String getPk_project () {
return this.pk_project;
 } 

/** 
* ������Ŀ
*
* @param pk_project ��Ŀ
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
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
* ��ȡǩ������
*
* @return ǩ������
*/
public UFDate getRentdate () {
return this.rentdate;
 } 

/** 
* ����ǩ������
*
* @param rentdate ǩ������
*/
public void setRentdate ( UFDate rentdate) {
this.rentdate=rentdate;
 } 

/** 
* ��ȡ��ͬ��ʼ����
*
* @return ��ͬ��ʼ����
*/
public UFDate getStartdate () {
return this.startdate;
 } 

/** 
* ���ú�ͬ��ʼ����
*
* @param startdate ��ͬ��ʼ����
*/
public void setStartdate ( UFDate startdate) {
this.startdate=startdate;
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
public String getVsrcbid () {
return this.vsrcbid;
 } 

/** 
* ������Դ����id
*
* @param vsrcbid ��Դ����id
*/
public void setVsrcbid ( String vsrcbid) {
this.vsrcbid=vsrcbid;
 } 

/** 
* ��ȡ��Դ��������
*
* @return ��Դ��������
*/
public String getVsrcbtype () {
return this.vsrcbtype;
 } 

/** 
* ������Դ��������
*
* @param vsrcbtype ��Դ��������
*/
public void setVsrcbtype ( String vsrcbtype) {
this.vsrcbtype=vsrcbtype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.parkcontract");
  }
}