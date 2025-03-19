package 건강관리프로그램;

import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	// 실수값 입력 예외 처리1700
	public static double getValidDoubleInput(String prompt) {
		
		while (true) {
			try {
				System.out.print(prompt);
				double input = sc.nextDouble();
				if (input <= 0) {
					System.out.println("올바른 값을 입력해주세요.");
				} else {
					return input;
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
				sc.next();
			}
		}
	}

	public static void main(String[] args) {

		BodyAnalysis ba;

		System.out.println("======= 안녕하세요! 건강관리 프로그램입니다. =======");

		System.out.println("서비스를 이용하시기 전 회원 정보를 먼저 등록하겠습니다.\n");

		// 사용자 기본 정보 입력
		double height = getValidDoubleInput("키를 입력하세요 (cm): ");
		double weight = getValidDoubleInput("몸무게를 입력하세요(kg): ");
		int age = (int) getValidDoubleInput("나이를 입력하세요: ");

		String gender;

		while (true) {
			System.out.print("성별을 입력해주세요. (남성/여성): ");
			gender = sc.next();
			if (gender.equals("남성") || gender.equals("남")) {
				break;
			} else if (gender.equals("여성") || gender.equals("여")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다. 성별을 입력해주세요.");
			}
		}

		// user 객체 생성
		User user = new User(height, weight, age, gender);

		// Sug_Workout 객체를 미리 초기화합니다.
		// (임시로 ba는 null, YorN은 빈 문자열로 초기화)
		Sug_Workout sw = new Sug_Workout(user, null, ""); // 운동 목적별 운동 추천 선택값 가져오기 용 호출

		// 인바디 정보 입력 여부 확인
		System.out.println("골격근량과 체지방량을 알고 계신가요? Y/N");
		String YorN = sc.next();
		while (true) {
			if (YorN.equals("Y") || YorN.equals("y")) { // 골격근량, 체지방량 알아
				sw.setYorN("Y");
				// 체지방률, 골격근량 입력받기
				double bfm = getValidDoubleInput("체지방량을 입력하세요(kg): ");
				double smm = getValidDoubleInput("골격근량을 입력하세요(kg): ");

				ba = new BodyAnalysis(user, bfm, smm);

				break;

			} else if (YorN.equals("N") || YorN.equals("n")) { // 골격근량, 체지방량 몰라
				sw.setYorN("N");
				ba = new BodyAnalysis(user, 0, 0); // 인바디 정보가 없을 시 0으로 초기화
				break;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요");
				YorN = sc.next();

			}
		}

		System.out.println("\n회원 정보가 등록되었습니다.");
		System.out.println("다음 서비스를 이용하실 수 있습니다.\n");

		// userManager 객체 생성
		UserManager um = new UserManager(user, ba);
		
		//menu 객체 생성
		Menu menu = new Menu(um, ba, user);
		menu.menu();

	}

}
