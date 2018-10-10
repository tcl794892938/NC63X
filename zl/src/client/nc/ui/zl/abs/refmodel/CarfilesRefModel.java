package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class CarfilesRefModel extends AbstractRefModel {

	public CarfilesRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("��������(����)");
		setRefTitle("��������(����)");
		setFieldCode(new String[] {
		"listid",
		"carnumber"
				});
		setFieldName(new String[] {
		"���ݱ��",
		"���ƺ�"
				});
		setHiddenFieldCode(new String[] {
		"pk_carfiles",
		"liststate",
		"billtypecode",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"checktime",
		"checkpy",
		"vdef1",
		"vdef2",
		"vde3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_carfiles");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_carfiles");
		setRefCodeField("listid");
		setRefNameField("carnumber");
	
	}
	
}