package qgb.project.math;

import qgb.*;

public class Primer {

	public static void main(String[] args) {
		int imax=200;
		for (int i = 2; i <=imax; i++) {
			int in=0;
			for (int j = 2; j <=i; j++) {
				int k=1,m=0;
				while (m<i) {
					m=j*(k++);
				}
				if(m==i){
					in++;
				}
			}
			if (in==1) {
				U.print(i);
			}
		}
		
	}

}
