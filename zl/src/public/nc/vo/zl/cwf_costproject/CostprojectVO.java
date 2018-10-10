package nc.vo.zl.cwf_costproject;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CostprojectVO extends SuperVO {
/**
*项目编码
*/
public String code;
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
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*项目名称
*/
public String name;
/**
*项目
*/
public String pk_baseproject;
/**
*主键
*/
public String pk_costproject;
/**
*集团
*/
public String pk_group;
/**
*收支项目
*/
public String pk_incomepro;
/**
 * 收费大类
 */
public String pk_costtype;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*上级项目
*/
public String pk_vid;
/**
*舍入方式
*/
public Integer roundtype;
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

public Integer DR=0;
public String getPk_costtype() {
	return pk_costtype;
}

public void setPk_costtype(String pk_costtype) {
	this.pk_costtype = pk_costtype;
}

public Integer getDR() {
	return DR;
}

public void setDR(Integer dR) {
	DR = dR;
}

/** 
* 获取项目编码
*
* @return 项目编码
*/
public String getCode () {
return this.code;
 } 

/** 
* 设置项目编码
*
* @param code 项目编码
*/
public void setCode ( String code) {
this.code=code;
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
* 获取项目名称
*
* @return 项目名称
*/
public String getName () {
return this.name;
 } 

/** 
* 设置项目名称
*
* @param name 项目名称
*/
public void setName ( String name) {
this.name=name;
 } 

/** 
* 获取项目
*
* @return 项目
*/
public String getPk_baseproject () {
return this.pk_baseproject;
 } 

/** 
* 设置项目
*
* @param pk_baseproject 项目
*/
public void setPk_baseproject ( String pk_baseproject) {
this.pk_baseproject=pk_baseproject;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_costproject () {
return this.pk_costproject;
 } 

/** 
* 设置主键
*
* @param pk_costproject 主键
*/
public void setPk_costproject ( String pk_costproject) {
this.pk_costproject=pk_costproject;
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
* 获取收支项目
*
* @return 收支项目
*/
public String getPk_incomepro () {
return this.pk_incomepro;
 } 

/** 
* 设置收支项目
*
* @param pk_incomepro 收支项目
*/
public void setPk_incomepro ( String pk_incomepro) {
this.pk_incomepro=pk_incomepro;
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
* 获取上级项目
*
* @return 上级项目
*/
public String getPk_vid () {
return this.pk_vid;
 } 

/** 
* 设置上级项目
*
* @param pk_vid 上级项目
*/
public void setPk_vid ( String pk_vid) {
this.pk_vid=pk_vid;
 } 

/** 
* 获取舍入方式
*
* @return 舍入方式
* @see String
*/
public Integer getRoundtype () {
return this.roundtype;
 } 

/** 
* 设置舍入方式
*
* @param roundtype 舍入方式
* @see String
*/
public void setRoundtype ( Integer roundtype) {
this.roundtype=roundtype;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.costproject");
  }
}