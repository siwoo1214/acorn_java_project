package Tosstest;

@SuppressWarnings("serial")
public class 잔고예외처리 extends RuntimeException{
	public 잔고예외처리(){
		super("잔고가 부족하여 실행할 수 없습니다!");
	}
}
