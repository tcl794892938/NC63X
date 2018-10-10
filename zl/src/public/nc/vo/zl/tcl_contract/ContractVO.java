package nc.vo.zl.tcl_contract;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ContractVO extends SuperVO {
/**
*�������
*/
public String approvenote;
/**
*�����
*/
public String approver;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*������
*/
public String creator;
/**
*���ʱ��
*/
public UFDateTime dapprovetime;
/**
*�Ƶ�����
*/
public UFDate dbilldate;
/**
*��ͬ��ֹ��
*/
public UFDate denddate;
/**
*��������
*/
public UFDate dindate;
/**
*��������
*/
public UFDate doutdate;
/**
*ǩ������
*/
public UFDate drentdate;
/**
*��ͬ��ʼ��
*/
public UFDate dstartdate;
/**
*��ͬ���
*/
public String htcode;
/**
*��ͬ����
*/
public String htname;
/**
*��ͬ״̬
*/
public Integer htstatus;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�޸���
*/
public String modifier;
/**
*���������
*/
public UFDouble narea;
/**
*�����
*/
public UFDouble ndaymny;
/**
*����ܽ��
*/
public UFDouble nmny;
/**
*�����
*/
public UFDouble nmonthmny;
/**
*��𵥼�
*/
public UFDouble nrentprice;
/**
*�����
*/
public UFDouble nyearmny;
/**
*�Żݽ��
*/
public UFDouble nyhmny;
/**
*���ʽ
*/
public Integer paystyle;
/**
*��������
*/
public String pk_billtype;
/**
*����
*/
public String pk_contract;
/**
*��ͬ����
*/
public String pk_contracttype;
/**
*�շ���Ŀ
*/
public String pk_costproject;

public Integer ndegree;
/**
*����
*/
public String pk_group;
/**
*������֯
*/
public String pk_org;
/**
*������֯�汾
*/
public String pk_org_v;
/**
*���޷�ʽ
*/
public Integer rentstyle;
/**
*��ͬ˰��
*/
public Integer taxstyle;
/**
*ʱ���
*/
public UFDateTime ts;
/**
*���ݺ�
*/
public String vbillno;
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
*�汾
*/
public Integer version;
/**
*��ע
*/
public String vmemo;
/**
*��Դ����id
*/
public String vsrcid;
/**
*��Դ��������
*/
public String vsrctype;

/**
*�ͻ�
*/
public String pk_customer;

/**
*��Ŀ
*/
public String pk_project;

public UFDouble nbzjmny;
public UFDouble nzqmny;
public String vmzq;
public String vdzfs;

public UFBoolean is_mz;
public UFDouble nysmny;
public String vsrcno;

public Integer dr=0;

public Integer getDr() {
	return dr;
}

public void setDr(Integer dr) {
	this.dr = dr;
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
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getDapprovetime () {
return this.dapprovetime;
 } 

/** 
* �������ʱ��
*
* @param dapprovetime ���ʱ��
*/
public void setDapprovetime ( UFDateTime dapprovetime) {
this.dapprovetime=dapprovetime;
 } 

/** 
* ��ȡ�Ƶ�����
*
* @return �Ƶ�����
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* �����Ƶ�����
*
* @param dbilldate �Ƶ�����
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* ��ȡ��ͬ��ֹ��
*
* @return ��ͬ��ֹ��
*/
public UFDate getDenddate () {
return this.denddate;
 } 

/** 
* ���ú�ͬ��ֹ��
*
* @param denddate ��ͬ��ֹ��
*/
public void setDenddate ( UFDate denddate) {
this.denddate=denddate;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDindate () {
return this.dindate;
 } 

/** 
* ���ý�������
*
* @param dindate ��������
*/
public void setDindate ( UFDate dindate) {
this.dindate=dindate;
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDoutdate () {
return this.doutdate;
 } 

/** 
* ������������
*
* @param doutdate ��������
*/
public void setDoutdate ( UFDate doutdate) {
this.doutdate=doutdate;
 } 

/** 
* ��ȡǩ������
*
* @return ǩ������
*/
public UFDate getDrentdate () {
return this.drentdate;
 } 

/** 
* ����ǩ������
*
* @param drentdate ǩ������
*/
public void setDrentdate ( UFDate drentdate) {
this.drentdate=drentdate;
 } 

/** 
* ��ȡ��ͬ��ʼ��
*
* @return ��ͬ��ʼ��
*/
public UFDate getDstartdate () {
return this.dstartdate;
 } 

/** 
* ���ú�ͬ��ʼ��
*
* @param dstartdate ��ͬ��ʼ��
*/
public void setDstartdate ( UFDate dstartdate) {
this.dstartdate=dstartdate;
 } 

/** 
* ��ȡ��ͬ���
*
* @return ��ͬ���
*/
public String getHtcode () {
return this.htcode;
 } 

/** 
* ���ú�ͬ���
*
* @param htcode ��ͬ���
*/
public void setHtcode ( String htcode) {
this.htcode=htcode;
 } 

/** 
* ��ȡ��ͬ����
*
* @return ��ͬ����
*/
public String getHtname () {
return this.htname;
 } 

/** 
* ���ú�ͬ����
*
* @param htname ��ͬ����
*/
public void setHtname ( String htname) {
this.htname=htname;
 } 

/** 
* ��ȡ��ͬ״̬
*
* @return ��ͬ״̬
* @see String
*/
public Integer getHtstatus () {
return this.htstatus;
 } 

/** 
* ���ú�ͬ״̬
*
* @param htstatus ��ͬ״̬
* @see String
*/
public void setHtstatus ( Integer htstatus) {
this.htstatus=htstatus;
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
* ��ȡ���������
*
* @return ���������
*/
public UFDouble getNarea () {
return this.narea;
 } 

/** 
* �������������
*
* @param narea ���������
*/
public void setNarea ( UFDouble narea) {
this.narea=narea;
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getNdaymny () {
return this.ndaymny;
 } 

/** 
* ���������
*
* @param ndaymny �����
*/
public void setNdaymny ( UFDouble ndaymny) {
this.ndaymny=ndaymny;
 } 

/** 
* ��ȡ����ܽ��
*
* @return ����ܽ��
*/
public UFDouble getNmny () {
return this.nmny;
 } 

/** 
* ��������ܽ��
*
* @param nmny ����ܽ��
*/
public void setNmny ( UFDouble nmny) {
this.nmny=nmny;
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getNmonthmny () {
return this.nmonthmny;
 } 

/** 
* ���������
*
* @param nmonthmny �����
*/
public void setNmonthmny ( UFDouble nmonthmny) {
this.nmonthmny=nmonthmny;
 } 

/** 
* ��ȡ��𵥼�
*
* @return ��𵥼�
*/
public UFDouble getNrentprice () {
return this.nrentprice;
 } 

/** 
* ������𵥼�
*
* @param nrentprice ��𵥼�
*/
public void setNrentprice ( UFDouble nrentprice) {
this.nrentprice=nrentprice;
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getNyearmny () {
return this.nyearmny;
 } 

/** 
* ���������
*
* @param nyearmny �����
*/
public void setNyearmny ( UFDouble nyearmny) {
this.nyearmny=nyearmny;
 } 

/** 
* ��ȡ�Żݽ��
*
* @return �Żݽ��
*/
public UFDouble getNyhmny () {
return this.nyhmny;
 } 

/** 
* �����Żݽ��
*
* @param nyhmny �Żݽ��
*/
public void setNyhmny ( UFDouble nyhmny) {
this.nyhmny=nyhmny;
 } 

/** 
* ��ȡ���ʽ
*
* @return ���ʽ
* @see String
*/
public Integer getPaystyle () {
return this.paystyle;
 } 

/** 
* ���ø��ʽ
*
* @param paystyle ���ʽ
* @see String
*/
public void setPaystyle ( Integer paystyle) {
this.paystyle=paystyle;
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
* ��ȡ����
*
* @return ����
*/
public String getPk_contract () {
return this.pk_contract;
 } 

/** 
* ��������
*
* @param pk_contract ����
*/
public void setPk_contract ( String pk_contract) {
this.pk_contract=pk_contract;
 } 

/** 
* ��ȡ��ͬ����
*
* @return ��ͬ����
*/
public String getPk_contracttype () {
return this.pk_contracttype;
 } 

/** 
* ���ú�ͬ����
*
* @param pk_contracttype ��ͬ����
*/
public void setPk_contracttype ( String pk_contracttype) {
this.pk_contracttype=pk_contracttype;
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPk_costproject () {
return this.pk_costproject;
 } 

/** 
* �����շ���Ŀ
*
* @param pk_costproject �շ���Ŀ
*/
public void setPk_costproject ( String pk_costproject) {
this.pk_costproject=pk_costproject;
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
* ��ȡ������֯
*
* @return ������֯
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* ���ò�����֯
*
* @param pk_org ������֯
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* ��ȡ������֯�汾
*
* @return ������֯�汾
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* ���ò�����֯�汾
*
* @param pk_org_v ������֯�汾
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* ��ȡ���޷�ʽ
*
* @return ���޷�ʽ
* @see String
*/
public Integer getRentstyle () {
return this.rentstyle;
 } 

/** 
* �������޷�ʽ
*
* @param rentstyle ���޷�ʽ
* @see String
*/
public void setRentstyle ( Integer rentstyle) {
this.rentstyle=rentstyle;
 } 

/** 
* ��ȡ��ͬ˰��
*
* @return ��ͬ˰��
* @see String
*/
public Integer getTaxstyle () {
return this.taxstyle;
 } 

/** 
* ���ú�ͬ˰��
*
* @param taxstyle ��ͬ˰��
* @see String
*/
public void setTaxstyle ( Integer taxstyle) {
this.taxstyle=taxstyle;
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
public String getVbillno () {
return this.vbillno;
 } 

/** 
* ���õ��ݺ�
*
* @param vbillno ���ݺ�
*/
public void setVbillno ( String vbillno) {
this.vbillno=vbillno;
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
* ��ȡ�汾
*
* @return �汾
*/
public Integer getVersion () {
return this.version;
 } 

/** 
* ���ð汾
*
* @param version �汾
*/
public void setVersion ( Integer version) {
this.version=version;
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return this.vmemo;
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.vmemo=vmemo;
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
    return VOMetaFactory.getInstance().getVOMeta("zl.contract_zl");
  }

public String getPk_customer() {
	return pk_customer;
}

public void setPk_customer(String pk_customer) {
	this.pk_customer = pk_customer;
}

public String getPk_project() {
	return pk_project;
}

public void setPk_project(String pk_project) {
	this.pk_project = pk_project;
}

public UFDouble getNbzjmny() {
	return nbzjmny;
}

public void setNbzjmny(UFDouble nbzjmny) {
	this.nbzjmny = nbzjmny;
}

public UFDouble getNzqmny() {
	return nzqmny;
}

public void setNzqmny(UFDouble nzqmny) {
	this.nzqmny = nzqmny;
}

public String getVmzq() {
	return vmzq;
}

public void setVmzq(String vmzq) {
	this.vmzq = vmzq;
}

public String getVdzfs() {
	return vdzfs;
}

public void setVdzfs(String vdzfs) {
	this.vdzfs = vdzfs;
}

public UFBoolean getIs_mz() {
	return is_mz;
}

public void setIs_mz(UFBoolean is_mz) {
	this.is_mz = is_mz;
}

public UFDouble getNysmny() {
	return nysmny;
}

public void setNysmny(UFDouble nysmny) {
	this.nysmny = nysmny;
}

public String getVsrcno() {
	return vsrcno;
}

public void setVsrcno(String vsrcno) {
	this.vsrcno = vsrcno;
}

public Integer getNdegree() {
	return ndegree;
}

public void setNdegree(Integer ndegree) {
	this.ndegree = ndegree;
}
}