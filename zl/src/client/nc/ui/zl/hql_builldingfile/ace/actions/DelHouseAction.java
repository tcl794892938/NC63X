package nc.ui.zl.hql_builldingfile.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.CheckDataPermissionUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.components.CommonConfirmDialogUtils;
import nc.ui.uif2.components.ITabbedPaneAwareComponent;
import nc.ui.uif2.model.IMultiRowSelectModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class DelHouseAction extends NCAction {
	private BillManageModel model;
	
    private ITabbedPaneAwareComponent singleBillView;
	
	private ISingleBillService<Object> singleBillService;
	
	private Object[] tempData = null;
	
	private boolean isScriptRunner = false; 
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}
	
	public ITabbedPaneAwareComponent getSingleBillView() {
		return singleBillView;
	}

	public void setSingleBillView(ITabbedPaneAwareComponent singleBillView) {
		this.singleBillView = singleBillView;
	}
	
	public ISingleBillService<Object> getSingleBillService() {
		return singleBillService;
	}

	public void setSingleBillService(ISingleBillService<Object> singleBillService) {
		this.singleBillService = singleBillService;
	}

	public DelHouseAction(){
		this.setCode("delhouse");
		this.setBtnName("删除全部房产");
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		Integer[] rows=model.getSelectedOperaRows();
		if(rows==null||rows.length==0||rows.length>1){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		if(!isDelete()){
			return;
		}
		
		Object obj=model.getSelectedData();
		AggBuildingfileVO vo=(AggBuildingfileVO)obj;
		//确定房产下都未推盘
		String sql="select count(1) from zl_housesource where buildname='"+vo.getParentVO().getPk_buildingfile()+"' "
				+ " and nvl(dr,0)=0 and housestate > 1 ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "该楼栋已有推盘房产，不允许删除！");
			return ;
		}
		
		HYPubBO_Client.deleteByWhereClause(HousesourceVO.class, " nvl(dr,0)=0 and buildname='"+vo.getParentVO().getPk_buildingfile()+"'");
		
		//插入完更新面积
		vo.getParentVO().setBuiltuparea(null);
		vo.getParentVO().setInnerarea(null);
		vo.getParentVO().setNproperty(null);
		vo.getParentVO().setIsbuild(new UFBoolean(false));
		HYPubBO_Client.update(vo.getParentVO());
		model.directlyUpdate(vo);
		//查询项目信息
		String sql1 = "select * from zl_project where pk_project = '"+vo.getParentVO().getPk_projectid()+"'";
		ProjectVO pvo = (ProjectVO) iQ.executeQuery(sql1, new BeanProcessor(ProjectVO.class));
		//查询总面积，总楼栋
		String sql2 = "select sum(builtuparea) builtuparea,count(pk_buildingfile) num from zl_buildingfile where " +
				      "pk_projectid = '"+pvo.getPk_project()+"' and nvl(dr,0)=0 and isbuild ='Y'";
		Map<String, Object> map = (Map<String, Object>) iQ.executeQuery(sql2, new MapProcessor());
		Object areaObj = map.get("builtuparea") == null ? 0 : map.get("builtuparea");
	    UFDouble area = new UFDouble(areaObj.toString());
		int num = (Integer) (map.get("num")==null ? 0 : map.get("num"));
		pvo.setNarea(area);
		pvo.setNfloor(num);
				/*//查询住宅
				String sql3 = "select count(*) from zl_housesource h left join zl_familyfile f on (h.pk_familyfile = " +
						      "f.pk_familyfile) left join zl_formattype t on (t.pk_formattype = f.pk_formattypeid) " +
						      "where substr(t.code,0,2)='04' and nvl(h.dr,0)= 0 and h.projectname = '"+pvo.getPk_project()+"'";
				int nhomeholds = (Integer) iQ.executeQuery(sql3, new ColumnProcessor());
				pvo.setNhomeholds(nhomeholds);
				//查询商铺
			    String sql4 = "select count(*) from zl_housesource h left join zl_familyfile f on (h.pk_familyfile = " +
					          "f.pk_familyfile) left join zl_formattype t on (t.pk_formattype = f.pk_formattypeid) " +
					          "where substr(t.code,0,2)='02' and nvl(h.dr,0)= 0 and h.projectname = '"+pvo.getPk_project()+"'";
			    int nshopholds = (Integer) iQ.executeQuery(sql4, new ColumnProcessor());
			    pvo.setNshopholds(nshopholds);
				//查询写字楼
			    String sql5 = "select count(*) from zl_housesource h left join zl_familyfile f on (h.pk_familyfile = " +
				          "f.pk_familyfile) left join zl_formattype t on (t.pk_formattype = f.pk_formattypeid) " +
				          "where substr(t.code,0,2)='01' and nvl(h.dr,0)= 0 and h.projectname = '"+pvo.getPk_project()+"'";
		        int nofficeholds = (Integer) iQ.executeQuery(sql5, new ColumnProcessor());
		        pvo.setNofficeholds(nofficeholds);
				//查询车位
		        String sql6 = "select count(*) from zl_housesource h left join zl_familyfile f on (h.pk_familyfile = " +
				          "f.pk_familyfile) left join zl_formattype t on (t.pk_formattype = f.pk_formattypeid) " +
				          "where substr(t.code,0,2)='03' and nvl(h.dr,0)= 0 and h.projectname = '"+pvo.getPk_project()+"'";
		        int ncarholds = (Integer) iQ.executeQuery(sql6, new ColumnProcessor());
		        pvo.setNcarholds(ncarholds);
		        pvo.setNholds(pvo.getNhomeholds()+pvo.getNshopholds()+pvo.getNofficeholds()+pvo.getNcarholds());*/
				String sql_count = "select t.code tcode from zl_housesource h left join" +
		                " zl_familyfile f on (h.pk_familyfile = f.pk_familyfile) left join " +
		                "zl_formattype t on (t.pk_formattype = f.pk_formattypeid) " +
		                "where nvl(h.dr,0)= 0 and h.projectname = '"+pvo.getPk_project()+"' and " +
		                "h.housestate <> 1 ";
		ArrayList<Object[]> ftcodeList = (ArrayList<Object[]>) iQ.executeQuery(sql_count, new ArrayListProcessor());
		String sql_f = "select code,name from zl_formattype where nvl(dr,0)=0 and name in ('住宅','商铺','写字楼','车位')";
		List<Map<String, Object>> flist = (List<Map<String, Object>>) iQ.executeQuery(sql_f, new MapListProcessor());
		Map<String, String> fmap = new HashMap<String, String>();
		for(int i = 0; i < flist.size();i++){
			fmap.put(flist.get(i).get("name").toString(),flist.get(i).get("code").toString());
		}
		int nhomeholds = 0;
		int nofficeholds = 0;
		int nshopholds = 0;
		int ncarholds = 0;
		for(int i = 0;i < ftcodeList.size();i++){
			
			String tcode = ftcodeList.get(i)[0] == null ? "" : ftcodeList.get(i)[0].toString();
			if(fmap.get("写字楼") != null && tcode.startsWith(fmap.get("写字楼"))){
				nofficeholds ++;
			}else if(fmap.get("商铺") != null && tcode.startsWith(fmap.get("商铺"))){
				nshopholds ++;
			}else if(fmap.get("车位") != null && tcode.startsWith(fmap.get("车位"))){
				ncarholds ++;
			}else if(fmap.get("住宅") != null && tcode.startsWith(fmap.get("住宅"))){
				nhomeholds ++;
			}
		}
		pvo.setNholds(ftcodeList.size());
		pvo.setNhomeholds(nhomeholds);
		pvo.setNshopholds(nshopholds);
		pvo.setNofficeholds(nofficeholds);
		pvo.setNcarholds(ncarholds);
		IVOPersistence ivp = NCLocator.getInstance().lookup(IVOPersistence.class);
		ivp.updateVO(pvo);
	}
	
	@Override
	protected boolean isActionEnable() {
		
		Object obj=model.getSelectedData();
		if(obj==null){
			return false;
		}
		AggBuildingfileVO vo=(AggBuildingfileVO)obj;
		UFBoolean ub=vo.getParentVO().getIsbuild();
		if(ub.booleanValue()==true){
			return true;
		}
		
		return false;
	}
	
	protected boolean isDelete() throws Exception {
		boolean ret = false;
		
		if ((this.getSingleBillView() != null) && this.getSingleBillView().isComponentVisible()) {
			// 卡片显示时，只删除当前选中的一行数据
			if (null != this.getModel().getSelectedData()) {
				tempData = new Object[] { this.getModel().getSelectedData() };
			}
		} else if (this.getModel() instanceof IMultiRowSelectModel) {
			tempData = ((IMultiRowSelectModel) this.getModel()).getSelectedOperaDatas();
		}
		
		checkDataPermission(tempData);// 数据权限校验
		
		if ((tempData != null) && (this.getSingleBillService() != null)) { 
			ret = UIDialog.ID_YES == CommonConfirmDialogUtils.showConfirmDeleteDialog(model.getContext().getEntranceUI());
			isScriptRunner = ret;
		}
		else {
			ret = false;
		}
		return ret;
	}
	
	/** 数据权限校验 **/
	protected void checkDataPermission(Object obj) throws Exception {
		Object[] objs = getUnDataPermissionData(obj);
		if (objs != null && objs.length > 0) {
			throw new BusinessException(
					IShowMsgConstant.getDataPermissionInfo());
		}
	}
	
	protected Object[] getUnDataPermissionData(Object obj) {
		return CheckDataPermissionUtil.getUnDataPermissionData(operateCode,
				mdOperateCode, resourceCode, model.getContext(), obj);
	}
	
	private String mdOperateCode = null; // 元数据操作编码
	private String operateCode = null; // 资源对象操作编码，以上两者注入其一，都不注入，则不进行数据权限控制。
	private String resourceCode = null; // 业务实体资源编码

	public String getMdOperateCode() {
		return mdOperateCode;
	}

	public void setMdOperateCode(String mdOperateCode) {
		this.mdOperateCode = mdOperateCode;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
}
