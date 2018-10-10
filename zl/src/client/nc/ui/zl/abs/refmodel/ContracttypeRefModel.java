package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class ContracttypeRefModel extends AbstractRefTreeModel {

	public ContracttypeRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("��ͬ����(����)");
		setRefTitle("��ͬ����(����)");
		setRootName("��ͬ����(����)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"��ͬ���ͱ���",
				"��ͬ��������"
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
