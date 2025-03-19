package day21.group;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	Vector<ChatHandler> v;
		

	public ChatServer( ) {
		try {
			ServerSocket ss = new ServerSocket(5000);
			v= new Vector<>();
			System.out.println("서버 준비 완료");
			while(true) {
				Socket s = ss.accept();
				
				//서버에서는 각 클라이언트와 통신할 수 있는 핸들러 객체를 생성한다. ( 핸들러는 client가 보낸 내용으로 서버에게 방송 요청한다)
				                                                      // 모든 핸들러 정보를 통해서 모든 클라이언트에게 데이터를 보낸다
				ChatHandler c= new ChatHandler(this, s);  //서버정보,  클라이언트소켓정보
				System.out.println("클라이언트");
				c.start();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void register(ChatHandler c) {	 
		v.add(c);
	}

	public void unregister(ChatHandler c) {		
		v.remove(c);
	}
	
	 
	public synchronized  void broadcast(String message) {	  
			 			
			for(int i=0; i<  v.size(); i++) {				 
				ChatHandler c = v.get(i);
				try {
					c.disp(message);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}	 	 
	}
	
	
	

	public static void main(String[] args) {
	 
		new ChatServer( );

	}

}
