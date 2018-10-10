package nc.vo.zl.ly_hflist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HflistVO extends SuperVO {
	/**
	 * 接单部门
	 */
	public String acceptbm;
	/**
	 * 接单人
	 */
	public String acceptman;
	/**
	 * 单据类型编码
	 */
	public String billtypecode;
	/**
	 * 报修内容
	 */
	public String bxcontent;
	/**
	 * 报修日期
	 */
	public UFDate bxdate;
	/**
	 * 审核人
	 */
	public String checkman;
	/**
	 * 审核批语
	 */
	public String checkpy;
	/**
	 * 审核时间
	 */
	public UFDateTime checktime;
	/**
	 * 来源单据类型
	 */
	public String clisttype;
	/**
	 * 创建时间
	 */
	public UFDateTime creationtime;
	/**
	 * 创建人
	 */
	public String creator;
	/**
	 * 制单日期
	 */
	public UFDate dbilldate = AppContext.getInstance().getBusiDate();
	/**
	 * 房产名称
	 */
	public String fcname;
	/**
	 * 客户名称
	 */
	public String khname;
	/**
	 * 单据日期
	 */
	public UFDate listdate;
	/**
	 * 单据编号
	 */
	public String listid;
	/**
	 * 单据状态
	 */
	public Integer liststate;
	/**
	 * 联系电话
	 */
	public String lxphone;
	/**
	 * 修改时间
	 */
	public UFDateTime modifiedtime;
	/**
	 * 修改人
	 */
	public String modifier;
	/**
	 * 单据类型
	 */
	public String pk_billtype;
	/**
	 * 来源单据id
	 */
	public String pk_clist;
	/**
	 * 集团
	 */
	public String pk_group;
	/**
	 * 主键
	 */
	public String pk_hflist;
	/**
	 * 组织
	 */
	public String pk_org;
	/**
	 * 组织版本
	 */
	public String pk_org_v;
	/**
	 * 时间戳
	 */
	public UFDateTime ts;
	/**
	 * 自定义项1
	 */
	public String vdef1;
	/**
	 * 自定义项2
	 */
	public String vdef2;
	/**
	 * 自定义项3
	 */
	public String vdef3;
	/**
	 * 自定义项4
	 */
	public String vdef4;
	/**
	 * 自定义项5
	 */
	public String vdef5;
	/**
	 * 是否删除
	 */
	public Integer dr = 0;
	/**
	*项目信息
	*/
	public String pk_project;
	/**
	*楼栋
	*/
	public String pk_building;

	public String getPk_project() {
		return pk_project;
	}

	public void setPk_project(String pk_project) {
		this.pk_project = pk_project;
	}

	public String getPk_building() {
		return pk_building;
	}

	public void setPk_building(String pk_building) {
		this.pk_building = pk_building;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * 获取接单部门
	 * 
	 * @return 接单部门
	 */
	public String getAcceptbm() {
		return this.acceptbm;
	}

	/**
	 * 设置接单部门
	 * 
	 * @param acceptbm
	 *            接单部门
	 */
	public void setAcceptbm(String acceptbm) {
		this.acceptbm = acceptbm;
	}

	/**
	 * 获取接单人
	 * 
	 * @return 接单人
	 */
	public String getAcceptman() {
		return this.acceptman;
	}

	/**
	 * 设置接单人
	 * 
	 * @param acceptman
	 *            接单人
	 */
	public void setAcceptman(String acceptman) {
		this.acceptman = acceptman;
	}

	/**
	 * 获取单据类型编码
	 * 
	 * @return 单据类型编码
	 */
	public String getBilltypecode() {
		return this.billtypecode;
	}

	/**
	 * 设置单据类型编码
	 * 
	 * @param billtypecode
	 *            单据类型编码
	 */
	public void setBilltypecode(String billtypecode) {
		this.billtypecode = billtypecode;
	}

	/**
	 * 获取报修内容
	 * 
	 * @return 报修内容
	 */
	public String getBxcontent() {
		return this.bxcontent;
	}

	/**
	 * 设置报修内容
	 * 
	 * @param bxcontent
	 *            报修内容
	 */
	public void setBxcontent(String bxcontent) {
		this.bxcontent = bxcontent;
	}

	/**
	 * 获取报修日期
	 * 
	 * @return 报修日期
	 */
	public UFDate getBxdate() {
		return this.bxdate;
	}

	/**
	 * 设置报修日期
	 * 
	 * @param bxdate
	 *            报修日期
	 */
	public void setBxdate(UFDate bxdate) {
		this.bxdate = bxdate;
	}

	/**
	 * 获取审核人
	 * 
	 * @return 审核人
	 */
	public String getCheckman() {
		return this.checkman;
	}

	/**
	 * 设置审核人
	 * 
	 * @param checkman
	 *            审核人
	 */
	public void setCheckman(String checkman) {
		this.checkman = checkman;
	}

	/**
	 * 获取审核批语
	 * 
	 * @return 审核批语
	 */
	public String getCheckpy() {
		return this.checkpy;
	}

	/**
	 * 设置审核批语
	 * 
	 * @param checkpy
	 *            审核批语
	 */
	public void setCheckpy(String checkpy) {
		this.checkpy = checkpy;
	}

	/**
	 * 获取审核时间
	 * 
	 * @return 审核时间
	 */
	public UFDateTime getChecktime() {
		return this.checktime;
	}

	/**
	 * 设置审核时间
	 * 
	 * @param checktime
	 *            审核时间
	 */
	public void setChecktime(UFDateTime checktime) {
		this.checktime = checktime;
	}

	/**
	 * 获取来源单据类型
	 * 
	 * @return 来源单据类型
	 */
	public String getClisttype() {
		return this.clisttype;
	}

	/**
	 * 设置来源单据类型
	 * 
	 * @param clisttype
	 *            来源单据类型
	 */
	public void setClisttype(String clisttype) {
		this.clisttype = clisttype;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return 创建时间
	 */
	public UFDateTime getCreationtime() {
		return this.creationtime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param creationtime
	 *            创建时间
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * 获取创建人
	 * 
	 * @return 创建人
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 设置创建人
	 * 
	 * @param creator
	 *            创建人
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 获取制单日期
	 * 
	 * @return 制单日期
	 */
	public UFDate getDbilldate() {
		return this.dbilldate;
	}

	/**
	 * 设置制单日期
	 * 
	 * @param dbilldate
	 *            制单日期
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	/**
	 * 获取房产名称
	 * 
	 * @return 房产名称
	 */
	public String getFcname() {
		return this.fcname;
	}

	/**
	 * 设置房产名称
	 * 
	 * @param fcname
	 *            房产名称
	 */
	public void setFcname(String fcname) {
		this.fcname = fcname;
	}

	/**
	 * 获取客户名称
	 * 
	 * @return 客户名称
	 */
	public String getKhname() {
		return this.khname;
	}

	/**
	 * 设置客户名称
	 * 
	 * @param khname
	 *            客户名称
	 */
	public void setKhname(String khname) {
		this.khname = khname;
	}

	/**
	 * 获取单据日期
	 * 
	 * @return 单据日期
	 */
	public UFDate getListdate() {
		return this.listdate;
	}

	/**
	 * 设置单据日期
	 * 
	 * @param listdate
	 *            单据日期
	 */
	public void setListdate(UFDate listdate) {
		this.listdate = listdate;
	}

	/**
	 * 获取单据编号
	 * 
	 * @return 单据编号
	 */
	public String getListid() {
		return this.listid;
	}

	/**
	 * 设置单据编号
	 * 
	 * @param listid
	 *            单据编号
	 */
	public void setListid(String listid) {
		this.listid = listid;
	}

	/**
	 * 获取单据状态
	 * 
	 * @return 单据状态
	 * @see String
	 */
	public Integer getListstate() {
		return this.liststate;
	}

	/**
	 * 设置单据状态
	 * 
	 * @param liststate
	 *            单据状态
	 * @see String
	 */
	public void setListstate(Integer liststate) {
		this.liststate = liststate;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */
	public String getLxphone() {
		return this.lxphone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param lxphone
	 *            联系电话
	 */
	public void setLxphone(String lxphone) {
		this.lxphone = lxphone;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return 修改时间
	 */
	public UFDateTime getModifiedtime() {
		return this.modifiedtime;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modifiedtime
	 *            修改时间
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * 获取修改人
	 * 
	 * @return 修改人
	 */
	public String getModifier() {
		return this.modifier;
	}

	/**
	 * 设置修改人
	 * 
	 * @param modifier
	 *            修改人
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * 获取单据类型
	 * 
	 * @return 单据类型
	 */
	public String getPk_billtype() {
		return this.pk_billtype;
	}

	/**
	 * 设置单据类型
	 * 
	 * @param pk_billtype
	 *            单据类型
	 */
	public void setPk_billtype(String pk_billtype) {
		this.pk_billtype = pk_billtype;
	}

	/**
	 * 获取来源单据id
	 * 
	 * @return 来源单据id
	 */
	public String getPk_clist() {
		return this.pk_clist;
	}

	/**
	 * 设置来源单据id
	 * 
	 * @param pk_clist
	 *            来源单据id
	 */
	public void setPk_clist(String pk_clist) {
		this.pk_clist = pk_clist;
	}

	/**
	 * 获取集团
	 * 
	 * @return 集团
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * 设置集团
	 * 
	 * @param pk_group
	 *            集团
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public String getPk_hflist() {
		return this.pk_hflist;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_hflist
	 *            主键
	 */
	public void setPk_hflist(String pk_hflist) {
		this.pk_hflist = pk_hflist;
	}

	/**
	 * 获取组织
	 * 
	 * @return 组织
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * 设置组织
	 * 
	 * @param pk_org
	 *            组织
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * 获取组织版本
	 * 
	 * @return 组织版本
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * 设置组织版本
	 * 
	 * @param pk_org_v
	 *            组织版本
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 设置时间戳
	 * 
	 * @param ts
	 *            时间戳
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	/**
	 * 获取自定义项1
	 * 
	 * @return 自定义项1
	 */
	public String getVdef1() {
		return this.vdef1;
	}

	/**
	 * 设置自定义项1
	 * 
	 * @param vdef1
	 *            自定义项1
	 */
	public void setVdef1(String vdef1) {
		this.vdef1 = vdef1;
	}

	/**
	 * 获取自定义项2
	 * 
	 * @return 自定义项2
	 */
	public String getVdef2() {
		return this.vdef2;
	}

	/**
	 * 设置自定义项2
	 * 
	 * @param vdef2
	 *            自定义项2
	 */
	public void setVdef2(String vdef2) {
		this.vdef2 = vdef2;
	}

	/**
	 * 获取自定义项3
	 * 
	 * @return 自定义项3
	 */
	public String getVdef3() {
		return this.vdef3;
	}

	/**
	 * 设置自定义项3
	 * 
	 * @param vdef3
	 *            自定义项3
	 */
	public void setVdef3(String vdef3) {
		this.vdef3 = vdef3;
	}

	/**
	 * 获取自定义项4
	 * 
	 * @return 自定义项4
	 */
	public String getVdef4() {
		return this.vdef4;
	}

	/**
	 * 设置自定义项4
	 * 
	 * @param vdef4
	 *            自定义项4
	 */
	public void setVdef4(String vdef4) {
		this.vdef4 = vdef4;
	}

	/**
	 * 获取自定义项5
	 * 
	 * @return 自定义项5
	 */
	public String getVdef5() {
		return this.vdef5;
	}

	/**
	 * 设置自定义项5
	 * 
	 * @param vdef5
	 *            自定义项5
	 */
	public void setVdef5(String vdef5) {
		this.vdef5 = vdef5;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("zl.hflist");
	}
}