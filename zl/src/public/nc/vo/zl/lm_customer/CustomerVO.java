package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CustomerVO extends SuperVO {
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*��ϵ��ַ
*/
public String customeraddress;
/**
*�ͻ�����
*/
public String customercode;
/**
*��ϵ��ʽ
*/
public String customerlxfs;
/**
*�ͻ�����
*/
public String customername;
/**
*�ͻ�����
*/
public String customertype;
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
*����
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
*���֤��
*/
public String sfzh;
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
*Ӫҵִ��
*/
public String yyzz;
/**
*��ҵ����
*/
public String zygw;
/**
 * �Ƿ�ɾ��
 */
public Integer dr = 0;

/**
 *�����ͻ� 
 */
public String fzkh;

/**
 * Ǳ�ڿͻ�����
 * @return
 */
public String pk_pocus;

public String getPk_pocus() {
	return pk_pocus;
}

public void setPk_pocus(String pk_pocus) {
	this.pk_pocus = pk_pocus;
}

public String getFzkh() {
	return fzkh;
}

public void setFzkh(String fzkh) {
	this.fzkh = fzkh;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
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
* ��ȡ��ϵ��ַ
*
* @return ��ϵ��ַ
*/
public String getCustomeraddress () {
return this.customeraddress;
 } 

/** 
* ������ϵ��ַ
*
* @param customeraddress ��ϵ��ַ
*/
public void setCustomeraddress ( String customeraddress) {
this.customeraddress=customeraddress;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getCustomercode () {
return this.customercode;
 } 

/** 
* ���ÿͻ�����
*
* @param customercode �ͻ�����
*/
public void setCustomercode ( String customercode) {
this.customercode=customercode;
 } 

/** 
* ��ȡ��ϵ��ʽ
*
* @return ��ϵ��ʽ
*/
public String getCustomerlxfs () {
return this.customerlxfs;
 } 

/** 
* ������ϵ��ʽ
*
* @param customerlxfs ��ϵ��ʽ
*/
public void setCustomerlxfs ( String customerlxfs) {
this.customerlxfs=customerlxfs;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getCustomername () {
return this.customername;
 } 

/** 
* ���ÿͻ�����
*
* @param customername �ͻ�����
*/
public void setCustomername ( String customername) {
this.customername=customername;
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
* ��ȡ����
*
* @return ����
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* ��������
*
* @param pk_customer ����
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
* ��ȡ���֤��
*
* @return ���֤��
*/
public String getSfzh () {
return this.sfzh;
 } 

/** 
* �������֤��
*
* @param sfzh ���֤��
*/
public void setSfzh ( String sfzh) {
this.sfzh=sfzh;
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
* ��ȡӪҵִ��
*
* @return Ӫҵִ��
*/
public String getYyzz () {
return this.yyzz;
 } 

/** 
* ����Ӫҵִ��
*
* @param yyzz Ӫҵִ��
*/
public void setYyzz ( String yyzz) {
this.yyzz=yyzz;
 } 

/** 
* ��ȡ��ҵ����
*
* @return ��ҵ����
*/
public String getZygw () {
return this.zygw;
 } 

/** 
* ������ҵ����
*
* @param zygw ��ҵ����
*/
public void setZygw ( String zygw) {
this.zygw=zygw;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer");
  }
}