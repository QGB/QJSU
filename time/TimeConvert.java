package qgb.time;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import qgb.*;

public class TimeConvert {

	public static void main(String[] args) throws Exception {
		
	}

	
//	public static String toString(String ast) {	//abandon
//		return toString(Long.valueOf(ast));
//	}
	
	/**
	 * @param ast	"yyyy-MM-dd[HH-mm-ss.SSS]"
	 */
	//TODO
	public static long toLong(String ast) {
		if (ast.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d[]")) {
			U.print("matches");
		}
		 
		return 0;
		
	}
	/**
	 *
	 *@see java.util.Date
	 */
	public static long toLong(int year, int month, int date, int hourOfDay, int minute,
            int second,int millisecond) {
		//����һ������Ĭ��ʱ������Ի����� Calendar��
		GregorianCalendar gc=new GregorianCalendar();
		//�й��׼ʱ�䡣
		//gc.set(year, month, date, hourOfDay, minute, second);
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
		gc.set(Calendar.YEAR, year);
		/**
		 *�·��ɴ� 0 �� 11 �������ʾ��0 ��һ�¡�1 �Ƕ��µȵȣ���� 11 ��ʮ���¡�
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
