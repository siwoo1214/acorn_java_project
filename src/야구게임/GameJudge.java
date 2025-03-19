package 야구게임;

import java.util.List;

//숫자 판정 로직
class GameJudge {
 public int[] checkNumbers(List<Integer> secret, List<Integer> guess) {
     int strike = 0, ball = 0;		
     for (int i = 0; i < 3; i++) {						//정답을 맞출때까지 반복 Computer.java의 List<Integer>를 매개변수로 받음)
         if (secret.get(i).equals(guess.get(i))) {		//
             strike++;
         } else if (secret.contains(guess.get(i))) {
             ball++;
         }
     }
     return new int[]{strike, ball};
 }
}
