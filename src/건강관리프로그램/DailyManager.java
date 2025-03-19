package 건강관리프로그램;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DailyManager {  //day16.io.file문자 참고하기

	//하루 식단 기록 저장
	public static void saveIntake(String date, String food, int intakeCal) {
		try(FileWriter save = new FileWriter("res/intake.txt", true)) {	
			save.write(date + "," + food + "," + intakeCal + "\n");
			save.flush();
			save.close();
			System.out.println("✅ 오늘의 식단이 저장되었습니다.");
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//하루 식단 기록 출력
	public static void printIntake(int dailycal, double bmi) {
		Map<String, List<String>> dailylog = new TreeMap<>(); // 날짜별로 음식 목록 저장
		Map<String, Integer> totalCal = new TreeMap<>(); // 날짜별로 총 섭취 칼로리량 저장
		try(BufferedReader show = new BufferedReader(new FileReader("res/intake.txt"))) { //파일에서 값 읽어오기
			String line;
            while ((line = show.readLine()) != null) { 
                String[] data = line.split(",");//콤마로 구분된 문자열을 분리
                if (data.length == 3) {
                    String date = data[0];
                    String food = data[1] + " (" + data[2] + "kcal)";
                    int intakeCal = Integer.parseInt(data[2]);

                    dailylog.putIfAbsent(date, new ArrayList<>()); // 날짜별 리스트 생성
                    dailylog.get(date).add(food); // 음식 추가
                    totalCal.put(date, totalCal.getOrDefault(date, 0) + intakeCal);
                }
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//날짜별로 정리하여 데이터 출력
		System.out.println("");
        for (String date : dailylog.keySet()) {
        	System.out.println("📅 " + date + " (총 " + totalCal.get(date) + " kcal)");
            for (String food : dailylog.get(date)) {
                System.out.println(" - " + food); 
            }
            System.out.println("");
            int totalcal = totalCal.get(date);
            if (bmi < 18.5) { // 저체중일 경우
                if (totalcal < dailycal) {
                    System.out.println("권장 칼로리보다 " + (dailycal-totalcal) + "kcal 부족합니다.");
                } else if (totalcal >= dailycal) {
                    System.out.println("권장 칼로리를 지키셨습니다.💪");
                }
            } else{ //나머지
                if (totalcal > dailycal) {
                    System.out.println("권장 칼로리보다 " + (totalcal-dailycal) + "kcal 더 섭취하셨습니다.");
                    System.out.println("운동으로 칼로리를 소비하세요!🔥");
                } else {
                    System.out.println("권장 칼로리를 지키셨습니다.💪");
                }

            }
        }
	
	}
	
}
