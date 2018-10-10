package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class CostprojectRefModel extends AbstractRefTreeModel {

	public CostprojectRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("收费项目(租赁)");
		setRefTitle("收费项目(租赁)");
		setRootName("收费项目(租赁)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"收费项目编码",
				"收费项目名称"
		});
		setHiddenFieldCode(new String[] {
		"pk_costproject",
		"roundtype",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"pk_vid",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
		});
		setTableName("zl_costproject");
		setPkFieldCode("pk_costproject");
		setWherePart("1=1 and isnull(dr,0)=0");
		setFatherField("pk_vid");
		setChildField("pk_costproject");
		setRefCodeField("code");
		setRefNameField("name");
		resetFieldName();
	}

}
