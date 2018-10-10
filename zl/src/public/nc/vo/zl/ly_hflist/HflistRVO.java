package nc.vo.zl.ly_hflist;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HflistRVO extends SuperVO {
	/**
	 * 完成情况
	 */
	public String finishqk;
	/**
	 * 完成时间
	 */
	public UFDateTime finishtime;
	/**
	 * 回访日期
	 */
	public UFDate hfdate;
	/**
	 * 回访人员
	 */
	public String hfman;
	/**
	 * 回访满意
	 */
	public UFBoolean hfservice;
	/**
	 * 回访情况
	 */
	public String hfsituation;
	/**
	 * 上层单据主键
	 */
	public String pk_hflist;
	/**
	 * 主键
	 */
	public String pk_hflist_r;
	/**
	 * 维修服务人员
	 */
	public String serviceman;
	/**
	 * 时间戳
	 */
	public UFDateTime ts;
	/**
	 * 未完成原因
	 */
	public String unfinishyy;
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
	*行号
	*/
	public String rowno;

	public String getRowno() {
		return rowno;
	}

	public void setRowno(String rowno) {
		this.rowno = rowno;
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
	public String getFinishqk() {
		return this.finishqk;
	}

	/**
	 * 设置完成情况
	 * 
	 * @param finishqk
	 *            完成情况
	 */
	public void setFinishqk(String finishqk) {
		this.finishqk = finishqk;
	}

	/**
	 * 获取完成时间
	 * 
	 * @return 完成时间
	 */
	public UFDateTime getFinishtime() {
		return this.finishtime;
	}

	/**
	 * 设置完成时间
	 * 
	 * @param finishtime
	 *            完成时间
	 */
	public void setFinishtime(UFDateTime finishtime) {
		this.finishtime = finishtime;
	}

	/**
	 * 获取回访日期
	 * 
	 * @return 回访日期
	 */
	public UFDate getHfdate() {
		return this.hfdate;
	}

	/**
	 * 设置回访日期
	 * 
	 * @param hfdate
	 *            回访日期
	 */
	public void setHfdate(UFDate hfdate) {
		this.hfdate = hfdate;
	}

	/**
	 * 获取回访人员
	 * 
	 * @return 回访人员
	 */
	public String getHfman() {
		return this.hfman;
	}

	/**
	 * 设置回访人员
	 * 
	 * @param hfman
	 *            回访人员
	 */
	public void setHfman(String hfman) {
		this.hfman = hfman;
	}

	/**
	 * 获取回访满意
	 * 
	 * @return 回访满意
	 */
	public UFBoolean getHfservice() {
		return this.hfservice;
	}

	/**
	 * 设置回访满意
	 * 
	 * @param hfservice
	 *            回访满意
	 */
	public void setHfservice(UFBoolean hfservice) {
		this.hfservice = hfservice;
	}

	/**
	 * 获取回访情况
	 * 
	 * @return 回访情况
	 */
	public String getHfsituation() {
		return this.hfsituation;
	}

	/**
	 * 设置回访情况
	 * 
	 * @param hfsituation
	 *            回访情况
	 */
	public void setHfsituation(String hfsituation) {
		this.hfsituation = hfsituation;
	}

	/**
	 * 获取上层单据主键
	 * 
	 * @return 上层单据主键
	 */
	public String getPk_hflist() {
		return this.pk_hflist;
	}

	/**
	 * 设置上层单据主键
	 * 
	 * @param pk_hflist
	 *            上层单据主键
	 */
	public void setPk_hflist(String pk_hflist) {
		this.pk_hflist = pk_hflist;
	}

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public String getPk_hflist_r() {
		return this.pk_hflist_r;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_hflist_r
	 *            主键
	 */
	public void setPk_hflist_r(String pk_hflist_r) {
		this.pk_hflist_r = pk_hflist_r;
	}

	/**
	 * 获取维修服务人员
	 * 
	 * @return 维修服务人员
	 */
	public String getServiceman() {
		return this.serviceman;
	}

	/**
	 * 设置维修服务人员
	 * 
	 * @param serviceman
	 *            维修服务人员
	 */
	public void setServiceman(String serviceman) {
		this.serviceman = serviceman;
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
	 * 获取未完成原因
	 * 
	 * @return 未完成原因
	 */
	public String getUnfinishyy() {
		return this.unfinishyy;
	}

	/**
	 * 设置未完成原因
	 * 
	 * @param unfinishyy
	 *            未完成原因
	 */
	public void setUnfinishyy(String unfinishyy) {
		this.unfinishyy = unfinishyy;
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
		return VOMetaFactory.getInstance().getVOMeta("zl.hflist_r");
	}
}