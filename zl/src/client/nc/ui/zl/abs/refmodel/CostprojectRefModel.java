package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class CostprojectRefModel extends AbstractRefTreeModel {

	public CostprojectRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("�շ���Ŀ(����)");
		setRefTitle("�շ���Ŀ(����)");
		setRootName("�շ���Ŀ(����)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"�շ���Ŀ����",
				"�շ���Ŀ����"
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
