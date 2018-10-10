package nc.vo.zl.hql_builldingfile;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BuildingfileVO extends SuperVO {
/**
*建筑面积
*/
public UFDouble builtuparea;
/**
*楼栋编码
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
*套内面积
*/
public UFDouble innerarea;
/**
*是否建房
*/
public UFBoolean isbuild;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*楼栋名称
*/
public String name;
/**
*楼栋层数
*/
public Integer nbuilding;
/**
*房产数量
*/
public Integer nproperty;
/**
*单元数量
*/
public Integer nunit;
/**
*主键
*/
public String pk_buildingfile;
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
public String pk_projectid;
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

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/**
 * 自用面积
 */
public UFDouble personalarea;

public UFDouble getPersonalarea() {
	return personalarea;
}

public void setPersonalarea(UFDouble personalarea) {
	this.personalarea = personalarea;
}

/** 
* 获取建筑面积
*
* @return 建筑面积
*/
public UFDouble getBuiltuparea () {
return this.builtuparea;
 } 

/** 
* 设置建筑面积
*
* @param builtuparea 建筑面积
*/
public void setBuiltuparea ( UFDouble builtuparea) {
this.builtuparea=builtuparea;
 } 

/** 
* 获取楼栋编码
*
* @return 楼栋编码
*/
public String getCode () {
return this.code;
 } 

/** 
* 设置楼栋编码
*
* @param code 楼栋编码
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
* 获取套内面积
*
* @return 套内面积
*/
public UFDouble getInnerarea () {
return this.innerarea;
 } 

/** 
* 设置套内面积
*
* @param innerarea 套内面积
*/
public void setInnerarea ( UFDouble innerarea) {
this.innerarea=innerarea;
 } 

/** 
* 获取是否建房
*
* @return 是否建房
*/
public UFBoolean getIsbuild () {
return this.isbuild;
 } 

/** 
* 设置是否建房
*
* @param isbuild 是否建房
*/
public void setIsbuild ( UFBoolean isbuild) {
this.isbuild=isbuild;
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
* 获取楼栋名称
*
* @return 楼栋名称
*/
public String getName () {
return this.name;
 } 

/** 
* 设置楼栋名称
*
* @param name 楼栋名称
*/
public void setName ( String name) {
this.name=name;
 } 

/** 
* 获取楼栋层数
*
* @return 楼栋层数
*/
public Integer getNbuilding () {
return this.nbuilding;
 } 

/** 
* 设置楼栋层数
*
* @param nbuilding 楼栋层数
*/
public void setNbuilding ( Integer nbuilding) {
this.nbuilding=nbuilding;
 } 

/** 
* 获取房产数量
*
* @return 房产数量
*/
public Integer getNproperty () {
return this.nproperty;
 } 

/** 
* 设置房产数量
*
* @param nproperty 房产数量
*/
public void setNproperty ( Integer nproperty) {
this.nproperty=nproperty;
 } 

/** 
* 获取单元数量
*
* @return 单元数量
*/
public Integer getNunit () {
return this.nunit;
 } 

/** 
* 设置单元数量
*
* @param nunit 单元数量
*/
public void setNunit ( Integer nunit) {
this.nunit=nunit;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_buildingfile () {
return this.pk_buildingfile;
 } 

/** 
* 设置主键
*
* @param pk_buildingfile 主键
*/
public void setPk_buildingfile ( String pk_buildingfile) {
this.pk_buildingfile=pk_buildingfile;
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
public String getPk_projectid () {
return this.pk_projectid;
 } 

/** 
* 设置项目信息
*
* @param pk_projectid 项目信息
*/
public void setPk_projectid ( String pk_projectid) {
this.pk_projectid=pk_projectid;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.buildingfile");
  }
}