package nc.vo.zl.cwf_carconedit;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CarconeditBVO extends SuperVO {
/**
*联系电话
*/
public String phone;
/**
*车位区
*/
public String pk_building;
/**
*上层单据主键
*/
public String pk_carconedit;
/**
*主键
*/
public String pk_carconedit_b;
/**
*客户
*/
public String pk_customer;
/**
*车位号
*/
public String pk_house;
/**
*车牌号
*/
public String pk_plate;
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
* 获取联系电话
*
* @return 联系电话
*/
public String getPhone () {
return this.phone;
 } 

/** 
* 设置联系电话
*
* @param phone 联系电话
*/
public void setPhone ( String phone) {
this.phone=phone;
 } 

/** 
* 获取车位区
*
* @return 车位区
*/
public String getPk_building () {
return this.pk_building;
 } 

/** 
* 设置车位区
*
* @param pk_building 车位区
*/
public void setPk_building ( String pk_building) {
this.pk_building=pk_building;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_carconedit () {
return this.pk_carconedit;
 } 

/** 
* 设置上层单据主键
*
* @param pk_carconedit 上层单据主键
*/
public void setPk_carconedit ( String pk_carconedit) {
this.pk_carconedit=pk_carconedit;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_carconedit_b () {
return this.pk_carconedit_b;
 } 

/** 
* 设置主键
*
* @param pk_carconedit_b 主键
*/
public void setPk_carconedit_b ( String pk_carconedit_b) {
this.pk_carconedit_b=pk_carconedit_b;
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
* 获取车位号
*
* @return 车位号
*/
public String getPk_house () {
return this.pk_house;
 } 

/** 
* 设置车位号
*
* @param pk_house 车位号
*/
public void setPk_house ( String pk_house) {
this.pk_house=pk_house;
 } 

/** 
* 获取车牌号
*
* @return 车牌号
*/
public String getPk_plate () {
return this.pk_plate;
 } 

/** 
* 设置车牌号
*
* @param pk_plate 车牌号
*/
public void setPk_plate ( String pk_plate) {
this.pk_plate=pk_plate;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.carconedit_b");
  }
}