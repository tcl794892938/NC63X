package nc.vo.zl.hql_payment;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PaymentVO extends SuperVO {
/**
*审批批语
*/
public String approvenote;
/**
*审批人
*/
public String approver;
/**
*审批时间
*/
public UFDateTime approvetime;
/**
*单据日期
*/
public UFDate billdate;
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
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*水电费缴费单主键
*/
public String pk_payment;
/**
*备注
*/
public String remark;
/**
*时间戳
*/
public UFDateTime ts;
/**
*单据号
*/
public String vbillno;
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
*来源单据id
*/
public String vsrcid;
/**
*来源单据类型
*/
public String vsrctype;
/**
*项目信息
*/
private String pk_project;
/**
*应收款日期
*/
public UFDate dysdate;
/**
*应收金额
*/
public UFDouble nysmny;
/**
*已收金额
*/
public UFDouble nskmny;
/**
*会计年月
*/
public String accountingyear;
/**
*收费项目
*/
public String pk_costproject;

public Integer dr=0;
/** 
* 获取项目信息
*
* @return 项目信息
*/
public String getPk_project() {
	return pk_project;
}
/** 
* 设置项目信息
*
* @param pk_project 项目信息
*/
public void setPk_project(String pk_project) {
	this.pk_project = pk_project;
}
/** 
* 获取应收款日期
*
* @return 应收款日期
*/
public UFDate getDysdate() {
	return dysdate;
}
/** 
* 设置应收款日期
*
* @param dysdate 应收款日期
*/
public void setDysdate(UFDate dysdate) {
	this.dysdate = dysdate;
}
/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getNysmny() {
	return nysmny;
}
/** 
* 设置应收金额
*
* @param nysmny 应收金额
*/
public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}
/** 
* 获取已收金额
*
* @return 已收金额
*/
public UFDouble getNskmny() {
	return nskmny;
}
/** 
* 设置已收金额
*
* @param nskmny 已收金额
*/
public void setNskmny(UFDouble nskmny) {
	this.nskmny = nskmny;
}
/** 
* 获取会计年月
*
* @return 会计年月
*/
public String getAccountingyear() {
	return accountingyear;
}
/** 
* 设置会计年月
*
* @param accountingyear 会计年月
*/
public void setAccountingyear(String accountingyear) {
	this.accountingyear = accountingyear;
}
/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getPk_costproject() {
	return pk_costproject;
}
/** 
* 设置收费项目
*
* @param pk_costproject 收费项目
*/
public void setPk_costproject(String pk_costproject) {
	this.pk_costproject = pk_costproject;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* 设置审批批语
*
* @param approvenote 审批批语
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* 获取审批人
*
* @return 审批人
*/
public String getApprover () {
return this.approver;
 } 

/** 
* 设置审批人
*
* @param approver 审批人
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* 获取审批时间
*
* @return 审批时间
*/
public UFDateTime getApprovetime () {
return this.approvetime;
 } 

/** 
* 设置审批时间
*
* @param approvetime 审批时间
*/
public void setApprovetime ( UFDateTime approvetime) {
this.approvetime=approvetime;
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getBilldate () {
return this.billdate;
 } 

/** 
* 设置单据日期
*
* @param billdate 单据日期
*/
public void setBilldate ( UFDate billdate) {
this.billdate=billdate;
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
* 获取水电费缴费单主键
*
* @return 水电费缴费单主键
*/
public String getPk_payment () {
return this.pk_payment;
 } 

/** 
* 设置水电费缴费单主键
*
* @param pk_payment 水电费缴费单主键
*/
public void setPk_payment ( String pk_payment) {
this.pk_payment=pk_payment;
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
public String getVbillno () {
return this.vbillno;
 } 

/** 
* 设置单据号
*
* @param vbillno 单据号
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.payment");
  }
}