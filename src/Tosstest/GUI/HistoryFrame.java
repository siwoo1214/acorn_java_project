package Tosstest.GUI;

import Tosstest.Main;
import Tosstest.History;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryFrame extends JFrame {

	public HistoryFrame() {
		setTitle("송금 및 인출 내역");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// ✅ 프로그램 아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res/toss.jpg");
		this.setIconImage(img);

		String[] columnNames = { "거래 유형", "송금자", "송금자 계좌", "수취인", "수취인 계좌", "금액", "시간" };

		// ✅ 테이블 모델 생성
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		// ✅ 내역 불러오기 추가
		Main.loadHistoriesFromFile();

		List<History> histories = Main.histories;
		for (History history : histories) {
			model.addRow(new Object[] { history.getTransactionType(), history.getSenderName(),
					history.getSenderAccountNum(), history.getReceiverName(), history.getReceiverAccountNum(),
					String.format("%,d원", history.getAmount()), history.getTransactionTime()
							.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) });
		}

		// ✅ JTable 생성 → 스크롤 가능하게 설정
		JTable table = new JTable(model);
		table.setRowHeight(25); // ✅ 행 높이 설정
		table.getTableHeader().setReorderingAllowed(false); // ✅ 열 이동 방지

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		// ✅ 뒤로가기 버튼 설정
		JButton backButton = new JButton("뒤로가기");
		backButton.setPreferredSize(new Dimension(100, 30));
		backButton.addActionListener(e -> {
			new BankFrame();
			dispose();
		});

		add(backButton, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
