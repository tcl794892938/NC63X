package nc.vo.zl.hql_builldingfile;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BuildingfileVO extends SuperVO {
/**
*�������
*/
public UFDouble builtuparea;
/**
*¥������
*/
public String code;
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
public UFDouble innerarea;
/**
*�Ƿ񽨷�
*/
public UFBoolean isbuild;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*¥������
*/
public String name;
/**
*¥������
*/
public Integer nbuilding;
/**
*��������
*/
public Integer nproperty;
/**
*��Ԫ����
*/
public Integer nunit;
/**
*����
*/
public String pk_buildingfile;
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
public String pk_projectid;
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

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/**
 * �������
 */
public UFDouble personalarea;

public UFDouble getPersonalarea() {
	return personalarea;
}

public void setPersonalarea(UFDouble personalarea) {
	this.personalarea = personalarea;
}

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getBuiltuparea () {
return this.builtuparea;
 } 

/** 
* ���ý������
*
* @param builtuparea �������
*/
public void setBuiltuparea ( UFDouble builtuparea) {
this.builtuparea=builtuparea;
 } 

/** 
* ��ȡ¥������
*
* @return ¥������
*/
public String getCode () {
return this.code;
 } 

/** 
* ����¥������
*
* @param code ¥������
*/
public void setCode ( String code) {
this.code=code;
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
* ��ȡ�Ƿ񽨷�
*
* @return �Ƿ񽨷�
*/
public UFBoolean getIsbuild () {
return this.isbuild;
 } 

/** 
* �����Ƿ񽨷�
*
* @param isbuild �Ƿ񽨷�
*/
public void setIsbuild ( UFBoolean isbuild) {
this.isbuild=isbuild;
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
* ��ȡ¥������
*
* @return ¥������
*/
public String getName () {
return this.name;
 } 

/** 
* ����¥������
*
* @param name ¥������
*/
public void setName ( String name) {
this.name=name;
 } 

/** 
* ��ȡ¥������
*
* @return ¥������
*/
public Integer getNbuilding () {
return this.nbuilding;
 } 

/** 
* ����¥������
*
* @param nbuilding ¥������
*/
public void setNbuilding ( Integer nbuilding) {
this.nbuilding=nbuilding;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public Integer getNproperty () {
return this.nproperty;
 } 

/** 
* ���÷�������
*
* @param nproperty ��������
*/
public void setNproperty ( Integer nproperty) {
this.nproperty=nproperty;
 } 

/** 
* ��ȡ��Ԫ����
*
* @return ��Ԫ����
*/
public Integer getNunit () {
return this.nunit;
 } 

/** 
* ���õ�Ԫ����
*
* @param nunit ��Ԫ����
*/
public void setNunit ( Integer nunit) {
this.nunit=nunit;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_buildingfile () {
return this.pk_buildingfile;
 } 

/** 
* ��������
*
* @param pk_buildingfile ����
*/
public void setPk_buildingfile ( String pk_buildingfile) {
this.pk_buildingfile=pk_buildingfile;
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
public String getPk_projectid () {
return this.pk_projectid;
 } 

/** 
* ������Ŀ��Ϣ
*
* @param pk_projectid ��Ŀ��Ϣ
*/
public void setPk_projectid ( String pk_projectid) {
this.pk_projectid=pk_projectid;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.buildingfile");
  }
}