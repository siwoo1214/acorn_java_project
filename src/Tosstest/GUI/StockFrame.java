package Tosstest.GUI;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

import Toss.Main;

public class StockFrame extends JFrame {

	public StockFrame() {
		setTitle("주식 계좌 메뉴");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);

		JButton buyButton = new JButton("주식 구매");
		buyButton.setBounds(100, 30, 200, 30);
		add(buyButton);

		JButton sellButton = new JButton("주식 판매");
		sellButton.setBounds(100, 70, 200, 30);
		add(sellButton);

		JButton viewButton = new JButton("주식 조회");
		viewButton.setBounds(100, 110, 200, 30);
		add(viewButton);

		JButton eventButton = new JButton("이벤트");
		eventButton.setBounds(100, 150, 200, 30);
		add(eventButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(100, 190, 200, 30);
		add(backButton);

		buyButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "주식 구매 기능 연결 예정");
		});

		sellButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "주식 판매 기능 연결 예정");
		});

		viewButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "주식 조회 기능 연결 예정");
		});

		eventButton.addActionListener(e -> {
			new EventFrame();
			dispose();
		});

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
