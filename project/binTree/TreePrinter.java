package qgb.project.binTree;


import java.util.ArrayList;

import qgb.*;


public class TreePrinter {
    
    public TreePrinter(){
    }
    
    public static String TreeString(TextNode root){
        ArrayList<String> layers = new ArrayList<String>();
        ArrayList<TextNode> bottom = new ArrayList<TextNode>();
         
        FillBottom(bottom, root);  DrawEdges(root);
        
        int height = GetHeight(root);
        for(int i = 0; i < height; i++) layers.add("");
        bottom.clear(); FillBottom(bottom, root);
        
        
        int min = layers.get(0).length();
        
        for(int i = 0; i < bottom.size(); i++){
            TextNode n = bottom.get(i);
            String s = layers.get(n.depth);
            
            while(s.length() < n.x) s += " ";
            if(min > s.length()) min = s.length();
            
            if(!n.isEdge) s += "[";
            s += n.text;
            if(!n.isEdge) s += "]";
            
            layers.set(n.depth, s);
        }

        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < layers.size(); i++){
            if(i != 0) sb.append("\n");
            sb.append(layers.get(i).substring(min));
        }
        
        return sb.toString();
    }
    
    private static void DrawEdges(TextNode n){
        if(n == null) return;
        if(isLeaf(n)) return;
        
        if(n.L != null){
            int count = n.x - (n.L.x + n.L.text.length() + 2);
            ArrayList<TextNode> temp = new ArrayList<TextNode>();
            
            for(int i = 0; i < count; i++){
                TextNode x = new TextNode("/"); 
                x.isEdge = true;
                x.x = n.x - i - 1;
                x.depth = n.depth + i + 1;
                if(i > 0) temp.get(i-1).L = x;
                temp.add(x);
            }
            
            temp.get(count-1).L = n.L;
            n.L.depth = temp.get(count-1).depth+1;
            n.L = temp.get(0);
            
            DrawEdges(temp.get(count-1).L);
        }
        if(n.R != null){
            int count = n.R.x - (n.x + n.text.length() + 2);
            ArrayList<TextNode> temp = new ArrayList<TextNode>();
            
            for(int i = 0; i < count; i++){
                TextNode x = new TextNode("\\"); 
                x.isEdge = true;
                x.x = n.x + n.text.length() + 2 + i;
                x.depth = n.depth + i + 1;
                if(i > 0) temp.get(i-1).R = x;
                temp.add(x);
            }
            
            temp.get(count-1).R = n.R;
            n.R.depth = temp.get(count-1).depth+1;
            n.R = temp.get(0);  
            
            DrawEdges(temp.get(count-1).R);
        }
    }
    
    private static void FillBottom(ArrayList<TextNode> bottom, TextNode n){
        if(n == null) return;
        
        FillBottom(bottom, n.L);
        
        if(!bottom.isEmpty()){            
            int i = bottom.size()-1;
            while(bottom.get(i).isEdge) i--;
            TextNode last = bottom.get(i);
            
            if(!n.isEdge) n.x = last.x + last.text.length() + 3;
        }
        bottom.add(n);
        FillBottom(bottom, n.R);
    }
    
    private static boolean isLeaf(TextNode n){
        return (n.L == null && n.R == null);
    }
    
    private static int GetHeight(TextNode n){
        if(n == null) return 0;
        
        int l = GetHeight(n.L);
        int r = GetHeight(n.R);
        
        return Math.max(l, r) + 1;
    }
}


class TextNode {
    public String text;
    public TextNode D, L, R;
    public TextNode getD() {
		return D;
	}

	public void setD(TextNode d) {
		D = d;
	}

	public TextNode getL() {
		return L;
	}

	public void setL(String l) {
		L = new TextNode(l, this);
	}

	public TextNode getR() {
		return R;
	}

	public void setR(String r) {
		R = new TextNode(r, this);
	}

	public boolean isEdge;
    public int x, depth;
    
    public TextNode(String text){
        this.text = text;
        D = null; L = null; R = null;
        isEdge = false;
        x = 0; depth = 0;
    }
    
    public TextNode(String text,TextNode aD){
    	if (aD==null) {
			U.argsError(text,aD);
		}
        this.text = text;
        D = aD; L = null; R = null;
        isEdge = false;
        x = 0; depth = 0;
    }

	public TextNode(char c) {
		this(String.valueOf(c));
	}

	public void setL(char c) {
		setL(String.valueOf(c));
	}
	
	public void setR(char c) {
		setR(String.valueOf(c));
	}
    
}
