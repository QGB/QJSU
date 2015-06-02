package qgb.project.website;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.zip.DataFormatException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.*;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.net.QNet;

public class Zhihu {
	static String gsZUser="incredible-vczh";
	static String gsDomain="http://www.zhihu.com/";
	static String gsAnswerList=gsDomain+"people/"+gsZUser+"/answers?page=";
	
	public static Connection gConn;
	public static Statement gStat;
	/** TODO:DB Test */
	final private static String gsDbName = U.autoPath("zhihu.db"); // "./mh.db";
	final static String gsCid = "id";
	final static String gsCTitle = "stitle";
	final static String gsCDetail = "sdetail";
	final static String gsCTime = "stime";
	final static String gsCiUp= "up";
	final static String gsCEditTime= "EditTime";
	final static String gsCiComment = "comment";
	final static String gsCUrl= "url";
	final static String gsTableZUser =T.delChars(gsZUser,'-')  ;
	static PreparedStatement prep;
	static String sql = T.format("INSERT INTO %s(%s,%s,%s  ,%s,%s,%s,%s) VALUES(?,?,?  ,?,?,?,?);",
			gsTableZUser, gsCDetail,gsCiComment ,gsCUrl,gsCEditTime,gsCiUp,gsCTime,gsCTitle);

	static {
		try {
			Class.forName("org.sqlite.JDBC");
			// 建立连接
			gConn = DriverManager.getConnection("jdbc:sqlite:" + gsDbName, "",
					"");
			gConn.setAutoCommit(true);
			gStat = gConn.createStatement();
			
			prep = gConn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				gConn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		//U.print(gConn);
		//createTable();
		Class.forName(Zhihu.class.getName());
		for (int i = 353; i <358; i++) {
			nextList(gsAnswerList+i);
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
		Elements eall= doc.select("div[id=zh-profile-answer-list]").select("div[class=zm-item]");
		int i=1;
		Elements esby;
		//
		String sDetail="",siComment="0",sid="",sedit = "",siUp="",sTime="",sTitle="",sUrl="";
		for (Element tr :eall) {	
			U.print(i++);
//			U.print(tr.text());
//			U.msgbox();
//			if (true) {
//				continue;
//			} 
			sUrl=tr.select("h2").html();
			sUrl=T.sub(sUrl,"href=\"","\"").trim();
			
			sTitle=tr.select("a[class=question_link]").html().trim();
			
			siUp=tr.select("div[class=zm-item-vote]").text().trim();
			if (siUp.length()<1) {
				siUp="0";
			}
			//siDown=tr.select("");
			
			sDetail=tr.select("textarea[class=content hidden]").text()	;	
			
			sedit=T.sub(sDetail, "answer-date-link-wrap");
			//U.print("e[%s]e",sedit);
			sTime=T.sub(sedit, "发布于","\"").trim();
			if (sTime.length()<1) {
				sTime=T.sub(sedit, "发布于","<").trim();
			}
			sedit=T.sub(sedit, "编辑于","</a>").trim();
			
			sDetail=T.sub(sDetail,"","<span class=\"answer-date-link-wrap\">");
			sDetail=T.replace(sDetail,"<br>", "\n");
			sDetail=T.replace(sDetail,"<p>", "\n");
			sDetail=T.replace(sDetail,"</p>", "\n");
			sDetail=T.replace(sDetail,"\n\n", "\n").trim();
			
			//sedit=tr.select("div[class*=zm]").html();
		
	
		
			
			siComment=tr.select("a[class*=toggle-comment]").text();
			siComment=T.sub(siComment,"","条评论").trim();
			if (siComment.length()<1) {
				siComment="0";
			}
			
		insertDB(sDetail, siComment, sUrl, siUp, sTime, sedit, sTitle);
//			U.print(sTime);
//			U.print(sedit);
//			U.print("d[%s]d",sDetail);
//			U.print("[%s,%s]%s\n%s",siUp,siComment,sUrl,sTitle);
//		
//			

//			U.msgbox();
			//sDetail=tr.select(cssQuery)
			
			//insertDB();
		}
		// U.msgbox();
		
		return "";
	}

	private static void insertDB( String asDetail,String asiComment,String asUrl,String asiUp,String asTime,
			String asEditT,String asTitle) {
		
	
		//U.msgbox("n=%s,m=%s,p=%s",asName,asNum,asPW);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asDetail);
			prep.setString(2, asiComment);
			prep.setString(3, asUrl);
			
			prep.setString(4, asEditT);
			prep.setString(5, asiUp);
			prep.setString(6, asTime);
			prep.setString(7, asTitle);
			
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
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
		
	}

	
	private static void createTable() {
		String sql ="";
		try {
			sql=T.format("create table %s(", gsTableZUser);
			sql = sql+T.format("%s integer,%s integer,%s integer,%s char(19) ,%s char(10) ,%s char(10),%s char(21),%s char(200)"
			+ ",PRIMARY KEY (%s));",
								gsCid,      gsCiUp, gsCiComment,gsCTitle, gsCTime , gsCEditTime, gsCUrl,gsCDetail,
					gsCid);
			U.msgbox(sql);
			gStat.executeUpdate(sql);

		}catch (SQLException e) {
			 e.printStackTrace();
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	
	
}
