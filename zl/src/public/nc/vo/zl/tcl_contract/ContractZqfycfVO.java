package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractZqfycfVO extends SuperVO {
/**
*费用截止日期
*/
public UFDate denddate;
/**
*应收款日期
*/
public UFDate drecdate;
/**
*费用起始日期
*/
public UFDate dstartdate;
/**
*费用金额
*/
public UFDouble nfymny;
/**
*无税金额
*/
public UFDouble nnotaxmny;
/**
*已收金额
*/
public UFDouble nskmny;
/**
*税额
*/
public UFDouble ntaxmny;
/**
*优惠金额
*/
public UFDouble nyhmny;
/**
*应收金额
*/
public UFDouble nysmny;
/**
*上层单据主键
*/
public String pk_contract;
/**
*主键
*/
public String pk_contract_zqfycf;
/**
*收费项目
*/
public String pk_costproject;
/**
*客户名称
*/
public String pk_customer;
/**
*房产名称
*/
public String pk_house;
/**
*应收月份
*/
public String pk_month;
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
public UFDouble nconfirmed;

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

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
public UFDate getDenddate () {
return this.denddate;
 } 

/** 
* 设置费用截止日期
*
* @param denddate 费用截止日期
*/
public void setDenddate ( UFDate denddate) {
this.denddate=denddate;
 } 

/** 
* 获取应收款日期
*
* @return 应收款日期
*/
public UFDate getDrecdate () {
return this.drecdate;
 } 

/** 
* 设置应收款日期
*
* @param drecdate 应收款日期
*/
public void setDrecdate ( UFDate drecdate) {
this.drecdate=drecdate;
 } 

/** 
* 获取费用起始日期
*
* @return 费用起始日期
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* 设置费用起始日期
*
* @param dstartdate 费用起始日期
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* 获取费用金额
*
* @return 费用金额
*/
public UFDouble getNfymny () {
return this.nfymny;
 } 

/** 
* 设置费用金额
*
* @param nfymny 费用金额
*/
public void setNfymny ( UFDouble nfymny) {
this.nfymny=nfymny;
 } 

/** 
* 获取无税金额
*
* @return 无税金额
*/
public UFDouble getNnotaxmny () {
return this.nnotaxmny;
 } 

/** 
* 设置无税金额
*
* @param nnotaxmny 无税金额
*/
public void setNnotaxmny ( UFDouble nnotaxmny) {
this.nnotaxmny=nnotaxmny;
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
* 获取税额
*
* @return 税额
*/
public UFDouble getNtaxmny () {
return this.ntaxmny;
 } 

/** 
* 设置税额
*
* @param ntaxmny 税额
*/
public void setNtaxmny ( UFDouble ntaxmny) {
this.ntaxmny=ntaxmny;
 } 

/** 
* 获取优惠金额
*
* @return 优惠金额
*/
public UFDouble getNyhmny () {
return this.nyhmny;
 } 

/** 
* 设置优惠金额
*
* @param nyhmny 优惠金额
*/
public void setNyhmny ( UFDouble nyhmny) {
this.nyhmny=nyhmny;
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
public String getPk_contract_zqfycf () {
return this.pk_contract_zqfycf;
 } 

/** 
* 设置主键
*
* @param pk_contract_zqfycf 主键
*/
public void setPk_contract_zqfycf ( String pk_contract_zqfycf) {
this.pk_contract_zqfycf=pk_contract_zqfycf;
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
* 获取应收月份
*
* @return 应收月份
*/
public String getPk_month () {
return this.pk_month;
 } 

/** 
* 设置应收月份
*
* @param pk_month 应收月份
*/
public void setPk_month ( String pk_month) {
this.pk_month=pk_month;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zqfycf");
  }
}