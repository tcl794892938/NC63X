package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractZzqVO extends SuperVO {
/**
*增长开始日期
*/
public UFDate dstartdate;
/**
*增长后日租金
*/
public UFDouble ndaymny;
/**
*日租金增长额
*/
public UFDouble ndayzzmny;
/**
*增长后月租金
*/
public UFDouble nmonthmny;
/**
*月租金增长额
*/
public UFDouble nmonthzzmny;
/**
*增长后年租金
*/
public UFDouble nyearmny;
/**
*年租金增长额
*/
public UFDouble nyearzzmny;
/**
*增长比例(%)
*/
public UFDouble nzzrate;
/**
*上层单据主键
*/
public String pk_contract;
/**
*主键
*/
public String pk_contract_zzq;
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

public UFDouble nmny;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* 获取增长开始日期
*
* @return 增长开始日期
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* 设置增长开始日期
*
* @param dstartdate 增长开始日期
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* 获取增长后日租金
*
* @return 增长后日租金
*/
public UFDouble getNdaymny () {
return this.ndaymny;
 } 

/** 
* 设置增长后日租金
*
* @param ndaymny 增长后日租金
*/
public void setNdaymny ( UFDouble ndaymny) {
this.ndaymny=ndaymny;
 } 

/** 
* 获取日租金增长额
*
* @return 日租金增长额
*/
public UFDouble getNdayzzmny () {
return this.ndayzzmny;
 } 

/** 
* 设置日租金增长额
*
* @param ndayzzmny 日租金增长额
*/
public void setNdayzzmny ( UFDouble ndayzzmny) {
this.ndayzzmny=ndayzzmny;
 } 

/** 
* 获取增长后月租金
*
* @return 增长后月租金
*/
public UFDouble getNmonthmny () {
return this.nmonthmny;
 } 

/** 
* 设置增长后月租金
*
* @param nmonthmny 增长后月租金
*/
public void setNmonthmny ( UFDouble nmonthmny) {
this.nmonthmny=nmonthmny;
 } 

/** 
* 获取月租金增长额
*
* @return 月租金增长额
*/
public UFDouble getNmonthzzmny () {
return this.nmonthzzmny;
 } 

/** 
* 设置月租金增长额
*
* @param nmonthzzmny 月租金增长额
*/
public void setNmonthzzmny ( UFDouble nmonthzzmny) {
this.nmonthzzmny=nmonthzzmny;
 } 

/** 
* 获取增长后年租金
*
* @return 增长后年租金
*/
public UFDouble getNyearmny () {
return this.nyearmny;
 } 

/** 
* 设置增长后年租金
*
* @param nyearmny 增长后年租金
*/
public void setNyearmny ( UFDouble nyearmny) {
this.nyearmny=nyearmny;
 } 

/** 
* 获取年租金增长额
*
* @return 年租金增长额
*/
public UFDouble getNyearzzmny () {
return this.nyearzzmny;
 } 

/** 
* 设置年租金增长额
*
* @param nyearzzmny 年租金增长额
*/
public void setNyearzzmny ( UFDouble nyearzzmny) {
this.nyearzzmny=nyearzzmny;
 } 

/** 
* 获取增长比例(%)
*
* @return 增长比例(%)
*/
public UFDouble getNzzrate () {
return this.nzzrate;
 } 

/** 
* 设置增长比例(%)
*
* @param nzzrate 增长比例(%)
*/
public void setNzzrate ( UFDouble nzzrate) {
this.nzzrate=nzzrate;
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
public String getPk_contract_zzq () {
return this.pk_contract_zzq;
 } 

/** 
* 设置主键
*
* @param pk_contract_zzq 主键
*/
public void setPk_contract_zzq ( String pk_contract_zzq) {
this.pk_contract_zzq=pk_contract_zzq;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zzq");
  }

public UFDouble getNmny() {
	return nmny;
}

public void setNmny(UFDouble nmny) {
	this.nmny = nmny;
}
}