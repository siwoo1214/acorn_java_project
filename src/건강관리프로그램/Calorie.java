package 건강관리프로그램;

public class Calorie {
	
	static double carbohydrate; //탄수화물
	static double protein; //단백질
	static double fat; //지방
	static double diet; //다이어트 시 권장 칼로리
	static double bulkup; //벌크업 시 권장 칼로리
	static double tdee; //하루 소비 칼로리 
		
	// 하루 권장 섭취 칼로리량 계산
    public static double dailyCalorie(User user, int day) {
		double bmr; //기초대사량
        if (user.gender.equals("남성") || user.gender.equals("남")) {
            bmr = 66.47 + (13.75 * user.weight) + (5 * user.height) - (6.76 * user.age);  // 남성 BMR 계산
        } else {
            bmr = 655.1 + (9.56 * user.weight) + (1.85 * user.height) - (4.68 * user.age);  // 여성 BMR 계산
        }
        
        //하루 소비 칼로리 계산
        if (day==1 || day==2) {
            tdee = bmr*1.2; 
        }else if(day==3) {
            tdee = bmr*1.55; 
        }else if(day==4 || day==5) {
            tdee = bmr * 1.9; 
        }
        return tdee;
         
    }
	
    // 하루 권장 섭취 칼로리량 출력
    public static void printDaily(double bmi) {
    	int dailycal=0;
    	System.out.println("");
        if (bmi < 18.5) {
        	dailycal = (int)tdee+300;
            System.out.println("회원님은 저체중이므로 하루에 " + dailycal + "kcal 섭취를 권장합니다.");
        } else if (bmi < 23) {
        	dailycal = (int)tdee-500;
            System.out.println("회원님은 정상체중이므로 하루에 " + dailycal + "kcal 섭취를 권장합니다.");
        } else if (bmi < 25) {
        	dailycal = (int)tdee-700;
            System.out.println("회원님은 과체중이므로 하루에 " + dailycal + "kcal 섭취를 권장합니다.");
        } else {
        	dailycal = (int)tdee-1000;
            System.out.println("회원님은 비만이므로 하루에 " + dailycal + "kcal 섭취를 권장합니다.");
        }  
        System.out.println("");
    }
	
	//영양성분 별 권장 섭취량(gram 수) 계산
	public static void nutritionCalorie(int reason) {
		if(reason == 1) {
			diet = tdee*0.8;
			carbohydrate = diet*0.4 /4;
			protein = diet*0.4 / 4;
			fat = diet*0.2 / 9;
		}else if(reason == 2) {
			bulkup = tdee*1.2;
			carbohydrate = bulkup*0.5 / 4;
			protein = bulkup*0.3 / 4;
			fat = bulkup*0.2 / 9;
		}
	}
	
	//영양성분 별 권장 섭취량(gram 수, 개수, 칼로리량) 출력
	public static void printNutrition() {
		System.out.printf( "\n섭취 탄수화물 = %.1f g", carbohydrate );
		System.out.printf("\n	고구마(100g) %.1f개 ",carbohydrate/20);
		System.out.printf("  |  kcal : %.1f",carbohydrate*4 );
		
		System.out.printf( "\n섭취 단백질 = %.1f g",protein);
		System.out.printf("\n	닭가슴살(100g) %.1f개 ",protein/23);
		System.out.printf("  |  kcal : %.1f",protein*4 );
		
		System.out.printf("\n섭취 지방 = %.1f g",fat);
		System.out.printf("\n	아몬드(50g) %.1f개 ",fat/24);
		System.out.printf("  |  kcal : %.1f",fat*9);
	}
}
