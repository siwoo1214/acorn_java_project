package Tosstest;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

		// ✅ 계좌 개설 수정
		if (type.equals("입출금 계좌")) {
			// ✅ 입출금 계좌는 정확히 BankAccount로 생성
			BankAccount newAccount = new BankAccount(AccountNum, username, bank, amount);
			Main.accounts.add(newAccount);
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

		// ✅ 사용자 계좌 목록만 표시
		String[] accountOptions = userAccounts.stream().map(Account::toString).toArray(String[]::new);

		String selectedAccount = (String) JOptionPane.showInputDialog(null, "해지할 계좌를 선택하세요:", "계좌 해지",
				JOptionPane.PLAIN_MESSAGE, null, accountOptions, accountOptions[0]);

		if (selectedAccount != null) {
			// ✅ 선택된 계좌 삭제
			Account removedAccount = null;
			for (Account account : userAccounts) {
				if (account.toString().equals(selectedAccount)) {
					removedAccount = account;
					break;
				}
			}

			if (removedAccount != null) {
				Main.accounts.remove(removedAccount);
				JOptionPane.showMessageDialog(null, "계좌가 성공적으로 해지되었습니다!");

				// ✅ 파일에서 계좌 정보 업데이트
				updateAccountFile();
			}
		}
	}

	// ✅ 파일에서 계좌 정보 업데이트
	static void updateAccountFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/Account.txt"))) {
			for (Account account : Main.accounts) {
				writer.write(account.toFileString());
				writer.newLine();
			}
			JOptionPane.showMessageDialog(null, "파일이 성공적으로 업데이트되었습니다.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일 업데이트 중 오류 발생: " + e.getMessage());
		}
	}

	public void AccountHistory(Scanner sc) {
		System.out.println("=== 송금 및 인출 내역 조회 ===");

		if (Main.AccountIn == null) {
			System.out.println("연결된 계좌가 없습니다.");
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
			System.out.println("해당 계좌에 송금 및 인출 내역이 없습니다.");
		} else {
			for (History h : Main.histories) {
				System.out.println(h);
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

		String selectedAccount = (String) JOptionPane.showInputDialog(null, "수정할 계좌를 선택하세요:", "계좌 정보 수정",
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
				// ✅ 새 은행명 선택 방식으로 수정
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
				updateAccountFile();
			}
		}
	}

	public static void setAccountInNull() {
		Main.AccountIn = null;
	}
}
