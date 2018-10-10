package nc.vo.zl.ld_parkcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ParkcontractFVO extends SuperVO {
/**
*费用截止日期
*/
public UFDate menddate;
/**
*费用开始日期
*/
public UFDate mstartdate;
/**
*实收金额
*/
public UFDouble ncollectemoney;
/**
*无税金额
*/
public UFDouble nfreetaxmoney;
/**
*应收金额
*/
public UFDouble nreceivemoney;
/**
*税额
*/
public UFDouble ntaxmoney;
/**
*车位区
*/
public String parkarea;
/**
*车位号
*/
public String parknum;
/**
*应交日期
*/
public UFDate paydate;
/**
*收费项目
*/
public String pk_costproject;
/**
*上层单据主键
*/
public String pk_parkcontract;
/**
*财务主键
*/
public String pk_parkcontract_f;
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
public UFDouble nconfirmed;
public String vdef3;
private Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
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
* 获取实收金额
*
* @return 实收金额
*/
public UFDouble getNcollectemoney () {
return this.ncollectemoney;
 } 

/** 
* 设置实收金额
*
* @param ncollectemoney 实收金额
*/
public void setNcollectemoney ( UFDouble ncollectemoney) {
this.ncollectemoney=ncollectemoney;
 } 

/** 
* 获取无税金额
*
* @return 无税金额
*/
public UFDouble getNfreetaxmoney () {
return this.nfreetaxmoney;
 } 

/** 
* 设置无税金额
*
* @param nfreetaxmoney 无税金额
*/
public void setNfreetaxmoney ( UFDouble nfreetaxmoney) {
this.nfreetaxmoney=nfreetaxmoney;
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
* 获取税额
*
* @return 税额
*/
public UFDouble getNtaxmoney () {
return this.ntaxmoney;
 } 

/** 
* 设置税额
*
* @param ntaxmoney 税额
*/
public void setNtaxmoney ( UFDouble ntaxmoney) {
this.ntaxmoney=ntaxmoney;
 } 

/** 
* 获取车位区
*
* @return 车位区
*/
public String getParkarea () {
return this.parkarea;
 } 

/** 
* 设置车位区
*
* @param parkarea 车位区
*/
public void setParkarea ( String parkarea) {
this.parkarea=parkarea;
 } 

/** 
* 获取车位号
*
* @return 车位号
*/
public String getParknum () {
return this.parknum;
 } 

/** 
* 设置车位号
*
* @param parknum 车位号
*/
public void setParknum ( String parknum) {
this.parknum=parknum;
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
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_parkcontract () {
return this.pk_parkcontract;
 } 

/** 
* 设置上层单据主键
*
* @param pk_parkcontract 上层单据主键
*/
public void setPk_parkcontract ( String pk_parkcontract) {
this.pk_parkcontract=pk_parkcontract;
 } 

/** 
* 获取财务主键
*
* @return 财务主键
*/
public String getPk_parkcontract_f () {
return this.pk_parkcontract_f;
 } 

/** 
* 设置财务主键
*
* @param pk_parkcontract_f 财务主键
*/
public void setPk_parkcontract_f ( String pk_parkcontract_f) {
this.pk_parkcontract_f=pk_parkcontract_f;
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


  public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

@Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.parkcontract_f");
  }
}