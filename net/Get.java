package qgb.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import qgb.CharsetName;
import qgb.T;
import qgb.text.QText;
import test.t2;

/////////////////////////////////////////////////
public class Get {
	public static BufferedInputStream urlfile(String asUrl)
			throws MalformedURLException, IOException {
		
		int HttpResult = 0; // ���������ص�״̬
		URL url = new URL(asUrl); // ����URL
		URLConnection urlconn = url.openConnection(); // ��ͼ���Ӳ�ȡ�÷���״̬��urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		try {
			HttpResult = httpconn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println("HTTP Status-Code:"+HttpResult);

		if (HttpResult != HttpURLConnection.HTTP_OK) { // ������HTTP_OK˵�����Ӳ��ɹ�
			// System.out.print("fail");
			T.notify("url=" + asUrl + "\n	HttpResult=" + HttpResult);
			return null;
		} else {
			int filesize = urlconn.getContentLength(); // ȡ��ݳ���

			// System.out.println("qgbt_filesize:" + filesize);

			BufferedInputStream bis = new BufferedInputStream(
					urlconn.getInputStream());
			return bis;

		}

	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		T.print(urlfile("http://www.baidu.com/s?wd=git 下载").toString());
		T.exit();
		String surl = "";
		surl = "13827";
		surl = "http://www.joces.org.cn/CN/abstract/abstract" + surl + ".shtml";

		try {
			jsoupDoc(surl, 1, 0);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// surl= html("http://www.joces.org.cn/CN/abstract/abstract"+
		// surl+".shtml");
		// T.print(surl);
		// urlfile("http://www.joces.org.cn/CN/abstract/abstract"+
		// surl+".shtml");
		// T.print("test1 qgb");
		// T.write(surl + ".pdf",
		// urlfile("http://www.joces.org.cn/CN/article/downloadArticleFile.do?attachType=PDF&id="
		// + surl));
		// T.write(surl + ".shtml",
		// urlfile("http://www.joces.org.cn/CN/abstract/abstract"
		// + surl+".shtml"));

	}

	/**
	 * @param aiRetry 1
	 * @throws java.net.SocketTimeoutException
	 *             if the connection times out
	 * @throws MalformedURLException
	 * @throws IOException
	 *             if a connection or read error occurs
	 **/
	public static Document jsoupDoc(String asUrl, int aiRetry, int timeoutMillis)
			throws IllegalArgumentException, IOException,
			SocketTimeoutException, MalformedURLException {
		if (aiRetry < 1 || timeoutMillis < 0) {
			T.argsError(asUrl, aiRetry, timeoutMillis);
		}

		URL url = new URL(asUrl);
		Document doc = null;
		try {
			doc = Jsoup.parse(url, timeoutMillis);
		} catch(SocketException e){
			T.print(url);
		}
		catch (SocketTimeoutException e) {
			if (aiRetry > 1) {
				doc = jsoupDoc(asUrl, aiRetry - 1, timeoutMillis+5100);
			} else {
				throw e;
			}
		}
		return doc;
	}

	
	public static Document jsoupDoc(String asUrl) {
		URL url=null;
		try {
			url = new URL(asUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		Document doc=null;
		try {
			doc=Jsoup.parse(url, 1500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static String html(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
