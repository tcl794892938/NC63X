package nc.vo.zl.lyw_billconfirmed;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillconfirmedBVO extends SuperVO {
/**
*已确认收入金额
*/
public UFDouble amountconfirmed;
/**
*本次确认收入
*/
public UFDouble amountconfirming;
/**
*应收金额
*/
public UFDouble amountreceivable;
/**
*楼栋号
*/
public String buildno;
/**
*会计年月
*/
public String caccountperiod;
/**
*收费项目
*/
public String chargingproject;
/**
*费用截止日期
*/
public UFDate dfeeenddate;
/**
*费用开始日期
*/
public UFDate dfeestartdate;
/**
*房产名称
*/
public String houseproperty;
/**
*上层单据主键
*/
public String pk_billconfirmed;
/**
*收入确认单表体主键
*/
public String pk_billconfirmedb;
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
*单据号
*/
public String vbillno;
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
*来源单据表体id
*/
public String vsrcbid;
/**
*来源单据id
*/
public String vsrcid;
/**
*来源单据类型
*/
public String vsrctype;
public Integer dr;
public UFDate dreccollectdate;
public UFDouble nrentarea;
public UFDouble nnotaxmny;
public UFDouble ntaxmny;
public UFDouble ntaxrate;
public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取已确认收入金额
*
* @return 已确认收入金额
*/
public UFDouble getAmountconfirmed () {
return this.amountconfirmed;
 } 

/** 
* 设置已确认收入金额
*
* @param amountconfirmed 已确认收入金额
*/
public void setAmountconfirmed ( UFDouble amountconfirmed) {
this.amountconfirmed=amountconfirmed;
 } 

/** 
* 获取本次确认收入
*
* @return 本次确认收入
*/
public UFDouble getAmountconfirming () {
return this.amountconfirming;
 } 

/** 
* 设置本次确认收入
*
* @param amountconfirming 本次确认收入
*/
public void setAmountconfirming ( UFDouble amountconfirming) {
this.amountconfirming=amountconfirming;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getAmountreceivable () {
return this.amountreceivable;
 } 

/** 
* 设置应收金额
*
* @param amountreceivable 应收金额
*/
public void setAmountreceivable ( UFDouble amountreceivable) {
this.amountreceivable=amountreceivable;
 } 

/** 
* 获取楼栋号
*
* @return 楼栋号
*/
public String getBuildno () {
return this.buildno;
 } 

/** 
* 设置楼栋号
*
* @param buildno 楼栋号
*/
public void setBuildno ( String buildno) {
this.buildno=buildno;
 } 

/** 
* 获取会计年月
*
* @return 会计年月
*/
public String getCaccountperiod () {
return this.caccountperiod;
 } 

/** 
* 设置会计年月
*
* @param caccountperiod 会计年月
*/
public void setCaccountperiod ( String caccountperiod) {
this.caccountperiod=caccountperiod;
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getChargingproject () {
return this.chargingproject;
 } 

/** 
* 设置收费项目
*
* @param chargingproject 收费项目
*/
public void setChargingproject ( String chargingproject) {
this.chargingproject=chargingproject;
 } 

/** 
* 获取费用截止日期
*
* @return 费用截止日期
*/
public UFDate getDfeeenddate () {
return this.dfeeenddate;
 } 

/** 
* 设置费用截止日期
*
* @param dfeeenddate 费用截止日期
*/
public void setDfeeenddate ( UFDate dfeeenddate) {
this.dfeeenddate=dfeeenddate;
 } 

/** 
* 获取费用开始日期
*
* @return 费用开始日期
*/
public UFDate getDfeestartdate () {
return this.dfeestartdate;
 } 

/** 
* 设置费用开始日期
*
* @param dfeestartdate 费用开始日期
*/
public void setDfeestartdate ( UFDate dfeestartdate) {
this.dfeestartdate=dfeestartdate;
 } 

/** 
* 获取房产名称
*
* @return 房产名称
*/
public String getHouseproperty () {
return this.houseproperty;
 } 

/** 
* 设置房产名称
*
* @param houseproperty 房产名称
*/
public void setHouseproperty ( String houseproperty) {
this.houseproperty=houseproperty;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_billconfirmed () {
return this.pk_billconfirmed;
 } 

/** 
* 设置上层单据主键
*
* @param pk_billconfirmed 上层单据主键
*/
public void setPk_billconfirmed ( String pk_billconfirmed) {
this.pk_billconfirmed=pk_billconfirmed;
 } 

/** 
* 获取收入确认单表体主键
*
* @return 收入确认单表体主键
*/
public String getPk_billconfirmedb () {
return this.pk_billconfirmedb;
 } 

/** 
* 设置收入确认单表体主键
*
* @param pk_billconfirmedb 收入确认单表体主键
*/
public void setPk_billconfirmedb ( String pk_billconfirmedb) {
this.pk_billconfirmedb=pk_billconfirmedb;
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
* 获取单据号
*
* @return 单据号
*/
public String getVbillno () {
return this.vbillno;
 } 

/** 
* 设置单据号
*
* @param vbillno 单据号
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
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
* 获取来源单据表体id
*
* @return 来源单据表体id
*/
public String getVsrcbid () {
return this.vsrcbid;
 } 

/** 
* 设置来源单据表体id
*
* @param vsrcbid 来源单据表体id
*/
public void setVsrcbid ( String vsrcbid) {
this.vsrcbid=vsrcbid;
 } 

/** 
* 获取来源单据id
*
* @return 来源单据id
*/
public String getVsrcid () {
return this.vsrcid;
 } 

/** 
* 设置来源单据id
*
* @param vsrcid 来源单据id
*/
public void setVsrcid ( String vsrcid) {
this.vsrcid=vsrcid;
 } 

/** 
* 获取来源单据类型
*
* @return 来源单据类型
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* 设置来源单据类型
*
* @param vsrctype 来源单据类型
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  public UFDate getDreccollectdate() {
	return dreccollectdate;
}

public void setDreccollectdate(UFDate dreccollectdate) {
	this.dreccollectdate = dreccollectdate;
}

public UFDouble getNrentarea() {
	return nrentarea;
}

public void setNrentarea(UFDouble nrentarea) {
	this.nrentarea = nrentarea;
}

public UFDouble getNnotaxmny() {
	return nnotaxmny;
}

public void setNnotaxmny(UFDouble nnotaxmny) {
	this.nnotaxmny = nnotaxmny;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

@Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.billconfirmedb");
  }
}