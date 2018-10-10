package nc.vo.zl.ld_feescale;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2017-12-20
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class FeescaleVO extends SuperVO {
	
/**
*项目主键
*/
public String pk_feescale;
/**
*标准编码
*/
public String code;
/**
*标准名称
*/
public String name;
/**
*收费项目
*/
public String chargeitem;
/**
*计算方式
*/
public Integer accountform;
/**
*计算数额
*/
public UFDouble accountscal;
/**
*舍入方式
*/
public Integer roundform;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*集团
*/
public String pk_group;
/**
*制单日期
*/
public UFDate dbilldate;
/**
*创建人
*/
public String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
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
*时间戳
*/
public UFDateTime ts;
    
public Integer DR=0;
    
public Integer getDR() {
	return DR;
}

public void setDR(Integer dR) {
	DR = dR;
}

/**
* 属性 pk_feescale的Getter方法.属性名：项目主键
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getPk_feescale() {
return this.pk_feescale;
} 

/**
* 属性pk_feescale的Setter方法.属性名：项目主键
* 创建日期:2017-12-20
* @param newPk_feescale java.lang.String
*/
public void setPk_feescale ( String pk_feescale) {
this.pk_feescale=pk_feescale;
} 
 
/**
* 属性 code的Getter方法.属性名：标准编码
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* 属性code的Setter方法.属性名：标准编码
* 创建日期:2017-12-20
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* 属性 name的Getter方法.属性名：标准名称
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* 属性name的Setter方法.属性名：标准名称
* 创建日期:2017-12-20
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* 属性 chargeitem的Getter方法.属性名：收费项目
*  创建日期:2017-12-20
* @return nc.vo.sm.UserVO
*/
public String getChargeitem() {
return this.chargeitem;
} 

/**
* 属性chargeitem的Setter方法.属性名：收费项目
* 创建日期:2017-12-20
* @param newChargeitem nc.vo.sm.UserVO
*/
public void setChargeitem ( String chargeitem) {
this.chargeitem=chargeitem;
} 
 
/**
* 属性 accountform的Getter方法.属性名：计算方式
*  创建日期:2017-12-20
* @return nc.vo.zl.ld_feescale.AccountType
*/
public Integer getAccountform() {
return this.accountform;
} 

/**
* 属性accountform的Setter方法.属性名：计算方式
* 创建日期:2017-12-20
* @param newAccountform nc.vo.zl.ld_feescale.AccountType
*/
public void setAccountform ( Integer accountform) {
this.accountform=accountform;
} 
 
/**
* 属性 accountscal的Getter方法.属性名：计算数额
*  创建日期:2017-12-20
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getAccountscal() {
return this.accountscal;
} 

/**
* 属性accountscal的Setter方法.属性名：计算数额
* 创建日期:2017-12-20
* @param newAccountscal nc.vo.pub.lang.UFDouble
*/
public void setAccountscal ( UFDouble accountscal) {
this.accountscal=accountscal;
} 
 
/**
* 属性 roundform的Getter方法.属性名：舍入方式
*  创建日期:2017-12-20
* @return nc.vo.zl.ld_feescale.RoundType
*/
public Integer getRoundform() {
return this.roundform;
} 

/**
* 属性roundform的Setter方法.属性名：舍入方式
* 创建日期:2017-12-20
* @param newRoundform nc.vo.zl.ld_feescale.RoundType
*/
public void setRoundform ( Integer roundform) {
this.roundform=roundform;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-12-20
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-12-20
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织版本
*  创建日期:2017-12-20
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织版本
* 创建日期:2017-12-20
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-12-20
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-12-20
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：制单日期
*  创建日期:2017-12-20
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：制单日期
* 创建日期:2017-12-20
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2017-12-20
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2017-12-20
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2017-12-20
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2017-12-20
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2017-12-20
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2017-12-20
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2017-12-20
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2017-12-20
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 vdef1的Getter方法.属性名：自定义项1
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getVdef1() {
return this.vdef1;
} 

/**
* 属性vdef1的Setter方法.属性名：自定义项1
* 创建日期:2017-12-20
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* 属性 vdef2的Getter方法.属性名：自定义项2
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getVdef2() {
return this.vdef2;
} 

/**
* 属性vdef2的Setter方法.属性名：自定义项2
* 创建日期:2017-12-20
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* 属性 vdef3的Getter方法.属性名：自定义项3
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getVdef3() {
return this.vdef3;
} 

/**
* 属性vdef3的Setter方法.属性名：自定义项3
* 创建日期:2017-12-20
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* 属性 vdef4的Getter方法.属性名：自定义项4
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getVdef4() {
return this.vdef4;
} 

/**
* 属性vdef4的Setter方法.属性名：自定义项4
* 创建日期:2017-12-20
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* 属性 vdef5的Getter方法.属性名：自定义项5
*  创建日期:2017-12-20
* @return java.lang.String
*/
public String getVdef5() {
return this.vdef5;
} 

/**
* 属性vdef5的Setter方法.属性名：自定义项5
* 创建日期:2017-12-20
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-12-20
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-12-20
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.feescale");
    }
   }
    