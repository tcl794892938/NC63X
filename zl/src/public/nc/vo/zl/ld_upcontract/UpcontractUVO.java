package nc.vo.zl.ld_upcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class UpcontractUVO extends SuperVO {
/**
*会计月
*/
public String accountmonth;
/**
*无税金额
*/
public UFDouble freetaxmoney;
/**
*已收金额
*/
public UFDouble getmoney;
/**
*费用截止日期
*/
public UFDate menddate;
/**
*应收款日期
*/
public UFDate moneydate;
/**
*费用起始日期
*/
public UFDate mstartdate;
/**
*收费项目
*/
public String pk_costproject;
/**
*客户名称
*/
public String pk_customer;
/**
*上层单据主键
*/
public String pk_upcontract;
/**
*修订子表主键
*/
public String pk_upcontract_u;
/**
*应收金额
*/
public UFDouble receivemoney;
/**
*备注
*/
public String remark;
/**
*行号
*/
public String rowno;
/**
*税额
*/
public UFDouble taxmoney;
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
* 获取会计月
*
* @return 会计月
*/
public String getAccountmonth () {
return this.accountmonth;
 } 

/** 
* 设置会计月
*
* @param accountmonth 会计月
*/
public void setAccountmonth ( String accountmonth) {
this.accountmonth=accountmonth;
 } 

/** 
* 获取无税金额
*
* @return 无税金额
*/
public UFDouble getFreetaxmoney () {
return this.freetaxmoney;
 } 

/** 
* 设置无税金额
*
* @param freetaxmoney 无税金额
*/
public void setFreetaxmoney ( UFDouble freetaxmoney) {
this.freetaxmoney=freetaxmoney;
 } 

/** 
* 获取已收金额
*
* @return 已收金额
*/
public UFDouble getGetmoney () {
return this.getmoney;
 } 

/** 
* 设置已收金额
*
* @param getmoney 已收金额
*/
public void setGetmoney ( UFDouble getmoney) {
this.getmoney=getmoney;
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
* 获取应收款日期
*
* @return 应收款日期
*/
public UFDate getMoneydate () {
return this.moneydate;
 } 

/** 
* 设置应收款日期
*
* @param moneydate 应收款日期
*/
public void setMoneydate ( UFDate moneydate) {
this.moneydate=moneydate;
 } 

/** 
* 获取费用起始日期
*
* @return 费用起始日期
*/
public UFDate getMstartdate () {
return this.mstartdate;
 } 

/** 
* 设置费用起始日期
*
* @param mstartdate 费用起始日期
*/
public void setMstartdate ( UFDate mstartdate) {
this.mstartdate=mstartdate;
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
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_upcontract () {
return this.pk_upcontract;
 } 

/** 
* 设置上层单据主键
*
* @param pk_upcontract 上层单据主键
*/
public void setPk_upcontract ( String pk_upcontract) {
this.pk_upcontract=pk_upcontract;
 } 

/** 
* 获取修订子表主键
*
* @return 修订子表主键
*/
public String getPk_upcontract_u () {
return this.pk_upcontract_u;
 } 

/** 
* 设置修订子表主键
*
* @param pk_upcontract_u 修订子表主键
*/
public void setPk_upcontract_u ( String pk_upcontract_u) {
this.pk_upcontract_u=pk_upcontract_u;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getReceivemoney () {
return this.receivemoney;
 } 

/** 
* 设置应收金额
*
* @param receivemoney 应收金额
*/
public void setReceivemoney ( UFDouble receivemoney) {
this.receivemoney=receivemoney;
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
* 获取税额
*
* @return 税额
*/
public UFDouble getTaxmoney () {
return this.taxmoney;
 } 

/** 
* 设置税额
*
* @param taxmoney 税额
*/
public void setTaxmoney ( UFDouble taxmoney) {
this.taxmoney=taxmoney;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.upcontract_u");
  }
}