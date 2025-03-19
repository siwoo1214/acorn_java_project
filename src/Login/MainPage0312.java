package Login; // MainPage는 main 패키지에 있음

import java.util.List;
import java.util.Scanner;
import Login.Login;
import Login.User;
import Login.UserFileReader;
import blackjack.blackjack;
import 세븐포커.Main;
import 야구게임.NumberBaseballGame;
import 조커뽑기.JokerDrawGame;
import 카드뒤집기.카드뒤집기게임;

public class MainPage0312 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<User> users = UserFileReader.loadUsers();
		int coins = Login.login(); // 로그인 하는 메서드, 로그인 성공하면 코인 개수 반환.
		User currentUser = users.get(0);

		loop: while (true) {
			if (coins <= 0) {
				System.out.println("게임을 시작하려면 코인을 넣어주세요 (최소 1개 필요)");
				coins = sc.nextInt();

				currentUser.setCoin(coins);
				continue;
			}

			System.out.println("남은 코인: " + coins);
			System.out.println("1.조커뽑기, 2.숫자야구, 3.카드뒤집기, 4.세븐포커, 5.블랙잭, 6.종료");
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				// 1번을 선택하면 조커뽑기 게임 실행
				JokerDrawGame.startNewGame(); // JokerDrawGame의 main 메소드 호출
				coins--;
				currentUser.setCoin(coins);// 게임한판 할때마다 코인1개씩 감소
				break;

			case 2:
				NumberBaseballGame baseballGame = new NumberBaseballGame();
				baseballGame.start(); // NumberBaseballGame의 start 메소드 호출
				coins--;
				currentUser.setCoin(coins);
				break;

			case 3:
				카드뒤집기게임.startgame(); // 카드뒤집기게임의 main 메소드 호출
				coins--;
				currentUser.setCoin(coins);
				break;

			case 4:
				Main.main(new String[] {}); // 세븐포커의 main 메소드 호출
				coins--;
				currentUser.setCoin(coins);
				break;

			case 5:
				blackjack.main(new String[] {});
				coins--;
				currentUser.setCoin(coins);
				break;

			case 6:
				// 코인이 남아있으면 종료되지 않고 게임이 게속 진행되도록
				UserFileWriter.saveUsers(users);
					System.out.println("게임을 종료합니다.");
					break loop;

			default:
				// 잘못된 입력 처리
				System.out.println("메뉴가 잘못 입력되었습니다.");
			}
		}

		sc.close(); // Scanner 자원 반환
	}
	
}
