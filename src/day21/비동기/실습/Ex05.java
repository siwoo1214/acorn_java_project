package day21.비동기.실습;

/*
 *  추상메소드
 * interface Runnable{
 * 		void run();
 * 
 * }
 */


public class Ex05 {
	public static void main(String[] args) {
	
		//Runnable인터페이스를 구현하는 3번째 방법 : 람다식
		//익명클래스를 더 축약된 형태로 사용 가능함
		//구현해야 할 메소드(추상메소드)가 한 개인 인터페이스만 가능하다
		
		for(int i=0; i<=100; i++) {
			System.out.println("하이");
		}

		//익명클래스를 축약된 형태로 사용할 수 있다
		Thread th = new Thread(()->{
			for(int i=0; i<=100; i++) {
				System.out.println("하이");
			}
		});
		
		th.start();
	}
}

