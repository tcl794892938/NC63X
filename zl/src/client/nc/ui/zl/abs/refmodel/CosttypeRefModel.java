package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class CosttypeRefModel extends AbstractRefTreeModel {

	public CosttypeRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("�շѴ���(����)");
		setRefTitle("�շѴ���");
		setRootName("�շѴ���");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"�շѴ������",
				"�շѴ�������"
		});
		setHiddenFieldCode(new String[] {
		"pk_costtype",
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
		setTableName("zl_costtype");
		setPkFieldCode("pk_costtype");
		setWherePart("1=1 and isnull(dr,0)=0");
		setFatherField("pk_vid");
		setChildField("pk_costtype");
		setRefCodeField("code");
		setRefNameField("name");
		resetFieldName();
	}

}
