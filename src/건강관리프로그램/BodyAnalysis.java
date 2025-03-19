package 건강관리프로그램;

import java.util.Scanner;

public class BodyAnalysis {

	Scanner sc = new Scanner(System.in);

	User user; // User클래스
	double bfm; // 체지방량
	double smm; // 골격근량
	double fatPer = 0; // 체지방률(%)
	double musleRange = 0; // 골격근량(%)
	double bmr = 0; // 기초대사량

	public BodyAnalysis(User user, double bfm, double smm) {
		this.user = user;
		this.bfm = bfm;
		this.smm = smm;
	}

	public double getbfm() {
		return bfm;
	}

	public void setbfm(double bfm) {
		this.bfm = bfm;
	}

	public double getSMM() {
		return smm;
	}

	public void setSMM(double SMM) {
		this.smm = SMM;
	}

	// 체지방률
	public void fatPer() {
		fatPer = (bfm / user.weight) * 100;
	}

	// 체지방률 정상범위
	public String getFatPerRange(double bfm) {
		fatPer();

		if (user.gender.equals("남성") || user.gender.equals("남")) {
			if (user.age < 40) {
				if (fatPer < 8) {
					return "저체지방";
				} else if (fatPer < 20) {
					return "평균";
				} else if (fatPer < 25) {
					return "과체지방";
				} else {
					return "비만";
				}
			} else if (user.age < 60) {
				if (fatPer < 11) {
					return "저체지방";
				} else if (fatPer < 22) {
					return "평균";
				} else if (fatPer < 28) {
					return "과체지방";
				} else {
					return "비만";
				}
			} else {
				if (fatPer < 13) {
					return "저체지방";
				} else if (fatPer < 26) {
					return "평균";
				} else if (fatPer < 31) {
					return "과체지방";
				} else {
					return "비만";
				}
			}
		} else if (user.gender.equals("여성") || user.gender.equals("여")) {
			if (user.age < 40) {
				if (fatPer < 21) {
					return "저체지방";
				} else if (fatPer < 33) {
					return "평균";
				} else if (fatPer < 40) {
					return "과체지방";
				} else {
					return "비만";
				}
			} else if (user.age < 60) {
				if (fatPer < 23) {
					return "저체지방";
				} else if (fatPer < 34) {
					return "평균";
				} else if (fatPer < 40) {
					return "과체지방";
				} else {
					return "비만";
				}
			} else {
				if (fatPer < 24) {
					return "저체지방";
				} else if (fatPer < 36) {
					return "평균";
				} else if (fatPer < 42) {
					return "과체지방";
				} else {
					return "비만";
				}
			}
		} else {
			return "잘못된 입력입니다";
		}
	}

	// 골격근량
	public void musleRange() {
		musleRange = (smm / user.weight) * 100;
	}

	// 골격근량 정상범위
	public String getMusleRange(double SMM) {
		musleRange();

		if (user.gender.equals("남성") || user.gender.equals("남")) {
			if (musleRange < 32) {
				return "부족";
			} else if (musleRange < 35) {
				return "평균";
			} else {
				return "우수";
			}
		} else if (user.gender.equals("여성") || user.gender.equals("여")) {
			if (musleRange < 26) {
				return "부족";
			} else if (musleRange < 30) {
				return "평균";
			} else {
				return "우수";
			}
		} else {
			return "잘못입력하였습니다";
		}
	}

	// 기초대사량
	public void bmr() {
		if (user.gender.equals("남성") || user.gender.equals("남")) {
			bmr = (10 * user.weight) + (6.25 * user.height) - (5 * user.age) + 5;
		} else if (user.gender.equals("여성") || user.gender.equals("여")) {
			bmr = (10 * user.weight) + (6.25 * user.height) - (5 * user.age) - 161;
		} else {
			System.out.println("잘못된 입력입니다");
		}
	}

	public String bodyInfo() {
		fatPer();
		bmr();

		return "체지방량: " + String.format("%.1f", bfm) + " kg\n" + 
			   "체지방률: " + String.format("%.1f", fatPer) + " % (" + getFatPerRange(bfm) + ")\n" + 
			   "골격근량: " + String.format("%.1f", smm) + " kg (" + getMusleRange(smm) + ")\n" + 
			   "기초대사량: " + String.format("%.1f", bmr) + " kcal";
	}

}
