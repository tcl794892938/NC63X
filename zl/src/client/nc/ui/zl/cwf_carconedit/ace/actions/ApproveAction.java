package nc.ui.zl.cwf_carconedit.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.zl.cwf_carconedit.CarconeditBVO;
import nc.vo.zl.cwf_carconedit.CarconeditVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ld_carcontract.CarcontractBVO;
import nc.vo.zl.ld_carcontract.CarcontractCVO;
import nc.vo.zl.ld_carcontract.CarcontractVO;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.zl.ld_parkcontract.ParkcontractBVO;
import nc.vo.zl.ld_parkcontract.ParkcontractCVO;
import nc.vo.zl.ld_parkcontract.ParkcontractFVO;
import nc.vo.zl.ld_parkcontract.ParkcontractVO;
import nc.vo.zl.lm_customer.Customer_carVO;
import nc.vo.zl.lm_customer.Customer_fcxxVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class ApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		//System.out.print(pk_billtype);
		
		super.doAction(e);
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		IVOPersistence ivp = NCLocator.getInstance().lookup(IVOPersistence.class);
		AggCarconeditVO aggvo = (AggCarconeditVO) getModel().getSelectedData();
		CarconeditVO vo = aggvo.getParentVO();
		CarconeditBVO[] cdbvos = (CarconeditBVO[]) aggvo.getChildren(CarconeditBVO.class);
		
		Map<Integer,List<RecbillVO>> recbillMap = new HashMap<Integer,List<RecbillVO>>();
		Map<Integer,CarFilesVO> carfileMap = new HashMap<Integer,CarFilesVO>();
		Map<Integer,Customer_carVO> customMap = new HashMap<Integer,Customer_carVO>();
		Map<Integer,Customer_fcxxVO> customMap1 = new HashMap<Integer,Customer_fcxxVO>();
		Map<Integer,List<ConfirmationVO>> conMap = new HashMap<Integer,List<ConfirmationVO>>();
		if(cdbvos.length>0){
			for(int a=0;a<cdbvos.length;a++){
				//应收单
				String rec_sql = "select * from zl_recbill r where  nvl(dr,0)=0 and pk_carno='"+cdbvos[a].getPk_plate()+"'";
				List<RecbillVO> recbillList = (List<RecbillVO>)iQ.executeQuery(rec_sql, new BeanListProcessor(RecbillVO.class));
				recbillMap.put(a, recbillList);
				//车牌号对应的车辆档案
				String car_sql = "select * from zl_carfiles f where nvl(dr,0)=0 and pk_carfiles='"+cdbvos[a].getPk_plate()+"'";
				CarFilesVO carfile = (CarFilesVO)iQ.executeQuery(car_sql, new BeanProcessor(CarFilesVO.class));
				carfileMap.put(a, carfile);
				//客户信息中心--车牌号
				String cus_sql = "select * from zl_customer_car where nvl(dr,0)=0 and carnum='"+cdbvos[a].getPk_plate()+"'";
				Customer_carVO customvo = (Customer_carVO)iQ.executeQuery(cus_sql, new BeanProcessor(Customer_carVO.class));
				customMap.put(a, customvo);
				//客户信息中心--房产
				String cus_sql_1 = "select * from zl_customer_fcxx where nvl(dr,0)=0 and fcname='"+cdbvos[a].getPk_house()+"'";
				Customer_fcxxVO customvo1 = (Customer_fcxxVO)iQ.executeQuery(cus_sql_1, new BeanProcessor(Customer_fcxxVO.class));
				customMap1.put(a, customvo1);
			}
			//待收入确认单
			String con_sql="select * from zl_confirmation where nvl(dr,0)=0 and vsrcid='"+vo.getVsrcbid()+"'";
			List<ConfirmationVO> conList = (List<ConfirmationVO>)iQ.executeQuery(con_sql, new BeanListProcessor(ConfirmationVO.class));
			conMap.put(0, conList);
		}
		
		
		/*//应收单
		String rec_sql = "select * from zl_recbill r where  nvl(dr,0)=0 and pk_carno=='"+aggvo.getParentVO().getVsrcbcode()+"'";
		List<RecbillVO> recbillList = (List<RecbillVO>)iQ.executeQuery(rec_sql, new BeanListProcessor(RecbillVO.class));*/
		//车辆合同
		Object vbilltypecode = aggvo.getParentVO().getVsrcbcode();
		//CarconeditVO vo = aggvo.getParentVO();
		if(vbilltypecode.equals("H524")){
			//获取车辆合同 的名称、编码、客户名称
			String sql = "select code,name,pk_customer from zl_carcontract where nvl(dr,0)=0 and version='-1' and  pk_carcontract='"+vo.getVsrcbid()+"'";
			Map<String, Object> map=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
			Object obj1 =  map.get("code");
			Object obj2=  map.get("name");
			Object obj3 =  map.get("pk_customer");
			//获取对应上游车辆合同主子表
			String car_sql_head = "select * from zl_carcontract where nvl(dr,0)=0 and version='-1' and  pk_carcontract='"+vo.getVsrcbid()+"'";
			CarcontractVO carhead = (CarcontractVO)iQ.executeQuery(car_sql_head, new BeanProcessor(CarcontractVO.class));
			String car_sql_body_b = "select * from zl_carcontract_b where nvl(dr,0)=0 and pk_carcontract = '"+vo.getVsrcbid()+"'";
			List<CarcontractBVO> carbodyList = (List<CarcontractBVO>)iQ.executeQuery(car_sql_body_b, new BeanListProcessor(CarcontractBVO.class));
			//判断更名
			if(!vo.getPk_customer().equals(obj3.toString())){
				//获取对应车牌号的车位合同
				Map<Integer,AggParkcontractVO[]> parkMap = new HashMap<Integer,AggParkcontractVO[]>();
				for(int b=0;b<cdbvos.length;b++){
					AggParkcontractVO parkaggco = new AggParkcontractVO();
					
					String park_sql_head = "select * from zl_parkcontract c where nvl(dr,0)=0 and c.version=-1 and c.pk_parkcontract "+
							" in(select pk_parkcontract from zl_parkcontract_b b where nvl(dr,0)=0 and b.platenum='"+cdbvos[b].getPk_plate()+"')";
					List<ParkcontractVO> parkheadList = (List<ParkcontractVO>)iQ.executeQuery(park_sql_head, new BeanListProcessor(ParkcontractVO.class));
					AggParkcontractVO[] parkaggcos = new AggParkcontractVO[parkheadList.size()];
					if(parkheadList.size()>0){
						for(int pb=0;pb<parkheadList.size();pb++){
							//基本信息
							String park_sql_body = "select * from zl_parkcontract_b p where nvl(dr,0)=0 and p.pk_parkcontract='"+parkheadList.get(pb).getPk_parkcontract()+"'";
							List<ParkcontractBVO> parkbodyList = (List<ParkcontractBVO>)iQ.executeQuery(park_sql_body, new BeanListProcessor(ParkcontractBVO.class));
							//费用信息
							String park_sql_body_1 = "select * from zl_parkcontract_c p where nvl(dr,0)=0 and p.pk_parkcontract='"+parkheadList.get(pb).getPk_parkcontract()+"'";
							List<ParkcontractCVO> parkbodyList_1 = (List<ParkcontractCVO>)iQ.executeQuery(park_sql_body_1, new BeanListProcessor(ParkcontractCVO.class));
							//财务信息
							String park_sql_body_2 = "select * from zl_parkcontract_f p where nvl(dr,0)=0 and p.pk_parkcontract='"+parkheadList.get(pb).getPk_parkcontract()+"'";
							List<ParkcontractFVO> parkbodyList_2 = (List<ParkcontractFVO>)iQ.executeQuery(park_sql_body_2, new BeanListProcessor(ParkcontractFVO.class));
							parkaggco.setParentVO(parkheadList.get(pb));
							ParkcontractBVO[] pbvo = new ParkcontractBVO[parkbodyList.size()];
							ParkcontractCVO[] pcvo = new ParkcontractCVO[parkbodyList_1.size()];
							ParkcontractFVO[] pfvo = new ParkcontractFVO[parkbodyList_2.size()];
							parkaggco.setChildren(ParkcontractBVO.class, parkbodyList.toArray(pbvo));
							parkaggco.setChildren(ParkcontractCVO.class, parkbodyList_1.toArray(pcvo));
							parkaggco.setChildren(ParkcontractFVO.class, parkbodyList_2.toArray(pfvo));
							parkaggcos[pb] = parkaggco;
						}
						parkMap.put(b, parkaggcos);
					}
				}
				
				CarconeditBVO[] bvo = (CarconeditBVO[]) aggvo.getTableVO("pk_carconedit_b");
				carhead.setPk_customer(vo.getPk_customer());
				
				for(int i=0;i<carbodyList.size();i++){
					carbodyList.get(i).setPk_customer(bvo[i].getPk_customer());
				}
				
				//回写车辆合同
				carhead.setVersion(-1);
				ivp.updateVO(carhead);
				ivp.updateVOList(carbodyList);
				
				//回写应收单
				for(List<RecbillVO> recbillList:recbillMap.values()){
					for(int c=0;c<recbillList.size();c++){
						String m = recbillList.get(c).getNrealmoney()==null?"0":recbillList.get(c).getNrealmoney().toString();
						double getm = Double.parseDouble(m);
						if(getm==0){
							recbillList.get(c).setPk_customer(vo.getPk_customer());
						}
						ivp.updateVOList(recbillList);
					}
				}
				//回写待收入确认单
				for(List<ConfirmationVO> confList:conMap.values()){
					for(int c=0;c<confList.size();c++){
						String m = confList.get(c).getAmountconfirmed()==null?"0":confList.get(c).getAmountconfirmed().toString();
						double getm = Double.parseDouble(m);
						if(getm==0){
							confList.get(c).setPk_customer(vo.getPk_customer());
						}
						ivp.updateVOList(confList);
					}
				}
				//回写车位合同
				if(!parkMap.containsValue(null)){
					ParkcontractVO pvo = null;
					for(AggParkcontractVO[] parkaggvos:parkMap.values()){
						
						for(int d=0;d<parkaggvos.length;d++){
							
							pvo = new ParkcontractVO();
							pvo = parkaggvos[d].getParentVO();
							pvo.setPk_customer(vo.getPk_customer());
							ivp.updateVO(pvo);
							//基本信息
							ParkcontractBVO[] pbvo = (ParkcontractBVO[]) parkaggvos[d].getChildren(ParkcontractBVO.class);
							//费用信息
							ParkcontractCVO[] pcvo = (ParkcontractCVO[]) parkaggvos[d].getChildren(ParkcontractCVO.class);
							
							for(int i=0;i<pbvo.length;i++){
								String parkarea = pbvo[i].getParkarea();
								String parknum = pbvo[i].getParknum();
								double count = 0.0;
								for(int j=0;j<pcvo.length;j++){
									String parkarea1 = pcvo[j].getParkarea();
									String parknum1 = pcvo[j].getParknum();
									if(parkarea.equals(parkarea1)&&parknum.equals(parknum1)){
										//if(pcvo[j].getNcollectemoney())
										String nm= pcvo[j].getNcollectemoney()==null?"0":pcvo[j].getNcollectemoney().toString();
										double getmo = Double.parseDouble(nm);
										count = count + getmo;	
									}
								}
								if(count==0){
									pbvo[i].setPk_customer(vo.getPk_customer());
								}
							}
							ivp.updateVOArray(pbvo);
							
							
						}
						///ivp.updateVO(parkaggvos);
						/*ILd_parkcontractMaintain cmd = NCLocator.getInstance().lookup(ILd_parkcontractMaintain.class);
						cmd.update(parkaggvos1, parkaggvos2);*/
					}
				}
				//回写车辆档案
				List<CarFilesVO> carfList = new ArrayList<CarFilesVO>();
				if(!carfileMap.containsValue(null)){
					for(CarFilesVO carfile:carfileMap.values()){
						carfile.setKhname(vo.getPk_customer());
						
						carfList.add(carfile);
					}
					ivp.updateVOList(carfList);
				}
				
				//回写客户信息中心
				if(!customMap.containsValue(null)){
					List<Customer_carVO> cusList = new ArrayList<Customer_carVO>();
					for(Customer_carVO customvo:customMap.values()){
						customvo.setPk_customer(vo.getPk_customer());
						cusList.add(customvo);
					}
					ivp.updateVOList(cusList);
				}
				//
			}else if(!vo.getName().equals(obj2.toString())||!vo.getCode().equals(obj1.toString())){//编码、名称
				carhead.setCode(vo.getCode());
				carhead.setName(vo.getName());
				ivp.updateVO(carhead);
			}
		}
		
		//车位合同
		if(vbilltypecode.equals("H523")){
			//获取车辆合同 的名称、编码、客户名称
			String sql = "select code,name,pk_customer from zl_parkcontract where nvl(dr,0)=0 and version='-1' and  pk_parkcontract='"+vo.getVsrcbid()+"'";
			Map<String, Object> map=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
			Object obj1 =  map.get("code");
			Object obj2=  map.get("name");
			Object obj3 =  map.get("pk_customer");
			
			//获取对应上游车位合同主子表
			String car_sql_head = "select * from zl_parkcontract where nvl(dr,0)=0 and version='-1' and  pk_parkcontract='"+vo.getVsrcbid()+"'";
			ParkcontractVO carhead = (ParkcontractVO)iQ.executeQuery(car_sql_head, new BeanProcessor(ParkcontractVO.class));
			String car_sql_body_b = "select * from zl_parkcontract_b where nvl(dr,0)=0 and pk_parkcontract = '"+vo.getVsrcbid()+"'";
			List<ParkcontractBVO> carbodyList = (List<ParkcontractBVO>)iQ.executeQuery(car_sql_body_b, new BeanListProcessor(ParkcontractBVO.class));
			
			//判断更名
			if(!vo.getPk_customer().equals(obj3.toString())){
				/*//获取车牌号对应的车位合同
				Map<Integer,AggCarcontractVO[]> carMap = new HashMap<Integer,AggCarcontractVO[]>();*/
				//回写车辆合同
				for(int b=0;b<cdbvos.length;b++){
					String park_sql_head = "select * from zl_carcontract ct where nvl(dr,0)=0 and ct.version=-1 and ct.pk_carcontract "+
							"in(select pk_carcontract from zl_carcontract_b cb where nvl(dr,0)=0 and cb.platenum='"+cdbvos[b].getPk_plate()+"')";
					List<CarcontractVO> parkheadList = (List<CarcontractVO>)iQ.executeQuery(park_sql_head, new BeanListProcessor(CarcontractVO.class));
					CarcontractVO carvo = null;
					if(parkheadList.size()>0){
						for(int j=0;j<parkheadList.size();j++){
							carvo = new CarcontractVO();
							carvo = parkheadList.get(j);
							carvo.setPk_customer(vo.getPk_customer());
							ivp.updateVO(carvo);
							
							
							String car_sql_body_b1 = "select * from zl_carcontract_b cb where nvl(dr,0)=0 and cb.pk_carcontract='"+parkheadList.get(j).getPk_carcontract()+"'";
							String car_sql_body_c = "select * from zl_carcontract_c cb where nvl(dr,0)=0 and cb.pk_carcontract='"+parkheadList.get(j).getPk_carcontract()+"'";
							
							List<CarcontractBVO> carBList = (List<CarcontractBVO>) iQ.executeQuery(car_sql_body_b1, new BeanListProcessor(CarcontractBVO.class));
							List<CarcontractCVO> carCList = (List<CarcontractCVO>) iQ.executeQuery(car_sql_body_c, new BeanListProcessor(CarcontractCVO.class));
							
							for(int k=0;k<carBList.size();k++){
								String bcarn = carBList.get(k).getPlatenum();
								Double count = 0.0 ;
								for(int m=0;m<carCList.size();m++){
									String ccarn = carCList.get(m).getPlatenum();
									
									if(bcarn.equals(ccarn)){
										String nm= carCList.get(m).getNcollectemoney()==null?"0":carCList.get(m).getNcollectemoney().toString();
										double getmo = Double.parseDouble(nm);
										if(getmo==0){
											count = count + getmo;
										}
									}
									
								}
								if(count==0){
									carBList.get(k).setPk_customer(vo.getPk_customer());
								}
								ivp.updateVOList(carBList);
							}
							
						}
					}
					
				}
				//CarconeditBVO[] bvo = (CarconeditBVO[]) aggvo.getTableVO("pk_carconedit_b");
				carhead.setPk_customer(vo.getPk_customer());
				
				for(int i=0;i<carbodyList.size();i++){
					carbodyList.get(i).setPk_customer(cdbvos[i].getPk_customer());
					//carbodyList.get(i).setDr(0);
				}
				
				//回写车位合同
				carhead.setVersion(-1);
				ivp.updateVO(carhead);
				ivp.updateVOList(carbodyList);
				//回写应收单
				for(List<RecbillVO> recbillList:recbillMap.values()){
					for(int j=0;j<recbillList.size();j++){
						String m = recbillList.get(j).getNrealmoney()==null?"0":recbillList.get(j).getNrealmoney().toString();
						double getm = Double.parseDouble(m);
						if(getm<1){
							recbillList.get(j).setPk_customer(vo.getPk_customer());
						}
						ivp.updateVOList(recbillList);
					}
				}
				//回写待收入确认单
				for(List<ConfirmationVO> confList:conMap.values()){
					for(int c=0;c<confList.size();c++){
						String m = confList.get(c).getAmountconfirmed()==null?"0":confList.get(c).getAmountconfirmed().toString();
						double getm = Double.parseDouble(m);
						if(getm==0){
							confList.get(c).setPk_customer(vo.getPk_customer());
						}
						ivp.updateVOList(confList);
					}
				}
				List<CarFilesVO> carfList = new ArrayList<CarFilesVO>();
				if(!carfileMap.containsValue(null)){
					for(CarFilesVO carfile:carfileMap.values()){
						carfile.setKhname(vo.getPk_customer());
						
						carfList.add(carfile);
					}
					ivp.updateVOList(carfList);
				}
				//回写客户信息中心--车牌号
				if(!customMap.containsValue(null)){
					List<Customer_carVO> cusList = new ArrayList<Customer_carVO>();
					for(Customer_carVO customvo:customMap.values()){
						customvo.setPk_customer(vo.getPk_customer());
						cusList.add(customvo);
					}
					ivp.updateVOList(cusList);
					
					//回写客户信息中心--房产
					List<Customer_fcxxVO> cusList1 = new ArrayList<Customer_fcxxVO>();
					for(Customer_fcxxVO customvo:customMap1.values()){
						customvo.setPk_customer(vo.getPk_customer());
						cusList1.add(customvo);
					}
					ivp.updateVOList(cusList1);
				}
				
				

			}else if(!vo.getName().equals(obj2.toString())||!vo.getCode().equals(obj1.toString())){//编码、名称
				carhead.setCode(vo.getCode());
				carhead.setName(vo.getName());
				ivp.updateVO(carhead);
			}
		}
	}
}
