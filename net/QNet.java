package qgb.net;

import java.io.IOException;
import java.net.MalformedURLException;

import qgb.T;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.XmlSerializer;
import com.gargoylesoftware.htmlunit.javascript.host.Text;

public class QNet {
	public class GetHtml implements Runnable {
		String st_url = "";
		String st_html = "";
		int i_sleep = 0;

		GetHtml(String ast_url, int ai_sleep) {
			st_url = ast_url;
			i_sleep = ai_sleep;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			final WebClient webClient = new WebClient();
			final HtmlPage htmlPage;
			// String st_url =
			// "http://dict.youdao.com/search?q=oo&keyfrom=dict.index";
			// "http://dict.cn/mini.php?q=skandal"
			// str_url="http://dict.cn/mini.php?q="+ast_word;'
			// /////////////////////////////////////////
			try {
				htmlPage = webClient.getPage(st_url);
				Thread.sleep(i_sleep);
				st_html = new XmlSerializer().asXml(htmlPage
						.getDocumentElement());
				// T.write("oo_y_utf8.html", st_html,"UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// //////////////////////////////////////
			// T.print(st_html);

		}

		public String Get() {

			return st_html;
		}

	}

	public static String html(String ast_url)
			throws FailingHttpStatusCodeException {
		final WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
		wc.getOptions().setThrowExceptionOnScriptError(false);
		// webClient.setThrowExceptionOnFailingStatusCode(false);
		// webClient.setThrowExceptionOnScriptError(false);
		// String str_url = "";
		// str_url="http://dict.cn/mini.php?q="+ast_word;
		Page p=null;
		try {
			p = wc.getPage(ast_url);
			T.sleep(200);
		} catch (MalformedURLException e) {
			T.error(e, Get.class + " 错误的 URL。或者在规范字符串中找不到任何合法协议，或者无法解析字符串。");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		try {
			HtmlPage hp=HtmlPage.class.cast(p);
			return hp.asXml();
		} catch (ClassCastException e) {}
		try {
			TextPage tp=TextPage.class.cast(p);
			return tp.getContent();
		} catch (ClassCastException e) {}
		
		T.msgbox(p);
		// HtmlElement he=htmlPage.getDocumentElement();
		String str = "";// hp.asXml();

		return str;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
