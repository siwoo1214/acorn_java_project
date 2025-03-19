package day21.비동기.실습;

/*
 *  추상메소드
 * interface Runnable{
 * 		void run();
 * 
 * }
 */

//Runnable 인터페이스를 구현하는 방법(익명으로 구현하는 방법)
public class Ex04 {
	public static void main(String[] args) {

//		for(int i=0; i<100; i++){
//			System.err.println("하이");
//		}
//		

		// 람다함수로 Runnable의 run함수를 실행시키는 코드
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100; i++) {
					System.out.println("하이");
				}
			}
		});
		th.start();

		
		//                          Runnable(){} => 익명클래스 작성
		//           new 익명클래스                 => 익명 클래스로 객체 생성
		
		Thread th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100; i++) {
					System.err.println("바이");
				}
			}
		});
		
		th2.start();
	}
}
