package day21.join.실습;

public class Ex03 {
	public static void main(String[] args) {
		Counter c = new Counter();
		c.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			c.join(5000);  //특정시간만큼 c쓰레드 기다리기
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("프로그램 종료");
	}
}
