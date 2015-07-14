package qgb.project.leetcode;

import java.util.ArrayList;
import java.util.List;

import qgb.U;

public class GenerateParenthesis {
	public static void main(String[] args) {
		GenerateParenthesis g=new GenerateParenthesis();
		List<String> ls=g.generateParenthesis(4);
		
		U.print(ls);
		//U.print(ls.contains("()()()()"));
//		ArrayList<String> als=new ArrayList<>();
//		als.addAll(ls);
//		als=traversal("(())()", als);
		
		//U.print(als);
//		for (int i = 0; i < ls.size(); i++) {
//			if (als.contains(ls.get(i))) {
//				U.print(ls.get(i));
//			}else {
//				als.add(ls.get(i));
//			}
//		}
	}
   
	 public List<String> generateParenthesis(int n) {
	        ArrayList<String> als=new  ArrayList<String>();
	        if (n==1){
	            als.add("()");
	            return als;
	        }
	        List<String> ls= generateParenthesis(n-1);
	        for (int i = 0; i <ls.size() ; i++) {
	        	String s="("+ls.get(i)+")";
	    		if(!als.contains(s)){ 
	    			als.add(s);
	    		}else {
	    			//U.msgbox(ls.get(i));
	    			als=traversal(ls.get(i), als);
				}
	        	
	        	String s1="()"+ls.get(i),s2=ls.get(i)+"()";
	        	
	    		if(!als.contains(s1)){ 
	    			als.add(s1);
	    		}else {
	    			//U.msgbox(ls.get(i));
	    			als=traversal(ls.get(i), als);
				}
	        	
	        	if (!s1.equals(s2))
	        		if(!als.contains(s2)){ 
	        			als.add(s2);
	        		}else {
	        			als=traversal(ls.get(i), als);
	        			//U.msgbox(s2);
					}
			}
			return als;
	    }
		
		public static ArrayList<String> traversal(String as,ArrayList<String> als) {
			for (int i = 1; i <=as.length(); i++) {
				String s=as.substring(0,i)+"()"+as.substring(i,as.length());
				if (!als.contains(s)) {
					als.add(s);
					//return als;
				}
			}
			return als;
		}
	
	
    public List<String> generateParenthesis0(int n) {
        ArrayList<String> als=new  ArrayList<String>();
        if (n==1){
            als.add("()");
            return als;
        }
        
        List<String> ls= generateParenthesis0(n-1);
        for (int i = 0; i <ls.size() ; i++) {
        	als.add("("+ls.get(i)+")");
        	String s1="()"+ls.get(i),s2=ls.get(i)+"()";
        	als.add(s1);
        	if (!s1.equals(s2)) {
				als.add(s2);
			}
		}
		return als;
    }

}
