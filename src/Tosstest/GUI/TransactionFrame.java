package Tosstest.GUI;

import Tosstest.Main;
import Tosstest.BankAccount;
import Tosstest.History;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class TransactionFrame extends JFrame {

	public TransactionFrame() {
		setTitle("송금 및 인출");
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);

		JButton sendButton = new JButton("송금");
		sendButton.setBounds(100, 30, 200, 30);
		add(sendButton);

		JButton withdrawButton = new JButton("인출");
		withdrawButton.setBounds(100, 70, 200, 30);
		add(withdrawButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(100, 110, 200, 30);
		add(backButton);

		// ✅ 송금 버튼 수정
		sendButton.addActionListener(e -> {
			if (Main.AccountIn == null) {
				JOptionPane.showMessageDialog(null, "연동된 계좌가 없습니다.");
				return;
			}

			// ✅ 받는 사람 이름 입력
			String receiverName = JOptionPane.showInputDialog("받는 사람 이름:");
			if (receiverName == null || receiverName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "송금이 취소되었습니다.");
				return;
			}

			// ✅ 받는 사람 계좌 입력
			String receiverAccountNumStr = JOptionPane.showInputDialog("받는 사람 계좌번호:");
			if (receiverAccountNumStr == null || receiverAccountNumStr.isEmpty()) {
				JOptionPane.showMessageDialog(null, "송금이 취소되었습니다.");
				return;
			}

			long receiverAccountNum;
			try {
				receiverAccountNum = Long.parseLong(receiverAccountNumStr);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "계좌번호는 숫자만 입력 가능합니다.");
				return;
			}

			// ✅ 송금 금액 입력
			String amountStr = JOptionPane.showInputDialog("송금할 금액:");
			if (amountStr == null || amountStr.isEmpty()) {
				JOptionPane.showMessageDialog(null, "송금이 취소되었습니다.");
				return;
			}

			int amount;
			try {
				amount = Integer.parseInt(amountStr);
				if (amount <= 0) {
					JOptionPane.showMessageDialog(null, "금액은 0보다 커야 합니다.");
					return;
				}
				if (amount > Main.AccountIn.getBalance()) {
					JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "금액은 숫자만 입력 가능합니다.");
				return;
			}

			if (Main.AccountIn instanceof BankAccount) {
				((BankAccount) Main.AccountIn).deposit(receiverName, receiverAccountNum, amount);

				JOptionPane.showMessageDialog(null, "송금이 완료되었습니다.");
				dispose();
				new BankFrame(); // ✅ 작업 후 BankFrame으로 복귀
			} else {
				JOptionPane.showMessageDialog(null, "연동된 계좌가 입출금 계좌가 아닙니다.");
			}

		});

		// ✅ 인출 버튼 수정
		withdrawButton.addActionListener(e -> {
			if (Main.AccountIn == null) {
				JOptionPane.showMessageDialog(null, "연동된 계좌가 없습니다.");
				return;
			}

			// ✅ 인출 금액 입력
			String amountStr = JOptionPane.showInputDialog("인출할 금액:");
			if (amountStr == null || amountStr.isEmpty()) {
				JOptionPane.showMessageDialog(null, "인출이 취소되었습니다.");
				return;
			}

			int amount;
			try {
				amount = Integer.parseInt(amountStr);
				if (amount <= 0) {
					JOptionPane.showMessageDialog(null, "금액은 0보다 커야 합니다.");
					return;
				}
				if (amount > Main.AccountIn.getBalance()) {
					JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "금액은 숫자만 입력 가능합니다.");
				return;
			}

			if (Main.AccountIn instanceof BankAccount) {
				((BankAccount) Main.AccountIn).withdraw(amount);

				// ✅ History 저장

				JOptionPane.showMessageDialog(null, "인출이 완료되었습니다.");
				dispose();
				new BankFrame(); // ✅ 작업 후 BankFrame으로 복귀
			} else {
				JOptionPane.showMessageDialog(null, "연동된 계좌가 입출금 계좌가 아닙니다.");
			}

		});

		// ✅ 뒤로가기 버튼 수정
		backButton.addActionListener(e -> {
			new BankFrame();
			dispose();
		});

		// ✅ 창 중앙 표시
		setLocationRelativeTo(null);

		setVisible(true);
	}
}
