package day21.chatt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {
	ServerSocket serverSocket;   //서버용 소켓
	Socket clientSocket;	    // 클라이언트와 통신할 소켓 
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;	
	Scanner in = new Scanner(System.in);
	
	
	 public Server1() {
		 
		 try {            // 5000~9000 사이  사용
			serverSocket  = new ServerSocket(6100);  // 서버가 port 포트를 지정함 !!!
			System.out.println(" server waiting");
			clientSocket = serverSocket.accept();
			System.out.println("server start");
			String clientIp = clientSocket.getInetAddress().toString();
			
			
			System.out.println( "clientIp="+ clientIp ) ;
			// 
			dataInputStream = new DataInputStream( clientSocket.getInputStream());
			dataOutputStream = new DataOutputStream ( clientSocket.getOutputStream());	
			
			dataRecv();
			dataSend();				
			
		} catch (IOException e) {
		 
			e.printStackTrace();
		}  	 
	 }
	
	 
	
	 //보내기
	private void dataSend() {		 	
			
		try {
			System.out.println(" 메시지 작성");
			String message  = in.nextLine();
			dataOutputStream.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	
	//받기
	private void dataRecv() {
		try {
			String message  =dataInputStream.readUTF();
			System.out.println( message);
		} catch (IOException e) {			 
			e.printStackTrace();
		} 
	} 


	public static void main(String[] args) {
		 new Server1();

	}

}
