package Tosstest.GUI;

import Tosstest.Account;
import Tosstest.AccountManager;
import Tosstest.Main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class AccountFrame extends JFrame {

	public AccountFrame() {
		setTitle("계좌 관리");
		setSize(400, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);

		JButton openButton = new JButton("계좌 개설");
		openButton.setBounds(100, 30, 200, 30);
		add(openButton);

		JButton connectButton = new JButton("계좌 연결");
		connectButton.setBounds(100, 70, 200, 30);
		add(connectButton);

		JButton deleteButton = new JButton("계좌 삭제");
		deleteButton.setBounds(100, 110, 200, 30);
		add(deleteButton);

		JButton updateButton = new JButton("계좌 정보 수정");
		updateButton.setBounds(100, 150, 200, 30);
		add(updateButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(100, 190, 200, 30);
		add(backButton);

		// ✅ 계좌 개설 수정
		openButton.addActionListener(e -> {
			new AccountManager().OpenedAccount();
			dispose();
			new AccountFrame();
		});

		AccountManager.setAccountInNull();
		
		// ✅ 계좌 연결 수정 → Main.AccountIn 값이 설정된 경우 처리
		connectButton.addActionListener(e -> {
			if (Main.AccountIn != null) {
				JOptionPane.showMessageDialog(null, "이미 계좌가 연동된 상태입니다.");
				return;
			}

			try {
				new AccountManager().ConnectAccount();

				// ✅ 연동 성공 시만 창 닫기
				if (Main.AccountIn != null) {
					dispose();
				}
			} catch (Exception ignored) {
			}
		});

		// ✅ 계좌 삭제 수정
		deleteButton.addActionListener(e -> {
			new AccountManager().CancelAccount();
			dispose();
			new AccountFrame();
		});

		// ✅ 계좌 정보 수정 수정
		updateButton.addActionListener(e -> {
			new AccountManager().UpdateAccount();
			dispose();
			new AccountFrame();
		});

		// ✅ 뒤로가기 버튼 수정
		backButton.addActionListener(e -> {
			new LoginFrame();
			dispose();
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
