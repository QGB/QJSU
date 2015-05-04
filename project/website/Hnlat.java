package qgb.project.website;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
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
	static final String gsd = "http://mechanics.xd.lib.hnlat.com/";//"http://172.17.5.48/";

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String[] ysp = U.readSt("200.txt").split("\n");
//		ArrayList<String> al200=new ArrayList<String>(),
//				al404=new ArrayList<String>(),alError=new ArrayList<String>();
		U.print(ysp);
		U.gstTestPath=U.gstTestPath+"xd/";
		U.setErrStream("error.txt");
		PrintStream ps200 = new PrintStream(U.autoPath("200.txt"),
				CharsetName.GST_UTF8);
		PrintStream ps404 = new PrintStream(U.autoPath("404.txt"),
				CharsetName.GST_UTF8);
		for (int i = 0; i < ysp.length; i++) {
			if (!ysp[i].contains(".")) {
				continue;
			}
			// U.print(ysp[i]);
			ysp[i]=gsd + ysp[i];
			try {
				con(ysp[i]);
				ps200.println(ysp[i]);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				ps404.println(ysp[i]);
			} catch (Exception e) {
				System.err.println(ysp[i]);
				e.printStackTrace();
				
			}
			U.sleep(111);
			U.print("[%s]%s",i,ysp[i]);
			if (i%222==0) {
				System.err.flush();
				ps200.flush();
				ps404.flush();
			}
		}
		
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
