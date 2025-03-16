package Tosstest.GUI;

import javax.swing.*;

public class StockFrame extends JFrame {

	public StockFrame() {
		setTitle("주식 계좌 메뉴");
		setSize(400, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		JButton buyButton = new JButton("주식 구매");
		buyButton.setBounds(100, 30, 200, 30);
		add(buyButton);

		JButton sellButton = new JButton("주식 판매");
		sellButton.setBounds(100, 70, 200, 30);
		add(sellButton);

		JButton viewButton = new JButton("보유 주식 조회");
		viewButton.setBounds(100, 110, 200, 30);
		add(viewButton);

		JButton backButton = new JButton("뒤로가기");
		backButton.setBounds(100, 150, 200, 30);
		add(backButton);

		buyButton.addActionListener(e -> new StockPurchaseFrame());
		sellButton.addActionListener(e -> new StockSellFrame());
		viewButton.addActionListener(e -> new StockViewFrame());
		backButton.addActionListener(e -> {
			new AccountFrame();
			dispose();
		});

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
