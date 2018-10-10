package nc.vo.zl.base_project;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2017-12-15
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class ProjectVO extends SuperVO {
	
/**
*��Ŀ����
*/
public String pk_project;
/**
*����
*/
public String code;
/**
*����
*/
public String name;
/**
*��Ŀ����
*/
public String projecthp;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*����
*/
public String pk_group;
/**
*�����
*/
public UFDouble narea;
/**
*��¥��
*/
public Integer nfloor;
/**
*�ܻ���
*/
public Integer nholds;
/**
*סլ����
*/
public Integer nhomeholds;
/**
*���̻���
*/
public Integer nshopholds;
/**
*д��¥����
*/
public Integer nofficeholds;
/**
 * ��λ����
 */
public Integer ncarholds;
/**
*������1
*/
public UFDouble leaserate1;
/**
*������2
*/
public UFDouble leaserate2;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*������
*/
public String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�Զ�����1
*/
public String vdef1;
/**
*�Զ�����2
*/
public String vdef2;
/**
*�Զ�����3
*/
public String vdef3;
/**
*�Զ�����4
*/
public String vdef4;
/**
*�Զ�����5
*/
public String vdef5;
/**
*ʱ���
*/
public UFDateTime ts;

public Integer dr;



public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
}

/**
* ���� pk_project��Getter����.����������Ŀ����
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getPk_project() {
return this.pk_project;
} 

/**
* ����pk_project��Setter����.����������Ŀ����
* ��������:2017-12-15
* @param newPk_project java.lang.String
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
} 
 
/**
* ���� code��Getter����.������������
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.������������
* ��������:2017-12-15
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.������������
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.������������
* ��������:2017-12-15
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� projecthp��Getter����.����������Ŀ����
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getProjecthp() {
return this.projecthp;
} 

/**
* ����projecthp��Setter����.����������Ŀ����
* ��������:2017-12-15
* @param newProjecthp java.lang.String
*/
public void setProjecthp ( String projecthp) {
this.projecthp=projecthp;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-12-15
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-12-15
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2017-12-15
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2017-12-15
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-12-15
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-12-15
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� narea��Getter����.�������������
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getNarea() {
return this.narea;
} 

/**
* ����narea��Setter����.�������������
* ��������:2017-12-15
* @param newNarea nc.vo.pub.lang.UFDouble
*/
public void setNarea ( UFDouble narea) {
this.narea=narea;
} 
 
/**
* ���� nfloor��Getter����.����������¥��
*  ��������:2017-12-15
* @return java.lang.Integer
*/
public Integer getNfloor() {
return this.nfloor;
} 

/**
* ����nfloor��Setter����.����������¥��
* ��������:2017-12-15
* @param newNfloor java.lang.Integer
*/
public void setNfloor ( Integer nfloor) {
this.nfloor=nfloor;
} 
 
/**
* ���� nholds��Getter����.���������ܻ���
*  ��������:2017-12-15
* @return java.lang.Integer
*/
public Integer getNholds() {
return this.nholds;
} 

/**
* ����nholds��Setter����.���������ܻ���
* ��������:2017-12-15
* @param newNholds java.lang.Integer
*/
public void setNholds ( Integer nholds) {
this.nholds=nholds;
} 
 
/**
* ���� nhomeholds��Getter����.��������סլ����
*  ��������:2017-12-15
* @return java.lang.Integer
*/
public Integer getNhomeholds() {
return this.nhomeholds;
} 

/**
* ����nhomeholds��Setter����.��������סլ����
* ��������:2017-12-15
* @param newNhomeholds java.lang.Integer
*/
public void setNhomeholds ( Integer nhomeholds) {
this.nhomeholds=nhomeholds;
} 
 
/**
* ���� nshopholds��Getter����.�����������̻���
*  ��������:2017-12-15
* @return java.lang.Integer
*/
public Integer getNshopholds() {
return this.nshopholds;
} 

/**
* ����nshopholds��Setter����.�����������̻���
* ��������:2017-12-15
* @param newNshopholds java.lang.Integer
*/
public void setNshopholds ( Integer nshopholds) {
this.nshopholds=nshopholds;
} 
 
/**
* ���� nofficeholds��Getter����.��������д��¥����
*  ��������:2017-12-15
* @return java.lang.Integer
*/
public Integer getNofficeholds() {
return this.nofficeholds;
} 

/**
* ����nofficeholds��Setter����.��������д��¥����
* ��������:2017-12-15
* @param newNofficeholds java.lang.Integer
*/
public void setNofficeholds ( Integer nofficeholds) {
this.nofficeholds=nofficeholds;
} 
 
/**
* ���� leaserate1��Getter����.��������������1
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLeaserate1() {
return this.leaserate1;
} 

/**
* ����leaserate1��Setter����.��������������1
* ��������:2017-12-15
* @param newLeaserate1 nc.vo.pub.lang.UFDouble
*/
public void setLeaserate1 ( UFDouble leaserate1) {
this.leaserate1=leaserate1;
} 
 
/**
* ���� leaserate2��Getter����.��������������2
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLeaserate2() {
return this.leaserate2;
} 

/**
* ����leaserate2��Setter����.��������������2
* ��������:2017-12-15
* @param newLeaserate2 nc.vo.pub.lang.UFDouble
*/
public void setLeaserate2 ( UFDouble leaserate2) {
this.leaserate2=leaserate2;
} 
 
/**
* ���� dbilldate��Getter����.���������Ƶ�����
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.���������Ƶ�����
* ��������:2017-12-15
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2017-12-15
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2017-12-15
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2017-12-15
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2017-12-15
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2017-12-15
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2017-12-15
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� vdef1��Getter����.���������Զ�����1
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getVdef1() {
return this.vdef1;
} 

/**
* ����vdef1��Setter����.���������Զ�����1
* ��������:2017-12-15
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* ���� vdef2��Getter����.���������Զ�����2
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getVdef2() {
return this.vdef2;
} 

/**
* ����vdef2��Setter����.���������Զ�����2
* ��������:2017-12-15
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* ���� vdef3��Getter����.���������Զ�����3
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getVdef3() {
return this.vdef3;
} 

/**
* ����vdef3��Setter����.���������Զ�����3
* ��������:2017-12-15
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* ���� vdef4��Getter����.���������Զ�����4
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getVdef4() {
return this.vdef4;
} 

/**
* ����vdef4��Setter����.���������Զ�����4
* ��������:2017-12-15
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* ���� vdef5��Getter����.���������Զ�����5
*  ��������:2017-12-15
* @return java.lang.String
*/
public String getVdef5() {
return this.vdef5;
} 

/**
* ����vdef5��Setter����.���������Զ�����5
* ��������:2017-12-15
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-12-15
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-12-15
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 

/**
 * ����ncarholds��Getter����.����������λ����
 * ����ʱ��:2018-01-09
 * @return java.lang.Integer
 */
public Integer getNcarholds() {
	return ncarholds;
}
/**
* ����ncarholds��Setter����.����������λ����
* ��������:2018-01-09
* @param newNcarholds java.lang.Integer
*/
public void setNcarholds(Integer ncarholds) {
	this.ncarholds = ncarholds;
}

	@Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.project");
    }
   }
    