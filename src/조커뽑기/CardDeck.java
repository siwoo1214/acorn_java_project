package 조커뽑기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	
    private List<String> deck;

    public CardDeck() {
        deck = new ArrayList<>();
        initializeDeck();
    }

 
    private void initializeDeck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        
        // 카드덱에 추가
        for (String suit : suits) {	//suit가 ♠, ♥, ♦, ♣ 순으로 변경됨.
            for (String rank : ranks) { 
                deck.add(suit + rank);        
            }
        }

        // 조커 추가
        deck.add("조커");
    }

    //아래의 매서드들은 다른 클래스에서도 사용하기 위해 존재
    // 카드 섞기
    public void shuffle() {	//셔플 매서드
        Collections.shuffle(deck);	//Collection.shuffle라이브러리 사용(deck을 섞어주는 역할)
    }

    public List<String> getDeck() {	
        return deck;				//deck 객체를 반환
    }
    
}
