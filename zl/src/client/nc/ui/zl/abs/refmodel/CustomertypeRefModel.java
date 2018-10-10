package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class CustomertypeRefModel extends AbstractRefTreeModel {

	public CustomertypeRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("客户类型(租赁)");
		setRefTitle("客户类型(租赁)");
		setRootName("客户类型(租赁)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"客户类型编码",
				"客户类型名称"
		});
		setHiddenFieldCode(new String[] {
		"pk_customertype",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"pk_parentid",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
		});
		setTableName("zl_customertype");
		setPkFieldCode("pk_customertype");
		setWherePart("1=1 and isnull(dr,0)=0");
		setFatherField("pk_parentid");
		setChildField("pk_customertype");
		setRefCodeField("code");
		setRefNameField("name");
		resetFieldName();
	}

}
