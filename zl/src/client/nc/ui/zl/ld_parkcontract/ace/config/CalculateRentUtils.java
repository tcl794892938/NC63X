package nc.ui.zl.ld_parkcontract.ace.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.ui.zl.tcl_contract.ace.config.CalendarUtls;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class CalculateRentUtils {
	

	/**
	 * 重算业务拆分
	 */
	public static void recalSplitYwcfData(BillCardPanel panel) throws BusinessException{
		
		BillModel modelb=panel.getBillModel("pk_parkcontract_c");//当前页签
		//获取车位页签数据
		BillModel modela=panel.getBillModel("pk_parkcontract_b");
		int row=modela.getRowCount();
		if(row<=0){
			return ;
		}
		
		
		Object pk_sfxm=panel.getHeadItem("pk_costproject").getValueObject();
		//拆分日期
		Object obj1=panel.getHeadItem("startdate").getValueObject();
		Object obj2=panel.getHeadItem("enddate").getValueObject();
		UFDate ud_fist=new UFDate(obj1.toString());
		UFDate ud_last=new UFDate(obj2.toString());
		//付款方式
		Object obj3=panel.getHeadItem("costcycle").getValueObject();
		Integer it=Integer.valueOf(obj3.toString());
		//租约金额
		Object obj4=panel.getHeadItem("nallrent").getValueObject();
		UFDouble rentmoney = new UFDouble(obj4.toString());
		UFDouble nrentmoney = rentmoney.multiply(it);
		//日租金
		UFDouble daym = rentmoney.div(new UFDouble(30), 2);
		//租约总金额
		int months = CalendarUtls.getBetweenMonths(ud_fist, ud_last);//自然月数
		int days = CalendarUtls.getLeftDays(ud_fist, ud_last);
		UFDouble allMonths = new UFDouble(rentmoney.multiply(months));
		UFDouble leftday = new UFDouble(daym.multiply(days));
		UFDouble allmoney = allMonths.add(leftday);
		
		
		//税率
		Object obj5=panel.getHeadItem("ntaxrate").getValueObject();
		Integer ntaxrate=Integer.valueOf(obj5.toString());
		//处理逻辑
		for(int i=0;i<row;i++){
			
			Object parkarea=modela.getValueObjectAt(i, "parkarea");
			Object parknum=modela.getValueObjectAt(i, "parknum");
			
			
			if(it==0||it==13){//一次性付款
				modelb.addLine();
				modelb.setValueAt(modelb.getRowCount()*10, modelb.getRowCount()-1, "rowno");
				modelb.setValueAt(parkarea, modelb.getRowCount()-1, "parkarea");
				modelb.setValueAt(parknum, modelb.getRowCount()-1, "parknum");
				modelb.setValueAt(pk_sfxm, modelb.getRowCount()-1, "pk_costproject");
				modelb.setValueAt(ud_fist, modelb.getRowCount()-1, "mstartdate");
				modelb.setValueAt(ud_last, modelb.getRowCount()-1, "menddate");
				modelb.setValueAt(ud_fist, modelb.getRowCount()-1, "paydate");
				modelb.setValueAt(new UFDouble(0), modelb.getRowCount()-1, "ncollectemoney");
				if(it==0){
					modelb.setValueAt(allmoney, modelb.getRowCount()-1, "nrentmoney");
					modelb.setValueAt(allmoney, modelb.getRowCount()-1, "nreceivemoney");
					allmoney = allmoney.add(new UFDouble(0), 2, 0);
					panel.getHeadItem("vdef1").setValue(allmoney);
				}else if(it==13){
					modelb.setValueAt(rentmoney, modelb.getRowCount()-1, "nrentmoney");
					modelb.setValueAt(rentmoney, modelb.getRowCount()-1, "nreceivemoney");
					rentmoney = rentmoney.add(new UFDouble(0), 2, 0);
					panel.getHeadItem("vdef1").setValue(rentmoney);
				}
				
			}else{//正常按月付款
				
				UFDate start=ud_fist;
				//
				UFDouble money = nrentmoney.div(CalendarUtls.getBetweenMonths(ud_fist, ud_last)/it);
				UFDouble ljfc=new UFDouble(0);
				while(!start.afterDate(ud_last)){
					UFDate end=CalendarUtls.getNextMonthDay(start, it);
					UFDate end2=CalendarUtls.getBeforeFirstDay(end);
					modelb.addLine();
					
					modelb.setValueAt(modelb.getRowCount()*10, modelb.getRowCount()-1, "rowno");
					modelb.setValueAt(parkarea, modelb.getRowCount()-1, "parkarea");
					modelb.setValueAt(parknum, modelb.getRowCount()-1, "parknum");
					modelb.setValueAt(pk_sfxm, modelb.getRowCount()-1, "pk_costproject");
					modelb.setValueAt(start, modelb.getRowCount()-1, "paydate");
					modelb.setValueAt(start, modelb.getRowCount()-1, "mstartdate");
					
					if(!end2.beforeDate(ud_last)){//结束日期之后
						end2=ud_last;
						
					}
					modelb.setValueAt(end2, modelb.getRowCount()-1, "menddate");
					if(!end2.beforeDate(ud_last)){//结束日期之后
						
						modelb.setValueAt(allmoney.sub(ljfc,2), modelb.getRowCount()-1, "nrentmoney");
						modelb.setValueAt(allmoney.sub(ljfc,2), modelb.getRowCount()-1, "nreceivemoney");
						modelb.setValueAt(new UFDouble(0), modelb.getRowCount()-1, "ndiscountmoney");
						modelb.setValueAt(new UFDouble(0), modelb.getRowCount()-1, "ncollectemoney");
						allmoney = allmoney.add(new UFDouble(0), 2, 0);
						panel.getHeadItem("vdef1").setValue(allmoney);
						break;
					}

					modelb.setValueAt(new UFDouble(0), modelb.getRowCount()-1, "ndiscountmoney");
					modelb.setValueAt(nrentmoney, modelb.getRowCount()-1, "nrentmoney");
					modelb.setValueAt(nrentmoney, modelb.getRowCount()-1, "nreceivemoney");
					modelb.setValueAt(new UFDouble(0), modelb.getRowCount()-1, "ncollectemoney");
					
					ljfc=ljfc.add(nrentmoney);
					start=CalendarUtls.getAfterFirstDay(end2);//交替起始日期
				}
			}
		}
		
		
		modelb.loadLoadRelationItemValue();
	
	}
	

	
	
	
	
	/**
	 * 重算财务拆分
	 */
	public static void recalSplitZqfyData(BillCardPanel panel) throws BusinessException{
		
		//BillModel model2=panel.getBillModel("pk_contract_zqfycf");
		BillModel modelc=panel.getBillModel("pk_parkcontract_f");
		//获取业务页签数据
		
		BillModel modela=panel.getBillModel("pk_parkcontract_c");
		int row=modela.getRowCount();
		if(row<=0){
			return ;
		}
		
		//租约金额
		Object obj4=panel.getHeadItem("nallrent").getValueObject();
		UFDouble rentmoney = new UFDouble(obj4.toString());
		//收费项目
		Object pk_sfxm=panel.getHeadItem("pk_costproject").getValueObject();
		//税率
		Object obj1=panel.getHeadItem("ntaxrate").getValueObject();
		UFDouble taxrate=getUFdobj(obj1.toString()).div(100);
		//付款方式
		Object obj_3=panel.getHeadItem("costcycle").getValueObject();
		Integer it=Integer.valueOf(obj_3.toString());
		//处理逻辑
		for(int i=0;i<row;i++){
			//拆分业务日期
			Object obj1_1=modela.getValueObjectAt(i, "mstartdate");
			Object obj2_1=modela.getValueObjectAt(i, "menddate");
			UFDate ud_fist1=new UFDate(obj1_1.toString());
			UFDate ud_last1=new UFDate(obj2_1.toString());
			//业务拆分应收金额
			Object obj3=modela.getValueObjectAt(i, "nreceivemoney");
			UFDouble nrecmoney = new UFDouble(obj3.toString());
			//日应收金额
			UFDouble ndaym = nrecmoney.div(CalendarUtls.getBetweenTwoDays(ud_fist1,ud_last1));
			
			Object parkarea=modela.getValueObjectAt(i, "parkarea");
			Object parknum=modela.getValueObjectAt(i, "parknum");
			
			UFDate start=ud_fist1;
			UFDouble ysmny=new UFDouble(0);
			if(it==13){
				modelc.addLine();
				modelc.setValueAt(modelc.getRowCount()*10, modelc.getRowCount()-1, "rowno");
				modelc.setValueAt(parkarea, modelc.getRowCount()-1, "parkarea");
				modelc.setValueAt(parknum, modelc.getRowCount()-1, "parknum");
				modelc.setValueAt(pk_sfxm, modelc.getRowCount()-1, "pk_costproject");
				modelc.setValueAt(ud_fist1, modelc.getRowCount()-1, "paydate");
				modelc.setValueAt(ud_fist1, modelc.getRowCount()-1, "mstartdate");
				modelc.setValueAt(ud_last1, modelc.getRowCount()-1, "menddate");
				modelc.setValueAt(new UFDouble(0), modelc.getRowCount()-1, "ncollectemoney");
				//应收金额
				nrecmoney = nrecmoney.sub(ysmny,2);
				//无税金额
				UFDouble freetax = nrecmoney.div(taxrate.add(1));
				//税额
				UFDouble ntax = nrecmoney.sub(freetax);
				
				modelc.setValueAt(nrecmoney, modelc.getRowCount()-1, "nreceivemoney");
				modelc.setValueAt(freetax, modelc.getRowCount()-1, "nfreetaxmoney");
				modelc.setValueAt(ntax, modelc.getRowCount()-1, "ntaxmoney");
			}else{
				while(!start.afterDate(ud_last1)){
					//UFDate end2=CalendarUtls.getNextMonthDay(start, 1);
					UFDate end=CalendarUtls.getMaxMonthDay(start);	
					modelc.addLine();
					//应收金额
					int daycount = CalendarUtls.getBetweenTwoDays(start,end);
					UFDouble money = ndaym.multiply(daycount);
					if(CalendarUtls.isFirstDayOfMonth(start)){
						money=rentmoney;
					}
					if(!end.beforeDate(ud_last1)){//结束日期之后(拆分的最后一行)
						end=ud_last1;
						//应收金额
						UFDouble l_money = nrecmoney.sub(ysmny,2);
						//无税金额
						UFDouble freetax = l_money.div(taxrate.add(1));
						//税额
						UFDouble ntax = l_money.sub(freetax);
						//UFDouble tax = l_money.multiply(taxrate);
						
						//UFDouble ntax = tax.div(100);
						
						modelc.setValueAt(l_money, modelc.getRowCount()-1, "nreceivemoney");
						modelc.setValueAt(freetax, modelc.getRowCount()-1, "nfreetaxmoney");
						modelc.setValueAt(ntax, modelc.getRowCount()-1, "ntaxmoney");
					}else{
						//无税金额
						UFDouble freetax = money.div(taxrate.add(1));
						//税额
						UFDouble ntax = money.sub(freetax);
						//UFDouble ntax = tax.div(100);
						
						modelc.setValueAt(money, modelc.getRowCount()-1, "nreceivemoney");
						modelc.setValueAt(freetax, modelc.getRowCount()-1, "nfreetaxmoney");
						modelc.setValueAt(ntax, modelc.getRowCount()-1, "ntaxmoney");
					}
					ysmny=ysmny.add(money);
					modelc.setValueAt(modelc.getRowCount()*10, modelc.getRowCount()-1, "rowno");
					modelc.setValueAt(parkarea, modelc.getRowCount()-1, "parkarea");
					modelc.setValueAt(parknum, modelc.getRowCount()-1, "parknum");
					modelc.setValueAt(pk_sfxm, modelc.getRowCount()-1, "pk_costproject");
					modelc.setValueAt(start, modelc.getRowCount()-1, "paydate");
					modelc.setValueAt(start, modelc.getRowCount()-1, "mstartdate");
					
					
					
					if(!end.beforeDate(ud_last1)||end.equals(ud_last1)){//结束日期之后
						end=ud_last1;
					}
					
					 
					modelc.setValueAt(end, modelc.getRowCount()-1, "menddate");
					
					
					modelc.setValueAt(new UFDouble(0), modelc.getRowCount()-1, "ncollectemoney");
					
					start=CalendarUtls.getNextMonthFirstDay(start);//交替起始日期
					
					
				}
			}
			
			
		}
		modelc.loadLoadRelationItemValue();
	}
	
	private static UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	
	private static Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}

}
