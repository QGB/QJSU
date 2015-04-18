package qgb.project.proxy;

import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.CharsetName;
import qgb.*;

import qgb.net.HttpRequest;
import qgb.net.html.JsoupTest;

public class GoogleCode {
	static String gsUrl;
	public static void main(String[] args) {
		svn_download();
	}

	private static void svn_download() {
		U.setProxy("127.0.0.1","8580");
		U.gstTestPath=U.gstTestPath+"save-data-dict/";
		U.print(U.gstTestPath);
		gsUrl="http://save-data-dict.googlecode.com/svn/";
		re_parse("",getDoc(gsUrl));
	}

	private static void re_parse(String asRePath,Document adoc) {
		Elements es= adoc.select("li");
		String sta="",sUrl="";
		
		for(Element e:es){
			sta=e.select("a[href]").html();
			if (sta.endsWith("..")) {
				continue;
			}			
			
			sUrl=gsUrl+asRePath+sta;
			U.print(sUrl);
			
			if (sta.endsWith("/")) {
				re_parse(asRePath+sta,getDoc(sUrl));
			}else {
				InputStream is=HttpRequest.get(sUrl);
				for (int i = 0; i < 3; i++) {
					if (is!=null) {
						U.write(asRePath+F.name(sta) ,is );	
						break;
					}else {
						continue;
					}
				}
			
				
			}
			
		}
	}

	private static Document getDoc(String asUrl) {
		InputStream is= HttpRequest.get(asUrl);
		return Jsoup.parse(U.InputStreamToString(is, CharsetName.GST_UTF8));
	}
}
