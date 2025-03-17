package 건강관리프로그램;

public class BMI {

	// 체중/키(m)의 제곱 = BMI
	public static double calcBMI(double weight, double height) {
		height = height / 100.0; // cm -> m
		return weight / (height * height);
	}

	public static double printBMI(double bmi) {

		// 비만도 결과 출력
		// 저체중 : 18.5 미만, 정상 : 18.5~23미만, 과체중 : 23~25미만, 25이상은 비만
		if (bmi < 18.5) {
			System.out.println("비만도 결과 : 저체중\n");
		} else if (bmi < 23) {
			System.out.println("비만도 결과 : 정상\n");
		} else if (bmi < 25) {
			System.out.println("비만도 결과 : 과체중\n");
		} else if (bmi >= 25) {
			System.out.println("비만도 결과 : 비만\n");
		}
		return bmi;
	}

}
