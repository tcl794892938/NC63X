package nc.vo.zl.hql_entryacceptance;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Entryacceptance_jcysVO extends SuperVO {
/**
*上层单据主键
*/
public String pk_entryacceptance;
/**
*进场验收登记主键
*/
public String pk_entryacceptance_jcys;
/**
*验收项目
*/
public String pk_jt_acceptance;
/**
*序号
*/
public String rowno;
/**
*序号2
*/
public String serialnumber;
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

private Integer dr = 0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
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
* 获取进场验收登记主键
*
* @return 进场验收登记主键
*/
public String getPk_entryacceptance_jcys () {
return this.pk_entryacceptance_jcys;
 } 

/** 
* 设置进场验收登记主键
*
* @param pk_entryacceptance_jcys 进场验收登记主键
*/
public void setPk_entryacceptance_jcys ( String pk_entryacceptance_jcys) {
this.pk_entryacceptance_jcys=pk_entryacceptance_jcys;
 } 

/** 
* 获取验收项目
*
* @return 验收项目
*/
public String getPk_jt_acceptance () {
return this.pk_jt_acceptance;
 } 

/** 
* 设置验收项目
*
* @param pk_jt_acceptance 验收项目
*/
public void setPk_jt_acceptance ( String pk_jt_acceptance) {
this.pk_jt_acceptance=pk_jt_acceptance;
 } 

/** 
* 获取序号
*
* @return 序号
*/
public String getRowno () {
return this.rowno;
 } 

/** 
* 设置序号
*
* @param rowno 序号
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
 } 

/** 
* 获取序号2
*
* @return 序号2
*/
public String getSerialnumber () {
return this.serialnumber;
 } 

/** 
* 设置序号2
*
* @param serialnumber 序号2
*/
public void setSerialnumber ( String serialnumber) {
this.serialnumber=serialnumber;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.entryacceptance_jcys");
  }
}