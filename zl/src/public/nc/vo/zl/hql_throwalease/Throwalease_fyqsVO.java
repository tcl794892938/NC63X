package nc.vo.zl.hql_throwalease;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Throwalease_fyqsVO extends SuperVO {
/**
*费用截止日期
*/
public UFDate dfyenddate;
/**
*费用开始日期
*/
public UFDate dfystartdate;
/**
*结算金额
*/
public UFDouble njsmny;
/**
*扣款金额
*/
public UFDouble nkkmny;
/**
*应退金额
*/
public UFDouble nskmny;
/**
*已退金额
*/
public UFDouble nytmny;
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
public String pk_housesource;
/**
*上层单据主键
*/
public String pk_throwalease;
/**
*费用清算主键
*/
public String pk_throwaleasefyqs;
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
/**
 * 税率
 * @return
 */
public UFDouble ntaxrate;
/**
*无税金额
*/
public UFDouble nnotaxmoney;
/**
 * 税额
 */
public UFDouble ntaxmny;
public UFDouble recqr;

public UFDouble getRecqr() {
	return recqr;
}

public void setRecqr(UFDouble recqr) {
	this.recqr = recqr;
}

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public UFDouble getNnotaxmoney() {
	return nnotaxmoney;
}

public void setNnotaxmoney(UFDouble nnotaxmoney) {
	this.nnotaxmoney = nnotaxmoney;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

private Integer dr = 0;

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
public UFDate getDfyenddate () {
return this.dfyenddate;
 } 

/** 
* 设置费用截止日期
*
* @param dfyenddate 费用截止日期
*/
public void setDfyenddate ( UFDate dfyenddate) {
this.dfyenddate=dfyenddate;
 } 

/** 
* 获取费用开始日期
*
* @return 费用开始日期
*/
public UFDate getDfystartdate () {
return this.dfystartdate;
 } 

/** 
* 设置费用开始日期
*
* @param dfystartdate 费用开始日期
*/
public void setDfystartdate ( UFDate dfystartdate) {
this.dfystartdate=dfystartdate;
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
* 获取应退金额
*
* @return 应退金额
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* 设置应退金额
*
* @param nskmny 应退金额
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 
/** 
* 获取已退金额
*
* @return 已退金额
*/
public UFDouble getNytmny() {
	return nytmny;
}
/** 
* 设置已退金额
*
* @param nytmny 已退金额
*/
public void setNytmny(UFDouble nytmny) {
	this.nytmny = nytmny;
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
public String getPk_housesource () {
return this.pk_housesource;
 } 

/** 
* 设置房产名称
*
* @param pk_housesource 房产名称
*/
public void setPk_housesource ( String pk_housesource) {
this.pk_housesource=pk_housesource;
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
* 获取费用清算主键
*
* @return 费用清算主键
*/
public String getPk_throwaleasefyqs () {
return this.pk_throwaleasefyqs;
 } 

/** 
* 设置费用清算主键
*
* @param pk_throwaleasefyqs 费用清算主键
*/
public void setPk_throwaleasefyqs ( String pk_throwaleasefyqs) {
this.pk_throwaleasefyqs=pk_throwaleasefyqs;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.throwalease_fyqs");
  }
}