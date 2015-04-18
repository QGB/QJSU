package qgb.project;

import qgb.*;
import qgb.text.Regex;
import qgb.time.Convert;

public class QQ_album {
	
	public static void main(String[] args) throws Exception  {	
		U.print(get_albums_info());
		}
	
	public static String get_albums_info() throws Exception  {
		String st_html="";
		String[] sty_re={"(?<=\"name\" : \").*(?=\",\n)",//album name
				"(?<=\"createtime\" : )\\d*(?=,\n)",//createtime
				"(?<=\"lastuploadtime\" : )\\d*(?=,\n)",//lastuploadtime
				"(?<=\"modifytime\" : )\\d*(?=,\n)"};//modifytime
		st_html=U.readSt("album.txt");
		String[][] sty_r=new String[ sty_re.length][];
		
		for (int i = 0; i < sty_re.length; i++) {
				 sty_r[i]= Regex.getYst(sty_re[i],st_html);
				// U.print(sty_r[i]);
				}
		if (sty_r[0].length!=sty_r[1].length || sty_r[1].length!=sty_r[2].length ) {
			U.notify("error");
			return "";
		}
		
		String str="";
		for (int i = 0; i < sty_r[0].length; i++) {
			str = str + T.repeat(10, "-") + (i+1) + T.repeat(10, "-")+ "\n" + sty_r[0][i] 
					+ "\n" + Convert.toString(Integer.valueOf(sty_r[1][i]))
					+ "\n" + Convert.toString(Integer.valueOf(sty_r[2][i])) 
					+ "\n" + Convert.toString(Integer.valueOf(sty_r[3][i])) + "\n";
		}
		//U.print(str);
		return str;
	}
	
	
}
