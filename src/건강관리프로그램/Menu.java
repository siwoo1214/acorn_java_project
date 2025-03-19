package 건강관리프로그램;

import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner(System.in);

	UserManager um;
	BodyAnalysis ba;
	User user;
	Sug_Workout sw;
	WorkPlanManager wm;

	public Menu(UserManager um, BodyAnalysis ba, User user) {
		this.um = um;
		this.ba = ba;
		this.user = user;
	}

	// 메인메뉴
	public void menu() {
		loop: while (true) {
			System.out.println("1.신체 건강 파악하기 2.식단 관리하기 3.운동 관리하기 4.나의 정보 관리하기 5.종료");
			System.out.println("번호를 입력하여 메뉴를 선택해주세요");
			int option;
			
			try {
				option = sc.nextInt(); 
			}catch(Exception e){
				System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.\n");
				sc.next();
				continue;
			}
			
				switch (option) {
				case 1:
					op1();
					break;
				case 2:
					op2();
					break;
				case 3:
					op3();
					break;
				case 4:
					op4();
					break;
				case 5:
					System.out.println("프로그램을 종료합니다");
					break loop;
				default:
					System.out.println("잘못된 번호입니다. 다시 입력해주세요.\n");
				}
		}
	}

	// 유효한 입력값(정수 입력)을 받을 때까지 반복하는 매서드
		public int getInput(String prompt) {
			while (true) {
				try {
					System.out.print(prompt);
					int input = sc.nextInt();
					return input;
				} catch (Exception e) {
					System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
					sc.next();
				}
			}
		}

	// 신체 건강 파악 메뉴
	public void op1() {
		System.out.println("\n신체 건강 파악 서비스입니다.");
		loop: while (true) {
			int select = getInput("1.BMI 지수 알아보기 2.신체 평가 알아보기 0.메인메뉴로 돌아가기\n");
			switch (select) {
			case 1:
				double result = BMI.calcBMI(user.weight, user.height);
				System.out.printf("당신의 BMI 지수는: %.2f입니다.\n", result);
				BMI.printBMI(result);
				break;
			case 2:
				if ((ba.bfm > 0) && (ba.smm > 0)) {
					System.out.println(ba.bodyInfo());
				} else {
					System.out.println("체지방량과 골격근량 입력시 사용 가능한 서비스입니다.\n");
				}
				break;
			case 0:
				System.out.println("메인메뉴로 돌아갑니다");
				break loop;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력해주세요");
			}

		}

	}

	// 식단 관리하기 메뉴
	public void op2() {
		System.out.println("-----------------------------🥗식단 관리하기🍽️-----------------------------");
		System.out.println("주 며칠 운동을 하시나요?(식단 관리 서비스 사용을 위해 필요한 정보입니다.)");
		System.out.println("1.거의 안 함 2.주 1일 ~ 3일 3.주 3일 ~ 5일 4.주 6일 ~ 7일 5.고강도 운동(운동 선수)");
		int healthDay = getInput("번호를 입력하여 운동량을 선택해주세요.\n");
		
		while (healthDay < 1 || healthDay > 5) {
	        System.out.println("올바른 번호를 입력해주세요.");
	        healthDay = getInput("1.거의 안 함 2.주 1일 ~ 3일 3.주 3일 ~ 5일 4.주 6일 ~ 7일 5.고강도 운동(운동 선수)\n");
	    }
		System.out.println("-----------------------------🥗식단 관리하기🍽️-----------------------------");
		//System.out.println("번호를 입력하여 메뉴를 선택해주세요.");
		
		double calcbmi = BMI.calcBMI(user.weight, user.height);
		double dailycal = Calorie.dailyCalorie(user, healthDay);
		
		
		loop: while (true) {
			int choice = getInput("1.하루별 권장 칼로리 섭취량 2.영양성분별 권장 칼로리 섭취량 0.메인메뉴로 돌아가기\n");
			
			switch (choice) {
			case 1:
				Calorie.printDaily(calcbmi);
				
				System.out.println("-------------------------🥗오늘 하루 식단 관리하기🍽️-------------------------");
				System.out.println("1.식단 기록하기 2.식단 조회하기 3.돌아가기");
				int select = sc.nextInt();
				sc.nextLine();
				
				if(select==1) {
	                System.out.print("날짜(월-일) : ");
	                String date = sc.nextLine();
	                
	                System.out.print("음식 : ");
	                String food = sc.nextLine();

	                System.out.print("칼로리 : ");
	                int intakeCal = sc.nextInt();
	                sc.nextLine(); 
	                //파일에 저장
	                DailyManager.saveIntake(date, food, intakeCal);
				}else if(select==2) {
					DailyManager.printIntake((int)dailycal, calcbmi);
					System.out.println("");
				}else if(select==3) {
					continue;
				}else {
					System.out.println("올바른 번호를 입력하세요");
				}
				break;
			case 2:
				System.out.println("운동 목적을 선택 해 주세요" + "\n1.다이어트 2.벌크업");
				int healthreason = sc.nextInt();
				Calorie.nutritionCalorie(healthreason);
				Calorie.printNutrition();
				System.out.println("\n");
				break;
			case 0:
				System.out.println("메인메뉴로 돌아갑니다");
				break loop;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
			}

		}

	}

	// 운동 관리하기 메뉴
	public void op3() {
		System.out.println("체형별 운동관리 프로그램입니다.");
		System.out.println("1. 체형별 추천 운동 2. 운동 계획 추가 & 조회하기  3. 메뉴로 돌아가기");

		int choice3 = sc.nextInt();
		sc.nextLine(); // 개행 문자 제거
		if (choice3 == 1) {
			// 인바디 정보가 있을 경우("Y") / 없을 경우("N")에 따라 YorN 값을 결정,
			// 혹은 Main에서 입력받은 YorN 값을 활용.
			String yorNValue = (ba.getbfm() > 0 && ba.getSMM() > 0) ? "Y" : "N";
			Sug_Workout sw = new Sug_Workout(user, ba, yorNValue);
			sw.Workout();
		} else if (choice3 == 2) {
			WorkPlanManager wm = new WorkPlanManager();
			wm.WorkoutChoice();
		} else if (choice3 == 3) {
			System.out.println("메인메뉴로 돌아갑니다");
			return;
		} else {
			System.out.println("잘못된 번호입니다.");
		}
	}

	// 나의 정보관리 메뉴
	public void op4() {
		um.manager();
	}

}
