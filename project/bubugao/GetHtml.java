package qgb.project.bubugao;

import java.io.IOException;
import java.net.MalformedURLException;

import qgb.*;
import qgb.net.Get;

public class GetHtml {

	public static void main(String[] args) {
//		U.setOutStream("e.txt");
//		for (int i = 1; i <256; i++) {
//			U.print(Get.html("http://124.232.133."+i+":80"));
//		}
//		
		//U.exit();
		U.gstTestPath=U.gstTestPath+"bubugao/";
		String stip=U.readSt("ip80.txt");
		String[] yst=stip.split("\r\n");
//		U.print(yst);
		for (int i = 0; i < yst.length; i++) {
				U.write(yst[i].substring(0, yst[i].length()-3)+".html", 
						Get.html("http://"+yst[i])
						);
				U.print(yst[i]);
			 
			
		}
	}

}
