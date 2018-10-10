package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CustomerVO extends SuperVO {
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
/**
*联系地址
*/
public String customeraddress;
/**
*客户编码
*/
public String customercode;
/**
*联系方式
*/
public String customerlxfs;
/**
*客户名称
*/
public String customername;
/**
*客户类型
*/
public String customertype;
/**
*制单日期
*/
public UFDate dbilldate;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*主键
*/
public String pk_customer;
/**
*集团
*/
public String pk_group;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*身份证号
*/
public String sfzh;
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
*营业执照
*/
public String yyzz;
/**
*置业顾问
*/
public String zygw;
/**
 * 是否删除
 */
public Integer dr = 0;

/**
 *辅助客户 
 */
public String fzkh;

/**
 * 潜在客户主键
 * @return
 */
public String pk_pocus;

public String getPk_pocus() {
	return pk_pocus;
}

public void setPk_pocus(String pk_pocus) {
	this.pk_pocus = pk_pocus;
}

public String getFzkh() {
	return fzkh;
}

public void setFzkh(String fzkh) {
	this.fzkh = fzkh;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取创建时间
*
* @return 创建时间
*/
public UFDateTime getCreationtime () {
return this.creationtime;
 } 

/** 
* 设置创建时间
*
* @param creationtime 创建时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
 } 

/** 
* 获取创建人
*
* @return 创建人
*/
public String getCreator () {
return this.creator;
 } 

/** 
* 设置创建人
*
* @param creator 创建人
*/
public void setCreator ( String creator) {
this.creator=creator;
 } 

/** 
* 获取联系地址
*
* @return 联系地址
*/
public String getCustomeraddress () {
return this.customeraddress;
 } 

/** 
* 设置联系地址
*
* @param customeraddress 联系地址
*/
public void setCustomeraddress ( String customeraddress) {
this.customeraddress=customeraddress;
 } 

/** 
* 获取客户编码
*
* @return 客户编码
*/
public String getCustomercode () {
return this.customercode;
 } 

/** 
* 设置客户编码
*
* @param customercode 客户编码
*/
public void setCustomercode ( String customercode) {
this.customercode=customercode;
 } 

/** 
* 获取联系方式
*
* @return 联系方式
*/
public String getCustomerlxfs () {
return this.customerlxfs;
 } 

/** 
* 设置联系方式
*
* @param customerlxfs 联系方式
*/
public void setCustomerlxfs ( String customerlxfs) {
this.customerlxfs=customerlxfs;
 } 

/** 
* 获取客户名称
*
* @return 客户名称
*/
public String getCustomername () {
return this.customername;
 } 

/** 
* 设置客户名称
*
* @param customername 客户名称
*/
public void setCustomername ( String customername) {
this.customername=customername;
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
* 获取制单日期
*
* @return 制单日期
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* 设置制单日期
*
* @param dbilldate 制单日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return this.modifiedtime;
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return this.modifier;
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* 设置主键
*
* @param pk_customer 主键
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return this.pk_group;
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* 获取组织版本
*
* @return 组织版本
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* 设置组织版本
*
* @param pk_org_v 组织版本
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* 获取身份证号
*
* @return 身份证号
*/
public String getSfzh () {
return this.sfzh;
 } 

/** 
* 设置身份证号
*
* @param sfzh 身份证号
*/
public void setSfzh ( String sfzh) {
this.sfzh=sfzh;
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
* 获取营业执照
*
* @return 营业执照
*/
public String getYyzz () {
return this.yyzz;
 } 

/** 
* 设置营业执照
*
* @param yyzz 营业执照
*/
public void setYyzz ( String yyzz) {
this.yyzz=yyzz;
 } 

/** 
* 获取置业顾问
*
* @return 置业顾问
*/
public String getZygw () {
return this.zygw;
 } 

/** 
* 设置置业顾问
*
* @param zygw 置业顾问
*/
public void setZygw ( String zygw) {
this.zygw=zygw;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.customer");
  }
}