package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractHouseVO extends SuperVO {
/**
*租赁面积
*/
public UFDouble narea;
/**
*日租金
*/
public UFDouble ndaymny;
/**
*月租金
*/
public UFDouble nmonthmny;
/**
*租金单价
*/
public UFDouble nprice;
/**
*年租金
*/
public UFDouble nyearmny;
/**
*楼栋信息
*/
public String pk_building;
/**
*上层单据主键
*/
public String pk_contract;
/**
*主键
*/
public String pk_contract_house;
/**
*房产名称
*/
public String pk_house;
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
*备注
*/
public String vmemo;

public String pk_customer;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* 获取租赁面积
*
* @return 租赁面积
*/
public UFDouble getNarea () {
return this.narea;
 } 

/** 
* 设置租赁面积
*
* @param narea 租赁面积
*/
public void setNarea ( UFDouble narea) {
this.narea=narea;
 } 

/** 
* 获取日租金
*
* @return 日租金
*/
public UFDouble getNdaymny () {
return this.ndaymny;
 } 

/** 
* 设置日租金
*
* @param ndaymny 日租金
*/
public void setNdaymny ( UFDouble ndaymny) {
this.ndaymny=ndaymny;
 } 

/** 
* 获取月租金
*
* @return 月租金
*/
public UFDouble getNmonthmny () {
return this.nmonthmny;
 } 

/** 
* 设置月租金
*
* @param nmonthmny 月租金
*/
public void setNmonthmny ( UFDouble nmonthmny) {
this.nmonthmny=nmonthmny;
 } 

/** 
* 获取租金单价
*
* @return 租金单价
*/
public UFDouble getNprice () {
return this.nprice;
 } 

/** 
* 设置租金单价
*
* @param nprice 租金单价
*/
public void setNprice ( UFDouble nprice) {
this.nprice=nprice;
 } 

/** 
* 获取年租金
*
* @return 年租金
*/
public UFDouble getNyearmny () {
return this.nyearmny;
 } 

/** 
* 设置年租金
*
* @param nyearmny 年租金
*/
public void setNyearmny ( UFDouble nyearmny) {
this.nyearmny=nyearmny;
 } 

/** 
* 获取楼栋信息
*
* @return 楼栋信息
*/
public String getPk_building () {
return this.pk_building;
 } 

/** 
* 设置楼栋信息
*
* @param pk_building 楼栋信息
*/
public void setPk_building ( String pk_building) {
this.pk_building=pk_building;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_contract () {
return this.pk_contract;
 } 

/** 
* 设置上层单据主键
*
* @param pk_contract 上层单据主键
*/
public void setPk_contract ( String pk_contract) {
this.pk_contract=pk_contract;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_contract_house () {
return this.pk_contract_house;
 } 

/** 
* 设置主键
*
* @param pk_contract_house 主键
*/
public void setPk_contract_house ( String pk_contract_house) {
this.pk_contract_house=pk_contract_house;
 } 

/** 
* 获取房产名称
*
* @return 房产名称
*/
public String getPk_house () {
return this.pk_house;
 } 

/** 
* 设置房产名称
*
* @param pk_house 房产名称
*/
public void setPk_house ( String pk_house) {
this.pk_house=pk_house;
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
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return this.vmemo;
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.vmemo=vmemo;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_house");
  }

public String getPk_customer() {
	return pk_customer;
}

public void setPk_customer(String pk_customer) {
	this.pk_customer = pk_customer;
}
}