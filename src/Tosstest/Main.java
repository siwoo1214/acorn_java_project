package Tosstest;

import Tosstest.GUI.AccountFrame;
import Tosstest.GUI.LoginFrame;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	// ✅ 상태 값 관리
	public static User LoggedInUser = null;
	public static Account AccountIn = null;
	public static List<Account> accounts = new ArrayList<>();
	public static List<History> histories = new ArrayList<>();
	public static List<User> users = new ArrayList<>();

	public static JFrame currentFrame;
	public static Component pointLabel;

	public static void main(String[] args) {

		// ✅ 프로그램 시작 시 StockManager 초기화
		new StockManager(); // 가격 변동 시작
		if (Main.accounts.isEmpty()) { // ✅ 이미 값이 존재하면 다시 불러오지 않음
			AccountManager.loadAccounts();
		}
		AccountManager.setAccountInNull(); // ✅ 초기화

		closeCurrentFrame(); // ✅ 기존 실행 프레임 닫기
		currentFrame = new LoginFrame(); // ✅ 로그인 프레임 실행
	}

	public static void closeCurrentFrame() {
		if (currentFrame != null) {
			currentFrame.dispose();
			currentFrame = null;
		}
	}

	public static boolean login(String id, String password) {
		loadUsersFromFile();
		for (User user : users) {
			if (user.id.equals(id) && user.password.equals(password)) {
				LoggedInUser = user;
				return true;
			}
		}
		return false;
	}

	// ✅ 사용자 정보 파일 저장
	public static void saveUserToFile(User user) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/User.txt", true))) {
			writer.write(user.toFileString()); // ✅ 저장 순서는 User.toFileString()에서 처리됨
			writer.newLine();
		} catch (IOException e) {
		}
	}

	// ✅ 사용자 정보 파일 읽기 → 저장 순서와 일치하도록 유지
	public static void loadUsersFromFile() {
		users.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader("res/User.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 3) {
					String id = data[0];
					String password = data[1];
					String name = data[2];
					users.add(new User(id, password, name));
				}
			}
		} catch (IOException e) {
		}
	}

	// ✅ 사용자 ID 중복 체크
	public static boolean isUserExists(String id) {
		loadUsersFromFile();
		for (User user : users) {
			if (user.id.equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static void saveAccountToFile(Account account) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/Account.txt", true))) { // ✅ 추가 모드로 설정
			writer.write(account.toFileString());
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadAccountsFromFile() {
		if (!accounts.isEmpty())
			return; // ✅ 기존 값이 있으면 다시 불러오지 않음

		try (BufferedReader reader = new BufferedReader(new FileReader("res/Account.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("Raw Line: " + line); // ✅ 원본 데이터 출력

				String[] data = line.split(",");

				if (data.length == 6) {
					try {
						long accountNum = Long.parseLong(data[0].trim());
						String username = data[1].trim();
						String bankname = data[2].trim();
						int balance = Integer.parseInt(data[3].trim());
						String accountType = data[4].trim();
						int point = Integer.parseInt(data[5].trim());

						System.out.println("Account Loaded: " + accountNum + ", " + username);

						if ("입출금 계좌".equals(accountType)) {
							BankAccount account = new BankAccount(accountNum, username, bankname, balance);
							account.setPoint(point);
							accounts.add(account);
						} else if ("주식 계좌".equals(accountType)) {
							StockAccount account = new StockAccount(accountNum, username, bankname, balance);
							account.setPoint(0);
							accounts.add(account);
						}
					} catch (NumberFormatException e) {
						System.out.println("❌ 잘못된 숫자 형식: " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			System.out.println("❌ 파일 읽기 오류: " + e.getMessage());
		}
	}

	// ✅ 송금 및 인출 내역 파일 저장
	public static void saveHistoryToFile(History history) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/History.txt", true))) {
			writer.write(history.toFileString());
			writer.newLine();

		} catch (IOException e) {
			System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
			e.printStackTrace(); // ✅ 오류 추적
		}
	}

	// ✅ 내역 불러오기 함수 추가
	public static void loadHistoriesFromFile() {
		histories.clear(); // ✅ 기존 내역 삭제
		try (BufferedReader reader = new BufferedReader(new FileReader("res/History.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length != 7) {
					System.out.println("오류: 잘못된 데이터 형식 → " + line);
					continue;
				}

				String senderName = data[0];
				long senderAccountNum = Long.parseLong(data[1]);
				String receiverName = data[2];
				long receiverAccountNum = Long.parseLong(data[3]);
				int amount = Integer.parseInt(data[4]);
				java.time.LocalDateTime transactionTime = java.time.LocalDateTime.parse(data[5]);
				String transactionType = data[6];

				// ✅ 현재 접속한 계좌의 계좌 번호와 일치하는 경우에만 추가
				if (Main.AccountIn != null && senderAccountNum == Main.AccountIn.getAccountnum()) {
					History history = new History(senderName, senderAccountNum, receiverName, receiverAccountNum,
							amount, transactionTime, transactionType);
					histories.add(history);
				}
			}
		} catch (IOException e) {
			System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
