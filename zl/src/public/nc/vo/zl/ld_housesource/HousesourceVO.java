package nc.vo.zl.ld_housesource;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HousesourceVO extends SuperVO {
/**
*�������
*/
public UFDouble buildarea;
/**
*¥��
*/
public String buildname;
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
*�������
*/
public String estatecode;
/**
*��������
*/
public String estatename;
/**
*��Դ״̬
*/
public Integer housestate;
/**
*�������
*/
public UFDouble innerarea;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*������Ϣ
*/
public String pk_familyfile;
/**
*����
*/
public String pk_group;
/**
*��Դ����
*/
public String pk_housesource;
/**
*��֯����
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*��Ŀ����
*/
public String projectname;
/**
*����
*/
public String roomnumber;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*��Ԫ
*/
public String unit;
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
 * ¥��
 */
public String floorn;


public String getFloorn() {
	return floorn;
}

public void setFloorn(String floorn) {
	this.floorn = floorn;
}


public Integer dr=0;

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
public UFDouble getBuildarea () {
return this.buildarea;
 } 

/** 
* ���ý������
*
* @param buildarea �������
*/
public void setBuildarea ( UFDouble buildarea) {
this.buildarea=buildarea;
 } 

/** 
* ��ȡ¥��
*
* @return ¥��
*/
public String getBuildname () {
return this.buildname;
 } 

/** 
* ����¥��
*
* @param buildname ¥��
*/
public void setBuildname ( String buildname) {
this.buildname=buildname;
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
* ��ȡ�������
*
* @return �������
*/
public String getEstatecode () {
return this.estatecode;
 } 

/** 
* ���÷������
*
* @param estatecode �������
*/
public void setEstatecode ( String estatecode) {
this.estatecode=estatecode;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getEstatename () {
return this.estatename;
 } 

/** 
* ���÷�������
*
* @param estatename ��������
*/
public void setEstatename ( String estatename) {
this.estatename=estatename;
 } 

/** 
* ��ȡ��Դ״̬
*
* @return ��Դ״̬
* @see String
*/
public Integer getHousestate () {
return this.housestate;
 } 

/** 
* ���÷�Դ״̬
*
* @param housestate ��Դ״̬
* @see String
*/
public void setHousestate ( Integer housestate) {
this.housestate=housestate;
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getInnerarea () {
return this.innerarea;
 } 

/** 
* �����������
*
* @param innerarea �������
*/
public void setInnerarea ( UFDouble innerarea) {
this.innerarea=innerarea;
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
* ��ȡ������Ϣ
*
* @return ������Ϣ
*/
public String getPk_familyfile () {
return this.pk_familyfile;
 } 

/** 
* ���û�����Ϣ
*
* @param pk_familyfile ������Ϣ
*/
public void setPk_familyfile ( String pk_familyfile) {
this.pk_familyfile=pk_familyfile;
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
* ��ȡ��Դ����
*
* @return ��Դ����
*/
public String getPk_housesource () {
return this.pk_housesource;
 } 

/** 
* ���÷�Դ����
*
* @param pk_housesource ��Դ����
*/
public void setPk_housesource ( String pk_housesource) {
this.pk_housesource=pk_housesource;
 } 

/** 
* ��ȡ��֯����
*
* @return ��֯����
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* ������֯����
*
* @param pk_org ��֯����
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
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getProjectname () {
return this.projectname;
 } 

/** 
* ������Ŀ����
*
* @param projectname ��Ŀ����
*/
public void setProjectname ( String projectname) {
this.projectname=projectname;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getRoomnumber () {
return this.roomnumber;
 } 

/** 
* ���÷���
*
* @param roomnumber ����
*/
public void setRoomnumber ( String roomnumber) {
this.roomnumber=roomnumber;
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
* ��ȡ��Ԫ
*
* @return ��Ԫ
*/
public String getUnit () {
return this.unit;
 } 

/** 
* ���õ�Ԫ
*
* @param unit ��Ԫ
*/
public void setUnit ( String unit) {
this.unit=unit;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.housesource");
  }
}