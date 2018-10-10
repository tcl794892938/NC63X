package nc.vo.zl.base_project;

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
 *  创建日期:2017-12-15
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class ProjectVO extends SuperVO {
	
/**
*项目主键
*/
public String pk_project;
/**
*编码
*/
public String code;
/**
*名称
*/
public String name;
/**
*项目辅助
*/
public String projecthp;
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
*总面积
*/
public UFDouble narea;
/**
*总楼栋
*/
public Integer nfloor;
/**
*总户数
*/
public Integer nholds;
/**
*住宅户数
*/
public Integer nhomeholds;
/**
*商铺户数
*/
public Integer nshopholds;
/**
*写字楼户数
*/
public Integer nofficeholds;
/**
 * 车位户数
 */
public Integer ncarholds;
/**
*出租率1
*/
public UFDouble leaserate1;
/**
*出租率2
*/
public UFDouble leaserate2;
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

public Integer dr;



public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/**
* 属性 pk_project的Getter方法.属性名：项目主键
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getPk_project() {
return this.pk_project;
} 

/**
* 属性pk_project的Setter方法.属性名：项目主键
* 创建日期:2017-12-15
* @param newPk_project java.lang.String
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
} 
 
/**
* 属性 code的Getter方法.属性名：编码
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* 属性code的Setter方法.属性名：编码
* 创建日期:2017-12-15
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* 属性 name的Getter方法.属性名：名称
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* 属性name的Setter方法.属性名：名称
* 创建日期:2017-12-15
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* 属性 projecthp的Getter方法.属性名：项目辅助
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getProjecthp() {
return this.projecthp;
} 

/**
* 属性projecthp的Setter方法.属性名：项目辅助
* 创建日期:2017-12-15
* @param newProjecthp java.lang.String
*/
public void setProjecthp ( String projecthp) {
this.projecthp=projecthp;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-12-15
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-12-15
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织版本
*  创建日期:2017-12-15
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织版本
* 创建日期:2017-12-15
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-12-15
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-12-15
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 narea的Getter方法.属性名：总面积
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNarea() {
return this.narea;
} 

/**
* 属性narea的Setter方法.属性名：总面积
* 创建日期:2017-12-15
* @param newNarea nc.vo.pub.lang.UFDouble
*/
public void setNarea ( UFDouble narea) {
this.narea=narea;
} 
 
/**
* 属性 nfloor的Getter方法.属性名：总楼栋
*  创建日期:2017-12-15
* @return java.lang.Integer
*/
public Integer getNfloor() {
return this.nfloor;
} 

/**
* 属性nfloor的Setter方法.属性名：总楼栋
* 创建日期:2017-12-15
* @param newNfloor java.lang.Integer
*/
public void setNfloor ( Integer nfloor) {
this.nfloor=nfloor;
} 
 
/**
* 属性 nholds的Getter方法.属性名：总户数
*  创建日期:2017-12-15
* @return java.lang.Integer
*/
public Integer getNholds() {
return this.nholds;
} 

/**
* 属性nholds的Setter方法.属性名：总户数
* 创建日期:2017-12-15
* @param newNholds java.lang.Integer
*/
public void setNholds ( Integer nholds) {
this.nholds=nholds;
} 
 
/**
* 属性 nhomeholds的Getter方法.属性名：住宅户数
*  创建日期:2017-12-15
* @return java.lang.Integer
*/
public Integer getNhomeholds() {
return this.nhomeholds;
} 

/**
* 属性nhomeholds的Setter方法.属性名：住宅户数
* 创建日期:2017-12-15
* @param newNhomeholds java.lang.Integer
*/
public void setNhomeholds ( Integer nhomeholds) {
this.nhomeholds=nhomeholds;
} 
 
/**
* 属性 nshopholds的Getter方法.属性名：商铺户数
*  创建日期:2017-12-15
* @return java.lang.Integer
*/
public Integer getNshopholds() {
return this.nshopholds;
} 

/**
* 属性nshopholds的Setter方法.属性名：商铺户数
* 创建日期:2017-12-15
* @param newNshopholds java.lang.Integer
*/
public void setNshopholds ( Integer nshopholds) {
this.nshopholds=nshopholds;
} 
 
/**
* 属性 nofficeholds的Getter方法.属性名：写字楼户数
*  创建日期:2017-12-15
* @return java.lang.Integer
*/
public Integer getNofficeholds() {
return this.nofficeholds;
} 

/**
* 属性nofficeholds的Setter方法.属性名：写字楼户数
* 创建日期:2017-12-15
* @param newNofficeholds java.lang.Integer
*/
public void setNofficeholds ( Integer nofficeholds) {
this.nofficeholds=nofficeholds;
} 
 
/**
* 属性 leaserate1的Getter方法.属性名：出租率1
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLeaserate1() {
return this.leaserate1;
} 

/**
* 属性leaserate1的Setter方法.属性名：出租率1
* 创建日期:2017-12-15
* @param newLeaserate1 nc.vo.pub.lang.UFDouble
*/
public void setLeaserate1 ( UFDouble leaserate1) {
this.leaserate1=leaserate1;
} 
 
/**
* 属性 leaserate2的Getter方法.属性名：出租率2
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLeaserate2() {
return this.leaserate2;
} 

/**
* 属性leaserate2的Setter方法.属性名：出租率2
* 创建日期:2017-12-15
* @param newLeaserate2 nc.vo.pub.lang.UFDouble
*/
public void setLeaserate2 ( UFDouble leaserate2) {
this.leaserate2=leaserate2;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：制单日期
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：制单日期
* 创建日期:2017-12-15
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2017-12-15
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2017-12-15
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2017-12-15
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2017-12-15
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2017-12-15
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2017-12-15
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 vdef1的Getter方法.属性名：自定义项1
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getVdef1() {
return this.vdef1;
} 

/**
* 属性vdef1的Setter方法.属性名：自定义项1
* 创建日期:2017-12-15
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* 属性 vdef2的Getter方法.属性名：自定义项2
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getVdef2() {
return this.vdef2;
} 

/**
* 属性vdef2的Setter方法.属性名：自定义项2
* 创建日期:2017-12-15
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* 属性 vdef3的Getter方法.属性名：自定义项3
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getVdef3() {
return this.vdef3;
} 

/**
* 属性vdef3的Setter方法.属性名：自定义项3
* 创建日期:2017-12-15
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* 属性 vdef4的Getter方法.属性名：自定义项4
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getVdef4() {
return this.vdef4;
} 

/**
* 属性vdef4的Setter方法.属性名：自定义项4
* 创建日期:2017-12-15
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* 属性 vdef5的Getter方法.属性名：自定义项5
*  创建日期:2017-12-15
* @return java.lang.String
*/
public String getVdef5() {
return this.vdef5;
} 

/**
* 属性vdef5的Setter方法.属性名：自定义项5
* 创建日期:2017-12-15
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-12-15
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-12-15
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 

/**
 * 属性ncarholds的Getter方法.属性名：车位户数
 * 创建时间:2018-01-09
 * @return java.lang.Integer
 */
public Integer getNcarholds() {
	return ncarholds;
}
/**
* 属性ncarholds的Setter方法.属性名：车位户数
* 创建日期:2018-01-09
* @param newNcarholds java.lang.Integer
*/
public void setNcarholds(Integer ncarholds) {
	this.ncarholds = ncarholds;
}

	@Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.project");
    }
   }
    