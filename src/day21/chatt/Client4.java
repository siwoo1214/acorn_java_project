package day21.chatt;

 

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client4 {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Scanner sc;

    
    
    public Client4() {
        try {
            //socket = new Socket("192.168.100.40", 6100);
            socket = new Socket("localhost", 6100);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            sc = new Scanner(System.in); 
            dataRecv();
            dataSend();
            
            
        } catch (UnknownHostException e) {
            System.out.println("서버를 찾을 수 없습니다: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("서버에 연결할 수 없습니다: " + e.getMessage());
        }
    }

    private void dataRecv() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	
                    while (true) {
                        String msg = reader.readLine();
                        if (msg.equals("나가기")) {
                            System.out.println("나가기.");
                            socket.close();
                            System.exit(0);
                        }
                      
                        System.out.println("Client: " + msg);

                      
                    }
                } catch (IOException e) {
                    System.out.println("나가기");
                    System.exit(0);
                }
            }
        });

        t.start();
    }

    private void dataSend() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String sendMsg = sc.nextLine();
                        writer.write(sendMsg + "\n"); // `\n` 필수 (BufferedReader의 `readLine()`이 정상 동작하게 함)
                        writer.flush(); // flush() 호출하여 데이터 즉시 전송
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }



      
  

    private void closeResources() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (sc != null) sc.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("자원 해제 중 오류 발생: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Client4();
    }
}
