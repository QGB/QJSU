package qgb.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import qgb.*;

public class QNet {
	public static String html(String path) throws MalformedURLException, ProtocolException  {
		URL url = new URL(path);
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection)url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream is;
		try {
			is = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}//通过输入流获取html数据
		return U.InputStreamToString(is, CharsetName.GST_DEF);//得到html的二进制数据
	}

	public static void main(String[] args) {
	}

}
