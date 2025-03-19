package day21.chatt.실습;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//서버
//서버소켓

//클라이이언트 - 서버 (1:1 채팅기능)
public class Server2 {
	ServerSocket serverSocket;  //서버소켓
	Socket clientSocket;        //클라리언트와 통신할 소켓(실제 통신하는 소켓, 전화기 역할 - 기반스트림 보유(나가는/들어오는 통로))
								//바이트스트림입니다 얘네는 (보조스트림이 필요하다)
	
	//보조스트림
	DataInputStream dis;
	DataOutputStream dos;
	Scanner sc = new Scanner(System.in);
	
	
	public Server2 (){
									//포트번호 (5000~9000 사이에서 사용)
		try {
			serverSocket = new ServerSocket(6100);
			System.out.println("서버 기다리는 중...");
			clientSocket = serverSocket.accept();  //서버가 listen상태에 빠진다 -> socket반환
			System.out.println("서버 start!!!");
			
			String clientIp = clientSocket.getInetAddress().toString();
			
			dis = new DataInputStream(clientSocket.getInputStream());  //들어오는 길
			dos = new DataOutputStream(clientSocket.getOutputStream());  //나가는 길
			
			//보내기
			sendMsg();
			
			//받기
			recvMsg();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//받기

		
		//보내기
	}
	
	private void sendMsg() {
		//계속 보내는 기능을 별도의 작업으로 변경
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						String msg = "박시우 : "+sc.nextLine();
						dos.writeUTF(msg);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		th.start();
		
//		try {
//			while(true) {
//				String msg = "서버 : "+sc.nextLine();
//				dos.writeUTF(msg);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void recvMsg() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true) {
						String msg = dis.readUTF();
						System.out.println(msg);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		th.start();
		
//		try {
//			while(true) {
//				String msg = dis.readUTF();
//				System.out.println(msg);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) {
		new Server2();
	}
}
