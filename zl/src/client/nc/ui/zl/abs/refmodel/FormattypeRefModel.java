package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefTreeModel;

public class FormattypeRefModel extends AbstractRefTreeModel {

	public FormattypeRefModel() {
		super();
		init();
	}
	
	private void init(){
		setRefNodeName("ҵ̬����(����)");
		setRefTitle("ҵ̬����(����)");
		setRootName("ҵ̬����(����)");
		setFieldCode(new String[] {
		"code",
		"name"
		});
		setFieldName(new String[] {
				"ҵ̬����",
				"ҵ̬����"
				});
		setHiddenFieldCode(new String[] {
		"pk_formattype",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"upname",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
		});
		setTableName("zl_formattype");
		setPkFieldCode("pk_formattype");
		setWherePart("1=1 and isnull(dr,0)=0");
		setFatherField("upname");
		setChildField("pk_formattype");
		setRefCodeField("code");
		setRefNameField("name");
		resetFieldName();
	}

}
