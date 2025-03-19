package day21.interrupt.실습;

public class Ex03 {
	public static void main(String[] args) {
		Counter3 c = new Counter3();
		c.start();
		
		try {
			Thread.sleep(3000); //3초뒤에 main실행문 실행
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		c.interrupt(); //c쓰레드에게 신호보내기
		System.out.println("프로그램 종료");
	}
}
