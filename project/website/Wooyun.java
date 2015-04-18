package qgb.project.website;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.IllegalFormatException;
import java.util.zip.DataFormatException;

import qgb.CharsetName;
import qgb.*;
import qgb.jdbc.Mysql;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.text.Regex;

public class Wooyun {
	// http://wooyun.org/bugs/new_public/page/858
	public static void main(String[] args) {
		Mysql.connect("jdbc:mysql://localhost:3306/web", "root", "123456");
		//createTable();
		getPages();

	}

	private static void getPages() {
		String str = "", sthtml = "";

		for (int i = 736; i < 870; i++) {
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
		String stinput = "http://wooyun.org/bugs/new_public/page/" + ai, sthtml = "";
		InputStream is;
		is = HttpRequest.get(stinput);

		try {
			sthtml = U.InputStreamToString(is, CharsetName.GST_UTF8);
			// U.print(sthtml);
			// U.write(i+".html",HttpRequest.get(sturl));
			// str= Get.html(sturl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		sthtml=Regex.getFirst("(?<=<tbody>)[\\w\\W]*(?=</tbody>)", sthtml);
		String[] yst=Regex.getYst("(?<=<tr>)[\\w\\W]*?(?=</tr>)", sthtml);
		String[] ystitle,yst_3;//1<td>,3<th>
		//String[] ystr=new String[4];
		String stitle="",sicommen="",sifollow="",sauthor="",sturl="",stsubmit="";
		int ititle=0,icf=0;
		String sql="";
		
		for (int i = 0; i < yst.length; i++) {
			yst_3=Regex.getYst("(?<=<th>)[\\w\\W]*?(?=</th>)", yst[i]);
			ystitle=Regex.getYst("(?<=<td><a href=\")[\\w\\W]*?(?=</a>)", yst[i]);
			if (yst_3.length!=3|ystitle.length!=1) {
				throw new DataFormatException(ai+" format error!");
			}
			//U.print(yst_3);
			//U.msgbox("");
			ititle= ystitle[0].indexOf("\">");
			stitle=ystitle[0].substring( ititle+2,ystitle[0].length());
			sturl="http://wooyun.org/"+ystitle[0].substring(0, ititle);
			
			stsubmit=yst_3[0];
			yst_3[1]=Regex.getFirst("(?<=#comment\">)[\\w\\W]*?(?=</a>)", yst_3[1]);
			icf=yst_3[1].indexOf('/');
			//U.print("yst_3[1]:"+yst_3[1]);
			sicommen=yst_3[1].substring(0, icf);
			sifollow=yst_3[1].substring(icf+1,yst_3[1].length());
			sauthor=Regex.getFirst("(?<=<a title=\")[\\w\\W]*?(?=\" href=\")", yst_3[2]);
			
			
			
			sql = "INSERT INTO wooyun (stitle,icommen,ifollow,sauthor,sturl,tsubmit) "
					+ "VALUES ('"
					+ stitle
					+ "',"
					+ sicommen
					+ ","
					+ sifollow
					+ ",'"
					+ sauthor
					+ "','"
					+ sturl
					+ "','"
					+ stsubmit
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
			
		}
		
		if (yst.length==0) {
			throw new IllegalArgumentException(ai+ " null !");
		}
		//U.print(ystr);
		return sthtml;
	}

	private static void createTable() {
		String sql = "CREATE TABLE wooyun ("
				+ "stitle  varchar(255)  NOT NULL,"// ����
				+ "icommen integer       NOT NULL,"// ����
				+ "ifollow integer       NOT NULL,"// ��ע
				+ "sauthor varchar(20)   NOT NULL,"// ����
				+ "sturl   varchar(50)   NOT NULL,"// ��ַ
				+ "tsubmit DATETIME      NOT NULL,"// �ύ
				+ "topened DATETIME,"// ����
				+ "dnotify DATE,"// ֪ͨ
				+ "dverify DATE,"// ȷ��
				+ "dneglec DATE,"// ����
				+ "dcore   DATE,"// ���� whitehat
				+ "dcommon DATE,"// ��ͨ whitehat
				+ "dIntern DATE,"// ʵϰ whitehat
				+ "PRIMARY KEY (sturl));";//

		Mysql.SQL_execute(sql);

	}

}
