package day21.chatt.실습;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient {
    private BufferedReader in; // in : 유저가 보낸 데이터를 서버에 저장
    private PrintWriter out; //  out : 서버에서 유저에게 데이터를 보내는 통로

    //스윙 채팅창 간단하게 구현
    private JFrame frame = new JFrame("채팅 클라이언트");
    private JTextField messageField = new JTextField(40);
    private JTextArea messageArea = new JTextArea(8, 40);
    private String userName;

    public ChatClient() {
        // 사용자 이름 입력
        userName = JOptionPane.showInputDialog(
            frame,
            "채팅에서 사용할 이름을 입력하세요:",
            "사용할 이름",
            JOptionPane.PLAIN_MESSAGE
        );

        // GUI 설정
        messageField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(messageField, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(messageArea), BorderLayout.CENTER);
        frame.pack();

        // 메시지 전송을 위한 이벤트 리스너
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(userName + ": " + messageField.getText());
                messageField.setText("");
            }
        });
    }
    // IOException 던져주기
    private void run() throws IOException {
        try {
            // 소켓에다가 port 번호 임의 지정 9999
            Socket socket = new Socket("192.168.100.10", 9999);
            // 1. new BufferedReader
            //   → 문자 데이터를 읽기 위한 버퍼 추가

            // 2. new InputStreamReader(...)
            //   → 데이터를 문자 단위로 변환

            // 3. socket.getInputStream()
            //   → 소켓으로부터 데이터를 받는 통로 생성
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 소켓(서버)에 저장한 데이터를 밖으로 보내는 outputStream() 통로 생성
            out = new PrintWriter(socket.getOutputStream(), true);

            // 메시지 수신을 위한 쓰레드 (람다식 사용)
            new Thread(() -> {
                String message;
                try {
                    // 메세지에 값이 널이 아닌경우
                    while ((message = in.readLine()) != null) {
                        // 보낸 메세지는 수정이 불가능 하게 변수
                        // 변하지 않는 절대상수로 변환
                        final String finalMessage = message;

                        // 스윙에다가 보내 메세지 표시하기
                        SwingUtilities.invokeLater(() -> {
                            messageArea.append(finalMessage + "\n");
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            messageField.setEditable(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "서버 연결 실패: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
} 