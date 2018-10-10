package nc.vo.zl.ly_sgmoney;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SgMoneyVO extends SuperVO {
/**
*单据类型编码
*/
public String billtypecode;
/**
*审核人
*/
public String checkman;
/**
*审核批语
*/
public String checkpy;
/**
*审核时间
*/
public UFDateTime checktime;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
/**
*制单日期
*/
public UFDate dbilldate;
/**
*房产名称
*/
public String fcname;
/**
*客户名称
*/
public String khname;
/**
*单据编号
*/
public String listid;
/**
*单据状态
*/
public Integer liststate;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*收费项目
*/
public String payproject;
/**
*单据类型
*/
public String pk_billtype;
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
*项目信息
*/
public String pk_project;
/**
*主键
*/
public String pk_sgmoney;
/**
*预缴款余额
*/
public UFDouble restmoney;
/**
*时间戳
*/
public UFDateTime ts;
/**
*自定义项3
*/
public String vde3;
/**
*自定义项1
*/
public String vdef1;
/**
*自定义项2
*/
public String vdef2;
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
* 获取单据类型编码
*
* @return 单据类型编码
*/
public String getBilltypecode () {
return this.billtypecode;
 } 

/** 
* 设置单据类型编码
*
* @param billtypecode 单据类型编码
*/
public void setBilltypecode ( String billtypecode) {
this.billtypecode=billtypecode;
 } 

/** 
* 获取审核人
*
* @return 审核人
*/
public String getCheckman () {
return this.checkman;
 } 

/** 
* 设置审核人
*
* @param checkman 审核人
*/
public void setCheckman ( String checkman) {
this.checkman=checkman;
 } 

/** 
* 获取审核批语
*
* @return 审核批语
*/
public String getCheckpy () {
return this.checkpy;
 } 

/** 
* 设置审核批语
*
* @param checkpy 审核批语
*/
public void setCheckpy ( String checkpy) {
this.checkpy=checkpy;
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getChecktime () {
return this.checktime;
 } 

/** 
* 设置审核时间
*
* @param checktime 审核时间
*/
public void setChecktime ( UFDateTime checktime) {
this.checktime=checktime;
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
* 获取房产名称
*
* @return 房产名称
*/
public String getFcname () {
return this.fcname;
 } 

/** 
* 设置房产名称
*
* @param fcname 房产名称
*/
public void setFcname ( String fcname) {
this.fcname=fcname;
 } 

/** 
* 获取客户名称
*
* @return 客户名称
*/
public String getKhname () {
return this.khname;
 } 

/** 
* 设置客户名称
*
* @param khname 客户名称
*/
public void setKhname ( String khname) {
this.khname=khname;
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getListid () {
return this.listid;
 } 

/** 
* 设置单据编号
*
* @param listid 单据编号
*/
public void setListid ( String listid) {
this.listid=listid;
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getListstate () {
return this.liststate;
 } 

/** 
* 设置单据状态
*
* @param liststate 单据状态
* @see String
*/
public void setListstate ( Integer liststate) {
this.liststate=liststate;
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
* 获取收费项目
*
* @return 收费项目
*/
public String getPayproject () {
return this.payproject;
 } 

/** 
* 设置收费项目
*
* @param payproject 收费项目
*/
public void setPayproject ( String payproject) {
this.payproject=payproject;
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getPk_billtype () {
return this.pk_billtype;
 } 

/** 
* 设置单据类型
*
* @param pk_billtype 单据类型
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
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
* 获取项目信息
*
* @return 项目信息
*/
public String getPk_project () {
return this.pk_project;
 } 

/** 
* 设置项目信息
*
* @param pk_project 项目信息
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_sgmoney () {
return this.pk_sgmoney;
 } 

/** 
* 设置主键
*
* @param pk_sgmoney 主键
*/
public void setPk_sgmoney ( String pk_sgmoney) {
this.pk_sgmoney=pk_sgmoney;
 } 

/** 
* 获取预缴款余额
*
* @return 预缴款余额
*/
public UFDouble getRestmoney () {
return this.restmoney;
 } 

/** 
* 设置预缴款余额
*
* @param restmoney 预缴款余额
*/
public void setRestmoney ( UFDouble restmoney) {
this.restmoney=restmoney;
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
* 获取自定义项3
*
* @return 自定义项3
*/
public String getVde3 () {
return this.vde3;
 } 

/** 
* 设置自定义项3
*
* @param vde3 自定义项3
*/
public void setVde3 ( String vde3) {
this.vde3=vde3;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.sgmoney");
  }
}