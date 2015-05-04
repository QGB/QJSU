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
		U.print(msgbox("",null));
		//U.print();
	}

	public static String msgbox(Object message, Object initialSelectionValue) {
		String str=JOptionPane.showInputDialog(message, initialSelectionValue);
		if (str==null) {
			U.exit();
		}
		return str;
	}
	
	
}
