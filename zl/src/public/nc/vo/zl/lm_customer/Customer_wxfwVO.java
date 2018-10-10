package nc.vo.zl.lm_customer;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class Customer_wxfwVO extends SuperVO {
	/**
	 * 报事单据
	 */
	public String bsdj;
	/**
	 * 报事内容
	 */
	public String bsnr;
	/**
	 * 楼栋号
	 */
	public String buildnum;
	/**
	 * 客户名称
	 */
	public String customername;
	/**
	 * 上层单据主键
	 */
	public String pk_customer;
	/**
	 * 主键
	 */
	public String pk_customerwxfw;
	/**
	 * 房间名称
	 */
	public String roomname;
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
	public Integer dr = 0;

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * 获取报事单据
	 * 
	 * @return 报事单据
	 */
	public String getBsdj() {
		return this.bsdj;
	}

	/**
	 * 设置报事单据
	 * 
	 * @param bsdj
	 *            报事单据
	 */
	public void setBsdj(String bsdj) {
		this.bsdj = bsdj;
	}

	/**
	 * 获取报事内容
	 * 
	 * @return 报事内容
	 */
	public String getBsnr() {
		return this.bsnr;
	}

	/**
	 * 设置报事内容
	 * 
	 * @param bsnr
	 *            报事内容
	 */
	public void setBsnr(String bsnr) {
		this.bsnr = bsnr;
	}

	/**
	 * 获取楼栋号
	 * 
	 * @return 楼栋号
	 */
	public String getBuildnum() {
		return this.buildnum;
	}

	/**
	 * 设置楼栋号
	 * 
	 * @param buildnum
	 *            楼栋号
	 */
	public void setBuildnum(String buildnum) {
		this.buildnum = buildnum;
	}

	/**
	 * 获取客户名称
	 * 
	 * @return 客户名称
	 */
	public String getCustomername() {
		return this.customername;
	}

	/**
	 * 设置客户名称
	 * 
	 * @param customername
	 *            客户名称
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}

	/**
	 * 获取上层单据主键
	 * 
	 * @return 上层单据主键
	 */
	public String getPk_customer() {
		return this.pk_customer;
	}

	/**
	 * 设置上层单据主键
	 * 
	 * @param pk_customer
	 *            上层单据主键
	 */
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public String getPk_customerwxfw() {
		return this.pk_customerwxfw;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_customerwxfw
	 *            主键
	 */
	public void setPk_customerwxfw(String pk_customerwxfw) {
		this.pk_customerwxfw = pk_customerwxfw;
	}

	/**
	 * 获取房间名称
	 * 
	 * @return 房间名称
	 */
	public String getRoomname() {
		return this.roomname;
	}

	/**
	 * 设置房间名称
	 * 
	 * @param roomname
	 *            房间名称
	 */
	public void setRoomname(String roomname) {
		this.roomname = roomname;
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
		return VOMetaFactory.getInstance().getVOMeta("zl.customer_wxfw");
	}
}