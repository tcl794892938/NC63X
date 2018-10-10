package nc.vo.zl.hql_payment;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Payment_bVO extends SuperVO {
/**
*会计年月
*/
public String caccountperiod;
/**
*应收款日期
*/
public UFDate dysdate;
/**
*本期数
*/
public UFDouble ncurrentnum;
/**
*实际用量
*/
public UFDouble nrealnum;
/**
*已收金额
*/
public UFDouble nskmny;
/**
 * 缴费金额
 */
public UFDouble npaymny;
/**
 * 冲抵金额
 */
public UFDouble noffsetmny;

/**
 * 预缴款可抵金额
 */
public UFDouble nkdmny;

/**
 * 期初可抵金额
 */
public UFDouble nqcmny;

/**
 * 已抵冲金额
 */
public UFDouble nycmny;

/**
*上期数
*/
public UFDouble nupnum;
/**
*应收金额
*/
public UFDouble nysmny;
/**
*收费项目
*/
public String pk_costproject;
/**
*上层单据主键
*/
public String pk_payment;
/**
*水电费缴费单表体主键
*/
public String pk_paymentb;
/**
*单价
*/
public UFDouble price;
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

public Integer dr=0;
public UFDouble nconfirmed;

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
* 获取本期数
*
* @return 本期数
*/
public UFDouble getNcurrentnum () {
return this.ncurrentnum;
 } 

/** 
* 设置本期数
*
* @param ncurrentnum 本期数
*/
public void setNcurrentnum ( UFDouble ncurrentnum) {
this.ncurrentnum=ncurrentnum;
 } 

/** 
* 获取实际用量
*
* @return 实际用量
*/
public UFDouble getNrealnum () {
return this.nrealnum;
 } 

/** 
* 设置实际用量
*
* @param nrealnum 实际用量
*/
public void setNrealnum ( UFDouble nrealnum) {
this.nrealnum=nrealnum;
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
* 获取预缴款可抵金额
*
* @return 预缴款可抵金额
*/
public UFDouble getNkdmny() {
	return nkdmny;
}
/** 
* 设置预缴款可抵金额
*
* @param nkdmny 预缴款可抵金额
*/
public void setNkdmny(UFDouble nkdmny) {
	this.nkdmny = nkdmny;
}
/** 
* 获取期初可抵金额
*
* @return 期初可抵金额
*/
public UFDouble getNqcmny() {
	return nqcmny;
}
/** 
* 设置期初可抵金额
*
* @param nkdmny 期初可抵金额
*/
public void setNqcmny(UFDouble nqcmny) {
	this.nqcmny = nqcmny;
}
/** 
* 获取已抵冲金额
*
* @return 已抵冲金额
*/
public UFDouble getNycmny() {
	return nycmny;
}
/** 
* 设置已抵冲金额
*
* @param nkdmny 已抵冲金额
*/
public void setNycmny(UFDouble nycmny) {
	this.nycmny = nycmny;
}

/** 
* 获取上期数
*
* @return 上期数
*/
public UFDouble getNupnum () {
return this.nupnum;
 } 

/** 
* 设置上期数
*
* @param nupnum 上期数
*/
public void setNupnum ( UFDouble nupnum) {
this.nupnum=nupnum;
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
* 获取冲抵金额
*
* @return 冲抵金额
*/
public UFDouble getNoffsetmny() {
	return noffsetmny;
}

/** 
* 设置冲抵金额
*
* @param noffsetmny 冲抵金额
*/
public void setNoffsetmny(UFDouble noffsetmny) {
	this.noffsetmny = noffsetmny;
}

/** 
* 获取缴费金额
*
* @return 缴费金额
*/
public UFDouble getNpaymny() {
	return npaymny;
}

/** 
* 设置缴费金额
*
* @param noffsetmny 缴费金额
*/
public void setNpaymny(UFDouble npaymny) {
	this.npaymny = npaymny;
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
public String getPk_payment () {
return this.pk_payment;
 } 

/** 
* 设置上层单据主键
*
* @param pk_payment 上层单据主键
*/
public void setPk_payment ( String pk_payment) {
this.pk_payment=pk_payment;
 } 

/** 
* 获取水电费缴费单表体主键
*
* @return 水电费缴费单表体主键
*/
public String getPk_paymentb () {
return this.pk_paymentb;
 } 

/** 
* 设置水电费缴费单表体主键
*
* @param pk_paymentb 水电费缴费单表体主键
*/
public void setPk_paymentb ( String pk_paymentb) {
this.pk_paymentb=pk_paymentb;
 } 

/** 
* 获取单价
*
* @return 单价
*/
public UFDouble getPrice () {
return this.price;
 } 

/** 
* 设置单价
*
* @param price 单价
*/
public void setPrice ( UFDouble price) {
this.price=price;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.payment_b");
  }
}