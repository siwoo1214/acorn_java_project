package Tosstest;

import java.util.ArrayList;
import java.util.Scanner;

public class EventManage {
	Scanner sc = new Scanner(System.in);
	int point = 10000;
	boolean checkin = false;
	private String[][] easyQuizzes = { { "은행에 돈을 맡기면 받을 수 있는 이자는?", "① 환율", "② 금리", "③ 배당금", "④ 세금", "2" },
			{ "은행에서 돈을 빌릴 때 내야 하는 추가 비용은?", "① 원금", "② 이자", "③ 환율", "④ 수수료", "2" },
			{ "한 국가의 화폐 가치가 낮아지는 현상을 무엇이라고 할까요?", "① 디플레이션", "② 인플레이션", "③ 환율 상승", "④ 저축", "2" },
			{ "주식 시장에서 주가가 계속 하락할 때 사용하는 표현은?", "① 불마켓", "② 약세장", "③ 강세장", "④ 상승장", "2" },
			{ "신용카드를 사용하면 한 달 후에 청구되는 방식은?", "① 일시불", "② 할부", "③ 후불제", "④ 선불제", "3" },
			{ "은행에서 고객이 돈을 보관하고 관리할 수 있도록 제공하는 서비스는?", "① 예금", "② 대출", "③ 주식", "④ 투자", "1" },
			{ "다음 중 신용 점수를 높이는 방법이 아닌 것은?", "① 대출을 제때 상환하기",

					"② 신용카드 연체 없이 사용하기", "③ 여러 개의 대출을 동시에 받기", "④ 공과금을 성실히 납부하기", "3" },
			{ "ATM에서 현금을 출금할 때 발생할 수 있는 추가 비용은?", "① 인출 수수료", "② 카드 연회비", "③ 환율 변동", "④ 대출 이자", "1" },
			{ "연말정산을 할 때 받을 수 있는 세금 혜택을 무엇이라고 할까요?", "① 소득공제", "② 부가세", "③ 고정지출", "④ 이자소득세", "1" },
			{ "체크카드와 신용카드의 가장 큰 차이점은?", "① 사용 가능 은행의 개수", "② 연회비 부담 여부", "③ 결제 방식 (즉시 vs 후불)", "④ 카드 발급 서류", "3" } };
	private String[][] hardQuizzes = {
			{ "DSR (총부채원리금상환비율)은 무엇을 의미할까요?", "① 대출자의 총 부채를 연소득과 비교한 비율", "② 은행 예금 금리 변화율", "③ 환율 변동과 외환 보유량 변화",
					"④ 기업 법인세 비율", "1" },
			{ "채권과 주식의 가장 큰 차이점은?", "① 채권은 회사 소유권, 주식은 회사 부채", "② 채권은 고정 이자, 주식은 변동 배당", "③ 주식은 은행만 발행 가능",
					"④ 둘 다 자동 현금 변환", "2" },
			{ "ETF(상장지수펀드)는 어떤 금융 상품인가요?", "① 개별 주식", "② 여러 주식을 한꺼번에 묶은 펀드", "③ 외환 거래", "④ 대출 상품", "2" },
			{ "국제 환율 변동이 가장 큰 영향을 미치는 것은?", "① 국내 부동산 가격", "② 수출 및 수입 가격", "③ 지역 세금 정책", "④ 은행 이자율", "2" },
			{ "가상화폐(암호화폐)는 중앙은행이 발행하는 화폐인가요?", "① 예", "② 아니오", "2" } };
	boolean easy = false;
	boolean hard = false;

	public EventManage() {

	}

	public void Event() {
		System.out.println("메뉴를 선택하세요: 1. 이벤트 참여 2. 포인트 관리 3. 이벤트 페이지 종료");
		int select = sc.nextInt();

		switch (select) {
		case 1:
			EventSelect();
			break;
		case 2:
			PointManager();
			break;
		case 3:
			System.out.println("이벤트 페이지를 종료합니다.");
			return;
		default:
			System.out.println("입력이 잘못됐습니다.");
			return;
		}
	}

	public void EventSelect() {
		System.out.println("참여할 이벤트를 선택하세요: 1. 출석 체크 2. 퀴즈 3. 돌아가기");
		int select = sc.nextInt();

		switch (select) {
		case 1:
			if (!checkin) {
				point += 100;
				System.out.println("출석 체크가 완료됐습니다! (100P 획득, 현재 포인트: " + point + "P)");
				checkin = true;
			} else {
				System.out.println("이미 출석 체크를 완료했습니다.");
				return;
			}
			break;

		case 2:
			QuizEvent();
			break;

		case 3:
			return;

		default:
			System.out.println("입력이 잘못됐습니다.");
			return;
		}
	}

	public void QuizEvent() {
		System.out.println("퀴즈 난이도를 선택하세요: 1.쉬움 2.어려움 3.돌아가기");
		int select = sc.nextInt();

		switch (select) {
		case 1:
			if (!easy) {
				EasyQuizList();
				easy = true;
				return;
			} else {
				System.out.println("퀴즈를 이미 풀었습니다.");
				return;
			}

		case 2:
			if (!hard) {
				HardQuizList();
				hard = true;
				return;
			} else {
				System.out.println("퀴즈를 이미 풀었습니다.");
				return;
			}

		case 3:
			return;

		default:
			System.out.println("입력이 잘못됐습니다.");
			return;
		}
	}

	public void EasyQuizList() {
		int index = (int) (Math.random() * easyQuizzes.length);

		System.out.println("문제: " + easyQuizzes[index][0]);
		for (int i = 1; i < 5; i++) {
			System.out.println(easyQuizzes[index][i]);
		}
		System.out.println("이 문제의 정답은 몇 번일까요?");

		String choice = sc.next();
		if (choice.equals(easyQuizzes[index][5])) {
			System.out.println("정답입니다! (100P 획득)");
			point += 100;
		} else {
			System.out.println("오답입니다. 다음 기회를 노려보세요! (50P 획득, 정답: " + easyQuizzes[index][5] + "번)");
			point += 50;
		}
	}

	public void HardQuizList() {
		int index = (int) (Math.random() * hardQuizzes.length);

		System.out.println("문제: " + hardQuizzes[index][0]);
		for (int i = 1; i < 5; i++) {
			System.out.println(hardQuizzes[index][i]);
		}
		System.out.println("이 문제의 정답은 몇 번일까요?");

		String choice = sc.next();
		if (choice.equals(hardQuizzes[index][5])) {
			System.out.println("정답입니다! (500P 획득)");
			point += 500;
		} else {
			System.out.println("오답입니다. 다음 기회를 노려보세요! (50P 획득, 정답: " + hardQuizzes[index][5] + "번)");
			point += 50;
		}
	}

	public void PointManager() {
		System.out.println("현재 포인트: " + point + "P");

		if (point >= 10000) {
			System.out.println("환전할 포인트를 입력하세요(1000P 단위로 환전 가능): ");
			int amount = sc.nextInt();
			sc.nextLine();

			if (amount % 1000 == 0 && amount <= point) {
				point -= amount;

				if (Main.accounts.isEmpty()) {
					System.out.println("연동된 계좌가 없습니다. 먼저 계좌를 개설하세요.");
					point += amount;
					return;
				}

				System.out.println("포인트를 입금할 계좌를 선택하세요:");
				for (int i = 0; i < Main.accounts.size(); i++) {
					System.out.println((i + 1) + ". " + Main.accounts.get(i));
				}

				int select = sc.nextInt();
				sc.nextLine();

				if (select < 1 || select > Main.accounts.size()) {
					System.out.println("잘못된 입력입니다. 다시 시도하세요.");
					point += amount;
					return;
				}

				Account selectedAccount = Main.accounts.get(select - 1);
				selectedAccount.setBalance(selectedAccount.getBalance() + amount);

				System.out.println("포인트 환전이 완료됐습니다! (현재 포인트: " + point + "P)");
				System.out.println("[" + selectedAccount.getBankname() + "] " + selectedAccount.getAccountnum()
						+ " 계좌에 " + amount + "원이 입금되었습니다.");
			} else {
				System.out.println("입력이 올바르지 않습니다. 1000P 단위로 환전 가능합니다.");
			}
		} else {
			System.out.println("10000P 이상부터 환전 가능합니다.");
		}
	}
}
