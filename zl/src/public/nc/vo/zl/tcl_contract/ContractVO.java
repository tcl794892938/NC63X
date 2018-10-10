package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractVO extends SuperVO {
/**
*审核批语
*/
public String approvenote;
/**
*审核人
*/
public String approver;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
/**
*审核时间
*/
public UFDateTime dapprovetime;
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
public UFDate dindate;
/**
*退租日期
*/
public UFDate doutdate;
/**
*签租日期
*/
public UFDate drentdate;
/**
*合同起始日
*/
public UFDate dstartdate;
/**
*合同编号
*/
public String htcode;
/**
*合同名称
*/
public String htname;
/**
*合同状态
*/
public Integer htstatus;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*租赁总面积
*/
public UFDouble narea;
/**
*日租金
*/
public UFDouble ndaymny;
/**
*租金总金额
*/
public UFDouble nmny;
/**
*月租金
*/
public UFDouble nmonthmny;
/**
*租金单价
*/
public UFDouble nrentprice;
/**
*年租金
*/
public UFDouble nyearmny;
/**
*优惠金额
*/
public UFDouble nyhmny;
/**
*付款方式
*/
public Integer paystyle;
/**
*单据类型
*/
public String pk_billtype;
/**
*主键
*/
public String pk_contract;
/**
*合同类型
*/
public String pk_contracttype;
/**
*收费项目
*/
public String pk_costproject;

public Integer ndegree;
/**
*集团
*/
public String pk_group;
/**
*财务组织
*/
public String pk_org;
/**
*财务组织版本
*/
public String pk_org_v;
/**
*租赁方式
*/
public Integer rentstyle;
/**
*合同税率
*/
public Integer taxstyle;
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
*版本
*/
public Integer version;
/**
*备注
*/
public String vmemo;
/**
*来源单据id
*/
public String vsrcid;
/**
*来源单据类型
*/
public String vsrctype;

/**
*客户
*/
public String pk_customer;

/**
*项目
*/
public String pk_project;

public UFDouble nbzjmny;
public UFDouble nzqmny;
public String vmzq;
public String vdzfs;

public UFBoolean is_mz;
public UFDouble nysmny;
public String vsrcno;

public Integer dr=0;

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
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getDapprovetime () {
return this.dapprovetime;
 } 

/** 
* 设置审核时间
*
* @param dapprovetime 审核时间
*/
public void setDapprovetime ( UFDateTime dapprovetime) {
this.dapprovetime=dapprovetime;
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
* 获取进场日期
*
* @return 进场日期
*/
public UFDate getDindate () {
return this.dindate;
 } 

/** 
* 设置进场日期
*
* @param dindate 进场日期
*/
public void setDindate ( UFDate dindate) {
this.dindate=dindate;
 } 

/** 
* 获取退租日期
*
* @return 退租日期
*/
public UFDate getDoutdate () {
return this.doutdate;
 } 

/** 
* 设置退租日期
*
* @param doutdate 退租日期
*/
public void setDoutdate ( UFDate doutdate) {
this.doutdate=doutdate;
 } 

/** 
* 获取签租日期
*
* @return 签租日期
*/
public UFDate getDrentdate () {
return this.drentdate;
 } 

/** 
* 设置签租日期
*
* @param drentdate 签租日期
*/
public void setDrentdate ( UFDate drentdate) {
this.drentdate=drentdate;
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
* 获取合同编号
*
* @return 合同编号
*/
public String getHtcode () {
return this.htcode;
 } 

/** 
* 设置合同编号
*
* @param htcode 合同编号
*/
public void setHtcode ( String htcode) {
this.htcode=htcode;
 } 

/** 
* 获取合同名称
*
* @return 合同名称
*/
public String getHtname () {
return this.htname;
 } 

/** 
* 设置合同名称
*
* @param htname 合同名称
*/
public void setHtname ( String htname) {
this.htname=htname;
 } 

/** 
* 获取合同状态
*
* @return 合同状态
* @see String
*/
public Integer getHtstatus () {
return this.htstatus;
 } 

/** 
* 设置合同状态
*
* @param htstatus 合同状态
* @see String
*/
public void setHtstatus ( Integer htstatus) {
this.htstatus=htstatus;
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
* 获取租赁总面积
*
* @return 租赁总面积
*/
public UFDouble getNarea () {
return this.narea;
 } 

/** 
* 设置租赁总面积
*
* @param narea 租赁总面积
*/
public void setNarea ( UFDouble narea) {
this.narea=narea;
 } 

/** 
* 获取日租金
*
* @return 日租金
*/
public UFDouble getNdaymny () {
return this.ndaymny;
 } 

/** 
* 设置日租金
*
* @param ndaymny 日租金
*/
public void setNdaymny ( UFDouble ndaymny) {
this.ndaymny=ndaymny;
 } 

/** 
* 获取租金总金额
*
* @return 租金总金额
*/
public UFDouble getNmny () {
return this.nmny;
 } 

/** 
* 设置租金总金额
*
* @param nmny 租金总金额
*/
public void setNmny ( UFDouble nmny) {
this.nmny=nmny;
 } 

/** 
* 获取月租金
*
* @return 月租金
*/
public UFDouble getNmonthmny () {
return this.nmonthmny;
 } 

/** 
* 设置月租金
*
* @param nmonthmny 月租金
*/
public void setNmonthmny ( UFDouble nmonthmny) {
this.nmonthmny=nmonthmny;
 } 

/** 
* 获取租金单价
*
* @return 租金单价
*/
public UFDouble getNrentprice () {
return this.nrentprice;
 } 

/** 
* 设置租金单价
*
* @param nrentprice 租金单价
*/
public void setNrentprice ( UFDouble nrentprice) {
this.nrentprice=nrentprice;
 } 

/** 
* 获取年租金
*
* @return 年租金
*/
public UFDouble getNyearmny () {
return this.nyearmny;
 } 

/** 
* 设置年租金
*
* @param nyearmny 年租金
*/
public void setNyearmny ( UFDouble nyearmny) {
this.nyearmny=nyearmny;
 } 

/** 
* 获取优惠金额
*
* @return 优惠金额
*/
public UFDouble getNyhmny () {
return this.nyhmny;
 } 

/** 
* 设置优惠金额
*
* @param nyhmny 优惠金额
*/
public void setNyhmny ( UFDouble nyhmny) {
this.nyhmny=nyhmny;
 } 

/** 
* 获取付款方式
*
* @return 付款方式
* @see String
*/
public Integer getPaystyle () {
return this.paystyle;
 } 

/** 
* 设置付款方式
*
* @param paystyle 付款方式
* @see String
*/
public void setPaystyle ( Integer paystyle) {
this.paystyle=paystyle;
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
* 获取主键
*
* @return 主键
*/
public String getPk_contract () {
return this.pk_contract;
 } 

/** 
* 设置主键
*
* @param pk_contract 主键
*/
public void setPk_contract ( String pk_contract) {
this.pk_contract=pk_contract;
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
* 获取财务组织
*
* @return 财务组织
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* 设置财务组织
*
* @param pk_org 财务组织
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* 获取财务组织版本
*
* @return 财务组织版本
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* 设置财务组织版本
*
* @param pk_org_v 财务组织版本
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* 获取租赁方式
*
* @return 租赁方式
* @see String
*/
public Integer getRentstyle () {
return this.rentstyle;
 } 

/** 
* 设置租赁方式
*
* @param rentstyle 租赁方式
* @see String
*/
public void setRentstyle ( Integer rentstyle) {
this.rentstyle=rentstyle;
 } 

/** 
* 获取合同税率
*
* @return 合同税率
* @see String
*/
public Integer getTaxstyle () {
return this.taxstyle;
 } 

/** 
* 设置合同税率
*
* @param taxstyle 合同税率
* @see String
*/
public void setTaxstyle ( Integer taxstyle) {
this.taxstyle=taxstyle;
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
* 获取版本
*
* @return 版本
*/
public Integer getVersion () {
return this.version;
 } 

/** 
* 设置版本
*
* @param version 版本
*/
public void setVersion ( Integer version) {
this.version=version;
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return this.vmemo;
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.vmemo=vmemo;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zl");
  }

public String getPk_customer() {
	return pk_customer;
}

public void setPk_customer(String pk_customer) {
	this.pk_customer = pk_customer;
}

public String getPk_project() {
	return pk_project;
}

public void setPk_project(String pk_project) {
	this.pk_project = pk_project;
}

public UFDouble getNbzjmny() {
	return nbzjmny;
}

public void setNbzjmny(UFDouble nbzjmny) {
	this.nbzjmny = nbzjmny;
}

public UFDouble getNzqmny() {
	return nzqmny;
}

public void setNzqmny(UFDouble nzqmny) {
	this.nzqmny = nzqmny;
}

public String getVmzq() {
	return vmzq;
}

public void setVmzq(String vmzq) {
	this.vmzq = vmzq;
}

public String getVdzfs() {
	return vdzfs;
}

public void setVdzfs(String vdzfs) {
	this.vdzfs = vdzfs;
}

public UFBoolean getIs_mz() {
	return is_mz;
}

public void setIs_mz(UFBoolean is_mz) {
	this.is_mz = is_mz;
}

public UFDouble getNysmny() {
	return nysmny;
}

public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}

public String getVsrcno() {
	return vsrcno;
}

public void setVsrcno(String vsrcno) {
	this.vsrcno = vsrcno;
}

public Integer getNdegree() {
	return ndegree;
}

public void setNdegree(Integer ndegree) {
	this.ndegree = ndegree;
}
}