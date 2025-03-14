package Tosstest;

import java.util.Timer;
import java.util.TimerTask;

public class Stock {
	String stockName; // 주식 이름
	int quantity; // 보유 수량
	int pricePerquantity; // 구매 시 1주당 가격
	double sellPrice; // 판매 가격

	public Stock(String stockName, int quantity) {
		this.stockName = stockName;
		this.quantity = quantity;
		this.pricePerquantity = setBuyPrice(); // 구매 가격 설정
		this.sellPrice = setSellPrice(); // 판매 가격 설정

		// 10초마다 매도가격 갱신
		startPriceUpdate();
	}

	// 매수가 랜덤 설정 
	private int setBuyPrice() {
		return (int) (Math.random() * 100000) + 2000;
	}

	// 매도가격 랜덤 설정 (-10% ~ +10% 변동)
	private double setSellPrice() {
		double randomFactor = (Math.random() * 0.2) - 0.1; // -10% ~ +10%
		return Math.round(pricePerquantity * (1 + randomFactor) * 100) / 100.0; // 소수점 2자리까지 반올림
	}

	// 새로운 가격을 설정하는 메서드 (판매 후 자동 업데이트)
	public void updateSellPrice() {
		this.sellPrice = setSellPrice();
		System.out.println(this); // 업데이트된 가격 출력
	}

	// 일정 시간마다 매도가격 갱신
	private void startPriceUpdate() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				updateSellPrice(); // 10초마다 가격 갱신
			}
		}, 0, 5000); // 5초마다 실행
	}

	public double getSellPrice() {
		return sellPrice;
	}

	@Override
	public String toString() {
		return "주식명: " + stockName + " | 수량: " + quantity + "주 | 매수가: " + pricePerquantity + "원 | 판매가: " + sellPrice
				+ "원";
	}

	// 메인 함수 테스트 코드
	public static void main(String[] args) {
		Stock stock = new Stock("삼성전자", 10);
	}
}
