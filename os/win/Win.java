package qgb.os.win;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import qgb.*;

public final class Win {
	/**
	 * @param args
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws Exception {
		//argsTest(new String[]{});
		//U.print(new String[]{}.getClass());
		//U.print(new Object[]{new String[]{}}.getClass());
		//Class<?> cs=A_Sample.class;
		JavaCompiler complier = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjf = complier.getStandardFileManager(null,
				null, null);
		Iterable it = sjf.getJavaFileObjects(U.autoPath("J.java"));
		CompilationTask task = complier
				.getTask(null, sjf, null, null, null, it);
		task.call(); // 调用创建
		sjf.close();
		//U.exit();
		URL urls[] = new URL[] { new URL("file:"+U.gstTestPath) }; // 储存文件目录的地址
		//U.print(urls);
		URLClassLoader uLoad = new URLClassLoader(urls); // classloader从哪个目录找？
		U.print(uLoad.getURLs());
		Class c = uLoad.loadClass("J"); // 找哪个class文件 注意不带后缀名
		
		Method[] ym=c.getDeclaredMethods();
		String[] ys=Y.ObjectsToYStr(ym);
		for (int i = 0; i < ym.length; i++) {
			ys[i]=T.sub(ys[i],"J.","(");
			U.print(ys[i]+"------------------");
			switch (ys[i]) {
			case "main":
				U.callStaticMethod(ym[i],new String[]{"11","222"});
				break;
			case "msgbox":
				U.callStaticMethod(ym[i],"msg");
				break;
			case "print":
				U.callStaticMethod(ym[i],"p[%s]p%sp",new String[]{"11","222"});
				break;				
			default:
				break;
			}
		}
		
//		U.print(m.invoke(null,new Object[]{new String[]{"11","222"}}
//		));
		// 创建一个实例
	}

	private static void argsTest(Object ... ayo ) {
		U.print(ayo.getClass());
	}

	public static String msgbox(Object message, Object initialSelectionValue) {
		return JOptionPane.showInputDialog(message, initialSelectionValue);
	}
	
	
}
