package 세븐포커;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class SevenPoker {

	String[][] card = { { "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥" },
			{ "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦" },
			{ "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣" },
			{ "A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠" } };

	Random random = new Random();

	// 랜덤으로 카드를 생성하는 매서드
	public void RandomCard() {
	}

	// 카드 추가
	public void CardAdd() {

	}

	// 룰 출력부분
	public void Rule() {
		// txt 파일의 저장된 내용을 불러오는 곳
		try (BufferedReader bf = new BufferedReader(new FileReader("res/SevenPokerRule.txt"))) {

			while (true) {
				String rule = bf.readLine();

				if (rule == null)
					break;

				System.out.println(rule);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	// 카드의 문자열을 숫자의 값으로 변경
	public static int getCardValue(String card) {

		//substring 문자열 자르는 역할을 함
		//0번째부터 card 배열의 -1까지, 즉 뒤에 모양 빼고 문자값만
		String numberPart = card.substring(0, card.length() - 1);

		switch (numberPart) {
		case "A":
			return 1;
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		default:
			return Integer.parseInt(numberPart);
		}
	}

}
