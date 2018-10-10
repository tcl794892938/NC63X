package nc.vo.zl.ld_parkcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ParkcontractBVO extends SuperVO {
/**
*自定义项3
*/
public String attrname;
/**
*车位区
*/
public String parkarea;
/**
*车位号
*/
public String parknum;
/**
*联系电话
*/
public String phonenum;
/**
*客户
*/
public String pk_customer;
/**
*上层单据主键
*/
public String pk_parkcontract;
/**
*基本信息主键
*/
public String pk_parkcontract_b;
/**
*车牌号
*/
public String platenum;
/**
*备注
*/
public String remark;
/**
*行号
*/
public String rowno;
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

private Integer dr=0;


public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取自定义项3
*
* @return 自定义项3
*/
public String getAttrname () {
return this.attrname;
 } 

/** 
* 设置自定义项3
*
* @param attrname 自定义项3
*/
public void setAttrname ( String attrname) {
this.attrname=attrname;
 } 

/** 
* 获取车位区
*
* @return 车位区
*/
public String getParkarea () {
return this.parkarea;
 } 

/** 
* 设置车位区
*
* @param parkarea 车位区
*/
public void setParkarea ( String parkarea) {
this.parkarea=parkarea;
 } 

/** 
* 获取车位号
*
* @return 车位号
*/
public String getParknum () {
return this.parknum;
 } 

/** 
* 设置车位号
*
* @param parknum 车位号
*/
public void setParknum ( String parknum) {
this.parknum=parknum;
 } 

/** 
* 获取联系电话
*
* @return 联系电话
*/
public String getPhonenum () {
return this.phonenum;
 } 

/** 
* 设置联系电话
*
* @param phonenum 联系电话
*/
public void setPhonenum ( String phonenum) {
this.phonenum=phonenum;
 } 

/** 
* 获取客户
*
* @return 客户
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* 设置客户
*
* @param pk_customer 客户
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_parkcontract () {
return this.pk_parkcontract;
 } 

/** 
* 设置上层单据主键
*
* @param pk_parkcontract 上层单据主键
*/
public void setPk_parkcontract ( String pk_parkcontract) {
this.pk_parkcontract=pk_parkcontract;
 } 

/** 
* 获取基本信息主键
*
* @return 基本信息主键
*/
public String getPk_parkcontract_b () {
return this.pk_parkcontract_b;
 } 

/** 
* 设置基本信息主键
*
* @param pk_parkcontract_b 基本信息主键
*/
public void setPk_parkcontract_b ( String pk_parkcontract_b) {
this.pk_parkcontract_b=pk_parkcontract_b;
 } 

/** 
* 获取车牌号
*
* @return 车牌号
*/
public String getPlatenum () {
return this.platenum;
 } 

/** 
* 设置车牌号
*
* @param platenum 车牌号
*/
public void setPlatenum ( String platenum) {
this.platenum=platenum;
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
* 获取行号
*
* @return 行号
*/
public String getRowno () {
return this.rowno;
 } 

/** 
* 设置行号
*
* @param rowno 行号
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.parkcontract_b");
  }
}