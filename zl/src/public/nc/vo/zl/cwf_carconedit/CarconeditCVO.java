package nc.vo.zl.cwf_carconedit;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CarconeditCVO extends SuperVO {
/**
*费用截止日期
*/
public UFDate menddate;
/**
*费用开始日期
*/
public UFDate mstartdate;
/**
*已收金额
*/
public UFDouble ncollectemoney;
/**
*优惠金额
*/
public UFDouble ndiscountmoney;
/**
*应收金额
*/
public UFDouble nreceivemoney;
/**
*租约金额
*/
public UFDouble nrentmoney;
/**
*应交日期
*/
public UFDate paydate;
/**
*车位区
*/
public String pk_building;
/**
*上层单据主键
*/
public String pk_carconedit;
/**
*费用信息主键
*/
public String pk_carconedit_c;
/**
*收费项目
*/
public String pk_costproject;
/**
*车位号
*/
public String pk_house;
/**
*车牌号
*/
public String pk_plate;
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
*自定义项
*/
public String vdef3;
/** 
* 获取费用截止日期
*
* @return 费用截止日期
*/
public UFDate getMenddate () {
return this.menddate;
 } 

/** 
* 设置费用截止日期
*
* @param menddate 费用截止日期
*/
public void setMenddate ( UFDate menddate) {
this.menddate=menddate;
 } 

/** 
* 获取费用开始日期
*
* @return 费用开始日期
*/
public UFDate getMstartdate () {
return this.mstartdate;
 } 

/** 
* 设置费用开始日期
*
* @param mstartdate 费用开始日期
*/
public void setMstartdate ( UFDate mstartdate) {
this.mstartdate=mstartdate;
 } 

/** 
* 获取已收金额
*
* @return 已收金额
*/
public UFDouble getNcollectemoney () {
return this.ncollectemoney;
 } 

/** 
* 设置已收金额
*
* @param ncollectemoney 已收金额
*/
public void setNcollectemoney ( UFDouble ncollectemoney) {
this.ncollectemoney=ncollectemoney;
 } 

/** 
* 获取优惠金额
*
* @return 优惠金额
*/
public UFDouble getNdiscountmoney () {
return this.ndiscountmoney;
 } 

/** 
* 设置优惠金额
*
* @param ndiscountmoney 优惠金额
*/
public void setNdiscountmoney ( UFDouble ndiscountmoney) {
this.ndiscountmoney=ndiscountmoney;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getNreceivemoney () {
return this.nreceivemoney;
 } 

/** 
* 设置应收金额
*
* @param nreceivemoney 应收金额
*/
public void setNreceivemoney ( UFDouble nreceivemoney) {
this.nreceivemoney=nreceivemoney;
 } 

/** 
* 获取租约金额
*
* @return 租约金额
*/
public UFDouble getNrentmoney () {
return this.nrentmoney;
 } 

/** 
* 设置租约金额
*
* @param nrentmoney 租约金额
*/
public void setNrentmoney ( UFDouble nrentmoney) {
this.nrentmoney=nrentmoney;
 } 

/** 
* 获取应交日期
*
* @return 应交日期
*/
public UFDate getPaydate () {
return this.paydate;
 } 

/** 
* 设置应交日期
*
* @param paydate 应交日期
*/
public void setPaydate ( UFDate paydate) {
this.paydate=paydate;
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
* 获取费用信息主键
*
* @return 费用信息主键
*/
public String getPk_carconedit_c () {
return this.pk_carconedit_c;
 } 

/** 
* 设置费用信息主键
*
* @param pk_carconedit_c 费用信息主键
*/
public void setPk_carconedit_c ( String pk_carconedit_c) {
this.pk_carconedit_c=pk_carconedit_c;
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
* 获取自定义项
*
* @return 自定义项
*/
public String getVdef3 () {
return this.vdef3;
 } 

/** 
* 设置自定义项
*
* @param vdef3 自定义项
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.carconedit_c");
  }
}