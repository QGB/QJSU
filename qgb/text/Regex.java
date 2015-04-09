package qgb.text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import qgb.CharsetName;
import qgb.T;
import qgb.net.Get;

public class Regex{
	public static void main(String argus[]) throws Exception {
	//test();
	//T.print(getYst("\\w", "ast_text"));
		//
	String str=T.read_st("g.txt",CharsetName.GST_GB2312);
	T.write("g.txt.html", txtToHtml(str),CharsetName.GST_GB2312);
	}
	/************************************************************/
	public static final String GS_IP="\\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b";
	public static final Pattern GP_IP=Pattern.compile(GS_IP);
			
	public static final String GS_URL="((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]"
			+ "{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))"
			+ "(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
	private static String txtToHtml(String ast) {
		String[] yst=getYst(GS_URL,ast);
		for(String st:yst){
			T.print(st);
			/**替换url 只能用 replace ，replaceAll无效*/
			ast=ast.replace(st, 
					QText.format("<a href=\"%s\" target='new'>%s</a>",st,st));
		}
		
		return ast.replaceAll("\\n", "</br>");
	}
	
	
	public static String get_first_text (String ast_re,String ast_text){
		//(?<=<span class="k">\n)\w*(?=\s*</span>)
		Matcher m=Pattern.compile(ast_re).matcher(ast_text); 
		while(m.find()){
			return m.group();
		}
		return "";
	}
	
	public static String[] getYst (String ast_re,String ast_text){
 
        Pattern p = Pattern.compile(ast_re);
        Matcher m = p.matcher(ast_text);
        ArrayList<String> al=new ArrayList<String>();

        while (m.find()) {
            al.add(m.group(0));
        }

        String[] str = new String[al.size()];
        for (int i=0;i<al.size();i++)
        {
        	str[i]=al.get(i).toString();
        	//System.out.println(al.get(i).toString());
        }
		return str;
	}
	
	static void test() throws Exception {
		/* start()����ƥ�䵽�����ַ����ַ��е�����λ��.  
		* end()����ƥ�䵽�����ַ�����һ���ַ����ַ��е�����λ��.  
		* group()����ƥ�䵽�����ַ�  */
		String st_url="http://dict.cn/mini.php?q=good";
		//String st_t=" <span class=\"g b\">\nDefine      <span class=\"k\">\naspiration      </span>\n:    </span>\n    <object classi";
		String st_t=Get.html(st_url);
		String st_re="(?<=<span class=\"k\">\\n)\\w*(?=\\s*</span>)";
		T.print( get_first_text(st_re,st_t));
		T.print(cst+"end");
		T.exit();
		Pattern p=Pattern.compile(st_re);  
		Matcher m=p.matcher(st_t);  
		while(m.find()){
			//System.out.println(m.start());//��һ��ѭ������3���ڶ���ѭ������9  
			//System.out.println(m.end());//����7,�ڶ���ѭ������14
			System.out.println(m.group());//����2233���ڶ�������11222
		}
	}
	
	


}
