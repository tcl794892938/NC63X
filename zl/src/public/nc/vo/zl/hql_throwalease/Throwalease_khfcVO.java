package nc.vo.zl.hql_throwalease;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Throwalease_khfcVO extends SuperVO {
/**
*楼栋
*/
public String pk_building;
/**
*客户名称
*/
public String pk_customer;
/**
*房产名称
*/
public String pk_housesource;
/**
*上层单据主键
*/
public String pk_throwalease;
/**
*客户房产信息主键
*/
public String pk_throwaleasekhfc;
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
*退租日期
*/
public UFDate tzdate;
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
 * 合同表体房产主键
 */
public String pk_fc;

public String getPk_fc() {
	return pk_fc;
}

public void setPk_fc(String pk_fc) {
	this.pk_fc = pk_fc;
}

/** 
* 获取楼栋
*
* @return 楼栋
*/
public String getPk_building () {
return this.pk_building;
 } 

/** 
* 设置楼栋
*
* @param pk_building 楼栋
*/
public void setPk_building ( String pk_building) {
this.pk_building=pk_building;
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
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_throwalease () {
return this.pk_throwalease;
 } 

/** 
* 设置上层单据主键
*
* @param pk_throwalease 上层单据主键
*/
public void setPk_throwalease ( String pk_throwalease) {
this.pk_throwalease=pk_throwalease;
 } 

/** 
* 获取客户房产信息主键
*
* @return 客户房产信息主键
*/
public String getPk_throwaleasekhfc () {
return this.pk_throwaleasekhfc;
 } 

/** 
* 设置客户房产信息主键
*
* @param pk_throwaleasekhfc 客户房产信息主键
*/
public void setPk_throwaleasekhfc ( String pk_throwaleasekhfc) {
this.pk_throwaleasekhfc=pk_throwaleasekhfc;
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
* 获取退租日期
*
* @return 退租日期
*/
public UFDate getTzdate () {
return this.tzdate;
 } 

/** 
* 设置退租日期
*
* @param tzdate 退租日期
*/
public void setTzdate ( UFDate tzdate) {
this.tzdate=tzdate;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.throwalease_khfc");
  }
}