package Tosstest.GUI;

import Tosstest.Main;
import Tosstest.GUI.BankFrame;

import javax.swing.*;
import java.awt.*;

public class PointExchangePanel extends JPanel {

	private JLabel pointLabel;

	public PointExchangePanel() {
		setLayout(new BorderLayout());

		// ✅ 현재 계좌의 포인트 상태 표시
		if (Main.AccountIn != null && Main.AccountIn.getAccountType().equals("입출금 계좌")) {
			pointLabel = new JLabel("현재 포인트: " + Main.AccountIn.getPoint() + "P");
			add(pointLabel, BorderLayout.NORTH);

			JButton exchangeButton = new JButton("포인트 환전");
			exchangeButton.addActionListener(e -> {
				if (Main.AccountIn.getPoint() >= 1000) {
					Main.AccountIn.setPoint(Main.AccountIn.getPoint() - 1000);
					Main.AccountIn.setBalance(Main.AccountIn.getBalance() + 1000);
					updatePointLabel(Main.AccountIn.getPoint());
				} else {
					JOptionPane.showMessageDialog(this, "포인트가 부족합니다.");
				}
			});

			add(exchangeButton, BorderLayout.CENTER);

			// ✅ 상태 강제 갱신 → repaint(), revalidate() 호출
			this.repaint();
			this.revalidate();

			// ✅ UI 상태 즉시 반영 보장
			SwingUtilities.invokeLater(() -> {
				this.updateUI();
			});

		} else {
			pointLabel = new JLabel("포인트는 입출금 계좌에서만 사용 가능합니다.");
			add(pointLabel, BorderLayout.NORTH);
		}

		// ✅ 뒤로가기 버튼 복구
		JButton backButton = new JButton("뒤로가기");
		backButton.addActionListener(e -> {
			new BankFrame(); // ✅ BankFrame으로 돌아가기
			SwingUtilities.getWindowAncestor(this).dispose(); // ✅ 현재 패널 닫기
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(backButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	// ✅ 포인트 상태 즉시 반영 및 UI 상태 갱신 강화
	public void updatePointLabel(int newPoint) {
		pointLabel.setText("현재 포인트: " + newPoint + "P");

		// ✅ 상태 강제 갱신 → repaint(), revalidate() 호출
		this.repaint();
		this.revalidate();

		// ✅ UI 상태 즉시 반영 보장
		SwingUtilities.invokeLater(() -> {
			this.updateUI();
		});
	}

}
