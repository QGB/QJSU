package qgb.project.tool;

import qgb.*;


public class AddPackageDeclation {
	static String gstPath="D:/java/eclipse/TIJ4/src/";
	
	public static void main(String[] args) {
		U.print(AddPackageDeclation.class.getName());
		String[] yst=F.getFilesStringArray(gstPath, ".java");
		String sta="",stb="";
		int ia=0;
		for (int i = 0; i < yst.length; i++) {
			sta=U.readSt(yst[i]);
			
			ia=yst[i].lastIndexOf("\\");
			stb=yst[i].substring(gstPath.length(),ia);
			stb= stb.replace("\\",".");
			stb="package "+stb+";";
			
			if (sta.contains(stb)) {
				U.print("!!!skip [%d]%s\n",i,yst[i]);
			}else {
				sta=stb+"\n"+sta;
				U.delFile(yst[i]);
				//U.msgbox(sta,yst[i]);
				
				U.write(yst[i], sta);
				U.print("[%d]%s\n",i,yst[i]);
			}
		}
		
	}

	
	
}
