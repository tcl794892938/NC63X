package nc.vo.zl.ld_mdcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class MdcontractVO extends SuperVO {
/**
*����ܽ��
*/
public UFDouble allrent;
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
*���ݺ�
*/
public String vbillcode;
public String getVbillcode() {
	return vbillcode;
}

public void setVbillcode(String vbillcode) {
	this.vbillcode = vbillcode;
}


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
*��ͬ��ֹ��
*/
public UFDate enddate;
/**
*��Ӫλ��
*/
public String location;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��ͬ����
*/
public String name;
/**
*��������
*/
public String pk_billtype;
/**
*��ͬ����
*/
public String pk_contracttype;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*����
*/
public String pk_group;
/**
*��Ӫ��ͬ����
*/
public String pk_mdcontract;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
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
*��ͬ��ʼ��
*/
public UFDate startdate;
/**
*����״̬
*/
public Integer state;
/**
*˰��
*/
public Integer taxrate;
/**
*ʱ���
*/
public UFDateTime ts;
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
*�汾��
*/
public Integer version=-1;
/**
*����
*/
public String code;



/**
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;
private Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* ��ȡ����ܽ��
*
* @return ����ܽ��
*/
public UFDouble getAllrent () {
return this.allrent;
 } 

/** 
* ��������ܽ��
*
* @param allrent ����ܽ��
*/
public void setAllrent ( UFDouble allrent) {
this.allrent=allrent;
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
* ��ȡ��ͬ��ֹ��
*
* @return ��ͬ��ֹ��
*/
public UFDate getEnddate () {
return this.enddate;
 } 

/** 
* ���ú�ͬ��ֹ��
*
* @param enddate ��ͬ��ֹ��
*/
public void setEnddate ( UFDate enddate) {
this.enddate=enddate;
 } 

/** 
* ��ȡ��Ӫλ��
*
* @return ��Ӫλ��
*/
public String getLocation () {
return this.location;
 } 

/** 
* ���þ�Ӫλ��
*
* @param location ��Ӫλ��
*/
public void setLocation ( String location) {
this.location=location;
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
* ��ȡ��Ӫ��ͬ����
*
* @return ��Ӫ��ͬ����
*/
public String getPk_mdcontract () {
return this.pk_mdcontract;
 } 

/** 
* ���þ�Ӫ��ͬ����
*
* @param pk_mdcontract ��Ӫ��ͬ����
*/
public void setPk_mdcontract ( String pk_mdcontract) {
this.pk_mdcontract=pk_mdcontract;
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
* ��ȡ��ͬ��ʼ��
*
* @return ��ͬ��ʼ��
*/
public UFDate getStartdate () {
return this.startdate;
 } 

/** 
* ���ú�ͬ��ʼ��
*
* @param startdate ��ͬ��ʼ��
*/
public void setStartdate ( UFDate startdate) {
this.startdate=startdate;
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getState () {
return this.state;
 } 

/** 
* ���õ���״̬
*
* @param state ����״̬
* @see String
*/
public void setState ( Integer state) {
this.state=state;
 } 

/** 
* ��ȡ˰��
*
* @return ˰��
* @see String
*/
public Integer getTaxrate () {
return this.taxrate;
 } 

/** 
* ����˰��
*
* @param taxrate ˰��
* @see String
*/
public void setTaxrate ( Integer taxrate) {
this.taxrate=taxrate;
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
* ��ȡ�汾��
*
* @return �汾��
*/
public Integer getVersion () {
return this.version;
 } 

/** 
* ���ð汾��
*
* @param version �汾��
*/
public void setVersion ( Integer version) {
this.version=version;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getCode () {
return this.code;
 } 

/** 
* ���ñ���
*
* @param code ����
*/
public void setCode ( String code) {
this.code=code;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.mdcontract");
  }
}