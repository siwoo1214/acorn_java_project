package blackjack;

import java.util.Scanner;

public class blackjack {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

        String[] cardList = new String[]{
            "A", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",	//♠
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",	//♣
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",	//♥
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"	//◆
        };
        
        
        
        boolean replay = true;
        
        while(replay) {
        System.out.println("블랙잭 게임이 시작 되었습니다.");
        System.out.println("=====================");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Player player = new Player(sc, cardList);
        Dealer dealer = new Dealer(cardList);
        
        //카드 분배
        player.카드분배();
        dealer.카드분배();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("현재 딜러의 카드는: " + dealer.getCards().get(0) + ", "+"비공개 카드가 있습니다.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        //플레이어와 딜러의 카드공개
        player.getCards();
        dealer.getCards();
        
        player.getScore();
        
        //플레이어 턴 hit stand 고르기
        player.playerAction();
        
        //딜러 턴 
        dealer.dealerAction();
        
        
        //승패 판정
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("플레이어의 최종 점수는 " + playerScore + "점 입니다.");
        System.out.println("딜러의 최종 점수는 " + dealerScore + "점 입니다.");

     // 승패 판정
        if (playerScore > 21) {
            System.out.println("플레이어가 21점을 초과했습니다. 플레이어 패배 !");
        } else if (dealerScore > 21) {
            System.out.println("딜러가 21점을 초과했습니다. 플레이어 승리 !");
        } else if (playerScore > dealerScore) {
            System.out.println("플레이어 승리 !");
        } else if (playerScore < dealerScore) {
            System.out.println("딜러 승리 !");
        } else {
            System.out.println("무승부 !");
        }
        
        System.out.println("게임을 다시 시작하시겠습니까 ? [1. 재시작] [2. 종료]");
        int answer = sc.nextInt();
        if(answer == 1) {
        	System.out.println("게임을 다시 시작합니다.");
        }else if(answer ==2) {
        	System.out.println("게임을 종료합니다.");
        	replay = false;
        }
        	
        
        
        
        }
        

        	
        
        
        
        
        
        
		
		
		
		
		
	}

}
