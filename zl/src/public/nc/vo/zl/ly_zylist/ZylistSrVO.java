package nc.vo.zl.ly_zylist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZylistSrVO extends SuperVO {
/**
*完成情况
*/
public String finishqk;
/**
*完成时间
*/
public UFDateTime finishtime;
/**
*收费项目
*/
public String payproject;
/**
*上层单据主键
*/
public String pk_zylist;
/**
*主键
*/
public String pk_zylist_sr;
/**
*备注
*/
public String remarks;
/**
*维修服务人员
*/
public String serviceman;
/**
*时间戳
*/
public UFDateTime ts;
/**
*未完成原因
*/
public String unfinishyy;
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
*应收金额
*/
public UFDouble ygsmoney=new UFDouble(0);
/**
*已收金额
*/
public UFDouble yjsmoney=new UFDouble(0);
/**
 * 是否删除
 */
public Integer dr=0;
/**
*会计年月
*/
public String caccountperiod;
/**
*行号
*/
public String rowno;
public UFDouble nconfirmed;

public UFDouble getNconfirmed() {
	return nconfirmed;
}

public void setNconfirmed(UFDouble nconfirmed) {
	this.nconfirmed = nconfirmed;
}

public String getRowno() {
	return rowno;
}

public void setRowno(String rowno) {
	this.rowno = rowno;
}

public String getCaccountperiod() {
	return caccountperiod;
}

public void setCaccountperiod(String caccountperiod) {
	this.caccountperiod = caccountperiod;
}

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/** 
* 获取完成情况
*
* @return 完成情况
*/
public String getFinishqk () {
return this.finishqk;
 } 

/** 
* 设置完成情况
*
* @param finishqk 完成情况
*/
public void setFinishqk ( String finishqk) {
this.finishqk=finishqk;
 } 

/** 
* 获取完成时间
*
* @return 完成时间
*/
public UFDateTime getFinishtime () {
return this.finishtime;
 } 

/** 
* 设置完成时间
*
* @param finishtime 完成时间
*/
public void setFinishtime ( UFDateTime finishtime) {
this.finishtime=finishtime;
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getPayproject () {
return this.payproject;
 } 

/** 
* 设置收费项目
*
* @param payproject 收费项目
*/
public void setPayproject ( String payproject) {
this.payproject=payproject;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_zylist () {
return this.pk_zylist;
 } 

/** 
* 设置上层单据主键
*
* @param pk_zylist 上层单据主键
*/
public void setPk_zylist ( String pk_zylist) {
this.pk_zylist=pk_zylist;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_zylist_sr () {
return this.pk_zylist_sr;
 } 

/** 
* 设置主键
*
* @param pk_zylist_sr 主键
*/
public void setPk_zylist_sr ( String pk_zylist_sr) {
this.pk_zylist_sr=pk_zylist_sr;
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getRemarks () {
return this.remarks;
 } 

/** 
* 设置备注
*
* @param remarks 备注
*/
public void setRemarks ( String remarks) {
this.remarks=remarks;
 } 

/** 
* 获取维修服务人员
*
* @return 维修服务人员
*/
public String getServiceman () {
return this.serviceman;
 } 

/** 
* 设置维修服务人员
*
* @param serviceman 维修服务人员
*/
public void setServiceman ( String serviceman) {
this.serviceman=serviceman;
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
* 获取未完成原因
*
* @return 未完成原因
*/
public String getUnfinishyy () {
return this.unfinishyy;
 } 

/** 
* 设置未完成原因
*
* @param unfinishyy 未完成原因
*/
public void setUnfinishyy ( String unfinishyy) {
this.unfinishyy=unfinishyy;
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
* 获取应收金额
*
* @return 应收金额
*/
public UFDouble getYgsmoney () {
return this.ygsmoney;
 } 

/** 
* 设置应收金额
*
* @param ygsmoney 应收金额
*/
public void setYgsmoney ( UFDouble ygsmoney) {
this.ygsmoney=ygsmoney;
 } 

/** 
* 获取已收金额
*
* @return 已收金额
*/
public UFDouble getYjsmoney () {
return this.yjsmoney;
 } 

/** 
* 设置已收金额
*
* @param yjsmoney 已收金额
*/
public void setYjsmoney ( UFDouble yjsmoney) {
this.yjsmoney=yjsmoney;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.zylist_sr");
  }
}