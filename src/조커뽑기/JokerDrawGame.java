package 조커뽑기;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class JokerDrawGame {
    private static CardDeck cardDeck;
    private static List<Player> players = new ArrayList<>();

    
    // 새 게임을 시작하는 메서드
    public static void startNewGame() {
        cardDeck = new CardDeck();	// 새로운 카드 덱 생성
        cardDeck.shuffle();		// 카드를 서로 섞어주는 매서드(CardDeck클래스에 있음)
        players.clear();	//기존 플레이어 리스트 초기화
        
        for (int i = 1; i <= 2; i++) {
            players.add(new Player("플레이어 " + i));	//플레이어1,2 players에 저장
        }

        distributeCards();	//카드분배 매서드 실행코드
        printInitialHands();	//첫 번째 패 출력 매서드 실행코드
        for (int i = 0; i < players.size(); i++) {
            removePairs(players.get(i));
        }

        try {
            playGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    // 카드 분배 메서드
    private static void distributeCards() {
        List<String> deck = cardDeck.getDeck(); //List<String>타입의 deck배열에 CardDeck클래스의 getDeck기능을 수행
        for (int i = 0; i < deck.size(); i++) {
            players.get(i % 2).addCard(deck.get(i)); 
            //i%2=결과는 0또는 1 만 나옴 즉 배열의 첫번째 두번째에만 add.Card로 카드가 더해짐 즉 플레이어1과 2의 패에만 카드가 추가되게됨
        }
    }

    
    // 첫 번째 패 출력
    private static void printInitialHands() {
        System.out.println("게임 시작! 각 플레이어의 첫번째 패:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println(player.getName() + "의 첫번째 패: " + player.getHand());
        }
        System.out.println();
    }

    
    // 한쌍 제거 메서드
    private static void removePairs(Player player) {
        Map<String, List<String>> rankMap = new HashMap<>(); 
        //rankMap: 카드 숫자(rank)를 기준으로 카드들을 분류하는 HashMap
        //Key: 카드의 숫자(rank) → "2", "3", "4", ..., "J", "Q", "K", "A"
        //Value: 해당 숫자의 카드 리스트 → ["2♠", "2♥", "2♦"]
        //HashMap라이브러리 기능 사용 배열의 존재하는 키값을 내가 지정하여 호출가능
        for (String card : player.getHand()) {
            String rank = getCardRank(card);
            rankMap.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
        }
        /*
         	for (String card : player.getHand()) 부분은 
         	player.getHand() 리스트에서 각 카드를 card 변수에 넣고 반복하면서 
         	같은 작업을 처리하게 됩니다.
         	
         	예시.
player.getHand()가 반환하는 것은 카드의 리스트입니다. 예를 들어, player.getHand()가 
["2♠", "K♠", "3♣"]와 같은 리스트라고 가정해 보겠습니다. 이때 for 문은 다음과 같이 동작합니다:

첫 번째 반복:
card 변수는 "2♠"로 설정됩니다.
그 후에 String rank = getCardRank(card); 구문이 실행되어 "2♠" 카드의 숫자 부분을 추출하게 됩니다. 
이때 rank에는 "2"가 들어갑니다.
그 후, rankMap.computeIfAbsent(rank, k -> new ArrayList<>()).add(card); 코드가 실행됩니다. 
"2"라는 숫자 랭크가 rankMap에 없다면 새로운 리스트를 만들고, "2♠" 카드를 그 리스트에 추가합니다.

두 번째 반복:
card 변수는 "K♠"로 설정됩니다.
"K♠" 카드의 숫자 부분을 추출하여 rank에는 "K"가 들어갑니다.
rankMap에서 "K"가 없다면 새로운 리스트가 생성되고, "K♠" 카드는 그 리스트에 추가됩니다.

세 번째 반복:
card 변수는 "3♣"로 설정됩니다.
"3♣" 카드의 숫자 부분을 추출하여 rank에는 "3"이 들어갑니다.
"3"이 rankMap에 없다면 새로운 리스트를 만들고, "3♣" 카드를 그 리스트에 추가합니다.

3. "반복하면서 같은 작업을 처리한다"는 의미:
이 부분에서 말하는 "같은 작업"은, 각 카드가 주어질 때마다 
rankMap.computeIfAbsent(rank, k -> new ArrayList<>()).add(card); 
이라는 코드가 모든 카드에 대해 반복적으로 실행된다는 것입니다.
         */

        rankMap.values().forEach(cards -> { 
            while (cards.size() >= 2) { //같은 숫자(rank)의 카드가 2장 이상 있을 때만 반복문 실행
                String card1 = cards.remove(0); //리스트에서 첫 번째 카드를 가져오고, 제거
                //예시: "2♠" 선택 후, 리스트에서 제거 남은 리스트: ["2♥", "2♣"] 
                String card2 = cards.remove(0); //리스트에서 두 번째 카드를 가져오고, 제거
                //예시: "2♥" 선택 후, 리스트에서 제거 남은 리스트: ["2♣"]
                player.removeCard(card1); //플레이어의 패(hand)에서 card1(2♠) 제거
                player.removeCard(card2); //플레이어의 패(hand)에서 card2(2♥) 제거
                System.out.println(player.getName() + "가 " + card1 + " 및 " + card2 + " 카드를 버렸습니다.");
                sleep(1000); //중복되는 Thread.sleep()코드를 sleep매서드로 만들어 표현
            }
        });
        System.out.println(player.getName() + "의 패 (버린 후): " + player.getHand());
        sleep(1000);
    }
    

    // 상대 플레이어의 카드에서 카드 가져오기
    private static void takeCardFromNextPlayer(int currentIndex) {
        Player currentPlayer = players.get(currentIndex);	//플레이어 설정 currentPlayer: 현재 차례의 플레이어
        Player nextPlayer = players.get((currentIndex + 1) % players.size()); //nextPlayer: 다음 차례의 플레이어
        //(currentIndex + 1) % players.size(): % 연산자를 통해 마지막 플레이어 다음엔 첫 번째 플레이어로 돌아가도록 처리 (원형 구조)
        
        if (!nextPlayer.getHand().isEmpty()) { //상대의 패가 비어있지 않을 때만 실행
            String cardTaken = nextPlayer.getHand().remove(0); //상대 플레이어의 첫 번째 카드를 뽑음
            currentPlayer.addCard(cardTaken); //뽑은 카드를 현재 플레이어의 패에 추가
            System.out.println(currentPlayer.getName() + "가 " + nextPlayer.getName() + "의 패에서 " + cardTaken + " 카드를 가져왔습니다.");
            removeMatchingCardFromPlayer1(currentPlayer, cardTaken);
            //뽑은 카드와 같은 랭크를 가진 카드 쌍이 있으면 버리는 메서드 호출
        }
        System.out.println(currentPlayer.getName() + "의 패: " + currentPlayer.getHand());
        System.out.println(nextPlayer.getName() + "의 패: " + nextPlayer.getHand());
        sleep(1000);
    }
    

    //같은 숫자(rank)를 가진 카드가 2장 이상 존재하면 자동으로 카드 쌍을 버리는 매서드
    private static void removeMatchingCardFromPlayer1(Player player, String newCard) {
        Map<String, List<String>> rankMap = new HashMap<>();
        
        player.getHand().forEach(card -> {
            String rank = getCardRank(card); 
            //getCardRank(card): 카드의 숫자(rank) 추출 (예: "2♠" → "2", "K♠" → "K")
            rankMap.computeIfAbsent(rank, k -> new ArrayList<>()).add(card);
            //computeIfAbsent(): 만약 해당 rank가 rankMap에 없다면, 새로운 리스트를 생성해서 추가
        });

        List<String> cardsWithSameRank = rankMap.get(getCardRank(newCard));
        //뽑은 카드(newCard)의 rank와 같은 카드들을 가져옴
        //예시) newCard = "2♠" → cardsWithSameRank = ["2♠", "2♥"]
        while (cardsWithSameRank != null && cardsWithSameRank.size() >= 2) {
            String card1 = cardsWithSameRank.remove(0);
            String card2 = cardsWithSameRank.remove(0);
            player.removeCard(card1);
            player.removeCard(card2);
            System.out.println(player.getName() + "가 " + card1 + " 및 " + card2 + " 카드를 버렸습니다.");
        }
        /*
         	1. 같은 숫자가 2장 이상 존재할 때만 반복문 실행
			2. 카드 두 장을 제거하면서 한 쌍을 버림
			3. removeCard(): Player 클래스에 있는 카드를 패에서 제거하는 메서드
			4. 버린 카드 출력
        */
    }

    
    
    // 게임 진행 메서드
    private static void playGame() throws InterruptedException {
        while (players.size() > 1) { //플레이어가 한 명 남을 때까지 반복
            for (int i = 0; i < players.size(); i++) {	//i번째 플레이어가 다음 차례 플레이어의 패에서 카드 1장 뽑기
                takeCardFromNextPlayer(i); // 상대 플레이어의 카드에서 카드 가져오기 메서드가 실행되면서 뽑은 카드 쌍 자동 제거
                if (players.get(i).getHand().isEmpty()) {
                    System.out.println(players.get(i).getName() + "이 승리하였습니다.");
                    players.remove(i--);
               /*
                패가 비어버린 플레이어 = 승리!
				해당 플레이어를 players 리스트에서 제거
				i--: remove() 메서드를 사용하면 리스트 인덱스가 당겨지기 때문에 인덱스 조정 필수
               */
                }
            }

            List<Player> playersWithJoker = new ArrayList<>();
            for (Player player : players) {
                if (player.getHand().contains("조커")) {
                    playersWithJoker.add(player);
                }
            }
            /*
             	for (Player player : players) 부분에서 players 리스트의 각 플레이어를 
             	순차적으로 확인하고, 그 플레이어의 카드에 "조커"가 있는지 확인한 후 
             	조건을 만족하면 playersWithJoker 리스트에 추가합니다.
             */
            
            
            if (playersWithJoker.size() == 1 && playersWithJoker.get(0).getHand().size() == 1) {
                System.out.println(playersWithJoker.get(0).getName() + "가 조커 한장만 남겼고 패배했습니다.");
                System.out.println("게임 종료!");
                break;
            }
            System.out.println("\n--- 게임 진행 ---"); //1턴마다 구분선 출력
        }
    }
    

    private static String getCardRank(String card) {
        return card.substring(card.length() - 1);
    }
    /*
     	카드 문자열에서 **맨 마지막 문자(♠, ♥, ♦, ♣)**만 추출하는 메서드
		카드 숫자(rank) 추출이 아니라, "카드 무늬" 추출 기능
     */

    
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
