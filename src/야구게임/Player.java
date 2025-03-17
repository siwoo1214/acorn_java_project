package 야구게임;

/*	질문
 * 예외가 발생하는이유
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {
    private final Scanner scanner = new Scanner(System.in);		//final 변수의 초기값 세팅
    
    public List<Integer> getUserInput() {
        System.out.print("숫자 3개를 입력하세요 (1~9, 중복X): ");
        while (true) {
            String input = scanner.nextLine();
            List<Integer> guess = parseInput(input);
               
            if (guess != null) return guess; //null이 아닐경우 리턴 null일경우 그대로 수행되면서 아래매세지가 뜸
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            
        }
        
    }
    
    private List<Integer> parseInput(String input) {   // "123"   ->    ArrayList<Integer>  1,2, 3
    	
        // 3자리 숫자 입력을 받는 경우
        if (input.length() == 3) {
            List<Integer> numbers = new ArrayList<>();    //  "123"
            try {
                // 각 자리를 하나씩 분리해서 숫자로 변환
                for (int i = 0; i < 3; i++) {
                    int num = Integer.parseInt(String.valueOf(input.charAt(i)));	//charAt의 i번째의 문자를 문자열로 변환하고 다시 숫자열로 변환
                    if (num < 1 || num > 9 || numbers.contains(num)) return null; // 1~9 범위, 중복 검사
                    numbers.add(num);	
                }
                return numbers;
            } catch (NumberFormatException e) {
                return null; // 숫자가 아닌 값이 입력되었을 경우
            }
            
        }
        return null; // 3자리 숫자가 아닌 경우
    }
}
