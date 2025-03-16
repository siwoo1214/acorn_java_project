package Tosstest;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Tosstest.GUI.PointExchangePanel;

import java.util.Random;

public class EventManage {

	private int point;
	public boolean easyCompleted = false;
	public boolean hardCompleted = false;
	private PointExchangePanel pointExchangePanel;

	public EventManage(PointExchangePanel panel) {
		this.pointExchangePanel = panel;
	}

	private String[][] easyQuizzes = { { "은행에 돈을 맡기면 받을 수 있는 이자는?", "① 환율", "② 금리", "③ 배당금", "④ 세금", "2" },
			{ "은행에서 돈을 빌릴 때 내야 하는 추가 비용은?", "① 원금", "② 이자", "③ 환율", "④ 수수료", "2" },
			{ "한 국가의 화폐 가치가 낮아지는 현상을 무엇이라고 할까요?", "① 디플레이션", "② 인플레이션", "③ 환율 상승", "④ 저축", "2" },
			{ "주식 시장에서 주가가 계속 하락할 때 사용하는 표현은?", "① 불마켓", "② 약세장", "③ 강세장", "④ 상승장", "2" },
			{ "신용카드를 사용하면 한 달 후에 청구되는 방식은?", "① 일시불", "② 할부", "③ 후불제", "④ 선불제", "3" } };

	private String[][] hardQuizzes = {
			{ "DSR (총부채원리금상환비율)은 무엇을 의미할까요?", "① 대출자의 총 부채를 연소득과 비교한 비율", "② 은행 예금 금리 변화율", "③ 환율 변동과 외환 보유량 변화",
					"④ 기업 법인세 비율", "1" },
			{ "채권과 주식의 가장 큰 차이점은?", "① 채권은 회사 소유권, 주식은 회사 부채", "② 채권은 고정 이자, 주식은 변동 배당", "③ 주식은 은행만 발행 가능",
					"④ 둘 다 자동 현금 변환", "2" },
			{ "ETF(상장지수펀드)는 어떤 금융 상품인가요?", "① 개별 주식", "② 여러 주식을 한꺼번에 묶은 펀드", "③ 외환 거래", "④ 대출 상품", "2" },
			{ "국제 환율 변동이 가장 큰 영향을 미치는 것은?", "① 국내 부동산 가격", "② 수출 및 수입 가격", "③ 지역 세금 정책", "④ 은행 이자율", "2" },
			{ "가상화폐(암호화폐)는 중앙은행이 발행하는 화폐인가요?", "① 예", "② 아니오", "2" } };

	public void startEasyQuiz() {
		if (easyCompleted) {
			JOptionPane.showMessageDialog(null, "쉬운 문제는 이미 완료하셨습니다.");
			return;
		}

		Random rand = new Random();
		int index = rand.nextInt(easyQuizzes.length);

		String[] quiz = easyQuizzes[index];
		String question = quiz[0] + "\n" + quiz[1] + "\n" + quiz[2] + "\n" + quiz[3] + "\n" + quiz[4];

		String answer = JOptionPane.showInputDialog(null, question, "쉬운 문제", JOptionPane.QUESTION_MESSAGE);

		if (answer != null && answer.equals(quiz[5])) {
			// ✅ 포인트를 직접 계좌에 반영
			if (Main.AccountIn != null) {
				Main.AccountIn.setPoint(Main.AccountIn.getPoint() + 100);
				JOptionPane.showMessageDialog(null, "정답입니다! +100P");
			}
		} else {
			if (Main.AccountIn != null) {
				Main.AccountIn.setPoint(Main.AccountIn.getPoint() + 50);
				JOptionPane.showMessageDialog(null, "틀렸습니다. +50P");
			}
		}

		easyCompleted = true;
		updatePointDisplay();
	}

	// ✅ 어려운 문제 시작
	public void startHardQuiz() {
		if (hardCompleted) {
			JOptionPane.showMessageDialog(null, "어려운 문제는 이미 완료하셨습니다.");
			return;
		}

		Random rand = new Random();
		int index = rand.nextInt(hardQuizzes.length);

		String[] quiz = hardQuizzes[index];
		String question = quiz[0] + "\n" + quiz[1] + "\n" + quiz[2] + "\n" + quiz[3] + "\n" + quiz[4];

		String answer = JOptionPane.showInputDialog(null, question, "어려운 문제", JOptionPane.QUESTION_MESSAGE);

		if (answer != null && answer.equals(quiz[5])) {
			if (Main.AccountIn != null) {
				Main.AccountIn.setPoint(Main.AccountIn.getPoint() + 500);
				JOptionPane.showMessageDialog(null, "정답입니다! +500P");
			}
		} else {
			if (Main.AccountIn != null) {
				Main.AccountIn.setPoint(Main.AccountIn.getPoint() + 50);
				JOptionPane.showMessageDialog(null, "틀렸습니다. +50P");
			}
		}

		hardCompleted = true;
		updatePointDisplay();
	}

	// ✅ 포인트 환전 기능 추가 완료
	public void exchangePoint() {
		if (point < 10000) {
			JOptionPane.showMessageDialog(null, "10000포인트 이상만 환전 가능합니다.");
			return;
		}

		String input = JOptionPane.showInputDialog("환전할 포인트를 입력하세요 (1000포인트 단위):");

		try {
			int exchangeAmount = Integer.parseInt(input);

			if (exchangeAmount % 1000 != 0 || exchangeAmount > point) {
				JOptionPane.showMessageDialog(null, "잘못된 값입니다.");
				return;
			}

			point -= exchangeAmount;

			// ✅ 현재 연결된 계좌의 잔액에 추가
			if (Main.AccountIn != null) {
				Main.AccountIn.setBalance(Main.AccountIn.getBalance() + exchangeAmount);
				JOptionPane.showMessageDialog(null, "포인트 환전 성공! +" + exchangeAmount + "원");
			} else {
				JOptionPane.showMessageDialog(null, "연결된 계좌가 없습니다.");
			}

			// ✅ 포인트 상태 즉시 반영
			updatePointDisplay();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "숫자를 입력하세요.");
		}
	}

	private void updatePointDisplay() {
		if (pointExchangePanel != null && Main.AccountIn != null) {
			pointExchangePanel.updatePointLabel(Main.AccountIn.getPoint());

			// ✅ 상태 강제 갱신 → repaint(), revalidate() 호출
			pointExchangePanel.repaint();
			pointExchangePanel.revalidate();

			// ✅ UI 갱신을 보장하기 위해 invokeLater() 호출
			SwingUtilities.invokeLater(() -> {
				pointExchangePanel.updateUI();
			});
		}
	}

	public int getPoint() {
		return point;
	}
}
