package qgb.project.hack;


import qgb.*;


public class PassWord {
	static String[] gyst36=new String[36];
	
	
	public static void main(String[] args) {
		U.print(PassWord.class);
		//create1();
		//create4();
		
		//U.exit();
		
		delMuti();
		//order();
		//createDigital();
		//mixUp();
	}

	private static void order() {
		String stname="p";
		String[] yst= U.readSt(stname+".txt").split("\r\n");
		U.print(yst.length);
		yst= Y.delMuti(yst);
		U.print(yst.length);
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < yst.length; i++) {
			sb.append(yst[i]+"\r\n");
		}
		U.write("delMuti-"+stname+".txt", sb.toString());
		
	}

	private static void delMuti() {
		String stname="p";
		String[] yst= U.readSt(stname+".txt").split("\r\n");
		U.print(yst.length);
		yst= Y.delMuti(yst);
		U.print(yst.length);
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < yst.length; i++) {
			sb.append(yst[i]+"\r\n");
		}
		U.write("delMuti-"+stname+".txt", sb.toString());
	}

	private static void create1() {
		String stn="0123456789",sta="abcdefghijklmnopqrstuvwxyz"
				,stb="`~!@#$%^&*()-_=+[{]};:'";
		String str="";
		str=stn+sta;
		for (int i = 0; i < gyst36.length; i++) {
			gyst36[i]=str.substring(i, i+1);
		}
		//U.print(stb.length());
		str="";
		stb=stn+sta+sta.toUpperCase()+stb;
		U.print(stb.length());
		
		for (int i = 0; i < stb.length(); i++) {
			str=str + stb.substring(i, i+1)+"\r\n";
		}
		
		
		U.write("1p.txt", str);
		//U.print(gyst36);
		//U.print(sta.length());
		
		
	}

	private static void create4() {
		StringBuilder sbd=new StringBuilder();
		
		for (int i1 = 0; i1 < gyst36.length; i1++) {
			for (int i2 = 0; i2 < gyst36.length; i2++) {
				//for (int i3 = 0; i3 < gyst36.length; i3++) {
					//for (int i4 = 0; i4 < gyst36.length; i4++) {
						sbd.append(gyst36[i1]+gyst36[i2]+"\r\n");
						
						
					//}
				//}
			}
		}
		
		U.write("2p.txt", sbd.toString());
		
	}

	private static void mixUp() {
		String sta="",stb="";
		sta= U.readSt("p.txt");
		stb=U.readSt("7d.txt");
		//Y.StrToArray();
	
	}

	private static void createDigital() {
		StringBuffer sb =new StringBuffer();
		for (int i = 0; i < 1234567; i++) {
			sb.append(i+"\n");
		}
		U.write("7d.txt", sb.toString());
	}
	
	
	
}
