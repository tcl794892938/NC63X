package nc.vo.zl.cwf_gather;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class GatherVO extends SuperVO {
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
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
/**
*收款日期
*/
public UFDate dbilldate;
/**
*是否已传会计平台
*/
public UFBoolean is_kjpt;
/**
*是否传凭证
*/
public UFBoolean is_voucher;
/**
 * 是否自制单据
 */
public UFBoolean isadd;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*汇率
*/
public UFDouble ncurrrate;
/**
*优惠金额
*/
public UFDouble ndiscountmny;
/**
*应收总金额
*/
public UFDouble nrecmny;
/**
*已收总金额
*/
public UFDouble nysmny;
/**
*收款总金额
*/
public UFDouble nskmny;
/**
*税率
*/
public UFDouble ntaxrate;
/**
*银行账号
*/
public String pk_accountno;
/**
*单据类型
*/
public String pk_billtype;
/**
*现金流量
*/
public String pk_cashflow;
/**
*币种
*/
public String pk_currtype;
/**
*客户名称
*/
public String pk_customer;
/**
*主键
*/
public String pk_gather;
/**
*集团
*/
public String pk_group;
/**
*款项
*/
public String pk_moneytype;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*付款形式
*/
public String pk_paytype;
/**
*项目信息
*/
public String pk_project;
/**
*置业顾问
*/
public String pk_psndoc;
/**
*收款人
*/
public String pk_skr;
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
*attrDisplayName
*/
public String vsrcbid;
/**
*来源单据id
*/
public String vsrcid;
/**
*来源单据类型
*/
public String vsrctype;




public UFBoolean getIsadd() {
	return isadd;
}

public void setIsadd(UFBoolean isadd) {
	this.isadd = isadd;
}

public UFDouble getNrecmny() {
	return nrecmny;
}

public void setNrecmny(UFDouble nrecmny) {
	this.nrecmny = nrecmny;
}

public UFDouble getNysmny() {
	return nysmny;
}

public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
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
* 获取收款日期
*
* @return 收款日期
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* 设置收款日期
*
* @param dbilldate 收款日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* 获取是否已传会计平台
*
* @return 是否已传会计平台
*/
public UFBoolean getIs_kjpt () {
return this.is_kjpt;
 } 

/** 
* 设置是否已传会计平台
*
* @param is_kjpt 是否已传会计平台
*/
public void setIs_kjpt ( UFBoolean is_kjpt) {
this.is_kjpt=is_kjpt;
 } 

/** 
* 获取是否传凭证
*
* @return 是否传凭证
*/
public UFBoolean getIs_voucher () {
return this.is_voucher;
 } 

/** 
* 设置是否传凭证
*
* @param is_voucher 是否传凭证
*/
public void setIs_voucher ( UFBoolean is_voucher) {
this.is_voucher=is_voucher;
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
* 获取汇率
*
* @return 汇率
*/
public UFDouble getNcurrrate () {
return this.ncurrrate;
 } 

/** 
* 设置汇率
*
* @param ncurrrate 汇率
*/
public void setNcurrrate ( UFDouble ncurrrate) {
this.ncurrrate=ncurrrate;
 } 

/** 
* 获取优惠金额
*
* @return 优惠金额
*/
public UFDouble getNdiscountmny () {
return this.ndiscountmny;
 } 

/** 
* 设置优惠金额
*
* @param ndiscountmny 优惠金额
*/
public void setNdiscountmny ( UFDouble ndiscountmny) {
this.ndiscountmny=ndiscountmny;
 } 

/** 
* 获取收款总金额
*
* @return 收款总金额
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* 设置收款总金额
*
* @param nskmny 收款总金额
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 

/** 
* 获取税率
*
* @return 税率
*/
public UFDouble getNtaxrate () {
return this.ntaxrate;
 } 

/** 
* 设置税率
*
* @param ntaxrate 税率
*/
public void setNtaxrate ( UFDouble ntaxrate) {
this.ntaxrate=ntaxrate;
 } 

/** 
* 获取银行账号
*
* @return 银行账号
*/
public String getPk_accountno () {
return this.pk_accountno;
 } 

/** 
* 设置银行账号
*
* @param pk_accountno 银行账号
*/
public void setPk_accountno ( String pk_accountno) {
this.pk_accountno=pk_accountno;
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
* 获取现金流量
*
* @return 现金流量
*/
public String getPk_cashflow () {
return this.pk_cashflow;
 } 

/** 
* 设置现金流量
*
* @param pk_cashflow 现金流量
*/
public void setPk_cashflow ( String pk_cashflow) {
this.pk_cashflow=pk_cashflow;
 } 

/** 
* 获取币种
*
* @return 币种
*/
public String getPk_currtype () {
return this.pk_currtype;
 } 

/** 
* 设置币种
*
* @param pk_currtype 币种
*/
public void setPk_currtype ( String pk_currtype) {
this.pk_currtype=pk_currtype;
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
* 获取主键
*
* @return 主键
*/
public String getPk_gather () {
return this.pk_gather;
 } 

/** 
* 设置主键
*
* @param pk_gather 主键
*/
public void setPk_gather ( String pk_gather) {
this.pk_gather=pk_gather;
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
* 获取款项
*
* @return 款项
*/
public String getPk_moneytype () {
return this.pk_moneytype;
 } 

/** 
* 设置款项
*
* @param pk_moneytype 款项
*/
public void setPk_moneytype ( String pk_moneytype) {
this.pk_moneytype=pk_moneytype;
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
* 获取付款形式
*
* @return 付款形式
*/
public String getPk_paytype () {
return this.pk_paytype;
 } 

/** 
* 设置付款形式
*
* @param pk_paytype 付款形式
*/
public void setPk_paytype ( String pk_paytype) {
this.pk_paytype=pk_paytype;
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
* 获取置业顾问
*
* @return 置业顾问
*/
public String getPk_psndoc () {
return this.pk_psndoc;
 } 

/** 
* 设置置业顾问
*
* @param pk_psndoc 置业顾问
*/
public void setPk_psndoc ( String pk_psndoc) {
this.pk_psndoc=pk_psndoc;
 } 

/** 
* 获取收款人
*
* @return 收款人
*/
public String getPk_skr () {
return this.pk_skr;
 } 

/** 
* 设置收款人
*
* @param pk_skr 收款人
*/
public void setPk_skr ( String pk_skr) {
this.pk_skr=pk_skr;
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
* 获取attrDisplayName
*
* @return attrDisplayName
*/
public String getVsrcbid () {
return this.vsrcbid;
 } 

/** 
* 设置attrDisplayName
*
* @param vsrcbid attrDisplayName
*/
public void setVsrcbid ( String vsrcbid) {
this.vsrcbid=vsrcbid;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.gather");
  }
}