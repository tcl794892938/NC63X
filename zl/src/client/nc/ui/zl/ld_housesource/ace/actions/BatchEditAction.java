package nc.ui.zl.ld_housesource.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.zl.ld_housesource.ace.config.BatchEditDlg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.zl.ld_formattype.FormattypeVO;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class BatchEditAction extends NCAction {

	private static final long serialVersionUID = 2621943746117790578L;
	private BillManageModel model;
	
	public BatchEditAction(){
		this.setCode("batchedit");
		this.setBtnName("批改");
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object[] obj=getModel().getSelectedOperaDatas();
		AggHousesourceVO[] aggvos=new AggHousesourceVO[obj.length];
		for(int i=0;i<obj.length;i++){
			
			
			AggHousesourceVO vo=(AggHousesourceVO) obj[i];
			
			aggvos[i]=vo;
		}
		
		String projectname=aggvos[0].getParentVO().getProjectname();
		String pk_building=aggvos[0].getParentVO().getBuildname();
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("XM"+projectname, projectname);
		map.put("LD"+pk_building, pk_building);
		
		for(AggHousesourceVO vo : aggvos){
			
			if(!map.containsKey("XM"+vo.getParentVO().getProjectname())){
				MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "包含不同项目，不允许批改！");
				return ;
			}
			if(!map.containsKey("LD"+vo.getParentVO().getBuildname())){
				MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "包含不同楼栋，不允许批改！");
				return ;
			}
			if(vo.getParentVO().getHousestate().equals(2)){
				MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "包含定租状态房源，不允许批改！");
				return ;
			}
			if(vo.getParentVO().getHousestate().equals(3)){
				MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "包含已租状态房源，不允许批改！");
				return ;
			}
		}
		
		//批改弹出框
		BatchEditDlg dlg=new BatchEditDlg(model.getContext().getEntranceUI(),projectname,aggvos);
		if(!dlg.isFlag()){
			return ;
		}
		
		HousesourceVO hvo=dlg.getVo();
		List<HousesourceVO> v=new ArrayList<HousesourceVO>();
		int f = 0;
		if(aggvos.length == 1 && hvo.getEstatecode() != null){
			String sql_code = "select count(*) from zl_housesource where nvl(dr,0)=0 and pk_housesource != '"+
		                      aggvos[0].getParentVO().getPk_housesource()+"' and projectname = '"+
					          aggvos[0].getParentVO().getProjectname()+"' and buildname = '"+
		                      aggvos[0].getParentVO().getBuildname()+"' and estatecode = '"+
					          hvo.getEstatecode()+"'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			int codenum = (Integer) iQ.executeQuery(sql_code, new ColumnProcessor());
			if(codenum > 0){
				MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "编码已存在，不允许批改！");
				return;
			}
		}
		
		for(AggHousesourceVO vo : aggvos){
			
			if(hvo.getPk_familyfile()!=null){
				vo.getParentVO().setPk_familyfile(hvo.getPk_familyfile());
			}
			if(hvo.getBuildarea()!=null){
				vo.getParentVO().setBuildarea(hvo.getBuildarea());
			}
			if(hvo.getInnerarea()!=null){
				vo.getParentVO().setInnerarea(hvo.getInnerarea());
			}
			if(hvo.getEstatecode()!=null){
				vo.getParentVO().setEstatecode(hvo.getEstatecode());
			}
			if(hvo.getEstatename()!=null){
				vo.getParentVO().setEstatename(hvo.getEstatename());
			}
			if(hvo.getHousestate()!=null){
				if(vo.getParentVO().getHousestate() == 2 ||vo.getParentVO().getHousestate() == 3){
					f = 1;
					continue;
				}
				vo.getParentVO().setHousestate(hvo.getHousestate());
			}
			v.add(vo.getParentVO());
		}
		/*if(f == 1){
			//MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "房产状态为定租或者已租，不批改成"+hvo.getHousestate()+"。");
			
		}*/
		HYPubBO_Client.updateAry(v.toArray(new HousesourceVO[0]));

		
		//回写楼栋信息
		
		String sql="select floorn,nnum,buildarea,buildarea2 from" +
			"(select sum(e.buildarea) buildarea from zl_housesource e " +
			" where  buildname ='"+pk_building+"' and nvl(dr,0)=0 and e.housestate=1)a ," +
			"(select sum(e.buildarea) buildarea2 from zl_housesource e " +
			" where  buildname ='"+pk_building+"' and nvl(dr,0)=0 and e.housestate<>1)b," +
			"(select count(distinct e.floorn) floorn," +
			"count(1) nnum from zl_housesource e " +
			" where  buildname ='"+pk_building+"' and nvl(dr,0)=0 )c ";
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Map<String, Object> map1=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
		
		String sql2="select * from zl_buildingfile where pk_buildingfile='"+pk_building+"'";
		BuildingfileVO fvo=(BuildingfileVO)iQ.executeQuery(sql2, new BeanProcessor(BuildingfileVO.class));
		if(fvo==null){
			throw new BusinessException("楼栋数据异常！");
		}
		fvo.setAttributeValue("nproperty", map1.get("nnum"));
		fvo.setAttributeValue("nbuilding", map1.get("floorn"));
		fvo.setAttributeValue("builtuparea", map1.get("buildarea2"));
		fvo.setAttributeValue("personalarea", map1.get("buildarea"));
		HYPubBO_Client.update(fvo);
		
		//String pk_xm=aggvo.getParentVO().getProjectname();
		//回写项目信息
		String sql3="select * from (select sum(e.buildarea) buildarea from zl_housesource e where e.projectname='"+projectname+"' and nvl(dr,0)=0 and e.housestate<>1)a," +
				"(select count(distinct e.buildname) buildnum,count(1) nnum from zl_housesource e where e.projectname='"+projectname+"' and nvl(dr,0)=0 )b," +
				"(select count(1) nhomenum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+projectname+"' and nvl(e.dr,0)=0 and g.code like '04%' )c," +
				"(select count(1) nshopnum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+projectname+"' and nvl(e.dr,0)=0 and g.code like '02%' )d," +
				"(select count(1) nofficenum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+projectname+"' and nvl(e.dr,0)=0 and g.code like '01%' )e," +
				"(select count(1) ncarnum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+projectname+"' and nvl(e.dr,0)=0 and g.code like '03%' )f ";
		Map<String, Object> map2=(Map<String, Object>)iQ.executeQuery(sql3, new MapProcessor());
		
		String sql4="select * from zl_project where pk_project='"+projectname+"'";
		ProjectVO pvo=(ProjectVO)iQ.executeQuery(sql4, new BeanProcessor(ProjectVO.class));
		if(pvo==null){
			throw new BusinessException("项目数据异常！");
		}
		pvo.setAttributeValue("narea", map2.get("buildarea"));
		pvo.setAttributeValue("nfloor", map2.get("buildnum"));
		pvo.setAttributeValue("nholds", map2.get("nnum"));
		pvo.setAttributeValue("nhomeholds", map2.get("nhomenum"));
		pvo.setAttributeValue("nshopholds", map2.get("nshopnum"));
		pvo.setAttributeValue("nofficeholds", map2.get("nofficenum"));
		pvo.setAttributeValue("ncarholds", map2.get("ncarnum"));
		HYPubBO_Client.update(pvo);

		MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "批改完成！");
		
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}
	//判断字符串是否为空
	public UFDouble getStr(Object str){
		return str==null?new UFDouble(0):new UFDouble(str.toString());
	}
	@Override
	protected boolean isActionEnable() {
		
		Object[] obj=getModel().getSelectedOperaDatas();
		if(obj==null){
			return false;
		}
		return true;
	}
	
}
