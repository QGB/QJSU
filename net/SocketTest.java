package qgb.net;


public class SocketTest {

	public static void main(String[] args) {
		Socket socket = new Socket("172.17.5.41", 12345);  
		socket.setSoTimeout(10000);  
		                  
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());  
	}
	
	

}
