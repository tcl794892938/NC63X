package nc.vo.zl.lyw_confirmation;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ConfirmationVO extends SuperVO {
/**
*确认收入金额
*/
public UFDouble amountconfirmed;
/**
*应收金额
*/
public UFDouble amountreceivable;
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
*楼栋号
*/
public String buildno;
/**
*会计年月
*/
public String caccountperiod;
/**
*收费项目
*/
public String chargingproject;
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
*收款日期
*/
public UFDate dcollectiondate;
/**
*费用截止日期
*/
public UFDate dfeeenddate;
/**
*费用开始日期
*/
public UFDate dfeestartdate;
/**
*房产名称
*/
public String houseproperty;
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
*收入确认单主键
*/
public String pk_confirmation;
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
*项目信息
*/
public String pk_project;
/**
*行号
*/
public String rowno;
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
public Integer dr;
public UFDate dreccollectdate;
public UFDouble nnotaxmny;
public UFDouble ntaxmny;
public UFDouble nrentarea;
public UFDouble ntaxrate;

/**
 * 是否自制单据
 */
public UFBoolean isadd;



public UFBoolean getIsadd() {
	return isadd;
}

public void setIsadd(UFBoolean isadd) {
	this.isadd = isadd;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取确认收入金额
*
* @return 确认收入金额
*/
public UFDouble getAmountconfirmed () {
return this.amountconfirmed;
 } 

/** 
* 设置确认收入金额
*
* @param amountconfirmed 确认收入金额
*/
public void setAmountconfirmed ( UFDouble amountconfirmed) {
this.amountconfirmed=amountconfirmed;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getAmountreceivable () {
return this.amountreceivable;
 } 

/** 
* 设置应收金额
*
* @param amountreceivable 应收金额
*/
public void setAmountreceivable ( UFDouble amountreceivable) {
this.amountreceivable=amountreceivable;
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
* 获取楼栋号
*
* @return 楼栋号
*/
public String getBuildno () {
return this.buildno;
 } 

/** 
* 设置楼栋号
*
* @param buildno 楼栋号
*/
public void setBuildno ( String buildno) {
this.buildno=buildno;
 } 

/** 
* 获取会计年月
*
* @return 会计年月
*/
public String getCaccountperiod () {
return this.caccountperiod;
 } 

/** 
* 设置会计年月
*
* @param caccountperiod 会计年月
*/
public void setCaccountperiod ( String caccountperiod) {
this.caccountperiod=caccountperiod;
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getChargingproject () {
return this.chargingproject;
 } 

/** 
* 设置收费项目
*
* @param chargingproject 收费项目
*/
public void setChargingproject ( String chargingproject) {
this.chargingproject=chargingproject;
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
* 获取收款日期
*
* @return 收款日期
*/
public UFDate getDcollectiondate () {
return this.dcollectiondate;
 } 

/** 
* 设置收款日期
*
* @param dcollectiondate 收款日期
*/
public void setDcollectiondate ( UFDate dcollectiondate) {
this.dcollectiondate=dcollectiondate;
 } 

/** 
* 获取费用截止日期
*
* @return 费用截止日期
*/
public UFDate getDfeeenddate () {
return this.dfeeenddate;
 } 

/** 
* 设置费用截止日期
*
* @param dfeeenddate 费用截止日期
*/
public void setDfeeenddate ( UFDate dfeeenddate) {
this.dfeeenddate=dfeeenddate;
 } 

/** 
* 获取费用开始日期
*
* @return 费用开始日期
*/
public UFDate getDfeestartdate () {
return this.dfeestartdate;
 } 

/** 
* 设置费用开始日期
*
* @param dfeestartdate 费用开始日期
*/
public void setDfeestartdate ( UFDate dfeestartdate) {
this.dfeestartdate=dfeestartdate;
 } 

/** 
* 获取房产名称
*
* @return 房产名称
*/
public String getHouseproperty () {
return this.houseproperty;
 } 

/** 
* 设置房产名称
*
* @param houseproperty 房产名称
*/
public void setHouseproperty ( String houseproperty) {
this.houseproperty=houseproperty;
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
* 获取收入确认单主键
*
* @return 收入确认单主键
*/
public String getPk_confirmation () {
return this.pk_confirmation;
 } 

/** 
* 设置收入确认单主键
*
* @param pk_confirmation 收入确认单主键
*/
public void setPk_confirmation ( String pk_confirmation) {
this.pk_confirmation=pk_confirmation;
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
* 获取行号
*
* @return 行号
*/
public String getRowno () {
return this.rowno;
 } 

/** 
* 设置行号
*
* @param rowno 行号
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
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

public UFDate getDreccollectdate() {
	return dreccollectdate;
}

public void setDreccollectdate(UFDate dreccollectdate) {
	this.dreccollectdate = dreccollectdate;
}

/** 
* 设置来源单据类型
*
* @param vsrctype 来源单据类型
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  public UFDouble getNnotaxmny() {
	return nnotaxmny;
}

public void setNnotaxmny(UFDouble nnotaxmny) {
	this.nnotaxmny = nnotaxmny;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

public UFDouble getNrentarea() {
	return nrentarea;
}

public void setNrentarea(UFDouble nrentarea) {
	this.nrentarea = nrentarea;
}

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

@Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.confirmation");
  }
}