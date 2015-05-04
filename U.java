package qgb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Set;


import qgb.os.win.Win;
import qgb.text.Regex;

/** QGB's java basic utils **/
public final class U {
	public static String gstTestPath = "";
	public final static String gstEclipseA = "._abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String gsOneLineTryCatch = "try {prep.close();} catch (SQLException e) {e.printStackTrace();}";
	static {
		if (OS.isWin()) {
			gstTestPath = "D:/test/";
		} else if (OS.isAndroid()) {
			gstTestPath = "/sdcard/test/";
		}
	}

	/****/
	private U() {
		throw new Error("Don't let anyone instantiate this class!");
	}

	// /////////////////////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		U.print(U.getCmdToRun());
		//main(null);
	}
	


	public static String getPublicClassName(String asJavaCode) {
		String st = Regex.getFirst(Regex.GS_PubClassName, asJavaCode);
		// st=T.replaceAll(st, "  ", " ");
		int i = st.lastIndexOf("class") + 5;
		// U.print(i);
		if (i < 1)
			return "";
		st = st.substring(i, st.length());
		return st.trim();
	}

	public static void runJavaCode(String ast,Object... args) {
		
	}

	public static String getClassFolder(Class ac) {
		String st = ac.getName().replace(".", "/");
		String stpath = ac.getClassLoader().getResource("").getPath();
		int i = st.lastIndexOf("/");
		if (i < 0) {
			return stpath;
		}
		return stpath + st.substring(0, i + 1);
	}

	public static String getClassPath(Class ac) {
		return getClassFolder(ac) + ac.getSimpleName() + ".class";
	}

	// ///////////////////////////////////////////////////////
	private static long glTimer = -1;

	/**
	 * unit: ms</br> return true:Success! if the method is called firstly or you
	 * had been call endKeepTime() recently.</br> return false:Fail!.
	 * **/
	public static boolean beginKeepTime() {
		if (glTimer < 0) {
			glTimer = System.currentTimeMillis();
			return true;
		}
		return false;

	}

	public static long endKeepTime() {
		long lr = System.currentTimeMillis() - glTimer;
		glTimer = -1;
		return lr;

	}

	public static long endKeepTime(boolean abPrint) {
		long lr = System.currentTimeMillis() - glTimer;
		glTimer = -1;
		if (abPrint) {
			print("Taking %s ms|%s", lr, getCurrentMethod().getName());
		}
		return lr;
	}

	/**
	 * Current class fixed at 2014-09-24 19:36:50
	 **/
	public static Class<?> getCurrentClass() {
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length < 2) {
			return null;
		}
		/** getCurrentClass **/
		String str = "";
		for (int i = 0; i < yste.length; i++) {
			if (yste[i].getMethodName().equals("getCurrentClass")) {
				try {
					return Class.forName(yste[i + 1].getClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	// public static String getCurrentClassName() {
	// return getCurrentClass().getName();
	// }
	/** TODO:閻偄鍤栭懗銈忔嫹 */
	public static Method getCurrentMethod() {
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length < 2) {
			return null;
		}
		/** getMethodName **/
		String str = "";
		for (int i = 0; i < yste.length; i++) {
			if (yste[i].getMethodName().equals("getCurrentMethod")) {
				Class<?> cC = null;
				try {
					cC = Class.forName(yste[i + 1].getClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				Method[] ym = cC.getMethods();
				str = yste[i + 1].toString();
				str = str.substring(0, str.lastIndexOf('('));
				// try {
				// U.print(cC.getMethod(str, null));
				// } catch (Exception e) { e.printStackTrace(); }
				// U.print(str);
				for (int j = 0; j < ym.length; j++) {
					if (str.endsWith(ym[j].getName())) {
						return ym[j];
						// U.msgbox(str);
					}
					// U.print(ym[j].getName());
					// U.print(ym[i].);
				}
			}
		}
		return null;
	}

	/**
	 * Thread safe</p>
	 * **/
	public static synchronized String argsError(Object... args)
			throws IllegalArgumentException {
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length < 2) {
			return null;
		}
		/** 鐠囨瑱鎷�*/
		String str = yste[yste.length - 1 - 1].toString();
		// print(yste);
		StringBuilder sb = new StringBuilder("\n");
		sb.append(str.substring(0, str.lastIndexOf('(') + 1));
		int im = args.length;
		if (im > 0) {
			for (int i = 0; i < im - 1; i++) {
				sb.append(args[i] + ",");
			}
			sb.append(args[im - 1]);
		}

		sb.append(")|"
				+ str.substring(str.lastIndexOf('(') + 1, str.lastIndexOf(')')));
		// U.write("ste.txt", str);
		// U.print();
		throw new IllegalArgumentException(T.lineWrap(sb, 76));
	}

	// //////////////////////////////////////////////////////
	public static void sleep(long alms) {
		try {
			java.lang.Thread.sleep(alms);
		} catch (InterruptedException e) {
			error(e, "U.sleep(" + alms + ") Interrupted!");
		}

	}

	// /////////////////////////////////////////////////////
	public static void printProperties() {
		Properties pes = System.getProperties();
		Set<String> set = pes.stringPropertyNames();
		for (String str : set) {
			System.out.printf("%s=%s\n", T.pad(str, 29, " "),
					pes.getProperty(str));
		}
	}

	public static void printEclipse() {
		print("Max console line char length=96");
	}

	// /////////////////////////////////////////////////////////
	public static String getCurrentThreadName() {
		return Thread.currentThread().getName();
	}

	public static void thread(int ai) {
		U.print("%d:%s", ai, Thread.currentThread().getName());
	}

	// ///////////////////////////////////////////////////////
	public static String time() {
		return qgb.time.TimeConvert.toString(System.currentTimeMillis());
	}

	// ///////////////////////////////////////////////////////
	public static Process run(String ast) {
		Runtime rt = Runtime.getRuntime();
		try {
			return rt.exec(ast);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// /////////////////////////////////////////////////////////
	public static String msgbox(Object message, Object initialSelectionValue) {
		if (OS.isWin()) {
			return Win.msgbox(message, initialSelectionValue);
		}
		return "";
		
	}

	/**
	 * Shows a question-message dialog requesting input from the user. The
	 * dialog uses the default frame, which usually means it is centered on the
	 * screen.
	 * 
	 * @param message
	 *            the Object to display
	 * @return the <code>String</code> property
	 * @see java.awt.GraphicsEnvironment.isHeadless
	 */
	public static String msgbox(Object message) {
		return msgbox(message, message);
	}

	public static String msgbox(String format, Object... args) {
		return msgbox(T.format(format, args));
	}

	public static void msgbox() {
		msgbox("pause","pause");
	}

	// /////////////////////////////////
	public static void notify(String ast_text) {
		Throwable taa = new Throwable();
		StackTraceElement[] yste = taa.getStackTrace();
		for (int i = 0; i < yste.length - 1; i++) {
			U.print((i + 1) + ":" + yste[yste.length - 1 - i]);
		}
		U.print(ast_text + "|" + getCurrentTime());
	}

	// ////////////////////////////////////////////////////////
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 閼哄倿娼诲锟�
		// System.out.println(df.format(new Date()));// new
		// Date()娑撳搫褰囬崜宥囬兇缂佺喐妞�
		return df.format(new Date());
	}

	// ///////// Print /////////////////////////////
	public static boolean printInnerSt(Object ae) {
		Field[] yf = ae.getClass().getDeclaredFields();
		boolean bool = false;
		for (int i = 0; i < yf.length; i++) {
			// U.msgbox(yf[i].toString());

			yf[i].setAccessible(true);

			if (String.class == yf[i].getType()) {
				String sta = null;
				try {
					sta = (String) yf[i].get(ae);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				if (sta != null) {
					U.print("%s=\"%s\"", "o." + yf[i].getName(), sta);
					bool = true;
				}
			}
		}
		return bool;
	}

	public static boolean printDetaills(Object ae, String astName) {
		Field[] yf = ae.getClass().getDeclaredFields();
		boolean bool = false;
		for (int i = 0; i < yf.length; i++) {
			// U.msgbox(yf[i].toString());

			yf[i].setAccessible(true);

			Object object = null;
			try {
				object = yf[i].get(ae);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				continue;
			}
			if (object == null) {
				continue;
			}
			if (String.class == yf[i].getType()) {
				String sta = null;
				try {
					sta = (String) yf[i].get(ae);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				if (sta != null) {
					U.print("%s=\"%s\"", astName + "." + yf[i].getName(), sta);
					bool = true;
				}
				/** TODO 閺冪姵纭舵潏鎾冲毉null閸婏拷**/
				// else {
				// U.print("%s=null", astName + "." + yf[i].getName());
				// }
				continue;
			} else {
				// if (Entry.class == yf[i].getType()) {
				// continue;
				// }
				if (printDetaills(object, astName + "." + yf[i].getName()) == false) {
					String stmp = object.toString();
					// if (stmp.startsWith("mh.struct.")) {
					// continue;
					// }
					U.print("%s.toString()=\"%s\"",
							astName + "." + yf[i].getName(), stmp);
				}
			}
		}
		return bool;
	}

	/** Print, NO breakline */
	public static void pt(Object aoa) {
		System.out.print(aoa);
	}

	/** Print Thread,Class,Time **/
	public static void print() {
		print(getCurrentThreadName());
		print(getCurrentClass().getName());
		print(getCurrentTime());
		print(getCmdToRun());
	}

	public static void print(Object aoa) {
		System.out.println(aoa);
	}

	public static void print(Collection<?> aoa) {
		for (Object o : aoa) {
			System.out.println(o.toString());
		}
	}

	public static void print(int[] ayi) {
		if (ayi == null) {
			return;
		}
		if (ayi.length > 0) {
			U.print("%s[%d]", "int", ayi.length);
		}
		String stFormat = "[%-" + get_intDigit(ayi.length) + "s]=%s";
		for (int i = 0; i < ayi.length; i++) {
			print(stFormat, i, ayi[i]);
		}
	}

	public static void print(char[] ayi) {
		if (ayi == null) {
			return;
		}
		if (ayi.length > 0) {
			U.print("%s[%d]", "char", ayi.length);
		}
		String stFormat = "[%-" + get_intDigit(ayi.length) + "s]=%s";
		for (int i = 0; i < ayi.length; i++) {
			print(stFormat, i, ayi[i]);
		}
	}

	/**
	 * modified at 2014-10-21 20:38:31
	 * **/
	public static void print(Object ayo[]) {
		if (ayo == null) {
			U.print("[null]");
			return;
		}
		if (ayo.length > 0) {
			U.print("%s[%d]", ayo[0].getClass().getName(), ayo.length);
		}else {
			U.print("%s[%d]", ayo.getClass().getName(), ayo.length);
		}
		String stFormat = "[%-" + U.get_intDigit(ayo.length - 1) + "s]=%s";
		for (int i = 0; i < ayo.length; i++) {
			U.print(stFormat, i, ayo[i]);
		}
	}

	/**
	 * Fixed at 2014-08-12 20:02:29
	 **/
	public static void print(String ast_text, int ai_size) {
		if (ast_text.contains("%")) {
			/** void qgb.U.print(String format, Object... args) **/
			U.print(ast_text + "%s", ai_size, "");
			// U.msgbox();
			return;
		}
		if (ai_size > ast_text.length()) {
			ai_size = ast_text.length();
		}
		System.out.println(ast_text.subSequence(0, ai_size));
	}

	/**
	 * fixed at 2014-07-12 22:06:13|auto new line
	 * **/
	public static void print(String format, Object... args) {
		System.out.printf(format + "\n", args);
	}

	// /////////// Print End //////////////////////

	public static int get_intDigit(int ai) {
		return String.valueOf(ai).length();
	}

	// ////////////////////////////////////////////////////
	public static String cin(String ast_parentComponent, String ast_message) {
		return (msgbox(ast_parentComponent, ast_message));
	}

	public static String cin(String ast_message) {
		return (msgbox("Pleaese input text :", ast_message));
	}

	// ///////////////////////////////////////////////////
	public static void write(File filename, String ast_text) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			fw.write(ast_text);
			fw.flush();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * writes ast_text to (gstTestPath +ast_filename)
	 * 
	 * @see java.io.FileWriter
	 * @see qgb.U.autoPath
	 */
	public static void write(String ast_filename, String ast_text) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(U.autoPath(ast_filename));
			fw.write(ast_text);
			fw.flush();
			// tprint(ast_text);
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 缁叉Ξt_filename .婢剁繝璐熼崜宥堢熅 </p> modified at 2014-07-23 02:08:51 modified at
	 * 2014-07-31 03:00:19 TODO:mac os 闁炬澘鍤栫悰妞﹀稄鎷�
	 **/
	public static String autoPath(String ast_filename) {
		if (ast_filename.startsWith(".")) {
			return ast_filename;
		}
		if (isFullPath(ast_filename)) {// 娓氥儳銆嬬憴鎺戝殩娑撳搫鍙忕捄锟�
			makeDirs(ast_filename);
			return ast_filename;
		} else {
			makeDirs(gstTestPath + ast_filename);
			return (gstTestPath + ast_filename);
		}
	}

	/**
	 * Encodes this {@code String} into a sequence of bytes using the named
	 * charset, storing the result into (gstTestPath +ast_filename).
	 * 
	 * @param ast_filename
	 *            .
	 * @param ast_text
	 *            .
	 * @param ast_bytes
	 *            . The name of a supported
	 *            {@linkplain java.nio.charset.Charset charset} ,"UTF-8" etc.
	 * @see java.nio.charset.Charset
	 * @see qgb.T#write(String, InputStream)
	 * @see java.lang.String#getBytes()
	 */
	public static void write(String ast_filename, String ast_text,
			String CharsetName) {
		InputStream isa;
		try {
			isa = BytesToInputStream(ast_text.getBytes(CharsetName));
			write(ast_filename, isa);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// U.print(getCurrentMethod());
	}

	/**
	 * 2014-2-7 23:00:06
	 * 
	 * @version 1.1 fixed
	 */
	public static void write(String ast_filename, InputStream abis) {
		byte[] data = new byte[1];
		String sFileName = autoPath(ast_filename);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(sFileName));
			while (abis.read(data) != -1) {
				bos.write(data);
				// U.notify("U.write(" + gstTestPath + ast_filename
				// + ",InputStream)");
			}

			// 閸欘偆顣渚亾閸欘偉鎻敓锟�
			bos.flush();

			// 閹搭亝鍞�
			// bufferedInputStream.close();
			bos.close();
			abis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void write(String ast_filename, byte[] ayb) {
		write(ast_filename, BytesToInputStream(ayb));

	}

	// /////////////////////////////////////////////////////////////
	public static void makeDirs(String fileName) {
		File f = new File(fileName);
		// U.print("%s isD %b",fileName,F.isDirectory(fileName));
		/** 娴ｇ竵ile.isDirectory()閸欘偉顔愭稉锟藉Ν绾板鑼庣涵鐤瀾閸戙倓璐熼惄顔肩秿 **/
		if (F.isDirectory(fileName) == false) {
			f = f.getParentFile();
			if (f == null) {
				return;
			}
		}
		// U.print(f.getPath());
		if (f.exists()) {
			// notify("illegal fileName="+fileName);
			return;
		}
		f.mkdirs();
		if (f.exists() == false) {
			notify("illegal fileName=" + fileName);
			return;
		}
	}

	// ////////////////////////////////////////////
	/**
	 * Automatic judgment on the file path
	 * <p>
	 * default charset is GBK
	 * 
	 * @version 1.1
	 * @see qgb.T#readSt(String, String)
	 */
	public static String readSt(String ast_filename) {
		BufferedInputStream bis;
		bis = readBis(ast_filename);
		InputStreamReader isr = new InputStreamReader(bis);

		// print("qgb"+isr.getEncoding());
		try {
			String str = new String(InputStreamToBytes(bis), isr.getEncoding());
			bis.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Automatic judgment on the file path
	 * <p>
	 * decodes this file into a sequence of {@code String} using the named
	 * charset.
	 * 
	 * @param ast_filename
	 * 
	 * @param ast_bytes
	 *            , The name of a supported
	 *            {@linkplain java.nio.charset.Charset charset} ,"UTF-8" etc.
	 * @see java.nio.charset.Charset
	 * @see qgb.T#readBis(String)
	 * @see java.lang.String#getBytes()
	 */
	public static String readSt(String fileName, String ast_CharsetName) {
		try {
			return new String(InputStreamToBytes(readBis(fileName)),
					ast_CharsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Automatic judgment on the file path
	 * 
	 * @see qgb.U.autoPath
	 */
	public static BufferedInputStream readBis(String fileName) {
		File file = new File(U.autoPath(fileName));

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		// MP3player.play(bis);//test
		return bis;
	}

	/**
	 * 2014-10-03 16:58:21
	 * 
	 * @throws FileNotFoundException
	 * @see qgb.U.autoPath
	 */
	public static InputStream readIs(String fileName)
			throws FileNotFoundException {
		File file = new File(U.autoPath(fileName));

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return fis;
		} catch (FileNotFoundException e) {
			throw e;
			// e.printStackTrace();
		}
	}

	public static boolean isFullPath(String fileName) {
		return (fileName.contains(":"));
	}

	// ////////////////////////////////////////////////////////////////
	public static void exit(boolean... abnotify) {
		if (abnotify.length < 1) {
			System.exit(0);
		}
		notify("Exit!");
		System.exit(0);
	}

	public static void exit() {
		System.exit(0);
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * Tested 2014-3-24 0:46:50
	 * 
	 * @param abis
	 * @param ast_charSet
	 * @return
	 * @throws IOException
	 */
	public static String InputStreamToString(InputStream abis,
			String ast_charSet) {
		if (abis == null) {
			U.notify("InputStream null!");
			return null;
		}
		String str = "";
		try {
			InputStreamReader isr = new InputStreamReader(abis, ast_charSet);
			int ich;
			while ((ich = isr.read()) != -1) {
				str = str + (char) ich;
			}

			isr.close();
		} catch (UnsupportedEncodingException e) {
			U.error(e, "UnsupportedEncodingException");
		} catch (IOException e) {
			U.error(e, "IOException");
		}
		// U.print(str);
		return str;
	}

	// //////////////////////////////////////////////////////////////////
	/** if parameter is null then return null **/
	public static byte[] InputStreamToBytes(InputStream ais) {
		if (ais == null) {
			return null;
		}
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		byte imgdata[] = null;
		try {
			while ((ch = ais.read()) != -1) {
				bytestream.write(ch);
			}
			imgdata = bytestream.toByteArray();
			bytestream.close();
			ais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imgdata;
	}

	// ////////////////////////////////////////////////////////////////
	/** if parameter is null then return null **/
	public static InputStream BytesToInputStream(byte[] b) {
		if (b == null) {
			return null;
		} else {
			return new ByteArrayInputStream(b);
		}
	}

	// //////////////////////////////////////////////////////////////////
	public static void error(Exception e) {
		e.printStackTrace();

	}

	public static void error(Exception e, String ast) {
		System.out.flush();
		System.err.flush();
		
		U.print(ast + "|" + time());
		System.out.flush();
		e.printStackTrace();
		System.err.flush();
	}

	public static byte[] readBytes(String fileName) {
		return InputStreamToBytes(readBis(fileName));
	}

	public static void delFile(String asPath) {
		File file = new File(U.autoPath(asPath));
		if (file.exists()) {
			if (file.delete() == false) {
				U.notify("U.delFile error file is not  deleted!"+autoPath(asPath));
			}
		} else {
			U.notify("U.delFile error file is not found! "+autoPath(asPath));
		}

	}

	public static void setProxy(String asIP, String asPort) {
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", asIP);
		System.getProperties().setProperty("http.proxyPort", asPort);
	}

	/*********** setOutStream *************************/
	public static void setOutStream(String ast) {
		try {
			PrintStream ps = new PrintStream(U.autoPath(ast),
					CharsetName.GST_UTF8);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			U.error(e, ast + " Not Found");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void setErrStream(String ast) {
		try {
			PrintStream ps = new PrintStream(U.autoPath(ast),
					CharsetName.GST_UTF8);
			System.setErr(ps);
		} catch (FileNotFoundException e) {
			U.error(e, ast + " Not Found");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void resetErrStream() {
		resetErrStream(true, CharsetName.GST_UTF8);
	}

	public static void resetErrStream(boolean bFlush, String asCharsetName) {
		try {
			System.setErr(new PrintStream(new FileOutputStream(
					FileDescriptor.err), bFlush, asCharsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void resetOutStream() {
		resetOutStream(true, CharsetName.GST_UTF8);
	}

	public static void resetOutStream(String asCharsetName) {
		resetOutStream(true, asCharsetName);
	}

	public static void resetOutStream(boolean bFlush, String asCharsetName) {
		try {
			System.setOut(new PrintStream(new FileOutputStream(
					FileDescriptor.out), bFlush, asCharsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/*********** setOutStream End ********************/
	/**
	 * The method max(int, int) in the type T is not applicable for the
	 * arguments (int, null)
	 **/
	public static int max(int aia, int aib) {
		if (aia > aib) {
			return aia;
		}
		return aib;
	}

	/** Efficient */
	public static int min(int aia, int aib) {
		if (aia < aib) {
			return aia;
		}
		return aib;
	}

	/** Efficient */
	public static int min(int i, int j, int k) {
		return min(min(i, j), min(j, k));
	}

	public static int min(int aia, int... ayi) {
		if (ayi.length < 1)
			return aia;
		if (aia < ayi[0])
			ayi[0] = aia;
		for (int i = 0; i < ayi.length; i++) {
			if (ayi[i] < ayi[0])
				ayi[0] = ayi[i];
		}
		return ayi[0];
	}

	public static String getSourcePath(Class<?> aClass) {
		String stp = aClass.getProtectionDomain().getCodeSource().getLocation()
				.toString();
		if (stp.endsWith("/bin/") == false)
			return "Not Eclipse!";
		stp = "./src/";
		stp = stp + aClass.getName().replace('.', '/') + ".java";
		// print(stp);
		return stp;
	}

	/** 閺�棳ac oswindows **/
	public static String getSource(Class<?> aClass) {
		String stp = aClass.getProtectionDomain().getCodeSource().getLocation()
				.toString();
		if (stp.endsWith("/bin/") == false)
			return "Not Eclipse!";
		stp = "./src/";
		stp = stp + aClass.getName().replace('.', '/') + ".java";
		// print(stp);
		return U.readSt(stp);
	}

	public static String getCmdToRun() {
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length < 2) {
			return null;
		}
		/** getCurrentClass **/
		String str = "";
		Class<?> c = null;
		for (int i = 0; i < yste.length; i++) {
			if (yste[i].getMethodName().equals("getCmdToRun")) {
				try {
					c = Class.forName(yste[i + 1].getClassName());
					if (c != null) {
						break;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return "";
				}
			}
		}
		if (c == null) {
			U.notify("Can not Find class ????");
			;
			return "";
		}
		return ("java -cp " + c.getClassLoader().getResource("").getPath()
				+ " " + c.getName());

	}

	public static boolean browser(String url) {
		Runtime run = Runtime.getRuntime();
		if (null == url || "".equals(url)) {
			return false;
		}
		try {
			if (OS.isWin()) {
				run.exec("rundll32.exe url.dll,FileProtocolHandler " + url);
			} else if (OS.isMac()) {
				run.exec("open " + url);
			} else if (OS.isLinux() || OS.isUnix()) {
				String[] cmd = new String[2];
				cmd[0] = "firefox";
				cmd[1] = url;
				try {
					run.exec(cmd);
				} catch (IOException e) {
					cmd[0] = "xdg-open";
					run.exec(cmd);
				}
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void browser(final String asUrl, final int aiDelayMs) {
		if (aiDelayMs < 1) {
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				U.sleep(aiDelayMs);
				browser(asUrl);
			}
		}).start();
	}

	public static String getClassBase() {
		return U.class.getClassLoader().getResource("").getPath();
	}

	public static String getUserDir() {
		return System.getProperty(PropertyKey.user_dir) + "/";
	}

	public static void error(Object aoa) {
		System.err.println(aoa);
	}

	public static String exec(String asc) {
		Process p=null;
		Runtime rt = Runtime.getRuntime();
		try {
			p= rt.exec(asc);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		if(p==null)return "";
		InputStream is=p.getInputStream();
		String st="";
		try {
			st=new String(U.InputStreamToBytes(is),	CharsetName.GST_GBK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return st;
	}

}
