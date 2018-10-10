package nc.vo.zl.ly_businesssource;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BusinessBVO extends SuperVO {
	/**
	 * 是否删除
	 */
	public Integer dr=0;
	/**
	 * 上层单据主键
	 */
	public String pk_bussiness;
	/**
	 * 主键
	 */
	public String pk_bussiness_b;
	/**
	 * 项目编码
	 */
	public String procode;
	/**
	 * 项目名称
	 */
	public String proname;
	/**
	 * 备注
	 */
	public String remarks;
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
	 * 获取上层单据主键
	 * 
	 * @return 上层单据主键
	 */
	public String getPk_bussiness() {
		return this.pk_bussiness;
	}

	/**
	 * 设置上层单据主键
	 * 
	 * @param pk_bussiness
	 *            上层单据主键
	 */
	public void setPk_bussiness(String pk_bussiness) {
		this.pk_bussiness = pk_bussiness;
	}

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public String getPk_bussiness_b() {
		return this.pk_bussiness_b;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_bussiness_b
	 *            主键
	 */
	public void setPk_bussiness_b(String pk_bussiness_b) {
		this.pk_bussiness_b = pk_bussiness_b;
	}

	/**
	 * 获取项目编码
	 * 
	 * @return 项目编码
	 */
	public String getProcode() {
		return this.procode;
	}

	/**
	 * 设置项目编码
	 * 
	 * @param procode
	 *            项目编码
	 */
	public void setProcode(String procode) {
		this.procode = procode;
	}

	/**
	 * 获取项目名称
	 * 
	 * @return 项目名称
	 */
	public String getProname() {
		return this.proname;
	}

	/**
	 * 设置项目名称
	 * 
	 * @param proname
	 *            项目名称
	 */
	public void setProname(String proname) {
		this.proname = proname;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * 设置备注
	 * 
	 * @param remarks
	 *            备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("zl.business_b");
	}
}