package qgb.project.website;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.*;
import qgb.net.Get;


public class BaiduKeyWord {
	final  static String gsb="http://www.baidu.com";
	final static String sKW="";
	final static int iLayout =3;
	static StringBuffer sb=new StringBuffer();
	static StringBuilder sbA=new StringBuilder();
	static HashSet<String> hs=new HashSet<String>();
	static ArrayList<String> al=new ArrayList<String>();
	public static void main(String[] args) {
	//	U.gstTestPath="E:/SourceCode/js/springy-master/";
		String s=traverse("馨予",4);
		
		U.print(s.length());
		U.write("G:/apache-tomcat-5.5.27/webapps/ROOT/d3/city.json",s );
		U.sleep(999);
		U.run("cmd /c \"start http://127.0.0.1:8080/d3/\"");
	}

	static String traverse(String as,int ai){
		if(ai<1||al.contains(as))
			return "";
		al.add(as);
		U.print("%s,%s",as,ai);
		StringBuffer sb1=new StringBuffer();
		sb1.append(T.format("{\"name\": \"%s\"",as));
		//U.msgbox(sb.toString());
		if(ai==1){
			sb1.append("}");
			return  sb1.toString();
		}
		
		Set<String> set=getSet(as);
		//int i=0;
		ArrayList<String> als=new ArrayList<String> ();
		for(String s:set){
			//i++;
			s=traverse(s, ai-1);
			if( s.length()>1){
				als.add(s);
			}
		}
		
		if(als.size()>0){
			sb1.append(",\"children\": [");
			if(als.size()==1){
				sb1.append(als.get(0));
			}else{
				for (int j = 0; j < als.size()-1; j++) {
					sb1.append(als.get(j));
					sb1.append(",");
				}
				sb1.append(als.get(als.size()-1));
			}
			sb1.append("]}");
		}else{
			sb1.append("}");
		}
		return  sb1.toString();
	}
	
	static boolean add(String as){
		if(al.contains(as))
			return false;
		
		al.add(as);
		sb.append(T.format("var v%s=graph.newNode({label: '%s'});",al.indexOf(as),as) 
				);
		return true;
	}
	
	static void arrow(String as1,String as2){
		
		if(al.contains(as1)&&al.contains(as2)==false)
			return;
		
		sbA.append(T.format("graph.newEdge(v%s,v%s);",al.indexOf(as1),al.indexOf(as2)) 
				);
		return ;
	}
	
	@SuppressWarnings("deprecation")
	private static Set<String> getSet(String asw) {
		if(al.size()%9==0){
			U.sleep(200);
		}
		Document doc=null;
		try {
			doc = Get.jsoupDoc( gsb+"/s?wd="+URLEncoder.encode(asw),3,2000);
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//U.print(doc.html());
		//U.print(doc.baseUri());
		Elements es = doc.select("div[id=rs]");
		es = es.select("a[href]");
		//U.print(es.size());
		HashSet<String> set=new HashSet<String>();
		for(Element e:es){
			set.add(e.text());
		}
		return set;
	}

}
