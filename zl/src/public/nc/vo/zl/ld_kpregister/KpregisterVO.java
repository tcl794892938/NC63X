package nc.vo.zl.ld_kpregister;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KpregisterVO extends SuperVO {
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
*开票号
*/
public String kpcode;
/**
*开票日期
*/
public UFDate kpdate;
/**
*开票人
*/
public String kpman;
/**
*开票金额
*/
public UFDouble kpmoney;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*单据类型
*/
public String pk_billtype;
/**
*客户名称
*/
public String pk_customer;
/**
*集团
*/
public String pk_group;
/**
*主键
*/
public String pk_kpregister;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*时间戳
*/
public UFDateTime ts;
/**
*单据号
*/
public String vbillcode;
/**
*单据状态
*/
public Integer vbillstatus;
/**
*单据类型编码
*/
public String vbilltypecode;
/**
*自定义项1
*/
public String vdef1;
/**
*自定义项10
*/
public String vdef10;
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
*自定义项6
*/
public String vdef6;
/**
*自定义项7
*/
public String vdef7;
/**
*自定义项8
*/
public String vdef8;
/**
*自定义项9
*/
public String vdef9;
/**
*来源单据id
*/
public String vsrcid;
/**
*来源单据类型
*/
public String vsrctype;
/**
 * 是否需要传会计平台
 */
public UFBoolean is_needkjpt;
/**
 * 是否已传会计平台
 */
public UFBoolean is_kjpt;

public UFBoolean getIs_needkjpt() {
	return is_needkjpt;
}

public void setIs_needkjpt(UFBoolean is_needkjpt) {
	this.is_needkjpt = is_needkjpt;
}

public UFBoolean getIs_kjpt() {
	return is_kjpt;
}

public void setIs_kjpt(UFBoolean is_kjpt) {
	this.is_kjpt = is_kjpt;
}

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
 * 项目
 */
public String pk_project;

public String getPk_project() {
	return pk_project;
}

public void setPk_project(String pk_project) {
	this.pk_project = pk_project;
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
* 获取开票号
*
* @return 开票号
*/
public String getKpcode () {
return this.kpcode;
 } 

/** 
* 设置开票号
*
* @param kpcode 开票号
*/
public void setKpcode ( String kpcode) {
this.kpcode=kpcode;
 } 

/** 
* 获取开票日期
*
* @return 开票日期
*/
public UFDate getKpdate () {
return this.kpdate;
 } 

/** 
* 设置开票日期
*
* @param kpdate 开票日期
*/
public void setKpdate ( UFDate kpdate) {
this.kpdate=kpdate;
 } 

/** 
* 获取开票人
*
* @return 开票人
*/
public String getKpman () {
return this.kpman;
 } 

/** 
* 设置开票人
*
* @param kpman 开票人
*/
public void setKpman ( String kpman) {
this.kpman=kpman;
 } 

/** 
* 获取开票金额
*
* @return 开票金额
*/
public UFDouble getKpmoney () {
return this.kpmoney;
 } 

/** 
* 设置开票金额
*
* @param kpmoney 开票金额
*/
public void setKpmoney ( UFDouble kpmoney) {
this.kpmoney=kpmoney;
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
* 获取主键
*
* @return 主键
*/
public String getPk_kpregister () {
return this.pk_kpregister;
 } 

/** 
* 设置主键
*
* @param pk_kpregister 主键
*/
public void setPk_kpregister ( String pk_kpregister) {
this.pk_kpregister=pk_kpregister;
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
* 获取单据号
*
* @return 单据号
*/
public String getVbillcode () {
return this.vbillcode;
 } 

/** 
* 设置单据号
*
* @param vbillcode 单据号
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getVbillstatus () {
return this.vbillstatus;
 } 

/** 
* 设置单据状态
*
* @param vbillstatus 单据状态
* @see String
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
 } 

/** 
* 获取单据类型编码
*
* @return 单据类型编码
*/
public String getVbilltypecode () {
return this.vbilltypecode;
 } 

/** 
* 设置单据类型编码
*
* @param vbilltypecode 单据类型编码
*/
public void setVbilltypecode ( String vbilltypecode) {
this.vbilltypecode=vbilltypecode;
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
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return this.vdef10;
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.vdef10=vdef10;
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
* 获取自定义项6
*
* @return 自定义项6
*/
public String getVdef6 () {
return this.vdef6;
 } 

/** 
* 设置自定义项6
*
* @param vdef6 自定义项6
*/
public void setVdef6 ( String vdef6) {
this.vdef6=vdef6;
 } 

/** 
* 获取自定义项7
*
* @return 自定义项7
*/
public String getVdef7 () {
return this.vdef7;
 } 

/** 
* 设置自定义项7
*
* @param vdef7 自定义项7
*/
public void setVdef7 ( String vdef7) {
this.vdef7=vdef7;
 } 

/** 
* 获取自定义项8
*
* @return 自定义项8
*/
public String getVdef8 () {
return this.vdef8;
 } 

/** 
* 设置自定义项8
*
* @param vdef8 自定义项8
*/
public void setVdef8 ( String vdef8) {
this.vdef8=vdef8;
 } 

/** 
* 获取自定义项9
*
* @return 自定义项9
*/
public String getVdef9 () {
return this.vdef9;
 } 

/** 
* 设置自定义项9
*
* @param vdef9 自定义项9
*/
public void setVdef9 ( String vdef9) {
this.vdef9=vdef9;
 } 

/** 
* 获取来源单据id
*
* @return 来源单据id
*/
public String getVsrcid () {
return this.vsrcid;
 } 

/** 
* 设置来源单据id
*
* @param vsrcid 来源单据id
*/
public void setVsrcid ( String vsrcid) {
this.vsrcid=vsrcid;
 } 

/** 
* 获取来源单据类型
*
* @return 来源单据类型
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* 设置来源单据类型
*
* @param vsrctype 来源单据类型
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.kpregister");
  }
}