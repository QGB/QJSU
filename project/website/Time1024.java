package qgb.project.website;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.zip.DataFormatException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.*;
import qgb.net.Get;
import qgb.net.HttpRequest;

public class Time1024 {
	static String gsDomain="http://woge.xyz/";
	private static int giv=9;
	
	
	public static void main(String[] args) {
		U.print();
		U.setProxy("172.17.5.27","808");
		String surl=gsDomain+"thread0806.php?fid=8&search=&page=";
		
		for (int i = 1; i < 99; i++) {
			nextList(surl+i);
		}
		

		//U.print(doc.html());
	}
	private static String nextList(String asurl) {
		if (asurl.length()<2) {
			U.exit();
		}
		U.print(asurl);
		Document doc = null;
		try {
			doc=Get.jsoupDoc(asurl, 4, 2222);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (doc==null) {
			return "";
		}
		Elements eall= doc.select("tr[class=tr3 t_one]");
		int i=0;
		Elements esby;
		//
		for (Element tr :eall) {	
			//U.print(tr.text());
			if(!tr.text().contains("洲] "))
				continue;
			int iv=Integer.valueOf(tr.select("td[class=tal f10 y-style]").text());
			if (iv<giv)
				continue;
			
			//U.msgbox(tr.select("td").get(0).html());
			String surl=tr.select("td").get(0).html();
			
			
			surl=T.sub(surl, "href=\"","\"") 	;
			//U.print(i+ surl);
			parserPage(gsDomain+surl);
			
			 i++;
			
			
		}
		// U.msgbox();
		
		return "";
	}
	
	
	private static void parserPage(String asurl) {
		if (asurl.length()<2) {
			U.exit();
		}
		U.print(asurl);
		Document doc = null;
		try {
			doc=Get.jsoupDoc(asurl, 4, 2222);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (doc==null) {
			return ;
		}
	//	
		Elements eall;
		String[] ys=doc.select("div[class=tpc_content]").get(0).html().split("<input");
		//eall=eall.select("input");
		for (int i = 0; i < ys.length; i++) {
			String surl=T.sub(ys[i],"src=\"","\"");
			if (surl.length()<5)continue;
			
		 	String sf="/climg/"+ T.subLast(surl,"/");
			if (F.isExist(sf)) {
				continue;
			}
			
			try {
				U.write(sf, Get.urlfile(surl));
			} catch (Exception e) {
				e.printStackTrace();
			}
			U.print(surl);
		}
		

//		Elements esby;
//		for (Element ei :eall) {	
//			U.msgbox(ei.html());
//			String surl=T.sub(ei.html(),"src=\"","\"");
//			U.print(surl);
//		}
		//U.msgbox();
	}

	
	
	

	
	
	
}
