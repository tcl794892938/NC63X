package nc.vo.zl.lm_customer;

import nc.ui.eom.intactrpt.model.intactrpt_config;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_carVO extends SuperVO {
/**
*车牌号
*/
public String carnum;
/**
*合同开始日期
*/
public UFDate htbegindate;
/**
*合同截止日期
*/
public UFDate htenddate;
/**
*上层单据主键
*/
public String pk_customer;
/**
*主键
*/
public String pk_customercar;
/**
*备注
*/
public String remark;
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



public  Integer DR=0;



public Integer getDr() {
	return DR;
}

public void setDr(Integer dR) {
	DR = dR;
}


/** 
* 获取车牌号
*
* @return 车牌号
*/
public String getCarnum () {
return this.carnum;
 } 

/** 
* 设置车牌号
*
* @param carnum 车牌号
*/
public void setCarnum ( String carnum) {
this.carnum=carnum;
 } 

/** 
* 获取合同开始日期
*
* @return 合同开始日期
*/
public UFDate getHtbegindate () {
return this.htbegindate;
 } 

/** 
* 设置合同开始日期
*
* @param htbegindate 合同开始日期
*/
public void setHtbegindate ( UFDate htbegindate) {
this.htbegindate=htbegindate;
 } 

/** 
* 获取合同截止日期
*
* @return 合同截止日期
*/
public UFDate getHtenddate () {
return this.htenddate;
 } 

/** 
* 设置合同截止日期
*
* @param htenddate 合同截止日期
*/
public void setHtenddate ( UFDate htenddate) {
this.htenddate=htenddate;
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
public String getPk_customercar () {
return this.pk_customercar;
 } 

/** 
* 设置主键
*
* @param pk_customercar 主键
*/
public void setPk_customercar ( String pk_customercar) {
this.pk_customercar=pk_customercar;
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getRemark () {
return this.remark;
 } 

/** 
* 设置备注
*
* @param remark 备注
*/
public void setRemark ( String remark) {
this.remark=remark;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer_car");
  }
}