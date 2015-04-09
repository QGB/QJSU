import java.io.*;
import java.net.InetSocketAddress;

//import org.apache.http.HttpStatus;
import com.sun.net.httpserver.*;

import qgb.*;

public class HS{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args){
    		//T.msgbox("title",T.getCmdToRun());
    		try{
        	HttpServer server = HttpServer.create(new InetSocketAddress(
                "0.0.0.0", 8888), 0);
                
        	server.createContext("/",new Txt("2"));
        	
        	//server.createContext("/j.json",new Txt("1"));
        	server.setExecutor(null); // creates a default executor
				}catch(Exception e1){
					e1.printStackTrace();
				}
				//T.msgbox();
				T.print("end");
    }
    
    public static class Txt implements HttpHandler {
    		String gs="";
    		public Txt(String as){
    				gs=as;
    		}
    	
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            //针对请求的处理部分     
            //返回请求响应时，遵循HTTP协议  
            String responseString =gs; 
            //设置响应头  
            httpExchange.sendResponseHeaders(200, responseString.length());    
            OutputStream os = httpExchange.getResponseBody();    
            os.write(responseString.getBytes());    
            os.close();  
        }
    }    
    
    public static class F implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            //针对请求的处理部分     
            //返回请求响应时，遵循HTTP协议  
            String responseString = "00000000098";//T.readSt("j.txt"); 
            //设置响应头  
            httpExchange.sendResponseHeaders(200, responseString.length());    
            OutputStream os = httpExchange.getResponseBody();    
            os.write(responseString.getBytes());    
            os.close();  
        }
    }

}