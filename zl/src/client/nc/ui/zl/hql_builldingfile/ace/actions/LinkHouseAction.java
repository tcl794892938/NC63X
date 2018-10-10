package nc.ui.zl.hql_builldingfile.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class LinkHouseAction extends NCAction {
	private BillManageModel model;
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public LinkHouseAction(){
		this.setCode("linkhouse");
		this.setBtnName("房源信息");
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
		
		String sql="select * from  zl_housesource where nvl(dr,0)=0 and buildname='"+vo.getParentVO().getPk_buildingfile()+"' "
				+ "order by to_number(floorn) desc,to_number(unit) asc,to_number(roomnumber) desc";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<HousesourceVO> list=(List<HousesourceVO>)iQ.executeQuery(sql, new BeanListProcessor(HousesourceVO.class));
		List<AggHousesourceVO> aggvos=new ArrayList<AggHousesourceVO>();
		for(HousesourceVO v : list ){
			AggHousesourceVO aggvo=new AggHousesourceVO();
			aggvo.setParentVO(v);
			aggvos.add(aggvo);
		}
		
		SFClientUtil.openLinkedQueryDialog("ZLH240", model.getContext().getEntranceUI(), new LinkQueryData(aggvos.toArray(new AggHousesourceVO[0])));

	}
	
	class LinkQueryData implements ILinkQueryData{
    	
		private String id;
		private Object obj;
		
		public LinkQueryData(String id){
			this.id=id;
		}	
		public LinkQueryData(Object obj){
			this.obj=obj;
		}			
		public Object getUserObject() {
			return obj;
		}
		public String getBillID() {
			return id;
		}
		@Override
		public String getBillType() {
			return null;
		}
		@Override
		public String getPkOrg() {
			return null;
		}
	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
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
	
}
