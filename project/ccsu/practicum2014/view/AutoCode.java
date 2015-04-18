package qgb.project.ccsu.practicum2014.view;

import qgb.*;

public class AutoCode {
	public static void main(String[] args) {
		String st=U.getSource(Main.class);
		//U.print(st);
		int is=0,ie=0;
		String sa="actionPerformed(ActionEvent e) {";
		String sb="",sba = "";
		while(is!=-1){
			is=st.indexOf(sa,is+sba.length())+sa.length();
			if (is==31) {
				break;
			}
			ie=st.indexOf("}",is+1);
			sb=st.substring(is,ie);
			sba="\r\n				"+sb.trim()+
					"\r\n				loadData();"
					+ "\r\n			";
			st= st.replace(sb,sba);
			//U.msgbox(sb);
			//U.print(st);
			//U.msgbox();
//			if (st.length()>9999) {
//				U.msgbox(sb);
//			}
		}
		U.write("main.txt", st);
	}
}
