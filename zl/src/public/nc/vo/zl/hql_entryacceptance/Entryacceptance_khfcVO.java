package nc.vo.zl.hql_entryacceptance;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Entryacceptance_khfcVO extends SuperVO {
/**
*进场日期
*/
public UFDate entrydate;
/**
*楼栋
*/
public String pk_buildingfile;
/**
*客户名称
*/
public String pk_customer;
/**
*上层单据主键
*/
public String pk_entryacceptance;
/**
*客户房产信息主键
*/
public String pk_entryacceptance_khfc;
/**
*房产名称
*/
public String pk_housesource;
/**
*备注
*/
public String remark;
/**
*房号
*/
public String roomnumber;
/**
*行号
*/
public String rowno;
/**
*时间戳
*/
public UFDateTime ts;
/**
*单元
*/
public String unit;
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

private Integer dr = 0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取进场日期
*
* @return 进场日期
*/
public UFDate getEntrydate () {
return this.entrydate;
 } 

/** 
* 设置进场日期
*
* @param entrydate 进场日期
*/
public void setEntrydate ( UFDate entrydate) {
this.entrydate=entrydate;
 } 

/** 
* 获取楼栋
*
* @return 楼栋
*/
public String getPk_buildingfile () {
return this.pk_buildingfile;
 } 

/** 
* 设置楼栋
*
* @param pk_buildingfile 楼栋
*/
public void setPk_buildingfile ( String pk_buildingfile) {
this.pk_buildingfile=pk_buildingfile;
 } 

/** 
* 获取客户名称
*
* @return 客户名称
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* 设置客户名称
*
* @param pk_customer 客户名称
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_entryacceptance () {
return this.pk_entryacceptance;
 } 

/** 
* 设置上层单据主键
*
* @param pk_entryacceptance 上层单据主键
*/
public void setPk_entryacceptance ( String pk_entryacceptance) {
this.pk_entryacceptance=pk_entryacceptance;
 } 

/** 
* 获取客户房产信息主键
*
* @return 客户房产信息主键
*/
public String getPk_entryacceptance_khfc () {
return this.pk_entryacceptance_khfc;
 } 

/** 
* 设置客户房产信息主键
*
* @param pk_entryacceptance_khfc 客户房产信息主键
*/
public void setPk_entryacceptance_khfc ( String pk_entryacceptance_khfc) {
this.pk_entryacceptance_khfc=pk_entryacceptance_khfc;
 } 

/** 
* 获取房产名称
*
* @return 房产名称
*/
public String getPk_housesource () {
return this.pk_housesource;
 } 

/** 
* 设置房产名称
*
* @param pk_housesource 房产名称
*/
public void setPk_housesource ( String pk_housesource) {
this.pk_housesource=pk_housesource;
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
* 获取房号
*
* @return 房号
*/
public String getRoomnumber () {
return this.roomnumber;
 } 

/** 
* 设置房号
*
* @param roomnumber 房号
*/
public void setRoomnumber ( String roomnumber) {
this.roomnumber=roomnumber;
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
* 获取单元
*
* @return 单元
*/
public String getUnit () {
return this.unit;
 } 

/** 
* 设置单元
*
* @param unit 单元
*/
public void setUnit ( String unit) {
this.unit=unit;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.entryacceptance_khfc");
  }
}