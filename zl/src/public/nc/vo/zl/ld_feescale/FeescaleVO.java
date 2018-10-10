package nc.vo.zl.ld_feescale;

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
 *  ��������:2017-12-20
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class FeescaleVO extends SuperVO {
	
/**
*��Ŀ����
*/
public String pk_feescale;
/**
*��׼����
*/
public String code;
/**
*��׼����
*/
public String name;
/**
*�շ���Ŀ
*/
public String chargeitem;
/**
*���㷽ʽ
*/
public Integer accountform;
/**
*��������
*/
public UFDouble accountscal;
/**
*���뷽ʽ
*/
public Integer roundform;
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
    
public Integer DR=0;
    
public Integer getDR() {
	return DR;
}

public void setDR(Integer dR) {
	DR = dR;
}

/**
* ���� pk_feescale��Getter����.����������Ŀ����
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getPk_feescale() {
return this.pk_feescale;
} 

/**
* ����pk_feescale��Setter����.����������Ŀ����
* ��������:2017-12-20
* @param newPk_feescale java.lang.String
*/
public void setPk_feescale ( String pk_feescale) {
this.pk_feescale=pk_feescale;
} 
 
/**
* ���� code��Getter����.����������׼����
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getCode() {
return this.code;
} 

/**
* ����code��Setter����.����������׼����
* ��������:2017-12-20
* @param newCode java.lang.String
*/
public void setCode ( String code) {
this.code=code;
} 
 
/**
* ���� name��Getter����.����������׼����
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getName() {
return this.name;
} 

/**
* ����name��Setter����.����������׼����
* ��������:2017-12-20
* @param newName java.lang.String
*/
public void setName ( String name) {
this.name=name;
} 
 
/**
* ���� chargeitem��Getter����.���������շ���Ŀ
*  ��������:2017-12-20
* @return nc.vo.sm.UserVO
*/
public String getChargeitem() {
return this.chargeitem;
} 

/**
* ����chargeitem��Setter����.���������շ���Ŀ
* ��������:2017-12-20
* @param newChargeitem nc.vo.sm.UserVO
*/
public void setChargeitem ( String chargeitem) {
this.chargeitem=chargeitem;
} 
 
/**
* ���� accountform��Getter����.�����������㷽ʽ
*  ��������:2017-12-20
* @return nc.vo.zl.ld_feescale.AccountType
*/
public Integer getAccountform() {
return this.accountform;
} 

/**
* ����accountform��Setter����.�����������㷽ʽ
* ��������:2017-12-20
* @param newAccountform nc.vo.zl.ld_feescale.AccountType
*/
public void setAccountform ( Integer accountform) {
this.accountform=accountform;
} 
 
/**
* ���� accountscal��Getter����.����������������
*  ��������:2017-12-20
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getAccountscal() {
return this.accountscal;
} 

/**
* ����accountscal��Setter����.����������������
* ��������:2017-12-20
* @param newAccountscal nc.vo.pub.lang.UFDouble
*/
public void setAccountscal ( UFDouble accountscal) {
this.accountscal=accountscal;
} 
 
/**
* ���� roundform��Getter����.�����������뷽ʽ
*  ��������:2017-12-20
* @return nc.vo.zl.ld_feescale.RoundType
*/
public Integer getRoundform() {
return this.roundform;
} 

/**
* ����roundform��Setter����.�����������뷽ʽ
* ��������:2017-12-20
* @param newRoundform nc.vo.zl.ld_feescale.RoundType
*/
public void setRoundform ( Integer roundform) {
this.roundform=roundform;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-12-20
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-12-20
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2017-12-20
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2017-12-20
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-12-20
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-12-20
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� dbilldate��Getter����.���������Ƶ�����
*  ��������:2017-12-20
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.���������Ƶ�����
* ��������:2017-12-20
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2017-12-20
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2017-12-20
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2017-12-20
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2017-12-20
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2017-12-20
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2017-12-20
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2017-12-20
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2017-12-20
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� vdef1��Getter����.���������Զ�����1
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getVdef1() {
return this.vdef1;
} 

/**
* ����vdef1��Setter����.���������Զ�����1
* ��������:2017-12-20
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* ���� vdef2��Getter����.���������Զ�����2
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getVdef2() {
return this.vdef2;
} 

/**
* ����vdef2��Setter����.���������Զ�����2
* ��������:2017-12-20
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* ���� vdef3��Getter����.���������Զ�����3
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getVdef3() {
return this.vdef3;
} 

/**
* ����vdef3��Setter����.���������Զ�����3
* ��������:2017-12-20
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* ���� vdef4��Getter����.���������Զ�����4
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getVdef4() {
return this.vdef4;
} 

/**
* ����vdef4��Setter����.���������Զ�����4
* ��������:2017-12-20
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* ���� vdef5��Getter����.���������Զ�����5
*  ��������:2017-12-20
* @return java.lang.String
*/
public String getVdef5() {
return this.vdef5;
} 

/**
* ����vdef5��Setter����.���������Զ�����5
* ��������:2017-12-20
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-12-20
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-12-20
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.feescale");
    }
   }
    