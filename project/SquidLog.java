package qgb.project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import qgb.*;


import qgb.text.Regex;

public class SquidLog {

	public static void main(String[] args) {
		//U.print("1427400838.289   1320 1.161.25.61 TCP_MISS/200".length());
		
		if(args.length!=1){
			String scmd=U.getCmdToRun();
			U.print(scmd);
			U.print(scmd+ " FileName");
			
			final String sFP="G:\\v\\access.log";
			if (F.isExist(sFP )) {
				args=new String[1];
				args[0]=sFP ;
			}else {
				U.exit();
			}
		}
		
		FileReader fr;
		String sline;
		TreeSet<String> hs=new TreeSet<String>();
		int in=0;
		try {
			fr = new FileReader(args[0]);
			BufferedReader bufferedreader = new BufferedReader(fr);
			while ((sline = bufferedreader.readLine()) != null) {
				if (0 != sline.length()) {
					sline=sline.substring(0, 46);
					sline=Regex.getFirst(Regex.GS_IP, sline);
					//Regex.GS_URL.charAt(0);
					//System.out.println(sline);
					//U.msgbox(sline);
					hs.add(sline);
					
//					in++;
//					if (in>42250) {
//						U.print("i:"+ in);
//						//U.exit();
//					}
				}
			}
			fr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		U.print(hs);
	}

}
