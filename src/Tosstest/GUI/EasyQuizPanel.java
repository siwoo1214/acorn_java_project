package Tosstest.GUI;

import Tosstest.EventManage;
import Tosstest.GUI.BankFrame;

import javax.swing.*;
import java.awt.*;

public class EasyQuizPanel extends JPanel {

	private EventManage eventManage;
	private JButton startQuizButton;

	public EasyQuizPanel(EventManage eventManage) {
		this.eventManage = eventManage;

		setLayout(new BorderLayout());

		startQuizButton = new JButton("쉬운 문제 시작");
		startQuizButton.addActionListener(e -> {
			if (!eventManage.easyCompleted) {
				eventManage.startEasyQuiz();
			} else {
				JOptionPane.showMessageDialog(this, "쉬운 문제는 이미 완료하셨습니다.");
			}
		});

		add(startQuizButton, BorderLayout.CENTER);

		// ✅ 뒤로가기 버튼 복구
		JButton backButton = new JButton("뒤로가기");
		backButton.addActionListener(e -> {
			new BankFrame(); // ✅ BankFrame으로 돌아가기
			SwingUtilities.getWindowAncestor(this).dispose();
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(backButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}
}
