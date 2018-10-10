package nc.vo.zl.hql_throwalease;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ThrowaleaseVO extends SuperVO {
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
*合同号
*/
public String contractid;
/**
*合同名称
*/
public String contractname;
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
public UFDate denddate;
/**
*进场日期
*/
public UFDate dentrydate;
/**
*退租日期
*/
public UFDate dtzdate;
/**
*合同起始日
*/
public UFDate dstartdate;
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
*项目
*/
public String pk_project;
/**
*退租管理主键
*/
public String pk_throwalease;
/**
*备注
*/
public String remark;
/**
*时间戳
*/
public UFDateTime ts;
/**
*退租类型
*/
public Integer tztype;
/**
*单据编号
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

private Integer dr = 0;

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
* 获取合同号
*
* @return 合同号
*/
public String getContractid () {
return this.contractid;
 } 

/** 
* 设置合同号
*
* @param contractid 合同号
*/
public void setContractid ( String contractid) {
this.contractid=contractid;
 } 

/** 
* 获取合同名称
*
* @return 合同名称
*/
public String getContractname () {
return this.contractname;
 } 

/** 
* 设置合同名称
*
* @param contractname 合同名称
*/
public void setContractname ( String contractname) {
this.contractname=contractname;
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
public UFDate getDenddate () {
return this.denddate;
 } 

/** 
* 设置合同终止日
*
* @param denddate 合同终止日
*/
public void setDenddate ( UFDate denddate) {
this.denddate=denddate;
 } 

/** 
* 获取合同起始日
*
* @return 合同起始日
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* 设置合同起始日
*
* @param dstartdate 合同起始日
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* 获取进场日期
*
* @return 进场日期
*/
public UFDate getDentrydate() {
	return dentrydate;
}

/**
 * 设置进场日期
 * 
 * @param dentrydate 进场日期
 */
public void setDentrydate(UFDate dentrydate) {
	this.dentrydate = dentrydate;
}
/** 
* 获取退租日期
*
* @return 退租日期
*/
public UFDate getDtzdate() {
	return dtzdate;
}
/**
 * 设置退租日期
 * 
 * @param dtzdate 退租日期
 */
public void setDtzdate(UFDate dtzdate) {
	this.dtzdate = dtzdate;
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
* 获取退租管理主键
*
* @return 退租管理主键
*/
public String getPk_throwalease () {
return this.pk_throwalease;
 } 

/** 
* 设置退租管理主键
*
* @param pk_throwalease 退租管理主键
*/
public void setPk_throwalease ( String pk_throwalease) {
this.pk_throwalease=pk_throwalease;
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
* 获取退租类型
*
* @return 退租类型
* @see String
*/
public Integer getTztype () {
return this.tztype;
 } 

/** 
* 设置退租类型
*
* @param tztype 退租类型
* @see String
*/
public void setTztype ( Integer tztype) {
this.tztype=tztype;
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillno () {
return this.vbillno;
 } 

/** 
* 设置单据编号
*
* @param vbillno 单据编号
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
    return VOMetaFactory.getInstance().getVOMeta("zl.throwalease");
  }
}