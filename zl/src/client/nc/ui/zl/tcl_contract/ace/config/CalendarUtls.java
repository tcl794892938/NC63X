package nc.ui.zl.tcl_contract.ace.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import nc.vo.pub.lang.UFDate;

public class CalendarUtls {

	/**
	 * 获取当前日期的下个月当天
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
	 * 获取当前日期的n个月后对应当天
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
	 * 获取当前日期的月末日期
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
	 * 获取当前日期的下个月1号
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
	 * 获取当前日期的前一天
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
	 * 获取当前日期的后一天
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
	 * 获取当前日期区间的总天数（包含起始）(去除闰年的29号)
	 */
	public static int getBetweenTwoDays(UFDate date,UFDate date2) {

		int k=0;
		int year=date.getYear();
		int year2=date2.getYear();
		
		for(int i=year;i<=year2;i++){
			
			if(UFDate.isLeapYear(i)){//闰年
				UFDate ud=new UFDate(i+"-02-29");
				if(!ud.beforeDate(date)&&!ud.afterDate(date2)){//介于起始终止，包括
					k++;
				}
			}
		}
		
		return UFDate.getDaysBetween(date, date2)+1-k;
	}

	/**
	 * 判断当前是否是该月的第一天
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
	 * 获取两个日期之间相距多少个月
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
        	return months;//满整月
        }else if(day2<day3){
        	return months-1;//不满自然整月
        }
        
    	return months;
    }
    //获取不满整月时多余的天数
    public static int getLeftDays(UFDate date,UFDate date2){
    	
    	Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        
        c1.setTime(date.toDate());
        c2.setTime(date2.toDate());
        int day1 = c1.get(Calendar.DAY_OF_MONTH); 
        int day2 = c2.get(Calendar.DAY_OF_MONTH); 
        int day3 = day1 - 1; 
        
        if(day3==day2){
        	return 0;//满整月
        }else if(day2<day3){
        	return day3;//不满自然整月
        }
        
    	return day2-day3;
    }
   
}
