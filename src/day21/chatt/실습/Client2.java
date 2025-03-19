package day21.chatt.실습;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
	Socket socket;  //통신할 소켓 - 바이트기반스트림(들어오늘 길, 나가는 길)
	DataInputStream dis;  //바이트 기반 스트림이기 때문에 DataInputStream이라는 보조스트림으로 이용한다
	DataOutputStream dos;
	Scanner sc = new Scanner(System.in);
	
	
	
	public Client2() {
		//서버 ip, 포트번호
		
		try {
			socket = new Socket("localhost",6100);  //localhost는 ip로 대체할 수 있다 (localhost는 나 자신으로 클라와 서버를 하고 싶을 떄)
			
			dis = new DataInputStream(socket.getInputStream());  //통신할 소켓을 바이트기반 스트림으로 변경
			dos = new DataOutputStream(socket.getOutputStream());

			//클라이언트와 서버의 주고 받는 메소드의 순서는 달라야한다
			//받기
			recvData();
			//보내기
			sendData();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//데이터(문자)를 주고받는 메소드를 Thread로 만들어서 비동기식으로 만듦
	private void sendData() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//서버가 종료될 때 동안 계속 입력받는다
					while(true) {
						String msg = "서버 : "+sc.nextLine();  //보낼 문자열의 형식 지정하기
						dos.writeUTF(msg);  //문자쓰기 (UTF형식으로)
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		th.start();  //스레드는 실행시켜야함 메소드 호출과 동시에
	}



	private void recvData() {
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
	}



	public static void main(String[] args) {
		new Client2();
		
		
	}
}
