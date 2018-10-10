package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractZjmxVO extends SuperVO {
/**
*免租结束日期
*/
public UFDate denddate;
/**
*免租开始日期
*/
public UFDate dstartdate;
/**
*上层单据主键
*/
public String pk_contract;
/**
*主键
*/
public String pk_contract_zjmx;
/**
*行号
*/
public String rowno;
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
*备注
*/
public String vmemo;

public UFDouble nhtmny;
public UFDouble nyearmny;

public UFDouble nht1mny;
public UFDouble nyear1mny;

public UFDouble nht2mny;
public UFDouble nyear2mny;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}
/** 
* 获取免租结束日期
*
* @return 免租结束日期
*/
public UFDate getDenddate () {
return this.denddate;
 } 

/** 
* 设置免租结束日期
*
* @param denddate 免租结束日期
*/
public void setDenddate ( UFDate denddate) {
this.denddate=denddate;
 } 

/** 
* 获取免租开始日期
*
* @return 免租开始日期
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* 设置免租开始日期
*
* @param dstartdate 免租开始日期
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_contract () {
return this.pk_contract;
 } 

/** 
* 设置上层单据主键
*
* @param pk_contract 上层单据主键
*/
public void setPk_contract ( String pk_contract) {
this.pk_contract=pk_contract;
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


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zjmx");
  }

public String getPk_contract_zjmx() {
	return pk_contract_zjmx;
}

public void setPk_contract_zjmx(String pk_contract_zjmx) {
	this.pk_contract_zjmx = pk_contract_zjmx;
}

public UFDouble getNhtmny() {
	return nhtmny;
}

public void setNhtmny(UFDouble nhtmny) {
	this.nhtmny = nhtmny;
}

public UFDouble getNyearmny() {
	return nyearmny;
}

public void setNyearmny(UFDouble nyearmny) {
	this.nyearmny = nyearmny;
}

public UFDouble getNht1mny() {
	return nht1mny;
}

public void setNht1mny(UFDouble nht1mny) {
	this.nht1mny = nht1mny;
}

public UFDouble getNyear1mny() {
	return nyear1mny;
}

public void setNyear1mny(UFDouble nyear1mny) {
	this.nyear1mny = nyear1mny;
}

public UFDouble getNht2mny() {
	return nht2mny;
}

public void setNht2mny(UFDouble nht2mny) {
	this.nht2mny = nht2mny;
}

public UFDouble getNyear2mny() {
	return nyear2mny;
}

public void setNyear2mny(UFDouble nyear2mny) {
	this.nyear2mny = nyear2mny;
}
}