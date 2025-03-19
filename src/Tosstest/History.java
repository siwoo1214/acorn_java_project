package Tosstest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History {
	private String senderName; // 보내는사람 이름
	private long senderAccountNum; // 보내는사람 계좌번호
	private String receiverName; // 받는사람 이름
	private long receiverAccountNum; // 받는사람 계좌번호
	private int amount; // 금액
	private LocalDateTime transactionTime; // 입출금 시간
	private String transactionType; // 입출금 유형

	public History(String senderName, long senderAccountNum, String receiverName, long receiverAccountNum, int amount,
			LocalDateTime transactionTime, String transactionType) {
		this.senderName = senderName;
		this.senderAccountNum = senderAccountNum;
		this.receiverName = receiverName;
		this.receiverAccountNum = receiverAccountNum;
		this.amount = amount;
		this.transactionTime = transactionTime;
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedTime = transactionTime.format(formatter);

		return String.format("%s: %s (%d) → %s (%d) | 금액: %,d원 | 시간: %s", transactionType, senderName, senderAccountNum,
				receiverName, receiverAccountNum, amount, formattedTime);
	}

	public String toFileString() {
		return senderName + "," + senderAccountNum + "," + receiverName + "," + receiverAccountNum + "," + amount + ","
				+ transactionTime + "," + transactionType;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public long getSenderAccountNum() {
		return senderAccountNum;
	}

	public void setSenderAccountNum(long senderAccountNum) {
		this.senderAccountNum = senderAccountNum;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public long getReceiverAccountNum() {
		return receiverAccountNum;
	}

	public void setReceiverAccountNum(long receiverAccountNum) {
		this.receiverAccountNum = receiverAccountNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
