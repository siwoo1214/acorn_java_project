package test1;

public class 공백예외처리 extends RuntimeException{
	public 공백예외처리() {
		super("공백이 입력되었으니 제대로 된 정보를 입력하세요");
	}
}
