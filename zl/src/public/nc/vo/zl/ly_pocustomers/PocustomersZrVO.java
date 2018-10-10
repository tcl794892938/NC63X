package nc.vo.zl.ly_pocustomers;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PocustomersZrVO extends SuperVO {
/**
*是否最新
*/
public UFBoolean isnew;
/**
*上层单据主键
*/
public String pk_pocustomers;
/**
*主键
*/
public String pk_pocustomers_zr;
/**
*记录人
*/
public String recorder;
/**
*客户跟踪状态
*/
public Integer tcustomertype;
/**
*跟踪日期
*/
public UFDate tdate;
/**
*时间戳
*/
public UFDateTime ts;
/**
*跟踪时间
*/
public UFDateTime ttime;
/**
*跟踪方式
*/
public String ttype;
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
 * 是否删除
 */
public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* 获取是否最新
*
* @return 是否最新
*/
public UFBoolean getIsnew () {
return this.isnew;
 } 

/** 
* 设置是否最新
*
* @param isnew 是否最新
*/
public void setIsnew ( UFBoolean isnew) {
this.isnew=isnew;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_pocustomers () {
return this.pk_pocustomers;
 } 

/** 
* 设置上层单据主键
*
* @param pk_pocustomers 上层单据主键
*/
public void setPk_pocustomers ( String pk_pocustomers) {
this.pk_pocustomers=pk_pocustomers;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_pocustomers_zr () {
return this.pk_pocustomers_zr;
 } 

/** 
* 设置主键
*
* @param pk_pocustomers_zr 主键
*/
public void setPk_pocustomers_zr ( String pk_pocustomers_zr) {
this.pk_pocustomers_zr=pk_pocustomers_zr;
 } 

/** 
* 获取记录人
*
* @return 记录人
*/
public String getRecorder () {
return this.recorder;
 } 

/** 
* 设置记录人
*
* @param recorder 记录人
*/
public void setRecorder ( String recorder) {
this.recorder=recorder;
 } 

/** 
* 获取客户跟踪状态
*
* @return 客户跟踪状态
* @see String
*/
public Integer getTcustomertype () {
return this.tcustomertype;
 } 

/** 
* 设置客户跟踪状态
*
* @param tcustomertype 客户跟踪状态
* @see String
*/
public void setTcustomertype ( Integer tcustomertype) {
this.tcustomertype=tcustomertype;
 } 

/** 
* 获取跟踪日期
*
* @return 跟踪日期
*/
public UFDate getTdate () {
return this.tdate;
 } 

/** 
* 设置跟踪日期
*
* @param tdate 跟踪日期
*/
public void setTdate ( UFDate tdate) {
this.tdate=tdate;
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
* 获取跟踪时间
*
* @return 跟踪时间
*/
public UFDateTime getTtime () {
return this.ttime;
 } 

/** 
* 设置跟踪时间
*
* @param ttime 跟踪时间
*/
public void setTtime ( UFDateTime ttime) {
this.ttime=ttime;
 } 

/** 
* 获取跟踪方式
*
* @return 跟踪方式
*/
public String getTtype () {
return this.ttype;
 } 

/** 
* 设置跟踪方式
*
* @param ttype 跟踪方式
*/
public void setTtype ( String ttype) {
this.ttype=ttype;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.pocustomers_zr");
  }
}