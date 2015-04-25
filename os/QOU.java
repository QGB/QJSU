package qgb.os;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import qgb.*;

import javax.swing.JOptionPane;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public class QOU {
	public static void main(String[] args) {
		String st = U.getSource(A_Sample.class);
		st=U.readSt("1.java");
		//U.sleep(5333);
		Class<?> c=U.compileJavaCode(st);
		U.print(c);
		
		// U.browser("http://qq.com",999);
		if (true)
			return;
		// U.exit();
		U.print("%s", 1);
		// getSource(aClass)
		// U.print(min(1, 23,4 ,87,32,324,234,2,324,423434,0,-4,5));
		U.beginKeepTime();

		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
			// min(1, 2);
			// min(1, 2, 3);
			// min(1, 23,4 ,87,32,324,234,2,324,423434,0,-4,5);
		}
		U.endKeepTime(true);
		// U.write("t.bin", "ast_text", CharsetName.GST_GBK);
		// getSource(U.class);
		// print("java.awt.EventDispatchThread.pumpEvents(java.awt.event.ActionEvent[ACTION_PERFORMED,cmd=,when=14".length());
	}

	// TODO
	/**@Tested**/
	public static Object callStaticMethod(Method am, Object... args) {
		try {
			 return am.invoke(null,args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			try {
				return am.invoke(null, new Object[] { args });
			} catch (IllegalAccessException|InvocationTargetException e1) {
				e1.printStackTrace();
			}catch (IllegalArgumentException e2) {
				U.error(e2, "!!!am="+am.toString()+",args="+args);
			}
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Class<?> compileJavaCode(String asJavaCode) {
		if (asJavaCode == null || asJavaCode.length() < 9) {
			return null;
		}
		asJavaCode=asJavaCode.trim();

		String stPubClass = getPublicClassName(asJavaCode);
		if(stPubClass.length()<1)return null;
		/****compile****/
		JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjf = complier.getStandardFileManager(null,
				null, null);
		U.write(stPubClass+".java",asJavaCode);
		Iterable it = sjf.getJavaFileObjects(U.autoPath(stPubClass+".java"));
		CompilationTask task = complier
				.getTask(null, sjf, null, null, null, it);
		task.call(); // Create .class file
		try {sjf.close();} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		U.delFile(stPubClass+".java");
		/****compile success!***/
		String stClassPath=U.gstTestPath,stClassName=stPubClass;
		if (asJavaCode.startsWith("package ")) {
			InputStream isC;
			try {
				isC = U.readIs(U.autoPath(stPubClass+".class"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			
			stClassName=T.sub(asJavaCode, "package ", ";").trim()+".";
			stClassPath=U.getClassBase()+stClassName.replace(".","/");
			U.write(stClassPath+stPubClass+".class", isC);
			U.delFile(stPubClass+".class");
			stClassPath=U.getClassBase();
			stClassName=stClassName+stPubClass;
		}
		/*****load ; Not del .class file!****/
		try {
			URL urls[] = new URL[] { new URL("file:"+stClassPath) }; // .class path
			//U.print(urls);
			URLClassLoader uLoad = new URLClassLoader(urls);
			//U.print(uLoad.getURLs());
			return  uLoad.loadClass(stClassName);
		} catch (MalformedURLException | ClassNotFoundException|NoClassDefFoundError e) {
			System.err.print(stClassPath+"|   ");
			e.printStackTrace();
			return null;
		}
	}

}
