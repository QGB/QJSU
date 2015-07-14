package qgb.project;

import qgb.U;

public class BreakLine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String st="";
		char[] s1=U.readSt("1.txt").toCharArray(),s2=U.readSt("2.txt").toCharArray();
		
		for (int i = 0; i < s1.length; i++) {
			st=st+s1[i]+"\n";
		}
		U.write("1n.txt",st);
		st="";
		
		for (int i = 0; i < s2.length; i++) {
			st=st+s2[i]+"\n";
		}
		U.write("2n.txt",st);
		//U.print(Decipher("Mn,Jc%A\\y8Pa"));
		U.exit();
	}

}
