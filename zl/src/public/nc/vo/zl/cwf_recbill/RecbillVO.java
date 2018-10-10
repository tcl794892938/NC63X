package nc.vo.zl.cwf_recbill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RecbillVO extends SuperVO {
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
*费用开始日期
*/
public UFDate begindate;
/**
*单据类型编码
*/
public String billtypecode;
/**
*会计年月
*/
public String caccountperiod;
/**
*收入确认金额
*/
public UFDouble confirm;
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
*费用截止日期
*/
public UFDate enddate;
/**
*应缴款日期
*/
public UFDate gatherdate;
/**
*开票金额
*/
public UFDouble invoicemoney;
/**
*开票号
*/
public String invoiceno;
/**
*备注
*/
public String memo;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*无税金额
*/
public UFDouble nnotaxmoney;
/**
*已收金额
*/
public UFDouble nrealmoney;
/**
*应收金额
*/
public UFDouble nrecmoney;
/**
*税额
*/
public UFDouble ntaxmoney;
/**
 * 税率
 */
public UFDouble ntaxrate;
/**
*单据类型
*/
public String pk_billtype;
/**
*楼栋
*/
public String pk_building;
/**
*收费项目
*/
public String pk_costproject;
/**
*客户名称
*/
public String pk_customer;
/**
*集团
*/
public String pk_group;
/**
*房产名称
*/
public String pk_house;
/**
 * 车牌号
 */
public String pk_carno;
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
*主键
*/
public String pk_recbill;
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
 * 是否删除
 */
public Integer dr=0;


public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public String getPk_carno() {
	return pk_carno;
}

public void setPk_carno(String pk_carno) {
	this.pk_carno = pk_carno;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
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
* 获取费用开始日期
*
* @return 费用开始日期
*/
public UFDate getBegindate () {
return this.begindate;
 } 

/** 
* 设置费用开始日期
*
* @param begindate 费用开始日期
*/
public void setBegindate ( UFDate begindate) {
this.begindate=begindate;
 } 

/** 
* 获取单据类型编码
*
* @return 单据类型编码
*/
public String getBilltypecode () {
return this.billtypecode;
 } 

/** 
* 设置单据类型编码
*
* @param billtypecode 单据类型编码
*/
public void setBilltypecode ( String billtypecode) {
this.billtypecode=billtypecode;
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
* 获取收入确认金额
*
* @return 收入确认金额
*/
public UFDouble getConfirm () {
return this.confirm;
 } 

/** 
* 设置收入确认金额
*
* @param confirm 收入确认金额
*/
public void setConfirm ( UFDouble confirm) {
this.confirm=confirm;
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
* 获取费用截止日期
*
* @return 费用截止日期
*/
public UFDate getEnddate () {
return this.enddate;
 } 

/** 
* 设置费用截止日期
*
* @param enddate 费用截止日期
*/
public void setEnddate ( UFDate enddate) {
this.enddate=enddate;
 } 

/** 
* 获取应缴款日期
*
* @return 应缴款日期
*/
public UFDate getGatherdate () {
return this.gatherdate;
 } 

/** 
* 设置应缴款日期
*
* @param gatherdate 应缴款日期
*/
public void setGatherdate ( UFDate gatherdate) {
this.gatherdate=gatherdate;
 } 

/** 
* 获取开票金额
*
* @return 开票金额
*/
public UFDouble getInvoicemoney () {
return this.invoicemoney;
 } 

/** 
* 设置开票金额
*
* @param invoicemoney 开票金额
*/
public void setInvoicemoney ( UFDouble invoicemoney) {
this.invoicemoney=invoicemoney;
 } 

/** 
* 获取开票号
*
* @return 开票号
*/
public String getInvoiceno () {
return this.invoiceno;
 } 

/** 
* 设置开票号
*
* @param invoiceno 开票号
*/
public void setInvoiceno ( String invoiceno) {
this.invoiceno=invoiceno;
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getMemo () {
return this.memo;
 } 

/** 
* 设置备注
*
* @param memo 备注
*/
public void setMemo ( String memo) {
this.memo=memo;
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
* 获取无税金额
*
* @return 无税金额
*/
public UFDouble getNnotaxmoney () {
return this.nnotaxmoney;
 } 

/** 
* 设置无税金额
*
* @param nnotaxmoney 无税金额
*/
public void setNnotaxmoney ( UFDouble nnotaxmoney) {
this.nnotaxmoney=nnotaxmoney;
 } 

/** 
* 获取已收金额
*
* @return 已收金额
*/
public UFDouble getNrealmoney () {
return this.nrealmoney;
 } 

/** 
* 设置已收金额
*
* @param nrealmoney 已收金额
*/
public void setNrealmoney ( UFDouble nrealmoney) {
this.nrealmoney=nrealmoney;
 } 

/** 
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getNrecmoney () {
return this.nrecmoney;
 } 

/** 
* 设置应收金额
*
* @param nrecmoney 应收金额
*/
public void setNrecmoney ( UFDouble nrecmoney) {
this.nrecmoney=nrecmoney;
 } 

/** 
* 获取税额
*
* @return 税额
*/
public UFDouble getNtaxmoney () {
return this.ntaxmoney;
 } 

/** 
* 设置税额
*
* @param ntaxmoney 税额
*/
public void setNtaxmoney ( UFDouble ntaxmoney) {
this.ntaxmoney=ntaxmoney;
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
* 获取楼栋
*
* @return 楼栋
*/
public String getPk_building () {
return this.pk_building;
 } 

/** 
* 设置楼栋
*
* @param pk_building 楼栋
*/
public void setPk_building ( String pk_building) {
this.pk_building=pk_building;
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getPk_costproject () {
return this.pk_costproject;
 } 

/** 
* 设置收费项目
*
* @param pk_costproject 收费项目
*/
public void setPk_costproject ( String pk_costproject) {
this.pk_costproject=pk_costproject;
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
* 获取房产名称
*
* @return 房产名称
*/
public String getPk_house () {
return this.pk_house;
 } 

/** 
* 设置房产名称
*
* @param pk_house 房产名称
*/
public void setPk_house ( String pk_house) {
this.pk_house=pk_house;
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
* 获取主键
*
* @return 主键
*/
public String getPk_recbill () {
return this.pk_recbill;
 } 

/** 
* 设置主键
*
* @param pk_recbill 主键
*/
public void setPk_recbill ( String pk_recbill) {
this.pk_recbill=pk_recbill;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.recbill");
  }
}