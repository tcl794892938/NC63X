package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class CustomerRefModel extends AbstractRefModel {

	public CustomerRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("客户信息中心(租赁)");
		setRefTitle("客户信息中心(租赁)");
		setFieldCode(new String[] {
		"customercode",
		"customername"
				});
		setFieldName(new String[] {
		"客户编码",
		"客户名称"
				});
		setHiddenFieldCode(new String[] {
		"pk_customer",
		"customerlxfs",
		"customeraddress",
		"sfzh",
		"yyzz",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5",
		"pk_customer",
		"pk_customer",
		"pk_customer",
		"pk_customer",
		"pk_customer"
			});
		setPkFieldCode("pk_customer");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_customer");
		setRefCodeField("customercode");
		setRefNameField("customername");
	
	}
	
}