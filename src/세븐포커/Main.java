package 세븐포커;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		SevenPokerDealer dealer;
		SevenPokerUser user;
		SevenPoker sp = new SevenPoker();

		// 룰 설명
//		sp.Rule();

		loop: while (true) {

			
			
			System.out.println("세븐 카드게임에 오신걸 환영합니다.");
			System.out.println("1. 룰 설명, 2. 게임시작, 3. 종료");

			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				sp.Rule();
				System.out.println();
				break;

			case 2:
				dealer = new SevenPokerDealer();
				user = new SevenPokerUser();
				System.out.println("게임을 시작하겠습니다.");
				System.out.println("딜러의 카드가 생성되었습니다.");
				dealer.RandomCard();
				System.out.println("유저의 카드가 생성되었습니다.");
				user.RandomCard();

				boolean gameOver = false;
				while (!gameOver) {

					try {
						Thread.sleep(1000); // 1초 딜레이
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println();
					System.out.println("카드가 자동으로 뽑고 진행됩니다...");

					// 딜러 카드 추가
					boolean dealerA = dealer.Ascending();
					boolean userA = user.Ascending();
//						 boolean usere = user.Ascending();

					// 사용자 카드 사용확인
					if (userA) {
						System.out.println("⭐️⭐유저의 승리입니다.⭐⭐");
						String result = dealer.toString();
						System.out.println(result);
						gameOver = true;
					} else if (dealerA) {
						System.out.println("⭐⭐딜러의 승리입니다.⭐⭐");
						String result = dealer.toString();
						System.out.println(result);
						gameOver = true;
					} else {
						System.out.println("유저의 카드를 1장 뽑습니다.");
						user.CardAdd();

						try {
							Thread.sleep(1000); // 1초 딜레이
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						System.out.println("딜러의 카드를 1장 뽑습니다.");
						dealer.CardAdd();
					}

					
					

				}
				break;

			case 3:
				System.out.println("게임을 종료하겠습니다.");
				break loop;
			}
		}

	}

}
