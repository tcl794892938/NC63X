package nc.ui.zl.abs.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class HousesourceRefModel extends AbstractRefModel {

	public HousesourceRefModel() {
		super();
		init();
	}
	
	private void init(){
	
		setRefNodeName("��Դ��Ϣ(����)");
		setRefTitle("��Դ��Ϣ(����)");
		setFieldCode(new String[] {
		"estatecode",
		"estatename"
				});
		setFieldName(new String[] {
		"�������",
		"��������"
				});
		setHiddenFieldCode(new String[] {
		"pk_housesource",
		"unit",
		"floorn",
		"roomnumber",
		"buildarea",
		"innerarea",
		"housestate",
		"dbilldate",
		"creationtime",
		"modifiedtime",
		"vdef1",
		"vdef2",
		"vdef3",
		"vdef4",
		"vdef5"
			});
		setPkFieldCode("pk_housesource");
		setWherePart("1=1 and isnull(dr,0)=0");
		setTableName("zl_housesource");
		setRefCodeField("estatecode");
		setRefNameField("estatename");
	
	}
	
}