package qgb.net.html;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;











import qgb.CharsetName;
import qgb.T;
import qgb.jdbc.Mysql;
import qgb.net.Get;
import qgb.net.HttpRequest;

public class JsoupTest {

	public static void main(String[] args) throws FileNotFoundException {
		//T.cst_test_path=T.cst_test_path+"/jsoup/";
		//PrintStream ps =new PrintStream(T.cst_test_path+stn+"ps.txt");
		//System.setOut(ps);
		String stn="1";
		String stin=T.read_st(stn+".html", CharsetName.GST_GBK);
//		Document doc =Jsoup.parse("http://cs.58.com/qzxueshengjianzhi/?"
//				+ "param8426=1&PGTID=14167135996990.2586963609792292&ClickID=6",999);//Jsoup.parse(stin);
		Elements eall;
		eall= doc.select("tbody[id*=thread_]");
		//select("div[id^=jaychang]");
		int i=1;
		for (Element src :eall) {
			//T.print(src.nodeName());
			//T.print("className|"+src.className());
			//T.print(src.html());
			T.print((i++)+ src.text()+"\n	"+src.html().replace("\n",""));
		}

		if (0==0) {
			return;	
		}
		
		Elements links = doc.select("a[href]");
	    Elements media = doc.select("[src]");
	    Elements imports = doc.select("link[href]");

	    print("Media: (%d)", media.size());
	    for (Element src : media) {
	        if (src.tagName().equals("img"))
	            print(" * %s: <%s> %sx%s (%s)",
	                    src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
	                    trim(src.attr("alt"), 20));
	        else
	            print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
	    }

	    print("\nImports: (%d)", imports.size());
	    for (Element link : imports) {
	        print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
	    }

	    print("\nLinks: (%d)", links.size());
	    for (Element link : links) {
	        print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
	    }

//		Attributes att= doc.attributes();
//		java.util.List<Attribute> list= att.asList();
//		T.print(list.size());
//		for (int i = 0; i < list.size(); i++) {
//			T.print(list.get(i));
//		}
		
		
		
		
//		Element content = doc.getElementById("content");
//		Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//		  String linkHref = link.attr("href");
//		  String linkText = link.text();
//		  T.print(linkHref);
//		  T.print(linkText);
//		}
		
	}
	


private static void getPage() {
		T.write("1.html", HttpRequest.get("http://bbs.kuaibo.com/forum-36-1.html"));
	}



private static void print(String msg, Object... args) {
    System.out.println(String.format(msg, args));
}

private static String trim(String s, int width) {
    if (s.length() > width)
        return s.substring(0, width-1) + ".";
    else
        return s;
}

}
