package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class CustomertypeRefModel extends AbstractRefTreeModel {

	public CustomertypeRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("�ͻ�����(����)");
		setRefTitle("�ͻ�����(����)");
		setRootName("�ͻ�����(����)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"�ͻ����ͱ���",
				"�ͻ���������"
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
