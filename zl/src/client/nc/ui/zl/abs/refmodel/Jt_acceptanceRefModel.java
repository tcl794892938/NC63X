package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class Jt_acceptanceRefModel extends AbstractRefModel {

	public Jt_acceptanceRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("���˳�������Ŀ(����)");
		setRefTitle("���˳�������Ŀ(����)");
		setFieldCode(new String[] {
		"code",
		"name"
				});
		setFieldName(new String[] {
		"��������",
		"��������"
				});
		setHiddenFieldCode(new String[] {
	    "pk_acceptance",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_acceptance");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_jt_acceptance");
		setRefCodeField("code");
		setRefNameField("name");
	
	}
	
}