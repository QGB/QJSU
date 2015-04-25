package qgb.project.discuz;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.sql.Date;
import java.sql.Time;
import java.util.IllegalFormatException;
import java.util.zip.DataFormatException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import qgb.*;
import qgb.jdbc.Mysql;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.text.Regex;
import qgb.time.TimeConvert;

public class Bbs_kuaibo_com {
	final static Date gDate0=
			new Date(TimeConvert.toLong(2014, 6, 2, 20, 10, 0, 0));
	static String  stitle="";
	static boolean bimg=false;
	static boolean bdigest=false;
	static String icommen="";
	static String iview="";
	static String sauthor="";
	static String sturl="";
	static String tsubmit="";
	static String tlast="";
	static String slastName="";
	//table columns 
	static String sql="";
	public static void main(String[] args) {
		Document da=Jsoup.parse("&nbsp;");
		U.print(da);
		U.print(da.text());
		//da.
		//parserPage(Jsoup.parse(U.readSt("1.html")));

	}
	

    
    
    
    
    
    
	private static void getPages() {
		String str = "", sthtml = "";

		for (int i = 1; i <=76; i++) {
			try {
				getPage(i);
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				U.write(i+"Exception.txt",e.getClass()+"\n"+U.getCurrentTime() );
				e.printStackTrace();
				continue;
			}catch (NullPointerException e) {
				U.write(i+"Exception.txt",e.getClass()+"\n"+U.getCurrentTime() );
				e.printStackTrace();
			}catch (SocketException e) {
				U.write(i+"Exception.txt",e.getClass()+"\n"+U.getCurrentTime() );
				e.printStackTrace();
			}catch (IOException e) {
				U.write(i+"Exception.txt",e.getClass()+"\n"+U.getCurrentTime() );
				e.printStackTrace();
			}
			
			
			
			
			//U.print(getPage(i));
		}

		//U.write("862.html", getPage(862), CharsetName.GST_UTF8);

	}

	private static String getPage(int ai) throws DataFormatException,SocketException,IOException,NullPointerException {
		String stinput = "http://bbs.kuaibo.com/forum-36-" + ai+".html", sthtml = "";
		InputStream is;
		is = HttpRequest.get(stinput);
		byte[] bytes;
		bytes=U.InputStreamToBytes(is);
		is=U.BytesToInputStream(bytes);

		try {
			sthtml = U.InputStreamToString(is, CharsetName.GST_UTF8);
			U.print(Bbs_kuaibo_com.class+":"+ ai);
			U.write("discuz/"+ai+".html",U.BytesToInputStream(bytes));
			// str= Get.html(sturl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (0==0) {
			return "";
		}
//		U.exit();
		
		
		parserPage(Jsoup.parse(sthtml));
			
		sql = "INSERT INTO wooyun (stitle,icommen,iview,sauthor,sturl,tsubmit,tlast,slastName) "
					+ "VALUES ('"
					+ stitle
					+ "',"
					+ icommen
					+ ","
					+ iview
					+ ",'"
					+ sauthor
					+ "','"
					+ sturl
					+ "','"
					+ tsubmit
					+ "','"
					+ tlast
					+ "','"
					+ slastName
					+ "');";;
			
			U.print(sql);
			Mysql.SQL_execute(sql);
			//yst_3[0]
			//U.print(yst[0]);
//			U.print(stitle);
//			U.print(sicommen);
//			U.print(sifollow);
//			U.print(sauthor);	
//			U.print(sturl);	
//			U.print(stsubmit);	
//			U.print("--------"+i	+ "----------");
			//stitle
			return sthtml;
			
		
	}

	private static void parserPage(Document adPage) {
		Elements eall;
		eall= adPage.select("tbody[id*=thread_]");
		int i=0;
		Elements esby;
		for (Element src :eall) {	
			if (i<15-1) {
				i++;
				continue;
			}
			
			stitle= src.select("a[class=s xst]").text();
			bimg=src.select("img[title=ͼƬ����]").toArray().length==1;
			bdigest=src.select("img[title*=����]").toArray().length==1;
			
			esby=src.select("td[class=by]");
			if (esby.size()==2) {
				sauthor=esby.select("cite").get(0).text();
				tsubmit=convertTime( esby.select("em").get(0).text());
				slastName=esby.select("cite").get(1).text();
				
			}
		
			
			U.print(stitle); 
			U.print(bimg);
			U.print(bdigest);
			U.print(sauthor);
			U.print(tsubmit);
			U.print(slastName);
			//U.print(esby.size());
			//U.print(esby.html());
			i++;
			//U.write("/jsoup/bool.txt", src.html());
			U.exit();
			if (i==3) {
				U.exit();
			}
			
			//U.print((i++)+ src.text()+"\n	"+src.html().replace("\n",""));
		}
		
	}

	private static String convertTime(String ast) {
		//U.print(ast);
		ast=ast.replace(Jsoup.parse("&nbsp;").text(), " ");
		return ast;
		//return null;
	}

	private static void createTable() {
		String sql = "CREATE TABLE bbs_kuaibo_com ("
				+ "stitle  varchar(255)  NOT NULL,"// ����
				+ "icommen integer       NOT NULL,"// ����
				+ "iview   integer       NOT NULL,"// ��ע
				+ "sauthor varchar(20)   NOT NULL,"// ����
				+ "sturl   varchar(30)   NOT NULL,"// ��ַ
				+ "tsubmit DATETIME      NOT NULL,"// �ύ
				+ "tlast   DATETIME      NOT NULL,"// ����
				+ "slastName varchar(20) NOT NULL,"// ��ַ
				+ "PRIMARY KEY (sturl));";//

		Mysql.SQL_execute(sql);

	}

	
	
	
}
