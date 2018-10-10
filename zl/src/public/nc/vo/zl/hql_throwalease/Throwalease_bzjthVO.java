package nc.vo.zl.hql_throwalease;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Throwalease_bzjthVO extends SuperVO {
/**
*应退款日期
*/
public UFDate dytkdate;
/**
*结算金额
*/
public UFDouble njsmny;
/**
*扣款金额
*/
public UFDouble nkkmny;
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
public String pk_throwalease;
/**
*保证金退还主键
*/
public String pk_throwaleasebzjth;
/**
*行号
*/
public String rowno;
/**
*时间戳
*/
public UFDateTime ts;
/**
*应退保证金
*/
public UFDouble ytdeposit;
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
*已退金额
*/
public UFDouble nytmny;
public UFDouble nconfirmed;

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

public UFDouble getNytmny() {
	return nytmny;
}

public void setNytmny(UFDouble nytmny) {
	this.nytmny = nytmny;
}

private Integer dr = 0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取应退款日期
*
* @return 应退款日期
*/
public UFDate getDytkdate () {
return this.dytkdate;
 } 

/** 
* 设置应退款日期
*
* @param dytkdate 应退款日期
*/
public void setDytkdate ( UFDate dytkdate) {
this.dytkdate=dytkdate;
 } 

/** 
* 获取结算金额
*
* @return 结算金额
*/
public UFDouble getNjsmny () {
return this.njsmny;
 } 

/** 
* 设置结算金额
*
* @param njsmny 结算金额
*/
public void setNjsmny ( UFDouble njsmny) {
this.njsmny=njsmny;
 } 

/** 
* 获取扣款金额
*
* @return 扣款金额
*/
public UFDouble getNkkmny () {
return this.nkkmny;
 } 

/** 
* 设置扣款金额
*
* @param nkkmny 扣款金额
*/
public void setNkkmny ( UFDouble nkkmny) {
this.nkkmny=nkkmny;
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
public String getPk_throwalease () {
return this.pk_throwalease;
 } 

/** 
* 设置上层单据主键
*
* @param pk_throwalease 上层单据主键
*/
public void setPk_throwalease ( String pk_throwalease) {
this.pk_throwalease=pk_throwalease;
 } 

/** 
* 获取保证金退还主键
*
* @return 保证金退还主键
*/
public String getPk_throwaleasebzjth () {
return this.pk_throwaleasebzjth;
 } 

/** 
* 设置保证金退还主键
*
* @param pk_throwaleasebzjth 保证金退还主键
*/
public void setPk_throwaleasebzjth ( String pk_throwaleasebzjth) {
this.pk_throwaleasebzjth=pk_throwaleasebzjth;
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
* 获取应退保证金
*
* @return 应退保证金
*/
public UFDouble getYtdeposit () {
return this.ytdeposit;
 } 

/** 
* 设置应退保证金
*
* @param ytdeposit 应退保证金
*/
public void setYtdeposit ( UFDouble ytdeposit) {
this.ytdeposit=ytdeposit;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.throwalease_bzjth");
  }
}