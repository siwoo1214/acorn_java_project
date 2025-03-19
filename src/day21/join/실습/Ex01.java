package day21.join.실습;

public class Ex01 {
	public static void main(String[] args) {
		Counter th = new Counter();
		th.start();
		
		//시간끌기
		//작업 수행 흉내
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			th.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("메인 프로그램 종료");
		
	}
}
