package Tosstest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class StockAccount extends Account {
	private HashMap<String, Integer> ownedStocks = new HashMap<>();  //갖고있는 주식목록
	private HashMap<String, Integer> purchasePrices = new HashMap<>();  //새로 구입한 주식

	public StockAccount(long accountnum, String username, String bankname, int balance) {
		super(accountnum, username, bankname, balance, "주식 계좌", 0); // ✅ 포인트 값 저장
		loadOwnedStocks(); // ✅ 파일에서 보유 주식 상태 불러오기
	}

	@Override
	public String toString() {
	    return "주식계좌정보 [ 이름:" + username +
	           ", 계좌번호:" + Accountnum +
	           ", 은행:" + bankname +
	           ", 예치금:" + balance + "원 ]";
	}


	public boolean buyStock(String stockName, int stockPrice, int quantity) {
		if (getBalance() < stockPrice * quantity) {
			try {
				throw new 잔고예외처리();
			} catch (잔고예외처리 e) {
				System.out.println("잔고가 부족하여 실행할 수 없습니다");
				return false; // 잔액 부족
			}
		}

		// ✅ 주식을 이미 보유 중이라면 수량 증가 & 매수가 갱신
		if (ownedStocks.containsKey(stockName)) {
			int newQuantity = ownedStocks.get(stockName) + quantity;
			ownedStocks.put(stockName, newQuantity);

			// ✅ 매수가를 평균 가격으로 조정
			int oldPrice = purchasePrices.get(stockName);
			int totalCost = (oldPrice * (newQuantity - quantity)) + (stockPrice * quantity);
			purchasePrices.put(stockName, totalCost / newQuantity);
		} else {  //아니면 해시맵에 새로운 객체 추가
			ownedStocks.put(stockName, quantity);
			purchasePrices.put(stockName, stockPrice);
		}

		// ✅ 잔액 수정 → 구입한 가격을 balance에서 차감하기
		setBalance(getBalance() - (stockPrice * quantity));

		// ✅ 보유 상태 저장
		saveState();

		return true;
	}

	// 주식 판매
	public boolean sellStock(String stockName, int quantity, int sellPrice) {
		if (!ownedStocks.containsKey(stockName) || ownedStocks.get(stockName) < quantity) {
			try {
				throw new 주식수량예외처리();
			} catch (주식수량예외처리 e) {
				System.out.println("주식의 수량이 부족하여 실행할 수 없습니다");
			}
			// 주식이 없으면 판매할 수 없고, 보유한 주식보다 많은 수량을 판매할 수 없음
		}

		// ✅ 보유 주식 수량 감소
		int remainingQuantity = ownedStocks.get(stockName) - quantity;
		if (remainingQuantity == 0) {  //판매하려는 주식이 없으면 해시맵에서 제거
			ownedStocks.remove(stockName);
			purchasePrices.remove(stockName);
		} else {  //아니면 차감된 갯수로 갱신
			ownedStocks.put(stockName, remainingQuantity);
		}

		// ✅ 판매 금액 추가
		setBalance(getBalance() + (sellPrice * quantity));

		// ✅ 판매 내역 저장
		History history = new History(this.getUsername(), this.getAccountnum(), "-", 0, (sellPrice * quantity),
				LocalDateTime.now(), "판매");
		Main.histories.add(history);
		Main.saveHistoryToFile(history);

		// ✅ 상태 저장
		saveState();
		return true;
	}

	private void saveState() {
		saveOwnedStocks(); // ✅ 보유 상태 저장
		AccountManager.updateAccount(this); // ✅ 잔액 상태 저장
	}

	private void saveOwnedStocks() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/" + getUsername() + "_stocks.txt"))) {
			for (String stock : ownedStocks.keySet()) {
				writer.write(stock + "," + ownedStocks.get(stock) + "," + purchasePrices.get(stock));
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ✅ 보유 주식 상태 불러오기
	private void loadOwnedStocks() {
		// 해시맵을 비우고 시작
		ownedStocks.clear();
		purchasePrices.clear();

		// 로그인된 유저 정보에 맞는 주식해시맵 반환하기 위해서
		File file = new File("res/" + getUsername() + "_stocks.txt");
		if (!file.exists())
			return;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length < 3)
					continue;

				String stockName = data[0];
				int quantity = Integer.parseInt(data[1]);
				int price = Integer.parseInt(data[2]);

				ownedStocks.put(stockName, quantity);
				purchasePrices.put(stockName, price);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ✅ 특정 주식의 보유 수량 반환
	public int getStockQuantity(String stockName) {
		return ownedStocks.getOrDefault(stockName, 0);
	}

	// ✅ 특정 주식의 매수가 반환
	public int getPurchasePrice(String stockName) {
		return purchasePrices.getOrDefault(stockName, -1);
	}

	//로그인된 계정의 balance를 업데이트
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

	@Override
	public String toFileString() {
		return getAccountnum() + "," + getUsername() + "," + getBankname() + "," + getBalance() + "," + getAccountType()
				+ "," + 0; // ✅ 의미 없는 값 추가 (항상 0 저장)
	}
}
