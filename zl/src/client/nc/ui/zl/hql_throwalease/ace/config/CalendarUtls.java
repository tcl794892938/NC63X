package nc.ui.zl.hql_throwalease.ace.config;

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
	 * ��ȡ��ǰ�������������������������ʼ��
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
		
		return UFDate.getDaysBetween(date, date2)-k;
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
        
        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
    	
    	return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
    	
    }
}
