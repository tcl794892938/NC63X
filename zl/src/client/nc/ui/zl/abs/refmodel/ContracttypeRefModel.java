package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class ContracttypeRefModel extends AbstractRefTreeModel {

	public ContracttypeRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("合同类型(租赁)");
		setRefTitle("合同类型(租赁)");
		setRootName("合同类型(租赁)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"合同类型编码",
				"合同类型名称"
				});
		setHiddenFieldCode(new String[] {
		"pk_contracttype",
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
		setTableName("zl_contracttype");
		setPkFieldCode("pk_contracttype");
		setWherePart("1=1 and isnull(dr,0)=0");
		setFatherField("pk_parentid");
		setChildField("pk_contracttype");
		setRefCodeField("code");
		setRefNameField("name");
		resetFieldName();
	}

}
