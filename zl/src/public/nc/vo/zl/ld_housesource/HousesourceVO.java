package nc.vo.zl.ld_housesource;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HousesourceVO extends SuperVO {
/**
*建筑面积
*/
public UFDouble buildarea;
/**
*楼栋
*/
public String buildname;
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
*房产编号
*/
public String estatecode;
/**
*房产名称
*/
public String estatename;
/**
*房源状态
*/
public Integer housestate;
/**
*套内面积
*/
public UFDouble innerarea;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*户型信息
*/
public String pk_familyfile;
/**
*集团
*/
public String pk_group;
/**
*房源主键
*/
public String pk_housesource;
/**
*组织名称
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*项目名称
*/
public String projectname;
/**
*房号
*/
public String roomnumber;
/**
*时间戳
*/
public UFDateTime ts;
/**
*单元
*/
public String unit;
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
 * 楼层
 */
public String floorn;


public String getFloorn() {
	return floorn;
}

public void setFloorn(String floorn) {
	this.floorn = floorn;
}


public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取建筑面积
*
* @return 建筑面积
*/
public UFDouble getBuildarea () {
return this.buildarea;
 } 

/** 
* 设置建筑面积
*
* @param buildarea 建筑面积
*/
public void setBuildarea ( UFDouble buildarea) {
this.buildarea=buildarea;
 } 

/** 
* 获取楼栋
*
* @return 楼栋
*/
public String getBuildname () {
return this.buildname;
 } 

/** 
* 设置楼栋
*
* @param buildname 楼栋
*/
public void setBuildname ( String buildname) {
this.buildname=buildname;
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
* 获取房产编号
*
* @return 房产编号
*/
public String getEstatecode () {
return this.estatecode;
 } 

/** 
* 设置房产编号
*
* @param estatecode 房产编号
*/
public void setEstatecode ( String estatecode) {
this.estatecode=estatecode;
 } 

/** 
* 获取房产名称
*
* @return 房产名称
*/
public String getEstatename () {
return this.estatename;
 } 

/** 
* 设置房产名称
*
* @param estatename 房产名称
*/
public void setEstatename ( String estatename) {
this.estatename=estatename;
 } 

/** 
* 获取房源状态
*
* @return 房源状态
* @see String
*/
public Integer getHousestate () {
return this.housestate;
 } 

/** 
* 设置房源状态
*
* @param housestate 房源状态
* @see String
*/
public void setHousestate ( Integer housestate) {
this.housestate=housestate;
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
* 获取户型信息
*
* @return 户型信息
*/
public String getPk_familyfile () {
return this.pk_familyfile;
 } 

/** 
* 设置户型信息
*
* @param pk_familyfile 户型信息
*/
public void setPk_familyfile ( String pk_familyfile) {
this.pk_familyfile=pk_familyfile;
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
* 获取房源主键
*
* @return 房源主键
*/
public String getPk_housesource () {
return this.pk_housesource;
 } 

/** 
* 设置房源主键
*
* @param pk_housesource 房源主键
*/
public void setPk_housesource ( String pk_housesource) {
this.pk_housesource=pk_housesource;
 } 

/** 
* 获取组织名称
*
* @return 组织名称
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* 设置组织名称
*
* @param pk_org 组织名称
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
* 获取项目名称
*
* @return 项目名称
*/
public String getProjectname () {
return this.projectname;
 } 

/** 
* 设置项目名称
*
* @param projectname 项目名称
*/
public void setProjectname ( String projectname) {
this.projectname=projectname;
 } 

/** 
* 获取房号
*
* @return 房号
*/
public String getRoomnumber () {
return this.roomnumber;
 } 

/** 
* 设置房号
*
* @param roomnumber 房号
*/
public void setRoomnumber ( String roomnumber) {
this.roomnumber=roomnumber;
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
* 获取单元
*
* @return 单元
*/
public String getUnit () {
return this.unit;
 } 

/** 
* 设置单元
*
* @param unit 单元
*/
public void setUnit ( String unit) {
this.unit=unit;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.housesource");
  }
}