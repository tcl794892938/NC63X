package nc.ui.zl.tcl_contract.ace.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nc.vo.pub.lang.UFDate;

public class CalendarUtls {

	/**
	 * ��ȡ��ǰ���ڵ��¸��µ���
	 */
	public static UFDate getNextMonthDay(UFDate date) {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		cldr.add(Calendar.MONTH, 1);

		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	/**
	 * ��ȡ��ǰ���ڵ�n���º��Ӧ����
	 */
	public static UFDate getNextMonthDay(UFDate date,int n) {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		cldr.add(Calendar.MONTH, n);

		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	/**
	 * ��ȡ��ǰ���ڵ���ĩ����
	 */
	public static UFDate getMaxMonthDay(UFDate date) {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		cldr.set(Calendar.DAY_OF_MONTH, cldr.getActualMaximum(Calendar.DAY_OF_MONTH));

		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	/**
	 * ��ȡ��ǰ���ڵ��¸���1��
	 */
	public static UFDate getNextMonthFirstDay(UFDate date) {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		cldr.add(Calendar.MONTH, 1);
		cldr.set(Calendar.DAY_OF_MONTH, cldr.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	/**
	 * ��ȡ��ǰ���ڵ�ǰһ��
	 */
	public static UFDate getBeforeFirstDay(UFDate date) {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		int day=cldr.get(Calendar.DATE);
		cldr.set(Calendar.DATE, day-1);
		
		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	/**
	 * ��ȡ��ǰ���ڵĺ�һ��
	 */
	public static UFDate getAfterFirstDay(UFDate date) {

		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cldr = Calendar.getInstance();
		cldr.setTime(date.toDate());
		int day=cldr.get(Calendar.DATE);
		cldr.set(Calendar.DATE, day+1);
		
		UFDate date2 = new UFDate(dft.format(cldr.getTime()));
		return date2;
	}
	
	
	/**
	 * ��ȡ��ǰ�����������������������ʼ��(ȥ�������29��)
	 */
	public static int getBetweenTwoDays(UFDate date,UFDate date2) {

		int k=0;
		int year=date.getYear();
		int year2=date2.getYear();
		
		for(int i=year;i<=year2;i++){
			
			if(UFDate.isLeapYear(i)){//����
				UFDate ud=new UFDate(i+"-02-29");
				if(!ud.beforeDate(date)&&!ud.afterDate(date2)){//������ʼ��ֹ������
					k++;
				}
			}
		}
		
		return UFDate.getDaysBetween(date, date2)+1-k;
	}

	/**
	 * �жϵ�ǰ�Ƿ��Ǹ��µĵ�һ��
	 */
	public static boolean isFirstDayOfMonth(UFDate date) {
		boolean flag = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date.toDate());
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		if (1 == today) {
			flag = true;
		}
		return flag;
	}
	/**
	 * ��ȡ��������֮�������ٸ���
	 */
    public static int getBetweenMonths(UFDate date,UFDate date2){
    	
    	Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        
        c1.setTime(date.toDate());
        c2.setTime(date2.toDate());
        int day1 = c1.get(Calendar.DAY_OF_MONTH); 
        int day2 = c2.get(Calendar.DAY_OF_MONTH); 
        int day3 = day1 - 1; 
        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        int months = year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
        if(day3==day2){
        	return months;//������
        }else if(day2<day3){
        	return months-1;//������Ȼ����
        }
        
    	return months;
    }
    //��ȡ��������ʱ���������
    public static int getLeftDays(UFDate date,UFDate date2){
    	
    	Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        
        c1.setTime(date.toDate());
        c2.setTime(date2.toDate());
        int day1 = c1.get(Calendar.DAY_OF_MONTH); 
        int day2 = c2.get(Calendar.DAY_OF_MONTH); 
        int day3 = day1 - 1; 
        
        if(day3==day2){
        	return 0;//������
        }else if(day2<day3){
        	return day3;//������Ȼ����
        }
        
    	return day2-day3;
    }
   
}
