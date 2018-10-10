package nc.vo.zl.hql_prepayment;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Prepayment_bVO extends SuperVO {
/**
*会计年月
*/
public String caccountperiod;
/**
*应收款日期
*/
public UFDate dysdate;
/**
*已收金额
*/
public UFDouble nskmny;
/**
*应收金额
*/
public UFDouble nysmny;
/**
 * 可冲抵金额
 */
public UFDouble noffsetmny;
/**
*收费项目
*/
public String pk_costproject;
/**
*上层单据主键
*/
public String pk_prepayment;
/**
*电费预缴单表体主键
*/
public String pk_prepayment_b;

/**
 * 是否已传会计平台
 */
public UFBoolean is_kjpt;

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
public UFDouble nconfirmed;

public Integer dr=0;

public String pk_customer;

public String getPk_customer() {
	return pk_customer;
}

public void setPk_customer(String pk_customer) {
	this.pk_customer = pk_customer;
}

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
* 获取会计年月
*
* @return 会计年月
*/
public String getCaccountperiod() {
	return caccountperiod;
}

/** 
* 设置会计年月
*
* @param accountingyear 会计年月
*/
public void setCaccountperiod(String caccountperiod) {
	this.caccountperiod = caccountperiod;
}

/** 
* 获取应收款日期
*
* @return 应收款日期
*/
public UFDate getDysdate () {
return this.dysdate;
 } 

/** 
* 设置应收款日期
*
* @param dysdate 应收款日期
*/
public void setDysdate ( UFDate dysdate) {
this.dysdate=dysdate;
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
* 获取可冲抵金额
*
* @return 可冲抵金额
*/
public UFDouble getNoffsetmny() {
	return noffsetmny;
}

/** 
* 设置可冲抵金额
*
* @param noffsetmny 可冲抵金额
*/
public void setNoffsetmny(UFDouble noffsetmny) {
	this.noffsetmny = noffsetmny;
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
public String getPk_prepayment () {
return this.pk_prepayment;
 } 

/** 
* 设置上层单据主键
*
* @param pk_prepayment 上层单据主键
*/
public void setPk_prepayment ( String pk_prepayment) {
this.pk_prepayment=pk_prepayment;
 } 

/** 
* 获取电费预缴单表体主键
*
* @return 电费预缴单表体主键
*/
public String getPk_prepayment_b () {
return this.pk_prepayment_b;
 } 

/** 
* 设置电费预缴单表体主键
*
* @param pk_prepayment_b 电费预缴单表体主键
*/
public void setPk_prepayment_b ( String pk_prepayment_b) {
this.pk_prepayment_b=pk_prepayment_b;
 } 

/** 
* 获取是否已传会计平台
*
* @return 是否已传会计平台
*/
public UFBoolean getIs_kjpt() {
	return is_kjpt;
}

/** 
* 设置是否已传会计平台
*
* @param is_kjpt 是否已传会计平台
*/
public void setIs_kjpt(UFBoolean is_kjpt) {
	this.is_kjpt = is_kjpt;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.prepayment_b");
  }
}