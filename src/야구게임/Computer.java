package 야구게임;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//컴퓨터가 랜덤 숫자 생성
public class Computer {
    public List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();	  //List<Integer> numbers를 객체 ArrayList로 생성한후 랜덤으로 카드를 생성	
        while (numbers.size() < 3) {  //여기서 랜덤은 라이브러리로 숫자를 랜덤으로 생성하는 기능이 있음 그후 아래의 식을 사용해 1~9로 숫자범위를 정해놓음
            int num = rand.nextInt(9) + 1; // 1~9 랜덤 숫자
            if (!numbers.contains(num)) { //숫자 중복체크
                numbers.add(num);
            }     
        }
        return numbers;   
    }
    
}
