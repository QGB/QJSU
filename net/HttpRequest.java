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
		// ���� GET ����
		InputStream is = null;
		is = HttpRequest.get(str);

		String s = T.InputStreamToString(is, CharsetName.GST_UTF8);
		System.out.println(s);

		// ���� POST ����
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
			System.out.println("����GET��������쳣��");
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
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			// Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// ���� BufferedReader����������ȡURL����Ӧ
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
			System.out.println("����GET��������쳣��");
			T.error(e);
		}
		// ʹ��finally�����ر�������
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
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	public static String get(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
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
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 */
	public static String post(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
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