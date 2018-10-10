package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class ProjectRefModel extends AbstractRefModel {

	public ProjectRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("项目信息(租赁)");
		setRefTitle("项目信息(租赁)");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"编码",
		"名称"
				});
		setHiddenFieldCode(new String[] {
		"pk_project",
		"projecthp",
		"narea",
		"nfloor",
		"nholds",
		"nhomeholds",
		"nshopholds",
		"nofficeholds",
		"leaserate1",
		"leaserate2",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_project");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_project");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}