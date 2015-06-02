package qgb.project.ccsu.log;

import java.io.PrintStream;

import qgb.T;
import qgb.U;
import qgb.net.QNet;
//
public class Infid {
	static String gsUrl="http://www.ccsu.cn"+"/servlet/com.cicro.cws.ware.NewsCountManager?type=get&infid=";
	public static void main(String[] args) throws Exception {
		U.print(U.getCmdToRun());
		//main(null);
		PrintStream ps=new PrintStream(U.autoPath("res.txt"));
		String[] ys=U.readSt("infid.txt").split("\n");
		U.print(ys);
		for (int i = 0; i < ys.length; i++) {
			U.print(i);
			String str=QNet.html(gsUrl+ys[i]);
		str=T.sub(str, "ge>","</");
		if(str.length()<1)
			continue;
		ps.println(str);
			//U.msgbox(str);
		}
	}

}
