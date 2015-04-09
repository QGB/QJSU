package qgb.time;

import java.util.Date;

public class Traversal {
	private Date gDate;
	public Traversal(int year, int month, int date, 
			int hourOfDay, int minute,int second,int millisecond) {
	this(new Date(TimeConvert.toLong(year, month, date,
			hourOfDay, minute, second, millisecond)));
	}
	public Traversal(Date aDate) {
		gDate=aDate;
	}

	public Date name() {
		
	}
	
	public static void main(String[] args) {
		Traversal t=new Traversal(2012, 12, 7, 0, 0, 0, 0);
		// TODO Auto-generated method stub

	}

}
