package Toss;

import java.time.LocalDateTime;

public class History {
	private String senderName; // 보내는사람 이름
	private long senderAccountNum; // 보내는사람 계좌번호
	private String receiverName; // 받는사람 이름
	private long receiverAccountNum; // 받는사람 계좌번호
	private int amount; // 금액
	private LocalDateTime transactionTime; // 입출금 시간
	private String transactionType; // 입출금 유형

	public History(String senderName, long l, String receiverName, long receiverAccountNum2, int amount,
			LocalDateTime transactionTime, String transactionType) {
		this.senderName = senderName;
		this.senderAccountNum = l;
		this.receiverName = receiverName;
		this.receiverAccountNum = receiverAccountNum2;
		this.amount = amount;
		this.transactionTime = transactionTime;
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return String.format("%s: %s (%d) → %s (%d) | Amount: %,d | Time: %s", transactionType, senderName,
				senderAccountNum, receiverName, receiverAccountNum, amount, transactionTime);
	}

	public String toFileString() {
		return senderName + "," + senderAccountNum + "," + receiverName + "," + receiverAccountNum + "," + amount + ","
				+ transactionTime + "," + transactionType;
	}
}
