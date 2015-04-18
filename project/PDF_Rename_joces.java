package qgb.project;

import java.io.BufferedInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import qgb.CharsetName;
import qgb.*;

import qgb.net.Get;
import qgb.text.Regex;

public class PDF_Rename_joces {

	public static void main(String[] args) throws Exception {
		final int imin=9870;//12058;
		int inum=0;
		BufferedInputStream bis;
		
		String st_name="";
		
		for (int i = 0; i < 13838-imin; i++) {//5479
			inum=imin+i;
			bis=Get.urlfile("http://www.joces.org.cn/CN/article/downloadArticleFile.do?attachType=PDF&id="+inum);
			if (bis==null) {
				U.print(inum+" error!");
				continue;
			}
			st_name=get_name(inum);
			U.write("pdf/"+st_name, bis);
			U.print(st_name);
		}
		

	}

	public static String get_name(int ai_number) throws Exception {
		String st_pdf_name = "", st_html = "", st_re = "";
		
		//st_html = U.readSt("13827.shtml", "UTF-8");
		st_html=Get.html("http://www.joces.org.cn/CN/abstract/abstract"+ai_number+".shtml");

		st_re = "(?<=<td class=\"J_ZhaiYao_BiaoTi\" valign=\"top\" height=\"20\">\\n).*(?=</td>)";
		//U.print(st_re);
		st_pdf_name=Regex.getFirst(st_re, st_html).trim();
		
		st_re = "(?<=<td class=\"J_ZhaiYao_ZuoZhe\" valign=\"top\">\\n).*(?=</td>)";
		//U.print(st_re);
		st_pdf_name = ai_number+st_pdf_name +"_"+ Regex.getFirst(st_re, st_html).trim();
		
		//U.print(st_pdf_name);
		st_pdf_name=F.name(st_pdf_name)+".pdf";
		//U.print(st_pdf_name);
		
		U.write("shtml/"+ai_number+".shtml",st_html,CharsetName.GST_UTF8);
		return st_pdf_name;
	}
}
