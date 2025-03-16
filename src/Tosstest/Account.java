package Tosstest;

public class Account {

	long Accountnum;
	String username;
	String bankname;
	int balance;
	String accountType;
	int point; // ✅ 포인트 추가

	public Account() {

	}

	public Account(long accountnum, String username, String bankname, int balance, String accountType) {
		this.Accountnum = accountnum;
		this.username = username;
		this.bankname = bankname;
		this.balance = balance;
		this.accountType = accountType;
	}

	// ✅ 포인트 추가 생성자
	public Account(long accountnum, String username, String bankname, int balance, String accountType, int point) {
		this.Accountnum = accountnum;
		this.username = username;
		this.bankname = bankname;
		this.balance = balance;
		this.accountType = accountType;
		this.point = point;
	}

	@Override
	public String toString() {
	    return "계좌 정보 → [이름: " + getUsername() +
	           ", 계좌번호: " + getAccountnum() +
	           ", 은행: " + getBankname() +
	           ", 잔액: " + getBalance() +
	           ", 유형: " + getAccountType() +
	           ", 포인트: " + getPoint() + "]"; // ✅ 개행 및 공백 제거 강화
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

	public void setBalance(int newBalance) {
		this.balance = newBalance;
		AccountManager.updateAccount(this);
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	// ✅ 포인트 getter/setter 추가
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
		AccountManager.updateAccount(this);
	}

	public String toFileString() {
	    return getAccountnum() + "," +
	           (getUsername() != null ? getUsername() : "N/A") + "," +
	           (getBankname() != null ? getBankname() : "N/A") + "," +
	           getBalance() + "," +
	           (getAccountType() != null ? getAccountType() : "N/A") + "," +
	           getPoint();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Account account = (Account) obj;

		return String.valueOf(Accountnum).trim().equals(String.valueOf(account.getAccountnum()).trim());
	}
}
