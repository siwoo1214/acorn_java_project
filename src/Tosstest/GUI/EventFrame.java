package Tosstest.GUI;

import Tosstest.EventManage;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class EventFrame extends JFrame {
	private JButton joinButton;
	private JButton managePointsButton;
	private JButton closeButton;

	public EventFrame() {
		setTitle("이벤트 및 포인트 관리");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);
		
		joinButton = new JButton("이벤트 참여");
		joinButton.setBounds(100, 50, 200, 30);
		add(joinButton);

		managePointsButton = new JButton("포인트 관리");
		managePointsButton.setBounds(100, 90, 200, 30);
		add(managePointsButton);

		JButton backButton = new JButton("뒤로가기");
		closeButton.setBounds(100, 130, 200, 30);
		add(closeButton);
		/*
		 * // ✅ 이벤트 참여 버튼 수정 joinButton.addActionListener(e -> { try { new
		 * EventManage().participateEvent(); JOptionPane.showMessageDialog(null,
		 * "이벤트 참여 완료!"); } catch (Exception ignored) {} });
		 * 
		 * // ✅ 포인트 관리 버튼 수정 managePointsButton.addActionListener(e -> { try { new
		 * EventManage().managePoints(); JOptionPane.showMessageDialog(null,
		 * "포인트 관리 완료!"); } catch (Exception ignored) {} });
		 * 
		 */
		backButton.addActionListener(e -> {
			try {
				new AccountFrame();
				dispose();
			} catch (Exception ignored) {
			}
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
