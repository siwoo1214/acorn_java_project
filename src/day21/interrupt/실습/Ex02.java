package day21.interrupt.실습;

public class Ex02 {
	public static void main(String[] args) {
		Counter2 c2 = new Counter2();
		c2.start();
		
		System.out.println(c2.isInterrupted());  //false
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Interrupt 신호 도달");
			

		}
		
		//신호주기
		c2.interrupt();
		System.out.println(c2.isInterrupted());  //true
		
		System.out.println("프로그램 종료");
	}
}
