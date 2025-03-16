package Tosstest;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Tosstest.GUI.BankFrame;
import Tosstest.GUI.StockFrame;

public class AccountManager extends Account {

	Scanner sc = new Scanner(System.in);
	private static ArrayList<Account> accounts = new ArrayList<>();

	public void OpenedAccount() {
		String username = Main.LoggedInUser.getName();
		if (username == null || username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "로그인 상태를 확인하세요.");
			return;
		}

		// ✅ 은행 선택
		String[] banks = { "신한은행", "우리은행", "하나은행", "IBK기업은행", "KB국민은행" };
		String bank = (String) JOptionPane.showInputDialog(null, "은행을 선택하세요:", "은행 선택", JOptionPane.PLAIN_MESSAGE, null,
				banks, banks[0]);

		if (bank == null) {
			JOptionPane.showMessageDialog(null, "계좌 개설이 취소되었습니다.");
			return;
		}

		// ✅ 계좌 유형 선택
		String[] types = { "입출금 계좌", "주식 계좌" };
		String type = (String) JOptionPane.showInputDialog(null, "계좌 유형을 선택하세요:", "계좌 유형 선택", JOptionPane.PLAIN_MESSAGE,
				null, types, types[0]);

		if (type == null || type.isEmpty()) {
			JOptionPane.showMessageDialog(null, "계좌 개설이 취소되었습니다.");
			return;
		}

		// ✅ 금액 입력 받기
		String amountStr = JOptionPane.showInputDialog("입금 금액을 입력하세요:");
		if (amountStr == null || amountStr.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "금액 입력이 취소되었습니다.");
			return;
		}

		int amount;
		try {
			amount = Integer.parseInt(amountStr);
			if (amount < 0) {
				JOptionPane.showMessageDialog(null, "금액은 음수일 수 없습니다.");
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "숫자를 입력해야 합니다.");
			return;
		}

		long AccountNum = (long) (Math.random() * 1000000000);

		if (type.equals("입출금 계좌")) {
			// ✅ 입출금 계좌 생성 시 포인트 값 자동 설정
			BankAccount newAccount = new BankAccount(AccountNum, username, bank, amount);
			Main.accounts.add(newAccount);

			// ✅ 포인트 값 상태 저장 추가
			Main.saveAccountToFile(newAccount);

			JOptionPane.showMessageDialog(null, "계좌 개설 완료!\n" + newAccount.toString());
		} else if (type.equals("주식 계좌")) {
			StockAccount newAccount = new StockAccount(AccountNum, username, bank, amount);
			Main.accounts.add(newAccount);
			Main.saveAccountToFile(newAccount);

			JOptionPane.showMessageDialog(null, "계좌 개설 완료!\n" + newAccount.toString());
		} else {
			JOptionPane.showMessageDialog(null, "잘못된 계좌 유형입니다.");
		}
	}

	public void ConnectAccount() {
		Main.loadAccountsFromFile();

		if (Main.LoggedInUser == null) {
			JOptionPane.showMessageDialog(null, "로그인 상태를 확인하세요.");
			return;
		}

		String currentUser = Main.LoggedInUser.getName();

		// ✅ 로그인한 사용자 계좌만 필터링
		List<Account> userAccounts = new ArrayList<>();
		for (Account account : Main.accounts) {
			if (account.getUsername().equals(currentUser)) {
				userAccounts.add(account);
			}
		}

		if (userAccounts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "연동할 계좌가 없습니다.");
			return;
		}

		// ✅ 사용자 계좌 목록 표시
		String[] accountOptions = userAccounts.stream().map(Account::toString).toArray(String[]::new);

		String selectedAccount = (String) JOptionPane.showInputDialog(null, "연동할 계좌를 선택하세요:", "계좌 연동",
				JOptionPane.PLAIN_MESSAGE, null, accountOptions, accountOptions[0]);

		// ✅ 취소 시 창 닫히지 않음
		if (selectedAccount == null) {
			JOptionPane.showMessageDialog(null, "연동이 취소되었습니다.");
			return;
		}

		for (Account account : userAccounts) {
			if (account.toString().equals(selectedAccount)) {
				Main.AccountIn = account;
				JOptionPane.showMessageDialog(null, "계좌가 성공적으로 연동되었습니다!");

				// ✅ 계좌 유형에 따라 다른 Frame으로 이동
				if (account.getAccountType().equals("입출금 계좌")) {
					new BankFrame();
				} else if (account.getAccountType().equals("주식 계좌")) {
					new StockFrame();
				}
				return;
			}
		}
	}

	public void CancelAccount() {
		Main.loadAccountsFromFile();

		// ✅ 로그인 상태 확인
		if (Main.LoggedInUser == null) {
			JOptionPane.showMessageDialog(null, "로그인 상태를 확인하세요.");
			return;
		}

		String currentUser = Main.LoggedInUser.getName();

		// ✅ 로그인한 사용자의 계좌만 필터링
		List<Account> userAccounts = new ArrayList<>();
		for (Account account : Main.accounts) {
			if (account.getUsername().equals(currentUser)) {
				userAccounts.add(account);
			}
		}

		if (userAccounts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "해지할 계좌가 없습니다.");
			return;
		}

		// ✅ 사용자 계좌 목록 표시 (개행 문자 제거 및 트리밍)
		String[] accountOptions = userAccounts.stream().map(account -> account.toString().replace("\n", "").trim())
				.toArray(String[]::new);

		String selectedAccount = (String) JOptionPane.showInputDialog(null, "해지할 계좌를 선택하세요:", "계좌 해지",
				JOptionPane.PLAIN_MESSAGE, null, accountOptions, accountOptions[0]);

		if (selectedAccount != null) {
			// ✅ 선택된 계좌 찾기
			Account removedAccount = null;
			for (Account account : userAccounts) {
				if (selectedAccount.equals(account.toString().replace("\n", "").trim())) {
					removedAccount = account;
					break;
				}
			}

			if (removedAccount != null) {
				long targetAccountNum = removedAccount.getAccountnum();

				// ✅ 현재 로그인한 사용자 계좌만 삭제
				boolean isRemoved = Main.accounts.removeIf(account -> account.getAccountnum() == targetAccountNum
						&& account.getUsername().equals(Main.LoggedInUser.getName()));

				if (isRemoved) {
					JOptionPane.showMessageDialog(null, "계좌가 성공적으로 해지되었습니다!");
					saveAccounts();
				} else {
					JOptionPane.showMessageDialog(null, "계좌 삭제에 실패했습니다.");
				}
			}
		}
	}

	// ✅ 파일 상태 저장 강화
	public static void updateAccountFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/Account.txt", false))) { // ✅ 덮어쓰기 모드
			for (Account account : Main.accounts) {
				writer.write(account.toFileString());
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
		}
	}

	public void AccountHistory(Scanner sc) {
		if (Main.AccountIn == null) {
			return;
		}

		Main.histories.clear();

		try (BufferedReader reader = new BufferedReader(new FileReader("res/History.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data[1].equals(Long.toString(Main.AccountIn.getAccountnum()))
						|| data[3].equals(Long.toString(Main.AccountIn.getAccountnum()))) {
					History history = new History(data[0], Long.parseLong(data[1]), data[2], Long.parseLong(data[3]),
							Integer.parseInt(data[4]), java.time.LocalDateTime.parse(data[5]), data[6]);

					Main.histories.add(history);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Main.histories.isEmpty()) {
		} else {
			for (History h : Main.histories) {
			}
		}
	}

	public void UpdateAccount() {
		Main.loadAccountsFromFile();

		if (Main.LoggedInUser == null) {
			JOptionPane.showMessageDialog(null, "로그인 상태를 확인하세요.");
			return;
		}

		String currentUser = Main.LoggedInUser.getName();

		// ✅ 로그인한 사용자 계좌만 필터링
		List<Account> userAccounts = new ArrayList<>();
		for (Account account : Main.accounts) {
			if (account.getUsername().equals(currentUser)) {
				userAccounts.add(account);
			}
		}

		if (userAccounts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "수정할 계좌가 없습니다.");
			return;
		}

		// ✅ 사용자 계좌 목록 표시
		String[] accountOptions = userAccounts.stream().map(Account::toString).toArray(String[]::new);

		String selectedAccount = (String) JOptionPane.showInputDialog(null, "수정할 계좌를 선택하세요:", "계좌 수정",
				JOptionPane.PLAIN_MESSAGE, null, accountOptions, accountOptions[0]);

		if (selectedAccount != null) {
			Account selected = null;
			for (Account account : userAccounts) {
				if (account.toString().equals(selectedAccount)) {
					selected = account;
					break;
				}
			}

			if (selected != null) {
				// ✅ 새 은행명 선택
				String[] banks = { "신한은행", "우리은행", "하나은행", "IBK기업은행", "KB국민은행" };
				String newBank = (String) JOptionPane.showInputDialog(null, "새 은행명을 선택하세요:", "은행명 수정",
						JOptionPane.PLAIN_MESSAGE, null, banks, selected.getBankname());

				if (newBank != null && !newBank.trim().isEmpty()) {
					selected.setBankname(newBank);
				} else {
					JOptionPane.showMessageDialog(null, "은행명이 선택되지 않았습니다.");
					return;
				}

				// ✅ 새 금액 입력 받기
				String newAmountStr = JOptionPane.showInputDialog("새 잔액을 입력하세요:",
						String.valueOf(selected.getBalance()));

				if (newAmountStr != null && !newAmountStr.trim().isEmpty()) {
					try {
						int newAmount = Integer.parseInt(newAmountStr);
						if (newAmount >= 0) {
							selected.setBalance(newAmount);
							// ✅ 수정된 계좌 정보 저장
							AccountManager.updateAccount(selected);
						} else {
							JOptionPane.showMessageDialog(null, "금액은 음수일 수 없습니다.");
							return;
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "숫자를 입력해야 합니다.");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "잔액이 입력되지 않았습니다.");
					return;
				}

				JOptionPane.showMessageDialog(null, "계좌 정보가 성공적으로 수정되었습니다!");
				// ✅ 파일에서 상태 업데이트
				AccountManager.updateAccount(selected);
			}
		}
	}

	public static void updateAccount(Account updatedAccount) {
		System.out.println(updatedAccount);
	    for (int i = 0; i < Main.accounts.size(); i++) {
	        // ✅ 디버그용 출력 추가
	        System.out.println("Account Number in List: '" + Main.accounts.get(i).getAccountnum() + "'");
	        System.out.println("Target Account Number: '" + updatedAccount.getAccountnum() + "'");

	        // ✅ 정확히 값이 매칭되도록 trim 적용
	        if (String.valueOf(Main.accounts.get(i).getAccountnum()).trim()
	                .equals(String.valueOf(updatedAccount.getAccountnum()).trim())) {

	            System.out.println("✅ Account Matched! Updating...");

	            // ✅ 기존 계좌 정보 수정
	            Main.accounts.set(i, updatedAccount);
	            saveAccounts(); // ✅ 수정된 내용 저장
	            System.out.println("✅ Account Updated and Saved!");
	            break;
	        }
	    }
	}


	public static void saveAccounts() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/Account.txt", false))) {
			for (Account account : Main.accounts) {
				writer.write(account.toFileString());
				writer.newLine(); // ✅ 한 줄씩 개행 추가
			}
			writer.flush(); // ✅ 버퍼 강제 출력
			System.out.println("✅ 계좌 정보 저장 완료");
		} catch (IOException e) {
			System.out.println("❌ 파일 저장 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void loadAccounts() {
		if (!accounts.isEmpty())
			return;

		try (BufferedReader reader = new BufferedReader(new FileReader("res/Account.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 5) {
					try {
						long accountNum = Long.parseLong(data[0].trim());
						String username = data[1].trim();
						String bankname = data[2].trim();
						int balance = Integer.parseInt(data[3].trim());
						String accountType = data[4].trim();

						boolean exists = accounts.stream().anyMatch(acc -> acc.getAccountnum() == accountNum);
						if (!exists) {
							if (accountType.equals("입출금 계좌")) {
								accounts.add(new BankAccount(accountNum, username, bankname, balance));
							} else {
								accounts.add(new StockAccount(accountNum, username, bankname, balance));
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("잘못된 형식: " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			System.out.println("파일 읽기 오류: " + e.getMessage());
		}
	}

	// ✅ 현재 로그인된 사용자 계좌 반환
	public static Account getAccount(String username) {
		for (Account account : accounts) {
			if (account.getUsername().equals(username)) {
				return account;
			}
		}
		return null;
	}

	public static ArrayList<Account> getAccounts() {
		return accounts;
	}

	public static void setAccountInNull() {
		Main.AccountIn = null; // ✅ 값 초기화
	}

}
