package qgb.project.website;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.*;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.net.QNet;

public class Zhaojie {
	public final static String gsDomain="http://blog.zhaojie.me";
	/** TODO:DB Test */
	final private static String gsDbName = U.autoPath("zhaojie.db"); // "./mh.db";
	final static String gsCid = "id";
	final static String gsCVisits = "v";
//	final static String gsCTitle = "stitle";
//	final static String gsCDetail = "sdetail";
//
//	final static String gsCRating = "Rating";
	final static String gsCUrl = "url";
	final static String gsCiComment = "comment";
	final static String gsTable = "zj";
	
	static Connection gConn;
	static Statement gStat;
	static PreparedStatement prep;
	static String sql = T.format(
			"INSERT INTO %s(%s,%s,%s  ) VALUES(?,?,? );", gsTable,
			 gsCVisits, gsCiComment, gsCUrl);
	

	static {
		try {
			Class.forName("org.sqlite.JDBC");
			// 建立连接
			gConn = DriverManager.getConnection("jdbc:sqlite:" + gsDbName, "",
					"");
			gConn.setAutoCommit(true);
			gStat = gConn.createStatement();
			if (!F.isExist(gsDbName)) {
				createTable();
			}
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
		// U.print(gConn);
		String[] ys=U.readSt("zjl.txt").split("\n");

		Class.forName(Zhaojie.class.getName());
		//String surl = "http://blog.zhaojie.me/";
		
		for (int i = 0; i < ys.length; i++) {
			nextList(gsDomain+ys[i]);			
		}
		
			
		// U.print(doc.html());
	}

	private static String nextList(String asurl) {
		if (asurl.length() < 2) {
			U.exit();
		}
		U.print(asurl);
		Document doc = null;
		try {
			InputStream is = HttpRequest.get(asurl);
			doc = Jsoup.parse(is, CharsetName.GST_UTF8, asurl);
			// doc=Get.jsoupDoc(asurl, 4, 2222);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (doc == null) {
			return "";
		}
		// U.write(gsTable+".html",doc.html());
		//U.msgbox();
		Elements eall = doc.select("div[id=content]");

		eall = eall.select("div[class=post]");
		//
		String  sC = "", sV = "", sUrl = "";
		for (Element tr : eall) {
			//U.print(tr.html());
			//U.msgbox();

			sUrl = tr.select("h2").html();
			sUrl = gsDomain+ T.sub(sUrl, "href=\"", "\"").trim();

			sV = tr.select("small").text().trim();
			sV=T.sub(sV, ", ", "visits").trim();
			
			
			sC=tr.select("li[class=icon_comment icon_r]").text().trim();
			sC=T.sub(sC,"", "Comments");
			//U.msgbox(sC);
			insertDB(sV,  sC, sUrl);

		}
		// U.msgbox();

		return "";
	}

	private static void insertDB(String asV, String asC,String asUrl) {
	//	 U.msgbox("t=%s,d=%s,r=%s,u=%s",asTitle,asDetail,asRating,asUrl);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asV);
			prep.setString(2, asC);
			prep.setString(3, asUrl);

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
		if (asurl.length() < 2) {
			U.exit();
		}
		U.print(asurl);
		Document doc = null;
		try {
			doc = Get.jsoupDoc(asurl, 4, 2222);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (doc == null) {
			return;
		}
		//
		Elements eall;
		String[] ys = doc.select("div[class=tpc_content]").get(0).html()
				.split("<input");
		// eall=eall.select("input");
		for (int i = 0; i < ys.length; i++) {
			String surl = T.sub(ys[i], "src=\"", "\"");
			if (surl.length() < 5)
				continue;

			String sf = "/climg/" + T.subLast(surl, "/");
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
		String sql = "";
		try {
			sql = T.format("create table %s(", gsTable);
			sql = sql
					+ T.format("%s integer,%s integer,%s integer,%s char(22) ,PRIMARY KEY (%s));", gsCid,
							gsCVisits,gsCiComment, 	gsCUrl, gsCid);
			U.msgbox(sql);
			gStat.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
