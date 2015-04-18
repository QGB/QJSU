package qgb.project.website;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import qgb.*;
import qgb.net.Get;

//http://www.acfun.tv/a/ac1822713
public class AcFun {

	public static void main(String[] args) {
		final int imin=0;//12058;
		int inum=0;
		BufferedInputStream bis = null;
		
		String st_name="";
		
		for (int i = 0; i < 1; i++) {//5479
			try {
				bis=Get.urlfile("http://www.acfun.tv/a/ac"+i);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (bis==null) {
				U.print(inum+" error!");
				continue;
			}
			//st_name=get_name(inum);
			//U.write("pdf/"+st_name, bis);
			U.print(st_name);
		}
		
		
	}

}
