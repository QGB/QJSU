
package qgb.project.ccsu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


import qgb.*;

/**
 * @author Administrator
 * 
 */
public class Cet {

	public static void main(String[] args) throws IOException {
		U.gstTestPath="G:/cet/09xk/";
		U.write("start", U.BytesToInputStream("test".getBytes()));
		DownLoad_all();
	}

	private static void DownLoad_all() {
		String stin="",st_url="",stpath="";
		InputStream is;
		stpath="G:/cet/09xk/09xk.txt";
		stin =U.readSt(stpath);
		if (null==stin) {return;} 
		//U.print(stin);
		String[] yst=  stin.split("\n");

		
		for (int i = 0; i < yst.length; i++) {
			yst[i]=yst[i].replaceAll("\\W","");
			st_url="http://shekao.hneao.cn/cet_pic/43025/"
					+ yst[i].substring(0, 2)
					+ "/" + yst[i].substring(2, 6) 
					+ "/" + yst[i]
					+ ".jpg";
			//U.print(st_url);
			try {
				
				is = urlfile(st_url);
				if (is == null) {
					System.out.println(yst[i] + " null");
					continue;
				}
				U.write(yst[i] + ".jpg", is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	}


	public static BufferedInputStream urlfile(String ast_url)
			throws IOException {
		int HttpResult = 0; // ���������ص�״̬
		URL url = new URL(ast_url); // ����URL
		URLConnection urlconn = url.openConnection(); // ��ͼ���Ӳ�ȡ�÷���״̬��urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		try {
			HttpResult = httpconn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("HTTP Status-Code:"+HttpResult);

		if (HttpResult != HttpURLConnection.HTTP_OK) { // ������HTTP_OK˵�����Ӳ��ɹ�
			// System.out.print("fail");
			System.out.println("HttpResult:" + HttpResult);
			return null;
		} else {
			int filesize = urlconn.getContentLength(); // ȡ��ݳ���

			System.out.println("qgbt_filesize:" + filesize);

			BufferedInputStream bis = new BufferedInputStream(
					urlconn.getInputStream());
			return bis;
			/*
			 * BufferedOutputStream bos=new BufferedOutputStream(new
			 * FileOutputStream(target)); byte[] buffer = new byte[1024];
			 * //��������������Ļ��� int num = -1; //������ֽ��� while (true) { num =
			 * bis.read(buffer); // ���뵽������ if (num ==-1){ bos.flush(); break;
			 * //�Ѿ����� } bos.flush(); bos.write(buffer,0,num); } bos.close();
			 * bis.close();
			 */
		}// else end

	}

	

}
