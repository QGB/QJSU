package qgb.project.zdsoft;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.CharsetName;
import qgb.*;
import qgb.jdbc.Mysql;
import qgb.net.HttpRequest;

public class ZDSoft {

	public static void main(String[] args) {
		//zdsoft();
		//U.exit();
		
		String str="";
		byte[] yb;
		yb=U.InputStreamToBytes(HttpRequest.get("http://61.187.64.231/admin/manageUsers.aspx?bigClassId=1&currentPageIndex=1"));
		str=U.InputStreamToString(U.BytesToInputStream(yb), CharsetName.GST_GB2312);
		U.write("zdsoft1.html", U.BytesToInputStream(yb));
		U.print(str);
		// TODO Auto-generated method stub

	}

	
private static void zdsoft() {
	Mysql.connect();
	createTable();
	String stin=U.readSt("zdsoft.html", CharsetName.GST_GB2312);
	//U.print(stin);
	Document doc =Jsoup.parse(stin);
	Elements links = doc.select("a[href]");
	String sta="";
	int ia=-1;
    for (Element link : links) {
    	sta=link.attr("href");
    	if (sta.endsWith(".aspx")) {
    		ia=sta.indexOf("/");
    		if (ia!=-1) {
    			//U.print(sta);
    			sta=sta.substring(ia+1, sta.length());	
			}
    		sta="http://61.187.64.231/admin/"+sta;
    		U.print(sta);
    		Mysql.SQL_execute("INSERT INTO zdsoft (sturl) "
					+ "VALUES ('"
					+ sta
					+ "');");
    		//Mysql
    		
		}
    	
    	}
    

	}


	private static void createTable() {
		String sql = "CREATE TABLE zdsoft ("
				+ "sturl  varchar(255)  NOT NULL,"
				+ "PRIMARY KEY (sturl));";//

		Mysql.SQL_execute(sql);

	}
	
}
