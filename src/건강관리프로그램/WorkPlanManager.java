package 건강관리프로그램;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WorkPlanManager {
	Scanner sc = new Scanner(System.in);
	ArrayList<WorkPlan> plans = new ArrayList<WorkPlan>();

	int input;

	// 운동 계획 추가

	public void addWorkout() {
		System.out.print("운동 이름 : ");
		String woName = sc.nextLine();

		System.out.print("운동 종류 : ");
		String woType = sc.nextLine();

		System.out.print("운동 시간 / 횟수: ");
		int woDuration = sc.nextInt();

		System.out.print("세트 수 : ");
		int woSets = sc.nextInt();
		sc.nextLine();

		plans.add(new WorkPlan(woName, woType, woDuration, woSets));
		System.out.println("✅ " + woName + " 운동 계획이 추가되었습니다.");
		WorkoutChoice();
	}
	// 운동 계획 조회 ( 이름 검색 )

	public void viewWorkout() {
		System.out.println("조회할 운동 이름 : ");
		String woName = sc.nextLine();

		for (WorkPlan plan : plans) {
			if (plan.getWoName().equals(woName)) {
				System.out.println("조회결과 " + plan);
				WorkoutChoice();
				return;
			}
		}
		System.err.println("❌" + woName + " 운동 계획을 찾을 수 없습니다.");
		WorkoutChoice();
	}

	// 운동 계획 삭제 ( 이름으로 검색 후 삭제 )
	public void removeWorkout() {
		System.out.println("삭제할 계획의 운동 이름을 적어주세요");
		String woName = sc.nextLine();

		Iterator<WorkPlan> iterator = plans.iterator();
		while (iterator.hasNext()) {
			WorkPlan plan = iterator.next();
			if (plan.getWoName().equalsIgnoreCase(woName)) {
				iterator.remove();
				System.out.println("🗑️ '" + woName + "' 운동 삭제 완료");
				WorkoutChoice();
				return;
			}
		}
		System.out.println("❌" + woName + " 운동 계획을 찾을 수 없습니다.");
		WorkoutChoice();
	}

	public void listWorkouts() {
		if (plans.isEmpty()) {
			System.err.println("현재 등록된 운동 계획이 없습니다.");
			WorkoutChoice();
			return;
		} else {
			System.out.println("운동 계획 목록");
			for (WorkPlan plan : plans) {
				System.out.printf("\n%s       ", plan);
			}
		}
		WorkoutChoice();
	}

	public void WorkoutChoice() {
		System.out.println("\n운동 계획 리스트입니다\n" + "1. 운동계획 추가  2. 운동계획 삭제  3. 운동계획 목록  4. 초기화면으로 돌아가기");

		// 유효성 검사
		while (!sc.hasNextInt()) {
			System.err.println("올바른 번호를 입력 해 주세요");
			sc.next();
			WorkoutChoice();
		}

		input = sc.nextInt();
		sc.nextLine();

		switch (input) {
		case 1:
			addWorkout();
			break; // 메뉴를 다시 출력
		case 2:
			removeWorkout();
			break;
		case 3:
			listWorkouts();
			break;
		case 4:
			System.out.println("🏠 초기 화면으로 돌아갑니다.");
			return;
		default:
			System.err.println("❌ 올바른 번호를 입력해 주세요.");
		}
	}
}
