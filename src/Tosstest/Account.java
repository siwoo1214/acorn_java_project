package Tosstest;

public class Account {

	long Accountnum; // 계좌번호
	String username; // 예금주명
	String bankname; // 은행명
	int balance; // 잔액
	String accountType; // 계좌 타입 (입출금/주식)

	public Account() {

	}

	public Account(long accountnum, String username, String bankname, int balance, String accountType) {
		this.Accountnum = accountnum;
		this.username = username;
		this.bankname = bankname;
		this.balance = balance;
		this.accountType = accountType;
	}

	public Account(long accountnum, String username, String bankname, int balance) {
		super();
		Accountnum = accountnum;
		this.username = username;
		this.bankname = bankname;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "계좌 유형: " + getAccountType() + ", 은행명: " + bankname + ", 계좌번호: " + Accountnum + ", 예금주명: " + username
				+ ", 잔액: " + balance;
	}

	public long getAccountnum() {
		return Accountnum;
	}

	public void setAccountnum(long accountnum) {
		Accountnum = accountnum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String toFileString() {
		return Accountnum + "," + username + "," + bankname + "," + balance + "," + accountType;
	}

}
