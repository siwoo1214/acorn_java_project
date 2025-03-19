package Tosstest.GUI;

import Tosstest.StockManager;
import Tosstest.StockAccount;
import Tosstest.AccountManager;
import Tosstest.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StockPurchaseFrame extends JFrame {
	private DefaultTableModel tableModel;
	private JTable stockTable;
	private Timer updateTimer;
	private JLabel balanceLabel;
	private StockAccount userAccount;

	public StockPurchaseFrame() {
		this.userAccount = (StockAccount) Main.AccountIn;
		setTitle("주식 구매");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		setupBalanceLabel(); // ✅ 상단에 계좌 잔액 추가
		createStockTable();
		setupButtons();
		startPriceUpdate(); // ✅ 가격 변동 시작

		setLocationRelativeTo(null);
		setVisible(true);
	}

	// ✅ 상단에 계좌 잔액 표시
	private void setupBalanceLabel() {
		JPanel balancePanel = new JPanel();
		balanceLabel = new JLabel("잔액: " + userAccount.getBalance() + " 원");
		balancePanel.add(balanceLabel);
		add(balancePanel, BorderLayout.NORTH);
	}

	// ✅ JTable 생성 (보유 수량 & 매수가 추가)
	private void createStockTable() {
		String[] columnNames = { "주식 이름", "현재 가격", "보유 수량", "매수가" };
		tableModel = new DefaultTableModel(columnNames, 0);
		stockTable = new JTable(tableModel);

		updateTableData(); // ✅ UI 데이터 로드

		JScrollPane scrollPane = new JScrollPane(stockTable);
		add(scrollPane, BorderLayout.CENTER);
	}

	// ✅ UI 테이블 데이터 업데이트 (보유 수량 & 매수가 포함)
	private void updateTableData() {
		tableModel.setRowCount(0); // 기존 데이터 삭제
		ArrayList<String> stockNames = StockManager.getStockNames();
		ArrayList<Integer> stockPrices = StockManager.getStockPrices();

		for (int i = 0; i < stockNames.size(); i++) {
			int ownedQuantity = userAccount.getStockQuantity(stockNames.get(i));
			int purchasePrice = userAccount.getPurchasePrice(stockNames.get(i));

			tableModel.addRow(new Object[] { stockNames.get(i), stockPrices.get(i), ownedQuantity,
					(purchasePrice > 0) ? purchasePrice : "-" });
		}

		// ✅ 잔액 업데이트
		balanceLabel.setText("잔액: " + userAccount.getBalance() + " 원");
		Main.AccountIn.setBalance(userAccount.getBalance());
	}

	// ✅ 30초마다 가격 변동을 UI에 반영 (뒤로가기 시 중지됨)
	private void startPriceUpdate() {
		updateTimer = new Timer();
		updateTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(() -> updateTableData());
			}
		}, 30000, 30000);
	}

	// ✅ 뒤로가기 버튼을 누르면 가격 변동을 중지
	private void stopPriceUpdate() {
		if (updateTimer != null) {
			updateTimer.cancel();
			updateTimer.purge();
		}
	}

	// ✅ 버튼 설정
	private void setupButtons() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton buyButton = new JButton("구매");
		buyButton.setPreferredSize(new Dimension(120, 30));
		buyButton.addActionListener(e -> buyStock());

		JButton backButton = new JButton("뒤로가기");
		backButton.setPreferredSize(new Dimension(120, 30));
		backButton.addActionListener(e -> {
			stopPriceUpdate(); // ✅ 뒤로가기 버튼 클릭 시 Timer 중지
			dispose();
		});

		buttonPanel.add(buyButton);
		buttonPanel.add(backButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void buyStock() {
		int selectedRow = stockTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "구매할 주식을 선택하세요.");
			return;
		}

		String stockName = StockManager.getStockNames().get(selectedRow);
		int stockPrice = StockManager.getStockPrices().get(selectedRow);
		String quantityStr = JOptionPane.showInputDialog("구매 수량을 입력하세요:");

		if (quantityStr != null) {
			try {
				int quantity = Integer.parseInt(quantityStr);
				if (userAccount.buyStock(stockName, stockPrice, quantity)) {
					JOptionPane.showMessageDialog(this, stockName + " " + quantity + "주 구매 완료!");
					updateTableData(); // ✅ 상태 최신화
				} else {
					JOptionPane.showMessageDialog(this, "잔액이 부족합니다.");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "올바른 숫자를 입력하세요.");
			}
		}
	}
}
