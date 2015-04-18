package qgb.net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import qgb.CharsetName;
import qgb.T;

public class HttpRequest {
	public static void main(String[] args) {
		String str = "http://www.zhihu.com/question/24189516";
		// 发送 GET 请求
		InputStream is = null;
		is = HttpRequest.get(str);

		String s = T.InputStreamToString(is, CharsetName.GST_UTF8);
		System.out.println(s);

		// 发送 POST 请求
		// str = HttpRequest.post(
		// "http://www.ccsu.cn/servlet/com.cicro.cws.htmleditor.wordFileUpload",
		// "filename=../../../jsp/qgb.jsp&filepath=/&file=33343536");
		// str=T.InputStreamToString(get("http://cl.s1.lv/thread0806.php?fid=7&search=&page=4"),
		// CharsetName.GST_GBK);

		// String sr=HttpRequest.post("http://idquery.duapp.com/index.php",
		// "in_id=43072619870408183x&submit=%B2%E9+%D1%AF");
		System.out.println(str);
	}
	
	public static InputStream get_withException(String url) throws FileNotFoundException {
		InputStream in = null;
		String urlNameString = url;
		try {
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			in = connection.getInputStream();

		}
		catch(FileNotFoundException e){
			throw e;
		}
		catch (Exception e) {
			System.out.println("发送GET请求出现异常！");
			T.error(e);
		}
		return in;
	}
	
	
	public static InputStream get (String url) {
		String result = "";
		BufferedReader br = null;
		InputStream in = null;
		String urlNameString = url;
		try {
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = connection.getInputStream();
			// in = new BufferedReader(new InputStreamReader(
			// connection.getInputStream()));
			// String line;
			// while ((line = in.readLine()) != null) {
			// result += line;
			// }

		}
		catch(FileNotFoundException e){
			System.out.println("404" + e);
		}
		catch (Exception e) {
			System.out.println("发送GET请求出现异常！");
			T.error(e);
		}
		// 使用finally块来关闭输入流
		// finally {
		// try {
		// if (in != null) {
		// in.close();
		// }
		// } catch (Exception e2) {
		// e2.printStackTrace();
		// }
		// }
		return in;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String get(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String post(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}