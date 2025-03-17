package 카드뒤집기;

import java.util.Scanner;

public class 카드뒤집기게임 {

	public static void startgame() {
		Scanner sc = new Scanner(System.in);
		MemoryCardGame game = new MemoryCardGame();//

		int computerTurn = 0;
		int userTurn = 0;

		while (!game.isGameOver()) {

			int x1, y1, x2, y2;

			game.printBoard();
			System.out.println("사용자.. 차례??? ");
			while (true) {
				System.out.println("뒤집을 카드를 두장 선택하세요 (1,1 ~ 4,4)");

				while (true) {
					try {
						x1 = sc.nextInt();
						y1 = sc.nextInt();
						x2 = sc.nextInt();
						y2 = sc.nextInt();
						break;
					} catch (Exception e) {
						System.out.println("숫자만 입력 가능합니다");
						sc.nextLine();
						continue;
					}
				}
				x1--; y1--; x2--; y2--;

				if (game.isValidMove(x1, y1, x2, y2)) {

					game.revealed[x1][y1] = true; // 선택한 카드 뒤집기
					game.revealed[x2][y2] = true;

					game.printBoard();

					if (!game.board[x1][y1].equals(game.board[x2][y2])) { // 다른 카드면 다시 숨김
						System.out.println("실패!");
						game.pause(); // 3초 동안 보여주기
						game.revealed[x1][y1] = false; // 다시 숨기기
						game.revealed[x2][y2] = false;

						game.clearConsole();// 콘솔창 밀어올려서 이전 턴 안보이게..

					} else {
						System.out.println("사용자가 카드를 맞췄습니다");
						userTurn++;
					}
					break;
				} else {
					System.out.println("다시 시도하세요.");
					continue;
				}
			}
			if (!game.isGameOver()) {
				System.out.println("컴퓨터... 차례??? "); // 뭐라고해야하지 컴퓨터 턴??
				while (true) {

					do {
						x1 = (int) (Math.random() * game.size);
						y1 = (int) (Math.random() * game.size);
					} while (game.revealed[x1][y1]); // 이미 뒤집힌 카드면 다시 선택

					do {// 이미 뒤집힌 카드거나 첫번째 카드랑 같은 좌표면 다시 선택
						x2 = (int) (Math.random() * game.size);
						y2 = (int) (Math.random() * game.size);
					} while (game.revealed[x2][y2] || (x1 == x2 && y1 == y2));

					if (game.isValidMove(x1, y1, x2, y2)) {
						game.revealed[x1][y1] = true;
						game.revealed[x2][y2] = true;

						game.printBoard();

						if (!game.board[x1][y1].equals(game.board[x2][y2])) { // 다른 카드면 다시 숨김
							System.out.println("실패!");
							game.pause(); // 2초 동안 보여주기
							game.revealed[x1][y1] = false;
							game.revealed[x2][y2] = false;

							game.clearConsole();
						} else {
							System.out.println("컴퓨터가 카드를 맞췄습니다");
							computerTurn++;
						}
						break; // 정상적으로 턴이 끝나면
					}
				}

			}
		}
		System.out.println("\n\n모든 카드를 뒤집었습니다.");
		if (userTurn > computerTurn) {
			System.out.println("\n사용자 승리~~~~");
		} else {
			System.out.println("\n컴퓨터 승리 ㅜㅜ");
		}
		System.out.println(userTurn + " : " + computerTurn + "\n게임을 종료합니다");
	}

}
