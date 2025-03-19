package day21.interrupt.실습;

public class Ex01 {
	public static void main(String[] args) {
		Counter c = new Counter();
		c.start();
		
		try {
			Thread.sleep(3000);  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//c쓰레드 신호주기
		c.interrupt();  //c쓰레드에게 신호주기
		
		System.out.println("프로그램 종료");
		
		
		
	}
}
