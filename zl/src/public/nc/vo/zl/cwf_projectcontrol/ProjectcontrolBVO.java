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
*上层单据主键
*/
public String pk_projectcontrol;
/**
*主键
*/
public String pk_projectcontrol_b;
/**
*时间戳
*/
public UFDateTime ts;
/**
*用户编码
*/
public String usercode;
/**
*用户名称
*/
public String username;
/**
*自定义项1
*/
public String vdef1;
/**
*自定义项2
*/
public String vdef2;
/**
*自定义项3
*/
public String vdef3;
/**
*自定义项4
*/
public String vdef4;
/**
*自定义项5
*/
public String vdef5;
/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_projectcontrol () {
return this.pk_projectcontrol;
 } 

/** 
* 设置上层单据主键
*
* @param pk_projectcontrol 上层单据主键
*/
public void setPk_projectcontrol ( String pk_projectcontrol) {
this.pk_projectcontrol=pk_projectcontrol;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_projectcontrol_b () {
return this.pk_projectcontrol_b;
 } 

/** 
* 设置主键
*
* @param pk_projectcontrol_b 主键
*/
public void setPk_projectcontrol_b ( String pk_projectcontrol_b) {
this.pk_projectcontrol_b=pk_projectcontrol_b;
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return this.ts;
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.ts=ts;
 } 

/** 
* 获取用户编码
*
* @return 用户编码
*/
public String getUsercode () {
return this.usercode;
 } 

/** 
* 设置用户编码
*
* @param usercode 用户编码
*/
public void setUsercode ( String usercode) {
this.usercode=usercode;
 } 

/** 
* 获取用户名称
*
* @return 用户名称
*/
public String getUsername () {
return this.username;
 } 

/** 
* 设置用户名称
*
* @param username 用户名称
*/
public void setUsername ( String username) {
this.username=username;
 } 

/** 
* 获取自定义项1
*
* @return 自定义项1
*/
public String getVdef1 () {
return this.vdef1;
 } 

/** 
* 设置自定义项1
*
* @param vdef1 自定义项1
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
 } 

/** 
* 获取自定义项2
*
* @return 自定义项2
*/
public String getVdef2 () {
return this.vdef2;
 } 

/** 
* 设置自定义项2
*
* @param vdef2 自定义项2
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
 } 

/** 
* 获取自定义项3
*
* @return 自定义项3
*/
public String getVdef3 () {
return this.vdef3;
 } 

/** 
* 设置自定义项3
*
* @param vdef3 自定义项3
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
 } 

/** 
* 获取自定义项4
*
* @return 自定义项4
*/
public String getVdef4 () {
return this.vdef4;
 } 

/** 
* 设置自定义项4
*
* @param vdef4 自定义项4
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
 } 

/** 
* 获取自定义项5
*
* @return 自定义项5
*/
public String getVdef5 () {
return this.vdef5;
 } 

/** 
* 设置自定义项5
*
* @param vdef5 自定义项5
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.projectcontrol_b");
  }
}