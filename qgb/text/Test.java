package qgb.text;

import java.io.IOException;
import java.util.*;

import qgb.T;

public class Test {  

    public static void main(String args[]) throws IOException{ 

        String s = T.read_st("D:/jass.txt"); //待测试的字符串

        Map<Character, Integer> result = getCharMaps(s);

        System.out.println(result);//打印出字符串中各字符出现的次数！

        

   } 

    public static Map<Character, Integer> getCharMaps(String s) {

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for(int ia = 0; ia < s.length(); ia++   ) {

            Character c = s.charAt(ia);

            Integer count = map.get(c);

            map.put(c, count == null ? 1 : count+1);

        }

        return map;

        

    }

   

} 


