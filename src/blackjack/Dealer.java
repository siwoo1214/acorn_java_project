package blackjack;

import java.util.ArrayList;
import java.util.Random;

class Dealer {
    private ArrayList<String> cards = new ArrayList<>();
    private Random ran = new Random();
    private String[] cardList;

    public Dealer(String[] cardList) {
        this.cardList = cardList;
    }

    public void addCard() {
        cards.add(cardList[ran.nextInt(cardList.length)]);
    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public int getScore() {
        int score = 0;
        int aceCount = 0;

        for (String card : cards) {
            if (card.equals("J") || card.equals("Q") || card.equals("K")) {
                score += 10;
            } else if (card.equals("A")) {
                aceCount++;
                score += 11;
            } else {
                score += Integer.parseInt(card);
            }
        }

        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }
    
    public void dealerAction() {
        System.out.println("딜러의 턴 입니다.");
        System.out.println("딜러의 카드는: " + cards + " 입니다");

        while (getScore() < 17) {
            addCard();
            System.out.println("딜러의 카드가 한장 추가 되었습니다 !");
            System.out.println("딜러의 카드는: " + cards + " 입니다");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        int finalScore = getScore();
        System.out.println("딜러의 점수는: " + finalScore + " 점 입니다.");
    }



    
    public void 카드분배() {
    	cards.add(cardList[ran.nextInt(cardList.length)]);
    	cards.add(cardList[ran.nextInt(cardList.length)]);
    	System.out.println("딜러에게 카드 두장이 지급되었습니다.");
    }
    
}