package 건강관리프로그램;

import java.util.Scanner;

public class Sug_Workout {

    Scanner sc = new Scanner(System.in);
    User user;          // User 정보를 가져오기 위함
    BodyAnalysis ba;    // BodyAnalysis의 체지방량(bfm) 정보를 가져오기 위함 
    String YorN;        // 인바디 정보를 기입한 경우("Y") 체지방률 별 운동 추천, 기입하지 않은 경우("N") BMI별 운동 추천  
    double fatPer;      // 체지방률 계산 결과

    // 기본 생성자
    public Sug_Workout() {
    }

    // 매개변수 있는 생성자
    public Sug_Workout(User user, BodyAnalysis ba, String YorN) {
        this.user = user;
        this.ba = ba;
        this.YorN = YorN;
    }

    public String getYorN() {
        return YorN;
    }

    public void setYorN(String YorN) {
        this.YorN = YorN;
    }

    // 체지방률 계산
    public void fatPer() {
        fatPer = (ba.getbfm() / user.getWeight()) * 100;
    }

    // 운동 추천 메서드
    public void Workout() {
        fatPer(); // 체지방률 계산

        if (YorN.equals("Y")) {
            // 인바디 정보를 기입한 경우: 체지방률 별 운동 추천
            if (user.getGender().equals("남성") || user.getGender().equals("남")) {
                if (fatPer < 13) { // 체지방률 13% 미만의 남성
                    System.out.println("저체지방 체형 운동 추천입니다.\n"
                            + "목표 : 근육량 증가\n"
                            + "운동 : 고중량 저반복 근력운동 (8회 4세트)\n"
                            + "유산소 : 짧고 강한 인터벌 (주 3회 20분)\n"
                            + "식단 : 탄수화물 + 단백질 섭취 ↑\n");
                } else if (fatPer < 26) { // 체지방률 13% ~ 26%의 남성
                    System.out.println("정상 체형 운동 추천입니다.\n"
                            + "목표 : 근육 유지, 체형 개선\n"
                            + "운동 : 중량증가 근력운동 (8회 4세트)\n"
                            + "유산소 : 가벼운 조깅 또는 인터벌 러닝\n"
                            + "식단 : 균형 잡힌 영양섭취 유지\n");
                } else if (fatPer < 31) { // 체지방률 26% ~ 31%의 남성
                    System.out.println("과체지방 체형 운동 추천입니다.\n"
                            + "목표 : 체지방 감량 및 근력 유지\n"
                            + "운동 : 중량증가 근력운동 (12회 4세트)\n"
                            + "유산소 : 중강도 유산소 (주 4회 60분)\n"
                            + "식단 : 탄수화물 ↓, 단백질 및 식이섬유 ↑\n");
                } else { // 체지방률 32% 이상의 남성
                    System.out.println("비만 체형 운동 추천입니다.\n"
                            + "목표 : 체중 감량 및 기초대사량 증가\n"
                            + "운동 : 가벼운 근력 운동 + 맨몸 운동 (15회 4세트)\n"
                            + "유산소 : 점진적 증가 / 빠른 걷기 → 조깅 → 러닝 (주 5~6회)\n"
                            + "식단 : 단백질 ↑, 칼로리 ↓, 당분 제한\n");
                }
            } else if (user.getGender().equals("여성") || user.getGender().equals("여")) {
                if (fatPer < 24) { // 체지방률 24% 미만의 여성
                    System.out.println("저체지방 체형 운동 추천입니다.\n"
                            + "목표 : 건강한 지방량 유지, 근육 증가\n"
                            + "운동 : 근력 운동 + 둔근, 코어 중심 운동 (8회 5세트)\n"
                            + "유산소 : 가볍게 (주 2회 30분)\n"
                            + "식단 : 탄수화물 + 건강한 지방 보충\n");
                } else if (fatPer < 36) { // 체지방률 24% ~ 36%의 여성
                    System.out.println("정상 체형 운동 추천입니다.\n"
                            + "목표 : 탄력 있는 몸매 유지\n"
                            + "운동 : 근력 + 전신 운동 병행 (12회 5세트)\n"
                            + "유산소 : 인터벌 트레이닝 (주 3회 40분)\n"
                            + "식단 : 균형 잡힌 식사 + 가공식품 줄이기\n");
                } else if (fatPer < 42) { // 체지방률 36% ~ 41%의 여성
                    System.out.println("과체지방 체형 운동 추천입니다.\n"
                            + "목표 : 체지방 감소, 체형 개선\n"
                            + "운동 : 중강도 근력 운동 (15회 4세트)\n"
                            + "유산소 : 빠른 걷기, 싸이클, 수영 (주 4회 60분)\n"
                            + "식단 : 탄수화물 ↓, 단백질 + 채소 위주\n");
                } else { // 체지방률 42% 이상의 여성
                    System.out.println("비만 체형 운동 추천입니다.\n"
                            + "목표 : 체중 감량 및 기초대사량 증가\n"
                            + "운동 : 저강도 운동 (맨몸 운동, 요가, 필라테스)\n"
                            + "유산소 : 일상 속 활동량 증가 (걷기, 가벼운 조깅)\n"
                            + "식단 : 단백질 ↑, 탄수화물 ↓, 충분한 물 섭취\n");
                }
            }
        } else if (YorN.equals("N")) {
            // 인바디 정보를 기입하지 않은 경우: BMI 기반 운동 추천
            System.out.println("인바디 정보가 입력되지 않아, BMI별 운동법으로 추천드리겠습니다.");
            double bmi = BMI.calcBMI(user.getWeight(), user.getHeight());
            if (user.getGender().equalsIgnoreCase("남성") || user.getGender().equalsIgnoreCase("남")) {
                if (bmi < 18.5) { // 저체중
                    System.out.println("🟢 저체중 체형 운동 추천입니다.\n"
                            + "목표 : 근육량 증가\n"
                            + "운동 : 고중량 저반복 근력운동 (6~8회 4세트)\n"
                            + "유산소 : 최소한으로 (주 2회 15분)\n"
                            + "식단 : 탄수화물 + 단백질 섭취 ↑\n");
                } else if (bmi < 23) { // 정상 체중
                    System.out.println("🟡 정상 체형 운동 추천입니다.\n"
                            + "목표 : 근육 유지, 체형 개선\n"
                            + "운동 : 중량 증가 근력운동 (8~12회 4세트)\n"
                            + "유산소 : 가벼운 조깅, 인터벌 러닝 (주 3회)\n"
                            + "식단 : 균형 잡힌 영양섭취 유지\n");
                } else if (bmi < 25) { // 과체중
                    System.out.println("🟠 과체중 체형 운동 추천입니다.\n"
                            + "목표 : 체지방 감량 및 근력 유지\n"
                            + "운동 : 중강도 근력운동 (12~15회 4세트)\n"
                            + "유산소 : 중강도 유산소 (주 4회 45분)\n"
                            + "식단 : 탄수화물 ↓, 단백질 및 식이섬유 ↑\n");
                } else { // 비만
                    System.out.println("🔴 비만 체형 운동 추천입니다.\n"
                            + "목표 : 체중 감량 및 기초대사량 증가\n"
                            + "운동 : 가벼운 근력운동 + 맨몸 운동 (15~20회 4세트)\n"
                            + "유산소 : 빠른 걷기 → 조깅 → 러닝 (주 5~6회 60분)\n"
                            + "식단 : 단백질 ↑, 칼로리 ↓, 당분 제한\n");
                }
            } else if (user.getGender().equalsIgnoreCase("여성") || user.getGender().equalsIgnoreCase("여")) {
                if (bmi < 18.5) { // 저체중
                    System.out.println("🟢 저체중 체형 운동 추천입니다.\n"
                            + "목표 : 건강한 지방량 유지, 근육 증가\n"
                            + "운동 : 근력 운동 + 둔근, 코어 중심 운동 (10회 5세트)\n"
                            + "유산소 : 최소한으로 (주 2회 20분)\n"
                            + "식단 : 탄수화물 + 건강한 지방 보충\n");
                } else if (bmi < 23) { // 정상 체중
                    System.out.println("🟡 정상 체형 운동 추천입니다.\n"
                            + "목표 : 탄력 있는 몸매 유지\n"
                            + "운동 : 근력 + 전신 운동 병행 (12회 5세트)\n"
                            + "유산소 : 인터벌 트레이닝 (주 3회 40분)\n"
                            + "식단 : 균형 잡힌 식사 + 가공식품 줄이기\n");
                } else if (bmi < 25) { // 과체중
                    System.out.println("🟠 과체중 체형 운동 추천입니다.\n"
                            + "목표 : 체지방 감소, 체형 개선\n"
                            + "운동 : 중강도 근력운동 (15회 4세트)\n"
                            + "유산소 : 빠른 걷기, 싸이클, 수영 (주 4회 60분)\n"
                            + "식단 : 탄수화물 ↓, 단백질 + 채소 위주\n");
                } else { // 비만
                    System.out.println("🔴 비만 체형 운동 추천입니다.\n"
                            + "목표 : 체중 감량 및 기초대사량 증가\n"
                            + "운동 : 저강도 운동 (맨몸 운동, 요가, 필라테스)\n"
                            + "유산소 : 일상 속 활동량 증가 (걷기, 가벼운 조깅)\n"
                            + "식단 : 단백질 ↑, 탄수화물 ↓, 충분한 물 섭취\n");
                }
            }
        } else {
            System.out.println("올바른 선택지를 골라주세요");
            return;
        }
    }
}