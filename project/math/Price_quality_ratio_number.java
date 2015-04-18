package qgb.project.math;

import qgb.*;

public class Price_quality_ratio_number {
	static int im=99;
	public static void main(String[] args) {
		PQR_number();
		//PQR_value();
	}
	
	private static void PQR_value() {
		for (int i = 1; i <=im; i++) {
			U.print((float)i/(i*2-1));
		}
	}
	
	private static void PQR_number() {
		int ib=1,i=0,j=0;
		float fa=1,fb=1;
		for ( i = 1; i <= im; i++) {
			fa=(float)i/ib;
			for ( j=ib; ; j++) {
				fb=(float)(i+1)/j;
				if (fa>fb) {
					break;
				}
			}
			U.print("%d/%d",i,ib);
			ib=j;
		}
	}

}
