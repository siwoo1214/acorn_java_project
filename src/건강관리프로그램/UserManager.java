package 건강관리프로그램;

import java.util.Scanner;

//사용자관리하는 클래스 
public class UserManager {

	Scanner sc = new Scanner(System.in);

	User user;
	BodyAnalysis ba;

	public UserManager(User user, BodyAnalysis ba) {
		this.user = user;
		this.ba = ba;
	}

	// 유효한 입력값(정수 입력)을 받을 때까지 반복하는 매서드
	public double getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				double input = sc.nextDouble();
				if (input >= 0)
					return input;
				System.out.println("올바른 값을 입력해주세요.");
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
				sc.next();
			}
		}
	}

	// 사용자 정보 출력
	public void view() {
		System.out.println("키: " + user.height + "cm\n" + 
						   "몸무게: " + user.weight + "kg\n" + 
						   "나이: " + user.age + "세\n" + 
						   "성별: " + user.gender);
		if (ba.bfm > 0 && ba.smm > 0)
			System.out.println("체지방률: " + ba.bfm + "kg\n" + "골격근량: " + ba.smm + "kg");
	}

	// 사용자 정보 수정
	public void update() {
		loop: while (true) {
			System.out.println("\n수정하려는 정보를 선택해주세요");
			int choice = (int) getInput("1.키, 2.몸무게, 3.나이, 4.성별, 5.체지방량, 6.골격근량, 0.수정완료\n");
			switch (choice) {
			case 1:
				user.height = getInput("변경할 키: ");
				break;
			case 2:
				user.weight = getInput("변경할 몸무게: ");
				break;
			case 3:
				user.age = (int) getInput("변경할 나이: ");
				break;
			case 4:
				while (true) {
					System.out.print("변경할 성별: ");
					user.gender = sc.next();
					if (user.gender.equals("남성") || user.gender.equals("남")) {
						break;
					} else if (user.gender.equals("여성") || user.gender.equals("여")) {
						break;
					} else {
						System.out.println("잘못된 입력입니다. 성별을 입력해주세요.");
					}
				}
				break;
			case 5:
				if (ba.bfm == 0) {
					System.out.println("체지방량에 대한 정보가 없습니다");
				} else {
					ba.bfm = getInput("변경할 체지방량: ");
				}
				break;
			case 6:
				if (ba.smm == 0) {
					System.out.println("골격근량에 대한 정보가 없습니다");
				} else {
					ba.smm = getInput("변경할 골격근량: ");
				}
				break;
			case 0:
				System.out.println("수정이 완료되었습니다");
				break loop;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력해주세요");
				break;
			}
		}

	}

	// 사용자 인바디 정보 추가
	public void addInfo() {
		while (true) {
			System.out.println("체지방량과 골격근량을 입력해주세요(취소: 0)");
			ba.bfm = getInput("체지방량을 입력하세요(kg): ");
			if (ba.bfm == 0) {
				System.out.println("입력을 취소합니다");
				break;
			}
			ba.smm = getInput("골격근량을 입력하세요(kg): ");
			if (ba.bfm < 0 || ba.smm < 0) {
				System.out.println("다시 입력해주세요");
				continue;
			} else {
				System.out.println("입력이 완료되었습니다");
				break;
			}
		}

	}

	// 관리 메뉴
	public void manager() {

		loop: while (true) {
			System.out.println("\n메뉴를 선택해주세요");
			int choice = (int) getInput("1.회원정보 확인, 2.회원정보 수정, 3.인바디정보 추가, 0.메인메뉴로 돌아가기\n");
			switch (choice) {
			case 1:
				view();
				break;
			case 2:
				update();
				break;
			case 3:
				addInfo();
				break;
			case 0:
				System.out.println("뒤로 돌아갑니다");
				break loop;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력해주세요");
				break;
			}
		}
	}
}
