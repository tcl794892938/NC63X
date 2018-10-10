package nc.vo.zl.ly_pocustomers;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PocustomersVO extends SuperVO {
/**
*联系地址
*/
public String address;
/**
*营业执照
*/
public String businesslicense;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
/**
*客户跟踪状态
*/
public String customert;
/**
*客户类型
*/
public String customertype;
/**
*制单日期
*/
public UFDate dbilldate;
/**
*身份证号
*/
public String idnumber;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*联系方式
*/
public String phone;
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
*主键
*/
public String pk_pocustomers;
/**
*业务员
*/
public String salesman;
/**
*商源编码
*/
public String sourceid;
/**
*商源名称
*/
public String sourcename;
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
 * 是否删除
 */
public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取联系地址
*
* @return 联系地址
*/
public String getAddress () {
return this.address;
 } 

/** 
* 设置联系地址
*
* @param address 联系地址
*/
public void setAddress ( String address) {
this.address=address;
 } 

/** 
* 获取营业执照
*
* @return 营业执照
*/
public String getBusinesslicense () {
return this.businesslicense;
 } 

/** 
* 设置营业执照
*
* @param businesslicense 营业执照
*/
public void setBusinesslicense ( String businesslicense) {
this.businesslicense=businesslicense;
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
* 获取客户跟踪状态
*
* @return 客户跟踪状态
*/
public String getCustomert () {
return this.customert;
 } 

/** 
* 设置客户跟踪状态
*
* @param customert 客户跟踪状态
*/
public void setCustomert ( String customert) {
this.customert=customert;
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
* 获取身份证号
*
* @return 身份证号
*/
public String getIdnumber () {
return this.idnumber;
 } 

/** 
* 设置身份证号
*
* @param idnumber 身份证号
*/
public void setIdnumber ( String idnumber) {
this.idnumber=idnumber;
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
* 获取联系方式
*
* @return 联系方式
*/
public String getPhone () {
return this.phone;
 } 

/** 
* 设置联系方式
*
* @param phone 联系方式
*/
public void setPhone ( String phone) {
this.phone=phone;
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
* 获取主键
*
* @return 主键
*/
public String getPk_pocustomers () {
return this.pk_pocustomers;
 } 

/** 
* 设置主键
*
* @param pk_pocustomers 主键
*/
public void setPk_pocustomers ( String pk_pocustomers) {
this.pk_pocustomers=pk_pocustomers;
 } 

/** 
* 获取业务员
*
* @return 业务员
*/
public String getSalesman () {
return this.salesman;
 } 

/** 
* 设置业务员
*
* @param salesman 业务员
*/
public void setSalesman ( String salesman) {
this.salesman=salesman;
 } 

/** 
* 获取商源编码
*
* @return 商源编码
*/
public String getSourceid () {
return this.sourceid;
 } 

/** 
* 设置商源编码
*
* @param sourceid 商源编码
*/
public void setSourceid ( String sourceid) {
this.sourceid=sourceid;
 } 

/** 
* 获取商源名称
*
* @return 商源名称
*/
public String getSourcename () {
return this.sourcename;
 } 

/** 
* 设置商源名称
*
* @param sourcename 商源名称
*/
public void setSourcename ( String sourcename) {
this.sourcename=sourcename;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.pocustomers");
  }
}