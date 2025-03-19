package Tosstest.GUI;

import Tosstest.Main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class LoginFrame extends JFrame {

	public LoginFrame() {
		Main.closeCurrentFrame(); // ✅ 기존 창 닫기
		Main.currentFrame = this; // ✅ 현재 프레임 설정

		setTitle("로그인");
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);
		
		JLabel idLabel = new JLabel("아이디 :");
		idLabel.setBounds(20, 30, 80, 25);
		add(idLabel);

		JTextField idField = new JTextField();
		idField.setBounds(100, 30, 220, 25);
		add(idField);

		JLabel passwordLabel = new JLabel("비밀번호 :");
		passwordLabel.setBounds(20, 70, 80, 25);
		add(passwordLabel);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(100, 70, 220, 25);
		add(passwordField);

		JButton loginButton = new JButton("로그인");
		loginButton.setBounds(100, 110, 100, 30);
		add(loginButton);

		JButton signupButton = new JButton("회원가입");
		signupButton.setBounds(220, 110, 100, 30);
		add(signupButton);

		JButton exitButton = new JButton("프로그램 종료");
		exitButton.setBounds(100, 150, 220, 30);
		add(exitButton);

		loginButton.addActionListener(e -> {
			String id = idField.getText();
			String password = new String(passwordField.getPassword());

			if (Main.login(id, password)) {
				JOptionPane.showMessageDialog(null, "로그인 성공");
				new AccountFrame(); // ✅ 새로운 프레임 실행
				dispose(); // ✅ 현재 프레임 닫기
			} else {
				JOptionPane.showMessageDialog(null, "로그인 실패");
			}
		});

		signupButton.addActionListener(e -> {
			new SignupFrame(); // ✅ 새로운 프레임 실행
			dispose();
		});

		exitButton.addActionListener(e -> System.exit(0));
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
