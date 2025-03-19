package day21.데몬.실습;

public class Ex01 {
	public static void main(String[] args) {
		
//		MyDaemon d = new MyDaemon();
//		
//		d.start();
		
		//데몬 스레드로 만들기
		//주의해야 할 점은 start하기 전에 daemon으로 만들어야함
		MyDaemon d = new MyDaemon();
		d.setDaemon(true);
		d.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("프로그램 종료");
	}
}

class MyDaemon extends Thread{
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}