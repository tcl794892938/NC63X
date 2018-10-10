package nc.vo.zl.cwf_gather;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class GatherVO extends SuperVO {
/**
*�������
*/
public String approvenote;
/**
*�����
*/
public String approver;
/**
*���ʱ��
*/
public UFDateTime approvetime;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*�տ�����
*/
public UFDate dbilldate;
/**
*�Ƿ��Ѵ����ƽ̨
*/
public UFBoolean is_kjpt;
/**
*�Ƿ�ƾ֤
*/
public UFBoolean is_voucher;
/**
 * �Ƿ����Ƶ���
 */
public UFBoolean isadd;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*����
*/
public UFDouble ncurrrate;
/**
*�Żݽ��
*/
public UFDouble ndiscountmny;
/**
*Ӧ���ܽ��
*/
public UFDouble nrecmny;
/**
*�����ܽ��
*/
public UFDouble nysmny;
/**
*�տ��ܽ��
*/
public UFDouble nskmny;
/**
*˰��
*/
public UFDouble ntaxrate;
/**
*�����˺�
*/
public String pk_accountno;
/**
*��������
*/
public String pk_billtype;
/**
*�ֽ�����
*/
public String pk_cashflow;
/**
*����
*/
public String pk_currtype;
/**
*�ͻ�����
*/
public String pk_customer;
/**
*����
*/
public String pk_gather;
/**
*����
*/
public String pk_group;
/**
*����
*/
public String pk_moneytype;
/**
*��֯
*/
public String pk_org;
/**
*��֯�汾
*/
public String pk_org_v;
/**
*������ʽ
*/
public String pk_paytype;
/**
*��Ŀ��Ϣ
*/
public String pk_project;
/**
*��ҵ����
*/
public String pk_psndoc;
/**
*�տ���
*/
public String pk_skr;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillcode;
/**
*����״̬
*/
public Integer vbillstatus;
/**
*�������ͱ���
*/
public String vbilltypecode;
/**
*�Զ�����1
*/
public String vdef1;
/**
*�Զ�����10
*/
public String vdef10;
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
*�Զ�����6
*/
public String vdef6;
/**
*�Զ�����7
*/
public String vdef7;
/**
*�Զ�����8
*/
public String vdef8;
/**
*�Զ�����9
*/
public String vdef9;
/**
*attrDisplayName
*/
public String vsrcbid;
/**
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;




public UFBoolean getIsadd() {
	return isadd;
}

public void setIsadd(UFBoolean isadd) {
	this.isadd = isadd;
}

public UFDouble getNrecmny() {
	return nrecmny;
}

public void setNrecmny(UFDouble nrecmny) {
	this.nrecmny = nrecmny;
}

public UFDouble getNysmny() {
	return nysmny;
}

public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}

/** 
* ��ȡ�������
*
* @return �������
*/
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* �����������
*
* @param approvenote �������
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return this.approver;
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getApprovetime () {
return this.approvetime;
 } 

/** 
* �������ʱ��
*
* @param approvetime ���ʱ��
*/
public void setApprovetime ( UFDateTime approvetime) {
this.approvetime=approvetime;
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getCreationtime () {
return this.creationtime;
 } 

/** 
* ���ô���ʱ��
*
* @param creationtime ����ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getCreator () {
return this.creator;
 } 

/** 
* ���ô�����
*
* @param creator ������
*/
public void setCreator ( String creator) {
this.creator=creator;
 } 

/** 
* ��ȡ�տ�����
*
* @return �տ�����
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* �����տ�����
*
* @param dbilldate �տ�����
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* ��ȡ�Ƿ��Ѵ����ƽ̨
*
* @return �Ƿ��Ѵ����ƽ̨
*/
public UFBoolean getIs_kjpt () {
return this.is_kjpt;
 } 

/** 
* �����Ƿ��Ѵ����ƽ̨
*
* @param is_kjpt �Ƿ��Ѵ����ƽ̨
*/
public void setIs_kjpt ( UFBoolean is_kjpt) {
this.is_kjpt=is_kjpt;
 } 

/** 
* ��ȡ�Ƿ�ƾ֤
*
* @return �Ƿ�ƾ֤
*/
public UFBoolean getIs_voucher () {
return this.is_voucher;
 } 

/** 
* �����Ƿ�ƾ֤
*
* @param is_voucher �Ƿ�ƾ֤
*/
public void setIs_voucher ( UFBoolean is_voucher) {
this.is_voucher=is_voucher;
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return this.modifiedtime;
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return this.modifier;
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getNcurrrate () {
return this.ncurrrate;
 } 

/** 
* ���û���
*
* @param ncurrrate ����
*/
public void setNcurrrate ( UFDouble ncurrrate) {
this.ncurrrate=ncurrrate;
 } 

/** 
* ��ȡ�Żݽ��
*
* @return �Żݽ��
*/
public UFDouble getNdiscountmny () {
return this.ndiscountmny;
 } 

/** 
* �����Żݽ��
*
* @param ndiscountmny �Żݽ��
*/
public void setNdiscountmny ( UFDouble ndiscountmny) {
this.ndiscountmny=ndiscountmny;
 } 

/** 
* ��ȡ�տ��ܽ��
*
* @return �տ��ܽ��
*/
public UFDouble getNskmny () {
return this.nskmny;
 } 

/** 
* �����տ��ܽ��
*
* @param nskmny �տ��ܽ��
*/
public void setNskmny ( UFDouble nskmny) {
this.nskmny=nskmny;
 } 

/** 
* ��ȡ˰��
*
* @return ˰��
*/
public UFDouble getNtaxrate () {
return this.ntaxrate;
 } 

/** 
* ����˰��
*
* @param ntaxrate ˰��
*/
public void setNtaxrate ( UFDouble ntaxrate) {
this.ntaxrate=ntaxrate;
 } 

/** 
* ��ȡ�����˺�
*
* @return �����˺�
*/
public String getPk_accountno () {
return this.pk_accountno;
 } 

/** 
* ���������˺�
*
* @param pk_accountno �����˺�
*/
public void setPk_accountno ( String pk_accountno) {
this.pk_accountno=pk_accountno;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_billtype () {
return this.pk_billtype;
 } 

/** 
* ���õ�������
*
* @param pk_billtype ��������
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
 } 

/** 
* ��ȡ�ֽ�����
*
* @return �ֽ�����
*/
public String getPk_cashflow () {
return this.pk_cashflow;
 } 

/** 
* �����ֽ�����
*
* @param pk_cashflow �ֽ�����
*/
public void setPk_cashflow ( String pk_cashflow) {
this.pk_cashflow=pk_cashflow;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_currtype () {
return this.pk_currtype;
 } 

/** 
* ���ñ���
*
* @param pk_currtype ����
*/
public void setPk_currtype ( String pk_currtype) {
this.pk_currtype=pk_currtype;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getPk_customer () {
return this.pk_customer;
 } 

/** 
* ���ÿͻ�����
*
* @param pk_customer �ͻ�����
*/
public void setPk_customer ( String pk_customer) {
this.pk_customer=pk_customer;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_gather () {
return this.pk_gather;
 } 

/** 
* ��������
*
* @param pk_gather ����
*/
public void setPk_gather ( String pk_gather) {
this.pk_gather=pk_gather;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return this.pk_group;
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_moneytype () {
return this.pk_moneytype;
 } 

/** 
* ���ÿ���
*
* @param pk_moneytype ����
*/
public void setPk_moneytype ( String pk_moneytype) {
this.pk_moneytype=pk_moneytype;
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* ��ȡ��֯�汾
*
* @return ��֯�汾
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* ������֯�汾
*
* @param pk_org_v ��֯�汾
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* ��ȡ������ʽ
*
* @return ������ʽ
*/
public String getPk_paytype () {
return this.pk_paytype;
 } 

/** 
* ���ø�����ʽ
*
* @param pk_paytype ������ʽ
*/
public void setPk_paytype ( String pk_paytype) {
this.pk_paytype=pk_paytype;
 } 

/** 
* ��ȡ��Ŀ��Ϣ
*
* @return ��Ŀ��Ϣ
*/
public String getPk_project () {
return this.pk_project;
 } 

/** 
* ������Ŀ��Ϣ
*
* @param pk_project ��Ŀ��Ϣ
*/
public void setPk_project ( String pk_project) {
this.pk_project=pk_project;
 } 

/** 
* ��ȡ��ҵ����
*
* @return ��ҵ����
*/
public String getPk_psndoc () {
return this.pk_psndoc;
 } 

/** 
* ������ҵ����
*
* @param pk_psndoc ��ҵ����
*/
public void setPk_psndoc ( String pk_psndoc) {
this.pk_psndoc=pk_psndoc;
 } 

/** 
* ��ȡ�տ���
*
* @return �տ���
*/
public String getPk_skr () {
return this.pk_skr;
 } 

/** 
* �����տ���
*
* @param pk_skr �տ���
*/
public void setPk_skr ( String pk_skr) {
this.pk_skr=pk_skr;
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return this.ts;
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.ts=ts;
 } 

/** 
* ��ȡ���ݺ�
*
* @return ���ݺ�
*/
public String getVbillcode () {
return this.vbillcode;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillcode ���ݺ�
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getVbillstatus () {
return this.vbillstatus;
 } 

/** 
* ���õ���״̬
*
* @param vbillstatus ����״̬
* @see String
*/
public void setVbillstatus ( Integer vbillstatus) {
this.vbillstatus=vbillstatus;
 } 

/** 
* ��ȡ�������ͱ���
*
* @return �������ͱ���
*/
public String getVbilltypecode () {
return this.vbilltypecode;
 } 

/** 
* ���õ������ͱ���
*
* @param vbilltypecode �������ͱ���
*/
public void setVbilltypecode ( String vbilltypecode) {
this.vbilltypecode=vbilltypecode;
 } 

/** 
* ��ȡ�Զ�����1
*
* @return �Զ�����1
*/
public String getVdef1 () {
return this.vdef1;
 } 

/** 
* �����Զ�����1
*
* @param vdef1 �Զ�����1
*/
public void setVdef1 ( String vdef1) {
this.vdef1=vdef1;
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return this.vdef10;
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.vdef10=vdef10;
 } 

/** 
* ��ȡ�Զ�����2
*
* @return �Զ�����2
*/
public String getVdef2 () {
return this.vdef2;
 } 

/** 
* �����Զ�����2
*
* @param vdef2 �Զ�����2
*/
public void setVdef2 ( String vdef2) {
this.vdef2=vdef2;
 } 

/** 
* ��ȡ�Զ�����3
*
* @return �Զ�����3
*/
public String getVdef3 () {
return this.vdef3;
 } 

/** 
* �����Զ�����3
*
* @param vdef3 �Զ�����3
*/
public void setVdef3 ( String vdef3) {
this.vdef3=vdef3;
 } 

/** 
* ��ȡ�Զ�����4
*
* @return �Զ�����4
*/
public String getVdef4 () {
return this.vdef4;
 } 

/** 
* �����Զ�����4
*
* @param vdef4 �Զ�����4
*/
public void setVdef4 ( String vdef4) {
this.vdef4=vdef4;
 } 

/** 
* ��ȡ�Զ�����5
*
* @return �Զ�����5
*/
public String getVdef5 () {
return this.vdef5;
 } 

/** 
* �����Զ�����5
*
* @param vdef5 �Զ�����5
*/
public void setVdef5 ( String vdef5) {
this.vdef5=vdef5;
 } 

/** 
* ��ȡ�Զ�����6
*
* @return �Զ�����6
*/
public String getVdef6 () {
return this.vdef6;
 } 

/** 
* �����Զ�����6
*
* @param vdef6 �Զ�����6
*/
public void setVdef6 ( String vdef6) {
this.vdef6=vdef6;
 } 

/** 
* ��ȡ�Զ�����7
*
* @return �Զ�����7
*/
public String getVdef7 () {
return this.vdef7;
 } 

/** 
* �����Զ�����7
*
* @param vdef7 �Զ�����7
*/
public void setVdef7 ( String vdef7) {
this.vdef7=vdef7;
 } 

/** 
* ��ȡ�Զ�����8
*
* @return �Զ�����8
*/
public String getVdef8 () {
return this.vdef8;
 } 

/** 
* �����Զ�����8
*
* @param vdef8 �Զ�����8
*/
public void setVdef8 ( String vdef8) {
this.vdef8=vdef8;
 } 

/** 
* ��ȡ�Զ�����9
*
* @return �Զ�����9
*/
public String getVdef9 () {
return this.vdef9;
 } 

/** 
* �����Զ�����9
*
* @param vdef9 �Զ�����9
*/
public void setVdef9 ( String vdef9) {
this.vdef9=vdef9;
 } 

/** 
* ��ȡattrDisplayName
*
* @return attrDisplayName
*/
public String getVsrcbid () {
return this.vsrcbid;
 } 

/** 
* ����attrDisplayName
*
* @param vsrcbid attrDisplayName
*/
public void setVsrcbid ( String vsrcbid) {
this.vsrcbid=vsrcbid;
 } 

/** 
* ��ȡ��Դ����id
*
* @return ��Դ����id
*/
public String getVsrcid () {
return this.vsrcid;
 } 

/** 
* ������Դ����id
*
* @param vsrcid ��Դ����id
*/
public void setVsrcid ( String vsrcid) {
this.vsrcid=vsrcid;
 } 

/** 
* ��ȡ��Դ��������
*
* @return ��Դ��������
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* ������Դ��������
*
* @param vsrctype ��Դ��������
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("zl.gather");
  }
}