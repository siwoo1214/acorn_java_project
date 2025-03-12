package Toss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class AccountManager extends Account {

	Scanner sc = new Scanner(System.in);

	public AccountManager() {

	}

	public void OpenedAccount(Scanner sc) {
		int accountType;
		while (true) {
			System.out.println("생성할 계좌 유형을 선택하세요. 1. 입출금 계좌  2. 주식 계좌");
			accountType = Integer.parseInt(sc.nextLine());

			if (accountType == 1 || accountType == 2) {
				break;
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}

		System.out.println("은행을 선택하세요. 1. 신한은행 2. 우리은행 3. 하나은행 4. IBK기업은행 5. KB국민은행");
		int banknum = Integer.parseInt(sc.nextLine());
		String bank = "";

		switch (banknum) {
		case 1:
			bank = "신한은행";
			break;
		case 2:
			bank = "우리은행";
			break;
		case 3:
			bank = "하나은행";
			break;
		case 4:
			bank = "IBK기업은행";
			break;
		case 5:
			bank = "KB국민은행";
			break;
		default: {
			System.out.println("입력이 잘못됐습니다.");
			return;
		}
		}

		String name = Main.LoggedInUser.getName();
		System.out.println("입금할 금액을 입력하세요: ");
		int money = Integer.parseInt(sc.nextLine());

		int accountNum = (int) (Math.random() * 1000000000);

		Account account;
		if (accountType == 1) {
			account = new BankAccount(accountNum, name, bank, money);
		} else {
			account = new StockAccount(accountNum, name, bank, money);
		}

		Main.accounts.add(account);

		System.out.println("계좌 개설 완료!");
		System.out.println(account);
	}

	public void ConnectAccount(Scanner sc) {
		if (Main.accounts.isEmpty()) {
			System.out.println("연동할 계좌가 없습니다.");
			return;
		}

		System.out.println("연동할 계좌를 선택하세요:");
		for (int i = 0; i < Main.accounts.size(); i++) {
			System.out.println((i + 1) + ". " + Main.accounts.get(i));
		}

		int select = Integer.parseInt(sc.nextLine());
		if (select < 1 || select > Main.accounts.size()) {
			System.out.println("잘못된 입력입니다. 다시 시도하세요.");
			return;
		}

		Main.AccountIn = Main.accounts.get(select - 1);
		if (Main.AccountIn.getAccountType().equals("입출금 계좌")) {

			Main.AccountIn.setAccountType("입출금 계좌");
		} else if (Main.AccountIn.getAccountType().equals("주식 계좌")) {
			Main.AccountIn.setAccountType("주식 계좌");
		}

		System.out.println("계좌가 성공적으로 연동되었습니다!");
		System.out.println("연동된 계좌: " + Main.AccountIn);
	}

	public void CancelAccount(Scanner sc) {
		if (Main.accounts.isEmpty()) {
			System.out.println("해지할 계좌가 없습니다.");
			return;
		}

		System.out.println("해지할 계좌를 선택하세요:");
		for (int i = 0; i < Main.accounts.size(); i++) {
			System.out.println((i + 1) + ". " + Main.accounts.get(i));
		}

		int select = Integer.parseInt(sc.nextLine());
		if (select < 1 || select > Main.accounts.size()) {
			System.out.println("잘못된 입력입니다. 다시 시도하세요.");
			return;
		}

		Main.accounts.remove(select - 1);

		System.out.println("계좌가 성공적으로 해지되었습니다!");
	}

	public void CheckAccount(Scanner sc) {
		if (Main.accounts.isEmpty()) {
			System.out.println("조회할 계좌가 없습니다.");
			return;
		}

		Collections.reverse(Main.accounts);

		for (Account a : Main.accounts) {
			System.out.println(a.toString());
		}
	}

	public void AccountHistory(Scanner sc) {
		System.out.println("=== 입출금 내역 조회 ===");

		if (Main.AccountIn == null) {
			System.out.println("연결된 계좌가 없습니다.");
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader("res/History.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 7) {
					String senderName = data[0];
					long senderAccountNum = Long.parseLong(data[1]);
					String receiverName = data[2];
					long receiverAccountNum = Long.parseLong(data[3]);
					int amount = Integer.parseInt(data[4]);
					String transactionTime = data[5];
					String transactionType = data[6];

					if (senderAccountNum == Main.AccountIn.getAccountnum()
							|| receiverAccountNum == Main.AccountIn.getAccountnum()) {
						History history = new History(senderName, senderAccountNum, receiverName, receiverAccountNum,
								amount, java.time.LocalDateTime.parse(transactionTime), transactionType);

						Main.histories.add(history);
					}
				}
			}

			if (Main.histories.isEmpty()) {
				System.out.println("해당 계좌에 입출금 내역이 없습니다.");
			} else {
				for (History h : Main.histories) {
					System.out.println(h);
				}
			}

		} catch (IOException e) {
			System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
		}
	}

	public void UpdateAccount(Scanner sc) {
		if (Main.accounts.isEmpty()) {
			System.out.println("변경할 계좌가 없습니다.");
			return;
		}

		System.out.println("변경할 계좌를 선택하세요:");
		for (int i = 0; i < Main.accounts.size(); i++) {
			System.out.println((i + 1) + ". " + Main.accounts.get(i));
		}

		int select = Integer.parseInt(sc.nextLine());
		if (select < 1 || select > Main.accounts.size()) {
			System.out.println("잘못된 입력입니다. 다시 시도하세요.");
			return;
		}

		Account selectedAccount = Main.accounts.get(select - 1);

		System.out.print("변경할 은행명을 입력하세요: ");
		String bankname = sc.nextLine();

		System.out.print("변경할 계좌번호를 입력하세요: ");
		long accountNum = Long.parseLong(sc.nextLine());

		System.out.print("변경할 예금주명을 입력하세요: ");
		String username = sc.nextLine();

		selectedAccount.setBankname(bankname);
		selectedAccount.setAccountnum(accountNum);
		selectedAccount.setUsername(username);

		System.out.println("계좌 정보가 성공적으로 변경되었습니다.");
		System.out.println("변경된 계좌 정보: " + selectedAccount);
	}

}
