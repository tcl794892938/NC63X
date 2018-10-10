package nc.ui.zl.ly_sgmoney.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.ILy_sgmoneyMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;

import nc.ui.zl.abs.tool.ExcelUtils;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.zl.ly_sgmoney.SgMoneyVO;

public class ImportAction extends NCAction {

	private static final long serialVersionUID = 8519431471345832207L;

	// ����Excel����
	Map<Integer, Object[]> mList = new HashMap<Integer, Object[]>();

	Map<Integer, String> maph=new HashMap<Integer, String>();
	Map<String,Integer> maph1=new HashMap<String, Integer>();
	private ShowUpableBillForm bill;
	private TangramContainer tcr;

	private LoginContext context;
	
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

		if(exlimp.length==1){
			MessageDialog.showHintDlg(tcr, "��ʾ", "�������һ�����ĵ���");
			return;
		}
		
		// ��Excel�е�ֵ�ŵ�mapList��
		for (int i = 0; i < exlimp.length; i++) {
			mList.put(i, exlimp[i]);
		}

		BillCardPanel card=bill.getBillCardPanel();
		
		maph.put(0, card.getHeadItem("pk_org").getName());
		maph.put(1, card.getHeadItem("pk_project").getName());
		maph.put(2, card.getHeadItem("payproject").getName());
		maph.put(3, card.getHeadItem("khname").getName());
		maph.put(4, card.getHeadItem("fcname").getName());
		maph.put(5, card.getHeadItem("restmoney").getName());
		
		Object[] exobj0=mList.get(0);
		for(int i=0;i<exobj0.length;i++){
			maph1.put(getStgObj(exobj0[i]), i);
		}

		if (null != exlimp) {

			// ��ʽУ��
			boolean gsFag = checkExcelGS(exlimp[0]);
			if (!gsFag) {
				String errMsg = "�����Excel��ʽ����ȷ��ȱ�ٱ�������д��";
				MessageDialog.showHintDlg(tcr, "��ʾ", errMsg);
				mList.clear();
				return;
			}

			// ����������Ƿ�Ϊ��
			String bsFag = checkNotNull();
			if (!"".equals(bsFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + bsFag.substring(0, bsFag.length() - 1)
								+ "�����б������Ϊ�գ����������룡");
				mList.clear();
				return;
			}
			
			//���Excel�Ƿ����������ظ�
			String cfFag = checkCf();
			if (!"".equals(cfFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + cfFag.substring(0, cfFag.length() - 1)
						+ "���������ظ������������룡");
				mList.clear();
				return;
			}

			// ������֯�����Ƿ����
			String zzczFag = checkZzbmCz();
			if (!"".equals(zzczFag)) {
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + zzczFag.substring(0, zzczFag.length() - 1)
								+ "����֯���벻���ڣ����������룡");
				mList.clear();
				return;
			}
			
			// �����Ŀ��Ϣ�Ƿ����
			String proFag=checkProCz();
			if(!"".equals(proFag)){
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + proFag.substring(0, proFag.length() - 1)
								+ "����Ŀ��Ϣ��Ų����ڣ����������룡");
				mList.clear();
				return;
			}
			
			// ����շ���Ŀ�Ƿ����
			String costFag=checkCostCz();
			if(!"".equals(costFag)){
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + costFag.substring(0, costFag.length() - 1)
								+ "���շ���Ŀ��Ų����ڻ��߲��ǵ�����ĩ�������������룡");
				mList.clear();
				return;
			}
			
			// ��鷿�������Ƿ����
			String fcFag=checkFcCz();
			if(!"".equals(fcFag)){
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + fcFag.substring(0, fcFag.length() - 1)
						+ "�пͷ������Ʊ�Ų����ڣ����������룡");
				mList.clear();
				return;
			}
			
			// ���ͻ������Ƿ����
			String khFag=checkKhCz();
			if(!"".equals(khFag)){
				MessageDialog.showHintDlg(tcr, "��ʾ",
						"Excel�е�" + khFag.substring(0, khFag.length() - 1)
								+ "�пͻ����Ʊ���뷿�����Ʊ�Ų���Ӧ�������������룡");
				mList.clear();
				return;
			}

			// ���ݿ�������
			// �½�һ�����ڳ�Ԥ�ɿ���ϢVOlist
			ILy_sgmoneyMaintain ly=NCLocator.getInstance().lookup(ILy_sgmoneyMaintain.class);
			List<AggSgMoneyVO> aggslist = new ArrayList<AggSgMoneyVO>();
			for(int i=1;i<mList.size();i++){
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(
						IUAPQueryBS.class);
				Object[] exobj=mList.get(i);
				// NEWһ���ڳ�Ԥ�ɿ�
				SgMoneyVO vo = new SgMoneyVO();
				// ��ȡ��֯����
				String sql = "select org_orgs.pk_org,org_orgs.pk_group,org_orgs.pk_vid from org_orgs where nvl(dr,0)=0 and org_orgs.code='"
						+ getStgObj(exobj[maph1.get(maph.get(0))]) + "'";
				Map<String, Object> map = (Map<String, Object>) iQ
						.executeQuery(sql, new MapProcessor());
				Object pk_org = map.get("pk_org");
				Object pk_group = map.get("pk_group");
				Object pk_org_v = map.get("pk_vid");
				vo.setPk_org(getStgObj(pk_org));
				vo.setPk_group(getStgObj(pk_group));
				vo.setPk_org_v(getStgObj(pk_org_v));
				//vo.setListid(getVbillCode(i-1));
				
				//��ȡ��Ŀ��Ϣ����
				String sql_pro = "select pk_project from zl_project where nvl(dr,0)=0 and code='"+ getStgObj(exobj[maph1.get(maph.get(1))]) + "'";
				Object pk_pro = iQ.executeQuery(sql_pro, new ColumnProcessor());
				vo.setPk_project(getStgObj(pk_pro));
				
				//��ȡ�շ���Ŀ����
				String sql_cost="select pk_costproject from zl_costproject where nvl(dr,0)=0 and code='"+getStgObj(exobj[maph1.get(maph.get(2))])+"'";
				Object pk_cost=iQ.executeQuery(sql_cost, new ColumnProcessor());
				vo.setPayproject(getStgObj(pk_cost));
				
				//��ȡ�ͻ���Ϣ����
				String sql_cus="select pk_customer from zl_customer where nvl(dr,0)=0 and customercode='"+getStgObj(exobj[maph1.get(maph.get(3))])+"'";
				Object pk_cus=iQ.executeQuery(sql_cus, new ColumnProcessor());
				vo.setKhname(getStgObj(pk_cus));
				
				//��ȡ������Ϣ����
				String sql_house="select pk_housesource from zl_housesource where nvl(dr,0)=0 and estatecode='"+getStgObj(exobj[maph1.get(maph.get(4))])+"'";
				Object pk_house=iQ.executeQuery(sql_house, new ColumnProcessor());
				vo.setFcname(getStgObj(pk_house));
				
				vo.setRestmoney(new UFDouble(getStgObj(exobj[maph1.get(maph.get(5))])));
				vo.setListstate(-1);
				
				String gettypeid="select pk_billtypeid from bd_billtype where pk_billtypecode='H610'";
				Object pk_billtype=iQ.executeQuery(gettypeid, new ColumnProcessor());
				vo.setPk_billtype(getStgObj(pk_billtype));
				vo.setBilltypecode("H610");
				vo.setDbilldate(AppContext.getInstance().getBusiDate());
				vo.setCreator(AppContext.getInstance().getPkUser());
				vo.setCreationtime(AppContext.getInstance().getServerTime());
				
				AggSgMoneyVO aggvo=new AggSgMoneyVO();
				aggvo.setParentVO(vo);
				aggslist.add(aggvo);
			}
			
			ly.insert(aggslist.toArray(new AggSgMoneyVO[aggslist.size()]), null);
			MessageDialog.showHintDlg(tcr, "��ʾ", "����ɹ���");
			mList.clear();
		}
	}

	// У���ʽ�Ƿ���ȷ���Ƿ�ȱ�ٱ�ͷ�ֶ�
	public boolean checkExcelGS(Object[] exobj) {
		int exlength = exobj.length;
		// ������ŵ�map��
		Object[] exob = mList.get(0);
		int count = 0;
		for (int i = 0; i < exlength; i++) {
			if (maph.get(0).equals(getStgObj(exob[i]))
					|| maph.get(1).equals(getStgObj(exob[i]))
					|| maph.get(2).equals(getStgObj(exob[i]))
					|| maph.get(3).equals(getStgObj(exob[i]))
					|| maph.get(4).equals(getStgObj(exob[i]))
					|| maph.get(5).equals(getStgObj(exob[i]))){
				count++;
			}
		}
		if (count < 6) {
			return false;
		}
		return true;
	}

	// �����Ϊ��
	public String checkNotNull() {
		String error = "";
		for (int i = 1; i < mList.size(); i++) {
			Object[] exobj = mList.get(i);
			if (getStgObj(exobj[maph1.get(maph.get(0))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(0))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(1))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(1))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(2))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(2))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(3))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(3))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(4))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(4))]).equals("")
					|| getStgObj(exobj[maph1.get(maph.get(5))]) == null
					|| getStgObj(exobj[maph1.get(maph.get(5))]).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}
	
	// ���Excel�Ƿ����������ظ�
	public String checkCf() {
		String error = "";
		for (int i = 1; i < mList.size()-1; i++) {
			Object[] exobj = mList.get(i);
			for (int j = i + 1; j < mList.size(); j++) {
				Object[] exobj1 = mList.get(j);
				if (exobj.equals(exobj1)) {
					error += (i + 1) + "�к�" + (j + 1) + "��,";
				}
			}
		}
		return error;
	}

	// �����֯�����Ƿ����
	public String checkZzbmCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < mList.size(); i++) {
			Object[] exobj = mList.get(i);
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
	
	// �����Ŀ��Ϣ�Ƿ����
	public String checkProCz() throws Exception{
		String error="";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < mList.size(); i++) {
			Object[] exobj = mList.get(i);
			//��ȡ��Ŀ��Ϣ����
			//�û��������ֶ�����Ա������Ϣ�������
			String count="select count(*) from zl_projectcontrol_b where nvl(dr,0)=0 and pk_projectcontrol " +
					"in (select p.pk_projectcontrol from zl_projectcontrol p where nvl(p.dr,0)=0 and " +
					"p.pk_org=in (select o.pk_org from org_orgs o where nvl(dr,0)=0 and " +
					"o.code='"+getStgObj(exobj[maph1.get(maph.get(0))])+"') ) and usercode = (select s.pk_base_doc " +
							"from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')";
			Integer count1=(Integer) iQ.executeQuery(count, new ColumnProcessor());
			String procontrol = "1=1";
			if(count1>0){
				procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
						"where nvl(c.dr,0)=0 and c.pk_projectcontrol in (select b.pk_projectcontrol from "+
						"zl_projectcontrol_b b where nvl(b.dr,0)=0 and b.usercode = " +
						"(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')))";
			}
			String sql_pro = "select pk_project from zl_project where nvl(dr,0)=0 and pk_org in " +
					"(select o.pk_org from org_orgs o where nvl(dr,0)=0 and o.code='"+getStgObj(exobj[maph1.get(maph.get(0))])+"') " +
							"and code='"+ getStgObj(exobj[maph1.get(maph.get(1))]) + "' and "+procontrol+"";
			Object pk_pro = iQ.executeQuery(sql_pro, new ColumnProcessor());
			if (getStgObj(pk_pro).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}
	
	// ����շ���Ŀ�Ƿ����
	public String checkCostCz() throws Exception{
		String error="";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < mList.size(); i++) {
			Object[] exobj = mList.get(i);
			//��ȡ�շ���Ŀ����
			String sql_cost="select a.pk_costproject from zl_costproject a where nvl(a.dr,0)=0 and " +
					"a.code='"+getStgObj(exobj[maph1.get(maph.get(2))])+"' and (a.pk_org='"+getStgObj(exobj[maph1.get(maph.get(0))])+"' " +
							"or nvl(a.vdef1,0)=0) and not exists (select c.pk_costproject from zl_costproject c where nvl(c.dr,0)=0 and " +
							"c.pk_vid=a.pk_costproject)";
			Object pk_cost=iQ.executeQuery(sql_cost, new ColumnProcessor());
			if (getStgObj(pk_cost).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}
	
	// ���ͻ������Ƿ����
	public String checkKhCz() throws Exception{
		String error="";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < mList.size(); i++) {
			Object[] exobj = mList.get(i);
			//��ȡ�ͻ���Ϣ����
			String sql_cus="select customercode from zl_customer where nvl(dr,0)=0 and "+
							"pk_customer=(select pk_customer from zl_customer_fcxx where nvl(dr,0)=0 and "+
							"fcname in (select pk_housesource from zl_housesource where nvl(dr,0)=0 and estatecode='"+getStgObj(exobj[maph1.get(maph.get(4))])+"'))";
			Object code=iQ.executeQuery(sql_cus, new ColumnProcessor());
			if (!getStgObj(code).equals(getStgObj(exobj[maph1.get(maph.get(3))]))) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}
	
	// ��鷿�������Ƿ����
	public String checkFcCz() throws Exception{
		String error="";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < mList.size(); i++) {
			Object[] exobj = mList.get(i);
			//��ȡ������Ϣ����
			String sql_house="select pk_housesource from zl_housesource where nvl(dr,0)=0 and estatecode='"+getStgObj(exobj[maph1.get(maph.get(4))])+"'";
			Object pk_house=iQ.executeQuery(sql_house, new ColumnProcessor());
			if (getStgObj(pk_house).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
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
