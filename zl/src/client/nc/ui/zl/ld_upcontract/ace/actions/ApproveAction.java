package nc.ui.zl.ld_upcontract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.cwf_gather.GatherBVO;
import nc.vo.zl.cwf_gather.GatherVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_mdcontract.MdcontractCVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.zl.ld_upcontract.UpcontractUVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		super.doAction(e);
		
		AggUpcontractVO aggvo = (AggUpcontractVO) getModel().getSelectedData();
		String up_name=aggvo.getParentVO().getName();
		String up_customer = aggvo.getParentVO().getPk_customer();
		UFDate up_enddate = aggvo.getParentVO().getEnddate();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		IVOPersistence ivp = NCLocator.getInstance().lookup(IVOPersistence.class);
		
		String vsrcid=aggvo.getParentVO().getVsrcid();
		String getversion="select max(version) from zl_upcontract where nvl(dr,0)=0 and vsrcid='"+vsrcid+"'";
		Integer version=(Integer) iQ.executeQuery(getversion, new ColumnProcessor());
		aggvo.getParentVO().setVersion(version==null?1:(version+1));
		ivp.updateVO(aggvo.getParentVO());
		
		//多种经营合同表头
		String sql1="select * from zl_mdcontract where nvl(dr,0)=0 and pk_mdcontract='"+aggvo.getParentVO().getVsrcid()+"'";
		MdcontractVO head=(MdcontractVO)iQ.executeQuery(sql1, new BeanProcessor(MdcontractVO.class));
		Object m_name=head.getName();
		Object m_customer =  head.getPk_customer();
		Object m_pk=  head.getPk_mdcontract();
		UFDate m_enddate = new UFDate(head.getEnddate().toString());
		
		//多种经营合同表体
		String sql2="select * from zl_mdcontract_c where nvl(dr,0)=0 and pk_mdcontract='"+aggvo.getParentVO().getVsrcid()+"'";
		List<MdcontractCVO> bodyList = (List<MdcontractCVO>)iQ.executeQuery(sql2, new BeanListProcessor(MdcontractCVO.class));
		//应收单
		String sql3 = "select * from zl_recbill where nvl(dr,0)=0 and vsrcid='"+getStgobj(m_pk)+"'";
		List<RecbillVO> recbillList = (List<RecbillVO>)iQ.executeQuery(sql3, new BeanListProcessor(RecbillVO.class));
		//收款单
		String sql4="select * from zl_gather where nvl(dr,0)=0 and vsrcid in (select pk_recbill from zl_recbill where nvl(dr,0)=0 and vsrcid='"+getStgobj(m_pk)+"')";
		List<GatherVO> gatherList=(List<GatherVO>) iQ.executeQuery(sql4, new BeanListProcessor(GatherVO.class));
		//待收入确认单
		String sql5="select * from zl_confirmation where nvl(dr,0)=0 and vsrcid in ('"+getStgobj(m_pk)+"')";
		List<ConfirmationVO> confirmList=(List<ConfirmationVO>) iQ.executeQuery(sql5, new BeanListProcessor(ConfirmationVO.class));
		//回写多种经营合同
		if(!up_name.equals(getStgobj(m_name))||
				!up_customer.equals(getStgobj(m_customer))||
				!up_enddate.equals(getStgobj(m_enddate))){
			if(!up_name.equals(getStgobj(m_name))){
				head.setName(up_name);
			}
			if(!up_enddate.equals(getStgobj(m_enddate))){
				head.setEnddate(up_enddate);
			}
			if(!up_customer.equals(getStgobj(m_customer))){
				head.setPk_customer(up_customer);
				if(bodyList.size()!=0){
					for(int i=0;i<bodyList.size();i++){
						bodyList.get(i).setPk_customer(up_customer);
						bodyList.get(i).setDr(0);
					}
					ivp.updateVOList(bodyList);
					//回写应收单
					for(int j=0;j<recbillList.size();j++){
						String m = recbillList.get(j).getNrealmoney()==null?"0":recbillList.get(j).getNrealmoney().toString();
						double getm = Double.parseDouble(m);
						if(getm==0){
							recbillList.get(j).setPk_customer(up_customer);
						}
	
					}
					//回写待收入确认单
					if(confirmList.size()>0){
						for(int k = 0;k<confirmList.size();k++){
							if(getUFdobj(confirmList.get(k).getAmountconfirmed()).toDouble()==0){
								confirmList.get(k).setPk_customer(up_customer);
							}
						}
						ivp.updateVOList(confirmList);
					}
					
				}
				ivp.updateVOList(recbillList);
				
				
				
			}
			ivp.updateVO(head);
		}
		
	}
	
	public String getStgobj(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
}
