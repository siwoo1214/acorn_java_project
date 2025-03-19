package 카드뒤집기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MemoryCardGame {

	int size = 4;
	int seconds = 3;
	String[][] board = new String[size][size];
	boolean[][] revealed = new boolean[size][size]; // 카드 공개여부 배열

	public MemoryCardGame() {
		initializeBoard();
	}

	public void initializeBoard() {// 초기화면
		// String[] list = { "A", "A", "B", "B", "C", "C", "D", "D", "E", "E", "F", "F",
		// "G", "G", "H", "H" };
		String[] list = { "🦄", "🦄", "🐶", "🐶", "🐱", "🐱", "🦊", "🦊", "🐷", "🐷", "🐰", "🐰", "🦁", "🦁", "🐸",
				"🐸" };

		List<String> cards = new ArrayList<>(Arrays.asList(list)); // 배열을 리스트로 변환
		Collections.shuffle(cards); // 카드 랜덤 섞기

		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = cards.get(index++);
				revealed[i][j] = false; // 모든 카드 감춤
			}
		}
	}

	public void printBoard() {
		System.out.println("\n현재 게임:");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (revealed[i][j]) {
					System.out.print(board[i][j] + "  "); // 뒤집힌 카드 표시
				} else {
					System.out.print("*  "); // 숨겨진 카드
				}
			}
			System.out.println();
		}
	}

	public boolean isValidMove(int x1, int y1, int x2, int y2) {// 알맞은 입력값인지 확인
		return x1 >= 0 && x1 < size && y1 >= 0 && y1 < size && x2 >= 0 && x2 < size && y2 >= 0 && y2 < size
				&& !revealed[x1][y1] && !revealed[x2][y2] && (x1 != x2 || y1 != y2);
	}

	public boolean isGameOver() {
		for (boolean[] i : revealed) {
			for (boolean j : i) {
				if (!j) // 배열에 false(안뒤집어진 카드)가 있으면 false 반환
					return false;
			}
		}
		return true;
	}

	public void pause() {
		long startTime = System.currentTimeMillis(); // 시작 시간 기록
		long endTime = startTime + seconds * 1000; // 종료 시간 계산 (밀리초 단위)

		while (System.currentTimeMillis() < endTime) {

		}
	}

	public void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

}
