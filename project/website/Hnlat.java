package qgb.project.website;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import qgb.CharsetName;
import qgb.U;
import qgb.Y;
import qgb.net.QNet;
import qgb.os.win.Win;

public class Hnlat {
	static final String gsd = "http://172.17.5.48/";

	public static void main(String[] args) {
		String[] ysp = U.readSt("f1.txt").split("\n");
		ArrayList<String> al200=new ArrayList<String>(),
				al404=new ArrayList<String>(),alError=new ArrayList<String>();
		
		for (int i = 0; i < ysp.length; i++) {
			if (!ysp[i].contains(".")) {
				continue;
			}
			// U.print(ysp[i]);
			try {
				con(gsd + ysp[i]);
				al200.add(ysp[i]);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				al404.add(ysp[i]);
			} catch (Exception e) {
				alError.add(ysp[i]);
				//e.printStackTrace();
			}
			U.sleep(77);
		}
		
		U.write("200.txt", Y.ArrayToStr(al200.toArray(), "\n"));
		U.write("404.txt", Y.ArrayToStr(al404.toArray(), "\n"));
		U.write("error.txt", Y.ArrayToStr(alError.toArray(), "\n"));
	}

	public static void con(String path) throws FileNotFoundException, Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream is = conn.getInputStream();// 通过输入流获取html数据
		is.close();
	}

}
