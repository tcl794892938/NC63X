package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_fcxxVO extends SuperVO {
/**
*楼栋
*/
public String buildnum;
/**
*房产名称
*/
public String fcname;
/**
*上层单据主键
*/
public String pk_customer;
/**
*主键
*/
public String pk_customerfcxx;
/**
*时间戳
*/
public UFDateTime ts;
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
*销售面积
*/
public UFDouble xsmj;
/**
*租赁开始时间
*/
public UFDateTime zlbegintime;
/**
*租赁截止时间
*/
public UFDateTime zlendtime;

public Integer dr=0;
/** 
* 获取楼栋
*
* @return 楼栋
*/
public String getBuildnum () {
return this.buildnum;
 } 

/** 
* 设置楼栋
*
* @param buildnum 楼栋
*/
public void setBuildnum ( String buildnum) {
this.buildnum=buildnum;
 } 

/** 
* 获取房产名称
*
* @return 房产名称
*/
public String getFcname () {
return this.fcname;
 } 

/** 
* 设置房产名称
*
* @param fcname 房产名称
*/
public void setFcname ( String fcname) {
this.fcname=fcname;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* 设置上层单据主键
*
* @param pk_customer 上层单据主键
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_customerfcxx () {
return this.pk_customerfcxx;
 } 

/** 
* 设置主键
*
* @param pk_customerfcxx 主键
*/
public void setPk_customerfcxx ( String pk_customerfcxx) {
this.pk_customerfcxx=pk_customerfcxx;
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

/** 
* 获取销售面积
*
* @return 销售面积
*/
public UFDouble getXsmj () {
return this.xsmj;
 } 

/** 
* 设置销售面积
*
* @param xsmj 销售面积
*/
public void setXsmj ( UFDouble xsmj) {
this.xsmj=xsmj;
 } 

/** 
* 获取租赁开始时间
*
* @return 租赁开始时间
*/
public UFDateTime getZlbegintime () {
return this.zlbegintime;
 } 

/** 
* 设置租赁开始时间
*
* @param zlbegintime 租赁开始时间
*/
public void setZlbegintime ( UFDateTime zlbegintime) {
this.zlbegintime=zlbegintime;
 } 

/** 
* 获取租赁截止时间
*
* @return 租赁截止时间
*/
public UFDateTime getZlendtime () {
return this.zlendtime;
 } 

/** 
* 设置租赁截止时间
*
* @param zlendtime 租赁截止时间
*/
public void setZlendtime ( UFDateTime zlendtime) {
this.zlendtime=zlendtime;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer_fcxx");
  }

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
}