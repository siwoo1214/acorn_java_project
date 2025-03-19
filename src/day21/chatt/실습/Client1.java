package day21.chatt.실습;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
	Socket socket;  //통신할 소켓 - 바이트기반스트림(들어오늘 길, 나가는 길)
	DataInputStream dis;
	DataOutputStream dos;
	Scanner sc = new Scanner(System.in);
	
	
	
	public Client1() {
		//서버 ip, 포트번호, 
		//
		try {
			socket = new Socket("localhost",6100);
			
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			//받기
			recvData();
			//보내기
			sendData();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private void sendData() {
		try {
			String msg = "박시우 : "+sc.nextLine();
			dos.writeUTF(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private void recvData() {
		try {
			String msg = dis.readUTF();
			System.out.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		new Client1();
		
		
	}
}
