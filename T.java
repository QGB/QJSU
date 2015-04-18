package qgb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**modified at 2014-09-22 21:21:51**/
public class T {
	public static void main(String[] args) throws IOException {
		//U.print("[%s]",lineWrap("0123456789Abcdefghijk",3));
		//U.print("0!!!!34567!!!!!!89A2!!!".length());
		String stW = "0!!!!34567!!!!!!89A2!!!!bcdefghijk!!";
		//stW=QText.delChars(stW,'|','`','~','!','@','#','$','%','^','&','*','(',')');
		
		U.print(replaceAll(stW, "!!", "!!!"));
		
		U.print(padNum(1,9999));
		U.print(sub("123456","23"));
		U.print(sub("123456","23","56"));
		//U.print(delMutiChar(stW));
		// U.print(str.substring(0,str.indexOf("3") ));
	}

	public static final String gsWinNewline="\r\n";
	/**format Decimal
	 * 2014-7---**/
	public static String formatNum(Object ao, String ast){
		DecimalFormat df = new DecimalFormat(ast);
		// U.print( df.format(ao));
		return df.format(ao);
	}

	// /////////////////////////////////////////////////
	public static String repeat(int itimes, String ast_text) {
		if (itimes < 1) {
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < itimes; i++) {
			sb.append(ast_text) ;
		}
		return sb.toString();
	}

	public static String delMutiChar(String ast_text) {
		String ta = "";
		String tb = "";
		for (int i = 0; i < ast_text.length(); i++) {
			tb = ast_text.substring(i, i + 1);
			if (ta.contains(tb) == false) {
				ta = ta + tb;
			}
		}
		return ta;
	}

	public static String padHead(Object aBePaded, int aiMax, String astPad) {
		int ia = aiMax - aBePaded.toString().length();
		if (ia < 0) {
			U.notify("aBePaded too large!");
			return aBePaded.toString();
		}
		int im = ia / astPad.length();
		int ir = ia % astPad.length();

		return (repeat(ir, " ") + repeat(im, astPad) + aBePaded.toString());
	}
	/**Result length is aiMax*/
	public static String pad(Object aBePaded, int aiMax, String astPad) {
		int ia = aiMax - aBePaded.toString().length();
		if (ia < 0) {
			U.notify("aBePaded too large!");
			return aBePaded.toString();
		}
		int im = ia / astPad.length();
		int ir = ia % astPad.length();

		// System.out.printf("ai=%d\nia=%d\nim=%d\nast.l=%d\nir=%d\n",
		// ai,ia,im,astPad.length(),ir);

		return (aBePaded.toString() + repeat(im, astPad) + repeat(ir, " "));
	}

	public static String pad(Object aBePaded, int aiMax) {

		int ia = aiMax - aBePaded.toString().length();
		if (ia < 0) {
			U.notify("aBePaded too large!");
			return aBePaded.toString();
		}

		// System.out.printf("ai=%d\nia=%d\nim=%d\nast.l=%d\nir=%d\n",
		// ai,ia,im,astPad.length(),ir);

		return (aBePaded.toString() + repeat(ia, " "));
	}
	/**�����������Խ���쳣**/
	public static String getBegins(String ast, int ai) {
		return (String) ast.subSequence(0, U.min(ast.length(),ai));
	}
	/**�����ڲ���SQL��䣻
	 * 2014-09-21 00:03:01**/
	public static String format(String format, Object... args) {
		ByteArrayOutputStream byos=new ByteArrayOutputStream();
		PrintStream ps=new PrintStream(byos,true);
		ps.format(format, args);
		try {
			return byos.toString(CharsetName.GST_DEF);
		} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		return "";
	}
	
	public static String lineWrap(StringBuilder asb,int aiColumns) {
		if(asb==null)return null;
		if(aiColumns<1||asb.length()<=aiColumns)return asb.toString();
		int ir=asb.length(),ic=aiColumns;//,irows=imax/aiColumns,ir=imax%aiColumns+irows;
		//if((ir)>aiColumns)irows+=ir/aiColumns
		while (ir>aiColumns) {
			asb.insert(ic,'\n');
			ic+=aiColumns+1;
			ir-=(aiColumns);
			//U.print("r=%d,c=%d",ir,ic);
		}
		//if(ir>0)asb.insert(ic,'\n');
		return asb.toString();
	}
	/**eclipse����̨���76����ͨ��,96��ȫ����**/
	public static String lineWrap(String ast,int aiColumns) {
		return lineWrap(new StringBuilder(ast), aiColumns);
	}

	public static String delChars(String stW, char... ayc) {
		if (ayc.length<1||stW.length()<1)return stW;
		StringBuilder sb=new StringBuilder(stW);
		int idc=0,imax= sb.length();
		for (int i = 0; i <imax; i++) {
			char c=sb.charAt(i-idc);
			for (int j = 0; j < ayc.length; j++) {
				if(c==ayc[j]){
					sb.deleteCharAt(i-idc);
					idc++;
					break;
				}
			}
		}
		return sb.toString();
	}

	/**�ݹ��滻��ֱ�������� astOld</br>
	 * **/
	public static String replaceAll(String ast, String astOld, String astNew) {
		/**��astNew �а� astOldʱ,���������ݹ顣���Է��� �滻һ�κ��ֵ��*/
		if (astNew.contains(astOld)) {
			return ast.replace(astOld, astNew);
		}
		if (ast.contains(astOld)==false) {
			return ast;
		}
		return replaceAll(ast.replace(astOld, astNew), astOld, astNew);
	}
	//////////////////////////////////////////////////////////////
	public static String replaceChars(String ast,String astNew, String asyc) {
		return replaceCharArray(ast,astNew,asyc.toCharArray());
	}
	public static String replaceChars(String ast,String astNew, char... ayc) {
		return replaceCharArray(ast,astNew,ayc);
	}
	public static String replaceCharArray(String ast,String astNew, char[] ayc) {
		if (ayc==null||ayc.length<1)return ast;
		ayc=Y.delMuti(ayc);
		if (ayc.length<1||ast==null||ast.length()<1)return ast;
		if (astNew==null||astNew.length()<1)return delChars(ast, ayc);	
		
		StringBuilder sb=new StringBuilder(ast);
		int idc=0,imax= sb.length();
		final int iNew=astNew.length()-1;
		for (int i = 0; i <imax; i++) {
			char c = sb.charAt(i-idc);
			for (int j = 0; j < ayc.length; j++) {
				if(c==ayc[j]){
					sb.replace(i-idc,i-idc+1, astNew);
					idc=idc+iNew;
					break;
				}
			}
		}
		return sb.toString();
	}

	public static String padNum(int i, int MaxValue) {
		final int im= U.get_intDigit(MaxValue);
		if (i>Math.pow(10, im)) {
			return (i+"").substring(0,im);
		}
		return i+repeat(im-(i+"").length(), " ");
	}

	public static String sub(String ast, String as1) {
		if (ast==null||as1==null) {
			return "";
		}
		if (!ast.contains(as1)) {
			return "";
		}
		int i=ast.indexOf(as1)+as1.length();
		return ast.substring(i);
	}

	public static String sub(String ast, String as1, String as2) {
		if (ast==null||as1==null||as2==null) {
			return "";
		}
		if (!ast.contains(as1)||!ast.contains(as2)) {
			return "";
		}
		int ia=ast.indexOf(as1)+as1.length();
		int ib=ast.indexOf(as2);
		if (ib<=ia) {
			return "";
		}
		return ast.substring(ia,ib);
	}

	public static String replace(String ast, String as1, String as2) {
		if(ast==null||as1==null||as2==null||ast.equals("")){
			return "";
		}
		if(as1.equals("")){
			return ast;
		}
		return ast.replace(as1, as2);
	}
}
