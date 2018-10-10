package nc.impl.zl;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceLd_parkcontractPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractCVO;
import nc.vo.zl.ld_carcontract.CarcontractFVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ld_mdcontract.MdcontractVO;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractBVO;
import nc.vo.zl.ld_parkcontract.ParkcontractCVO;
import nc.vo.zl.ld_parkcontract.ParkcontractFVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractCustVO;
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractMzqVO;
import nc.vo.zl.tcl_contract.ContractZzqVO;
import nc.itf.zl.ILd_parkcontractMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;

public class Ld_parkcontractMaintainImpl extends AceLd_parkcontractPubServiceImpl
		implements ILd_parkcontractMaintain {

	@Override
	public void delete(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggParkcontractVO[] insert(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggParkcontractVO[] update(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggParkcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggParkcontractVO[] save(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggParkcontractVO[] unsave(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggParkcontractVO[] approve(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		AggParkcontractVO []bills=super.pubapprovebills(clientFullVOs, originBills);
		if(bills.length<1){
			throw new BusinessException("请选择一条单据！");
		}
		//合同管理审核通过时保留0号版本
		ParkcontractVO pvo=bills[0].getParentVO();
		if(pvo.getVbillstatus()==1&&pvo.getVbilltypecode().equals("H523")){
			AggParkcontractVO newvo=(AggParkcontractVO) bills[0].clone();
			newvo.getParentVO().setPk_parkcontract(null);
			newvo.getParentVO().setVbillcode("123");
			newvo.getParentVO().setVsrcbid(pvo.getPk_parkcontract());
			newvo.getParentVO().setVersion(0);
			for(ParkcontractBVO bvo:(ParkcontractBVO[])newvo.getChildren(ParkcontractBVO.class)){
				bvo.setPrimaryKey(null);
				bvo.setPk_parkcontract_b(null);
			}
			for(ParkcontractCVO cvo:(ParkcontractCVO[])newvo.getChildren(ParkcontractCVO.class)){
				cvo.setPrimaryKey(null);
				cvo.setPk_parkcontract_c(null);
			}
			for(ParkcontractFVO fvo:(ParkcontractFVO[])newvo.getChildren(ParkcontractFVO.class)){
				fvo.setPrimaryKey(null);
				fvo.setPk_parkcontract_f(null);
			}
			AggParkcontractVO[]vos=this.insert(new AggParkcontractVO[]{newvo}, null);
			String pk=vos[0].getParentVO().getPk_parkcontract();
			String sql="update zl_parkcontract set vbillcode='"+pvo.getVbillcode()+"' where pk_parkcontract ='"+pk+"'";
			BaseDAO dao=new BaseDAO();
			dao.executeUpdate(sql);
			
		}
		
		return bills;
	}

	@Override
	public AggParkcontractVO[] unapprove(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		BaseDAO dao=new BaseDAO();
		AggParkcontractVO[] bills = super.pubunapprovebills(clientFullVOs, originBills);
		if(bills.length<1){
			throw new BusinessException("请选择一条数据！");
		}
	 ParkcontractVO vo = bills[0].getParentVO();
		
		//取消审核时去除0号版本
		for(AggParkcontractVO aggvo:originBills){
			ParkcontractVO pvo=aggvo.getParentVO();
			if(pvo.getVbillstatus()==1&&pvo.getVbilltypecode().equals("H523")){
				//校验是否有下游
				String sqlxy="select count(*) from zl_carconedit where nvl(dr,0)=0 and vsrcbid='"+pvo.getPk_parkcontract()+"'";
				Integer it=(Integer)dao.executeQuery(sqlxy, new ColumnProcessor());
				if(it>0){
					throw new BusinessException("单据已有下游合同修订，不能取消审核！");
				}
				String pk=pvo.getPk_parkcontract();
				dao.deleteByClause(ParkcontractVO.class, " pk_parkcontract in(select pk_parkcontract from zl_parkcontract where " +
						"  vsrcbid='"+pk+"' and nvl(dr,0)=0)");
				dao.deleteByClause(ParkcontractBVO.class, " pk_parkcontract in(select pk_parkcontract from zl_parkcontract where  " +
						" vsrcbid='"+pk+"')");
				dao.deleteByClause(ParkcontractCVO.class, " pk_parkcontract in(select pk_parkcontract from zl_parkcontract where  " +
						"  vsrcbid='"+pk+"')");
				dao.deleteByClause(ParkcontractFVO.class, " pk_parkcontract in(select pk_parkcontract from zl_parkcontract where  " +
						"  vsrcbid='"+pk+"')");
			}
		}
		
		//应收单
		String sql="select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in " +
				"(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+vo.getPk_parkcontract()+"')";
		Integer gather_c=(Integer) dao.executeQuery(sql, new ColumnProcessor());
		
		//待收入确认单
		String sql1="select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in " +
				"(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r " +
				"where nvl(r.dr,0)=0 and r.vsrcid='"+vo.getPk_parkcontract()+"')";
		Integer bill_c=(Integer)dao.executeQuery(sql1, new ColumnProcessor());
		if(gather_c>0){
			throw new BusinessException("生成应收单已被收款单参照无法取消审批！");
		}
		if(bill_c>0){
			throw new BusinessException("生成待收入确认已被收入确认参照无法取消审批");
		}
		dao.deleteByClause(RecbillVO.class, " vsrcid='"+vo.getPk_parkcontract()+"'");
		dao.deleteByClause(ConfirmationVO.class, " vsrcid='"+vo.getPk_parkcontract()+"'");
	   
		return bills;
	}

	@Override
	public AggParkcontractVO[] query2(IQueryScheme queryScheme)
			throws BusinessException {
		String where=queryScheme.getTableListFromWhereSQL().getWhere();
		String from = queryScheme.getTableListFromWhereSQL().getFrom();
		String sql_head = "select * from "+from+" where "+where+" and vbillstatus=1 and version=-1 and nvl(dr,0)=0 order by code ";
		String sql_body_1 = "select * from zl_parkcontract_b where nvl(dr,0)=0 and pk_parkcontract in(select pk_parkcontract from zl_parkcontract where vbillstatus=1 and version=-1 and nvl(dr,0)=0)";
		BaseDAO dao=new BaseDAO();
		List<ParkcontractVO> parkList = (List<ParkcontractVO>)dao.executeQuery(sql_head, new BeanListProcessor(ParkcontractVO.class));
		List<ParkcontractBVO> parkBList = (List<ParkcontractBVO>)dao.executeQuery(sql_body_1, new BeanListProcessor(ParkcontractBVO.class));
		
		AggParkcontractVO[] aggvos = new AggParkcontractVO[parkList.size()];
		List<ParkcontractBVO> parkBList1 = new ArrayList<ParkcontractBVO>();
		ParkcontractBVO bvo = null;
		AggParkcontractVO aggvo = null;
		if(parkList.size()>0){
			for(int i=0;i<parkList.size();i++){
				aggvo = new AggParkcontractVO();
				aggvo.setParentVO(parkList.get(i));
				aggvos[i]=aggvo;
				//基本信息
				if(parkBList.size()>0){
					for(int a=0;a<parkBList.size();a++){
						if(parkList.get(i).getPk_parkcontract().equals(parkBList.get(a).getPk_parkcontract())){
							bvo = new ParkcontractBVO();
							
							bvo = parkBList.get(a);
							parkBList1.add(bvo);
						}	
					}
					 
					ParkcontractBVO[] bvos = new ParkcontractBVO[parkBList1.size()];
					aggvos[i].setChildren(ParkcontractBVO.class, parkBList1.toArray(bvos));
					parkBList1 = new ArrayList<ParkcontractBVO>();
				}
			}
		}
		
		return aggvos;
	}

	@Override
	public AggParkcontractVO queryCHTbyPK(String pk) throws BusinessException {
		BaseDAO dao=new BaseDAO();
		AggParkcontractVO aggvo=new AggParkcontractVO();
		String sql="select * from zl_parkcontract t where pk_parkcontract='"+pk+"'";
		ParkcontractVO vo=(ParkcontractVO)dao.executeQuery(sql, new BeanProcessor(ParkcontractVO.class));
		aggvo.setParentVO(vo);
		
		//查询基本信息页签
		String sql_b = "select * from zl_parkcontract_b where nvl(dr,0)=0 and pk_parkcontract='"+pk+"'";
		List<ParkcontractBVO> list1=(List<ParkcontractBVO>)dao.executeQuery(sql_b, new BeanListProcessor(ParkcontractBVO.class));
		aggvo.setChildren(ParkcontractBVO.class, list1.toArray(new ParkcontractBVO[0]));
		//查询费用信息页签
		String sql_c = "select * from zl_parkcontract_c where nvl(dr,0)=0 and pk_parkcontract='"+pk+"'";
		List<ParkcontractCVO> list2=(List<ParkcontractCVO>)dao.executeQuery(sql_c, new BeanListProcessor(ParkcontractCVO.class));
		aggvo.setChildren(ParkcontractCVO.class, list2.toArray(new ParkcontractCVO[0]));
		//查询财务分摊页签
		String sql_f = "select * from zl_parkcontract_f where nvl(dr,0)=0 and pk_parkcontract='"+pk+"'";
		List<ParkcontractFVO> list3=(List<ParkcontractFVO>)dao.executeQuery(sql_f, new BeanListProcessor(ParkcontractFVO.class));
		aggvo.setChildren(ParkcontractFVO.class, list3.toArray(new ParkcontractFVO[0]));
		
		return aggvo;
	}
	
	public Double getDouble(Object obj){
		return obj==null?0:Double.parseDouble(obj.toString());
	}

}
