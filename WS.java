import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;

import qgb.*;
import qgb.text.QText;
public class WS {
	public static void main(String [] args){
		int port;
		ServerSocket server_socket;
		try{
			port=Integer.parseInt(args[0]);
		}
		catch (Exception e){
			port=8080;
		}
		try{
			server_socket=new ServerSocket(port);
			System.out.println("WebServer running on port"+server_socket.getLocalPort());
			while(true){
				Socket socket=server_socket.accept();
				System.out.println("New connection accepted"+socket.getInetAddress()+":"+socket.getPort());
				//针对特定的请求创建处理该请求的线程
				try{
					httpRequestHandler request=new httpRequestHandler(socket);
					Thread thread=new Thread(request);
					thread.start();
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
}

//处理请求的线程类
class httpRequestHandler implements Runnable{
	static int gi=0;
	final static String CRLF="/r/n";
	Socket socket;
	InputStream input;
	OutputStream output;
	BufferedReader br;
	//判断请求的文件类型是否正确
	boolean fileType=true;
	
	//初始化参数
	public httpRequestHandler(Socket socket) throws Exception{
		this.socket=socket;
		this.input=socket.getInputStream();
		this.output=socket.getOutputStream();
		this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	//启动该线程
	public void run(){
		try{
			processRequest();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//处理请求的核心函数
	private void processRequest(){
		while(true){
			String shl = null;
			try {
				shl = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(shl==null||shl.equals(CRLF)||shl.length()<1)
				break;
			
			System.out.println("the client request is: "+shl);
			StringTokenizer s=new StringTokenizer(shl);
			String temp=s.nextToken();
			if(temp.equals("GET")){
				
				String fileName=s.nextToken();
				fileName=fileName.substring(1, fileName.length());
				InputStream fis=null;
				boolean fileExists=true;
				
				//if()
				try{
					fis=T.readIs(fileName);
				}
				catch(FileNotFoundException e){
					e.printStackTrace();
					fileExists=false;
				}
				
				String serverLine="Server:a simple java WebServer";
				String statusLine=null;
				String contentTypeLine=null;
				String entityBody=null;
				String contentLengthLine="error";
				//T.msgbox(QText.format("fe %s.ft %s",fileExists,this.fileType));
				T.print("---------%sfe %s.ft %s",++gi,fileExists,this.fileType);
				if(fileExists&&this.fileType){
					statusLine="HTTP/1.0 200 OK"+CRLF;
					contentTypeLine="Content-type:"+this.contentType(fileName)+CRLF;
					try {
						contentLengthLine="Content-Length:"+(new Integer(fis.available())).toString()+CRLF;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					if(fileExists&&this.fileType==false){
						statusLine="HTTP/1.0 200 BadRequest"+CRLF;
						contentTypeLine="text/html";
						entityBody="<HTML>400 Not BadRequest</TITLE></HEAD>"+
						"<BODY>400 BadRequest"+
						"<br>usage:http://yourHostName:port/"+
						"fileName.html</BODY></HTML>";	
					}
					else if(fileExists==false){
						statusLine="HTTP/1.0 404 Not Found"+CRLF;
						contentTypeLine="text/html";
						entityBody="<HTML>404 Not Found</TITLE></HEAD>"+
						"<BODY>404 Not Found"+
						"<br>usage:http://yourHostName:port/"+
						"fileName.html</BODY></HTML>";
					}
				}
				try {
//					output.write(statusLine.getBytes());
//					output.write(serverLine.getBytes());
//					output.write(contentTypeLine.getBytes());
//					output.write(contentLengthLine.getBytes());
//					output.write(CRLF.getBytes());
//					
					if(fileExists&&this.fileType){
						sendBytes(fis,output);
						fis.close();
					}
										else{
											output.write(entityBody.getBytes());
										}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//while end
		try{
			output.close();
			br.close();
			socket.close();
		}
		catch(Exception e){}
	}
	
	//将客户端请求的页面发送出去
	private static void sendBytes(InputStream fis,OutputStream os) throws Exception{
		byte[] buffer=new byte[1024];
		int bytes=0;
		while((bytes=fis.read(buffer))!=-1){
			os.write(buffer,0,bytes);
		}	
	}
	//设置contentType的内容	
	private static String contentType(String fileName){
		if(fileName.endsWith(".htm")||fileName.endsWith(".html")){
			return "text/html";
		}
		return "fileName";
	}
		
}
