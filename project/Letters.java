package qgb.project;
import static qgb.U.*;
import qgb.*;
public class Letters {

	public static void main(String[] args) {
		U.print();
		letters('D');
	}

	static void letters(char ac){
		if(ac<'A'||ac>'Z'){print("error");return;}
		String s="";
		for (char i = 'A'; i <= ac; i++) {
			s=s+i+s;
		}
		print(s);
	}
	
	
	static void let(char ac){
		int id=ac-'A'+1;
		char[] yc=new char[2^id-1];
		for (int i = 0; i < id; i++) {
		}
	}
}
