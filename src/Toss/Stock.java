package Toss;

public class Stock {
    String stockName;  // 주식 이름
    int quantity;  // 보유 수량
    int pricePerquantity;  // 구매 시 1주당 가격
    double sellPrice;  // 판매 가격

    public Stock(String stockName, int quantity) {
        this.stockName = stockName;
        this.quantity = quantity;
        this.pricePerquantity = setBuyPrice(); // 구매 가격 설정
        this.sellPrice = setSellPrice(); // 판매 가격 설정
    }

    // 매수가 랜덤 설정 (1~4000원)
    private int setBuyPrice() {
        return (int) (Math.random() * 3900) + 100;
    }

    // 매도가격 랜덤 설정 (-10% ~ +10% 변동)
    private double setSellPrice() {
        double randomFactor = (Math.random() * 0.2) - 0.1; // -10% ~ +10%
        return Math.round(pricePerquantity * (1 + randomFactor) * 100) / 100.0; // 소수점 2자리까지 반올림
    }

    // 새로운 가격을 설정하는 메서드 (판매 후 자동 업데이트)
    public void updateSellPrice() {
        this.sellPrice = setSellPrice();
    }

    public double getSellPrice() {
        return sellPrice;
    }

    @Override
    public String toString() {
        return "주식명: " + stockName + " | 수량: " + quantity + "주 | 매수가: " + pricePerquantity + "원 | 판매가: " + sellPrice + "원";
    }
}
