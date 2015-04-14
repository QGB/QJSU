package qgb.os.win;

import java.lang.reflect.Method;
import java.util.Arrays;

import qgb.T;
import qgb.U;

public class Vc {
	public static void aa(String[] t) {
		System.out.println(t + "T");
	}
	public static void main(String[] args) throws ClassNotFoundException{
		String[] ys=U.readSt("load.txt").split(T.gsWinNewline);
		//U.print(ys);
		Class<?>[] yc=new Class[ys.length];
		int im=0;
		for (int i = 0; i < ys.length; i++) {
			ys[i]=T.sub(ys[i], "[Loaded ", " from");
			//if (ys[i].startsWith("j")) {
				try {
					yc[im]=Class.forName(ys[i]);
					im++;
				} catch (Exception e) {
					//e.printStackTrace();
					//U.msgbox(ys[i]);
					continue;
				}
				//U.print("[%-3s]%s",i,yc[i]);
			//}
		
		}
		yc=Arrays.copyOfRange(yc, 0, im);
		U.print(yc);
		for (int i = 0; i < yc.length; i++) {
			Method[] ym=yc[i].getDeclaredMethods();
			for (int j = 0; j < ym.length; j++) {
				if (ym[j].toString().contains(".main(j")) {
					U.print("[%-3s]%s",i,yc[i]);
					U.print("[%-3s]%s",j, ym[j]);
					U.msgbox();
				}
			}
		
		}
	}
}