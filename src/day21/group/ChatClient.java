package day21.group;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements Runnable, ActionListener {

	private DataInputStream in;
	private DataOutputStream out;
	private JTextArea output;
	private JTextField input;
	private JLabel label;
	private Thread t;
	private String server;
	
	
	public ChatClient(String server) {
		super("Java 채팅");
		this.server=server;
		t= new Thread(this);
		t.start();
		
		output= new JTextArea();
		JScrollPane pane = new JScrollPane(output);
		label = new JLabel("사용자이름");
		input = new JTextField();
		
		output.setEditable(false);
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		p.add(label, "West");
		p.add(input, "Center");
		
		getContentPane().add(pane,"Center");
		getContentPane().add(p, "South");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(500,400);
		setVisible(true);
		
		input.addActionListener(this); 
		
		
	}
	
	public void run() {
		
		try {
			Socket s = new Socket(server, 6100);
			in = new DataInputStream(  s.getInputStream());
			out= new DataOutputStream( s.getOutputStream());
			
			while(true)
			{
				String line = in.readUTF();
				output.append(line+ "\n");
				
			}
			}catch(Exception e) {
				//e.printStackTrace();
				System.out.println("연결이 종료");
			}
	}
	
	//엔터키에 대한 이벤트 처리 부분
	public void actionPerformed(ActionEvent e) {
		label.setText("메시지");
		try {
			out.writeUTF(input.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		input.setText("");
	}
	
	public static void main(String[] args) {
		new ChatClient("192.168.100.40");
	}
	
	
}
