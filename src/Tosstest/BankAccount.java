package Tosstest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BankAccount extends Account {

	public BankAccount(long accountnum, String username, String bankname, int balance) {
		super(accountnum, username, bankname, balance, "입출금 계좌", 10000); // ✅ 포인트 기본값 설정
	}

	public void deposit(String receiverName, long receiverAccountNum, int amount) {
		if (amount <= 0) {
			return;
		}

		this.setBalance(this.getBalance() - amount);
		History history = new History(this.getUsername(), this.getAccountnum(), receiverName, receiverAccountNum,
				amount, java.time.LocalDateTime.now(), "입금");

		Main.histories.add(history); // ✅ 값 저장 확인
		Main.saveHistoryToFile(history); // ✅ 내역 저장 추가
		AccountManager.updateAccount(this);
	}

	public void withdraw(int amount) {
		if (amount <= 0 || amount > this.getBalance()) {
			return;
		}

		this.setBalance(this.getBalance() - amount);
		History history = new History(this.getUsername(), this.getAccountnum(), "-", 0, amount,
				java.time.LocalDateTime.now(), "출금");

		Main.histories.add(history); // ✅ 값 저장 확인
		Main.saveHistoryToFile(history); // ✅ 내역 저장 추가
		AccountManager.updateAccount(this);
	}

	public void saveHistoryToFile(History history) { // ✅ public으로 수정
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/History.txt", true))) {
			writer.write(history.toFileString());
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
	    return "입출금계좌정보 [ 이름:" + username +
	           ", 계좌번호:" + Accountnum +
	           ", 은행:" + bankname +
	           ", 예치금:" + balance +
	           "원, 포인트:" + getPoint() + "P ]"; // ✅ 개행 제거 및 일관성 강화
	}


	@Override
	public void setBalance(int newBalance) {
		super.setBalance(newBalance);

		for (int i = 0; i < Main.accounts.size(); i++) {
			if (Main.accounts.get(i).getAccountnum() == getAccountnum()) {
				Main.accounts.set(i, this);
				break;
			}
		}

		// ✅ 중복 추가 방지 강화
		AccountManager.updateAccount(this);
	}

}
