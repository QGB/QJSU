package qgb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import qgb.text.QText;
/**QGB's java basic array method**/
public class Y {  
  
    public static void main(String [] args) {  
    	if (true) {
			return;
		}
    	String stin=T.readSt("y.txt");
    	//"char","Character"
    	String[][] yyc={{"boolean","Boolean"},{"byte","Byte"},{"char","Character"},
    			{"double","Double"},{"float","Float"},{"int","Integer"},{"long","Long"},{"short","Short"}};
    	for (int i = 0; i < yyc.length; i++) {
			String str=QText.replaceAll(stin, "char", yyc[i][0]);
			str=QText.replaceAll(str, "Character", yyc[i][1]);
			T.print(str);
			//T.msgbox();
		}
    	
    }  
    
    /***/
    public static ArrayList<Object> addArrayToArrayList(ArrayList<Object> aal,Object[] ayo) {
    	for (int i = 0; i < ayo.length; i++) {
			aal.add(ayo[i]);
		}
		return aal;
	}
    public static ArrayList<Object> ArrayToArrayList(Object[] ayo) {
		return addArrayToArrayList(new ArrayList<Object>(), ayo);
		
	}
    /***************************************/
    //去掉数组中重复的值  
    public static <Type> List<Type> delMuti(List<Type> str) {  
        List<Type> list = new ArrayList<Type>();  
        for (int i=0; i<str.size(); i++) {  
            if(!list.contains(str.get(i))) {  
                list.add(str.get(i));  
            }  
        }  
          //返回一个包含所有对象的指定类型的数组   
        return list;
    }  
    public static <Type> Type[] delMuti(Type[] str) {  
        return delMuti(Arrays.asList(str)).toArray(str);
    }  
	public static char[] delMuti(char[] ayc) {
		 return unboxing(delMuti(boxing(ayc))) ;
	}
	//////////// boxing unboxing//////////////////////////////////////////////
	/**boolean[] Boolean[]**/
	public static Boolean[] boxing(boolean[] ay) {
		Boolean[] y=new Boolean[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Boolean(ay[i]);
		}
		return y;
	}
	public static boolean[] unboxing(Boolean[] ay) {
		boolean[] y=new boolean[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].booleanValue();
		}
		return y;
	}

	/**byte[] Byte[]**/
	public static Byte[] boxing(byte[] ay) {
		Byte[] y=new Byte[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Byte(ay[i]);
		}
		return y;
	}
	public static byte[] unboxing(Byte[] ay) {
		byte[] y=new byte[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].byteValue();
		}
		return y;
	}

	/**char[] Character[]**/
	public static Character[] boxing(char[] ay) {
		Character[] y=new Character[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Character(ay[i]);
		}
		return y;
	}
	public static char[] unboxing(Character[] ay) {
		char[] y=new char[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].charValue();
		}
		return y;
	}

	/**double[] Double[]**/
	public static Double[] boxing(double[] ay) {
		Double[] y=new Double[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Double(ay[i]);
		}
		return y;
	}
	public static double[] unboxing(Double[] ay) {
		double[] y=new double[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].doubleValue();
		}
		return y;
	}

	/**float[] Float[]**/
	public static Float[] boxing(float[] ay) {
		Float[] y=new Float[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Float(ay[i]);
		}
		return y;
	}
	public static float[] unboxing(Float[] ay) {
		float[] y=new float[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].floatValue();
		}
		return y;
	}

	/**int[] Integer[]**/
	public static Integer[] boxing(int[] ay) {
		Integer[] y=new Integer[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Integer(ay[i]);
		}
		return y;
	}
	public static int[] unboxing(Integer[] ay) {
		int[] y=new int[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].intValue();
		}
		return y;
	}

	/**long[] Long[]**/
	public static Long[] boxing(long[] ay) {
		Long[] y=new Long[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Long(ay[i]);
		}
		return y;
	}
	public static long[] unboxing(Long[] ay) {
		long[] y=new long[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].longValue();
		}
		return y;
	}

	/**short[] Short[]**/
	public static Short[] boxing(short[] ay) {
		Short[] y=new Short[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=new Short(ay[i]);
		}
		return y;
	}
	public static short[] unboxing(Short[] ay) {
		short[] y=new short[ay.length];
		for (int i = 0; i < ay.length; i++) {
			y[i]=ay[i].shortValue();
		}
		return y;
	}

  ////////////// boxing unboxing End////////////////////////////////////////////////
    public static String[] del_one_element(String[] ayst,int aidel) {  
        /*String [] str = {"Java", "C++", "Php", "C#", "Python"};  
        for (String elementA:ayst ) {  
            System.out.print(elementA + " ");  
        }  */
        //删除php  
        List<String> list = new ArrayList<String>();  
        for (int i=0; i<ayst.length; i++) {  
            list.add(ayst[i]);  
        }  
        list.remove(aidel); //list.remove("Php")   
        //System.out.println();  
        return( list.toArray(new String[1]));
        /*String[] newStr =  list.toArray(new String[1]); //返回一个包含所有对象的指定类型的数组   
        for (String elementB:newStr ) {  
            System.out.print(elementB + " ");  
        }     
        System.out.println();  */
    }  
  

	public static String ArrayToStr(String[] ayst, String ast_separator) {
		String str="";
		//T.print("["+ast_separator+"]");
		for (int i = 0; i < ayst.length; i++) {
			str=str+ayst[i]+ast_separator;
		}
		return str;
	}
	/**自动剔除""与null值</br>
	 * Windows 换行符 \r\n**/
	public static String[] StrToArray(String ast,String ast_separator) {
		String[] yst=ast.split(ast_separator);
		return  delTarget(yst,"");	
	}
	/**删除aySource中的ayTarget。</br>自动删除null值**/
	public static <Type> Type[] delTarget(Type[] aySource, Type... ayTarget) {
		if (ayTarget==null||ayTarget.length<1) return aySource;
		int ir=0;
		for (int i = 0; i < aySource.length; i++) {
			for (int j = 0; j < ayTarget.length; j++) {
				if (aySource[i]==null||aySource[i].equals(ayTarget[j])) {
					break;
				}
				if (j==(ayTarget.length-1)) {
					aySource[ir]=aySource[i];
					ir++;
					//yT[++ir]=aySource[i];
				}
			}
		}
		return Arrays.copyOfRange(aySource, 0, ir);
	}
	
	public static String[] ObjectsToYStr(Object[] ayo) {
		String[] yst = new String[ayo.length];
		for (int i = 0; i < ayo.length; i++) {
			yst[i] = ayo[i].toString();
		}
		return yst;
	}

}  