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
    public static void main(String[] args) throws IOException {
    		T.msgbox("title",T.getCmdToRun());
        HttpServer server = HttpServer.create(new InetSocketAddress(
                "172.17.5.26", 8765), 0);
                
        server.createContext("/",new MyResponseHandler());
          server.createContext("/j.json",new Txt(T.readSt("j.txt")));
        server.setExecutor(null); // creates a default executor
        server.start();

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
    
    public static class MyResponseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            //针对请求的处理部分     
            //返回请求响应时，遵循HTTP协议  
            String responseString = "<font color='#ff0000'>Hello! This a HttpServer!</font>"; 
            //设置响应头  
            httpExchange.sendResponseHeaders(200, responseString.length());    
            OutputStream os = httpExchange.getResponseBody();    
            os.write(responseString.getBytes());    
            os.close();  
        }
    }

}