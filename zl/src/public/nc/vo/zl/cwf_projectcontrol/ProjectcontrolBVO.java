package nc.vo.zl.cwf_projectcontrol;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ProjectcontrolBVO extends SuperVO {
/**
*�ϲ㵥������
*/
public String pk_projectcontrol;
/**
*����
*/
public String pk_projectcontrol_b;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*�û�����
*/
public String usercode;
/**
*�û�����
*/
public String username;
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
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_projectcontrol () {
return this.pk_projectcontrol;
 } 

/** 
* �����ϲ㵥������
*
* @param pk_projectcontrol �ϲ㵥������
*/
public void setPk_projectcontrol ( String pk_projectcontrol) {
this.pk_projectcontrol=pk_projectcontrol;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_projectcontrol_b () {
return this.pk_projectcontrol_b;
 } 

/** 
* ��������
*
* @param pk_projectcontrol_b ����
*/
public void setPk_projectcontrol_b ( String pk_projectcontrol_b) {
this.pk_projectcontrol_b=pk_projectcontrol_b;
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
* ��ȡ�û�����
*
* @return �û�����
*/
public String getUsercode () {
return this.usercode;
 } 

/** 
* �����û�����
*
* @param usercode �û�����
*/
public void setUsercode ( String usercode) {
this.usercode=usercode;
 } 

/** 
* ��ȡ�û�����
*
* @return �û�����
*/
public String getUsername () {
return this.username;
 } 

/** 
* �����û�����
*
* @param username �û�����
*/
public void setUsername ( String username) {
this.username=username;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.projectcontrol_b");
  }
}