package day21.chatt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server4 {
    private ServerSocket serverSocket;   // 서버용 소켓
    private Socket clientSocket;         // 클라이언트와 통신할 소켓

    private BufferedReader bufferedReader; // 입력용 스트림
    private BufferedWriter bufferedWriter; // 출력용 스트림
    private Scanner sc = new Scanner(System.in);
    
    

    public Server4() {
        try {
            serverSocket = new ServerSocket(6100); // 포트번호
            System.out.println(" server create ");
            clientSocket = serverSocket.accept();  // 대기 상태 (Listen), 클라이언트 연결 시 통신 소켓 생성
            System.out.println("연결 성공");

            // 스트림 초기화
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String clientIp = clientSocket.getInetAddress().toString();
            System.out.println(clientIp);

            // 메시지 송수신 실행
            dataRecv();
            dataSend();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataRecv() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	
                    while (true) {
                        String msg = bufferedReader.readLine();
                        if (msg.equals("나가기")) {
                            System.out.println("나가기.");
                            clientSocket.close();
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
                        bufferedWriter.write(sendMsg + "\n"); // `\n` 필수 (BufferedReader의 `readLine()`이 정상 동작하게 함)
                        bufferedWriter.flush(); // flush() 호출하여 데이터 즉시 전송
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    public static void main(String[] args) {
        new Server4();
    }
}
