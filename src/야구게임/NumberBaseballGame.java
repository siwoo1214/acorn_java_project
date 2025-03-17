package 야구게임;

import java.util.List;

//숫자 야구 게임 클래스
public class NumberBaseballGame implements Game {	//Game 인터페이스를 상속받음 void Start();
    private final Computer computer;				//Computer.java를 변수 computer로 지정
    private final Player player;					
    private final GameJudge judge;

    public NumberBaseballGame() {
        this.computer = new Computer();
        this.player = new Player();
        this.judge = new GameJudge();
    }

    @Override
    public void start() {
        System.out.println("숫자 야구 게임을 시작합니다!");
        List<Integer> secretNumbers = computer.generateNumbers();	
        int attempts = 0;
        
        while (true) {
            attempts++;
            List<Integer> guess = player.getUserInput();
            int[] result = judge.checkNumbers(secretNumbers, guess);
            
            System.out.println(result[0] + " 스트라이크, " + result[1] + " 볼");
            
            if (result[0] == 3) {
                System.out.println("정답입니다! 총 " + attempts + "번 만에 맞추셨습니다!");
                break;
            }
        }
    }
}
