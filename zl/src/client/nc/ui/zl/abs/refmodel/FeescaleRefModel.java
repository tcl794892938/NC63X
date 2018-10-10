package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class FeescaleRefModel extends AbstractRefModel {

	public FeescaleRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("�շѱ�׼(����)");
		setRefTitle("�շѱ�׼(����)");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"��׼����",
		"��׼����"
				});
		setHiddenFieldCode(new String[] {
		"pk_feescale",
		"accountform",
		"accountscal",
		"roundform",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_feescale");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_feescale");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}