package 조커뽑기;

/*질문
 * addCard의 스트링타입의 카드는 갑자기 어디서 나타난건가요?
 */


import java.util.ArrayList;
import java.util.List;

public class Player {
	
    private String name;
    private List<String> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }
    
    

    public String getName() {	//getName이라는 매서드
        return name;
    }

    public List<String> getHand() {	//getHand이라는 매서드
        return hand;
    }

    public void addCard(String card) {	//addCard이라는 매서드
        hand.add(card);					//add기능을 사용 hand에 카드추가
    }

    public void removeCard(String card) {	//removeCard이라는 매서드
        hand.remove(card);					//remove기능을 사용 hand에 카드추가
    }
    
}
