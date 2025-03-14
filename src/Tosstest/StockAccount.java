package Tosstest;

import java.util.ArrayList;
import java.util.Scanner;

public class StockAccount extends Account {
	ArrayList<Stock> stocklist = new ArrayList<>();
	int totalStockValuation = 0; // 보유 주식 총 가치
	static Scanner sc = new Scanner(System.in);

	public StockAccount(long accountNum, String username, String bankname, int balance) {
		super(accountNum, username, bankname, balance, "주식 계좌");
	}

	// 주식 구매
	public void buyStock() {
		System.out.println("구매할 주식 이름과 수량을 입력하세요.");
		System.out.print("주식 이름: ");
		String name = sc.next();
		System.out.print("수량: ");
		int num = sc.nextInt();

		Stock newStock = new Stock(name, num);
		int result = Main.AccountIn.getBalance();

		if (result <= newStock.pricePerquantity * num) {
			System.out.println("보유금액이 부족하여 주식을 구매할 수 없습니다.");
		} else {
			stocklist.add(newStock);
			result -= newStock.pricePerquantity * num; // 구매한 만큼 잔액 차감
			Main.AccountIn.setBalance(result);
			System.out.printf("%s 주식 %d주를 매수가 %.2f원에 구매했습니다.\n", name, num, (double) newStock.pricePerquantity);
			updatePortfolio();
		}

	}

	// 주식 판매
	public void sellStock() {
		System.out.println("판매할 주식 이름과 수량을 입력하세요.");
		System.out.print("주식 이름: ");
		String name = sc.next();
		System.out.print("수량: ");
		int num = sc.nextInt();
		int result = Main.AccountIn.getBalance();

		if (stocklist.isEmpty()) {
			System.out.println("판매할 주식이 없습니다.");
			return;
		}

		boolean found = false;
		for (int i = 0; i < stocklist.size(); i++) {
			Stock stock = stocklist.get(i);

			if (stock.stockName.equals(name)) {
				if (stock.quantity >= num) {
					double sellPricePerStock = stock.getSellPrice(); // 판매 가격 적용
					double totalSellPrice = sellPricePerStock * num;

					stock.quantity -= num;
					result += totalSellPrice; // 판매한 만큼 잔액 증가
					Main.AccountIn.setBalance(result);

					System.out.printf("%s 주식 %d주를 개당 %.2f원에 판매하였습니다.\n", name, num, sellPricePerStock);
					System.out.printf("총 판매 금액: %.2f원\n", totalSellPrice);

					// 모든 주식을 팔았다면 리스트에서 제거
					if (stock.quantity == 0) {
						stocklist.remove(i);
						System.out.println(name + " 주식을 모두 판매하여 목록에서 제거되었습니다.");
					} else {
						stock.updateSellPrice(); // 남은 주식의 판매 가격 업데이트
					}

					found = true;
					break;
				} else {
					System.out.println("해당 개수만큼 보유하고 있지 않습니다.");
					return;
				}
			}
		}

		if (!found) {
			System.out.println("해당 주식을 보유하고 있지 않습니다.");
		}

		updatePortfolio();
	}

	public void stockCheck() {
		System.out.println("======================보유한 주식 조회======================\n");
		for (Stock tmp : stocklist) {
			System.out.println(tmp);
		}
		System.out.println("========================================================\n");
	}

	// 현재 보유하고 있는 계좌 업데이트
	private void updatePortfolio() {
		setTotalStockValuation();
		System.out.println("현재 계좌 잔액: " + Main.AccountIn.getBalance() + "원\n");

		System.out.println("================= 보유한 주식 정보 =================");
		for (Stock stock : stocklist) {
			System.out.println(stock.toString());
		}
		System.out.println("보유한 주식 총 가치: " + totalStockValuation + "원");
		System.out.println("=================================================\n");
	}

	// 보유한 주식 총 가격 갱신
	public void setTotalStockValuation() {
		totalStockValuation = 0;
		for (Stock stock : stocklist) {
			totalStockValuation += stock.pricePerquantity * stock.quantity;
		}
	}

	@Override
	public String toString() {
		return "주식계좌정보 [ 이름:" + username + ", 계좌번호:" + Accountnum + ", 은행:" + bankname + ", 예치금:" + balance + "원 ]\n";
	}

}
