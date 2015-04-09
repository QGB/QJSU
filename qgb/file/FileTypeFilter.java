package qgb.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import qgb.T;


public class FileTypeFilter implements FilenameFilter{  
	String st_end;

	public FileTypeFilter(String str){
		st_end="."+str.toLowerCase();// "." determines whether filenames have an extension
		}

	
	@Override
	public  boolean accept(File dir, String name) {
		return name.toLowerCase().endsWith(st_end);
	}
	
	public static  boolean accept( String astf,String astFileType) {
		return astf.toLowerCase().endsWith(astFileType.toLowerCase());
	}
	
	
	public static void main(String [] args) throws IOException {  
		// bulk add package statement 
		System.exit(0);
    	String st_path="D:/jswing2/ch";
    	String stn="",sta="";
    	String[] sty;
    	for (int i = 0; i < 27; i++) {
    		stn= String.format("%02d", i+2);
    		File fa =new File(st_path+stn+"/");
    		sty=fa.list(new FileTypeFilter("java"));
    		
			for (int j = 0; j < sty.length; j++) {
				sta ="package ch"+stn+";\n"+T.read_st(st_path + stn + "/" + sty[j]);
				//
				//System.exit(0);
				//T.write(st_path + stn + "/" + sty[j],sta,false);
				//T.print("---"+sty[j]);
			}
    		
    		T.print(stn);
    		//break;
		}
    	//
    }

    
}
    