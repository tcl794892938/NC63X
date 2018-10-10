package nc.impl.zl;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceLd_carcontractPubServiceImpl;
import nc.itf.zl.ILd_carcontractMaintain;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractCVO;
import nc.vo.zl.ld_carcontract.CarcontractFVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.lm_customer.Customer_carVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractCustVO;
import nc.vo.zl.tcl_contract.ContractVO;

public class Ld_carcontractMaintainImpl extends AceLd_carcontractPubServiceImpl
		implements ILd_carcontractMaintain {

	@Override
	public void delete(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarcontractVO[] insert(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarcontractVO[] update(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}
	@Override
	public AggCarcontractVO[] query2(IQueryScheme queryScheme)
			throws BusinessException {
		String where=queryScheme.getTableListFromWhereSQL().getWhere();
		String from = queryScheme.getTableListFromWhereSQL().getFrom();
		String sql_head = "select * from "+from+" where "+where+" and vbillstatus=1 and version=-1 and nvl(dr,0)=0 order by code ";
		String sql_body_1 = "select * from zl_carcontract_b where nvl(dr,0)=0 and pk_carcontract in(select pk_carcontract from zl_carcontract where vbillstatus=1 and version=-1 and nvl(dr,0)=0) ";
		
		BaseDAO dao=new BaseDAO();
		List<CarcontractVO> carList = (List<CarcontractVO>)dao.executeQuery(sql_head, new BeanListProcessor(CarcontractVO.class));
		List<CarcontractBVO> carBList = (List<CarcontractBVO>)dao.executeQuery(sql_body_1, new BeanListProcessor(CarcontractBVO.class));

		AggCarcontractVO[] aggvos = new AggCarcontractVO[carList.size()];
		
		List<CarcontractBVO> carBList1 = new ArrayList<CarcontractBVO>();
		CarcontractBVO bvo = null;
		
		AggCarcontractVO vo =null;
		if(carList.size()>0){
			for(int i=0;i<carList.size();i++){
				vo = new AggCarcontractVO();
				vo.setParentVO(carList.get(i));
				aggvos[i]=vo;
				//基本信息
				if(carBList.size()>0){
					for(int a=0;a<carBList.size();a++){
						if(carList.get(i).getPk_carcontract().equals(carBList.get(a).getPk_carcontract())){
							bvo = new CarcontractBVO();
							
							bvo = carBList.get(a);
							carBList1.add(bvo);
						}	
					}
					
					CarcontractBVO[] bvos = new CarcontractBVO[carBList1.size()];
					aggvos[i].setChildren(CarcontractBVO.class, carBList1.toArray(bvos));
					carBList1 = new ArrayList<CarcontractBVO>();
				}
				
			}	
		}
		return aggvos;
	}

	@Override
	public AggCarcontractVO[] save(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarcontractVO[] unsave(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarcontractVO[] approve(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		AggCarcontractVO []bills=super.pubapprovebills(clientFullVOs, originBills);
		if(bills.length>1){
			throw new BusinessException("请选择一条单据！");
		}
		//合同管理审核通过时保留0号版本
		CarcontractVO pvo=bills[0].getParentVO();
		if(pvo.getVbillstatus()==1&&pvo.getVbilltypecode().equals("H524")){
			AggCarcontractVO newvo=(AggCarcontractVO) bills[0].clone();
			newvo.getParentVO().setPk_carcontract(null);
			newvo.getParentVO().setVbillcode("123");
			newvo.getParentVO().setVsrcbid(pvo.getPk_carcontract());
			newvo.getParentVO().setVersion(0);
			for(CarcontractBVO bvo:(CarcontractBVO[])newvo.getChildren(CarcontractBVO.class)){
				bvo.setPrimaryKey(null);
				bvo.setPk_carcontract_b(null);
			}
			for(CarcontractCVO cvo:(CarcontractCVO[])newvo.getChildren(CarcontractCVO.class)){
				cvo.setPrimaryKey(null);
				cvo.setPk_carcontract_c(null);
			}
			for(CarcontractFVO fvo:(CarcontractFVO[])newvo.getChildren(CarcontractFVO.class)){
				fvo.setPrimaryKey(null);
				fvo.setPk_carcontract_f(null);
			}
			AggCarcontractVO[]vos=this.insert(new AggCarcontractVO[]{newvo}, null);
			String pk=vos[0].getParentVO().getPk_carcontract();
			String sql="update zl_carcontract set vbillcode='"+pvo.getVbillcode()+"' where pk_carcontract ='"+pk+"'";
			BaseDAO dao=new BaseDAO();
			dao.executeUpdate(sql);
		}
		
		return bills;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggCarcontractVO[] unapprove(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {

		BaseDAO dao=new BaseDAO();
		AggCarcontractVO[] bills = super.pubunapprovebills(clientFullVOs, originBills);
		if(bills.length>1){
			throw new BusinessException("请选择一条数据！");
		}
		CarcontractVO vo = bills[0].getParentVO();
		CarcontractBVO[] bvos = (CarcontractBVO[]) bills[0].getAllChildrenVO();
		
			//客户信息中心
			String sql3 = "select * from zl_customer_car where nvl(dr,0)=0 and pk_customer ='"+vo.getPk_customer()+"'";
			List<Customer_carVO> carList=(List<Customer_carVO>)dao.executeQuery(sql3, new BeanListProcessor(Customer_carVO.class));
			for(Customer_carVO carvo:carList){
				for(CarcontractBVO bvo:bvos){
					if(carvo!=null&&carvo.getCarnum().equals(bvo.getPlatenum())){
						dao.deleteVO(carvo);
					}
				}
				
			}
		//取消审核时去除0号版本
		for(AggCarcontractVO aggvo:originBills){
			CarcontractVO pvo=aggvo.getParentVO();
			if(pvo.getVbillstatus()==1&&pvo.getVbilltypecode().equals("H524")){
				//校验是否有下游
				String sqlxy="select count(*) from zl_carconedit where nvl(dr,0)=0 and vsrcbid='"+pvo.getPk_carcontract()+"'";
				Integer it=(Integer)dao.executeQuery(sqlxy, new ColumnProcessor());
				if(it>0){
					throw new BusinessException("单据已有下游合同修订，不能取消审核！");
				}
				String pk=pvo.getPk_carcontract();
				dao.deleteByClause(CarcontractVO.class, " pk_carcontract in(select pk_carcontract from zl_carcontract where " +
						"  vsrcbid='"+pk+"' and nvl(dr,0)=0)");
				dao.deleteByClause(CarcontractBVO.class, " pk_carcontract in(select pk_carcontract from zl_carcontract where  " +
						" vsrcbid='"+pk+"')");
				dao.deleteByClause(CarcontractCVO.class, " pk_carcontract in(select pk_carcontract from zl_carcontract where  " +
						"  vsrcbid='"+pk+"')");
				dao.deleteByClause(CarcontractFVO.class, " pk_carcontract in(select pk_carcontract from zl_carcontract where  " +
						"  vsrcbid='"+pk+"')");
			}
		}
			
			//应收单
			String sql="select count(*) from zl_gather_b b where nvl(b.dr,0)=0 and b.pk_gather in " +
					"(select g.pk_gather from zl_gather g where nvl(g.dr,0)=0) and b.vsrcid in (select r.pk_recbill from zl_recbill r " +
					"where nvl(r.dr,0)=0 and r.vsrcid='"+vo.getPk_carcontract()+"')";
			Integer gather_c=(Integer) dao.executeQuery(sql, new ColumnProcessor());
			
			//待收入确认单
			String sql1="select count(*) from zl_billconfirmedb b where nvl(b.dr,0)=0 and b.pk_billconfirmed in " +
					"(select a.pk_billconfirmed from zl_billconfirmed a where nvl(a.dr,0)=0) and b.vsrcid in (select r.pk_confirmation from zl_confirmation r " +
					"where nvl(r.dr,0)=0 and r.vsrcid='"+vo.getPk_carcontract()+"')";
			Integer bill_c=(Integer)dao.executeQuery(sql1, new ColumnProcessor());
			if(gather_c>0){
				throw new BusinessException("生成应收单已被收款单参照无法取消审批！");
			}
			if(bill_c>0){
				throw new BusinessException("生成待收入确认已被收入确认参照无法取消审批");
			}
			dao.deleteByClause(RecbillVO.class, " vsrcid='"+vo.getPk_carcontract()+"'");
			dao.deleteByClause(ConfirmationVO.class, " vsrcid='"+vo.getPk_carcontract()+"'");
		
		
		return bills;
	
	}

	@Override
	public AggCarcontractVO queryCHTbyPK(String pk) throws BusinessException {
		BaseDAO dao=new BaseDAO();
		AggCarcontractVO aggvo=new AggCarcontractVO();
		String sql="select * from zl_carcontract t where pk_carcontract='"+pk+"'";
		CarcontractVO vo=(CarcontractVO)dao.executeQuery(sql, new BeanProcessor(CarcontractVO.class));
		aggvo.setParentVO(vo);
		
		//查询基本信息页签
		String sql_b = "select * from zl_carcontract_b where nvl(dr,0)=0 and pk_carcontract='"+pk+"'";
		List<CarcontractBVO> list1=(List<CarcontractBVO>)dao.executeQuery(sql_b, new BeanListProcessor(CarcontractBVO.class));
		aggvo.setChildren(CarcontractBVO.class, list1.toArray(new CarcontractBVO[0]));
		//查询费用信息页签
		String sql_c = "select * from zl_carcontract_c where nvl(dr,0)=0 and pk_carcontract='"+pk+"'";
		List<CarcontractCVO> list2=(List<CarcontractCVO>)dao.executeQuery(sql_c, new BeanListProcessor(CarcontractCVO.class));
		aggvo.setChildren(CarcontractCVO.class, list2.toArray(new CarcontractCVO[0]));
		//查询财务分摊页签
		String sql_f = "select * from zl_carcontract_f where nvl(dr,0)=0 and pk_carcontract='"+pk+"'";
		List<CarcontractFVO> list3=(List<CarcontractFVO>)dao.executeQuery(sql_f, new BeanListProcessor(CarcontractFVO.class));
		aggvo.setChildren(CarcontractFVO.class, list3.toArray(new CarcontractFVO[0]));
		
		return aggvo;
	}

	public Double getDouble(Object obj){
		return obj==null?0:Double.parseDouble(obj.toString());
	}
}
