package nc.vo.zl.cwf_costproject;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CostprojectVO extends SuperVO {
/**
*��Ŀ����
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
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*��Ŀ����
*/
public String name;
/**
*��Ŀ
*/
public String pk_baseproject;
/**
*����
*/
public String pk_costproject;
/**
*����
*/
public String pk_group;
/**
*��֧��Ŀ
*/
public String pk_incomepro;
/**
 * �շѴ���
 */
public String pk_costtype;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*�ϼ���Ŀ
*/
public String pk_vid;
/**
*���뷽ʽ
*/
public Integer roundtype;
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

public Integer DR=0;
public String getPk_costtype() {
	return pk_costtype;
}

public void setPk_costtype(String pk_costtype) {
	this.pk_costtype = pk_costtype;
}

public Integer getDR() {
	return DR;
}

public void setDR(Integer dR) {
	DR = dR;
}

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getCode () {
return this.code;
 } 

/** 
* ������Ŀ����
*
* @param code ��Ŀ����
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
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getName () {
return this.name;
 } 

/** 
* ������Ŀ����
*
* @param name ��Ŀ����
*/
public void setName ( String name) {
this.name=name;
 } 

/** 
* ��ȡ��Ŀ
*
* @return ��Ŀ
*/
public String getPk_baseproject () {
return this.pk_baseproject;
 } 

/** 
* ������Ŀ
*
* @param pk_baseproject ��Ŀ
*/
public void setPk_baseproject ( String pk_baseproject) {
this.pk_baseproject=pk_baseproject;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_costproject () {
return this.pk_costproject;
 } 

/** 
* ��������
*
* @param pk_costproject ����
*/
public void setPk_costproject ( String pk_costproject) {
this.pk_costproject=pk_costproject;
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
* ��ȡ��֧��Ŀ
*
* @return ��֧��Ŀ
*/
public String getPk_incomepro () {
return this.pk_incomepro;
 } 

/** 
* ������֧��Ŀ
*
* @param pk_incomepro ��֧��Ŀ
*/
public void setPk_incomepro ( String pk_incomepro) {
this.pk_incomepro=pk_incomepro;
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
* ��ȡ�ϼ���Ŀ
*
* @return �ϼ���Ŀ
*/
public String getPk_vid () {
return this.pk_vid;
 } 

/** 
* �����ϼ���Ŀ
*
* @param pk_vid �ϼ���Ŀ
*/
public void setPk_vid ( String pk_vid) {
this.pk_vid=pk_vid;
 } 

/** 
* ��ȡ���뷽ʽ
*
* @return ���뷽ʽ
* @see String
*/
public Integer getRoundtype () {
return this.roundtype;
 } 

/** 
* �������뷽ʽ
*
* @param roundtype ���뷽ʽ
* @see String
*/
public void setRoundtype ( Integer roundtype) {
this.roundtype=roundtype;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.costproject");
  }
}