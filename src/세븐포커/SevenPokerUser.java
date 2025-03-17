package 세븐포커;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SevenPokerUser extends SevenPoker {

	ArrayList<String> user1 = new ArrayList<>();
	private boolean cardNumFound;
	private boolean cardFound;

	public SevenPokerUser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void RandomCard() {

		for (int i = 0; i < 7; i++) {
			int shape = random.nextInt(4);
			int cardNum = random.nextInt(13);
//			System.out.print( card[shape][cardNum] + ", ");
			user1.add(card[shape][cardNum]);
		}

		System.out.println("유저 카드: " + user1);

	}

	public boolean Ascending() {

		//
		cardFound = false;
		cardNumFound = false;

		// size()-2를 해서 array의 크기가 넘어가지 않도록함
		for (int i = 0; i < user1.size() - 2; i++) {

			// 카드값 가져오기 (1,2,3) (2,3,4), (3,4,5), (4,5,6), (5,6,7)
			String card1 = user1.get(i);
			String card2 = user1.get(i + 1);
			String card3 = user1.get(i + 2);

//        	System.out.println("card1 : " + card1);
//        	System.out.println();

			// 카드의 숫자 가져오기 (1,2,3) (2,3,4), (3,4,5), (4,5,6), (5,6,7)
			int num1 = getCardValue(card1);
			int num2 = getCardValue(card2);
			int num3 = getCardValue(card3);

//            System.out.println("num1 : " + num1);
//            System.out.println();

			// 카드의 모양 가져오기 (1,2,3) (2,3,4), (3,4,5), (4,5,6), (5,6,7)
			char suit1 = card1.charAt(card1.length() - 1);
			char suit2 = card2.charAt(card2.length() - 1);
			char suit3 = card3.charAt(card3.length() - 1);

//            System.out.print("suit1 : " + suit1);
//            System.out.println("length : " + card1.length() );
//            System.out.println();

			// 배열의 연속적으로 숫자가 같은게 있다면 true, 없으면 false
			if (num1 == num2 && num2 == num3) {
				cardNumFound = true;
				System.out.println("유저의 카드 세트: " + card1 + ", " + card2 + ", " + card3);
				return true;
				// 숫자가 연속적으로 커지고 모양이 같으면 true, 없으면 false
			} else if ((num1 + 1 == num2) && (num2 + 1 == num3) && (suit1 == suit2) && (suit2 == suit3)) {
				cardFound = true;
				System.out.println("유저의 카드 스트레이트: " + card1 + ", " + card2 + ", " + card3);
				return true;
			}

		}
		return false;
	}
	
	

	public void CardAdd() {
		int row = random.nextInt(4); // 0~3 (문양 선택)
		int col = random.nextInt(13); // 0~12 (숫자 선택)
		String newUserCard = card[row][col];

		System.out.println("유저 새로운 카드 추가: " + newUserCard);
		user1.add(newUserCard);

		Collections.sort(user1, new Comparator<String>() {

			@Override
			public int compare(String card1, String card2) {
				// TODO Auto-generated method stub
				return getCardValue(card1) - getCardValue(card2);
			}

		});

		System.out.println("유저 카드 : " + user1);
	}
}
