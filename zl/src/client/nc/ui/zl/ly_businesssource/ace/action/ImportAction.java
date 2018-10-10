package nc.ui.zl.ly_businesssource.ace.action;

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
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;

import nc.ui.zl.abs.tool.ExcelUtils;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;
import nc.vo.zl.ly_businesssource.BusinessBVO;
import nc.vo.zl.ly_businesssource.BusinessVO;

public class ImportAction extends NCAction {

	private static final long serialVersionUID = 8519431471345832207L;

	// 导入商源内容
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
		this.setBtnName("导入");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent arg0) throws Exception {

		// TODO 自动生成的方法存根
		// System.out.println("导入");

		Object[][] exlimp = ExcelUtils.doImport(this.tcr);

		if (getStgObj(exlimp).equals("")) {
			return;
		}

		// 将Excel中的值放到mapList中
		for (int i = 0; i < exlimp.length; i++) {
			bList.put(i, exlimp[i]);
		}

		BillCardPanel card=bill.getBillCardPanel();
		
		maph.put(0, card.getHeadItem("pk_org").getName());
		//map.put(1, card.getHeadItem("sourceid").getName());
		maph.put(1, card.getHeadItem("sourcename").getName());
		maph.put(2, card.getHeadItem("customertype").getName());
		maph.put(3, card.getHeadItem("phone").getName());
		maph.put(4, card.getHeadItem("address").getName());
		maph.put(5, card.getHeadItem("salesman").getName());
		maph.put(6, card.getHeadItem("idnumber").getName());
		maph.put(7, card.getHeadItem("businesslicense").getName());
		maph.put(8, "表头行号");
		maph.put(9, card.getBodyItem("procode").getName());
		maph.put(10, card.getBodyItem("remarks").getName());
		
		for(int j=0;j<bList.size();j++){
			Object[] exobj_0 = bList.get(j);
			for (int i = 0; i < exobj_0.length; i++) {
				if ("表头行号".equals(getStgObj(exobj_0[i]))) {
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
		
		//获取表头部分
		for(int i=0;i<btrow;i++){
			Object[] top=bList.get(i);
			if((top[maph1.get(maph.get(0))]==null||top[maph1.get(maph.get(0))].equals(""))
					&&(top[maph1.get(maph.get(1))]==null||top[maph1.get(maph.get(1))].equals(""))
					&&(top[maph1.get(maph.get(2))]==null||top[maph1.get(maph.get(2))].equals(""))
					&&(top[maph1.get(maph.get(3))]==null||top[maph1.get(maph.get(3))].equals(""))
					&&(top[maph1.get(maph.get(4))]==null||top[maph1.get(maph.get(4))].equals(""))
					&&(top[maph1.get(maph.get(5))]==null||top[maph1.get(maph.get(5))].equals(""))
					&&(top[maph1.get(maph.get(6))]==null||top[maph1.get(maph.get(6))].equals(""))
					&&(top[maph1.get(maph.get(7))]==null||top[maph1.get(maph.get(7))].equals(""))){
				continue;
			}
			topList.put(i, top);
		}
		
		if(topList.size()==1){
			MessageDialog.showHintDlg(tcr, "提示", "导入的表头无数据！");
			return;
		}

		if (null != exlimp) {

			// 格式校验
			boolean gsFag = checkExcelGS();
			if (!gsFag) {
				String errMsg = "导入的Excel格式不正确，缺少标题或标题写错！";
				MessageDialog.showHintDlg(tcr, "提示", errMsg);
				bList.clear();
				topList.clear();
				return;
			}

			// 检验必输项是否为空
			String bsFag = checkNotNull();
			if (!"".equals(bsFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + bsFag.substring(0, bsFag.length() - 1)
								+ "行所有必输项不能为空，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			//检验每条数据是否都有对应的项目编码
			String prodyFag=checkProcodeDy();
			if(!"".equals(prodyFag)){
				MessageDialog.showHintDlg(
						tcr,
						"提示",
						"Excel中第"
								+ prodyFag.substring(0, prodyFag.length() - 1)
								+ "行没有对应的"+maph.get(9)+"，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			//检验是否表体都有对应的表头数据
			String btdyFag=checkBTCz();
			if(!"".equals(btdyFag)){
				MessageDialog.showHintDlg(
						tcr,
						"提示",
						"Excel中第"
								+ btdyFag.substring(0, btdyFag.length() - 1)
								+ "行"+maph.get(9)+"没有对应的表头数据，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}

			// 检验组织编码是否存在
			String zzczFag = checkZzbmCz();
			if (!"".equals(zzczFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + zzczFag.substring(0, zzczFag.length() - 1)
								+ "行"+maph.get(0)+"不存在，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}

			// 检验客户类型数据库中是否存在
			String typeFag = checkTypeCz();
			if (!"".equals(typeFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + typeFag.substring(0, typeFag.length() - 1)
								+ "行"+maph.get(2)+"不存在或者不是档案最末级，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}

			// 检验置业顾问数据库中是否存在
			String gwFag = checkGwCz();
			if (!"".equals(gwFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + gwFag.substring(0, gwFag.length() - 1)
								+ "行"+maph.get(5)+"不存在，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			//检验身份证号Excel中是否有重复
			String sfcfFag=checkSFCf();
			if (!"".equals(sfcfFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + sfcfFag.substring(0, sfcfFag.length() - 1)
								+ "行"+maph.get(6)+"有重复，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			//检验身份证号数据库中是否存在
			String sfczFag=checkSFCz();
			if (!"".equals(sfczFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + sfczFag.substring(0, sfczFag.length() - 1)
								+ "行"+maph.get(6)+"已经被注册，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			//检验营业执照Excel中是否有重复
			String yycfFag=checkYYCf();
			if (!"".equals(yycfFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + yycfFag.substring(0, yycfFag.length() - 1)
								+ "行"+maph.get(7)+"有重复，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			//检验营业执照数据库中是否存在
			String yyczFag=checkYYCz();
			if (!"".equals(yyczFag)) {
				MessageDialog.showHintDlg(tcr, "提示",
						"Excel中第" + yyczFag.substring(0, yyczFag.length() - 1)
								+ "行"+maph.get(7)+"已经被注册，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}
			
			// 检验项目编码数据库中是否存在
			String proczFag = checkProcodeCz();
			if (!"".equals(proczFag)) {
				MessageDialog.showHintDlg(
						tcr,
						"提示",
						"Excel中第"
								+ proczFag.substring(0, proczFag.length() - 1)
								+ "行"+maph.get(9)+"不存在，请重新输入！");
				bList.clear();
				topList.clear();
				return;
			}

			// 数据库插入操作
			// 新建一个空商源信息VOlist
			List<BusinessVO> voList = new ArrayList<BusinessVO>();

			for (int i = 1; i < topList.size(); i++) {
				Object[] colobj = topList.get(i);
				if(colobj==null){
					continue;
				}
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(
						IUAPQueryBS.class);

				// NEW一个商源信息
				BusinessVO bvo = new BusinessVO();
				// 获取组织主键
				String sql = "select org_orgs.pk_org,org_orgs.pk_group,org_orgs.pk_vid from org_orgs where nvl(dr,0)=0 and org_orgs.code='"
						+ getStgObj(colobj[maph1.get(maph.get(0))]) + "'";
				Map<String, Object> map = (Map<String, Object>) iQ
						.executeQuery(sql, new MapProcessor());
				Object pk_org = map.get("pk_org");
				Object pk_group = map.get("pk_group");
				Object pk_org_v = map.get("pk_vid");
				bvo.setPk_org(getStgObj(pk_org));
				bvo.setPk_group(getStgObj(pk_group));
				bvo.setPk_org_v(getStgObj(pk_org_v));
				//bvo.setSourceid(getVbillCode());
				bvo.setSourcename(getStgObj(colobj[maph1.get(maph.get(1))]));
				// 获取客户类型主键
				String sql1 = "select pk_customertype from zl_customertype where nvl(dr,0)=0 and code='"
						+ getStgObj(colobj[maph1.get(maph.get(2))]) + "'";
				Object pk_cus = iQ.executeQuery(sql1, new ColumnProcessor());
				bvo.setCustomertype(getStgObj(pk_cus));
				bvo.setPhone(getStgObj(colobj[maph1.get(maph.get(3))]));
				bvo.setAddress(getStgObj(colobj[maph1.get(maph.get(4))]));
				// 获取人员信息主键
				String sql2 = "select pk_psndoc from bd_psndoc where nvl(dr,0)=0 and code='"
						+ getStgObj(colobj[maph1.get(maph.get(5))]) + "'";
				Object pk_psndoc = iQ.executeQuery(sql2, new ColumnProcessor());
				bvo.setSalesman(getStgObj(pk_psndoc));
				bvo.setIdnumber(getStgObj(colobj[maph1.get(maph.get(6))]));
				bvo.setBusinesslicense(getStgObj(colobj[maph1.get(maph.get(7))]));
				bvo.setDbilldate(AppContext.getInstance().getBusiDate());
				bvo.setCreator(AppContext.getInstance().getPkUser());
				bvo.setCreationtime(AppContext.getInstance().getServerTime());
				voList.add(bvo);

			}
			
			for (int i=1;i<=voList.size();i++) {
				voList.get(i-1).setSourceid(getVbillCode().get(i-1));
			}

			IVOPersistence ivp = NCLocator.getInstance().lookup(
					IVOPersistence.class);
			List<BusinessBVO> bbvolist = new ArrayList<BusinessBVO>();
			Object[] pk_b = ivp.insertVOList(voList);
			for (int i = 0; i < pk_b.length; i++) {
				for (int j = btrow+1; j < bList.size(); j++) {
					Object[] colobj = bList.get(j);
					IUAPQueryBS iQ = NCLocator.getInstance().lookup(
							IUAPQueryBS.class);

					if (getStgObj(colobj[maph2.get(maph.get(8))]).equals(getStgObj(i + 2))) {
						BusinessBVO bbvo = new BusinessBVO();
						bbvo.setPk_bussiness(getStgObj(pk_b[i]));
						String sql3 = "select pk_project,name from zl_project where nvl(dr,0)=0 and code='"
								+ getStgObj(colobj[maph2.get(maph.get(9))]) + "'";
						Map<String, Object> map = (Map<String, Object>) iQ
								.executeQuery(sql3, new MapProcessor());
						Object pk_project = map.get("pk_project");
						Object proname = map.get("name");
						bbvo.setProcode(getStgObj(pk_project));
						bbvo.setProname(getStgObj(proname));
						bbvo.setRemarks(getStgObj(colobj[maph2.get(maph.get(10))]));
						bbvolist.add(bbvo);
					}
				}
			}
			ivp.insertVOList(bbvolist);
			MessageDialog.showHintDlg(tcr, "提示", "导入成功！");
			bList.clear();
			topList.clear();
		}

	}

	// 校验格式是否正确，是否缺少表头字段
	public boolean checkExcelGS() {
		// 将标题放到map中
		Object[] exob = bList.get(0);
		Object[] exobj2 = bList.get(btrow);
		int count = 0;
		for (int i = 0; i < exob.length; i++) {
			if (maph.get(0).equals(getStgObj(exob[i]))
					|| maph.get(1).equals(getStgObj(exob[i]))
					|| maph.get(2).equals(getStgObj(exob[i]))
					|| maph.get(3).equals(getStgObj(exob[i]))
					|| maph.get(4).equals(getStgObj(exob[i]))
					|| maph.get(5).equals(getStgObj(exob[i]))
					|| maph.get(6).equals(getStgObj(exob[i]))
					|| maph.get(7).equals(getStgObj(exob[i]))) {
				count++;
			}
		}
		for (int i = 0; i < exobj2.length; i++) {
			if(maph.get(8).equals(getStgObj(exobj2[i]))
					|| maph.get(9).equals(getStgObj(exobj2[i]))
					|| maph.get(10).equals(getStgObj(exobj2[i]))){
				count++;
			}
		}
		if (count < 11) {
			return false;
		}
		return true;
	}

	// 必输项不为空
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
		for (int i = btrow+1; i < bList.size(); i++) {
			Object[] exobj = bList.get(i);
			if (getStgObj(exobj[maph2.get(maph.get(9))]) == null || getStgObj(exobj[maph2.get(maph.get(9))]).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}

	// 检查组织编码是否存在
	public String checkZzbmCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < topList.size(); i++) {
			Object[] exobj = topList.get(i);
			// 获取组织主键
			String get_pk_org = "select org_orgs.pk_org from org_orgs where nvl(dr,0)=0 and org_orgs.code='"
					+ getStgObj(exobj[maph1.get(maph.get(0))]) + "'";
			Object pk_org = iQ.executeQuery(get_pk_org, new ColumnProcessor());
			if (getStgObj(pk_org).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}


	// 检查客户类型是否存在
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
	
	// 检查身份证号Excel是否重复
		public String checkSFCf() {
			String error = "";
			for (int i = 1; i < topList.size(); i++) {
				Object[] exobj = topList.get(i);
				if(getStgObj(exobj[maph1.get(maph.get(6))]).equals("")){
					continue;
				}
				for (int j = i + 1; j < bList.size(); j++) {
					Object[] exobj1 = bList.get(j);
					if (getStgObj(exobj[maph1.get(maph.get(6))]).equals(getStgObj(exobj1[maph1.get(maph.get(6))]))) {
						error += (i + 1) + ",";
					}
				}
			}
			return error;
		}

		// 检查身份证号数据库是否存在
		public String checkSFCz() throws Exception {
			String error = "";
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			for (int i = 1; i < topList.size(); i++) {
				Object[] exobj = topList.get(i);
				if(getStgObj(exobj[maph1.get(maph.get(6))]).equals("")){
					continue;
				}
				String get_code = "select pk_bussiness from zl_business where nvl(dr,0)=0 and idnumber='"
						+ getStgObj(exobj[maph1.get(maph.get(6))]) + "'";
				Object getcode = iQ.executeQuery(get_code, new ColumnProcessor());
				if (!getStgObj(getcode).equals("")) {
					error += (i + 1) + ",";
				}
			}
			return error;
		}

	// 检查置业顾问是否存在
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
	
	//检查营业执照Excel是否重复
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
	
	//检查营业执照数据库是否有重复
	public String checkYYCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for (int i = 1; i < topList.size(); i++) {
			Object[] exobj = topList.get(i);
			if(getStgObj(exobj[maph1.get(maph.get(7))]).equals("")){
				continue;
			}
			String get_code = "select pk_bussiness from zl_business where nvl(dr,0)=0 and businesslicense='"
					+ getStgObj(exobj[maph1.get(maph.get(7))]) + "'";
			Object getcode = iQ.executeQuery(get_code, new ColumnProcessor());
			if (!getStgObj(getcode).equals("")) {
				error += (i + 1) + ",";
			}
		}
		return error;
	}
	
	//检查每条数据是否都有对应的项目编码
		public String checkProcodeDy(){
			String error="";
			for(int i=1;i<topList.size();i++){
				Integer count=0;
				for(int j=btrow+1;j<bList.size();j++){
					Object[] exobj=bList.get(j);
					if(getStgObj(exobj[maph2.get(maph.get(8))]).equals(getStgObj(i+1))){
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
	
	//检查行号是否有对应表头
	public String checkBTCz() throws Exception{
		String error="";
		for(int i=btrow+1;i<bList.size();i++){
			Object[] exobj=bList.get(i);
			Integer count=0;
			for(int j=1;j<topList.size();j++){
				if(getStgObj(exobj[maph2.get(maph.get(8))]).equals(getStgObj(j+1))){
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

	// 检查项目编码是否存在
	public String checkProcodeCz() throws Exception {
		String error = "";
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		//获取项目信息主键
		for (int j = 1; j < topList.size(); j++) {
			Object[] exobj0 = topList.get(j);
			
			//用户表的身份字段是人员基本信息表的主键
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
				if (getStgObj(exobj[maph2.get(maph.get(8))]).equals(getStgObj(j + 1))) {
					String sql_pro = "select pk_project from zl_project where nvl(dr,0)=0 and pk_org in " +
							"(select o.pk_org from org_orgs o where nvl(dr,0)=0 and o.code='"+getStgObj(exobj0[maph1.get(maph.get(0))])+"') " +
									"and code='"+ getStgObj(exobj[maph1.get(maph.get(9))]) + "' and "+procontrol+"";
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
	 * 生成单据编号
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<String> getVbillCode() throws Exception{
		
		List<String> billcodes=new ArrayList<String>();
		
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(
				IUAPQueryBS.class);
		String sql="select max(sourceid) from zl_business where nvl(dr,0)=0 and sourceid like '"+getStgObj(bList.get(1)[maph1.get(maph.get(0))])+"ZL%'";
		Object sourceid=iQ.executeQuery(sql, new ColumnProcessor());
		String billcode=sourceid==null?"":sourceid.toString();
		for(int j=1;j<topList.size();j++){
			if(getStgObj(billcode).equals("")){
				billcode=getStgObj(bList.get(1)[maph1.get(maph.get(0))])+"ZL0001";
			}else{
				Integer length=billcode.length();
				Integer num=Integer.parseInt(billcode.substring(length-4,length))+1;
				String zero="";
				for(int i=0;i<4-num.toString().length();i++){
					zero+="0";
				}
				billcode=getStgObj(bList.get(1)[maph1.get(maph.get(0))])+"ZL"+zero+num;
			}
			billcodes.add(billcode);
		}
		
		return billcodes;
	}
	
	// 字符串封装
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
