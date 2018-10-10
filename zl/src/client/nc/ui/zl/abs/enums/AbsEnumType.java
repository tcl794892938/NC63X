package nc.ui.zl.abs.enums;

public class AbsEnumType {
	
	//合同管理-租赁方式
	/** 日/平方米*/
	public static final Integer ZLstyle_1 = 1;
	/** 日/套*/
	public static final Integer ZLstyle_2 = 2;
	/** 月/平方米*/
	public static final Integer ZLstyle_3 = 3;
	/** 月/套*/
	public static final Integer ZLstyle_4 = 4;
	/** 年/平方米*/
	public static final Integer ZLstyle_5 = 5;
	/** 年/套*/
	public static final Integer ZLstyle_6 = 6;
	
	//合同管理-合同税率
	/** 无税*/
	public static final Integer HTtaxrate0 = 0;
	/** 3%*/
	public static final Integer HTtaxrate1 = 3;
	/** 5%*/
	public static final Integer HTtaxrate2 = 5;
	/** 6%*/
	public static final Integer HTtaxrate3 = 6;
	/** 11%*/
	public static final Integer HTtaxrate4 = 11;
	/** 17%*/
	public static final Integer HTtaxrate5 = 17;
	
	//合同管理-合同状态
	/** 定租*/
	public static final Integer HTstatus1 = 1;
	/** 签租*/
	public static final Integer HTstatus2 = 2;
	/** 进场*/
	public static final Integer HTstatus3 = 3;
	/** 退租*/
	public static final Integer HTstatus4 = 4;
	
	//合同管理-付款方式
	/** 一次性付款*/
	public static final Integer PayStyle1 = 0;

	
	//收费标准-计算方式
	/** 租赁面积*/
	public static final Integer FeeScale_mj = 0;
	/** 固定金额*/
	public static final Integer FeeScale_je = 1;
	
	//收费标准-舍入方式
	/** 四舍五入取整*/
	public static final Integer FeeScale2_QZ = 0;
	/** 四舍五入保留一位*/
	public static final Integer FeeScale2_BLYW = 1;
	/** 四舍五入保留两位*/
	public static final Integer FeeScale2_BLLW = 2;
	/** 四舍五入保留三位*/
	public static final Integer FeeScale2_BLSANW = 3;
	/** 四舍五入保留四位*/
	public static final Integer FeeScale2_BLSIW = 4;
	/** 进位取整*/
	public static final Integer FeeScale2_JW = -1;
	
	//房源状态
	/** 空置*/
	public static final Integer HOUSE_KZ = 0;
	/** 自用*/
	public static final Integer HOUSE_ZY = 1;
	/** 定租*/
	public static final Integer HOUSE_DZ = 2;
	/** 已租*/
	public static final Integer HOUSE_YZ = 3;
	
	//会计期间默认值
	public static final String Period = "0001Z000000000000001";
	
	//合同传应收单的源头
	/** 保证金*/
	public static final String HT_BZJ = "zl_contract_bzj";
	/** 业务拆分*/
	public static final String HT_YWCF = "zl_contract_ywcf";
	/** 周期拆分*/
	public static final String HT_ZQCF = "zl_contract_zqfycf";
}
