package qgb.project;

import java.io.IOException;


import qgb.*;
import qgb.net.HttpRequest;
import qgb.text.Regex;


/* second-level domain (SLD)
 * @Time 2014-3-2 18:39:16
 * */
public class SLD_info {	
	public static void main(String[] args) throws IOException  {
		SLD_info sInfo=new SLD_info("ccsu.cn");
//"\\w{1,20}\\.ccsu\\.cn"
	}
	
	String gst_html;
	/*
	 * 
	 * */
	public SLD_info(String ast_domain) throws IOException  {		
		String[] yst = null;
		//gst_html= HttpRequest.post("http://i.links.cn/subdomain/", "domain="+ast_domain+ "&b2=1&b3=1&b4=1");
		//U.print(gst_html);
		//U.write("sld.html", gst_html);
		//yst=U.readSt("yst.txt").split("\n");
		//yst= Regex.getYst("\\w{1,20}\\.ccsu\\.cn", gst_html);
		
		yst=Y.delMuti(yst);
		//U.print("|\n|");
		U.write("ysld.txt",Y.ArrayToStr(yst,"\n"));
		U.print(yst);
	}

	
}
