package Tosstest.GUI;

import Tosstest.Main;
import Tosstest.User;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class SignupFrame extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JButton signupButton;
    private JButton backButton;

    public SignupFrame() {
        setTitle("회원가입");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);
        
        JLabel idLabel = new JLabel("아이디:");
        idLabel.setBounds(20, 30, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 30, 150, 25);
        add(idField);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setBounds(20, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 150, 25);
        add(passwordField);

        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setBounds(20, 110, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 110, 150, 25);
        add(nameField);

        signupButton = new JButton("가입하기");
        signupButton.setBounds(100, 150, 100, 30);
        add(signupButton);

        backButton = new JButton("뒤로가기");
        backButton.setBounds(220, 150, 100, 30);
        add(backButton);

        signupButton.addActionListener(e -> {
            try {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();

                if (!id.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
                    User newUser = new User(id, password, name);
                    Main.saveUserToFile(newUser);
                    JOptionPane.showMessageDialog(null, "회원가입 성공!");
                    new LoginFrame(); // ✅ 회원가입 후 로그인 창 이동
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.");
                }
            } catch (Exception ignored) {}
        });

        backButton.addActionListener(e -> {
            try {
                new LoginFrame(); 
                dispose();
            } catch (Exception ignored) {}
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
