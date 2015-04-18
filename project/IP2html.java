package qgb.project;

import qgb.*;


public class IP2html {


	public static void main(String[] args) {
		if(args.length!=1){
			U.print(U.getCurrentClass().getName()+ " FileName");
			U.exit();
		}
		String stin=U.readSt("./"+args[0]).replaceAll(" ","");
		//U.write(s, ayb);
		U.print(stin);
		String[] yst;
		if(stin.contains(T.gsWinNewline)){
			yst=stin.split(T.gsWinNewline);
		}else {
			yst=stin.split("\n");
		}
		for (int i = 0; i < yst.length; i++) {
			
		}
	}
}
