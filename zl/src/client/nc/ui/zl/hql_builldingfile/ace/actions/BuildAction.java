package nc.ui.zl.hql_builldingfile.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zl.IHql_builldingfileMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.md.data.access.NCObject;
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
import nc.ui.zl.hql_builldingfile.ace.config.BuildStep1Dlg;
import nc.ui.zl.hql_builldingfile.ace.config.BuildStep3Dlg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class BuildAction extends NCAction {
	
	private BillManageModel model;
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		Integer[] rows=model.getSelectedOperaRows();
		if(rows==null||rows.length==0||rows.length>1){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		Object obj=model.getSelectedData();
		AggBuildingfileVO vo=(AggBuildingfileVO)obj;
		//预留生成房源信息的过程（第一步，确定单元，楼层，每层户数）
		BuildStep3Dlg.setVos(null);
		new BuildStep1Dlg(model.getContext().getEntranceUI(),vo.getParentVO());
		HousesourceVO[] vos=BuildStep3Dlg.getVos();
		if(vos==null||vos.length<=0){
			return ;
		}
		HYPubBO_Client.insertAry(vos);
		//插入完更新面积
		IHql_builldingfileMaintain ibm=NCLocator.getInstance().lookup(IHql_builldingfileMaintain.class);
		BuildingfileVO bvo=ibm.updateAndRewrite(vo.getParentVO().getPk_buildingfile());
		vo.getParentVO().setBuiltuparea(bvo.getBuiltuparea());
		vo.getParentVO().setInnerarea(bvo.getInnerarea());
		vo.getParentVO().setNproperty(vos.length);
		vo.getParentVO().setIsbuild(new UFBoolean(true));
		HYPubBO_Client.update(vo.getParentVO());
		model.directlyUpdate(vo);
		//查询项目信息
		String sql = "select * from zl_project where pk_project = '"+vo.getParentVO().getPk_projectid()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		ProjectVO pvo = (ProjectVO) iQ.executeQuery(sql, new BeanProcessor(ProjectVO.class));
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

	public BuildAction() {
		//super();
		// TODO 自动生成的构造函数存根
		this.setBtnName("建房");
		this.setCode("buildaction");
	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		Object obj = this.getModel().getSelectedData();
		if(obj == null){
			return false;
		}
		AggBuildingfileVO vo=(AggBuildingfileVO)obj;
		UFBoolean ub=vo.getParentVO().getIsbuild();
		if(ub.booleanValue()==false){
			return true;
		}
		
		return false;
	}
	
}
