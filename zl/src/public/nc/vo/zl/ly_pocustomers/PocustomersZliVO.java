package nc.vo.zl.ly_pocustomers;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PocustomersZliVO extends SuperVO {
/**
	 * 
	 */
	private static final long serialVersionUID = -4836263887877905291L;
/**
*楼栋
*/
public String building;
/**
*期望单价止(每月每平)
*/
public UFDouble epriceend;
/**
*期望单价起(每月每平)
*/
public UFDouble epricestart;
/**
*房产
*/
public String house;
/**
*其他意向
*/
public String other;
/**
*上层单据主键
*/
public String pk_pocustomers;
/**
*主键
*/
public String pk_pocustomers_zli;
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
* 获取楼栋
*
* @return 楼栋
*/
public String getBuilding () {
return this.building;
 } 

/** 
* 设置楼栋
*
* @param building 楼栋
*/
public void setBuilding ( String building) {
this.building=building;
 } 

/** 
* 获取期望单价止(每月每平)
*
* @return 期望单价止(每月每平)
*/
public UFDouble getEpriceend () {
return this.epriceend;
 } 

/** 
* 设置期望单价止(每月每平)
*
* @param epriceend 期望单价止(每月每平)
*/
public void setEpriceend ( UFDouble epriceend) {
this.epriceend=epriceend;
 } 

/** 
* 获取期望单价起(每月每平)
*
* @return 期望单价起(每月每平)
*/
public UFDouble getEpricestart () {
return this.epricestart;
 } 

/** 
* 设置期望单价起(每月每平)
*
* @param epricestart 期望单价起(每月每平)
*/
public void setEpricestart ( UFDouble epricestart) {
this.epricestart=epricestart;
 } 

/** 
* 获取房产
*
* @return 房产
*/
public String getHouse () {
return this.house;
 } 

/** 
* 设置房产
*
* @param house 房产
*/
public void setHouse ( String house) {
this.house=house;
 } 

/** 
* 获取其他意向
*
* @return 其他意向
*/
public String getOther () {
return this.other;
 } 

/** 
* 设置其他意向
*
* @param other 其他意向
*/
public void setOther ( String other) {
this.other=other;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_pocustomers () {
return this.pk_pocustomers;
 } 

/** 
* 设置上层单据主键
*
* @param pk_pocustomers 上层单据主键
*/
public void setPk_pocustomers ( String pk_pocustomers) {
this.pk_pocustomers=pk_pocustomers;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_pocustomers_zli () {
return this.pk_pocustomers_zli;
 } 

/** 
* 设置主键
*
* @param pk_pocustomers_zli 主键
*/
public void setPk_pocustomers_zli ( String pk_pocustomers_zli) {
this.pk_pocustomers_zli=pk_pocustomers_zli;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.pocustomers_zli");
  }
}