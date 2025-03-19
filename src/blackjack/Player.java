package blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Player {
    private ArrayList<String> cards = new ArrayList<>();
    private Random ran = new Random();
    private Scanner sc;
    private String[] cardList;

    public Player(Scanner sc, String[] cardList) {
        this.sc = sc;
        this.cardList = cardList;
    }
    
    public void addCard() {
        cards.add(cardList[ran.nextInt(cardList.length)]);
    }
    
    public ArrayList<String> getCards() {
        System.out.println("현재 플레이어의 카드는: " + cards + " 입니다.");
        System.out.println("현재 플레이어의 점수는: " + getScore() + " 점 입니다");
        return cards;
    }
    
    // Ace의 점수를 자동으로 조정하는 점수 계산 메서드
    public int getScore() {
        int score = 0;
        int aceCount = 0;
        
        for (String card : cards) {
            if (card.equals("J") || card.equals("Q") || card.equals("K")) {
                score += 10;
            } else if (card.equals("A")) {
                score += 11;
                aceCount++;
            } else {
                score += Integer.parseInt(card);
            }
        }
        
        // 총 점수가 21을 초과하면 Ace의 값을 11에서 1로 조정
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }
        
        return score;
    }
    
    public void 카드분배() {
        cards.add(cardList[ran.nextInt(cardList.length)]);
        cards.add(cardList[ran.nextInt(cardList.length)]);
        System.out.println("플레이어에게 카드 두장이 지급되었습니다.");
    }
    
    public void playerAction() {
        while (true) {
            System.out.println("플레이어는 행동을 선택해주세요. [1. Hit] [2. Stand]");
            int pick = sc.nextInt();
            
            if (pick == 1) {
                addCard();
                System.out.println("Hit! 새로운 카드를 받았습니다.");
                getCards();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                int score = getScore();
                if (score > 21) {
                    System.out.println("플레이어가 21점을 초과했습니다. 턴을 종료합니다.");
                    break;
                }
            } else if (pick == 2) {
                System.out.println("Stand! 플레이어 턴이 종료되었습니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }
}
