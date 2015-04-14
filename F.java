package qgb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import qgb.*;
import qgb.file.FileTypeFilter;

public class F {
	public static void main(String[] args) throws IOException {
		//InputStream is = U.read_bis("c.jsp");
//		String str = "";
//		str = ToHex(is);
//		U.write("c.txt", str);
		//U.print(Integer.valueOf());
//		byte[] yb=hexToBytes("3031323334353637");
//		U.print(new String(yb));
//		U.print(toHexString(yb));
		U.print(getFilesStringArray("d:/test/", ".txt"));
	}
	//////////////////////////////////////////////
	/**
	 *Example1: getFilesStringArray("d:/",".txt");</p>
	 *Example2: getFilesStringArray("d:",".txt");</p>
	 * 
	 * @see java.nio.charset.Charset
	 * @see qgb.T#write(String, InputStream)
	 * @see java.lang.String#getBytes()
	 */
	public static String[] getFilesStringArray(String filePath, String astFileType){
		Object[] yo=getFilesStringList(filePath, astFileType).toArray();
		String[] yst=new String[yo.length];
		for (int i = 0; i < yst.length; i++) {
			yst[i]=yo[i].toString().replace("\\", "/");
		}
		return yst;
	}
	
	public static ArrayList<String> getFilesStringList(String filePath, String astFileType) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		ArrayList<String> filelist = new ArrayList<String>();
		String sta="";
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				filelist.addAll(getFilesStringList(file.getAbsolutePath()
						, astFileType));
			} else {
				sta=file.getAbsolutePath();
				if (FileTypeFilter.accept(sta, astFileType)) {
					filelist.add(sta);
				}
			}
		}
		return filelist;
	}

	// /////////////////////////////////////////////
	public static byte[] hexToBytes(String ast) {
		if (ast.length()%2!=0) {
			U.notify("illegal input! |ast.length()="+ast.length());
			return null;
		}
		byte[] yb =new byte[ast.length()/2];
		String str;
		int ia=0;
		for (int i = 0; i < ast.length()/2; i++) {
			str=ast.substring(i*2,i*2+2);
			ia=Integer.valueOf(str, 16);
			yb[i]=(byte)ia;
		}
		return yb;
	}
	public static String toHexString(InputStream ais) {
		return toHexString(U.InputStreamToBytes(ais));
	}

	public static String toHexString(byte[] ayb) {
		StringBuilder sb=new StringBuilder();

		for (int i = 0; i < ayb.length; i++) {
			Byte b = ayb[i];
			sb.append(Integer.toString(b.intValue(), 16));
		}
		return sb.toString().toUpperCase();
	}
	
	public static String name(String ast) {
		ast = ast.replaceAll("[\\\\\\/\\:\\*\\?\\\"\\<\\>\\|]", "");
		ast = ast.substring(0, Math.min(ast.length(), 200));
		while (ast.startsWith(".")) {
			ast=ast.substring(1, ast.length());
		}
		//U.print(ast);
		return ast;
	}

	/**不能使用File.isDirectory()判断一个不存在的文件是否为目录**/
	public static boolean isDirectory(String asPath) {
		if (asPath.endsWith("/")||asPath.endsWith("\\")) {
			return true;
		}
		return false;
	}
	public static boolean isExist(String astF) {
		astF=U.autoPath(astF);
		return new File(astF).exists();
	}
	public static void rename(String sFP, String sFNOld, String sFNNew) {
		rename(sFP+sFNOld, sFP+sFNNew);
	}

	public static void rename(String sFOld, String sFNew) {
		File f=new File(sFOld);
		if (!f.renameTo(new File(sFNew))) {
			U.notify(sFOld+"\n"+sFNew);
		}
	}
}
