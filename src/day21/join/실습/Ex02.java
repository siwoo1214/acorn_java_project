package day21.join.실습;

public class Ex02 {
	public static void main(String[] args) {
		//a,b라는 Thread를 만들었슴
		A a = new A();
		B b = new B();
		
		//Thread 시작하는 함수
		a.start();
		b.start();
		
		// 메인 스레드의 작업이 진행
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			a.join(); b.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total=0;
		total=a.getSum()+b.getSum();
		
		System.out.println("프로그램 종료"+total);
	}
}
