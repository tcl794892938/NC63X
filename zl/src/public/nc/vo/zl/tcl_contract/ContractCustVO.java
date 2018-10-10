package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractCustVO extends SuperVO {
/**
*营业执照
*/
public String busilicence;
/**
*客户类型
*/
public String customertype;
/**
*身份证号
*/
public String idno;
/**
*联系地址
*/
public String lxaddress;
/**
*联系电话
*/
public String lxphone;
/**
*上层单据主键
*/
public String pk_contract;
/**
*主键
*/
public String pk_contract_cust;
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

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* 获取营业执照
*
* @return 营业执照
*/
public String getBusilicence () {
return this.busilicence;
 } 

/** 
* 设置营业执照
*
* @param busilicence 营业执照
*/
public void setBusilicence ( String busilicence) {
this.busilicence=busilicence;
 } 

/** 
* 获取客户类型
*
* @return 客户类型
*/
public String getCustomertype () {
return this.customertype;
 } 

/** 
* 设置客户类型
*
* @param customertype 客户类型
*/
public void setCustomertype ( String customertype) {
this.customertype=customertype;
 } 

/** 
* 获取身份证号
*
* @return 身份证号
*/
public String getIdno () {
return this.idno;
 } 

/** 
* 设置身份证号
*
* @param idno 身份证号
*/
public void setIdno ( String idno) {
this.idno=idno;
 } 

/** 
* 获取联系地址
*
* @return 联系地址
*/
public String getLxaddress () {
return this.lxaddress;
 } 

/** 
* 设置联系地址
*
* @param lxaddress 联系地址
*/
public void setLxaddress ( String lxaddress) {
this.lxaddress=lxaddress;
 } 

/** 
* 获取联系电话
*
* @return 联系电话
*/
public String getLxphone () {
return this.lxphone;
 } 

/** 
* 设置联系电话
*
* @param lxphone 联系电话
*/
public void setLxphone ( String lxphone) {
this.lxphone=lxphone;
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
public String getPk_contract_cust () {
return this.pk_contract_cust;
 } 

/** 
* 设置主键
*
* @param pk_contract_cust 主键
*/
public void setPk_contract_cust ( String pk_contract_cust) {
this.pk_contract_cust=pk_contract_cust;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_cust");
  }
}