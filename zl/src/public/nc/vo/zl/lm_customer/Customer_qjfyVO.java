package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_qjfyVO extends SuperVO {
/**
*开始日期
*/
public UFDateTime begindate;
/**
*楼栋
*/
public String buildnum;
/**
*客户名称
*/
public String customername;
/**
*截止日期
*/
public UFDateTime enddate;
/**
*房产名称
*/
public String fcname;
/**
*会计年月
*/
public String kjny;
/**
*上层单据主键
*/
public String pk_customer;
/**
*主键
*/
public String pk_customerqjfy;
/**
*收费项目
*/
public String sfproject;
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
*应缴款日期
*/
public UFDateTime yjkdate;
/**
*应收金额
*/
public UFDouble ysamount;
/** 
* 获取开始日期
*
* @return 开始日期
*/
public UFDateTime getBegindate () {
return this.begindate;
 } 

/** 
* 设置开始日期
*
* @param begindate 开始日期
*/
public void setBegindate ( UFDateTime begindate) {
this.begindate=begindate;
 } 

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
* 获取客户名称
*
* @return 客户名称
*/
public String getCustomername () {
return this.customername;
 } 

/** 
* 设置客户名称
*
* @param customername 客户名称
*/
public void setCustomername ( String customername) {
this.customername=customername;
 } 

/** 
* 获取截止日期
*
* @return 截止日期
*/
public UFDateTime getEnddate () {
return this.enddate;
 } 

/** 
* 设置截止日期
*
* @param enddate 截止日期
*/
public void setEnddate ( UFDateTime enddate) {
this.enddate=enddate;
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
* 获取会计年月
*
* @return 会计年月
*/
public String getKjny () {
return this.kjny;
 } 

/** 
* 设置会计年月
*
* @param kjny 会计年月
*/
public void setKjny ( String kjny) {
this.kjny=kjny;
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
public String getPk_customerqjfy () {
return this.pk_customerqjfy;
 } 

/** 
* 设置主键
*
* @param pk_customerqjfy 主键
*/
public void setPk_customerqjfy ( String pk_customerqjfy) {
this.pk_customerqjfy=pk_customerqjfy;
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getSfproject () {
return this.sfproject;
 } 

/** 
* 设置收费项目
*
* @param sfproject 收费项目
*/
public void setSfproject ( String sfproject) {
this.sfproject=sfproject;
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
* 获取应缴款日期
*
* @return 应缴款日期
*/
public UFDateTime getYjkdate () {
return this.yjkdate;
 } 

/** 
* 设置应缴款日期
*
* @param yjkdate 应缴款日期
*/
public void setYjkdate ( UFDateTime yjkdate) {
this.yjkdate=yjkdate;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getYsamount () {
return this.ysamount;
 } 

/** 
* 设置应收金额
*
* @param ysamount 应收金额
*/
public void setYsamount ( UFDouble ysamount) {
this.ysamount=ysamount;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer_qjfy");
  }
}