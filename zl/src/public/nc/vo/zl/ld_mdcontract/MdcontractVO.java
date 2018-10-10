package nc.vo.zl.ld_mdcontract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class MdcontractVO extends SuperVO {
/**
*租金总金额
*/
public UFDouble allrent;
/**
*审核批语
*/
public String approvenote;
/**
*审核人
*/
public String approver;
/**
*审核时间
*/
public UFDateTime approvetime;
/**
*单据号
*/
public String vbillcode;
public String getVbillcode() {
	return vbillcode;
}

public void setVbillcode(String vbillcode) {
	this.vbillcode = vbillcode;
}


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
*合同终止日
*/
public UFDate enddate;
/**
*经营位置
*/
public String location;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*合同名称
*/
public String name;
/**
*单据类型
*/
public String pk_billtype;
/**
*合同类型
*/
public String pk_contracttype;
/**
*客户名称
*/
public String pk_customer;
/**
*集团
*/
public String pk_group;
/**
*经营合同主键
*/
public String pk_mdcontract;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*项目
*/
public String pk_project;
/**
*备注
*/
public String remark;
/**
*签租日期
*/
public UFDate rentdate;
/**
*合同起始日
*/
public UFDate startdate;
/**
*单据状态
*/
public Integer state;
/**
*税率
*/
public Integer taxrate;
/**
*时间戳
*/
public UFDateTime ts;
/**
*单据类型编码
*/
public String vbilltypecode;
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
*版本号
*/
public Integer version=-1;
/**
*编码
*/
public String code;



/**
*来源单据id
*/
public String vsrcid;
/**
*来源单据类型
*/
public String vsrctype;
private Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取租金总金额
*
* @return 租金总金额
*/
public UFDouble getAllrent () {
return this.allrent;
 } 

/** 
* 设置租金总金额
*
* @param allrent 租金总金额
*/
public void setAllrent ( UFDouble allrent) {
this.allrent=allrent;
 } 

/** 
* 获取审核批语
*
* @return 审核批语
*/
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* 设置审核批语
*
* @param approvenote 审核批语
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return this.approver;
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getApprovetime () {
return this.approvetime;
 } 

/** 
* 设置审核时间
*
* @param approvetime 审核时间
*/
public void setApprovetime ( UFDateTime approvetime) {
this.approvetime=approvetime;
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
* 获取合同终止日
*
* @return 合同终止日
*/
public UFDate getEnddate () {
return this.enddate;
 } 

/** 
* 设置合同终止日
*
* @param enddate 合同终止日
*/
public void setEnddate ( UFDate enddate) {
this.enddate=enddate;
 } 

/** 
* 获取经营位置
*
* @return 经营位置
*/
public String getLocation () {
return this.location;
 } 

/** 
* 设置经营位置
*
* @param location 经营位置
*/
public void setLocation ( String location) {
this.location=location;
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
* 获取合同名称
*
* @return 合同名称
*/
public String getName () {
return this.name;
 } 

/** 
* 设置合同名称
*
* @param name 合同名称
*/
public void setName ( String name) {
this.name=name;
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
* 获取合同类型
*
* @return 合同类型
*/
public String getPk_contracttype () {
return this.pk_contracttype;
 } 

/** 
* 设置合同类型
*
* @param pk_contracttype 合同类型
*/
public void setPk_contracttype ( String pk_contracttype) {
this.pk_contracttype=pk_contracttype;
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
* 获取经营合同主键
*
* @return 经营合同主键
*/
public String getPk_mdcontract () {
return this.pk_mdcontract;
 } 

/** 
* 设置经营合同主键
*
* @param pk_mdcontract 经营合同主键
*/
public void setPk_mdcontract ( String pk_mdcontract) {
this.pk_mdcontract=pk_mdcontract;
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
* 获取项目
*
* @return 项目
*/
public String getPk_project () {
return this.pk_project;
 } 

/** 
* 设置项目
*
* @param pk_project 项目
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getRemark () {
return this.remark;
 } 

/** 
* 设置备注
*
* @param remark 备注
*/
public void setRemark ( String remark) {
this.remark=remark;
 } 

/** 
* 获取签租日期
*
* @return 签租日期
*/
public UFDate getRentdate () {
return this.rentdate;
 } 

/** 
* 设置签租日期
*
* @param rentdate 签租日期
*/
public void setRentdate ( UFDate rentdate) {
this.rentdate=rentdate;
 } 

/** 
* 获取合同起始日
*
* @return 合同起始日
*/
public UFDate getStartdate () {
return this.startdate;
 } 

/** 
* 设置合同起始日
*
* @param startdate 合同起始日
*/
public void setStartdate ( UFDate startdate) {
this.startdate=startdate;
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getState () {
return this.state;
 } 

/** 
* 设置单据状态
*
* @param state 单据状态
* @see String
*/
public void setState ( Integer state) {
this.state=state;
 } 

/** 
* 获取税率
*
* @return 税率
* @see String
*/
public Integer getTaxrate () {
return this.taxrate;
 } 

/** 
* 设置税率
*
* @param taxrate 税率
* @see String
*/
public void setTaxrate ( Integer taxrate) {
this.taxrate=taxrate;
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
* 获取版本号
*
* @return 版本号
*/
public Integer getVersion () {
return this.version;
 } 

/** 
* 设置版本号
*
* @param version 版本号
*/
public void setVersion ( Integer version) {
this.version=version;
 } 

/** 
* 获取编码
*
* @return 编码
*/
public String getCode () {
return this.code;
 } 

/** 
* 设置编码
*
* @param code 编码
*/
public void setCode ( String code) {
this.code=code;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.mdcontract");
  }
}