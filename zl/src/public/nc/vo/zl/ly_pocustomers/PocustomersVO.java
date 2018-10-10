package nc.vo.zl.ly_pocustomers;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PocustomersVO extends SuperVO {
/**
*��ϵ��ַ
*/
public String address;
/**
*Ӫҵִ��
*/
public String businesslicense;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*�ͻ�����״̬
*/
public String customert;
/**
*�ͻ�����
*/
public String customertype;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*���֤��
*/
public String idnumber;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��ϵ��ʽ
*/
public String phone;
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
*����
*/
public String pk_pocustomers;
/**
*ҵ��Ա
*/
public String salesman;
/**
*��Դ����
*/
public String sourceid;
/**
*��Դ����
*/
public String sourcename;
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
* ��ȡ��ϵ��ַ
*
* @return ��ϵ��ַ
*/
public String getAddress () {
return this.address;
 } 

/** 
* ������ϵ��ַ
*
* @param address ��ϵ��ַ
*/
public void setAddress ( String address) {
this.address=address;
 } 

/** 
* ��ȡӪҵִ��
*
* @return Ӫҵִ��
*/
public String getBusinesslicense () {
return this.businesslicense;
 } 

/** 
* ����Ӫҵִ��
*
* @param businesslicense Ӫҵִ��
*/
public void setBusinesslicense ( String businesslicense) {
this.businesslicense=businesslicense;
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
* ��ȡ�ͻ�����״̬
*
* @return �ͻ�����״̬
*/
public String getCustomert () {
return this.customert;
 } 

/** 
* ���ÿͻ�����״̬
*
* @param customert �ͻ�����״̬
*/
public void setCustomert ( String customert) {
this.customert=customert;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getCustomertype () {
return this.customertype;
 } 

/** 
* ���ÿͻ�����
*
* @param customertype �ͻ�����
*/
public void setCustomertype ( String customertype) {
this.customertype=customertype;
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
* ��ȡ���֤��
*
* @return ���֤��
*/
public String getIdnumber () {
return this.idnumber;
 } 

/** 
* �������֤��
*
* @param idnumber ���֤��
*/
public void setIdnumber ( String idnumber) {
this.idnumber=idnumber;
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
* ��ȡ��ϵ��ʽ
*
* @return ��ϵ��ʽ
*/
public String getPhone () {
return this.phone;
 } 

/** 
* ������ϵ��ʽ
*
* @param phone ��ϵ��ʽ
*/
public void setPhone ( String phone) {
this.phone=phone;
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
* ��ȡ����
*
* @return ����
*/
public String getPk_pocustomers () {
return this.pk_pocustomers;
 } 

/** 
* ��������
*
* @param pk_pocustomers ����
*/
public void setPk_pocustomers ( String pk_pocustomers) {
this.pk_pocustomers=pk_pocustomers;
 } 

/** 
* ��ȡҵ��Ա
*
* @return ҵ��Ա
*/
public String getSalesman () {
return this.salesman;
 } 

/** 
* ����ҵ��Ա
*
* @param salesman ҵ��Ա
*/
public void setSalesman ( String salesman) {
this.salesman=salesman;
 } 

/** 
* ��ȡ��Դ����
*
* @return ��Դ����
*/
public String getSourceid () {
return this.sourceid;
 } 

/** 
* ������Դ����
*
* @param sourceid ��Դ����
*/
public void setSourceid ( String sourceid) {
this.sourceid=sourceid;
 } 

/** 
* ��ȡ��Դ����
*
* @return ��Դ����
*/
public String getSourcename () {
return this.sourcename;
 } 

/** 
* ������Դ����
*
* @param sourcename ��Դ����
*/
public void setSourcename ( String sourcename) {
this.sourcename=sourcename;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.pocustomers");
  }
}