package qgb.time;

import java.util.Calendar;
import java.util.Date;

import qgb.T;


public class Test {
	
	public static void main(String[] args)  {	

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			T.print(System.currentTimeMillis());
			//sbd.append(gst+i+"|"+date.getTime()+"\n");
			
		}
	}
	
	
	
}
			



