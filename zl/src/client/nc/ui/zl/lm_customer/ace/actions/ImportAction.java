package nc.ui.zl.lm_customer.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;

import nc.ui.zl.abs.tool.ExcelUtils;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;
import nc.vo.bd.cust.CustSupplierVO;
import nc.vo.bd.cust.custorg.CustOrgVO;
import nc.vo.zl.lm_customer.CustomerVO;
import nc.vo.zl.lm_customer.Customer_zzxmVO;

public class ImportAction extends NCAction {

	private static final long serialVersionUID = 8519431471345832207L;

	// ����ͻ���Ϣ��������
	Map<Integer, Object[]> bList = new HashMap<Integer, Object[]>();
	Map<Integer, Object[]> topList = new HashMap<Integer, Object[]>();
	Map<Integer, String> maph=new HashMap<Integer, String>();
	Map<String, Integer> maph1=new HashMap<String, Integer>();
	Map<String, Integer> maph2=new HashMap<String, Integer>();

	private ShowUpableBillForm bill;
	
	private TangramContainer tcr;

	private LoginContext context;
	
	private Integer btrow=0;

	public ImportAction() {
		super();
		this.setCode("import");
		this.setBtnName("����");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent arg0) throws Exception {

		// TODO �Զ����ɵķ������
		// System.out.println("����");

		Object[][] exlimp = ExcelUtils.doImport(this.tcr);

		if (getStgObj(exlimp).equals("")) {
			return;
		}
		
		// ��Excel�е�ֵ�ŵ�mapList��
		for (int i = 0; i < exlimp.length; i++) {
			bList.put(i, exlimp[i]);
		}
		
		BillCardPanel card=bill.getBillCardPanel();
		
		maph.put(0, card.getHeadItem("pk_org").getName());
		//map.put(1, card.getHeadItem("customercode").getName());
		maph.put(1, card.getHeadItem("customername").getName());
		maph.put(2, card.getHeadItem("customertype").getName());
		maph.put(3, card.getHeadItem("customerlxfs").getName());
		maph.put(4, card.getHeadItem("customeraddress").getName());
		maph.put(5, card.getHeadItem("zygw").getName());
		maph.put(6, card.getHeadItem("sfzh").getName());
		maph.put(7, card.getHeadItem("yyzz").getName());
		maph.put(8, card.getHeadItem("fzkh").getName());
		maph.put(9, "��ͷ�к�");
		maph.put(10, card.getBodyItem("pk_project").getName());

		for(int j=0;j<bList.size();j++){
			Object[] exobj_0 = bList.get(j);
			for (int i = 0; i < exobj_0.length; i++) {
				if ("��ͷ�к�".equals(getStgObj(exobj_0[i]))) {
					btrow=j;
					break;
				}
			}
		}
		Object[] exo1=bList.get(0);
		Object[] exo2=bList.get(btrow);
		
		for(int i=0;i<exo1.length;i++){
			maph1.put(getStgObj(exo1[i]), i);
		}
		for(int i=0;i<exo2.length;i++){
			maph2.put(getStgObj(exo2[i]),i);
		}
		
		//��ȡ��ͷ����
		for(int i=0;i<btrow;i++){
			Object[] top=bList.get(i);
			if((top[maph1.get(maph.get(0))]==null||top[maph1.get(maph.get(0))].equals(""))
					&&(top[maph1.get(maph.get(1))]==null||top[maph1.get(maph.get(1))].equals(""))
					&&(top[maph1.get(maph.get(2))]==null||top[maph1.get(maph.get(2))].equals(""))
					&&(top[maph1.get(maph.get(3))]==null||top[maph1.get(maph.get(3))].equals(""))
					&&(top[maph1.get(maph.get(4))]==null||top[maph1.get(maph.get(4))].equals(""))
					&&(top[maph1.get(maph.get(5))]==null||top[maph1.get(maph.get(5))].equals(""))
					&&(top[maph1.get(maph.get(6))]==null||top[maph1.get(maph.get(6))].equals(""))
					&&(top[maph1.get(maph.get(7))]==null||top[maph1.get(maph.get(7))].equals(""))
					&&(top[maph1.get(maph.get(8))]==null||top[maph1.get(maph.get(8))].equals(""))){
				continue;
			}
			topList.put(i, top);
		}
		
		if(topList.size()==1){
			MessageDialog.showHintDlg(tcr, "��ʾ", "����ı�ͷ�����ݣ�");
			return;
		}

		if (null != exlimp) {

			// ��ʽУ��
			boolean gsFag = checkExcelGS();
			if (!gsFag) {
				String errMsg = "�����Excel��ʽ����ȷ��ȱ�ٱ�������д��";
				MessageDialog.showHintDlg(tcr, "��ʾ", errMsg);
				bList.clear();
				topList.clear();
				return;
			}

			// ����������Ƿ�Ϊ��
			String bsFag = checkNotNull();
			if (!"".equals(bsFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + bsFag.substring(0, bsFag.length() - 1)
								+ "�����б������Ϊ�գ����������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//����ÿ�������Ƿ��ж�Ӧ����Ŀ����
			String prodyFag=checkProcodeDy();
			if(!"".equals(prodyFag)){
				MessageDialog.showHintDlg(
						tcr,
						"��ʾ",
						"Excel�е�"
								+ prodyFag.substring(0, prodyFag.length() - 1)
								+ "��û�ж�Ӧ��"+maph.get(10)+"�����������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//�����Ƿ���嶼�ж�Ӧ�ı�ͷ����
			String btdyFag=checkBTCz();
			if(!"".equals(btdyFag)){
				MessageDialog.showHintDlg(
						tcr,
						"��ʾ",
						"Excel�е�"
								+ btdyFag.substring(0, btdyFag.length() - 1)
								+ "��"+maph.get(10)+"û�ж�Ӧ�ı�ͷ���ݣ����������룡");
				bList.clear();
				topList.clear();
				return;
			}

			// ������֯�����Ƿ����
			String zzczFag = checkZzbmCz();
			if (!"".equals(zzczFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + zzczFag.substring(0, zzczFag.length() - 1)
								+ "��"+maph.get(0)+"�����ڣ����������룡");
				bList.clear();
				topList.clear();
				return;
			}

			// ����ͻ��������ݿ����Ƿ����
			String typeFag = checkTypeCz();
			if (!"".equals(typeFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + typeFag.substring(0, typeFag.length() - 1)
								+ "��"+maph.get(2)+"�����ڻ��߲��ǵ�����ĩ�������������룡");
				bList.clear();
				topList.clear();
				return;
			}

			// ������ҵ�������ݿ����Ƿ����
			String gwFag = checkGwCz();
			if (!"".equals(gwFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + gwFag.substring(0, gwFag.length() - 1)
								+ "��"+maph.get(5)+"�����ڣ����������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//�������֤��Excel���Ƿ����ظ�
			String sfcfFag=checkSFCf();
			if (!"".equals(sfcfFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + sfcfFag.substring(0, sfcfFag.length() - 1)
								+ "��"+maph.get(6)+"���ظ������������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//�������֤�����ݿ����Ƿ����
			String sfczFag=checkSFCz();
			if (!"".equals(sfczFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + sfczFag.substring(0, sfczFag.length() - 1)
								+ "��"+maph.get(6)+"�Ѿ���ע�ᣬ���������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//����Ӫҵִ��Excel���Ƿ����ظ�
			String yycfFag=checkYYCf();
			if (!"".equals(yycfFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + yycfFag.substring(0, yycfFag.length() - 1)
								+ "��"+maph.get(7)+"���ظ������������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//����Ӫҵִ�����ݿ����Ƿ����
			String yyczFag=checkYYCz();
			if (!"".equals(yyczFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + yyczFag.substring(0, yyczFag.length() - 1)
								+ "��"+maph.get(7)+"�Ѿ���ע�ᣬ���������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			//���鸨���ͻ��Ƿ����
			String fzkhCz=checkFzkhCz();
			if(!"".equals(fzkhCz)){
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + fzkhCz.substring(0, fzkhCz.length() - 1)
								+ "��"+maph.get(8)+"�����ڣ����������룡");
				bList.clear();
				topList.clear();
				return;
			}
			
			// ������Ŀ�������ݿ����Ƿ����
			String proczFag = checkProcodeCz();
			if (!"".equals(proczFag)) {
				MessageDialog.showHintDlg(
						tcr,
						"��ʾ",
						"Excel�е�"
								+ proczFag.substring(0, proczFag.length() - 1)
								+ "��"+maph.get(10)+"�����ڣ����������룡");
				bList.clear();
				topList.clear();
				return;
			}

			// ���ݿ�������
			// �½�һ���տͻ���ϢVOlist
			List<CustomerVO> voList = new ArrayList<CustomerVO>();

			for (int i = 1; i < topList.size(); i++) {
				Object[] colobj = topList.get(i);
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(
						IUAPQueryBS.class);

				// NEWһ���ͻ���Ϣ
				CustomerVO vo = new CustomerVO();
				// ��ȡ��֯����
				String sql = "select org_orgs.pk_org,org_orgs.pk_group,org_orgs.pk_vid from org_orgs where nvl(dr,0)=0 and org_orgs.code='"
						+ getStgObj(colobj[maph1.get(maph.get(0))]) + "'";
				Map<String, Object> map = (Map<String, Object>) iQ
						.executeQuery(sql, new MapProcessor());
				Object pk_org = map.get("pk_org");
				Object pk_group = map.get("pk_group");
				Object pk_org_v = map.get("pk_vid");
				vo.setPk_org(getStgObj(pk_org));
				vo.setPk_group(getStgObj(pk_group));
				vo.setPk_org_v(getStgObj(pk_org_v));
				//vo.setCustomercode(getVbillCode());
				vo.setCustomername(getStgObj(colobj[maph1.get(maph.get(1))]));
				// ��ȡ�ͻ���������
				String sql1 = "select pk_customertype from zl_customertype where nvl(dr,0)=0 and code='"
						+ getStgObj(colobj[maph1.get(maph.get(2))]) + "'";
				Object pk_cus = iQ.executeQuery(sql1, new ColumnProcessor());
				vo.setCustomertype(getStgObj(pk_cus));
				vo.setCustomerlxfs(getStgObj(colobj[maph1.get(maph.get(3))]));
				vo.setCustomeraddress(getStgObj(colobj[maph1.get(maph.get(4))]));
				// ��ȡ��Ա��Ϣ����
				String sql2 = "select pk_psndoc from bd_psndoc where nvl(dr,0)=0 and code='"
						+ getStgObj(colobj[maph1.get(maph.get(5))]) + "'";
				Object pk_psndoc = iQ.executeQuery(sql2, new ColumnProcessor());
				vo.setZygw(getStgObj(pk_psndoc));
				vo.setSfzh(getStgObj(colobj[maph1.get(maph.get(6))]));
				vo.setYyzz(getStgObj(colobj[maph1.get(maph.get(7))]));
				
				String getFzkh="select pk_customer from bd_customer where nvl(dr,0)=0 and code='"+getStgObj(colobj[maph1.get(maph.get(8))])+"'";
				Object fzkh=iQ.executeQuery(getFzkh, new ColumnProcessor());
				vo.setFzkh(getStgObj(fzkh));
				vo.setDbilldate(AppContext.getInstance().getBusiDate());
				vo.setCreator(AppContext.getInstance().getPkUser());
				vo.setCreationtime(AppContext.getInstance().getServerTime());
				voList.add(vo);
				
			}

			for (int i=1;i<=voList.size();i++) {
				voList.get(i-1).setCustomercode(getVbillCode().get(i-1));
			}
			
			IVOPersistence ivp = NCLocator.getInstance().lookup(
					IVOPersistence.class);
			List<Customer_zzxmVO> czlist = new ArrayList<Customer_zzxmVO>();
			String[] pk_b = ivp.insertVOList(voList);
			for (int i = 0; i < pk_b.length; i++) {
				for (int j = btrow+1; j < bList.size(); j++) {
					Object[] colobj = bList.get(j);
					IUAPQueryBS iQ = NCLocator.getInstance().lookup(
							IUAPQueryBS.class);

					if (getStgObj(colobj[maph2.get(maph.get(9))]).equals(getStgObj(i + 2))) {
						Customer_zzxmVO czvo = new Customer_zzxmVO();
						czvo.setPk_customer(getStgObj(pk_b[i]));
						String sql3 = "select pk_project from zl_project where nvl(dr,0)=0 and code='"
								+ getStgObj(colobj[maph2.get(maph.get(10))]) + "'";
						Object pk_project = iQ.executeQuery(sql3, new ColumnProcessor());
						czvo.setPk_project(getStgObj(pk_project));
						czlist.add(czvo);
					}
				}
			}
			ivp.insertVOList(czlist);
			
			List<CustomerVO> vos1=new ArrayList<CustomerVO>();//�޸����ͻ�
			List<String> pk_c=new ArrayList<String>();
			for(int i=1;i<topList.size();i++){
				Object[] exobj=topList.get(i);
				if(getStgObj(exobj[maph1.get(maph.get(8))]).equals("")){
					pk_c.add(pk_b[i-1]);
					vos1.add(voList.get(i-1));
				}
			}
			
			List<nc.vo.bd.cust.CustomerVO> list3=new ArrayList<nc.vo.bd.cust.CustomerVO>();
			List<CustSupplierVO> list4=new ArrayList<CustSupplierVO>();
			List<CustOrgVO> list5=new ArrayList<CustOrgVO>();
			for(int i=0;i<pk_c.size();i++){
				nc.vo.bd.cust.CustomerVO cvo=new nc.vo.bd.cust.CustomerVO();
				cvo.setPk_customer(pk_c.get(i));
				cvo.setCode(vos1.get(i).getCustomercode());
				cvo.setName(vos1.get(i).getCustomername());
				cvo.setPk_group(vos1.get(i).getPk_group());
				cvo.setPk_org(vos1.get(i).getPk_org());
				cvo.setCreator(vos1.get(i).getCreator());
				cvo.setCreationtime(vos1.get(i).getCreationtime());
				cvo.setModifier(vos1.get(i).getModifier());
				cvo.setModifiedtime(vos1.get(i).getModifiedtime());
				cvo.setEnablestate(2);
				cvo.setTel1(vos1.get(i).getCustomerlxfs());
				cvo.setPk_country("0001Z010000000079UJJ");
				cvo.setPk_custclass("1001A91000000009IZL0");
				cvo.setPk_format("FMT0Z000000000000000");
				cvo.setPk_timezone("0001Z010000000079U2P");
				cvo.setCreator(AppContext.getInstance().getPkUser());
				cvo.setCreationtime(AppContext.getInstance().getServerTime());
				list3.add(cvo);
				
				CustSupplierVO csvo=new CustSupplierVO();
				csvo.setPk_cust_sup(pk_c.get(i));
				csvo.setCode(vos1.get(i).getCustomercode());
				csvo.setName(vos1.get(i).getCustomername());
				csvo.setSupenablestate(2);
				csvo.setCustsuptype(1);
				csvo.setPk_custclass("1001A91000000009IZL0");
				csvo.setPk_group(vos1.get(i).getPk_group());
				csvo.setPk_org(vos1.get(i).getPk_org());
				list4.add(csvo);
				
				CustOrgVO covo=new CustOrgVO();
				covo.setPk_customer(pk_c.get(i));
				covo.setPk_org(vos1.get(i).getPk_org());
				covo.setPk_group(vos1.get(i).getPk_group());
				covo.setEnablestate(2);
				covo.setDataoriginflag(0);
				list5.add(covo);
				
			}
			nc.vo.bd.cust.CustomerVO[] cvos=list3.toArray(new nc.vo.bd.cust.CustomerVO[0]);
			CustSupplierVO[] csvos=list4.toArray(new CustSupplierVO[0]);
			CustOrgVO[] covos=list5.toArray(new CustOrgVO[0]);
			HYPubBO_Client.insertAry(cvos);
			HYPubBO_Client.insertAry(csvos);
			HYPubBO_Client.insertAry(covos);
			
			MessageDialog.showHintDlg(tcr, "��ʾ", "����ɹ���");
			bList.clear();
			topList.clear();
		}

	}

	// У���ʽ�Ƿ���ȷ���Ƿ�ȱ�ٱ�ͷ�ֶ�
	public boolean checkExcelGS() {
		// ������ŵ�map��
		Object[] exob = bList.get(0);
		Object[] exob1=bList.get(btrow);
		int count = 0;
		for (int i = 0; i < exob.length; i++) {
			if (maph.get(0).equals(getStgObj(exob[i]))
					|| maph.get(1).equals(getStgObj(exob[i]))
					|| maph.get(2).equals(getStgObj(exob[i]))
					|| maph.get(3).equals(getStgObj(exob[i]))
					|| maph.get(4).equals(getStgObj(exob[i]))
					|| maph.get(5).equals(getStgObj(exob[i]))
					|| maph.get(6).equals(getStgObj(exob[i]))
					|| maph.get(7).equals(getStgObj(exob[i]))
					|| maph.get(8).equals(getStgObj(exob[i]))) {
				count++;
			}
		}
		for(int i=0;i<exob1.length;i++){
			if(maph.get(9).equals(getStgObj(exob1[i]))
					|| maph.get(10).equals(getStgObj(exob1[i]))){
				count++;
			}
		}
		if (count < 11) {
			return false;
		}
		return true;
	}

	// �����Ϊ��
	public String checkNotNull() {
		String error = "";
		for (int i = 1; i < topList.size(); i++) {
			Object[] exobj = topList.get(i);
			if (getStgObj(exobj[maph1.get(maph.get(0))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(0))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(1))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(1))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(2))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(2))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(5))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(5))]).equals("")) {
				error += (i + 1) + ",";
			}
		}
		for(int i=btrow+1;i<bList.size();i++){
			Object[] exobj=bList.get(i);
			if(getStgObj(exobj[maph2.get(maph.get(10))]) == null
					|| getStgObj(exobj[maph2.get(maph.get(10))]).equals("")){
				error+=(i+1)+",";
			}
		}
		return error;
	}

	// �����֯�����Ƿ����
	public String checkZzbmCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < topList.size(); i++) {
			Object[] exobj = topList.get(i);
			// ��ȡ��֯����
			String get_pk_org = "select org_orgs.pk_org from org_orgs where nvl(dr,0)=0 and org_orgs.code='"
					+ getStgObj(exobj[maph1.get(maph.get(0))]) + "'";
			Object pk_org = iQ.executeQuery(get_pk_org, new ColumnProcessor());
			if (getStgObj(pk_org).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}

	// ���ͻ������Ƿ����
	public String checkTypeCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < topList.size(); i++) {
			Object[] exobj = topList.get(i);
			String sqltype = "select a.pk_customertype from zl_customertype a where nvl(a.dr,0)=0 and a.code='"
					+ getStgObj(exobj[maph1.get(maph.get(2))]) + "' and (a.pk_org='"+getStgObj(exobj[maph1.get(maph.get(0))])+"' or nvl(a.vdef1,0)=0) " +
							"and not exists (select b.pk_customertype from zl_customertype b where nvl(b.dr,0)=0 " +
							"and a.pk_customertype=b.pk_parentid)";
			Object pk_customertype = iQ.executeQuery(sqltype,
					new ColumnProcessor());
			if (getStgObj(pk_customertype).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}

	// �����ҵ�����Ƿ����
	public String checkGwCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < topList.size(); i++) {
			Object[] exobj = topList.get(i);
			String get_pk_psndoc = "select pk_psndoc from bd_psndoc where nvl(dr,0)=0 and code='"
					+ getStgObj(exobj[maph1.get(maph.get(5))]) + "'";
			Object pk_psndoc = iQ.executeQuery(get_pk_psndoc,
					new ColumnProcessor());
			if (getStgObj(pk_psndoc).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}
	
	// ������֤��Excel�Ƿ��ظ�
			public String checkSFCf() {
				String error = "";
				for (int i = 1; i < topList.size(); i++) {
					Object[] exobj = topList.get(i);
					if(getStgObj(exobj[maph1.get(maph.get(6))]).equals("")){
						continue;
					}
					for (int j = i + 1; j < topList.size(); j++) {
						Object[] exobj1 = topList.get(j);
						if (getStgObj(exobj[maph1.get(maph.get(6))]).equals(getStgObj(exobj1[maph1.get(maph.get(6))]))) {
							error += (i + 1) + ",";
						}
					}
				}
				return error;
			}

			// ������֤�����ݿ��Ƿ����
			public String checkSFCz() throws Exception {
				String error = "";
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				for (int i = 1; i < topList.size(); i++) {
					Object[] exobj = topList.get(i);
					if(getStgObj(exobj[maph1.get(maph.get(6))]).equals("")){
						continue;
					}
					String get_code = "select pk_customer from zl_customer where nvl(dr,0)=0 and sfzh='"
							+ getStgObj(exobj[maph1.get(maph.get(6))]) + "'";
					Object getcode = iQ.executeQuery(get_code, new ColumnProcessor());
					if (!getStgObj(getcode).equals("")) {
						error += (i + 1) + ",";
					}
				}
				return error;
			}
			
			//���Ӫҵִ��Excel�Ƿ��ظ�
			public String checkYYCf() {
				String error = "";
				for (int i = 1; i < topList.size(); i++) {
					Object[] exobj = topList.get(i);
					if(getStgObj(exobj[maph1.get(maph.get(7))]).equals("")){
						continue;
					}
					for (int j = i + 1; j < bList.size(); j++) {
						Object[] exobj1 = bList.get(j);
						if (getStgObj(exobj[maph1.get(maph.get(7))]).equals(getStgObj(exobj1[maph1.get(maph.get(7))]))) {
							error += (i + 1) + ",";
						}
					}
				}
				return error;
			}
			
			//���Ӫҵִ�����ݿ��Ƿ����ظ�
			public String checkYYCz() throws Exception {
				String error = "";
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				for (int i = 1; i < topList.size(); i++) {
					Object[] exobj = topList.get(i);
					if(getStgObj(exobj[maph1.get(maph.get(7))]).equals("")){
						continue;
					}
					String get_code = "select pk_customer from zl_customer where nvl(dr,0)=0 and yyzz='"
							+ getStgObj(exobj[maph1.get(maph.get(7))]) + "'";
					Object getcode = iQ.executeQuery(get_code, new ColumnProcessor());
					if (!getStgObj(getcode).equals("")) {
						error += (i + 1) + ",";
					}
				}
				return error;
			}		
	
	//��鸨���ͻ��Ƿ����
	public String checkFzkhCz() throws Exception{
		String error="";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for(int i=1;i<topList.size();i++){
			Object[] exobj=topList.get(i);
			if(!getStgObj(exobj[maph1.get(maph.get(8))]).equals("")){
				String sql="select count(*) from bd_customer where nvl(dr,0)=0 and code='"+getStgObj(exobj[maph1.get(maph.get(8))])+"'";
				Integer a =(Integer) iQ.executeQuery(sql, new ColumnProcessor());
				if(a<=0){
					error+=(i + 1) + ",";
				}
			}
		}
		return error;
	}
	
	//���ÿ�������Ƿ��ж�Ӧ����Ŀ����
	public String checkProcodeDy(){
		String error="";
		for(int i=1;i<topList.size();i++){
			Integer count=0;
			for(int j=btrow+1;j<bList.size();j++){
				Object[] exobj=bList.get(j);
				if(getStgObj(exobj[maph2.get(maph.get(9))]).equals(getStgObj(i+1))){
					count++;
					break;
				}
			}
			if(count==0){
				error+=(i+1)+",";
			}
		}
		return error;
	}
	
	//����к��Ƿ��ж�Ӧ��ͷ
		public String checkBTCz() throws Exception{
			String error="";
			for(int i=btrow+1;i<bList.size();i++){
				Object[] exobj=bList.get(i);
				Integer count=0;
				for(int j=1;j<topList.size();j++){
					if(getStgObj(exobj[maph2.get(maph.get(9))]).equals(getStgObj(j+1))){
						count++;
						break;
					}
				}
				if(count==0){
					error+=(i+1)+",";
				}
			}
			
			return error;
		}

	// �����Ŀ�����Ƿ����
	public String checkProcodeCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		//��ȡ��Ŀ��Ϣ����
		for (int j = 1; j < topList.size(); j++) {
			Object[] exobj0 = topList.get(j);
			
			//�û��������ֶ�����Ա������Ϣ�������
			String count="select count(*) from zl_projectcontrol_b where nvl(dr,0)=0 and pk_projectcontrol " +
					"in (select p.pk_projectcontrol from zl_projectcontrol p where nvl(p.dr,0)=0 and " +
					"p.pk_org=in (select o.pk_org from org_orgs o where nvl(dr,0)=0 and " +
					"o.code='"+getStgObj(exobj0[maph1.get(maph.get(0))])+"') ) and usercode = (select s.pk_base_doc " +
							"from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')";
			Integer count1=(Integer) iQ.executeQuery(count, new ColumnProcessor());
			String procontrol = "1=1";
			if(count1>0){
				procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
						"where nvl(c.dr,0)=0 and c.pk_projectcontrol in (select b.pk_projectcontrol from "+
						"zl_projectcontrol_b b where nvl(b.dr,0)=0 and b.usercode = " +
						"(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')))";
			}
			
			for (int i = btrow+1; i < bList.size(); i++) {
				Object[] exobj = bList.get(i);
				if (getStgObj(exobj[maph2.get(maph.get(9))]).equals(getStgObj(j + 1))) {
					String sql_pro = "select pk_project from zl_project where nvl(dr,0)=0 and pk_org in " +
							"(select o.pk_org from org_orgs o where nvl(dr,0)=0 and o.code='"+getStgObj(exobj0[maph1.get(maph.get(0))])+"') " +
									"and code='"+ getStgObj(exobj[maph1.get(maph.get(10))]) + "' and "+procontrol+"";
					Object pk_pro = iQ.executeQuery(sql_pro, new ColumnProcessor());
					if (getStgObj(pk_pro).equals("")) {
						error += (i + 1) + ",";
					}
				}
			}
		}
		return error;
	}

	/**
	 * ���ɵ��ݱ��
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<String> getVbillCode() throws Exception{
		
		List<String> billcodes=new ArrayList<String>();
		
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(
				IUAPQueryBS.class);
		String sql="select max(customercode) from zl_customer where nvl(dr,0)=0 and customercode like '"+getStgObj(topList.get(1)[maph1.get(maph.get(0))])+"ZL%'";
		Object sourceid=iQ.executeQuery(sql, new ColumnProcessor());
		String billcode=sourceid==null?"":sourceid.toString();
		for(int j=1;j<topList.size();j++){
			if(getStgObj(billcode).equals("")){
				billcode=getStgObj(topList.get(1)[maph1.get(maph.get(0))])+"ZL0001";
			}else{
				Integer length=billcode.length();
				Integer num=Integer.parseInt(billcode.substring(length-4,length))+1;
				String zero="";
				for(int i=0;i<4-num.toString().length();i++){
					zero+="0";
				}
				billcode=getStgObj(topList.get(1)[maph1.get(maph.get(0))])+"ZL"+zero+num;
			}
			billcodes.add(billcode);
		}
		
		return billcodes;
	}
	
	// �ַ�����װ
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public TangramContainer getTcr() {
		return tcr;
	}

	public void setTcr(TangramContainer tcr) {
		this.tcr = tcr;
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}
