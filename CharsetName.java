package qgb;

import java.nio.charset.Charset;

//TODO add new CharsetName.
public class CharsetName {
	public static final String GST_GB2312="GB2312";
	public static final String GST_GBK="GBK";
	public static final String GST_UTF8="UTF-8";
	/**Default**/
	public static String GST_DEF=GST_UTF8;
	public static void main(String[] args)  {	
		//T.write(ast_filename, ast_text, ast_bytes);
		Charset.isSupported(GST_GB2312);
		Charset.isSupported(GST_GBK);
		Charset.isSupported(GST_UTF8);
		T.print(CharsetName.class);
	}
	
	
	
}
