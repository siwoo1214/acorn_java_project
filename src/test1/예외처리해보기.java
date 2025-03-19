package test1;

public class 예외처리해보기 {
	public int sum(int a, int b) {
		if(a<0 || b<0) {
			throw new 예외처리prac();
		}
		return a+b;
	}
	
	public static void main(String[] args) {
	    예외처리해보기 a = new 예외처리해보기();

	    try {
	        a.sum(5, -3); // 예외 발생!
	    } catch (예외처리prac e) {
	        System.out.println("예외 발생: " + e.getMessage());
	    }

	    // 예외 발생 후에도 다음 코드가 실행됨
	    System.out.println("정상 실행");
	    System.out.println("결과: " + a.sum(5, 3)); // 정상 실행됨
	}

}
