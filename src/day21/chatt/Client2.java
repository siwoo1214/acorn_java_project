package day21.chatt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {	
	
	Socket socket;	
	DataInputStream dataInputStream;      // 보조스트림  in
	DataOutputStream dataOutputStream;	  // 보조스트림 out
	Scanner sc = new Scanner(System.in);
	
	
	public Client2() {		
		try {			
			 socket = new Socket("192.168.100.40", 6100);   // 서버의 ip , 포트번호 		
			//socket = new Socket("localhost", 6100);   // 서버의 ip , 포트번호 		
			dataInputStream = new DataInputStream( socket.getInputStream());
			dataOutputStream = new DataOutputStream( socket.getOutputStream());			
			//보내기	
			sendMsg();
			//받기	
			receiveMsg();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 	
	
	
	
	
	private  void sendMsg() {		
		 
		  Thread t = new Thread( new Runnable() {
			@Override
			public void run() {	
				   try {
						 while(true) {
								
							  String msg = sc.nextLine();							  	
							  dataOutputStream.writeUTF( "클라이언트:"+ msg );
						 }					      
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						 
					}				
				 
			}});
		  
		  
		  t.start();	
		 
	}
	
	
	
	private void receiveMsg() {	
		Thread t = new Thread( new Runnable() {
			@Override
			public void run() { 
				
					try {  
						
						  while(true) {
						    String  msg = dataInputStream.readUTF();
							System.out.println( msg);
						  }
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						 
					}
				
			 }});
		
		
		t.start();
		
	
		
	} 
	
	public static void main( String[] args) {
		 new Client2();
		 
	}

}
