package nc.vo.zl.cwf_gather;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class GatherBVO extends SuperVO {
/**
*费用开始日期
*/
public UFDate begindate;
/**
*会计年月
*/
public String caccountperiod;
/**
*费用截止日期
*/
public UFDate enddate;
/**
*可冲抵金额
*/
public UFDouble nkcdmny;
/**
*无税金额
*/
public UFDouble nnotaxmoney;
/**
 * 税额
 */
public UFDouble ntaxmny;
/**
*应收金额
*/
public UFDouble nrecmoney;
/**
*已收金额
*/
public UFDouble nysmny;
/**
*本次收款金额
*/
public UFDouble nskmny;
/**
*本次收款无税金额
*/
public UFDouble nsknotaxmny;
/**
*本次收款税额
*/
public UFDouble nsktaxmny;
/**
*会计科目
*/
public String pk_account;
/**
*楼栋
*/
public String pk_building;
/**
*收费项目
*/
public String pk_costproject;
/**
*上层单据主键
*/
public String pk_gather;
/**
*子表主键
*/
public String pk_gather_b;
/**
*房产名称
*/
public String pk_house;
/**
 * 车牌号
 */
public String pk_carno;
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

/**
 * 来源单据id
 */
public String vsrcid;
/**
 * 来源单据类型
 */
public String vsrctype;
/**
 * 业务单据主键
 * @return
 */
public String firstpk;
/**
 * 业务单据类型
 * @return
 */
public String firstbilltype;
/**
 * 税率
 * @return
 */
public UFDouble ntaxrate;

public UFDouble getNtaxrate() {
	return ntaxrate;
}

public void setNtaxrate(UFDouble ntaxrate) {
	this.ntaxrate = ntaxrate;
}

public String getFirstpk() {
	return firstpk;
}

public void setFirstpk(String firstpk) {
	this.firstpk = firstpk;
}



public String getFirstbilltype() {
	return firstbilltype;
}

public void setFirstbilltype(String firstbilltype) {
	this.firstbilltype = firstbilltype;
}

public UFDouble getNtaxmny() {
	return ntaxmny;
}

public void setNtaxmny(UFDouble ntaxmny) {
	this.ntaxmny = ntaxmny;
}

public String getPk_carno() {
	return pk_carno;
}

public void setPk_carno(String pk_carno) {
	this.pk_carno = pk_carno;
}

public UFDouble getNysmny() {
	return nysmny;
}

public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}

public String getVsrcid() {
	return vsrcid;
}

public void setVsrcid(String vsrcid) {
	this.vsrcid = vsrcid;
}

public String getVsrctype() {
	return vsrctype;
}

public void setVsrctype(String vsrctype) {
	this.vsrctype = vsrctype;
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
* 获取可冲抵金额
*
* @return 可冲抵金额
*/
public UFDouble getNkcdmny () {
return this.nkcdmny;
 } 

/** 
* 设置可冲抵金额
*
* @param nkcdmny 可冲抵金额
*/
public void setNkcdmny ( UFDouble nkcdmny) {
this.nkcdmny=nkcdmny;
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
* 获取本次收款金额
*
* @return 本次收款金额
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* 设置本次收款金额
*
* @param nskmny 本次收款金额
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 

/** 
* 获取本次收款无税金额
*
* @return 本次收款无税金额
*/
public UFDouble getNsknotaxmny () {
return this.nsknotaxmny;
 } 

/** 
* 设置本次收款无税金额
*
* @param nsknotaxmny 本次收款无税金额
*/
public void setNsknotaxmny ( UFDouble nsknotaxmny) {
this.nsknotaxmny=nsknotaxmny;
 } 

/** 
* 获取本次收款税额
*
* @return 本次收款税额
*/
public UFDouble getNsktaxmny () {
return this.nsktaxmny;
 } 

/** 
* 设置本次收款税额
*
* @param nsktaxmny 本次收款税额
*/
public void setNsktaxmny ( UFDouble nsktaxmny) {
this.nsktaxmny=nsktaxmny;
 } 

/** 
* 获取会计科目
*
* @return 会计科目
*/
public String getPk_account () {
return this.pk_account;
 } 

/** 
* 设置会计科目
*
* @param pk_account 会计科目
*/
public void setPk_account ( String pk_account) {
this.pk_account=pk_account;
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
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_gather () {
return this.pk_gather;
 } 

/** 
* 设置上层单据主键
*
* @param pk_gather 上层单据主键
*/
public void setPk_gather ( String pk_gather) {
this.pk_gather=pk_gather;
 } 

/** 
* 获取子表主键
*
* @return 子表主键
*/
public String getPk_gather_b () {
return this.pk_gather_b;
 } 

/** 
* 设置子表主键
*
* @param pk_gather_b 子表主键
*/
public void setPk_gather_b ( String pk_gather_b) {
this.pk_gather_b=pk_gather_b;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.gather_b");
  }
}