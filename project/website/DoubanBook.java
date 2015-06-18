package qgb.project.website;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.*;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.net.QNet;

public class DoubanBook {
	public static Connection gConn;
	public static Statement gStat;
	/** TODO:DB Test */
	final private static String gsDbName = U.autoPath("Book.db"); // "./mh.db";
	final static String gsCid = "id";
	final static String gsCTitle = "stitle";
	final static String gsCDetail = "sdetail";

	final static String gsCRating = "Rating";
	final static String gsCUrl = "url";
	final static String gsCiComment = "comment";
	final static String gsTableBook = "book";
	static PreparedStatement prep;
	static String sql = T.format(
			"INSERT INTO %s(%s,%s,%s  ,%s) VALUES(?,?,?  ,?);", gsTableBook,
			gsCTitle, gsCDetail, gsCRating, gsCUrl);

	static {
		try {
			Class.forName("org.sqlite.JDBC");
			// 建立连接
			gConn = DriverManager.getConnection("jdbc:sqlite:" + gsDbName, "",
					"");
			gConn.setAutoCommit(true);
			gStat = gConn.createStatement();
			createTable();
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
		
		Class.forName(DoubanBook.class.getName());
		String surl = "http://www.douban.com/tag/%E7%BC%96%E7%A8%8B/book?start=";
		for (int i = 0; i < 4171; i=i+15) {
			nextList(surl+i);
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
		// U.write("dou.html",doc.html());
		// U.msgbox();
		Elements eall = doc.select("div[class=mod book-list]");

		eall = eall.select("dd");
		//
		String sDetail = "", sRating = "", sTitle = "", sUrl = "";
		for (Element tr : eall) {
			//U.print(tr.html());
			//U.msgbox();

			sUrl = tr.html();
			sUrl = T.sub(sUrl, "href=\"", "\"").trim();

			sTitle = tr.select("a[class=title]").text().trim();

			sDetail = tr.select("div[class=desc]").text().trim();

			sRating=tr.select("span[class=rating_nums]").text().trim();

			insertDB(sTitle, sDetail, sRating, sUrl);

		}
		// U.msgbox();

		return "";
	}

	private static void insertDB(String asTitle, String asDetail,
			String asRating, String asUrl) {
	//	 U.msgbox("t=%s,d=%s,r=%s,u=%s",asTitle,asDetail,asRating,asUrl);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asTitle);
			prep.setString(2, asDetail);
			prep.setString(3, asRating);
			prep.setString(4, asUrl);

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
			sql = T.format("create table %s(", gsTableBook);
			sql = sql
					+ T.format(
							"%s integer,%s integer,%s char(22) ,%s char(222) ,%s char(3),%s char(21)"
									+ ",PRIMARY KEY (%s));", gsCid,
							gsCiComment, gsCTitle, gsCDetail, gsCRating,
							gsCUrl, gsCid);
			U.msgbox(sql);
			gStat.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
