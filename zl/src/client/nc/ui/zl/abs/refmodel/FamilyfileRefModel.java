package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class FamilyfileRefModel extends AbstractRefModel {

	public FamilyfileRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("户型档案(租赁)");
		setRefTitle("户型档案(租赁)");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"户型编码",
		"户型名称"
				});
		setHiddenFieldCode(new String[] {
		"pk_familyfile",
		"builtuparea",
		"innerarea",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_familyfile");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_familyfile");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}