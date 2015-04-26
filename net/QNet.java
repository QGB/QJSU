package qgb.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import qgb.*;

public class QNet {
	public static String html(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream is = conn.getInputStream();//通过输入流获取html数据
		return U.InputStreamToString(is, CharsetName.GST_DEF);//得到html的二进制数据
	}

	public static void main(String[] args) {
	}

}
