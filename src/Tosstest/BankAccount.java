package Tosstest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BankAccount extends Account {

	public BankAccount(long accountnum, String username, String bankname, int balance) {
		super(accountnum, username, bankname, balance, "입출금 계좌");
	}

	public void deposit(String receiverName, long receiverAccountNum, int amount) {
		if (amount <= 0) {
			System.out.println("잘못된 입금 금액입니다.");
			return;
		}

		this.setBalance(this.getBalance() - amount);

		History history = new History(this.getUsername(), this.getAccountnum(), receiverName, receiverAccountNum,
				amount, java.time.LocalDateTime.now(), "입금");

		Main.histories.add(history);

		// ✅ 저장 상태 확인
		saveHistoryToFile(history);
		AccountManager.updateAccountFile();
	}

	public void withdraw(int amount) {
		if (amount <= 0 || amount > this.getBalance()) {
			System.out.println("잘못된 출금 금액입니다.");
			return;
		}

		this.setBalance(this.getBalance() - amount);

		History history = new History(this.getUsername(), this.getAccountnum(), "-", 0, amount,
				java.time.LocalDateTime.now(), "출금");

		Main.histories.add(history);

		// ✅ 저장 상태 확인
		saveHistoryToFile(history);
		AccountManager.updateAccountFile();
	}

	public void saveHistoryToFile(History history) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/History.txt", true))) {
			writer.write(history.toFileString());
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "입출금계좌정보 [ 이름:" + username + ", 계좌번호:" + Accountnum + ", 은행:" + bankname + ", 예치금:" + balance + "원 ]\n";
	}
}
