package nc.ui.zl.tcl_contract.ace.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.UITable.SortItem;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class CalculateRentUtils {
	
	private static List<Map<String, Object>> paymap=new ArrayList<Map<String,Object>>();
	/**
	 * �������
	 */
	public static void recalRent(BillCardPanel panel) throws BusinessException{
		
		//���ݱ���ķ���ҳǩ���ܱ�ͷ��������
		BillModel bmodel=panel.getBillModel("pk_contract_house");
		Object oarea=bmodel.getTotalTableModel().getValueAt(0, bmodel.getBodyColByKey("narea"));
		Object nday=bmodel.getTotalTableModel().getValueAt(0, bmodel.getBodyColByKey("ndaymny"));
		Object nmonth=bmodel.getTotalTableModel().getValueAt(0, bmodel.getBodyColByKey("nmonthmny"));
		Object nyear=bmodel.getTotalTableModel().getValueAt(0, bmodel.getBodyColByKey("nyearmny"));
		
		//���±�ͷ�Żݽ������Ӧ�ս��
		BillModel bmodel2=panel.getBillModel("pk_contract_ywcf");
		Object objh1=bmodel2.getTotalTableModel().getValueAt(0, bmodel2.getBodyColByKey("nyhmny"));
		Object objh2=bmodel2.getTotalTableModel().getValueAt(0, bmodel2.getBodyColByKey("nysmny"));
		
		//���±�ͷ�ʱ�������ڷ���
		BillModel bmodel3=panel.getBillModel("pk_contract_bzj");
		Object objh11=bmodel3.getTotalTableModel().getValueAt(0, bmodel3.getBodyColByKey("nysmny"));
		BillModel bmodel4=panel.getBillModel("pk_contract_zqfycf");
		Object objh12=bmodel4.getTotalTableModel().getValueAt(0, bmodel4.getBodyColByKey("nysmny"));
		
		panel.setHeadItem("ndaymny", getUFdobj(nday));
		panel.setHeadItem("nmonthmny", getUFdobj(nmonth));
		panel.setHeadItem("nyearmny", getUFdobj(nyear));
		panel.setHeadItem("narea", getUFdobj(oarea));
		
		panel.setHeadItem("nyhmny", objh1);
		panel.setHeadItem("nysmny", objh2);
		
		panel.setHeadItem("nbzjmny", objh11);
		panel.setHeadItem("nzqmny", objh12);
	}
	
	/**
	 * �����ϸ����
	 * @param panel
	 * @throws BusinessException
	 */
	public static void recalRent2(BillCardPanel panel) throws BusinessException{
		
		BillModel model2=panel.getBillModel("pk_contract_zjmx");//��ǰҳǩ
		BillModel modelmz=panel.getBillModel("pk_contract_mzq");//������
		
		
		//������ڣ��ж������ڣ�
		Object objmz=panel.getHeadItem("is_mz").getValueObject();
		UFBoolean is_mz=new UFBoolean(objmz.toString());
		Object obj1=panel.getHeadItem("dstartdate").getValueObject();
		Object obj2=panel.getHeadItem("denddate").getValueObject();
		UFDate ht_fist=new UFDate(obj1.toString());//��ͬ��ʼ����
		UFDate ud_fist=new UFDate(obj1.toString());
		UFDate ud_last=new UFDate(obj2.toString());
		if(is_mz.booleanValue()){
			Object mzdate=modelmz.getValueAt(0, "denddate");
			if(mzdate==null){
				throw new BusinessException("�������������ڲ���Ϊ�գ�");
			}
			UFDate ud=new UFDate(mzdate.toString());
			ud_fist=CalendarUtls.getAfterFirstDay(ud);
			if(ud_fist.afterDate(ud_last)){
				throw new BusinessException("��ͬ�����쳣��");
			}
		}
		
		UFDate start=ud_fist;
		while(!start.afterDate(ud_last)){
			UFDate end=CalendarUtls.getNextMonthDay(start, 12);
			UFDate end2=CalendarUtls.getBeforeFirstDay(end);
			model2.addLine();
			model2.setValueAt(model2.getRowCount()*10, model2.getRowCount()-1, "rowno");
			model2.setValueAt(start, model2.getRowCount()-1, "dstartdate");
			if(!end2.beforeDate(ud_last)){//��������֮��
				end2=ud_last;
			}
			model2.setValueAt(end2, model2.getRowCount()-1, "denddate");
			
			start=end;//������ʼ����
		}
		
		
		calculateMnyWithMzz(panel, "pk_contract_zjmx");
		calculateMnyWithZzq(panel, "pk_contract_zjmx");
		model2.setValueAt(ht_fist, 0, "dstartdate");
		Object obj=model2.getTotalTableModel().getValueAt(0, model2.getBodyColByKey("nht2mny"));
		panel.setHeadItem("nmny", obj);
		
	}
	
	/**
	 * ���㷿����Ϣ 
	 * rows��������������
	 */
	public static void recalHouse(BillCardPanel panel,int[] rows) throws BusinessException{
		
		if(rows==null||rows.length<=0){
			return ;
		}
		
		BillModel model2=panel.getBillModel("pk_contract_house");
		//���Һ�ͬ��ͷ���޷�ʽ�����
		Object objrent=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(objrent.toString());
		Object objprice=panel.getHeadItem("nrentprice").getValueObject();
		UFDouble nprice=new UFDouble(objprice.toString());
		
		for(int i : rows){
			Object objarea=model2.getValueAt(i, "narea");
			if(objarea==null){
				return ;
			}
			UFDouble ud=new UFDouble(objarea.toString());
			
			UFDouble nyear=new UFDouble(0);
			if(rent==AbsEnumType.ZLstyle_1){//��ÿƽ����
				nyear=nprice.multiply(ud).multiply(365);
			}else if(rent==AbsEnumType.ZLstyle_2){
				nyear=nprice.multiply(365);
			}else if(rent==AbsEnumType.ZLstyle_3){
				nyear=nprice.multiply(ud).multiply(12);
			}else if(rent==AbsEnumType.ZLstyle_4){
				nyear=nprice.multiply(12);
			}else if(rent==AbsEnumType.ZLstyle_5){
				nyear=nprice.multiply(ud);
			}else if(rent==AbsEnumType.ZLstyle_6){
				nyear=nprice;
			}
			
			model2.setValueAt(nprice, i, "nprice");
			model2.setValueAt(nyear.div(new UFDouble(365),4), i, "ndaymny");
			model2.setValueAt(nyear.div(new UFDouble(12),4), i, "nmonthmny");
			model2.setValueAt(nyear, i, "nyearmny");
		}
		
	}
	
	/**
	 * ����������
	 * rows��������������
	 */
	public static void recalZzqData(BillCardPanel panel,int[] rows) throws BusinessException{
		
		if(rows==null||rows.length<=0){
			return ;
		}
		
		BillModel model2=panel.getBillModel("pk_contract_zzq");
		
		//���Һ�ͬ��ͷ���޷�ʽ
		Object objrent=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(objrent.toString());
		String key="";
		if(rent==AbsEnumType.ZLstyle_1||rent==AbsEnumType.ZLstyle_2){
			key="ndaymny";
		}else if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
			key="nmonthmny";
		}else if(rent==AbsEnumType.ZLstyle_5||rent==AbsEnumType.ZLstyle_6){
			key="nyearmny";
		}
		
		
		for(int i : rows){
			
			UFDouble nyearall=new UFDouble(0);
			UFDouble nrate=getUFdobj(model2.getValueAt(i, "nzzrate"));
			UFDouble nmny=getUFdobj(model2.getValueAt(i, "nmny"));
			UFDouble nzhrate=nrate.div(100).add(1);
			
			if(i==0){//��һ�У�Ĭ�������ͷ���
				recalRent(panel);
				Object objday=panel.getHeadItem("ndaymny").getValueObject();
				UFDouble nday=new UFDouble(objday.toString());//��ͷ�����
				Object objmonth=panel.getHeadItem("nmonthmny").getValueObject();
				UFDouble nmonth=new UFDouble(objmonth.toString());//�����
				Object objyear=panel.getHeadItem("nyearmny").getValueObject();
				UFDouble nyear=new UFDouble(objyear.toString());//�����
				UFDouble newmny = getUFdobj(panel.getHeadItem(key).getValueObject());
				
				if(rent==AbsEnumType.ZLstyle_1||rent==AbsEnumType.ZLstyle_2){
					nyearall=new UFDouble(365);
				}else if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
					nyearall=new UFDouble(12);
				}else if(rent==AbsEnumType.ZLstyle_5||rent==AbsEnumType.ZLstyle_6){
					nyearall=new UFDouble(1);
				}
				if(nmny.compareTo(new UFDouble(0))>0){//�������
					nyearall=nyearall.multiply(nmny);
					
					model2.setValueAt(nyearall.sub(nyear,4),i,"nyearzzmny");
					model2.setValueAt(nyearall, i, "nyearmny");
					
					model2.setValueAt(nyearall.div(12).sub(nmonth,4), i, "nmonthzzmny");
					model2.setValueAt(nyearall.div(12), i, "nmonthmny");
					
					model2.setValueAt(nyearall.div(365).sub(nday,4), i, "ndayzzmny");
					model2.setValueAt(nyearall.div(365), i, "ndaymny");
					
					if(nyearall.compareTo(new UFDouble(0))==0){
						model2.setValueAt(100, i, "nzzrate");
					}else{
						model2.setValueAt(nyearall.multiply(100).div(nyear).sub(new UFDouble(100),4), i, "nzzrate");
					}
					
				}else{//��������
					nyearall=nyearall.multiply(newmny);
					
					model2.setValueAt(nyearall.div(365).multiply(nrate).div(new UFDouble(100), 4), i, "ndayzzmny");
					model2.setValueAt(nyearall.div(365).multiply(nzhrate, 4), i, "ndaymny");
					model2.setValueAt(nyearall.div(12).multiply(nrate).div(new UFDouble(100), 4), i, "nmonthzzmny");
					model2.setValueAt(nyearall.div(12).multiply(nzhrate, 4), i, "nmonthmny");
					model2.setValueAt(nyearall.multiply(nrate).div(new UFDouble(100), 4), i, "nyearzzmny");
					model2.setValueAt(nyearall.multiply(nzhrate, 4), i, "nyearmny");
				}
				
			}else{//ÿ�θ�����һ����
				
				int row=i-1;
				
				Object objday=model2.getValueAt(row, "ndaymny");
				UFDouble nday=new UFDouble(objday.toString());//��һ�������
				Object objmonth=model2.getValueAt(row, "nmonthmny");
				UFDouble nmonth=new UFDouble(objmonth.toString());//�����
				Object objyear=model2.getValueAt(row, "nyearmny");
				UFDouble nyear=new UFDouble(objyear.toString());//�����
				UFDouble newmny=getUFdobj(model2.getValueAt(row, key));
				
				if(rent==AbsEnumType.ZLstyle_1||rent==AbsEnumType.ZLstyle_2){
					nyearall=new UFDouble(365);
				}else if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
					nyearall=new UFDouble(12);
				}else if(rent==AbsEnumType.ZLstyle_5||rent==AbsEnumType.ZLstyle_6){
					nyearall=new UFDouble(1);
				}
				
				if(nmny.compareTo(new UFDouble(0))>0){//�������
					nyearall=nyearall.multiply(nmny);
					
					model2.setValueAt(nyearall.sub(nyear,4),i,"nyearzzmny");
					model2.setValueAt(nyearall, i, "nyearmny");
					
					model2.setValueAt(nyearall.div(12).sub(nmonth,4), i, "nmonthzzmny");
					model2.setValueAt(nyearall.div(12), i, "nmonthmny");
					
					model2.setValueAt(nyearall.div(365).sub(nday,4), i, "ndayzzmny");
					model2.setValueAt(nyearall.div(365), i, "ndaymny");
					
					if(nyearall.compareTo(new UFDouble(0))==0){
						model2.setValueAt(100, i, "nzzrate");
					}else{
						model2.setValueAt(nyearall.multiply(100).div(nyear).sub(new UFDouble(100),4), i, "nzzrate");
					}
					
				}else{//��������
					nyearall=nyearall.multiply(newmny);
					
					model2.setValueAt(nyearall.div(365).multiply(nrate).div(new UFDouble(100), 4), i, "ndayzzmny");
					model2.setValueAt(nyearall.div(365).multiply(nzhrate, 4), i, "ndaymny");
					model2.setValueAt(nyearall.div(12).multiply(nrate).div(new UFDouble(100), 4), i, "nmonthzzmny");
					model2.setValueAt(nyearall.div(12).multiply(nzhrate, 4), i, "nmonthmny");
					model2.setValueAt(nyearall.multiply(nrate).div(new UFDouble(100), 4), i, "nyearzzmny");
					model2.setValueAt(nyearall.multiply(nzhrate, 4), i, "nyearmny");
				}
			}
		}
		
	}
	
	/**
	 * ����ҵ����(δ����)
	 */
	public static void recalSplitYwcfData(BillCardPanel panel) throws BusinessException{
		
		BillModel model2=panel.getBillModel("pk_contract_ywcf");//��ǰҳǩ
		//��ȡ����ҳǩ����
		BillModel model1=panel.getBillModel("pk_contract_house");
		int row=model1.getRowCount();
		if(row<=0){
			return ;
		}
		
		Object pk_cust=panel.getHeadItem("pk_customer").getValueCache();
		Object pk_sfxm=panel.getHeadItem("pk_costproject").getValueCache();
		//�������
		Object obj1=panel.getHeadItem("dstartdate").getValueObject();
		Object obj2=panel.getHeadItem("denddate").getValueObject();
		UFDate ud_fist=new UFDate(obj1.toString());
		UFDate ud_last=new UFDate(obj2.toString());
		//���ʽ
		Object obj3=panel.getHeadItem("paystyle").getValueObject();
		Integer it=Integer.valueOf(obj3.toString());
		//�����߼�
		for(int i=0;i<row;i++){
			
			Object pk_house=getColvalue(model1.getValueObjectAt(i, "pk_house"));
			UFDouble daymny=getUFdobj(model1.getValueAt(i, "ndaymny"));
			
			if(it==0){//һ���Ը���
				model2.addLine();
				model2.setValueAt(model2.getRowCount()*10, model2.getRowCount()-1, "rowno");
				model2.setValueAt(pk_cust, model2.getRowCount()-1, "pk_customer");
				model2.setValueAt(pk_house, model2.getRowCount()-1, "pk_house");
				model2.setValueAt(pk_sfxm, model2.getRowCount()-1, "pk_costproject");
				model2.setValueAt(ud_fist, model2.getRowCount()-1, "dstartdate");
				model2.setValueAt(ud_last, model2.getRowCount()-1, "denddate");
				model2.setValueAt(ud_fist, model2.getRowCount()-1, "drecdate");
				int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
				model2.setValueAt(daymny.multiply(day), model2.getRowCount()-1, "nqzmny");
				model2.setValueAt(daymny.multiply(day), model2.getRowCount()-1, "nysmny");
				model2.setValueAt(new UFDouble(0), model2.getRowCount()-1, "nskmny");
			}else{//�������¸���
				
				UFDate start=ud_fist;
				while(!start.afterDate(ud_last)){
					UFDate end=CalendarUtls.getNextMonthDay(start, it);
					UFDate end2=CalendarUtls.getBeforeFirstDay(end);
					model2.addLine();
					model2.setValueAt(model2.getRowCount()*10, model2.getRowCount()-1, "rowno");
					model2.setValueAt(pk_cust, model2.getRowCount()-1, "pk_customer");
					model2.setValueAt(pk_house, model2.getRowCount()-1, "pk_house");
					model2.setValueAt(pk_sfxm, model2.getRowCount()-1, "pk_costproject");
					model2.setValueAt(start, model2.getRowCount()-1, "drecdate");
					model2.setValueAt(start, model2.getRowCount()-1, "dstartdate");
					if(!end2.beforeDate(ud_last)){//��������֮��
						end2=ud_last;
					}
					model2.setValueAt(end2, model2.getRowCount()-1, "denddate");
					int day=CalendarUtls.getBetweenTwoDays(start, end2);
					model2.setValueAt(daymny.multiply(day), model2.getRowCount()-1, "nqzmny");
					model2.setValueAt(daymny.multiply(day), model2.getRowCount()-1, "nysmny");
					model2.setValueAt(new UFDouble(0), model2.getRowCount()-1, "nskmny");
					
					start=end;//������ʼ����
				}
			}
		}
		
		//�������·�
		String tab="pk_contract_ywcf";
		calculatePeriod(panel,tab);
		//���������������Ľ��
		calculateMnyWithMzqAndZzq(panel,tab);
		
		model2.loadLoadRelationItemValue();
	
	}
	
	/**
	 * ����ҵ����
	 */
	public static void recalSplitYwcfDataNew(BillCardPanel panel) throws BusinessException{
		
		BillModel model2=panel.getBillModel("pk_contract_ywcf");//��ǰҳǩ
		
		Object pk_cust=panel.getHeadItem("pk_customer").getValueObject();
		Object pk_sfxm=panel.getHeadItem("pk_costproject").getValueObject();
		Object rentstyle=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(rentstyle.toString());
		Object area=panel.getHeadItem("narea").getValueObject();
		UFDouble narea=getUFdobj(area);
		
		//��ȡ����ҳǩ����
		BillModel model1=panel.getBillModel("pk_contract_house");
		int row=model1.getRowCount();
		if(row<=0){
			return ;
		}
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		//���ʽ���
		List<Map<String, Object>> paymap = recalPaystyle(panel);
		//��ÿ�����ʽ����ҵ����
		List<Map<String, Object>> ywmap = recalYwcfByPaystyle(panel,paymap);
		
		//�۳������ڽ��
		//UFDouble ljmny=new UFDouble(0);
		for(Map<String, Object> map:ywmap){
			
			UFDate ud_first=new UFDate(map.get("dstartdate").toString());
			UFDate ud_last=new UFDate(map.get("denddate").toString());
			UFDouble nysmny=getUFdobj(map.get("nysmny"));
			String hasmz=map.get("ishasmz").toString();
			boolean ishasmz=hasmz.equals("Y")?true:false;
			
			//��ȡ��ǰҵ�����ڿ۳���Ľ��
			UFDouble mzmny=new UFDouble(0);
			if(!ishasmz){
				mzmny=getMzzGatherMny(panel, ud_first, ud_last);
			}
			UFDouble ud=nysmny.sub(mzmny);
			if(ud.compareTo(new UFDouble(0))<0){
				ud=new UFDouble(0);
			}
			map.put("nysmny", ud);
		}
		
		//�˴����ô���ҳǩ�߼�
		
		//���������
		boolean flag=true;
		if(rent==AbsEnumType.ZLstyle_1||rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_5){//ƽ����
			flag=false;
		}else if(rent==AbsEnumType.ZLstyle_2||rent==AbsEnumType.ZLstyle_4||rent==AbsEnumType.ZLstyle_6){//��
			flag=true;
		}
		
		for(Map<String, Object> map:ywmap){
			UFDate ud_first=new UFDate(map.get("dstartdate").toString());
			UFDate ud_last=new UFDate(map.get("denddate").toString());
			UFDouble nysmny=getUFdobj(map.get("nysmny"));
			
			UFDouble ljfc=new UFDouble(0);
			
			for(int i=0;i<row;i++){
				
				model2.addLine();
				UFDouble narea2=getUFdobj(model1.getValueAt(i, "narea"));
				Object house=getColvalue(model1.getValueObjectAt(i, "pk_house"));
				model2.setValueAt(model2.getRowCount()*10, model2.getRowCount()-1, "rowno");
				model2.setValueAt(pk_cust, model2.getRowCount()-1, "pk_customer");
				model2.setValueAt(house, model2.getRowCount()-1, "pk_house");
				model2.setValueAt(pk_sfxm, model2.getRowCount()-1, "pk_costproject");
				model2.setValueAt(ud_first, model2.getRowCount()-1, "dstartdate");
				model2.setValueAt(ud_last, model2.getRowCount()-1, "denddate");
				model2.setValueAt(ud_first, model2.getRowCount()-1, "drecdate");
				
				if(i==row-1){//���һ�е���
					model2.setValueAt(nysmny.sub(ljfc), model2.getRowCount()-1, "nqzmny");
					model2.setValueAt(nysmny.sub(ljfc), model2.getRowCount()-1, "nysmny");
					model2.setValueAt(new UFDouble(0), model2.getRowCount()-1, "nskmny");
					ljfc=new UFDouble(0);
					break ;
				}
				
				UFDouble nmny=new UFDouble(0);
				if(flag){//���׼���
					nmny=nysmny.div(new UFDouble(row));
				}else{
					nmny=nysmny.multiply(narea2).div(narea);
				}
				
				if(ndignum==AbsEnumType.FeeScale2_JW){
					nmny=new UFDouble(Math.ceil(nmny.doubleValue()));
				}else{
					nmny=nmny.add(new UFDouble(0), ndignum);
				}
				
				model2.setValueAt(nmny, model2.getRowCount()-1, "nqzmny");
				model2.setValueAt(nmny, model2.getRowCount()-1, "nysmny");
				model2.setValueAt(new UFDouble(0), model2.getRowCount()-1, "nskmny");
				ljfc=ljfc.add(nmny);
			}
		}
		
		//��������
		Object obj1=panel.getHeadItem("dstartdate").getValueObject();
		
		BillModel modelmz=panel.getBillModel("pk_contract_mzq");//��ǰҳǩ
		Object objmz=panel.getHeadItem("is_mz").getValueObject();
		UFBoolean is_mz=new UFBoolean(objmz.toString());
		if(is_mz.booleanValue()){
			Object mzdate=modelmz.getValueAt(0, "denddate");
			UFDate ud=new UFDate(mzdate.toString());
			UFDate pay_fist=CalendarUtls.getAfterFirstDay(ud);
			for(int i=0;i<model2.getRowCount();i++){
				Object obj=model2.getValueAt(i, "dstartdate");
				if(obj.equals(pay_fist)){
					model2.setValueAt(obj1, i, "dstartdate");
					model2.setValueAt(obj1, i, "drecdate");
				}
			}
		}
		
		//�������·�
		String tab="pk_contract_ywcf";
		calculatePeriod(panel,tab);
		
		model2.loadLoadRelationItemValue();
		
		//����
		int col1=model2.getBodyColByKey("pk_house");
		int col2=model2.getBodyColByKey("dstartdate");
		List<SortItem> list=new ArrayList<SortItem>();
		list.add(new SortItem(col2,true));
		list.add(new SortItem(col1,true));
		
		model2.sortByColumns(list);
		
		for(int i=0;i<model2.getRowCount();i++){
			model2.setValueAt((i+1)*10, i, "rowno");
		}
	}
	
	/**
	 * �����ʽ�������
	 * @param panel
	 * @return
	 * @throws BusinessException
	 */
	private static List<Map<String, Object>> recalPaystyle(BillCardPanel panel) throws BusinessException{
		
		paymap.clear();
		//�����ʽ���
		BillModel modelf=panel.getBillModel("pk_contract_fkmx");
		BillModel modelmz=panel.getBillModel("pk_contract_mzq");
		//Object mny = panel.getHeadItem("nmny").getValueObject();
		//UFDouble nmny=getUFdobj(mny);
		
		List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
		int frow=modelf.getRowCount();
		//�������(���ʽ)(�жϿ۳�������)
		Object obj1=panel.getHeadItem("dstartdate").getValueObject();
		Object obj2=panel.getHeadItem("denddate").getValueObject();
		Object obj3=panel.getHeadItem("paystyle").getValueObject();
		Object objmz=panel.getHeadItem("is_mz").getValueObject();
		UFBoolean is_mz=new UFBoolean(objmz.toString());
		
		UFDate pay_fist=new UFDate(obj1.toString());
		UFDate ud_last=new UFDate(obj2.toString());
		if(is_mz.booleanValue()){
			Object mzdate=modelmz.getValueAt(0, "denddate");
			UFDate ud=new UFDate(mzdate.toString());
			pay_fist=CalendarUtls.getAfterFirstDay(ud);
		}
		
		UFDouble cfud=new UFDouble(0);
		for(int i=0;i<frow;i++){

			Object obj=modelf.getValueAt(i, "dstartdate");
			Object objpay=getColvalue(modelf.getValueObjectAt(i, "paystyle"));
			UFDate ud3=new UFDate(obj.toString());
			UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("dstartdate", pay_fist);
			map.put("denddate", ud4);
			map.put("paystyle", obj3);
			UFDouble ud=calculateMnyBetweenDate(panel, pay_fist, ud4);
			map.put("nysmny", ud);
			cfud=cfud.add(ud);
			listmap.add(map);
		
			pay_fist=ud3;//��������
			obj3=objpay;//���渶�ʽ
		}
		//�������һ��
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dstartdate", pay_fist);
		map.put("denddate", ud_last);
		map.put("paystyle", obj3);
		UFDouble ud2=calculateMnyBetweenDate(panel, pay_fist, ud_last);
		map.put("nysmny", ud2);//������
		listmap.add(map);
		
		paymap=listmap;
		return listmap;
	}
	
	/**
	 * �����ʽ���ڲ������
	 * @param panel
	 * @return
	 * @throws BusinessException
	 */
	private static List<Map<String, Object>> recalYwcfByPaystyle(BillCardPanel panel,List<Map<String, Object>> paymap) throws BusinessException{
		
		List<Map<String, Object>> ywmap=new ArrayList<Map<String,Object>>();
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		for(Map<String, Object> map : paymap){
			
			UFDate ud_first=new UFDate(map.get("dstartdate").toString());
			UFDate ud_last=new UFDate(map.get("denddate").toString());
			Integer pays=Integer.valueOf(map.get("paystyle").toString());
			UFDouble nysmny=getUFdobj(map.get("nysmny"));
			
			
			if(pays==0){//һ���Ը���
				
				Map<String, Object> map2=new HashMap<String, Object>();
				
				map2.put("dstartdate", ud_first);
				map2.put("denddate", ud_last);
				map2.put("nysmny", nysmny);
				map2.put("ishasmz", "Y");//�Ƿ����������
				ywmap.add(map2);
				
			}else{//�������¸���
				
				//UFDouble allmny=new UFDouble(0);
				UFDate start=ud_first;
				while(!start.afterDate(ud_last)){
					
					Map<String, Object> map2=new HashMap<String, Object>();
					UFDate end=CalendarUtls.getNextMonthDay(start, pays);
					UFDate end2=CalendarUtls.getBeforeFirstDay(end);
					
					UFDouble nmny=new UFDouble(0);
					map2.put("dstartdate", start);
					if(!end2.beforeDate(ud_last)){//��������֮��
						boolean is_same=false;
						if(end2.isSameDate(ud_last)){
							is_same=true;
						}
						end2=ud_last;
						//nmny=nysmny.sub(allmny);//����(�Զ����㾫��)
						//�ж������Ƿ���꣨������꣬�����㣬���򰴸��������
						boolean flag=isStrideYear(panel, start, end2);
						UFDouble ud=new UFDouble(0);
						if(flag||!is_same){//��������ڲ��ȶ��������
							ud=calculateMnyBetweenDate(panel, start, end2);
							map2.put("ishasmz", "Y");
						}else{
							UFDouble nyearmny=getYearMny(panel, start);
							if(ndignum==AbsEnumType.FeeScale2_JW){
								ud=nyearmny.multiply(pays).div(new UFDouble(12));
								ud=new UFDouble(Math.ceil(ud.doubleValue()));
							}else{
								ud=nyearmny.multiply(pays).div(new UFDouble(12),ndignum);
							}
							map2.put("ishasmz", "N");
						}
						nmny=ud;
					}else{
						//�ж������Ƿ���꣨������꣬�����㣬���򰴸��������
						boolean flag=isStrideYear(panel, start, end2);
						UFDouble ud=new UFDouble(0);
						if(flag){
							ud=calculateMnyBetweenDate(panel, start, end2);
							map2.put("ishasmz", "Y");
						}else{
							UFDouble nyearmny=getYearMny(panel, start);
							if(ndignum==AbsEnumType.FeeScale2_JW){
								ud=nyearmny.multiply(pays).div(new UFDouble(12));
								ud=new UFDouble(Math.ceil(ud.doubleValue()));
							}else{
								ud=nyearmny.multiply(pays).div(new UFDouble(12),ndignum);
							}
							map2.put("ishasmz", "N");
						}
						nmny=ud;
						//allmny=allmny.add(ud);
					}
					map2.put("denddate", end2);
					map2.put("nysmny", nmny);
					ywmap.add(map2);
				
					start=end;//������ʼ����
				}
			}
		}
		
		return ywmap;
	}
	
	/**
	 * �������·ݺͽ��
	 * @param panel
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	private static void calculatePeriod(BillCardPanel panel,String tabcode) throws BusinessException{
		
		//�������·�
		Object obj1=panel.getHeadItem("dstartdate").getValueObject();
		Object obj2=panel.getHeadItem("denddate").getValueObject();
		String str1=obj1.toString().substring(0, 7);
		String str2=obj2.toString().substring(0, 7);
		
		String sql="select h.yearmth,h.pk_accperiodmonth from bd_accperiodmonth h " +
				"where h.yearmth>='"+str1+"' and h.yearmth<='"+str2+"' and nvl(dr,0)=0 and " +
				"pk_accperiodscheme='"+AbsEnumType.Period+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String,Object>> maplist=(List<Map<String,Object>>)iQ.executeQuery(sql, new MapListProcessor());
		
		Map<Object,Object> map2=new HashMap<Object, Object>();
		for(Map<String,Object> map:maplist){
			map2.put(map.get("yearmth"), map.get("pk_accperiodmonth"));
		}
		
		BillModel model=panel.getBillModel(tabcode);
		int row=model.getRowCount();
		for(int i=0;i<row;i++){
			Object obj=model.getValueAt(i, "dstartdate");
			String sss=obj.toString().substring(0, 7);
			Object objkj=map2.get(sss);
			model.setValueAt(objkj, i, "pk_month");
		}
		
	}
	
	/**
	 * ����ÿ����¼�����ս����������������(�ò���)
	 * @param panel
	 * @throws BusinessException
	 */
	private static void calculateMnyWithMzqAndZzq(BillCardPanel panel,String tabcode) throws BusinessException{
		
		BillModel hmodel=panel.getBillModel("pk_contract_house");
		int hrow=hmodel.getRowCount();
		Map<Object, UFDouble> map=new HashMap<Object, UFDouble>();//��¼���������
		for(int i=0;i<hrow;i++){
			Object objhouse=getColvalue(hmodel.getValueObjectAt(i, "pk_house"));
			UFDouble ud=getUFdobj(hmodel.getValueAt(i, "ndaymny"));
			map.put(objhouse, ud);
		}
		
		//������ҳǩ
		BillModel zmodel=panel.getBillModel("pk_contract_zzq");
		int zrow=zmodel.getRowCount();
		
		//������ҳǩ
		BillModel mmodel=panel.getBillModel("pk_contract_mzq");
		int mrow=mmodel.getRowCount();
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		//ҵ����ҳǩ
		BillModel model=panel.getBillModel(tabcode);
		int row=model.getRowCount();
		for(int i=0;i<row;i++){
			Object objhouse=getColvalue(model.getValueObjectAt(i, "pk_house"));
			UFDouble daymny=map.get(objhouse);//�����
			Object obj1=model.getValueAt(i, "dstartdate");
			Object obj2=model.getValueAt(i, "denddate");
			UFDate ud_fist=new UFDate(obj1.toString());
			UFDate ud_last=new UFDate(obj2.toString());
			//��������ȥ��ֵ�ǰ��ʼ���ڣ���������
			List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
			
			for(int k=0;k<zrow;k++){
				
				Object obj3=zmodel.getValueAt(k, "dstartdate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDouble zzrate=getUFdobj(zmodel.getValueAt(k, "nzzrate"));
				UFDouble mny=getUFdobj(zmodel.getValueAt(k, "nmny"));
				//UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(k, "ndaymny"));
				if(ud3.afterDate(ud_last)){//���������ڼ�֮�󣬲�������
					break ;
				}else if(ud3.beforeDate(ud_fist)){//��������֮ǰ������������
					//daymny=daymny.multiply(zzrate).div(100).add(daymny);
					if(mny.compareTo(new UFDouble(0))>0){
						daymny=mny;
					}else{
						daymny=daymny.multiply(zzrate).div(100).add(daymny);
					}
				}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
					//���
					UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
					if(!ud4.beforeDate(ud_fist)){//ǰ�벿�����ϴ����
						Map<String, Object> mapzj=new HashMap<String, Object>();
						mapzj.put("dstartdate", ud_fist);
						mapzj.put("denddate", ud4);
						mapzj.put("ndaymny", daymny);
						int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
						mapzj.put("nday", day);
						listmap.add(mapzj);
					}
					
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
					ud_fist=ud3;
				}
				
			}
			//����ʣ�µ���ʼ��������
			Map<String, Object> mapzj=new HashMap<String, Object>();
			mapzj.put("dstartdate", ud_fist);
			mapzj.put("denddate", ud_last);
			mapzj.put("ndaymny", daymny);
			int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
			mapzj.put("nday", day);
			listmap.add(mapzj);
			
			
			//�۳�������
			for(Map<String, Object> maps:listmap){
				
				UFDate ud1=new UFDate(maps.get("dstartdate").toString());
				UFDate ud2=new UFDate(maps.get("denddate").toString());
				Integer days=Integer.valueOf(maps.get("nday").toString());
				
				for(int k=0;k<mrow;k++){
					
					Object obj3=mmodel.getValueAt(k, "dstartdate");
					Object obj4=mmodel.getValueAt(k, "denddate");
					UFDate ud3=new UFDate(obj3.toString());
					UFDate ud4=new UFDate(obj4.toString());
					UFDate start=new UFDate();
					UFDate end=new UFDate();
					
					if(ud3.afterDate(ud2)||ud4.beforeDate(ud1)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
						continue ;
					}
									
					if(ud3.beforeDate(ud1)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
						start=ud1;
					}else{
						start=ud3;
					}
					
					if(ud4.afterDate(ud2)){
						end=ud2;
					}else{
						end=ud4;
					}
					
					int mzday=CalendarUtls.getBetweenTwoDays(start, end);
					
					days=days-mzday;
				}
				
				maps.put("nday", days);
			}
			
			
			//�����ܽ��
			UFDouble udrow=new UFDouble(0);
			for(Map<String, Object> maps:listmap){
				
				UFDouble price=getUFdobj(maps.get("ndaymny"));
				Integer days=Integer.valueOf(maps.get("nday").toString());
				udrow=price.multiply(days).add(udrow);
			}
			if(ndignum==AbsEnumType.FeeScale2_JW){
				udrow=new UFDouble(Math.ceil(udrow.doubleValue()));
			}else{
				udrow=udrow.add(new UFDouble(0), ndignum);
			}
			
			if(tabcode.equals("pk_contract_ywcf")){
				model.setValueAt(udrow, i, "nqzmny");//���޽��
				model.setValueAt(udrow, i, "nysmny");//Ӧ�ս��
			}else if(tabcode.equals("pk_contract_cwcf")){
				UFDouble udyh=getUFdobj(model.getValueAt(i, "nysmny"));
				model.setValueAt(udrow.sub(udyh), i, "nysmny");//Ӧ�ս��
			}
			
		}
	}
	
	
	/**
	 * �ж����������ҳǩ�Ƿ����
	 * @return
	 */
	private static boolean isStrideYear(BillCardPanel panel,UFDate start,UFDate end){
		
		boolean flag=true;
		
		BillModel model=panel.getBillModel("pk_contract_zjmx");
		int row=model.getRowCount();
		if(row<=0){
			return true;
		}
		for(int i=0;i<row;i++){
			Object obj3=model.getValueAt(i, "dstartdate");
			Object obj4=model.getValueAt(i, "denddate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDate ud4=new UFDate(obj4.toString());
			if(!start.beforeDate(ud3)&&!end.afterDate(ud4)){//�����ڼ��ڣ�����
				return false;
			}
		}
		
		return flag;
	}
	
	/**
	 * ��ȡĳ����ʼ���������ҳǩ�������
	 * @return
	 */
	private static UFDouble getYearMny(BillCardPanel panel,UFDate start){
		
		
		BillModel model=panel.getBillModel("pk_contract_zjmx");
		int row=model.getRowCount();
		if(row<=0){
			return new UFDouble(0);
		}
		
		for(int i=0;i<row;i++){
			Object obj3=model.getValueAt(i, "dstartdate");
			Object obj4=model.getValueAt(i, "denddate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDate ud4=new UFDate(obj4.toString());
			if(!start.beforeDate(ud3)&&!start.afterDate(ud4)){//�����ڼ��ڣ�����
				return getUFdobj(model.getValueAt(i, "nyear2mny"));
			}
		}
		
		return new UFDouble(0);
	}
	
	/**
	 * ��ȡĳ��ʱ��������ڣ����������⣩�Ľ��(���������ڣ�������)
	 * @return
	 */
	private static UFDouble getMzzGatherMny(BillCardPanel panel,UFDate ud_fist,UFDate ud_last){
		
		//������ҳǩ
		BillModel zmodel=panel.getBillModel("pk_contract_zzq");
		int zrow=zmodel.getRowCount();
		
		//������ҳǩ
		BillModel mmodel=panel.getBillModel("pk_contract_mzq");
		int mrow=mmodel.getRowCount();
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		Object objrent=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(objrent.toString());
		boolean ismonth=false;
		if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
			ismonth=true;
		}
		
		Object objday=panel.getHeadItem("ndaymny").getValueObject();
		Object objmon=panel.getHeadItem("nmonthmny").getValueObject();
		
		//====================================================��������=============================================================================
		
		UFDate ud_fist2=ud_fist;
		UFDate ud_last2=ud_last;
		
		UFDouble mzmny=new UFDouble(0);
		//�ж������ڵļ��㷽ʽ
		for(int k=0;k<mrow;k++){
			
			boolean ismonthmz=false;
			
			Object objdef5=mmodel.getValueAt(k, "vdef5");
			if(objdef5!=null){
				ismonthmz=true;
			}
			
			Object obj3=mmodel.getValueAt(k, "dstartdate");
			Object obj4=mmodel.getValueAt(k, "denddate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDate ud4=new UFDate(obj4.toString());
			UFDate start=new UFDate();
			UFDate end=new UFDate();
			
			if(ud3.afterDate(ud_last2)||ud4.beforeDate(ud_fist2)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
				continue ;
			}
							
			if(ud3.beforeDate(ud_fist2)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
				start=ud_fist2;
				ismonthmz=false;
			}else{
				start=ud3;
			}
			
			if(ud4.afterDate(ud_last2)){
				ismonthmz=false;
				end=ud_last2;
			}else{
				end=ud4;
			}
			
			UFDouble daymny2=getUFdobj(objday);
			UFDouble monthmny2=getUFdobj(objmon);
			UFDouble mzmny2=new UFDouble(0);
			List<Map<String, Object>> listmap2=new ArrayList<Map<String,Object>>();
			for(int kk=0;kk<zrow;kk++){
				
				Object obj33=zmodel.getValueAt(kk, "dstartdate");
				UFDate ud33=new UFDate(obj33.toString());
				UFDouble zzrate=getUFdobj(zmodel.getValueAt(kk, "nzzrate"));
				UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(kk, "ndaymny"));
				UFDouble zzmonmny=getUFdobj(zmodel.getValueAt(kk, "nmonthmny"));
				UFDouble mny=getUFdobj(zmodel.getValueAt(kk, "nmny"));
				if(ud33.afterDate(end)){//��������������֮�󣬲�������
					continue ;
				}else if(ud33.beforeDate(start)){//��������֮ǰ������������
					//daymny=daymny.multiply(zzrate).div(100).add(daymny);
					if(mny.compareTo(new UFDouble(0))>0){
						daymny2=zzdaymny;
						monthmny2=zzmonmny;
					}else{
						daymny2=daymny2.multiply(zzrate).div(100).add(daymny2);
						monthmny2=monthmny2.multiply(zzrate).div(100).add(monthmny2);
					}
				}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
					//���
					UFDate ud44=CalendarUtls.getBeforeFirstDay(ud33);
					if(!ud44.beforeDate(start)){//ǰ�벿�����ϴ����
						Map<String, Object> mapzj2=new HashMap<String, Object>();
						mapzj2.put("dstartdate", start);
						mapzj2.put("denddate", ud44);
						mapzj2.put("ndaymny", daymny2);
						int day2=CalendarUtls.getBetweenTwoDays(start, ud44);
						mapzj2.put("nday", day2);
						listmap2.add(mapzj2);
						ismonthmz=false;
					}
					
					if(mny.compareTo(new UFDouble(0))>0){
						daymny2=zzdaymny;
						monthmny2=zzmonmny;
					}else{
						daymny2=daymny2.multiply(zzrate).div(100).add(daymny2);
						monthmny2=monthmny2.multiply(zzrate).div(100).add(monthmny2);
					}
					start=ud33;
				}
				
			}
			
			//����ʣ�µ���ʼ��������
			Map<String, Object> mapzj2=new HashMap<String, Object>();
			mapzj2.put("dstartdate", start);
			mapzj2.put("denddate", end);
			mapzj2.put("ndaymny", daymny2);
			int day2=CalendarUtls.getBetweenTwoDays(start, end);
			mapzj2.put("nday", day2);
			listmap2.add(mapzj2);
			
			//�ж��Ƿ�������
			if(ismonthmz&&ismonth){
				mzmny2=mzmny2.add(monthmny2.multiply(getUFdobj(objdef5)));
			}else{
				for(Map<String, Object> maps:listmap2){
					UFDouble price=getUFdobj(maps.get("ndaymny"));
					Integer days=Integer.valueOf(maps.get("nday").toString());
					mzmny2=price.multiply(days).add(mzmny2);
				}
			}
			
			mzmny=mzmny.add(mzmny2);
		}
		/*//��������ȥ��ֵ�ǰ��ʼ���ڣ���������
		List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
		
		for(int k=0;k<zrow;k++){
			
			Object obj3=zmodel.getValueAt(k, "dstartdate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDouble zzrate=getUFdobj(zmodel.getValueAt(k, "nzzrate"));
			UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(k, "ndaymny"));
			UFDouble mny=getUFdobj(zmodel.getValueAt(k, "nmny"));
			if(ud3.afterDate(ud_last)){//���������ڼ�֮�󣬲�������
				break ;
			}else if(ud3.beforeDate(ud_fist)){//��������֮ǰ������������
				//daymny=daymny.multiply(zzrate).div(100).add(daymny);
				if(mny.compareTo(new UFDouble(0))>0){
					daymny=zzdaymny;
				}else{
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
				}
			}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
				//���
				UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
				if(!ud4.beforeDate(ud_fist)){//ǰ�벿�����ϴ����
					Map<String, Object> mapzj=new HashMap<String, Object>();
					mapzj.put("dstartdate", ud_fist);
					mapzj.put("denddate", ud4);
					mapzj.put("ndaymny", daymny);
					int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
					mapzj.put("nday", day);
					listmap.add(mapzj);
				}
				
				if(mny.compareTo(new UFDouble(0))>0){
					daymny=zzdaymny;
				}else{
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
				}
				ud_fist=ud3;
			}
			
		}	
			
		//����ʣ�µ���ʼ��������
		Map<String, Object> mapzj=new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist);
		mapzj.put("denddate", ud_last);
		mapzj.put("ndaymny", daymny);
		int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
		mapzj.put("nday", day);
		listmap.add(mapzj);	
		
		//����������
		for(Map<String, Object> maps:listmap){
			
			UFDate ud1=new UFDate(maps.get("dstartdate").toString());
			UFDate ud2=new UFDate(maps.get("denddate").toString());
			UFDouble dmny=getUFdobj(maps.get("ndaymny"));
			
			for(int k=0;k<mrow;k++){
				
				Object obj3=mmodel.getValueAt(k, "dstartdate");
				Object obj4=mmodel.getValueAt(k, "denddate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDate ud4=new UFDate(obj4.toString());
				UFDate start=new UFDate();
				UFDate end=new UFDate();
				
				if(ud3.afterDate(ud2)||ud4.beforeDate(ud1)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
					continue ;
				}
								
				if(ud3.beforeDate(ud1)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
					start=ud1;
				}else{
					start=ud3;
				}
				
				if(ud4.afterDate(ud2)){
					end=ud2;
				}else{
					end=ud4;
				}
				
				int mzday=CalendarUtls.getBetweenTwoDays(start, end);
				mzmny=mzmny.add(dmny.multiply(mzday));
				
				if(ndignum==AbsEnumType.FeeScale2_JW){
					mzmny=new UFDouble(Math.ceil(mzmny.doubleValue()));
				}else{
					mzmny=mzmny.add(new UFDouble(0), ndignum);
				}
			}
		}
	
		*/
		if(ndignum==AbsEnumType.FeeScale2_JW){
			mzmny=new UFDouble(Math.ceil(mzmny.doubleValue()));
		}else{
			mzmny=mzmny.add(new UFDouble(0), ndignum);
		}
		return mzmny;
	}
	
	/**
	 * ����ÿ������𣨲������⣩�������ϸҳǩ��
	 * @param panel
	 * @throws BusinessException
	 */
	private static void calculateMnyWithZzq(BillCardPanel panel,String tabcode) throws BusinessException{
		
		//������ҳǩ
		BillModel zmodel=panel.getBillModel("pk_contract_zzq");
		int zrow=zmodel.getRowCount();
		
		//Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		//Integer ndignum=Integer.valueOf(objtype.toString());
		Object objrent=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(objrent.toString());
		boolean ismonth=false;
		if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
			ismonth=true;
		}
		
		//�����ҳǩ
		BillModel model=panel.getBillModel(tabcode);
		int row=model.getRowCount();
		Object objday=panel.getHeadItem("ndaymny").getValueObject();
		Object objmon=panel.getHeadItem("nmonthmny").getValueObject();
		
		for(int i=0;i<row;i++){
			
			boolean ishaszz=false;
			
			UFDouble daymny=getUFdobj(objday);//���������
			UFDouble monthmny=getUFdobj(objmon);//���������
			
			Object obj1=model.getValueAt(i, "dstartdate");
			UFDate ud_fist=new UFDate(obj1.toString());
			UFDate end=CalendarUtls.getNextMonthDay(ud_fist, 12);
			UFDate end2=CalendarUtls.getBeforeFirstDay(end);
			UFDate ud_last=end2;
			//��������ȥ��ֵ�ǰ��ʼ���ڣ���������
			List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
			
			for(int k=0;k<zrow;k++){
				
				Object obj3=zmodel.getValueAt(k, "dstartdate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDouble zzrate=getUFdobj(zmodel.getValueAt(k, "nzzrate"));
				UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(k, "ndaymny"));
				UFDouble zzmonmny=getUFdobj(zmodel.getValueAt(k, "nmonthmny"));
				UFDouble mny=getUFdobj(zmodel.getValueAt(k, "nmny"));
				if(ud3.afterDate(ud_last)){//���������ڼ�֮�󣬲�������
					break ;
				}else if(ud3.beforeDate(ud_fist)){//��������֮ǰ������������
					//daymny=daymny.multiply(zzrate).div(100).add(daymny);
					if(mny.compareTo(new UFDouble(0))>0){
						daymny=zzdaymny;
						monthmny=zzmonmny;
					}else{
						daymny=daymny.multiply(zzrate).div(100).add(daymny);
						monthmny=monthmny.multiply(zzrate).div(100).add(monthmny);
					}
				}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
					//���
					UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
					if(!ud4.beforeDate(ud_fist)){//ǰ�벿�����ϴ����
						Map<String, Object> mapzj=new HashMap<String, Object>();
						mapzj.put("dstartdate", ud_fist);
						mapzj.put("denddate", ud4);
						mapzj.put("ndaymny", daymny);
						int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
						mapzj.put("nday", day);
						listmap.add(mapzj);
						ishaszz=true;
					}
					
					if(mny.compareTo(new UFDouble(0))>0){
						daymny=zzdaymny;
						monthmny=zzmonmny;
					}else{
						daymny=daymny.multiply(zzrate).div(100).add(daymny);
						monthmny=monthmny.multiply(zzrate).div(100).add(monthmny);
					}
					ud_fist=ud3;
				}
				
			}
			//����ʣ�µ���ʼ��������
			Map<String, Object> mapzj=new HashMap<String, Object>();
			mapzj.put("dstartdate", ud_fist);
			mapzj.put("denddate", ud_last);
			mapzj.put("ndaymny", daymny);
			int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
			mapzj.put("nday", day);
			listmap.add(mapzj);
			
			//�����ܽ��ж��Ƿ����������
			UFDouble udrow=new UFDouble(0);
			
			if(ismonth&&!ishaszz){//���¼��㲢��û�и�����û��������(��������ʼ����)
				udrow=monthmny.multiply(new UFDouble(12),4);
			}else{
				for(Map<String, Object> maps:listmap){
					
					UFDouble price=getUFdobj(maps.get("ndaymny"));
					Integer days=Integer.valueOf(maps.get("nday").toString());
					udrow=price.multiply(days).add(udrow,4);
				}
			}
			
			
			/*if(ndignum==AbsEnumType.FeeScale2_JW){
				udrow=new UFDouble(Math.ceil(udrow.doubleValue()));
			}else{
				udrow=udrow.add(new UFDouble(0), ndignum);
			}*/
			
			model.setValueAt(udrow, i, "nyearmny");//�����
			model.setValueAt(udrow, i, "nyear2mny");
			model.setValueAt(new UFDouble(0), i, "nyear1mny");
		}
	}
	
	/**
	 * ����ÿ���ͬ�������⣩(�����ϸҳǩ)
	 * @param panel
	 * @throws BusinessException
	 */
	private static void calculateMnyWithMzz(BillCardPanel panel,String tabcode) throws BusinessException{
		
		//������ҳǩ
		BillModel zmodel=panel.getBillModel("pk_contract_zzq");
		int zrow=zmodel.getRowCount();
		
		//Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		//Integer ndignum=Integer.valueOf(objtype.toString());
		
		Object objrent=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(objrent.toString());
		boolean ismonth=false;
		if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
			ismonth=true;
		}
		
		//������ҳǩ
		BillModel mmodel=panel.getBillModel("pk_contract_mzq");
		int mrow=mmodel.getRowCount();
		
		//�����ҳǩ
		BillModel model=panel.getBillModel(tabcode);
		int row=model.getRowCount();
		Object objday=panel.getHeadItem("ndaymny").getValueObject();
		Object objmon=panel.getHeadItem("nmonthmny").getValueObject();
		
		for(int i=0;i<row;i++){
			
			boolean ishaszz=false;
			
			UFDouble daymny=getUFdobj(objday);
			UFDouble monthmny=getUFdobj(objmon);//���������
			
			Object obj1=model.getValueAt(i, "dstartdate");
			Object obj2=model.getValueAt(i, "denddate");
			UFDate ud_fist=new UFDate(obj1.toString());
			UFDate ud_last=new UFDate(obj2.toString());
			
			int allday=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
			//��������ȥ��ֵ�ǰ��ʼ���ڣ���������
			List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
			
			for(int k=0;k<zrow;k++){
				
				Object obj3=zmodel.getValueAt(k, "dstartdate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDouble zzrate=getUFdobj(zmodel.getValueAt(k, "nzzrate"));
				UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(k, "ndaymny"));
				UFDouble zzmonmny=getUFdobj(zmodel.getValueAt(k, "nmonthmny"));
				UFDouble mny=getUFdobj(zmodel.getValueAt(k, "nmny"));
				if(ud3.afterDate(ud_last)){//���������ڼ�֮�󣬲�������
					continue ;
				}else if(ud3.beforeDate(ud_fist)){//��������֮ǰ������������
					//daymny=daymny.multiply(zzrate).div(100).add(daymny);
					if(mny.compareTo(new UFDouble(0))>0){
						daymny=zzdaymny;
						monthmny=zzmonmny;
					}else{
						daymny=daymny.multiply(zzrate).div(100).add(daymny);
						monthmny=monthmny.multiply(zzrate).div(100).add(monthmny);
					}
				}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
					//���
					UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
					if(!ud4.beforeDate(ud_fist)){//ǰ�벿�����ϴ����
						Map<String, Object> mapzj=new HashMap<String, Object>();
						mapzj.put("dstartdate", ud_fist);
						mapzj.put("denddate", ud4);
						mapzj.put("ndaymny", daymny);
						int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
						mapzj.put("nday", day);
						listmap.add(mapzj);
						ishaszz=true;
					}
					
					if(mny.compareTo(new UFDouble(0))>0){
						daymny=zzdaymny;
						monthmny=zzmonmny;
					}else{
						daymny=daymny.multiply(zzrate).div(100).add(daymny);
						monthmny=monthmny.multiply(zzrate).div(100).add(monthmny);
					}
					ud_fist=ud3;
				}
				
			}
			//����ʣ�µ���ʼ��������
			Map<String, Object> mapzj=new HashMap<String, Object>();
			mapzj.put("dstartdate", ud_fist);
			mapzj.put("denddate", ud_last);
			mapzj.put("ndaymny", daymny);
			int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
			mapzj.put("nday", day);
			listmap.add(mapzj);
			
			//�����ܽ��
			UFDouble udrow=new UFDouble(0);
			
			if(ismonth&&!ishaszz&&allday==365){//���¼��㲢��û�и�����û������������һ����(��������ʼ����)
				udrow=monthmny.multiply(new UFDouble(12),4);
				//�۳�������
				
			}else{//���ռ����������
				for(Map<String, Object> maps:listmap){
					UFDouble price=getUFdobj(maps.get("ndaymny"));
					Integer days=Integer.valueOf(maps.get("nday").toString());
					udrow=price.multiply(days).add(udrow,4);
				}
			}
			
//====================================================��������=============================================================================
			
			UFDate ud_fist2=new UFDate(obj1.toString());
			UFDate ud_last2=new UFDate(obj2.toString());
			
			UFDouble mzmny=new UFDouble(0);
			//�ж������ڵļ��㷽ʽ
			for(int k=0;k<mrow;k++){
				
				boolean ismonthmz=false;
				
				Object objdef5=mmodel.getValueAt(k, "vdef5");
				if(objdef5!=null){
					ismonthmz=true;
				}
				
				Object obj3=mmodel.getValueAt(k, "dstartdate");
				Object obj4=mmodel.getValueAt(k, "denddate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDate ud4=new UFDate(obj4.toString());
				UFDate start=new UFDate();
				UFDate end=new UFDate();
				
				if(ud3.afterDate(ud_last2)||ud4.beforeDate(ud_fist2)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
					continue ;
				}
								
				if(ud3.beforeDate(ud_fist2)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
					start=ud_fist2;
					ismonthmz=false;
				}else{
					start=ud3;
				}
				
				if(ud4.afterDate(ud_last2)){
					ismonthmz=false;
					end=ud_last2;
				}else{
					end=ud4;
				}
				
				UFDouble daymny2=getUFdobj(objday);
				UFDouble monthmny2=getUFdobj(objmon);
				UFDouble mzmny2=new UFDouble(0);
				List<Map<String, Object>> listmap2=new ArrayList<Map<String,Object>>();
				for(int kk=0;kk<zrow;kk++){
					
					Object obj33=zmodel.getValueAt(kk, "dstartdate");
					UFDate ud33=new UFDate(obj33.toString());
					UFDouble zzrate=getUFdobj(zmodel.getValueAt(kk, "nzzrate"));
					UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(kk, "ndaymny"));
					UFDouble zzmonmny=getUFdobj(zmodel.getValueAt(kk, "nmonthmny"));
					UFDouble mny=getUFdobj(zmodel.getValueAt(kk, "nmny"));
					if(ud33.afterDate(end)){//��������������֮�󣬲�������
						continue ;
					}else if(ud33.beforeDate(start)){//��������֮ǰ������������
						//daymny=daymny.multiply(zzrate).div(100).add(daymny);
						if(mny.compareTo(new UFDouble(0))>0){
							daymny2=zzdaymny;
							monthmny2=zzmonmny;
						}else{
							daymny2=daymny2.multiply(zzrate).div(100).add(daymny2);
							monthmny2=monthmny2.multiply(zzrate).div(100).add(monthmny2);
						}
					}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
						//���
						UFDate ud44=CalendarUtls.getBeforeFirstDay(ud33);
						if(!ud44.beforeDate(start)){//ǰ�벿�����ϴ����
							Map<String, Object> mapzj2=new HashMap<String, Object>();
							mapzj2.put("dstartdate", start);
							mapzj2.put("denddate", ud44);
							mapzj2.put("ndaymny", daymny2);
							int day2=CalendarUtls.getBetweenTwoDays(start, ud44);
							mapzj2.put("nday", day2);
							listmap2.add(mapzj2);
							ismonthmz=false;
						}
						
						if(mny.compareTo(new UFDouble(0))>0){
							daymny2=zzdaymny;
							monthmny2=zzmonmny;
						}else{
							daymny2=daymny2.multiply(zzrate).div(100).add(daymny2);
							monthmny2=monthmny2.multiply(zzrate).div(100).add(monthmny2);
						}
						start=ud33;
					}
					
				}
				
				//����ʣ�µ���ʼ��������
				Map<String, Object> mapzj2=new HashMap<String, Object>();
				mapzj2.put("dstartdate", start);
				mapzj2.put("denddate", end);
				mapzj2.put("ndaymny", daymny2);
				int day2=CalendarUtls.getBetweenTwoDays(start, end);
				mapzj2.put("nday", day2);
				listmap2.add(mapzj2);
				
				//�ж��Ƿ�������
				if(ismonthmz&&ismonth){
					mzmny2=mzmny2.add(monthmny2.multiply(getUFdobj(objdef5)));
				}else{
					for(Map<String, Object> maps:listmap2){
						UFDouble price=getUFdobj(maps.get("ndaymny"));
						Integer days=Integer.valueOf(maps.get("nday").toString());
						mzmny2=price.multiply(days).add(mzmny2);
					}
				}
				
				mzmny=mzmny.add(mzmny2);
				
			}
			
			udrow=udrow.sub(mzmny,4);
			
			/*//�۳�������
			for(Map<String, Object> maps:listmap){
				
				UFDate ud1=new UFDate(maps.get("dstartdate").toString());
				UFDate ud2=new UFDate(maps.get("denddate").toString());
				Integer days=Integer.valueOf(maps.get("nday").toString());
				
				for(int k=0;k<mrow;k++){
					
					Object obj3=mmodel.getValueAt(k, "dstartdate");
					Object obj4=mmodel.getValueAt(k, "denddate");
					UFDate ud3=new UFDate(obj3.toString());
					UFDate ud4=new UFDate(obj4.toString());
					UFDate start=new UFDate();
					UFDate end=new UFDate();
					
					if(ud3.afterDate(ud2)||ud4.beforeDate(ud1)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
						continue ;
					}
									
					if(ud3.beforeDate(ud1)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
						start=ud1;
					}else{
						start=ud3;
					}
					
					if(ud4.afterDate(ud2)){
						end=ud2;
					}else{
						end=ud4;
					}
					
					int mzday=CalendarUtls.getBetweenTwoDays(start, end);
					
					days=days-mzday;
				}
				
				maps.put("nday", days);
			}*/
			
			model.setValueAt(udrow, i, "nhtmny");
			model.setValueAt(udrow, i, "nht2mny");
			model.setValueAt(new UFDouble(0), i, "nht1mny");
		}
	}
	
	/**
	 * ��������ʱ����ڵĺ�ͬ���(������������)�����£�
	 * @param panel
	 * @throws BusinessException
	 */
	private static UFDouble calculateMnyBetweenDate(BillCardPanel panel,UFDate ud_fist,UFDate ud_last) throws BusinessException{
		
		//������ҳǩ
		BillModel zmodel=panel.getBillModel("pk_contract_zzq");
		int zrow=zmodel.getRowCount();
		
		UFDate ud_fist1=ud_fist;
		UFDate ud_last1=ud_last;
		
		//������ҳǩ
		BillModel mmodel=panel.getBillModel("pk_contract_mzq");
		int mrow=mmodel.getRowCount();
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		Object objrent=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(objrent.toString());
		boolean ismonth=false;
		if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
			ismonth=true;
		}
		
		Object objday=panel.getHeadItem("ndaymny").getValueObject();
		Object objmon=panel.getHeadItem("nmonthmny").getValueObject();
		
		List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
		UFDouble daymny=getUFdobj(objday);
		for(int k=0;k<zrow;k++){
				
			Object obj3=zmodel.getValueAt(k, "dstartdate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDouble zzrate=getUFdobj(zmodel.getValueAt(k, "nzzrate"));
			UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(k, "ndaymny"));
			UFDouble mny=getUFdobj(zmodel.getValueAt(k, "nmny"));
			
			if(ud3.afterDate(ud_last1)){//���������ڼ�֮�󣬲�������
				break ;
			}else if(ud3.beforeDate(ud_fist1)){//��������֮ǰ������������
				//daymny=daymny.multiply(zzrate).div(100).add(daymny);
				if(mny.compareTo(new UFDouble(0))>0){
					daymny=zzdaymny;
				}else{
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
				}
			}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
				//���
				UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
				if(!ud4.beforeDate(ud_fist1)){//ǰ�벿�����ϴ����
					Map<String, Object> mapzj=new HashMap<String, Object>();
					mapzj.put("dstartdate", ud_fist1);
					mapzj.put("denddate", ud4);
					mapzj.put("ndaymny", daymny);
					int day=CalendarUtls.getBetweenTwoDays(ud_fist1, ud4);
					mapzj.put("nday", day);
					listmap.add(mapzj);
				}
					
				if(mny.compareTo(new UFDouble(0))>0){
					daymny=zzdaymny;
				}else{
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
				}
				ud_fist1=ud3;
			}
		}
		//����ʣ�µ���ʼ��������
		Map<String, Object> mapzj=new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist1);
		mapzj.put("denddate", ud_last1);
		mapzj.put("ndaymny", daymny);
		int day=CalendarUtls.getBetweenTwoDays(ud_fist1, ud_last1);
		mapzj.put("nday", day);
		listmap.add(mapzj);
		
		UFDouble udrow=new UFDouble(0);
		//���ռ����ܽ��
		for(Map<String, Object> maps:listmap){
			UFDouble price=getUFdobj(maps.get("ndaymny"));
			Integer days=Integer.valueOf(maps.get("nday").toString());
			udrow=price.multiply(days).add(udrow);
		}
		
		//====================================================����������=============================================================================
		
		UFDate ud_fist2=ud_fist;
		UFDate ud_last2=ud_last;
		
		UFDouble mzmny=new UFDouble(0);
		//�ж������ڵļ��㷽ʽ
		for(int k=0;k<mrow;k++){
			
			boolean ismonthmz=false;
			
			Object objdef5=mmodel.getValueAt(k, "vdef5");
			if(objdef5!=null){
				ismonthmz=true;
			}
			
			Object obj3=mmodel.getValueAt(k, "dstartdate");
			Object obj4=mmodel.getValueAt(k, "denddate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDate ud4=new UFDate(obj4.toString());
			UFDate start=new UFDate();
			UFDate end=new UFDate();
			
			if(ud3.afterDate(ud_last2)||ud4.beforeDate(ud_fist2)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
				continue ;
			}
							
			if(ud3.beforeDate(ud_fist2)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
				start=ud_fist2;
				ismonthmz=false;
			}else{
				start=ud3;
			}
			
			if(ud4.afterDate(ud_last2)){
				ismonthmz=false;
				end=ud_last2;
			}else{
				end=ud4;
			}
			
			UFDouble daymny2=getUFdobj(objday);
			UFDouble monthmny2=getUFdobj(objmon);
			UFDouble mzmny2=new UFDouble(0);
			List<Map<String, Object>> listmap2=new ArrayList<Map<String,Object>>();
			for(int kk=0;kk<zrow;kk++){
				
				Object obj33=zmodel.getValueAt(kk, "dstartdate");
				UFDate ud33=new UFDate(obj33.toString());
				UFDouble zzrate=getUFdobj(zmodel.getValueAt(kk, "nzzrate"));
				UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(kk, "ndaymny"));
				UFDouble zzmonmny=getUFdobj(zmodel.getValueAt(kk, "nmonthmny"));
				UFDouble mny=getUFdobj(zmodel.getValueAt(kk, "nmny"));
				if(ud33.afterDate(end)){//��������������֮�󣬲�������
					continue ;
				}else if(ud33.beforeDate(start)){//��������֮ǰ������������
					//daymny=daymny.multiply(zzrate).div(100).add(daymny);
					if(mny.compareTo(new UFDouble(0))>0){
						daymny2=zzdaymny;
						monthmny2=zzmonmny;
					}else{
						daymny2=daymny2.multiply(zzrate).div(100).add(daymny2);
						monthmny2=monthmny2.multiply(zzrate).div(100).add(monthmny2);
					}
				}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
					//���
					UFDate ud44=CalendarUtls.getBeforeFirstDay(ud33);
					if(!ud44.beforeDate(start)){//ǰ�벿�����ϴ����
						Map<String, Object> mapzj2=new HashMap<String, Object>();
						mapzj2.put("dstartdate", start);
						mapzj2.put("denddate", ud44);
						mapzj2.put("ndaymny", daymny2);
						int day2=CalendarUtls.getBetweenTwoDays(start, ud44);
						mapzj2.put("nday", day2);
						listmap2.add(mapzj2);
						ismonthmz=false;
					}
					
					if(mny.compareTo(new UFDouble(0))>0){
						daymny2=zzdaymny;
						monthmny2=zzmonmny;
					}else{
						daymny2=daymny2.multiply(zzrate).div(100).add(daymny2);
						monthmny2=monthmny2.multiply(zzrate).div(100).add(monthmny2);
					}
					start=ud33;
				}
				
			}
			
			//����ʣ�µ���ʼ��������
			Map<String, Object> mapzj2=new HashMap<String, Object>();
			mapzj2.put("dstartdate", start);
			mapzj2.put("denddate", end);
			mapzj2.put("ndaymny", daymny2);
			int day2=CalendarUtls.getBetweenTwoDays(start, end);
			mapzj2.put("nday", day2);
			listmap2.add(mapzj2);
			
			//�ж��Ƿ�������
			if(ismonthmz&&ismonth){
				mzmny2=mzmny2.add(monthmny2.multiply(getUFdobj(objdef5)));
			}else{
				for(Map<String, Object> maps:listmap2){
					UFDouble price=getUFdobj(maps.get("ndaymny"));
					Integer days=Integer.valueOf(maps.get("nday").toString());
					mzmny2=price.multiply(days).add(mzmny2);
				}
			}
			
			mzmny=mzmny.add(mzmny2);
			
		}
		
		udrow=udrow.sub(mzmny);
		if(udrow.compareTo(new UFDouble(0))<0){
			udrow=new UFDouble(0);
		}
		if(ndignum==AbsEnumType.FeeScale2_JW){
			udrow=new UFDouble(Math.ceil(udrow.doubleValue()));
		}else{
			udrow=udrow.add(new UFDouble(0), ndignum);
		}
		
		return udrow;
			
		//��������ȥ��ֵ�ǰ��ʼ���ڣ���������
		/*	
		//�۳�������
		for(Map<String, Object> maps:listmap){
				
			UFDate ud1=new UFDate(maps.get("dstartdate").toString());
			UFDate ud2=new UFDate(maps.get("denddate").toString());
			Integer days=Integer.valueOf(maps.get("nday").toString());
			
			for(int k=0;k<mrow;k++){
					
				Object obj3=mmodel.getValueAt(k, "dstartdate");
				Object obj4=mmodel.getValueAt(k, "denddate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDate ud4=new UFDate(obj4.toString());
				UFDate start=new UFDate();
				UFDate end=new UFDate();
					
				if(ud3.afterDate(ud2)||ud4.beforeDate(ud1)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
					continue ;
				}
									
				if(ud3.beforeDate(ud1)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
					start=ud1;
				}else{
					start=ud3;
				}
					
				if(ud4.afterDate(ud2)){
					end=ud2;
				}else{
					end=ud4;
				}
					
				int mzday=CalendarUtls.getBetweenTwoDays(start, end);
				
				days=days-mzday;
			}
				
			maps.put("nday", days);
		}*/
			
			
		//�����ܽ��
		/*UFDouble udrow=new UFDouble(0);
		for(Map<String, Object> maps:listmap){
				
			UFDouble price=getUFdobj(maps.get("ndaymny"));
			Integer days=Integer.valueOf(maps.get("nday").toString());
			udrow=price.multiply(days).add(udrow);
		}
		if(ndignum==AbsEnumType.FeeScale2_JW){
			udrow=new UFDouble(Math.ceil(udrow.doubleValue()));
		}else{
			udrow=udrow.add(new UFDouble(0), ndignum);
		}
			
		return udrow;*/
	}
	
	/**
	 * ��������ʱ����ڵ�ĳ�������Ľ��
	 * @param panel
	 * @throws BusinessException
	 */
	private static UFDouble calculateHouseMnyBetweenDate(BillCardPanel panel,UFDate ud_fist,UFDate ud_last,Object pk_house) throws BusinessException{
		
		//������ҳǩ
		BillModel zmodel=panel.getBillModel("pk_contract_zzq");
		int zrow=zmodel.getRowCount();
		
		//������ҳǩ
		BillModel mmodel=panel.getBillModel("pk_contract_mzq");
		int mrow=mmodel.getRowCount();
		
		//��ȡ���������
		BillModel hmodel=panel.getBillModel("pk_contract_house");
		int hrow=hmodel.getRowCount();
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		Object area=panel.getHeadItem("narea").getValueObject();
		UFDouble narea=getUFdobj(area);
		
		Object objday=null;
		UFDouble narea2=new UFDouble(0);
		
		for(int i=0;i<hrow;i++){
			if(getColvalue(hmodel.getValueObjectAt(i, "pk_house")).equals(pk_house)){
				objday=hmodel.getValueAt(i, "ndaymny");
				narea2=getUFdobj(hmodel.getValueAt(i, "narea"));
				break;
			}
		}
		
		UFDouble daymny=getUFdobj(objday);
		
		Object rentstyle=panel.getHeadItem("rentstyle").getValueObject();
		Integer rent=Integer.valueOf(rentstyle.toString());
		boolean flag=true;
		if(rent==AbsEnumType.ZLstyle_1||rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_5){//ƽ����
			flag=false;
		}else if(rent==AbsEnumType.ZLstyle_2||rent==AbsEnumType.ZLstyle_4||rent==AbsEnumType.ZLstyle_6){//��
			flag=true;
		}
		
		//��������ȥ��ֵ�ǰ��ʼ���ڣ���������
		List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
			
		for(int k=0;k<zrow;k++){
				
			Object obj3=zmodel.getValueAt(k, "dstartdate");
			UFDate ud3=new UFDate(obj3.toString());
			UFDouble zzrate=getUFdobj(zmodel.getValueAt(k, "nzzrate"));
			UFDouble zzdaymny=getUFdobj(zmodel.getValueAt(k, "ndaymny"));
			UFDouble mny=getUFdobj(zmodel.getValueAt(k, "nmny"));
			if(ud3.afterDate(ud_last)){//���������ڼ�֮�󣬲�������
				break ;
			}else if(ud3.beforeDate(ud_fist)){//��������֮ǰ������������
				if(mny.compareTo(new UFDouble(0))>0){
					if(flag){//���׼���
						mny=zzdaymny.div(new UFDouble(hrow));
					}else{
						mny=zzdaymny.multiply(narea2).div(narea);
					}
					daymny=mny;
				}else{
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
				}
			}else{//���������м�(�ɵ�����ʼ��ֹ����)�����
				//���
				UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
				if(!ud4.beforeDate(ud_fist)){//ǰ�벿�����ϴ����
					Map<String, Object> mapzj=new HashMap<String, Object>();
					mapzj.put("dstartdate", ud_fist);
					mapzj.put("denddate", ud4);
					mapzj.put("ndaymny", daymny);
					int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud4);
					mapzj.put("nday", day);
					listmap.add(mapzj);
				}
				
				if(mny.compareTo(new UFDouble(0))>0){
					if(flag){//���׼���
						mny=zzdaymny.div(new UFDouble(hrow));
					}else{
						mny=zzdaymny.multiply(narea2).div(narea);
					}
					daymny=mny;
				}else{
					daymny=daymny.multiply(zzrate).div(100).add(daymny);
				}
				
				ud_fist=ud3;
			}
		}
		//����ʣ�µ���ʼ��������
		Map<String, Object> mapzj=new HashMap<String, Object>();
		mapzj.put("dstartdate", ud_fist);
		mapzj.put("denddate", ud_last);
		mapzj.put("ndaymny", daymny);
		int day=CalendarUtls.getBetweenTwoDays(ud_fist, ud_last);
		mapzj.put("nday", day);
		listmap.add(mapzj);
			
			
		//�۳�������
		for(Map<String, Object> maps:listmap){
				
			UFDate ud1=new UFDate(maps.get("dstartdate").toString());
			UFDate ud2=new UFDate(maps.get("denddate").toString());
			Integer days=Integer.valueOf(maps.get("nday").toString());
			
			for(int k=0;k<mrow;k++){
					
				Object obj3=mmodel.getValueAt(k, "dstartdate");
				Object obj4=mmodel.getValueAt(k, "denddate");
				UFDate ud3=new UFDate(obj3.toString());
				UFDate ud4=new UFDate(obj4.toString());
				UFDate start=new UFDate();
				UFDate end=new UFDate();
					
				if(ud3.afterDate(ud2)||ud4.beforeDate(ud1)){//����������ڿ�ʼ���������⿪ʼ���ڽ�����������
					continue ;
				}
									
				if(ud3.beforeDate(ud1)){//�������ڿ�ʼ����ȡ��ʼ����,����ȡ���⿪ʼ����
					start=ud1;
				}else{
					start=ud3;
				}
					
				if(ud4.afterDate(ud2)){
					end=ud2;
				}else{
					end=ud4;
				}
					
				int mzday=CalendarUtls.getBetweenTwoDays(start, end);
				
				days=days-mzday;
			}
				
			maps.put("nday", days);
		}
			
			
		//�����ܽ��
		UFDouble udrow=new UFDouble(0);
		for(Map<String, Object> maps:listmap){
				
			UFDouble price=getUFdobj(maps.get("ndaymny"));
			Integer days=Integer.valueOf(maps.get("nday").toString());
			udrow=price.multiply(days).add(udrow);
		}
		
		if(ndignum==AbsEnumType.FeeScale2_JW){
			udrow=new UFDouble(Math.ceil(udrow.doubleValue()));
		}else{
			udrow=udrow.add(new UFDouble(0), ndignum);
		}
			
		return udrow;
	}
	
	/**
	 * ���������
	 */
	public static void recalSplitCwcfData(BillCardPanel panel) throws BusinessException{
		
		BillModel model=panel.getBillModel("pk_contract_cwcf");
		//��ȡҵ����ҳǩ
		BillModel model1=panel.getBillModel("pk_contract_ywcf");
		int row=model1.getRowCount();
		if(row<=0){
			return ;
		}
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		//�����߼�
		for(int i=0;i<row;i++){
			
			Object obj1=model1.getValueAt(i, "dstartdate");
			Object obj2=model1.getValueAt(i, "denddate");
			UFDate ud_fist=new UFDate(obj1.toString());
			UFDate ud_last=new UFDate(obj2.toString());
			
			Object pk_house=getColvalue(model1.getValueObjectAt(i, "pk_house"));
			Object pk_cust=getColvalue(model1.getValueObjectAt(i, "pk_customer"));
			Object pk_sfxm=getColvalue(model1.getValueObjectAt(i, "pk_costproject"));
			Object is_qc=model1.getValueAt(i, "is_qc");//�Ƿ��ڳ�
			
			UFDouble ysmny=new UFDouble(0);
			UFDouble nysmny=getUFdobj(model1.getValueAt(i, "nysmny"));
			
			UFDate start=ud_fist;
			while(!start.afterDate(ud_last)){
				UFDate end=CalendarUtls.getMaxMonthDay(start);
				
				model.addLine();
				UFDouble mny=calculateHouseMnyBetweenDate(panel, start, end,pk_house);
				
				if(!end.beforeDate(ud_last)){//��������֮��(��ֵ����һ��)
					end=ud_last;
					model.setValueAt(nysmny.sub(ysmny), model.getRowCount()-1, "nysmny");//��ȡ�Żݽ��
				}else{
					model.setValueAt(mny, model.getRowCount()-1, "nysmny");
				}
				ysmny=ysmny.add(mny);
				model.setValueAt(model.getRowCount()*10, model.getRowCount()-1, "rowno");
				model.setValueAt(pk_cust, model.getRowCount()-1, "pk_customer");
				model.setValueAt(pk_house, model.getRowCount()-1, "pk_house");
				model.setValueAt(pk_sfxm, model.getRowCount()-1, "pk_costproject");
				model.setValueAt(start, model.getRowCount()-1, "drecdate");
				model.setValueAt(start, model.getRowCount()-1, "dstartdate");
				model.setValueAt(end, model.getRowCount()-1, "denddate");
				
				model.setValueAt(is_qc, model.getRowCount()-1, "is_qc");
				UFDate nextfistday=CalendarUtls.getNextMonthFirstDay(start);
				start=nextfistday;//������ʼ����
			}
			
		}
		
		//�������·�
		String tab="pk_contract_cwcf";
		calculatePeriod(panel,tab);
		//���������������Ľ��
		//calculateMnyWithMzqAndZzq(panel,tab);
		
		//����˰��
		UFDouble taxrate=getUFdobj(panel.getHeadItem("taxstyle").getValueObject());
		for(int i=0;i<model.getRowCount();i++){
			UFDouble udys=getUFdobj(model.getValueAt(i, "nysmny"));
			
			UFDouble ntaxmny=udys.div(taxrate.div(100).add(1));
			if(ndignum==AbsEnumType.FeeScale2_JW){
				ntaxmny=new UFDouble(Math.ceil(ntaxmny.doubleValue()));
			}else{
				ntaxmny=ntaxmny.add(new UFDouble(0), 2);
			}
			//UFDouble taxmny=udys.multiply(taxrate).div(100);
			model.setValueAt(udys.sub(ntaxmny,2), i, "ntaxmny");
			model.setValueAt(ntaxmny, i, "nnotaxmny");
			model.setValueAt(new UFDouble(0), i, "nskmny");
		}
		
		model.loadLoadRelationItemValue();
		
		//����
		int col1=model.getBodyColByKey("pk_house");
		int col2=model.getBodyColByKey("dstartdate");
		List<SortItem> list=new ArrayList<SortItem>();
		list.add(new SortItem(col2,true));
		list.add(new SortItem(col1,true));
		
		model.sortByColumns(list);
		
		for(int i=0;i<model.getRowCount();i++){
			model.setValueAt((i+1)*10, i, "rowno");
		}
		
	}
	
	/**
	 * �������ڷ��ò��
	 */
	public static void recalSplitZqfyData(BillCardPanel panel) throws BusinessException{
		
		BillModel model=panel.getBillModel("pk_contract_zqfycf");
		
		BillModel modelmz=panel.getBillModel("pk_contract_mzq");
		//��ȡ���ڷ���ҳǩ����
		BillModel model1=panel.getBillModel("pk_contract_zqfy");
		int row=model1.getRowCount();
		if(row<=0){
			return ;
		}
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		Object objfirst=panel.getHeadItem("dstartdate").getValueObject();
		UFDate ud_start=new UFDate(objfirst.toString());
		
		Object objend=panel.getHeadItem("denddate").getValueObject();
		UFDate ud_last=new UFDate(objend.toString());
		
		Object objmz=panel.getHeadItem("is_mz").getValueObject();
		UFBoolean is_mz=new UFBoolean(objmz.toString());
		//�����߼�
		for(int i=0;i<row;i++){
			
			boolean flag=false;
			
			Object pk_house=getColvalue(model1.getValueObjectAt(i, "pk_house"));
			Object pk_cust=getColvalue(model1.getValueObjectAt(i, "pk_customer"));
			Object pk_sfxm=getColvalue(model1.getValueObjectAt(i, "pk_costproject"));
			Object nfymny=model1.getValueAt(i, "nsfmny");//�շѽ��
			Object taxrate=model1.getValueAt(i, "ntaxrate");//˰��
			
			
			Object obj=model1.getValueAt(i, "dstartdate");
			UFDate ud_fist=new UFDate(obj.toString());
			
			if(is_mz.booleanValue()&&ud_fist.isSameDate(ud_start)){
				flag=true;
				Object mzdate=modelmz.getValueAt(0, "denddate");
				UFDate ud=new UFDate(mzdate.toString());
				ud_fist=CalendarUtls.getAfterFirstDay(ud);
			}
			
			Object obj2=model1.getValueAt(i, "dsfzq");
			Integer it=Integer.valueOf(obj2.toString());
			
			//���
			UFDate start=ud_fist;
			while(!start.afterDate(ud_last)){
				UFDouble fymny=getUFdobj(nfymny).multiply(it);
				UFDate end=CalendarUtls.getNextMonthDay(start, it);
				UFDate end2=CalendarUtls.getBeforeFirstDay(end);
				model.addLine();
				model.setValueAt(model.getRowCount()*10, model.getRowCount()-1, "rowno");
				model.setValueAt(pk_cust, model.getRowCount()-1, "pk_customer");
				model.setValueAt(pk_house, model.getRowCount()-1, "pk_house");
				model.setValueAt(pk_sfxm, model.getRowCount()-1, "pk_costproject");
				model.setValueAt(start, model.getRowCount()-1, "drecdate");
				model.setValueAt(start, model.getRowCount()-1, "dstartdate");
				
				if(!end2.beforeDate(ud_last)){//��������֮��
					int day1=CalendarUtls.getBetweenTwoDays(start, end2);
					int day2=CalendarUtls.getBetweenTwoDays(start, ud_last);
					fymny=fymny.multiply(day2).div(new UFDouble(day1));
					
					if(ndignum==AbsEnumType.FeeScale2_JW){
						fymny=new UFDouble(Math.ceil(fymny.doubleValue()));
					}else{
						fymny=fymny.add(new UFDouble(0), ndignum);
					}
					
					end2=ud_last;
					
				}
				model.setValueAt(end2, model.getRowCount()-1, "denddate");
				model.setValueAt(fymny, model.getRowCount()-1, "nfymny");
				model.setValueAt(taxrate, model.getRowCount()-1, "vdef1");
				
				if(flag&&start.isSameDate(ud_fist)){//���迪ʼ����
					model.setValueAt(ud_start, model.getRowCount()-1, "drecdate");
					model.setValueAt(ud_start, model.getRowCount()-1, "dstartdate");
				}
				start=end;//������ʼ����
			}
			
		}
		
		//�������·�
		String tab="pk_contract_zqfycf";
		calculatePeriod(panel,tab);
		
		//����˰��
		for(int i=0;i<model.getRowCount();i++){
			UFDouble taxrate=getUFdobj(model.getValueAt(i, "vdef1"));
			UFDouble udys=getUFdobj(model.getValueAt(i, "nfymny"));
			model.setValueAt(udys, i, "nysmny");
			UFDouble ntaxmny=udys.div(taxrate.div(100).add(1));
			if(ndignum==AbsEnumType.FeeScale2_JW){
				ntaxmny=new UFDouble(Math.ceil(ntaxmny.doubleValue()));
			}else{
				ntaxmny=ntaxmny.add(new UFDouble(0), 2);
			}
			model.setValueAt(udys.sub(ntaxmny,2), i, "ntaxmny");
			model.setValueAt(ntaxmny, i, "nnotaxmny");
			model.setValueAt(new UFDouble(0), i, "nskmny");
		}
		
		model.loadLoadRelationItemValue();
	}
	
	public static void recalSplitZqfyDataNew(BillCardPanel panel) throws BusinessException{
		
		BillModel model=panel.getBillModel("pk_contract_zqfycf");
		
		BillModel modelmz=panel.getBillModel("pk_contract_mzq");
		//��ȡ���ڷ���ҳǩ����
		BillModel model1=panel.getBillModel("pk_contract_zqfy");
		int row=model1.getRowCount();
		if(row<=0){
			return ;
		}
		
		Object objtype=panel.getHeadItem("ndegree").getValueObject();//����
		Integer ndignum=Integer.valueOf(objtype.toString());
		
		//���ڸ��ʽ���
		List<Map<String, Object>> paymap = recalPaystyleByzq(panel);
		
		Object objmz=panel.getHeadItem("is_mz").getValueObject();
		UFBoolean is_mz=new UFBoolean(objmz.toString());
		
		Object objfirst=panel.getHeadItem("dstartdate").getValueObject();
		UFDate ud_start=new UFDate(objfirst.toString());
		
		//�����߼�
		for(int i=0;i<paymap.size();i++){
			
			Map<String, Object> map=paymap.get(i);
			
			boolean flag=false;
			
			Object pk_house=map.get("pk_house");
			Object pk_cust=map.get("pk_cust");
			Object pk_sfxm=map.get("pk_sfxm");
			Object nfymny=map.get("nfymny");
			Object taxrate=map.get("taxrate");
			
			Object objend=map.get("denddate");
			UFDate ud_last=new UFDate(objend.toString());
			
			Object obj=map.get("dstartdate");
			UFDate ud_fist=new UFDate(obj.toString());
			
			if(is_mz.booleanValue()&&ud_fist.isSameDate(ud_start)){
				flag=true;
				Object mzdate=modelmz.getValueAt(0, "denddate");
				UFDate ud=new UFDate(mzdate.toString());
				ud_fist=CalendarUtls.getAfterFirstDay(ud);
			}
			
			Object obj2=map.get("paystyle");
			Integer it=Integer.valueOf(obj2.toString());
			
			//���
			UFDate start=ud_fist;
			while(!start.afterDate(ud_last)){
				UFDouble fymny=getUFdobj(nfymny).multiply(it);
				UFDate end=CalendarUtls.getNextMonthDay(start, it);
				UFDate end2=CalendarUtls.getBeforeFirstDay(end);
				model.addLine();
				model.setValueAt(model.getRowCount()*10, model.getRowCount()-1, "rowno");
				model.setValueAt(pk_cust, model.getRowCount()-1, "pk_customer");
				model.setValueAt(pk_house, model.getRowCount()-1, "pk_house");
				model.setValueAt(pk_sfxm, model.getRowCount()-1, "pk_costproject");
				model.setValueAt(start, model.getRowCount()-1, "drecdate");
				model.setValueAt(start, model.getRowCount()-1, "dstartdate");
				
				if(!end2.beforeDate(ud_last)){//��������֮��
					int day1=CalendarUtls.getBetweenTwoDays(start, end2);
					int day2=CalendarUtls.getBetweenTwoDays(start, ud_last);
					fymny=fymny.multiply(day2).div(new UFDouble(day1));
					
					if(ndignum==AbsEnumType.FeeScale2_JW){
						fymny=new UFDouble(Math.ceil(fymny.doubleValue()));
					}else{
						fymny=fymny.add(new UFDouble(0), ndignum);
					}
					end2=ud_last;
					
				}
				model.setValueAt(end2, model.getRowCount()-1, "denddate");
				model.setValueAt(fymny, model.getRowCount()-1, "nfymny");
				model.setValueAt(taxrate, model.getRowCount()-1, "vdef1");
				
				if(flag&&start.isSameDate(ud_fist)){//���迪ʼ����
					model.setValueAt(ud_start, model.getRowCount()-1, "drecdate");
					model.setValueAt(ud_start, model.getRowCount()-1, "dstartdate");
				}
				start=end;//������ʼ����
			}
			
		}
		
		//�������·�
		String tab="pk_contract_zqfycf";
		calculatePeriod(panel,tab);
		
		//����˰��
		for(int i=0;i<model.getRowCount();i++){
			UFDouble taxrate=getUFdobj(model.getValueAt(i, "vdef1"));
			UFDouble udys=getUFdobj(model.getValueAt(i, "nfymny"));
			model.setValueAt(udys, i, "nysmny");
			
			UFDouble ntaxmny=udys.div(taxrate.div(100).add(1));
			if(ndignum==AbsEnumType.FeeScale2_JW){
				ntaxmny=new UFDouble(Math.ceil(ntaxmny.doubleValue()));
			}else{
				ntaxmny=ntaxmny.add(new UFDouble(0), 2);
			}
			model.setValueAt(udys.sub(ntaxmny,2), i, "ntaxmny");
			model.setValueAt(ntaxmny, i, "nnotaxmny");
			model.setValueAt(new UFDouble(0), i, "nskmny");
		}
		
		model.loadLoadRelationItemValue();
		
		int col1=model.getBodyColByKey("pk_house");
		int col2=model.getBodyColByKey("dstartdate");
		List<SortItem> list=new ArrayList<SortItem>();
		list.add(new SortItem(col2,true));
		list.add(new SortItem(col1,true));
		
		model.sortByColumns(list);
		
		for(int i=0;i<model.getRowCount();i++){
			model.setValueAt((i+1)*10, i, "rowno");
		}
	}

	 /**
	 * �����ڷ�ʽ�������
	 * @param panel
	 * @return
	 * @throws BusinessException
	 */
	private static List<Map<String, Object>> recalPaystyleByzq(BillCardPanel panel) throws BusinessException{
		
		//�����ʽ���
		BillModel modelf=panel.getBillModel("pk_contract_zqmx");
		BillModel model1=panel.getBillModel("pk_contract_zqfy");
		int row=model1.getRowCount();
		int row2=modelf.getRowCount();
		
		Object obj2=panel.getHeadItem("denddate").getValueObject();
		
		List<Map<String, Object>> listmap=new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<row;i++){
			
			Object obj1=model1.getValueAt(i, "dstartdate");
			UFDate pay_fist=new UFDate(obj1.toString());
			UFDate ud_last=new UFDate(obj2.toString());
			Object obj3=model1.getValueAt(i,"dsfzq");
			
			Object pk_house=getColvalue(model1.getValueObjectAt(i, "pk_house"));
			Object pk_cust=getColvalue(model1.getValueObjectAt(i, "pk_customer"));
			Object pk_sfxm=getColvalue(model1.getValueObjectAt(i, "pk_costproject"));
			Object nfymny=model1.getValueAt(i, "nsfmny");//�շѽ��
			Object taxrate=model1.getValueAt(i, "ntaxrate");//˰��
			
			for(int k=0;k<row2;k++){
				
				Object obj=modelf.getValueAt(k, "dstartdate");
				Object objpay=getColvalue(modelf.getValueObjectAt(k, "paystyle"));
				UFDate ud3=new UFDate(obj.toString());
				UFDate ud4=CalendarUtls.getBeforeFirstDay(ud3);
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("dstartdate", pay_fist);
				map.put("denddate", ud4);
				map.put("paystyle", obj3);
				map.put("pk_house", pk_house);
				map.put("pk_cust", pk_cust);
				map.put("pk_sfxm", pk_sfxm);
				map.put("nfymny", nfymny);
				map.put("taxrate", taxrate);
				listmap.add(map);
			
				pay_fist=ud3;//��������
				obj3=objpay;//���渶�ʽ
			}
			
			//�������һ��
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("dstartdate", pay_fist);
			map.put("denddate", ud_last);
			map.put("paystyle", obj3);
			map.put("pk_house", pk_house);
			map.put("pk_cust", pk_cust);
			map.put("pk_sfxm", pk_sfxm);
			map.put("nfymny", nfymny);
			map.put("taxrate", taxrate);
			listmap.add(map);
			
		}
		
		return listmap;
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
