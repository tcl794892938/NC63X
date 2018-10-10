package nc.ui.zl.ld_housesource.ace.actions;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.pub.BusinessException;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;

public class SaveAction extends DifferentVOSaveAction {

	private ShowUpableBillForm billform;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		billform.getBillCardPanel().dataNotNullValidate();
		Object rnumber=billform.getBillCardPanel().getHeadItem("roomnumber").getValueObject();
		if(!isNumeric(getStgObj(rnumber))){
			MessageDialog.showErrorDlg(billform, "错误", "房号不能带有非数字型字符，请检查！");
			return;
		}
		
		
		super.doAction(e);
		
		//String pkld=aggvo.getParentVO().getBuildname();
		Object pkld=billform.getBillCardPanel().getHeadItem("buildname").getValueObject();
		//回写楼栋信息
		
		String sql="select floorn,nnum,buildarea,buildarea2 from" +
			"(select sum(e.buildarea) buildarea from zl_housesource e " +
			" where  buildname ='"+pkld+"' and nvl(dr,0)=0 and e.housestate=1)a ," +
			"(select sum(e.buildarea) buildarea2 from zl_housesource e " +
			" where  buildname ='"+pkld+"' and nvl(dr,0)=0 and e.housestate<>1)b," +
			"(select count(distinct e.floorn) floorn," +
			"count(1) nnum from zl_housesource e " +
			" where  buildname ='"+pkld+"' and nvl(dr,0)=0 )c ";
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Map<String, Object> map=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
		
		String sql2="select * from zl_buildingfile where pk_buildingfile='"+pkld+"'";
		BuildingfileVO fvo=(BuildingfileVO)iQ.executeQuery(sql2, new BeanProcessor(BuildingfileVO.class));
		if(fvo==null){
			throw new BusinessException("楼栋数据异常！");
		}
		fvo.setAttributeValue("nproperty", map.get("nnum"));
		fvo.setAttributeValue("nbuilding", map.get("floorn"));
		fvo.setAttributeValue("builtuparea", map.get("buildarea2"));
		fvo.setAttributeValue("personalarea", map.get("buildarea"));
		HYPubBO_Client.update(fvo);
		
		Object pk_xm=billform.getBillCardPanel().getHeadItem("projectname").getValueObject();
		//String pk_xm=aggvo.getParentVO().getProjectname();
		//回写项目信息
		String sql3="select * from (select sum(e.buildarea) buildarea from zl_housesource e where e.projectname='"+pk_xm+"' and nvl(dr,0)=0 and e.housestate<>1)a," +
				"(select count(distinct e.buildname) buildnum,count(1) nnum from zl_housesource e where e.projectname='"+pk_xm+"' and nvl(dr,0)=0 )b," +
				"(select count(1) nhomenum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '04%' )c," +
				"(select count(1) nshopnum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '02%' )d," +
				"(select count(1) nofficenum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '01%' )e," +
				"(select count(1) ncarnum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
				"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '03%' )f ";
		Map<String, Object> map2=(Map<String, Object>)iQ.executeQuery(sql3, new MapProcessor());
		
		String sql4="select * from zl_project where pk_project='"+pk_xm+"'";
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
		
		
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){  
			if (!Character.isDigit(str.charAt(i))){
		           return false;
		       }
	   }
	   return true;
	 }
	

}
