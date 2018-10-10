package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractBzjVO extends SuperVO {
/**
*应交日期
*/
public UFDate drecdate;
/**
*欠收金额
*/
public UFDouble nqkmny;
/**
*已收金额
*/
public UFDouble nskmny;
/**
*已退金额
*/
public UFDouble ntkmny;
/**
*应收金额
*/
public UFDouble nysmny;
/**
*应退金额
*/
public UFDouble nytmny;
/**
*上层单据主键
*/
public String pk_contract;
/**
*主键
*/
public String pk_contract_bzj;
/**
*收费项目
*/
public String pk_costproject;
/**
*客户名称
*/
public String pk_customer;
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

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取应交日期
*
* @return 应交日期
*/
public UFDate getDrecdate () {
return this.drecdate;
 } 

/** 
* 设置应交日期
*
* @param drecdate 应交日期
*/
public void setDrecdate ( UFDate drecdate) {
this.drecdate=drecdate;
 } 

/** 
* 获取欠收金额
*
* @return 欠收金额
*/
public UFDouble getNqkmny () {
return this.nqkmny;
 } 

/** 
* 设置欠收金额
*
* @param nqkmny 欠收金额
*/
public void setNqkmny ( UFDouble nqkmny) {
this.nqkmny=nqkmny;
 } 

/** 
* 获取已收金额
*
* @return 已收金额
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* 设置已收金额
*
* @param nskmny 已收金额
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 

/** 
* 获取已退金额
*
* @return 已退金额
*/
public UFDouble getNtkmny () {
return this.ntkmny;
 } 

/** 
* 设置已退金额
*
* @param ntkmny 已退金额
*/
public void setNtkmny ( UFDouble ntkmny) {
this.ntkmny=ntkmny;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getNysmny () {
return this.nysmny;
 } 

/** 
* 设置应收金额
*
* @param nysmny 应收金额
*/
public void setNysmny ( UFDouble nysmny) {
this.nysmny=nysmny;
 } 

/** 
* 获取应退金额
*
* @return 应退金额
*/
public UFDouble getNytmny () {
return this.nytmny;
 } 

/** 
* 设置应退金额
*
* @param nytmny 应退金额
*/
public void setNytmny ( UFDouble nytmny) {
this.nytmny=nytmny;
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
public String getPk_contract_bzj () {
return this.pk_contract_bzj;
 } 

/** 
* 设置主键
*
* @param pk_contract_bzj 主键
*/
public void setPk_contract_bzj ( String pk_contract_bzj) {
this.pk_contract_bzj=pk_contract_bzj;
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getPk_costproject () {
return this.pk_costproject;
 } 

/** 
* 设置收费项目
*
* @param pk_costproject 收费项目
*/
public void setPk_costproject ( String pk_costproject) {
this.pk_costproject=pk_costproject;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_bzj");
  }
}