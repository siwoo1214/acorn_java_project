package day21.interrupt.실습;



public class Counter extends Thread{
	@Override
	public void run() {
		for(int i=10; i>0; i--) {  //~~동안
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Interrupt발생! 프로그램 종료합니다");
				return;  //인터럽트가 들어오면 실행할 동작을 여기다가 작성하면 된다
			}
			System.out.println(i);
		}
	}
}
