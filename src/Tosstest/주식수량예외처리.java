package Tosstest;

@SuppressWarnings("serial")
public class 주식수량예외처리 extends RuntimeException{
	public 주식수량예외처리() {
		super("주식의 수량이 부족하여 처리할 수 없습니다");
	}
}
