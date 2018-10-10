package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class BuildingfileRefModel extends AbstractRefModel {

	public BuildingfileRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("¥������(����)");
		setRefTitle("¥������(����)");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"¥������",
		"¥������"
				});
		setHiddenFieldCode(new String[] {
		"pk_buildingfile",
		"nunit",
		"nbuilding",
		"nproperty",
		"builtuparea",
		"innerarea",
		"isbuild",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_buildingfile");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_buildingfile");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}