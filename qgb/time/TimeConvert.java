package qgb.time;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import qgb.T;

public class TimeConvert {

	public static void main(String[] args) throws Exception {
		
	}

	
//	public static String toString(String ast) {	//abandon
//		return toString(Long.valueOf(ast));
//	}
	
	/**
	 * @param ast	"yyyy-MM-dd[HH-mm-ss.SSS]"
	 */
	public static long toLong(String ast) {
		if (ast.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d[]")) {
			T.print("matches");
		}
		 
		return 0;
		
	}
	/**
	 *
	 *@see java.util.Date
	 */
	public static long toLong(int year, int month, int date, int hourOfDay, int minute,
            int second,int millisecond) {
		//构造一个带有默认时区和语言环境的 Calendar。
		GregorianCalendar gc=new GregorianCalendar();
		//中国标准时间。
		//gc.set(year, month, date, hourOfDay, minute, second);
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
		gc.set(Calendar.YEAR, year);
		/**
		 *月份由从 0 至 11 的整数表示；0 是一月、1 是二月等等；因此 11 是十二月。
		 */
		gc.set(Calendar.MONTH, month-1);	
		gc.set(Calendar.DATE, date);
		gc.set(Calendar.HOUR_OF_DAY, hourOfDay);
		gc.set(Calendar.MINUTE, minute);
		gc.set(Calendar.SECOND, second);
		gc.set(Calendar.MILLISECOND, millisecond);
		
		//T.print(gc.getTimeZone().getDisplayName());
		return gc.getTimeInMillis();
		
	}
	
	
	/**
	 * accuracy: second
	 */
	public static String toString(int ai) {
		// int ia=1391451796;//2014-02-04[02-23-16]
		// T.print(ia);
		// T.print(Integer.MAX_VALUE);
		long ticks = (long) ai * 1000;
		Time date = new Time(ticks);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
		return df.format(date);
	}

	/**
	 * accuracy: ms
	 * 
	 */
	public static String toString(long along) {
		Time date = new Time(along);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss.SSS]");
		return df.format(date);
	}
}
