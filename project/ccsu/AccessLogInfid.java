package qgb.project.ccsu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import qgb.T;
import qgb.U;

public class AccessLogInfid {
	public static void main(String[] args) throws Exception {
		U.print(U.getCmdToRun());
		//main(null);
		
		 FileReader fr=new FileReader(U.autoPath("j.txt"));
	        BufferedReader br=new BufferedReader(fr);
	        String sl="",str="";
	        ArrayList<String> als=new ArrayList<>();
	        
	        while ((sl=br.readLine())!=null) {
	        	sl=T.sub(sl, "d="," ");
	        	if (!als.contains(sl)) {
					
					try {
						Integer.valueOf(sl);
					} catch (Exception e) {
						continue;
					}
					als.add(sl);
					str=str+sl+"\n";
				}
	        	//U.msgbox(sl);
	        }
	        br.close();
	        fr.close();
	        U.print(als);
	       // Y.ArrayToStr(O, ast_separator)
	        U.write("r.txt",str);
	}

}
