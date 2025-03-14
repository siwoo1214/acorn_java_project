package Tosstest.GUI;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

import Toss.Main;

import java.awt.Image;
import java.awt.Toolkit;

public class BankFrame extends JFrame {

	public BankFrame() {
		setTitle("입출력 계좌 메뉴");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);

		JButton transactionButton = new JButton("송금/인출");
		transactionButton.setBounds(100, 50, 200, 30);
		add(transactionButton);

		JButton historyButton = new JButton("송금 및 인출 내역");
		historyButton.setBounds(100, 90, 200, 30);
		add(historyButton);

		JButton eventButton = new JButton("이벤트");
		eventButton.setBounds(100, 130, 200, 30);
		add(eventButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(100, 170, 200, 30);
		add(backButton);

		// ✅ 송금/인출 버튼 수정
		transactionButton.addActionListener(e -> {
			try {
				new TransactionFrame(); // ✅ 송금/인출 기능 연결
				dispose();
			} catch (Exception ignored) {
			}
		});

		// ✅ 송금 내역 버튼 수정
		historyButton.addActionListener(e -> {
			try {
				new HistoryFrame();
				dispose();
			} catch (Exception ignored) {
			}
		});

		// ✅ 이벤트 버튼 수정
		eventButton.addActionListener(e -> {
			try {
				new EventFrame();
				dispose();
			} catch (Exception ignored) {
			}
		});

		// ✅ 뒤로가기 버튼 수정
		backButton.addActionListener(e -> {
			try {
				Main.AccountIn = null;
				new AccountFrame();
				dispose();
			} catch (Exception ignored) {
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
